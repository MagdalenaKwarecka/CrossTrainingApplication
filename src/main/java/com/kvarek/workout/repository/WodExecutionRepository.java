package com.kvarek.workout.repository;


import com.kvarek.workout.model.WODExecution;
import org.springframework.data.jpa.repository.JpaRepository;

public interface WodExecutionRepository extends JpaRepository<WODExecution, Long> {



    WODExecution save (WODExecution wodExecution);

}

