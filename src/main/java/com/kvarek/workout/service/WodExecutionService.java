package com.kvarek.workout.service;

import com.kvarek.workout.model.ExerciseExecution;
import com.kvarek.workout.model.Person;
import com.kvarek.workout.model.WOD;
import com.kvarek.workout.model.WODExecution;
import com.kvarek.workout.repository.WodExecutionRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Optional;

@Service
public class WodExecutionService {

    private WodExecutionRepository wodExecutionRepository;

    public WodExecutionService(WodExecutionRepository wodExecutionRepository) {
        this.wodExecutionRepository = wodExecutionRepository;
    }

    public WODExecution save(WODExecution wodExecution) {
        return this.wodExecutionRepository.save(wodExecution);
    }

    public WODExecution findById(Long id) throws IllegalArgumentException {
        Optional<WODExecution> wodExecution = this.wodExecutionRepository.findById(id);
        wodExecution.orElseThrow(IllegalArgumentException::new);
        return wodExecution.get();
    }

   public void update(long id, Double wodResult, String userComment){
        this.wodExecutionRepository.update(id, wodResult, userComment);
    }
}
