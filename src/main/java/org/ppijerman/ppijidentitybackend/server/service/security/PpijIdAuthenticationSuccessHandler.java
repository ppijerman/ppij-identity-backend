package org.ppijerman.ppijidentitybackend.server.service.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
public class PpijIdAuthenticationSuccessHandler implements AuthenticationSuccessHandler {

    private final IpRateLimiterService ipRateLimiterService;

    private final String LOGIN_SUCCESS_REDIRECT_PATH;

    @Autowired
    public PpijIdAuthenticationSuccessHandler(
            IpRateLimiterService ipRateLimiterService,
            @Value("${ppij-id.security.login-success-redirect-path:/}") String loginSuccessPath
    ) {
        this.ipRateLimiterService = ipRateLimiterService;
        this.LOGIN_SUCCESS_REDIRECT_PATH = loginSuccessPath;
    }

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
        final String ip = request.getRemoteAddr();
        ipRateLimiterService.resetTrialForIp(ip);
        response.sendRedirect(LOGIN_SUCCESS_REDIRECT_PATH);
    }
}
