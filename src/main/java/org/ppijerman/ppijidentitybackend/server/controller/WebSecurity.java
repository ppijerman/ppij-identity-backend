package org.ppijerman.ppijidentitybackend.server.controller;

import org.ppijerman.ppijidentitybackend.server.service.security.PpijIdAuthenticationFailureHandler;
import org.ppijerman.ppijidentitybackend.server.service.security.PpijIdAuthenticationSuccessHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
@EnableWebSecurity
public class WebSecurity extends WebSecurityConfigurerAdapter {

    private final PpijIdAuthenticationSuccessHandler ppijIdAuthenticationSuccessHandler;
    private final PpijIdAuthenticationFailureHandler ppijIdAuthenticationFailureHandler;

    @Autowired
    public WebSecurity(
            PpijIdAuthenticationSuccessHandler ppijIdAuthenticationSuccessHandler,
            PpijIdAuthenticationFailureHandler ppijIdAuthenticationFailureHandler
    ){
        this.ppijIdAuthenticationSuccessHandler = ppijIdAuthenticationSuccessHandler;
        this.ppijIdAuthenticationFailureHandler = ppijIdAuthenticationFailureHandler;
    }

    @Override
    protected void configure(HttpSecurity httpSecurity) throws Exception {
        httpSecurity
                .cors()
                .and().authorizeRequests()
                .antMatchers(
                        "/",
                        "/privacy-policy",
                        "/about",
                        "/register"
                ).permitAll()
                .anyRequest().authenticated()
                .and()
                .formLogin()
                    .loginPage("/login")
                    .permitAll()
                    .successHandler(this.ppijIdAuthenticationSuccessHandler)
                    .failureHandler(this.ppijIdAuthenticationFailureHandler)
                .and().logout().logoutUrl("/logout").invalidateHttpSession(true).clearAuthentication(true).permitAll();
    }
}
