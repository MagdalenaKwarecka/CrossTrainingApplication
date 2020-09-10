package com.kvarek.workout.controller;

import com.kvarek.workout.model.WOD;
import com.kvarek.workout.model.WodType;
import com.kvarek.workout.service.WodService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/wod")
public class WodController {

    private final WodService wodService;

    public WodController(WodService wodService) {
        this.wodService = wodService;
    }

    @PostMapping("/save")
    public ResponseEntity<WOD> save(@RequestBody WOD wod) {
        this.wodService.save(wod);
        return new ResponseEntity<>(wod, HttpStatus.CREATED);
    }

    @GetMapping("/findById")
    public ResponseEntity<WOD> findById(Long id) {
        try {
            return ResponseEntity.ok(this.wodService.findById(id));
        } catch (IllegalArgumentException e) {
            WOD notFound = new WOD();
            notFound.setWodName("Taki WOD nie istnieje");
            return new ResponseEntity<>(notFound, HttpStatus.BAD_REQUEST);
        }
    }
}
