package com.fitnesschain.backend.controllers;
import com.fitnesschain.backend.models.ClassEnrollment;
import com.fitnesschain.backend.models.GroupClass;
import com.fitnesschain.backend.services.ClassEnrollmentService;
import com.fitnesschain.backend.services.GroupClassService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("fitnesschain/classes")
@RequiredArgsConstructor
public class GroupClassesController {
    private final GroupClassService groupClassService;
    private final ClassEnrollmentService classEnrollmentService;

    @GetMapping
    public List<GroupClass> getGroupClasses(){
        return groupClassService.retrieveAllGroupClasses();
    }
    @GetMapping("/{id}")
    public GroupClass getGroupClassById(@PathVariable Long id){
        return groupClassService.retrieveGroupClassById(id);
    }
    @GetMapping("/{id}/enrollments")
    public List<ClassEnrollment> getSpecificClassEnrollments(@PathVariable Long id){
        return classEnrollmentService.getEnrollmentsByClassesId(id);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<GroupClass> deleteGroupClassById(@PathVariable Long id){
        groupClassService.deleteGroupClassById(id);
        return ResponseEntity.noContent().build();
    }
    @PutMapping("/{id}")
    public ResponseEntity<GroupClass> updateGroupClass(@PathVariable Long id, @RequestBody @Valid GroupClass newGroupClass){
        newGroupClass.setId(id);
        GroupClass updatedClass = groupClassService.updateGroupClass(newGroupClass);
        return ResponseEntity.ok(updatedClass);
    }
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public GroupClass createGroupClass(@RequestBody @Valid GroupClass newGroupClass){
        return groupClassService.createClass(newGroupClass);
    }
}
