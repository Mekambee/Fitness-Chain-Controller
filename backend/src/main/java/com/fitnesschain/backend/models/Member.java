package com.fitnesschain.backend.models;


import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "members")
public class Member {
    @Id
    @Column(name = "id", updatable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "first_name", nullable = false)
    private String firstName;

    @Column(name = "last_name", nullable = false)
    private String lastName;

    @Column(name="email")
    private String email;

    @Column(name = "phone")
    private String phone;

    @Column(name = "date_of_birth", nullable = false)
    private LocalDateTime dateOfBirth;

    @Column(name = "registration_date", nullable = false)
    private LocalDate registrationDate;

    @OneToMany(
            mappedBy = "member",
            cascade = CascadeType.ALL,
            orphanRemoval = true,
            fetch = FetchType.LAZY
    )
    @JsonManagedReference
    private List<Membership> memberships = new ArrayList<>();

    @OneToMany(
            mappedBy = "member",
            cascade = CascadeType.ALL,
            orphanRemoval = true,
            fetch = FetchType.LAZY
    )
    @JsonManagedReference
    private List<ClassEnrollment> classEnrollments = new ArrayList<>();

    @PrePersist
    private void prePersist() {
        if (this.registrationDate == null) {
            this.registrationDate = LocalDate.now();
        }
    }
    public void addMembership(Membership m){
        this.memberships.add(m);
        m.setMember(this);
    }

    public void removeMembership(Membership m){
        this.memberships.remove(m);
        m.setMember(null);
    }

    public void addClassEnrollment(ClassEnrollment ce){
        this.classEnrollments.add(ce);
        ce.setMember(this);
    }

    public void removeClassEnrollment(ClassEnrollment ce){
        this.classEnrollments.remove(ce);
        ce.setMember(null);
    }
}
