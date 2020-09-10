package com.kvarek.registration;

import com.kvarek.registration.email.EmailSenderImpl;
import com.kvarek.registration.validation.PersonValidator;
import com.kvarek.workout.model.Person;
import com.kvarek.workout.service.person.PersonService;
import com.kvarek.workout.service.person.PersonServiceImpl;
import com.kvarek.registration.validation.LoginCredentials;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
//@RequestMapping("/registration")
public class RegistrationController {

   /* private final PersonService personService;
    private final PersonServiceImpl personServiceImpl;*/
    private final PersonValidator personValidator;
  /*  private final EmailSenderImpl emailSender;*/

    public RegistrationController(/*PersonService personService, PersonServiceImpl personServiceImpl, */PersonValidator personValidator/*, EmailSenderImpl emailSender*/) {
        /*this.personService = personService;
        this.personServiceImpl = personServiceImpl;*/
        this.personValidator = personValidator;
        /*this.emailSender = emailSender;*/
    }

    @PostMapping("/saveCoach")
    public ResponseEntity<String> save(@RequestBody Person person) {
        return personValidator.coachMessage(person);
    }


    @PostMapping("/login")
    public void login(@RequestBody LoginCredentials credentials) {
    }

}
