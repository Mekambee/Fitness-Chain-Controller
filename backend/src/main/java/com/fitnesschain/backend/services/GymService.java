package com.fitnesschain.backend.services;

import com.fitnesschain.backend.models.Gym;
import com.fitnesschain.backend.repositories.GymRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class GymService {
    private final GymRepository gymRepository;
    public List<Gym> retrieveAllGyms(){
        return gymRepository.findAll();
    }
    public Gym retrieveGymById(Long id){
        return gymRepository.findById(id).
                orElseThrow(() -> new RuntimeException("There is no gym with such ID: "));
    }
    public Gym createGym(Gym gym){
        return gymRepository.save(gym);
    }
}
