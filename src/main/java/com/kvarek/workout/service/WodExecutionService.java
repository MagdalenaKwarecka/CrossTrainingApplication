package com.kvarek.workout.service;


import com.kvarek.workout.model.Person;
import com.kvarek.workout.model.WODExecution;

import com.kvarek.workout.repository.WodExecutionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.List;
import java.util.Optional;

@Service
public class WodExecutionService {

    private final WodExecutionRepository wodExecutionRepository;
    Person person;

    @Autowired
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

    public List<WODExecution> findAllByAthlete (String lastName, String firstName) throws IllegalArgumentException{
        Optional <List<WODExecution>> executions = this.wodExecutionRepository.findAllByAthlete(lastName, firstName);
        return executions.orElseThrow(IllegalArgumentException::new);
    }

}

