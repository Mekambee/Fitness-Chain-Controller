package com.fitnesschain.backend.models;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

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
    private String name;

    @Column(name= "address", nullable = false)
    private String address;

    @Column(name= "phone")
    private String phone;

    @Column(name="email")
    private String email;

    @Column(name="opening_time", nullable = false)
    private LocalTime openingTime;

    @Column(name="closing_time", nullable = false)
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
