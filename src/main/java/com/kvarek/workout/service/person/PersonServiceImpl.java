package com.kvarek.workout.service.person;


import com.kvarek.registration.email.EmailSenderImpl;

import com.kvarek.workout.model.Person;
import com.kvarek.workout.model.PersonRole;
import com.kvarek.workout.repository.PersonRepository;
import net.bytebuddy.utility.RandomString;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class PersonServiceImpl implements IPersonService {


    private final PersonRepository personRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;
    private final EmailSenderImpl emailSender;



    RandomString random = new RandomString();
    String generatedPassword = random.nextString();

    @Autowired

    PersonServiceImpl(PersonRepository personRepository, BCryptPasswordEncoder bCryptPasswordEncoder, EmailSenderImpl emailSender) {
        this.personRepository = personRepository;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;

        this.emailSender = emailSender;

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
        person.setLogin(person.getEmail());
        person.setPassword(bCryptPasswordEncoder.encode(generatedPassword));
        emailSender.sendEmail(person.getEmail(), "Stworzono konto", "<p>Witaj </p>" +person.getFirstName()+
                "<p>Twoje dane do logowania to:</p><p><br>login: </br></p>"+ person.getEmail()+"</p><p><br>hasło: </br></p>"+ generatedPassword);
        personRepository.save(person);
    }

    @Override
    public void update(Person person) {
        person.setPassword(bCryptPasswordEncoder.encode(person.getPassword()));
        this.personRepository.update(person.getLogin(), person.getPassword());
    }
}

