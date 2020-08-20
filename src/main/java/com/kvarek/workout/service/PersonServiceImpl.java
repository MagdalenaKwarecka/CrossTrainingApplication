package com.kvarek.workout.service;


import com.kvarek.registration.email.EmailSenderImpl;
import com.kvarek.workout.model.Person;
import com.kvarek.workout.model.PersonRole;
import com.kvarek.workout.repository.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

@Service
public class PersonServiceImpl implements IPersonService {


    private final PersonRepository personRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;
    private final EmailSenderImpl emailSender;
    private final TemplateEngine templateEngine;

    @Autowired
    PersonServiceImpl(PersonRepository personRepository, BCryptPasswordEncoder bCryptPasswordEncoder, EmailSenderImpl emailSender, TemplateEngine templateEngine){
        this.personRepository=personRepository;
        this.bCryptPasswordEncoder=bCryptPasswordEncoder;
        this.emailSender = emailSender;
        this.templateEngine = templateEngine;
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
        Context context=new Context();
        String body=templateEngine.process("template", context);
        person.setRole(PersonRole.ATHLETE);
        emailSender.sendEmail(person.getEmail(), "Stworzono konto", body);
        personRepository.save(person);
    }

    @Override
    public void update(Person person) {
        person.setPassword(bCryptPasswordEncoder.encode(person.getPassword()));
        this.personRepository.update(person.getId(), person.getLogin(), person.getPassword());
    }
}

