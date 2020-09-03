package com.kvarek.workout.model;

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

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getWorkDurationInSeconds() {
        return workDurationInSeconds;
    }

    public void setWorkDurationInSeconds(Long workDurationInSeconds) {
        this.workDurationInSeconds = workDurationInSeconds;
    }

    public Integer getNumberOfRepetition() {
        return numberOfRepetition;
    }

    public void setNumberOfRepetition(Integer numberOfRepetition) {
        this.numberOfRepetition = numberOfRepetition;
    }

    public Exercise getExercise() {
        return exercise;
    }

    public void setExercise(Exercise exercise) {
        this.exercise = exercise;
    }

    public Double getExerciseWeight() {
        return exerciseWeight;
    }

    public void setExerciseWeight(Double exerciseWeight) {
        this.exerciseWeight = exerciseWeight;
    }

    public Integer getExerciseCalories() {
        return exerciseCalories;
    }

    public void setExerciseCalories(Integer exerciseCalories) {
        this.exerciseCalories = exerciseCalories;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }
}

