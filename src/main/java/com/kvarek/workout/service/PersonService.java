package com.kvarek.workout.service;


import com.kvarek.workout.model.Person;
import com.kvarek.workout.model.PersonRole;
import com.kvarek.workout.repository.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class PersonService implements UserDetailsService {

    @Autowired
    PersonRepository personRepository;

    public PersonService(PersonRepository personRepository) {
        this.personRepository = personRepository;
    }

    public UserDetails loadUserByUsername(String login) {
        Person person = personRepository.findByLogin(login);
        if (person == null) throw new UsernameNotFoundException(login);

        GrantedAuthority grantedAuthority = new SimpleGrantedAuthority(person.getRole().name());
        return new org.springframework.security.core.userdetails.User(person.getLogin(), person.getPassword(), Collections.singleton(grantedAuthority));
    }

    public void delete(Person person) {
        this.personRepository.delete(person);
    }

    public void deleteByLogin(String login) throws IllegalArgumentException {
        this.personRepository.deleteByLogin(login);
    }

    public Person save(Person person) {
        return this.personRepository.save(person);
    }

    public Person findById(Long id) throws IllegalArgumentException {
        Optional<Person> person = this.personRepository.findById(id);
        person.orElseThrow(IllegalArgumentException::new);
        return person.get();
    }


    public Person findByLoginOrEmail(String login, String email) throws IllegalArgumentException {
        Optional<Person> person = this.personRepository.findByLoginOrEmail(login, email);
        person.orElseThrow(IllegalArgumentException::new);
        return person.get();
    }

    public List<Person> findAllByLastNameContaining(String lastName) throws IllegalArgumentException {
        Optional<List<Person>> people = this.personRepository.findAllByLastNameContaining(lastName);
        return people.orElseThrow(IllegalArgumentException::new);
    }

    public List<Person> findAllByRoleOrderByLastName(PersonRole role) {
        return this.personRepository.findAllByRoleOrderByLastName(role);
    }

    public Boolean existsPersonByLogin(String login) {
        return this.personRepository.existsPersonByLogin(login);
    }

    public Boolean existsPersonByEmail(String email) {
        return this.personRepository.existsPersonByEmail(email);
    }


}
