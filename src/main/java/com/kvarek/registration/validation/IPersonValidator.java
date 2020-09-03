package com.kvarek.registration.validation;

import com.kvarek.workout.model.Person;
import org.springframework.http.ResponseEntity;

public interface IPersonValidator {

    ResponseEntity<String> coachMessage(Person person);

    ResponseEntity<String> athleteMessageToCoach(Person person);

    ResponseEntity<String> athleteMessage(Person person);

}
