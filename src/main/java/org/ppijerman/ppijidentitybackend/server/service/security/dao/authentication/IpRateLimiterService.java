package org.ppijerman.ppijidentitybackend.server.service.security.dao.authentication;

import com.google.common.net.InetAddresses;
import org.ppijerman.ppijidentitybackend.server.data.dto.IpTrialLog;
import org.ppijerman.ppijidentitybackend.server.data.repository.IpRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;

@Service
public class IpRateLimiterService {

    private final Logger log = LoggerFactory.getLogger(IpRateLimiterService.class);

    private final short TRIAL_LIMIT_FOR_EACH_IP;
    private final int COOLWDOWN;

    private final IpRepository ipRepository;
    private final Map<String, ScheduledFuture<?>> timerMap;
    private final ScheduledExecutorService executorService;

    @Autowired
    public IpRateLimiterService(
            IpRepository ipRepository,
            @Value("${ppij-id.security.lockoff.limit:5}") short trialLimit,
            @Value("${ppij-id.security.lockoff.cooldown:300}") int cooldown
    ) {
        this.ipRepository = ipRepository;
        this.TRIAL_LIMIT_FOR_EACH_IP = trialLimit;
        this.COOLWDOWN = cooldown;
        this.timerMap = new HashMap<>();
        this.executorService = Executors.newScheduledThreadPool(1);
    }

    public void addTrialErrorToIp(String ip) {
        if (!InetAddresses.isInetAddress(ip)) {
            log.warn("Checked string ({}) is not a valid Ip address", ip);
            return;
        }

        Optional<IpTrialLog> ipTrialLogOpt = this.ipRepository.findById(ip);
        addTrialErrorToIp(ip, ipTrialLogOpt);
    }

    private void addTrialErrorToIp(String ip, Optional<IpTrialLog> ipTrialLogOpt) {
        IpTrialLog ipTrialLog = ipTrialLogOpt.orElse(
                IpTrialLog.builder()
                        .ipTrialLogAddress(ip)
                        .ipTrialLogCount((short) 0)
                        .build()
        );

        ipTrialLog.setIpTrialLogCount((short) (ipTrialLog.getIpTrialLogCount() + 1));
        Calendar timestamp = Calendar.getInstance();
        timestamp.setTimeInMillis(Instant.now().toEpochMilli());
        ipTrialLog.setIpTrialLogLastTimestamp(timestamp);

        this.ipRepository.save(ipTrialLog);

    }

    public void resetTrialForIp(String ip) {
        if (!InetAddresses.isInetAddress(ip)) {
            log.warn("Trying to reset trial for invalid ip {}.", ip);
            return;
        }
        resetTrialForIp(this.ipRepository.findById(ip));
    }

    private void resetTrialForIp(Optional<IpTrialLog> ipTrialLog) {
        if (ipTrialLog.isPresent()) {
            String ipAddress = ipTrialLog.get().getIpTrialLogAddress();
            this.ipRepository.deleteById(ipAddress);
            log.debug("Reset trial for ip {}.", ipAddress);
        } else {
            log.debug("Tried to delete trial although ip does not shows up.");
        }
    }

    public boolean checkIpIsNotRateLimited(String ip) {
        if (!InetAddresses.isInetAddress(ip)) {
            log.warn("Checked string ({}) is not a valid Ip address", ip);
            return false;
        }

        return checkIpIsNotRateLimited(ip, this.ipRepository.findById(ip));
    }

    private boolean checkIpIsNotRateLimited(String ip, Optional<IpTrialLog> ipTrialLog) {
        if (ipTrialLog.isEmpty()) {
            return true;
        }

        final short trials = getTrialErrorCountForIp(ipTrialLog);
        final Calendar upperLimitTimestamp = getTimestampForIp(ipTrialLog);
        upperLimitTimestamp.add(Calendar.SECOND, COOLWDOWN);

        if (Instant.now().isBefore(upperLimitTimestamp.toInstant())) {
            if (trials > TRIAL_LIMIT_FOR_EACH_IP) {
                log.debug("Checked ip address {} and it exceeds limited amount of trial ({}/{}).", ipTrialLog.get().getIpTrialLogAddress(), trials, TRIAL_LIMIT_FOR_EACH_IP);
                return false;
            } else {
                addTrialErrorToIp(ip, ipTrialLog);
                return true;
            }
        }

        this.resetTrialForIp(ipTrialLog);
        return true;
    }

    private short getTrialErrorCountForIp(String ip) {
        if (!InetAddresses.isInetAddress(ip)) {
            log.warn("Checked string ({}) is not a valid Ip address", ip);
            return 0;
        }

        Optional<IpTrialLog> ipTrialLog = this.ipRepository.findById(ip);
        return getTrialErrorCountForIp(ipTrialLog);
    }

    private Calendar getTimestampForIp(String ip) {
        if (!InetAddresses.isInetAddress(ip)) {
            log.warn("Checked string ({}) is not a valid Ip address", ip);
            return null;
        }

        Optional<IpTrialLog> ipTrialLog = this.ipRepository.findById(ip);
        return getTimestampForIp(ipTrialLog);
    }

    private short getTrialErrorCountForIp(Optional<IpTrialLog> ipTrialLog) {
        return ipTrialLog.map(IpTrialLog::getIpTrialLogCount).orElse((short) 0);
    }

    private Calendar getTimestampForIp(Optional<IpTrialLog> ipTrialLog) {
        return ipTrialLog.map(IpTrialLog::getIpTrialLogLastTimestamp).orElse(null);
    }
}
