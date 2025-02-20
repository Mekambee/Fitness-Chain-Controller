package com.fitnesschain.backend.models;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fitnesschain.backend.models.enums.MembershipType;
import jakarta.persistence.*;
import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "memberships")
public class Membership {
    @Id
    @Column(name = "id", updatable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "type", nullable = false)
    @Enumerated(EnumType.STRING)
    @NotNull(message = "You have to specify type of membership")
    private MembershipType type;

    @Column(name = "start_date", nullable = false)
    @FutureOrPresent(message = "Membership start date must be future or present")
    private LocalDate startDate;

    @Column(name = "end_date", nullable = false)
    @Future(message = "Membership end date must be in the future")
    private LocalDate endDate;

    @ManyToOne
    @JoinColumn(name = "member_id", nullable = false)
    @NotNull(message = "Membership must have member referenced")
    @JsonBackReference
    private Member member;

}
