package com.kvarek.workout.controller;

import com.kvarek.workout.model.ExerciseExecution;
import com.kvarek.workout.model.Round;
import com.kvarek.workout.service.RoundService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/round")
public class RoundController {

    private RoundService roundService;

    public RoundController(RoundService roundService) {
        this.roundService = roundService;
    }

    @PostMapping("/save")
    public ResponseEntity<String> save(@RequestBody Round round) {
        this.roundService.save(round);
        return new ResponseEntity<>("rundę dodano do listy", HttpStatus.CREATED);
    }

    @GetMapping("/findById")
    public ResponseEntity<Round> findById(Long id) {
        try {
            return ResponseEntity.ok(this.roundService.findById(id));
        } catch (IllegalArgumentException e) {
            Round notFound = new Round();
            notFound.setComment(String.format("rundy o id %d nie ma w bazie danych", id));
            return new ResponseEntity<>(notFound, HttpStatus.BAD_REQUEST);
        }
    }
}

