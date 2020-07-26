package com.kvarek.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class Person implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
    @Enumerated(EnumType.STRING)
    PersonRole role;
    String firstName;
    String lastName;
    String email;
    String login;
    String password;
    @OneToMany
    List<WodExecution> wodExecution;

    public Person(PersonRole role, String firstName, String lastName,String email,String login,String password){
        this.role=role;
        this.firstName=firstName;
        this.lastName=lastName;
        this.email=email;
        this.password=password;
    }
}
