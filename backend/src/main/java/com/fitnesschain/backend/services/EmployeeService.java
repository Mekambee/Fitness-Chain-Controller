package com.fitnesschain.backend.services;

import com.fitnesschain.backend.models.Employee;
import com.fitnesschain.backend.models.Gym;
import com.fitnesschain.backend.models.enums.EmployeeType;
import com.fitnesschain.backend.repositories.EmployeeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class EmployeeService {
    private final EmployeeRepository employeeRepository;
    private final GymService gymService;
    public List<Employee> getEmployeesByGymId(Long id){
        Gym gym = gymService.retrieveGymById(id);
        return employeeRepository.findByGym(gym);
    }
    public List<Employee> getTrainersByGymId(Long id){
        Gym gym = gymService.retrieveGymById(id);
        return employeeRepository.findByGymAndRole(gym, EmployeeType.TRAINER);
    }
}
