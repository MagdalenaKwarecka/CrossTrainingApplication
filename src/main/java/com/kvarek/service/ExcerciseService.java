package com.kvarek.service;

import com.kvarek.model.Exercise;
import com.kvarek.repository.ExerciseRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ExcerciseService {

    ExerciseRepository excerciseRepository;

    public ExcerciseService(ExerciseRepository excerciseRepository){
        this.excerciseRepository=excerciseRepository;
    }

    public void delete(Exercise excercise){
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

    public List<Exercise> findAllSortedByName(){
        return this.excerciseRepository.findAllSortedByName();
    }

    public List<Exercise> findAllByNameContaining(String name) throws IllegalArgumentException{
        Optional <List<Exercise>> excercises = this.excerciseRepository.findAllByNameContaining(name);
        return excercises.orElseThrow(IllegalArgumentException::new);
    }

    public Exercise findByName(String name) throws IllegalArgumentException{
        Optional <Exercise> excercise=this.excerciseRepository.findByName(name);
        return excercise.orElseThrow(IllegalArgumentException::new);
    }

    public Boolean existsExcerciseByName(String name){
        return this.excerciseRepository.existsExcerciseByName(name);
    }

}
