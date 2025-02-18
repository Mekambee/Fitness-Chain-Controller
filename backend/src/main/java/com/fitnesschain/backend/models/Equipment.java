package com.fitnesschain.backend.models;

import com.fitnesschain.backend.models.enums.EquipmentStatus;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "equipment")
public class Equipment {
    @Id
    @Column(name = "id", updatable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "description")
    private String description;

    @Column(name = "status", nullable = false)
    @Enumerated(EnumType.STRING)
    private EquipmentStatus status;

    @Column(name = "serial_number", nullable = false)
    private String serialNumber;

    @ManyToOne
    @JoinColumn(name = "gym_id")
    private Gym gym;

}
