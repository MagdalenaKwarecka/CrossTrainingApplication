package com.kvarek.registration;

import com.kvarek.registration.validation.PersonValidator;
import com.kvarek.workout.model.Person;
import com.kvarek.workout.service.PersonService;
import com.kvarek.workout.service.PersonServiceImpl;
import com.kvarek.registration.validation.LoginCredentials;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
//@RequestMapping("/registration")
public class RegistrationController {

    private final PersonService personService;
    private final PersonServiceImpl personServiceImpl;
    private final PersonValidator personValidator;

    public RegistrationController(PersonService personService, PersonServiceImpl personServiceImpl, PersonValidator personValidator) {
        this.personService = personService;
        this.personServiceImpl = personServiceImpl;
        this.personValidator = personValidator;
    }

    @PostMapping("/saveCoach")
    public ResponseEntity<String> save(@RequestBody Person person) {
        return personValidator.coachMessage(person);
    }


    @PostMapping("/login")
    public void login(@RequestBody LoginCredentials credentials) {
    }
}
