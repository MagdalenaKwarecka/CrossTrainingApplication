package com.kvarek.model;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;

@Data
@Entity
public class ExerciseExecution implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
    @ManyToOne
    Exercise exercise;
    Integer repetiton;
    Double weight;
    Long workDurationInSeconds;
    Integer calories;
    String comment;
}
