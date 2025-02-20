package com.fitnesschain.backend.controllers;

import com.fitnesschain.backend.models.Employee;
import com.fitnesschain.backend.models.Equipment;
import com.fitnesschain.backend.models.GroupClass;
import com.fitnesschain.backend.models.Gym;
import com.fitnesschain.backend.services.EmployeeService;
import com.fitnesschain.backend.services.EquipmentService;
import com.fitnesschain.backend.services.GroupClassService;
import com.fitnesschain.backend.services.GymService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/fitnesschain/gyms")
@RequiredArgsConstructor
public class GymController {
    private final GymService gymService;
    private final EquipmentService equipmentService;
    private final GroupClassService groupClassService;
    private final EmployeeService employeeService;

    @GetMapping
    public List<Gym> getAllGyms(){
        return gymService.retrieveAllGyms();
    }
    @GetMapping("/{id}")
    public Gym getGymById(@PathVariable Long id){
        return gymService.retrieveGymById(id);
    }
    @GetMapping("/{id}/equipment")
    public List<Equipment> getGymEquipmentById(@PathVariable Long id){
        return equipmentService.getSpecificGymEquipmentById(id);
    }
    @GetMapping("/{id}/classes")
    public List<GroupClass> getGymGroupClassesById(@PathVariable Long id){
        return groupClassService.getGymGroupClassesById(id);
    }
    @GetMapping("{id}/trainers")
    public List<Employee> getGymTrainersById(@PathVariable Long id){
        return employeeService.getTrainersByGymId(id);
    }
    @GetMapping("{id}/employees")
    public List<Employee> getGymEmployeesById(@PathVariable Long id){
        return employeeService.getEmployeesByGymId(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Gym createGym(@RequestBody @Valid Gym gym){
        return gymService.createGym(gym);
    }
}
