package com.fitnesschain.backend.repositories;

import com.fitnesschain.backend.models.ClassEnrollment;
import com.fitnesschain.backend.models.GroupClass;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ClassEnrollmentRepository extends JpaRepository<ClassEnrollment, Long> {
    List<ClassEnrollment> findByGroupClass(GroupClass groupClass);
}
