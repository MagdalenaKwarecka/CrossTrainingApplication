package com.kvarek.workout.service.person;

import com.kvarek.registration.validation.LoginCredentials;
import com.kvarek.workout.model.Person;
import com.kvarek.workout.model.PersonRole;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.util.*;

@Component
public class PersonDetails implements UserDetails {

    @Autowired
    Person person;

    public PersonDetails(String login, String password){//, List<GrantedAuthority> grantedAuthorities) {
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        List<GrantedAuthority> grantedAuthorities = null;


        if (person.getRole().equals("ATHLETE")) {
            GrantedAuthority grantedAuthority = new SimpleGrantedAuthority("ROLE_USER");
            grantedAuthorities = Arrays.asList(grantedAuthority);
        }

        if (person.getRole().equals("COACH")) {
            GrantedAuthority grantedAuthorityUser = new SimpleGrantedAuthority("ROLE_USER");
            GrantedAuthority grantedAuthorityAdmin = new SimpleGrantedAuthority("ROLE_ADMIN");
            grantedAuthorities = Arrays.asList(grantedAuthorityUser, grantedAuthorityAdmin);
        }

        return grantedAuthorities;
    }

    @Override
    public String getPassword() {
        return person.getPassword();
    }

    @Override
    public String getUsername() {
        return person.getLogin();
    }

    @Override
    public boolean isAccountNonExpired() {
        return false;
    }

    @Override
    public boolean isAccountNonLocked() {
        return false;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return false;
    }

    @Override
    public boolean isEnabled() {
        return false;
    }
}
