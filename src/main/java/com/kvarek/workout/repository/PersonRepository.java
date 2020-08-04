package com.kvarek.repository;

import com.kvarek.model.Person;
import com.kvarek.model.PersonRole;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface PersonRepository extends JpaRepository<Person, Long> {

    void delete(Person person);

    Person save(Person person);

    Person findByLogin(String login);

    void deleteByLogin(String login);

    Optional<Person> findByLoginOrEmail(String login, String email);

    Optional<List<Person>> findAllByLastNameContaining(String lastName);

    Boolean existsPersonByLogin(String login);

    Boolean existsPersonByEmail(String login);

    @Query(value = "SELECT p FROM Person p WHERE p.role=?1 ORDER BY p.lastName")
    List<Person> findAllByRoleOrderByLastName(PersonRole role);
}
