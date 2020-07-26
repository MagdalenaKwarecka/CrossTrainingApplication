package com.kvarek.service;

import com.kvarek.model.Person;
import com.kvarek.model.PersonRole;
import com.kvarek.repository.PersonRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PersonService {

    PersonRepository personRepository;

    public PersonService(PersonRepository personRepository){
        this.personRepository=personRepository;
    }
    public void delete(Person person){this.personRepository.delete(person);}

    public void deleteByLogin(String login) throws IllegalArgumentException{
        this.personRepository.deleteByLogin(login);
    }

    public Person save(Person person){
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

    public List<Person> findAllByLastNameContaining (String lastName) throws IllegalArgumentException{
        Optional <List<Person>> people = this.personRepository.findAllByLastNameContaining(lastName);
        return people.orElseThrow(IllegalArgumentException::new);
    }

    public List<Person> findAllByRoleOrderByLastName(PersonRole role){
        return this.personRepository.findAllByRoleOrderByLastName(role);
    }

    public Boolean existsPersonByLogin (String login)
    {
        return this.personRepository.existsPersonByLogin(login);
    }

    public Boolean existsPersonByEmail (String email){
        return this.personRepository.existsPersonByEmail(email);
    }

}

