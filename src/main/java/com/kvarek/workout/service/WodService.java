package com.kvarek.workout.service;

import com.kvarek.workout.model.WOD;
import com.kvarek.workout.repository.WodRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class WodService {

    private final WodRepository wodRepository;

    @Autowired
    public WodService(WodRepository wodRepository) {
        this.wodRepository = wodRepository;
    }

    public WOD save(WOD wod) {
        return this.wodRepository.save(wod);
    }

    public WOD findById(Long id) throws IllegalArgumentException {
        Optional<WOD> wod = this.wodRepository.findById(id);
        wod.orElseThrow(IllegalArgumentException::new);
        return wod.get();
    }
}
