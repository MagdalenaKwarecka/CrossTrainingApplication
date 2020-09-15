package com.kvarek.workout.service.person;

import com.kvarek.workout.model.Person;
import com.kvarek.workout.repository.PersonRepository;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collections;

@Service
public class PersonDetailsService implements UserDetailsService {

    private final PersonRepository personRepository;

    public PersonDetailsService(PersonRepository personRepository) {
        this.personRepository = personRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String login) throws UsernameNotFoundException {
        Person person = personRepository.findByLogin(login);
        if (person == null) throw new UsernameNotFoundException(login);

        GrantedAuthority grantedAuthority = new SimpleGrantedAuthority(person.getRole().name());
        return new org.springframework.security.core.userdetails.User(person.getLogin(), person.getPassword(), Collections.singleton(grantedAuthority));
    }

}
