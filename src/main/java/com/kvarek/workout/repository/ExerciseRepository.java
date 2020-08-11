package com.kvarek.workout.repository;

import com.kvarek.workout.model.Exercise;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ExerciseRepository extends JpaRepository<Exercise, Long> {

    @Query(value = "SELECT ex FROM Exercise ex ORDER BY ex.name" )
    List<Exercise> findAllSortedByName();


    Optional<List<Exercise>> findAllByNameContaining(String name);

    Optional <Exercise> findByName(String name);

    Boolean existsExcerciseByName(String name);
}
