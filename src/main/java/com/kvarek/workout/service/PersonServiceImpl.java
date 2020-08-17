package com.kvarek.workout.service;


import com.kvarek.workout.model.Person;
import com.kvarek.workout.model.PersonRole;
import com.kvarek.workout.repository.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class PersonServiceImpl implements IPersonService {


    private final PersonRepository personRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

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

    @Override
    public void saveAthlete(Person person) {
        person.setRole(PersonRole.ATHLETE);
        personRepository.save(person);
    }
}

