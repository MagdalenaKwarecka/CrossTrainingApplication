package com.kvarek.workout.service;

import com.kvarek.workout.model.Exercise;
import com.kvarek.workout.model.ExerciseExecution;
import com.kvarek.workout.repository.ExerciseExecutionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ExerciseExecutionService {

    @Autowired
    ExerciseExecutionRepository exerciseExecutionRepository;



    public ExerciseExecutionService(ExerciseExecutionRepository exerciseExecutionRepository) {
        this.exerciseExecutionRepository=exerciseExecutionRepository;
    }

     public ExerciseExecution save(ExerciseExecution exerciseExecution) {
        return this.exerciseExecutionRepository.save(exerciseExecution);
    }

    public ExerciseExecution findById(Long id) throws IllegalArgumentException {
        Optional<ExerciseExecution> excerciseExecution = this.exerciseExecutionRepository.findById(id);
        excerciseExecution.orElseThrow(IllegalArgumentException::new);
        return excerciseExecution.get();
    }
}
