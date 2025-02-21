package com.fitnesschain.backend.services;

import com.fitnesschain.backend.models.ClassEnrollment;
import com.fitnesschain.backend.models.GroupClass;
import com.fitnesschain.backend.repositories.ClassEnrollmentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ClassEnrollmentService {
    private final ClassEnrollmentRepository classEnrollmentRepository;
    private final GroupClassService groupClassService;

    public List<ClassEnrollment> getEnrollmentsByClassesId(Long id){
        GroupClass groupClass = groupClassService.retrieveGroupClassById(id);
        return classEnrollmentRepository.findByGroupClass(groupClass);
    }
}
