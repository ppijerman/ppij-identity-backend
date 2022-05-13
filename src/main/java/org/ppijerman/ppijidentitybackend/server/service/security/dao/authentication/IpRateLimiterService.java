package org.ppijerman.ppijidentitybackend.server.service.security.dao.authentication;

import com.google.common.net.InetAddresses;
import org.ppijerman.ppijidentitybackend.server.data.dto.IpTrialLog;
import org.ppijerman.ppijidentitybackend.server.data.repository.IpRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;

@Service
public class IpRateLimiterService {

    private final Logger log = LoggerFactory.getLogger(IpRateLimiterService.class);

    private final int TRIAL_LIMIT_FOR_EACH_IP;
    private final long COOLWDOWN;

    private final IpRepository ipRepository;
    private final Map<String, ScheduledFuture<?>> timerMap;
    private final ScheduledExecutorService executorService;

    @Autowired
    public IpRateLimiterService(
            IpRepository ipRepository,
            @Value("${ppij-id.security.lockoff.limit:5}") int trialLimit,
            @Value("${ppij-id.security.lockoff.cooldown:300}") long cooldown
    ) {
        this.ipRepository = ipRepository;
        this.TRIAL_LIMIT_FOR_EACH_IP = trialLimit;
        this.COOLWDOWN = cooldown;
        this.timerMap = new HashMap<>();
        this.executorService = Executors.newScheduledThreadPool(1);
    }

    public boolean addTrialErrorToIp(String ip) {
        if (!InetAddresses.isInetAddress(ip)) {
            log.warn("Checked string ({}) is not a valid Ip address", ip);
            return false;
        }

        if (!timerMap.containsKey(ip)) {
            log.debug("Ip address {} is not on cooldown yet, adding the cooldown.", ip);
            this.addCooldown(ip);
        }

        IpTrialLog ipTrialLog = this.ipRepository.findById(ip).orElse(IpTrialLog.builder().ipTrialLogAddress(ip).ipTrialLogCount((short) 1).build());
        ipTrialLog.setIpTrialLogCount((short) (ipTrialLog.getIpTrialLogCount() + 1));
        this.ipRepository.save(ipTrialLog);
        return true;
    }

    public boolean resetTrialForIp(String ip) {
        if (!InetAddresses.isInetAddress(ip)) {
            log.warn("Trying to reset trial for invalid ip {}.", ip);
            return false;
        }

        boolean isExists = this.ipRepository.findById(ip).isPresent();
        this.ipRepository.deleteById(ip);
        this.timerMap.get(ip).cancel(false);
        this.timerMap.remove(ip);
        log.debug("Reset trial and removed from timer map for ip {}.", ip);
        return isExists;
    }

    public boolean checkIpIsNotRateLimited(String ip) {
        if (!InetAddresses.isInetAddress(ip)) {
            log.warn("Checked string ({}) is not a valid Ip address", ip);
            return false;
        }

        final int trials = getTrialErrorCountForIp(ip);

        if (trials >= TRIAL_LIMIT_FOR_EACH_IP) {
            log.debug("Checked ip address {} and it exceeds limited amount of trial ({}/{}).", ip, trials, TRIAL_LIMIT_FOR_EACH_IP);
            return false;
        }

        return true;
    }

    private void addCooldown(String ip) {
        final Runnable timer = () -> {
            this.resetTrialForIp(ip);
            log.debug("Removed limited ip {} from the lockoff list.", ip);
        };
        this.timerMap.put(ip, executorService.schedule(timer, COOLWDOWN, TimeUnit.SECONDS));
    }

    private int getTrialErrorCountForIp(String ip) {
        return this.ipRepository.findById(ip).orElse(IpTrialLog.builder().ipTrialLogAddress(ip).ipTrialLogCount((short) 1).build()).getIpTrialLogCount();
    }
}
