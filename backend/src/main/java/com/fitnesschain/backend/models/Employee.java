package com.fitnesschain.backend.models;

import com.fitnesschain.backend.models.enums.EmployeeType;
import jakarta.persistence.*;
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
    private String firstName;

    @Column(name = "last_name", nullable = false)
    private String lastName;

    @Column(name="email")
    private String email;

    @Column(name = "phone")
    private String phone;

    @Column(name = "role", nullable = false)
    @Enumerated(EnumType.STRING)
    private EmployeeType role;

    @ManyToOne
    @JoinColumn(name = "gym_id")
    private Gym gym;

    @OneToMany(
            mappedBy = "trainer",
            cascade = CascadeType.ALL,
            orphanRemoval = true,
            fetch = FetchType.LAZY
    )
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
