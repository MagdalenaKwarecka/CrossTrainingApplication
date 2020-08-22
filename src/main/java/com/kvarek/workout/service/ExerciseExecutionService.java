package com.kvarek.workout.service;

import com.kvarek.workout.model.Exercise;
import com.kvarek.workout.repository.ExerciseExecutionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ExerciseExecutionService {

    @Autowired
    ExerciseExecutionRepository exerciseExecutionRepository;
    Exercise exercise;

    public ExerciseExecutionService(ExerciseExecutionRepository exerciseExecutionRepository) {
        this.exerciseExecutionRepository=exerciseExecutionRepository;
    }


}
