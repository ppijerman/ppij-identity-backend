package org.ppijerman.ppijidentitybackend.server.service.security.dao.authentication;

import lombok.Getter;
import org.ppijerman.ppijidentitybackend.server.data.dto.Person;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;

public class PpijUserDetails implements UserDetails {

    @Getter
    private final Person person;

    PpijUserDetails(Person person) {
        this.person = person;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of();
    }

    @Override
    public String getPassword() {
        return person.getPersonPassword();
    }

    @Override
    public String getUsername() {
        return person.getPersonEmail();
    }

    @Override
    public boolean isAccountNonExpired() {
        return !person.isExpired();
    }

    @Override
    public boolean isAccountNonLocked() {
        return !person.isLocked();
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return !person.isCredentialExpired();
    }

    @Override
    public boolean isEnabled() {
        return person.isActive();
    }
}
