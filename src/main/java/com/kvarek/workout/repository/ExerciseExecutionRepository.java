package com.kvarek.workout.repository;

import com.kvarek.workout.model.ExerciseExecution;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ExerciseExecutionRepository extends JpaRepository<ExerciseExecution, Long> {



    ExerciseExecution save (ExerciseExecution exerciseExecution);


}
