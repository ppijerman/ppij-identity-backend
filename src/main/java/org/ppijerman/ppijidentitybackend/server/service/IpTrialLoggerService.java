package org.ppijerman.ppijidentitybackend.server.service;

import org.ppijerman.ppijidentitybackend.server.dto.IpTrialLog;
import org.ppijerman.ppijidentitybackend.server.repository.IpRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class IpTrialLoggerService {
    IpRepository ipRepository;

    @Autowired
    public IpTrialLoggerService(IpRepository ipRepository) {
        this.ipRepository = ipRepository;
    }

    public void addFailTrialToIp(String ip) {
        IpTrialLog ipTrialLog = ipRepository.findById(ip).orElse(new IpTrialLog(ip, 1));
        ipRepository.save(ipTrialLog);
    }

    public int getTrialErrorCountForIp(String ip) {
        return ipRepository.findById(ip).orElse(new IpTrialLog( ip, 0)).getTrialCount();
    }

    public boolean resetTrialForIp(String ip) {
        boolean isExists = ipRepository.findById(ip).isPresent();
        ipRepository.deleteById(ip);
        return isExists;
    }
}
