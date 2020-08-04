package com.kvarek.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
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
    @OneToMany
    List<ExerciseExecution> excerciseExecutionList;

}
