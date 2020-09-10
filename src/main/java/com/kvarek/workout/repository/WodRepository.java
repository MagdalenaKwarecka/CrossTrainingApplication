package com.kvarek.workout.repository;

import com.kvarek.workout.model.WOD;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface WodRepository extends JpaRepository<WOD, Long> {
}
