package com.fitnesschain.backend.services;

import com.fitnesschain.backend.exceptions.InvalidTimeRangeException;
import com.fitnesschain.backend.exceptions.ResourceNotFoundException;
import com.fitnesschain.backend.models.GroupClass;
import com.fitnesschain.backend.models.Gym;
import com.fitnesschain.backend.repositories.GroupClassRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.Duration;
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

    public List<GroupClass> retrieveAllGroupClasses(){
        return groupClassRepository.findAll();
    }

    public GroupClass retrieveGroupClassById(Long id){
        return groupClassRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("There is no group class with such ID"));
    }
    public void deleteGroupClassById(Long id){
        if(!groupClassRepository.existsById(id)){
            throw new ResourceNotFoundException("There is no group class with such ID");
        }
        groupClassRepository.deleteById(id);
    }
    public GroupClass updateGroupClass(GroupClass fromRequest){
        validateStartingAndEndingTime(fromRequest);
        GroupClass fromDB = retrieveGroupClassById(fromRequest.getId());
        fromDB.setClassesName(fromRequest.getClassesName());
        fromDB.setGym(fromRequest.getGym());
        fromDB.setDescription(fromRequest.getDescription());
        fromDB.setTrainer(fromRequest.getTrainer());
        fromDB.setStartTime(fromRequest.getStartTime());
        fromDB.setEndTime(fromRequest.getEndTime());
        fromDB.setCapacity(fromRequest.getCapacity());
        return groupClassRepository.save(fromDB);
    }
    public GroupClass createClass(GroupClass newGroupClass){
        validateStartingAndEndingTime(newGroupClass);
        return groupClassRepository.save(newGroupClass);
    }
    private void validateStartingAndEndingTime(GroupClass groupClass){
        if (groupClass.getStartTime().isAfter(groupClass.getEndTime())) {
            throw new InvalidTimeRangeException("Starting time must be earlier than ending time.");
        }
        Duration duration = Duration.between(groupClass.getStartTime(), groupClass.getEndTime());
        long minutes = duration.toMinutes();
        if(minutes < 30 || minutes > 300){
            throw new InvalidTimeRangeException("Classes time must be at least 30 minutes up to 5 hours");
        }
    }
}
