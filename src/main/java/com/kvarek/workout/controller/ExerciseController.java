package com.kvarek.workout.controller;

import com.kvarek.workout.model.Exercise;
import com.kvarek.workout.service.ExerciseService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@RestController
@RequestMapping("/exercise")
public class ExerciseController {

    ExerciseService exerciseService;

    public ExerciseController(ExerciseService exerciseService) {
        this.exerciseService = exerciseService;

    }

    @PostMapping("/initial")
    public String createInitialData() {
        Exercise exercise1 = new Exercise("airbike");
        Exercise exercise2 = new Exercise("burpee");
        Exercise exercise3 = new Exercise("strict pull up");
        Exercise exercise4 = new Exercise("deadlift");
        Exercise exercise5 = new Exercise("double under");
        this.exerciseService.save(exercise1);
        this.exerciseService.save(exercise2);
        this.exerciseService.save(exercise3);
        this.exerciseService.save(exercise4);
        this.exerciseService.save(exercise5);
        return "ćwiczenia dodano do bazy danych";
    }

    @DeleteMapping("/delete")
    public ResponseEntity<String> delete(@RequestBody Exercise exercise) {
        this.exerciseService.delete(exercise);
        return new ResponseEntity<>("ćwiczenie usunięto z listy", HttpStatus.ACCEPTED);
    }

    @PostMapping("/save")
    public ResponseEntity<String> save(@RequestParam String name) {
        if (this.exerciseService.existsExcerciseByName(name)) {
            return new ResponseEntity<>("ćwiczenie już jest na liście", HttpStatus.CONFLICT);
        }
        Exercise exercise = new Exercise(name);
        this.exerciseService.save(exercise);
        return new ResponseEntity<>("ćwiczenie dodano do listy", HttpStatus.CREATED);
    }

    @GetMapping("/findById{id}")
    public ResponseEntity<Exercise> findById(@PathVariable Long id) {
        try {
            return ResponseEntity.ok(this.exerciseService.findById(id));
        } catch (IllegalArgumentException e) {
            Exercise notFound = new Exercise();
            notFound.setName(String.format("ćwiczenia o id %d nie ma na liście", id));
            return new ResponseEntity<>(notFound, HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/findAllSortedByName")
    public ResponseEntity<List<Exercise>> findAllSortedByName() {
        return ResponseEntity.ok(this.exerciseService.findAllSortedByName());
    }

    @GetMapping("/findAllByNameContaining")
    public ResponseEntity<List<Exercise>> findAllByNameContaining(String name) {
        try {
            return ResponseEntity.ok(this.exerciseService.findAllByNameContaining(name));
        } catch (IllegalArgumentException e) {
            Exercise notFound = new Exercise();
            notFound.setName("Takiego ćwiczenia nie ma na liście");
            return new ResponseEntity<>(new ArrayList<>(Collections.singletonList(notFound)), HttpStatus.BAD_REQUEST);
        }
    }

}
