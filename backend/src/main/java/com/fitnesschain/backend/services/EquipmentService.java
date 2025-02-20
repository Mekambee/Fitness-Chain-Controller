package com.fitnesschain.backend.services;

import com.fitnesschain.backend.models.Equipment;
import com.fitnesschain.backend.models.Gym;
import com.fitnesschain.backend.repositories.EquipmentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class EquipmentService {
    private final EquipmentRepository equipmentRepository;
    private final GymService gymService;

    public List<Equipment> getSpecificGymEquipmentById(Long id){
        Gym gym = gymService.retrieveGymById(id);
        return equipmentRepository.findByGym(gym);
    }
}
