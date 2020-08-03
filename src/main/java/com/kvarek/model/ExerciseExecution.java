package com.kvarek.model;

import com.sun.istack.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class ExerciseExecution implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
    Long workDurationInSeconds;
    Integer numberOfRepetition;
    @NotNull
    @ManyToOne
    Exercise exercise;
    Double exerciseWeight;
    Integer exerciseCalories;
    String comment;

}

