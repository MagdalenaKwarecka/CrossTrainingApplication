package com.kvarek.controller;


import com.kvarek.model.Person;
import com.kvarek.model.PersonRole;
import com.kvarek.service.PersonService;
import com.kvarek.service.PersonServiceImpl;
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
    PersonServiceImpl personServiceImpl;

    public PersonController(PersonService personService, PersonServiceImpl personServiceImpl)
    {
        this.personService = personService;
        this.personServiceImpl=personServiceImpl;
    }

    @PostMapping("/initial")
    public String createInitialData() {


        Person person1 = new Person(PersonRole.COACH,"Jan", "Myśliński", "jmyslinski@wp.pl", "myslin", "jan1mys12");
        Person person2 = new Person(PersonRole.COACH, "Zofia", "Przybył", "przybylzof@gmail.com", "zochenka", "qwerty12345");
        Person person3 = new Person(PersonRole.ATHLETE,"Karol", "Zawitkowski", "zawkar@gmail.com", "zawit", "mojehaslo12");
        Person person4 = new Person(PersonRole.ATHLETE,"Anna", "Badurska", "banna@vp.pl", "annaba", "bananaana123");
        Person person5 = new Person(PersonRole.ATHLETE,"Marta", "Kowalik", "marta.kowalik@o2.pl", "kowalik", "kowal21!1548");
        Person person6 = new Person(PersonRole.ATHLETE,"Filip", "Musiał", "filipm@gmail.com", "fifi", "kjh772879kjh");

        this.personService.save(person1);
        this.personService.save(person2);
        this.personService.save(person3);
        this.personService.save(person4);
        this.personService.save(person5);
        this.personService.save(person6);

        return "dodano inicjalne dane";
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
