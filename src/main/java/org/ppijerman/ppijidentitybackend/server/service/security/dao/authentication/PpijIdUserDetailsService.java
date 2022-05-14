package org.ppijerman.ppijidentitybackend.server.service.security.dao.authentication;

import org.ppijerman.ppijidentitybackend.server.data.dto.Person;
import org.ppijerman.ppijidentitybackend.server.data.repository.PersonRepository;
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

    private PersonRepository personRepository;

    @Autowired
    public PpijIdUserDetailsService(
            PersonRepository personRepository
    ) {
        this.personRepository = personRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        log.trace("Loading user with email {} within user details service.", email);
        return new PpijUserDetails(personRepository.findByPersonEmail(email));
    }

    @Override
    public UserDetails updatePassword(UserDetails user, String newPassword) {
        log.trace("Updating user password with email {}.", user.getUsername());

        if (!(user instanceof PpijUserDetails)) {
            throw new IllegalArgumentException("User is not instance of PPIJ User Details");
        }

        PpijUserDetails ppijUserDetails = (PpijUserDetails) user;
        Person person = ppijUserDetails.getPerson();
        person.setPersonPassword(newPassword);
        return new PpijUserDetails(person);
    }
}
