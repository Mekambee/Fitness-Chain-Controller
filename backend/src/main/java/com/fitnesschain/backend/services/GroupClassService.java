package com.fitnesschain.backend.services;

import com.fitnesschain.backend.models.GroupClass;
import com.fitnesschain.backend.models.Gym;
import com.fitnesschain.backend.repositories.GroupClassRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class GroupClassService {
    private final GroupClassRepository groupClassRepository;
    private final GymService gymService;
    public List<GroupClass> getGymGroupClassesById(Long id){
        Gym gym = gymService.retrieveGymById(id);
        return groupClassRepository.findByGym(gym);
    }
}
