package org.ppijerman.ppijidentitybackend.server.service.security.dao.authentication;

import org.ppijerman.ppijidentitybackend.server.service.security.crypto.CryptoService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.stereotype.Component;

@Component
public class PpijIdAuthenticationProvider extends DaoAuthenticationProvider {

    private final Logger log = LoggerFactory.getLogger(PpijIdAuthenticationProvider.class);

    @Autowired
    public PpijIdAuthenticationProvider(
        CryptoService cryptoService,
        PpijIdUserDetailsService ppijIdUserDetailsService
    ) {
        this.setPasswordEncoder(cryptoService);
        this.setUserDetailsService(ppijIdUserDetailsService);
        this.setUserDetailsPasswordService(ppijIdUserDetailsService);
    }
}
