package com.kvarek.service;


import com.kvarek.model.Person;
import com.kvarek.model.PersonRole;
import com.kvarek.repository.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class PersonServiceImpl implements IPersonService {


    private PersonRepository personRepository;
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    PersonServiceImpl(PersonRepository personRepository, BCryptPasswordEncoder bCryptPasswordEncoder){
        this.personRepository=personRepository;
        this.bCryptPasswordEncoder=bCryptPasswordEncoder;

    }

    @Override
    public void saveCoach(Person person) {
        person.setPassword(bCryptPasswordEncoder.encode(person.getPassword()));
        person.setRole(PersonRole.COACH);
        personRepository.save(person);
    }

    @Override
    public Person findByLogin(String login) {
        return personRepository.findByLogin(login);
    }
}

