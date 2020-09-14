package com.kvarek.registration;

import com.kvarek.registration.validation.PersonValidator;
import com.kvarek.workout.model.Person;
import com.kvarek.registration.validation.LoginCredentials;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/registration")
public class RegistrationController {


    private final PersonValidator personValidator;


    public RegistrationController(PersonValidator personValidator) {

        this.personValidator = personValidator;
    }

    @PostMapping("/saveCoach")
    public ResponseEntity<String> save(@RequestBody Person person) {
        return personValidator.coachMessage(person);
    }


    @PostMapping("/login")
   public void login(@RequestBody LoginCredentials credentials) {

   }}