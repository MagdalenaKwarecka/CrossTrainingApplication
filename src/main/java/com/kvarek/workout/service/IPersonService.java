package com.kvarek.workout.service;

import com.kvarek.workout.model.Person;

public interface IPersonService {

    void saveCoach(Person person);

    Person findByLogin(String login);
}
