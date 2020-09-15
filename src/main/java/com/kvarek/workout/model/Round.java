package com.kvarek.workout.model;

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
public class Round implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
    WodType wodType;
    Long roundDurationInSeconds;
    Integer numberOfRepetition;
    String comment;
    @NotNull
    @ManyToMany
    List<ExerciseExecution> exerciseExecution;

}
