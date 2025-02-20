package com.fitnesschain.backend.models;


import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;
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
    @NotBlank(message = "Member name cannot be empty")
    @Size(min = 2, max = 50, message = "Member first name must be between 2 and 50 characters")
    private String firstName;

    @Column(name = "last_name", nullable = false)
    @NotBlank(message = "Member last name cannot be empty")
    @Size(min = 2, max = 50, message = "Member last name must be between 2 and 50 characters")
    private String lastName;

    @Column(name="email", nullable = false)
    @Email(message = "Email format must be proper: person@example.com")
    @Size(min = 5, max = 45, message = "Email must be beetwen 5 and 45 characters")
    private String email;

    @Column(name= "phone", nullable = false)
    @NotBlank(message = "Phone number cannot be null")
    @Pattern(regexp = "^[0-9]*$", message = "Phone number must consist of digits only")
    @Size(min = 7, max = 15, message = "Phone number must be between 7 and 15 characters")
    private String phone;

    @Column(name = "date_of_birth", nullable = false)
    @NotNull(message = "Birth date cannot be null")
    @Past(message = "Birth date must be in past")
    private LocalDateTime dateOfBirth;

    @Column(name = "registration_date", nullable = false)
    @PastOrPresent(message = "Registration date must be past or present")
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
