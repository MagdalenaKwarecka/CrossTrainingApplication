package com.kvarek.workout.repository;

import com.kvarek.workout.model.Person;
import com.kvarek.workout.model.PersonRole;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Repository
public interface PersonRepository extends JpaRepository<Person, Long> {

    void delete(Person person);

    @Transactional
    @Modifying
    @Query(value = "UPDATE Person p SET p.password =?2 where p.login=?1")
    void update(@Param("login") String login, @Param("password") String password );

    Person findByLogin(String login);

    void deleteByLogin(String login);

    Optional<Person> findByLoginOrEmail(String login, String email);

    Optional<List<Person>> findAllByLastNameContaining(String lastName);

    Boolean existsPersonByLogin(String login);

    Boolean existsPersonByEmail(String login);

    @Query(value = "SELECT p FROM Person p WHERE p.role=?1 ORDER BY p.lastName")
    List<Person> findAllByRoleOrderByLastName(PersonRole role);
}
