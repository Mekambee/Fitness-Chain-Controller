package com.fitnesschain.backend.repositories;

import com.fitnesschain.backend.models.Employee;
import com.fitnesschain.backend.models.Gym;
import com.fitnesschain.backend.models.enums.EmployeeType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Long> {
    List<Employee> findByGym(Gym gym);
    List<Employee> findByGymAndRole(Gym gym, EmployeeType role);
}
