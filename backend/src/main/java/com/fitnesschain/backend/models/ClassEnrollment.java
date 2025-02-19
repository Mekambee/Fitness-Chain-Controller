package com.fitnesschain.backend.models;


import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
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

    @Column(name ="enrollment_date", nullable = false)
    private LocalDateTime enrollmentDate;

    @ManyToOne
    @JoinColumn(name = "member_id")
    @JsonBackReference
    private Member member;

    @ManyToOne
    @JoinColumn(name = "class_id")
    @JsonBackReference
    private GroupClass groupClass;

}
