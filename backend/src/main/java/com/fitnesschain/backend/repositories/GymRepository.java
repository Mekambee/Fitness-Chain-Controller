package com.fitnesschain.backend.repositories;

import com.fitnesschain.backend.models.Gym;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GymRepository extends JpaRepository<Gym, Long> {
}
