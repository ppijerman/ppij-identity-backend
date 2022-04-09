package org.ppijerman.ppijidentitybackend.server.service.security;

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

    public void addTrialToIp(String ip) {
        IpTrialLog ipTrialLog = this.ipRepository.findById(ip).orElse(new IpTrialLog(ip, 1));
        this.ipRepository.save(ipTrialLog);
    }

    public int getTrialErrorCountForIp(String ip) {
        return this.ipRepository.findById(ip).orElse(new IpTrialLog( ip, 0)).getTrialCount();
    }

    public boolean resetTrialForIp(String ip) {
        boolean isExists = this.ipRepository.findById(ip).isPresent();
        this.ipRepository.deleteById(ip);
        return isExists;
    }
}
