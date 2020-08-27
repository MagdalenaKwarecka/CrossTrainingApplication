package com.kvarek.workout.service.person;


import com.kvarek.registration.email.EmailSenderImpl;
import com.kvarek.workout.model.Person;
import com.kvarek.workout.model.PersonRole;
import com.kvarek.workout.repository.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class PersonService {


    private final PersonRepository personRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;
    private final EmailSenderImpl emailSender;

    @Autowired
    public PersonService(PersonRepository personRepository, BCryptPasswordEncoder bCryptPasswordEncoder, EmailSenderImpl emailSender) {
        this.personRepository = personRepository;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
        this.emailSender = emailSender;
    }

    public void delete(Person person) {
        this.personRepository.delete(person);
    }

    public void deleteByLogin(String login) throws IllegalArgumentException {
        this.personRepository.deleteByLogin(login);
    }

    public void saveCoach(Person person) {
        person.setPassword(bCryptPasswordEncoder.encode(person.getPassword()));
        person.setRole(PersonRole.COACH);
        personRepository.save(person);
    }

    public void saveAthlete(Person person) {
        person.setRole(PersonRole.ATHLETE);
        emailSender.sendEmail(person.getEmail(), "Cześć :)", "<p>Pozdrawiam serdecznie, Magda</p>") ;
        personRepository.save(person);
    }

    public void update(Person person) {
        person.setPassword(bCryptPasswordEncoder.encode(person.getPassword()));
        this.personRepository.update(person.getEmail(), person.getLogin(), person.getPassword());
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
