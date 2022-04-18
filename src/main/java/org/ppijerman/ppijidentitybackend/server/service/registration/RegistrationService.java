package org.ppijerman.ppijidentitybackend.server.service.registration;

import lombok.AllArgsConstructor;
import org.ppijerman.ppijidentitybackend.server.service.appuser.AppUser;
import org.ppijerman.ppijidentitybackend.server.service.appuser.AppUserRole;
import org.ppijerman.ppijidentitybackend.server.service.appuser.AppUserService;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class RegistrationService {

    private final AppUserService appUserService;
    private final EmailValidator emailValidator;

    public String register(RegistrationRequest request) {
        boolean isValidEmail = emailValidator.test(request.getEmail());
        if (!isValidEmail) {
            throw new IllegalStateException("email not valid");
        }
        return appUserService.signUpUser(new AppUser(
                request.getFirstName(),
                request.getLastName(),
                request.getEmail(),
                request.getPassword(),
                AppUserRole.USER));
    }
}
