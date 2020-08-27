package com.kvarek.workout.controller;



import com.kvarek.workout.model.ExerciseExecution;
import com.kvarek.workout.service.ExerciseExecutionService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/exerciseExecution")
public class ExcerciseExecutionController {

    private ExerciseExecutionService exerciseExecutionService;


    public ExcerciseExecutionController(ExerciseExecutionService exerciseExecutionService) {
        this.exerciseExecutionService = exerciseExecutionService;

    }

    @PostMapping("/save")
    public ResponseEntity<String> save(@RequestBody ExerciseExecution exerciseExecution, @RequestParam String name) {
        this.exerciseExecutionService.save(exerciseExecution, name);
        return new ResponseEntity<>("ćwiczenie dodano do listy", HttpStatus.CREATED);
    }

    @GetMapping("/findById")
    public ResponseEntity<ExerciseExecution> findById(Long id) {
        try {
            return ResponseEntity.ok(this.exerciseExecutionService.findById(id));
        } catch (IllegalArgumentException e) {
            ExerciseExecution notFound = new ExerciseExecution();
            notFound.setComment(String.format("wykonania o id %d nie ma w bazie danych", id));
            return new ResponseEntity<>(notFound, HttpStatus.BAD_REQUEST);
        }
    }
}
