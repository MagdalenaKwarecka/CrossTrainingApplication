package com.kvarek.workout.service;

import com.kvarek.workout.model.Exercise;
import com.kvarek.workout.model.ExerciseExecution;
import com.kvarek.workout.repository.ExerciseExecutionRepository;
import com.kvarek.workout.repository.ExerciseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ExerciseExecutionService {

    private final ExerciseExecutionRepository exerciseExecutionRepository;
    private final ExerciseRepository exerciseRepository;

    @Autowired
    public ExerciseExecutionService(ExerciseExecutionRepository exerciseExecutionRepository, ExerciseRepository exerciseRepository) {
        this.exerciseExecutionRepository = exerciseExecutionRepository;
        this.exerciseRepository = exerciseRepository;
    }

    public ExerciseExecution save(ExerciseExecution exerciseExecution, String name) throws IllegalArgumentException {
        Optional<Exercise> exercise = exerciseRepository.findByName(name);
        exerciseExecution.setExercise(exercise.orElseThrow(IllegalArgumentException::new));
        return this.exerciseExecutionRepository.save(exerciseExecution);
    }

    public ExerciseExecution findById(Long id) throws IllegalArgumentException {
        Optional<ExerciseExecution> excerciseExecution = this.exerciseExecutionRepository.findById(id);
        excerciseExecution.orElseThrow(IllegalArgumentException::new);
        return excerciseExecution.get();
    }
}
