package com.kvarek.workout.service;

import com.kvarek.workout.model.Exercise;
import com.kvarek.workout.repository.ExerciseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ExerciseService {

    private final ExerciseRepository excerciseRepository;

    @Autowired
    public ExerciseService(ExerciseRepository excerciseRepository) {
        this.excerciseRepository = excerciseRepository;
    }

    public void delete(Exercise excercise) {
        this.excerciseRepository.delete(excercise);
    }

    public Exercise save(Exercise excercise) {
        return this.excerciseRepository.save(excercise);
    }

    public Exercise findById(Long id) throws IllegalArgumentException {
        Optional<Exercise> excercise = this.excerciseRepository.findById(id);
        excercise.orElseThrow(IllegalArgumentException::new);
        return excercise.get();
    }

    public List<Exercise> findAllSortedByName() {
        return this.excerciseRepository.findAllSortedByName();
    }

    public List<Exercise> findAllByNameContaining(String name) throws IllegalArgumentException {
        Optional<List<Exercise>> excercises = this.excerciseRepository.findAllByNameContaining(name);
        return excercises.orElseThrow(IllegalArgumentException::new);
    }

    public Exercise findByName(String name) throws IllegalArgumentException {
        Optional<Exercise> excercise = this.excerciseRepository.findByName(name);
        return excercise.orElseThrow(IllegalArgumentException::new);
    }

    public Boolean existsExcerciseByName(String name) {
        return this.excerciseRepository.existsExerciseByName(name);
    }
}
