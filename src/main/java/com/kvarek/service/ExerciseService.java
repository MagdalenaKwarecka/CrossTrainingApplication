package com.kvarek.service;

import com.kvarek.model.Exercise;
import com.kvarek.repository.ExerciseRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ExerciseService {

    ExerciseRepository exerciseRepository;

    public ExerciseService(ExerciseRepository exerciseRepository){
        this.exerciseRepository=exerciseRepository;
    }

    public void delete(Exercise exercise){
        this.exerciseRepository.delete(exercise);
    }

    public Exercise save(Exercise exercise) {
        return this.exerciseRepository.save(exercise);
    }

    public Exercise findById(Long id) throws IllegalArgumentException {
        Optional<Exercise> exercise = this.exerciseRepository.findById(id);
        exercise.orElseThrow(IllegalArgumentException::new);
        return exercise.get();
    }

    public List<String> findAllSortedByName(){
        return this.exerciseRepository.findAllSortedByName();
    }

    public List<Exercise> findAllByNameContaining(String name) throws IllegalArgumentException{
        Optional <List<Exercise>> exercises = this.exerciseRepository.findAllByNameContaining(name);
        return exercises.orElseThrow(IllegalArgumentException::new);
    }

    public Exercise findByName(String name) throws IllegalArgumentException{
        Optional <Exercise> exercise=this.exerciseRepository.findByName(name);
        return exercise.orElseThrow(IllegalArgumentException::new);
    }

    public Boolean existsExerciseByName(String name){
        return this.exerciseRepository.existsExerciseByName(name);
    }

}

