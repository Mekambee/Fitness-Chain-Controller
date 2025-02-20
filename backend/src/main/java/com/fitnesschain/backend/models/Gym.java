package com.fitnesschain.backend.models;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.*;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name="gyms")
public class Gym {
    @Id
    @Column(name = "id", updatable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name", nullable = false, unique = true)
    @NotBlank(message = "Gym name cannot be empty")
    @Size(min = 3, max = 30, message = "Gym names length must be between 3-50 characters")
    private String name;

    @Column(name= "address", nullable = false)
    @NotBlank(message = "Gym address cannot be empty")
    @Size(min = 3, max = 30, message = "Gym address length must be between 3-50 characters")
    private String address;

    @Column(name= "phone", nullable = false)
    @NotBlank(message = "Phone number cannot be null")
    @Pattern(regexp = "^[0-9]*$", message = "Phone number must consist of digits only")
    @Size(min = 7, max = 15, message = "Phone number must be between 7 and 15 characters")
    private String phone;

    @Column(name="email", nullable = false)
    @Email(message = "Email format must be proper: person@example.com")
    @Size(min = 5, max = 45, message = "Email must be beetwen 5 and 45 characters")
    private String email;

    @Column(name="opening_time", nullable = false)
    @NotNull(message = "Opening time cannot be null")
    private LocalTime openingTime;

    @Column(name="closing_time", nullable = false)
    @NotNull(message = "Closing time cannot be null")
    private LocalTime closingTime;

    @OneToMany(
            mappedBy = "gym",
            cascade = CascadeType.ALL,
            orphanRemoval = true,
            fetch = FetchType.LAZY
    )
    @JsonManagedReference
    private List<Equipment> equipment = new ArrayList<>();

    @OneToMany(
            mappedBy = "gym",
            cascade = CascadeType.ALL,
            orphanRemoval = true,
            fetch = FetchType.LAZY
    )
    @JsonManagedReference
    private List<Employee> employees = new ArrayList<>();

    @OneToMany(
            mappedBy = "gym",
            cascade = CascadeType.ALL,
            orphanRemoval = true,
            fetch = FetchType.LAZY
    )
    @JsonManagedReference
    private List<GroupClass> groupClasses = new ArrayList<>();

    public void addEquipment(Equipment e){
        equipment.add(e);
        e.setGym(this);
    }

    public void removeEquipment(Equipment e){
        equipment.remove(e);
        e.setGym(null);
    }

    public void addEmployee(Employee e){
        employees.add(e);
        e.setGym(this);
    }

    public void removeEmployee(Employee e){
        employees.remove(e);
        e.setGym(null);
    }
    public void addGroupClass(GroupClass g){
        groupClasses.add(g);
        g.setGym(this);
    }

    public void removeGroupClass(GroupClass g){
        groupClasses.remove(g);
        g.setGym(null);
    }
}
