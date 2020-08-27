package com.kvarek.workout.controller;


import com.kvarek.registration.validation.PersonValidator;
import com.kvarek.workout.model.Person;
import com.kvarek.workout.model.PersonRole;
import com.kvarek.workout.service.person.PersonService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@RestController
@RequestMapping("/person")
public class PersonController {

    PersonService personService;
    PersonValidator personValidator;

    public PersonController(PersonService personService, PersonValidator personValidator)
    {
        this.personService = personService;
        this.personValidator=personValidator;
    }

    @PostMapping("/saveAthlete")
    public ResponseEntity<String> save (@RequestBody Person person){
        return personValidator.athleteMessageToCoach(person);
    }

    @PutMapping("/updateAthlete")
    public ResponseEntity<String> update (@RequestBody Person person) {
       return this.personValidator.athleteMessage(person);
      }

    @DeleteMapping("/delete")
    public ResponseEntity<String> delete(@RequestBody Person person) {
        this.personService.delete(person);
        return new ResponseEntity<>("użytkownik został usunięty z bazy danych", HttpStatus.ACCEPTED);
    }

    @GetMapping("/findById")
    public ResponseEntity<Person> findById(Long id) {
        try {
            return ResponseEntity.ok(this.personService.findById(id));
        } catch (IllegalArgumentException e) {
            Person notFound = new Person();
            notFound.setFirstName(String.format("osoby o id %d nie ma w bazie danych", id));
            return new ResponseEntity<>(notFound, HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/findByLoginOrEmail")
    public ResponseEntity<Person> findByLoginOrEmail(@RequestParam(required = false) String login, @RequestParam(required = false) String email) {
        try {
            return ResponseEntity.ok(this.personService.findByLoginOrEmail(login, email));

        } catch (IllegalArgumentException e) {
            Person notFound = new Person();
            notFound.setFirstName("takiej osoby nie ma w bazie danych");
            return new ResponseEntity<>(notFound, HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/findAllByLastNameContaining")
    public ResponseEntity<List<Person>> findAllByLastNameContaining(String lastName) {
        try {
            return ResponseEntity.ok(this.personService.findAllByLastNameContaining(lastName));
        } catch (IllegalArgumentException e) {
            Person notFound = new Person();
            notFound.setLastName("Takiej osoby nie ma na liście");
            return new ResponseEntity<>(new ArrayList<>(Collections.singletonList(notFound)), HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/findAllByRoleOrderByLastName")
    public ResponseEntity<List<Person>> findAllByRoleOrderByLastName(@RequestParam PersonRole role) {
        return ResponseEntity.ok(this.personService.findAllByRoleOrderByLastName(role));
    }


}
