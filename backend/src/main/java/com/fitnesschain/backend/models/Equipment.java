package com.fitnesschain.backend.models;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fitnesschain.backend.models.enums.EquipmentStatus;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
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
    @NotNull(message = "Equipment name cannot be false")
    @Size(min = 3, max = 100, message = "Equipment name must be between 3 and 100 characters")
    private String name;

    @Column(name = "description")
    @Size(max = 255, message = "Equipment description max characters number is 255")
    private String description;

    @Column(name = "status", nullable = false)
    @NotNull(message = "Equipment status cannot be null")
    @Enumerated(EnumType.STRING)
    private EquipmentStatus status;

    @Column(name = "serial_number", nullable = false)
    @NotNull(message = "Equipment serial number cannot be null")
    @Size(min = 3, max = 15, message = "Equipment serial number must be between 3 and 15 characters")
    private String serialNumber;

    @ManyToOne
    @JoinColumn(name = "gym_id")
    @JsonBackReference("gym-equipment")
    private Gym gym;

}
