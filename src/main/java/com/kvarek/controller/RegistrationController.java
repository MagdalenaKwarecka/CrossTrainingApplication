package com.kvarek.controller;

import com.kvarek.model.Person;
import com.kvarek.service.PersonService;
import com.kvarek.service.PersonServiceImpl;
import com.kvarek.validator.LoginCredentials;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
//@RequestMapping("/registration")
public class RegistrationController {

    private PersonService personService;
    private PersonServiceImpl personServiceImpl;

    public RegistrationController(PersonService personService, PersonServiceImpl personServiceImpl) {
        this.personService = personService;
        this.personServiceImpl = personServiceImpl;
    }

    @PostMapping("/saveCoach")
    public ResponseEntity<String> save(@RequestBody Person person) {
        {

            if (this.personService.existsPersonByEmail(person.getEmail())) {
                return new ResponseEntity<>("email jest już w bazie danych", HttpStatus.BAD_REQUEST);
            } else if (this.personService.existsPersonByLogin(person.getLogin())) {
                return new ResponseEntity<>("taki login jest już w zajęty", HttpStatus.BAD_REQUEST);
            }   else if(!person.getPassword().equals(person.getMatchingPassword())){
                return new ResponseEntity<>("hasła nie są zgodne", HttpStatus.BAD_REQUEST);
            } else {
                this.personServiceImpl.saveCoach(person);
                return new ResponseEntity<>("trenera dodano do bazy danych", HttpStatus.CREATED);
            }
        }
    }

    @PostMapping("/login")
    public void login (@RequestBody LoginCredentials credentials){
    }
}
