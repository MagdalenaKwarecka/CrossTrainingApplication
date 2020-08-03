package com.kvarek.service;

import com.kvarek.model.Person;

public interface IPersonService {

    void saveCoach(Person person);

    Person findByLogin(String login);
}
