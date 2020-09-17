package com.kvarek.workout.service;

import com.kvarek.workout.model.Exercise;
import com.kvarek.workout.repository.ExerciseRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ExerciseService {

    ExerciseRepository exerciseRepository;

    public ExerciseService(ExerciseRepository exerciseRepository){
        this.exerciseRepository = exerciseRepository;
    }

    public void delete(Exercise excercise){
        this.exerciseRepository.delete(excercise);
    }

    public Exercise save(Exercise excercise) {
        return this.exerciseRepository.save(excercise);
    }

    public Exercise findById(Long id) throws IllegalArgumentException {
        Optional<Exercise> excercise = this.exerciseRepository.findById(id);
        excercise.orElseThrow(IllegalArgumentException::new);
        return excercise.get();
    }

    public List<Exercise> findAllSortedByName(){
        return this.exerciseRepository.findAllSortedByName();
    }

    public List<Exercise> findAllByNameContaining(String name) throws IllegalArgumentException{
        Optional <List<Exercise>> excercises = this.exerciseRepository.findAllByNameContaining(name);
        return excercises.orElseThrow(IllegalArgumentException::new);
    }

    public Exercise findByName(String name) throws IllegalArgumentException{
        Optional <Exercise> excercise=this.exerciseRepository.findByName(name);
        return excercise.orElseThrow(IllegalArgumentException::new);
    }

    public Boolean existsExcerciseByName(String name){
        return this.exerciseRepository.existsExcerciseByName(name);
    }

}
