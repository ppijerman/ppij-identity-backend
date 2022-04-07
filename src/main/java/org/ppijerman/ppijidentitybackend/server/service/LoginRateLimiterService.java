package org.ppijerman.ppijidentitybackend.server.service;

import com.google.common.net.InetAddresses;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;

@Service
public class LoginRateLimiterService {
    @Value("${ppij-id.security.lockoff.limit}")
    private int TRIAL_LIMIT_FOR_EACH_IP;

    @Value("${ppij-id.security.lockoff.cooldown:30}")
    private long COOLWDOWN;

    private final Logger log = LoggerFactory.getLogger(LoginRateLimiterService.class);
    private final Map<String, ScheduledFuture<?>> timerMap;
    private final ScheduledExecutorService executorService;

    IpTrialLoggerService ipTrialLoggerService;

    @Autowired
    public LoginRateLimiterService(IpTrialLoggerService ipTrialLoggerService) {
        this.ipTrialLoggerService = ipTrialLoggerService;
        this.timerMap = new HashMap<>();
        this.executorService = Executors.newScheduledThreadPool(1);
    }

    public boolean checkAndLogIp(String ip) {
        if (!InetAddresses.isInetAddress(ip)) {
            log.warn("Checked string ({}) is not a valid Ip address", ip);
            return false;
        }

        final int trials = ipTrialLoggerService.getTrialErrorCountForIp(ip);

        if (trials < TRIAL_LIMIT_FOR_EACH_IP) {
            log.debug("Checked ip address {} and it does not exceed limited trial ({}/{}).", ip, trials, TRIAL_LIMIT_FOR_EACH_IP);
            this.ipTrialLoggerService.addTrialToIp(ip);
            return true;
        } else {
            log.debug("Checked ip address {} and it exceeds limited amount of trial ({}/{}).", ip, trials, TRIAL_LIMIT_FOR_EACH_IP);
            if (!timerMap.containsKey(ip)) {
                log.debug("Ip address {} is not on cooldown yet, adding the cooldown.", ip);
                this.addCooldown(ip);
            }
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
