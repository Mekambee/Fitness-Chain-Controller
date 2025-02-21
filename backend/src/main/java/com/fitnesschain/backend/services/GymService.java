package com.fitnesschain.backend.services;

import com.fitnesschain.backend.exceptions.InvalidTimeRangeException;
import com.fitnesschain.backend.exceptions.ResourceNotFoundException;
import com.fitnesschain.backend.models.Gym;
import com.fitnesschain.backend.repositories.GymRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.util.List;

@Service
@RequiredArgsConstructor
public class GymService {
    private final GymRepository gymRepository;
    public List<Gym> retrieveAllGyms(){
        return gymRepository.findAll();
    }
    public Gym retrieveGymById(Long id){
        return gymRepository.findById(id).
                orElseThrow(() -> new ResourceNotFoundException("There is no gym with such ID"));
    }
    public Gym createGym(Gym gym){
        validateOpeningAndClosingTime(gym);
        return gymRepository.save(gym);
    }

    public void deleteGym(Long id){
        if(!gymRepository.existsById(id)){
            throw new ResourceNotFoundException("There is no gym with such ID");
        }
        gymRepository.deleteById(id);
    }

    public Gym updateGym(Gym updatedGym){
        validateOpeningAndClosingTime(updatedGym);
        Gym gymFromDb = retrieveGymById(updatedGym.getId());
        gymFromDb.setName(updatedGym.getName());
        gymFromDb.setAddress(updatedGym.getAddress());
        gymFromDb.setPhone(updatedGym.getPhone());
        gymFromDb.setEmail(updatedGym.getEmail());
        gymFromDb.setOpeningTime(updatedGym.getOpeningTime());
        gymFromDb.setClosingTime(updatedGym.getClosingTime());

        return gymRepository.save(gymFromDb);
    }

    private void validateOpeningAndClosingTime(Gym gym){
        if (gym.getOpeningTime().isAfter(gym.getClosingTime())) {
            throw new InvalidTimeRangeException("Opening time must be earlier than closing time.");
        }
        Duration duration = Duration.between(gym.getOpeningTime(), gym.getClosingTime());
        long hours = duration.toHours();
        if(hours < 6 || hours > 24){
            throw new InvalidTimeRangeException("Gym must be opened at least 6 hours, up to 24");
        }
    }
}
