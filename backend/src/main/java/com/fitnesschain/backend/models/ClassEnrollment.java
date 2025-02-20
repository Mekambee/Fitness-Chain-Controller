package com.fitnesschain.backend.models;


import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "class_enrollments")
public class ClassEnrollment {

    @Id
    @Column(name="id", updatable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "enrollment_date", nullable = false)
    @NotNull(message = "Enrollment date cannot be null")
    private LocalDateTime enrollmentDate;

    @ManyToOne
    @JoinColumn(name = "member_id", nullable = false)
    @NotNull(message = "Enrollment has to be referenced with member")
    @JsonBackReference
    private Member member;

    @ManyToOne
    @JoinColumn(name = "class_id", nullable = false)
    @NotNull(message = "Enrollment has to be referenced with class")
    @JsonBackReference
    private GroupClass groupClass;

}
