package org.ppijerman.ppijidentitybackend.server.service.security;

import com.google.common.net.InetAddresses;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;

public class PpijAuthenticationFilter extends UsernamePasswordAuthenticationFilter {

    private final int TRIAL_LIMIT_FOR_EACH_IP;

    private final long COOLWDOWN;

    private final Logger log = LoggerFactory.getLogger(PpijAuthenticationFilter.class);

    private final Map<String, ScheduledFuture<?>> timerMap;
    private final ScheduledExecutorService executorService;

    IpTrialLoggerService ipTrialLoggerService;

    @Autowired
    public PpijAuthenticationFilter(
            IpTrialLoggerService ipTrialLoggerService,
            @Value("${ppij-id.security.lockoff.limit}") int trialLimit,
            @Value("${ppij-id.security.lockoff.cooldown:300}") long cooldown
    ) {
        this.ipTrialLoggerService = ipTrialLoggerService;
        this.timerMap = new HashMap<>();
        this.executorService = Executors.newScheduledThreadPool(1);
        this.TRIAL_LIMIT_FOR_EACH_IP = trialLimit;
        this.COOLWDOWN = cooldown;
    }

    @Override
    @Autowired
    public void setAuthenticationManager(AuthenticationManager authenticationManager) {
        super.setAuthenticationManager(authenticationManager);
    }

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException {
        final String ip = request.getRemoteAddr();
        final String username = obtainUsername(request);
        log.debug("Got authentication attempt from ip {}.", ip);

        if (!checkAndLogIp(ip)) {
            throw new IpRateLimitedException("Ip is rate limited due to many failed trials.");
        }

        return super.attemptAuthentication(request, response);
    }

    public boolean checkAndLogIp(String ip) {
        if (!InetAddresses.isInetAddress(ip)) {
            log.warn("Checked string ({}) is not a valid Ip address", ip);
            return false;
        }
        if (!timerMap.containsKey(ip)) {
            log.debug("Ip address {} is not on cooldown yet, adding the cooldown.", ip);
            this.addCooldown(ip);
        }

        final int trials = ipTrialLoggerService.getTrialErrorCountForIp(ip);

        if (trials < TRIAL_LIMIT_FOR_EACH_IP) {
            log.debug("Checked ip address {} and it does not exceed limited trial ({}/{}).", ip, trials, TRIAL_LIMIT_FOR_EACH_IP);
            this.ipTrialLoggerService.addTrialToIp(ip);
            return true;
        } else {
            log.debug("Checked ip address {} and it exceeds limited amount of trial ({}/{}).", ip, trials, TRIAL_LIMIT_FOR_EACH_IP);
            return false;
        }
    }

    public void resetTrialForIp(String ip) {
        if (!InetAddresses.isInetAddress(ip)) {
            log.warn("Trying to reset trial for invalid ip {}.", ip);
        } else {
            this.ipTrialLoggerService.resetTrialForIp(ip);
            this.timerMap.remove(ip);
            log.debug("Reset trial and removed from timer map for ip {}.", ip);
        }
    }

    private void addCooldown(String ip) {
        final Runnable timer = () -> this.resetTrialForIp(ip);
        this.timerMap.put(ip, executorService.schedule(timer, COOLWDOWN, TimeUnit.SECONDS));
    }
}