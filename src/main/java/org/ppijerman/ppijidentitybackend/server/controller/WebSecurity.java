package org.ppijerman.ppijidentitybackend.server.controller;

import org.ppijerman.ppijidentitybackend.server.service.security.api.authentication.PpijIdApiAuthenticationFilter;
import org.ppijerman.ppijidentitybackend.server.service.security.dao.authentication.PpijIdAuthenticationFailureHandler;
import org.ppijerman.ppijidentitybackend.server.service.security.dao.authentication.PpijIdAuthenticationFilter;
import org.ppijerman.ppijidentitybackend.server.service.security.dao.authentication.PpijIdAuthenticationSuccessHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

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
                .and()
                .addFilterBefore(new PpijIdApiAuthenticationFilter(new AntPathRequestMatcher("/api/**")), PpijIdAuthenticationFilter.class)
                .csrf()
                .and()
                .authorizeRequests()
                .antMatchers(
                        "/",
                        "/privacy-policy",
                        "/about",
                        "/register"
                ).permitAll()
                .anyRequest()
                .authenticated()
                .and()
                .formLogin()
                    .loginPage("/login")
                    .usernameParameter("email")
                    .successHandler(this.ppijIdAuthenticationSuccessHandler)
                    .failureHandler(this.ppijIdAuthenticationFailureHandler)
                    .permitAll()
                .and()
                .logout()
                    .logoutUrl("/logout")
                    .invalidateHttpSession(true)
                    .clearAuthentication(true)
                    .permitAll();
    }
}
