package com.kvarek.workout.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import java.io.Serializable;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

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
    String firstName;
    String lastName;
    String email;
    String login;
    String password;
    @Transient
    String matchingPassword;


    @OneToMany(mappedBy = "person")
   // @Fetch(value = FetchMode.SELECT)
    @JsonIgnore
    Set<WODExecution> wodExecutions = new HashSet<>();


    public Person(@NotEmpty PersonRole role, @NotEmpty String firstName,
                  @NotEmpty String lastName, @NotEmpty String email, @NotEmpty String login, @NotEmpty String password) {
        this.role = role;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.login = login;
        this.password = password;
    }

    public void setRole(PersonRole role) {
        this.role = role;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setMatchingPassword(String matchingPassword) {
        this.matchingPassword = matchingPassword;
    }

    public Set<WODExecution> getWodExecutions() {
        return wodExecutions;
    }

    public void setWodExecutions(Set<WODExecution> wodExecutions) {
        this.wodExecutions = wodExecutions;
    }
}
