package org.ppijerman.ppijidentitybackend.server.service;

import org.ppijerman.ppijidentitybackend.server.PersonRepository;
import org.ppijerman.ppijidentitybackend.server.dto.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

public class personDetailsService implements UserDetailsService {
    @Autowired
    private PersonRepository personRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Person person = personRepository.findByEmail(username);
        if (person == null) {
            throw new UsernameNotFoundException("User not found");
        }
        return new personDetail(person);
    }
}
