package org.ppijerman.ppijidentitybackend.server.service.registration;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class RegistrationService {
    public String register(RegistrationRequest request) {
        return "it works";
    }
}
