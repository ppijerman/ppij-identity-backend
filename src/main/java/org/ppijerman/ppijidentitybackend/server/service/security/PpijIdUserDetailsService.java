package org.ppijerman.ppijidentitybackend.server.service.security;

import org.ppijerman.ppijidentitybackend.server.repository.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsPasswordService;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class PpijIdUserDetailsService implements UserDetailsService, UserDetailsPasswordService {

    private final Logger log = LoggerFactory.getLogger(PpijIdUserDetailsService.class);

    private final UserRepository userRepository;

    @Autowired
    public PpijIdUserDetailsService(
            UserRepository userRepository
    ) {
        this.userRepository = userRepository;
    }


    // TODO after repository done
    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        log.trace("Loading user with email {} within user details service.", email);
        return null;
    }

    // TODO after repository done
    @Override
    public UserDetails updatePassword(UserDetails user, String newPassword) {
        log.trace("Updating user password with email {}.", user.getUsername());
        return null;
    }
}
