package com.kvarek.model;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.List;


@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class Person implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
    @Enumerated
    PersonRole role;
    @NotNull
    @NotEmpty
    String firstName;
    @NotNull
    @NotEmpty
    String lastName;
    @NotNull
    @NotEmpty
    String email;
    String login;
    @NotNull
    @NotEmpty
    String password;
    @Transient
    String matchingPassword;
    @OneToMany
    List<WODExecution> wodExecutions;

    public Person(PersonRole role, String firstName, String lastName, String email, String login, String password) {
        this.role=role;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.login = login;
        this.password = password;
    }
}
