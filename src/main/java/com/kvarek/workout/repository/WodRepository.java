package com.kvarek.workout.repository;

import com.kvarek.workout.model.WOD;
import org.springframework.data.jpa.repository.JpaRepository;

public interface WodRepository extends JpaRepository<WOD, Long> {



    WOD save (WOD wod);


}

