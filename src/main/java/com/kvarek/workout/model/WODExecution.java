package com.kvarek.workout.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;

@Data
@Entity
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
public class WODExecution implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
    @OneToOne
    WOD wod;
    @ManyToOne
    Person athlete;
    LocalDate wodDate;
    String coachComment;
    String userComment;
    Double wodResult;

    public WODExecution (WOD wod, Person athlete, LocalDate wodDate, String coachComment){
        this.wod=wod;
        this.athlete=athlete;
        this.wodDate=wodDate;
        this.coachComment=coachComment;
    }
}
