package com.fitnesschain.backend.repositories;

import com.fitnesschain.backend.models.GroupClass;
import com.fitnesschain.backend.models.Gym;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface GroupClassRepository extends JpaRepository<GroupClass, Long> {
    List<GroupClass> findByGym(Gym gym);
}
