package com.kvarek.workout.controller;

import com.kvarek.workout.model.ExerciseExecution;
import com.kvarek.workout.model.WODExecution;
import com.kvarek.workout.service.WodExecutionService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("wodExecution")
public class WodExecutionController {

    private WodExecutionService wodExecutionService;


    public WodExecutionController(WodExecutionService wodExecutionService) {
        this.wodExecutionService = wodExecutionService;
    }

    @PostMapping("/save")
    public ResponseEntity<WODExecution> save(@RequestBody WODExecution wodExecution) {
        this.wodExecutionService.save(wodExecution);
        return new ResponseEntity<>(wodExecution, HttpStatus.CREATED);
    }

    @PutMapping("/update")
    public ResponseEntity<WODExecution> update(@RequestParam long id, @RequestParam Double wodResult, @RequestParam String userComment){

        this.wodExecutionService.update(id, wodResult, userComment);
        WODExecution newExecution = new WODExecution();
        newExecution.setWodResult(wodResult);
        newExecution.setUserComment(userComment);
        return new ResponseEntity<>(newExecution, HttpStatus.ACCEPTED);
    }

    @GetMapping("/findById")
    public ResponseEntity<WODExecution> findById(Long id) {
        try {
            return ResponseEntity.ok(this.wodExecutionService.findById(id));
        } catch (IllegalArgumentException e) {
            WODExecution notFound = new WODExecution();
            notFound.setCoachComment(String.format("treningu o id %d nie ma w bazie danych", id));
            return new ResponseEntity<>(notFound, HttpStatus.BAD_REQUEST);
        }
    }
}

