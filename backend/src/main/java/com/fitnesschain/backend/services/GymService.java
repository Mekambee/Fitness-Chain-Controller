package com.fitnesschain.backend.services;

import com.fitnesschain.backend.models.Gym;
import com.fitnesschain.backend.repositories.GymRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class GymService {
    private final GymRepository gymRepository;
    public List<Gym> retrieveAllGyms(){
        return gymRepository.findAll();
    }
}
