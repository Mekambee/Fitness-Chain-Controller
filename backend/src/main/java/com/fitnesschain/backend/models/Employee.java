package com.fitnesschain.backend.models;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fitnesschain.backend.models.enums.EmployeeType;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "employees")
public class Employee {
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

    @Column(name = "role", nullable = false)
    @Enumerated(EnumType.STRING)
    @NotNull(message = "Employee role cannot be null")
    private EmployeeType role;

    @ManyToOne
    @JoinColumn(name = "gym_id", nullable = false)
    @NotNull(message = "Employee has to be referenced to specific gym")
    @JsonBackReference
    private Gym gym;

    @OneToMany(
            mappedBy = "trainer",
            cascade = CascadeType.ALL,
            orphanRemoval = true,
            fetch = FetchType.LAZY
    )
    @JsonManagedReference
    private List<GroupClass> groupClassList = new ArrayList<>();

    public void addGroupClass(GroupClass gc){
        this.groupClassList.add(gc);
        gc.setTrainer(this);
    }

    public void removeGroupClass(GroupClass gc){
        this.groupClassList.remove(gc);
        gc.setTrainer(null);
    }

}
