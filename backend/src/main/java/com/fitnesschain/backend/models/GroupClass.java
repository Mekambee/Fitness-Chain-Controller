package com.fitnesschain.backend.models;


import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fitnesschain.backend.models.enums.ClassesType;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "group_classes")
public class GroupClass {
    @Id
    @Column(name = "id", updatable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "classes_name", nullable = false)
    @Enumerated(EnumType.STRING)
    private ClassesType classesName;

    @Column(name = "description")
    private String description;

    @ManyToOne
    @JoinColumn(name = "trainer_id")
    @JsonBackReference
    private Employee trainer;

    @ManyToOne
    @JoinColumn(name = "gym_id")
    @JsonBackReference
    private Gym gym;

    @Column(name = "start_time", nullable = false)
    private LocalTime startTime;

    @Column(name = "end_time", nullable = false)
    private LocalTime endTime;

    @Column(name = "capacity", nullable = false)
    private Long capacity;

    @OneToMany(
            mappedBy = "groupClass",
            cascade = CascadeType.ALL,
            orphanRemoval = true,
            fetch = FetchType.LAZY
    )
    @JsonManagedReference
    private List<ClassEnrollment> enrollments = new ArrayList<>();

    public void addEnrollment(ClassEnrollment ce){
        this.enrollments.add(ce);
        ce.setGroupClass(this);
    }

    public void removeEnrollment(ClassEnrollment ce){
        this.enrollments.remove(ce);
        ce.setGroupClass(null);
    }

}
