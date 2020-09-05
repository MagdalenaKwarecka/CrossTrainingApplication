package com.kvarek.registration.validation;

import com.kvarek.workout.model.Person;
import com.kvarek.workout.service.person.PersonService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

@Component
public class PersonValidator implements IPersonValidator {

    private static final String EMAIL_PATTERN = "^[\\w!#$%&'*+/=?`{|}~^-]+(?:\\.[\\w!#$%&'*+/=?`{|}~^-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,6}$";
    private final PersonService personService;

    public PersonValidator(PersonService personService) {
        this.personService = personService;
    }

    @Override
    public ResponseEntity<String> coachMessage(Person person) {

        if (person.getFirstName() == null || person.getFirstName().isEmpty()) {
            return new ResponseEntity<>("Uzupełnij imię", HttpStatus.BAD_REQUEST);
        } else if (person.getLastName() == null || person.getLastName().isEmpty()) {
            return new ResponseEntity<>("Uzupełnij nazwisko", HttpStatus.BAD_REQUEST);
        } else if (person.getLogin() == null || person.getLogin().isEmpty()) {
            return new ResponseEntity<>("Uzupełnij login", HttpStatus.BAD_REQUEST);
        } else if (this.personService.existsPersonByLogin(person.getLogin())) {
            return new ResponseEntity<>("Taki login jest już w zajęty", HttpStatus.BAD_REQUEST);
        } else if (person.getEmail() == null || person.getEmail().isEmpty()) {
            return new ResponseEntity<>("Uzupełnij email", HttpStatus.BAD_REQUEST);
        } else if (!person.getEmail().matches(EMAIL_PATTERN)) {
            return new ResponseEntity<>("Wpisz prawidłowy email", HttpStatus.BAD_REQUEST);
        } else if (this.personService.existsPersonByEmail(person.getEmail())) {
            return new ResponseEntity<>("Email jest już w bazie danych", HttpStatus.BAD_REQUEST);
        } else if (person.getPassword() == null || person.getPassword().isEmpty()) {
            return new ResponseEntity<>("Uzupełnij hasło", HttpStatus.BAD_REQUEST);
        } else if (person.getMatchingPassword() == null || person.getMatchingPassword().isEmpty()) {
            return new ResponseEntity<>("Powtórz hasło", HttpStatus.BAD_REQUEST);
        } else if (!person.getPassword().equals(person.getMatchingPassword())) {
            return new ResponseEntity<>("Hasła nie są zgodne", HttpStatus.BAD_REQUEST);
        } else {
            this.personService.saveCoach(person);
            return new ResponseEntity<>("Trenera dodano do bazy danych", HttpStatus.CREATED);
        }
    }

    @Override
    public ResponseEntity<String> athleteMessageToCoach(Person person) {
        if (person.getFirstName() == null || person.getFirstName().isEmpty()) {
            return new ResponseEntity<>("Uzupełnij imię", HttpStatus.BAD_REQUEST);
        } else if (person.getLastName() == null || person.getLastName().isEmpty()) {
            return new ResponseEntity<>("Uzupełnij nazwisko", HttpStatus.BAD_REQUEST);
        } else if (person.getEmail() == null || person.getEmail().isEmpty()) {
            return new ResponseEntity<>("Uzupełnij email", HttpStatus.BAD_REQUEST);
        } else if (!person.getEmail().matches(EMAIL_PATTERN)) {
            return new ResponseEntity<>("Wpisz prawidłowy email", HttpStatus.BAD_REQUEST);
        } else if (this.personService.existsPersonByEmail(person.getEmail())) {
            return new ResponseEntity<>("Email jest już w bazie danych", HttpStatus.BAD_REQUEST);
        } else {
            this.personService.saveAthlete(person);
            return new ResponseEntity<>("Zawodnika dodano do bazy danych", HttpStatus.CREATED);
        }
    }

    @Override
    public ResponseEntity<String> athleteMessage(Person person) {
        if (!this.personService.existsPersonByEmail(person.getEmail())) {
            return new ResponseEntity<>("Takiego maila nie ma w bazie danych", HttpStatus.BAD_REQUEST);
        } else if (this.personService.existsPersonByLogin(person.getLogin())) {
            return new ResponseEntity<>("Taki login jest już w zajęty", HttpStatus.BAD_REQUEST);
        } else if (person.getPassword() == null || person.getPassword().isEmpty()) {
            return new ResponseEntity<>("Uzupełnij hasło", HttpStatus.BAD_REQUEST);
        } else if (person.getMatchingPassword() == null || person.getMatchingPassword().isEmpty()) {
            return new ResponseEntity<>("Powtórz hasło", HttpStatus.BAD_REQUEST);
        } else if (!person.getPassword().equals(person.getMatchingPassword())) {
            return new ResponseEntity<>("Hasła nie są zgodne", HttpStatus.BAD_REQUEST);
        } else {
            this.personService.update(person);
            return new ResponseEntity<>("Zaktualizowano dane zawodnika", HttpStatus.CREATED);
        }
    }
}
