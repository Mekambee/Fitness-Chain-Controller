package com.fitnesschain.backend.repositories;

import com.fitnesschain.backend.models.Equipment;
import com.fitnesschain.backend.models.Gym;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface EquipmentRepository extends JpaRepository<Equipment, Long> {

    List<Equipment> findByGym(Gym gym);
}
