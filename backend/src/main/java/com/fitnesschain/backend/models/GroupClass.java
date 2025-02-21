package com.fitnesschain.backend.models;


import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fitnesschain.backend.models.enums.ClassesType;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;
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
    @NotNull(message = "Classes name cannot be empty")
    private ClassesType classesName;

    @Column(name = "description")
    @Size(max = 255, message = "Group class description max characters number is 255")
    private String description;

    @ManyToOne
    @JoinColumn(name = "trainer_id", nullable = false)
    @NotNull(message = "Group class must be referenced with the trainer")
    @JsonBackReference("trainer-class")
    private Employee trainer;

    @ManyToOne
    @JoinColumn(name = "gym_id")
    @JsonBackReference("gym-classes")
    private Gym gym;

    @Column(name = "start_time", nullable = false)
    @NotNull(message = "Start time cannot be null")
    private LocalTime startTime;

    @Column(name = "end_time", nullable = false)
    @NotNull(message = "End time cannot be null")
    private LocalTime endTime;

    @Column(name = "capacity", nullable = false)
    @NotNull(message = "Capacity cannot be empty")
    @Min(value = 3, message = "The classes should have at least 3 members capacity, with a max of 50")
    @Max(value = 50, message = "The classes should have at least 3 members capacity, with a max of 50")
    private Long capacity;

    @OneToMany(
            mappedBy = "groupClass",
            cascade = CascadeType.ALL,
            orphanRemoval = true,
            fetch = FetchType.LAZY
    )
    @JsonManagedReference("class-enrollment")
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
