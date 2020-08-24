package com.kvarek.workout.service;

import com.kvarek.workout.model.ExerciseExecution;
import com.kvarek.workout.model.Round;
import com.kvarek.workout.repository.RoundRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class RoundService {

    @Autowired
    private RoundRepository roundRepository;

    public RoundService(RoundRepository roundRepository) {
        this.roundRepository = roundRepository;
    }

    public Round save(Round round) {
        return this.roundRepository.save(round);
    }

    public Round findById(Long id) throws IllegalArgumentException {
        Optional<Round> round = this.roundRepository.findById(id);
        round.orElseThrow(IllegalArgumentException::new);
        return round.get();
    }
}
