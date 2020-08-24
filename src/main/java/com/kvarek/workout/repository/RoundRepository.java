package com.kvarek.workout.repository;

import com.kvarek.workout.model.Round;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoundRepository extends JpaRepository<Round, Long> {



    Round save (Round round);


}

