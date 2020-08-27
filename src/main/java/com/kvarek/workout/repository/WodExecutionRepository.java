package com.kvarek.workout.repository;


import com.kvarek.workout.model.Person;
import com.kvarek.workout.model.WOD;
import com.kvarek.workout.model.WODExecution;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;

public interface WodExecutionRepository extends JpaRepository<WODExecution, Long> {



    WODExecution save (WODExecution wodExecution);

    @Transactional
    @Modifying
    @Query(value = "UPDATE WODExecution w SET w.wodResult =?2, w.userComment =?3 where w.id=?1")
    void update(@Param("id") long id, @Param("wodResult") Double wodResult, @Param("userComment") String userComment );

}

