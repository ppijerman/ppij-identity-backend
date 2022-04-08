package org.ppijerman.ppijidentitybackend.server.service.email;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

public class NotificationRequest {

    // TODO: User's credentials during registration
    @Getter
    @Setter
    @Email
    private String email;

    @Getter
    @Setter
    @NotBlank
    private String content;

}
