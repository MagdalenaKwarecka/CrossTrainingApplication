package com.kvarek.registration.validation;

import com.kvarek.workout.model.Person;
import org.springframework.http.ResponseEntity;

public interface IPersonValidator {

    ResponseEntity<String> message (Person person);



}
