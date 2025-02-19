package com.fitnesschain.backend.controllers;

import com.fitnesschain.backend.models.Gym;
import com.fitnesschain.backend.services.GymService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/app/gyms")
@RequiredArgsConstructor
public class GymController {
    private final GymService gymService;

    @GetMapping
    public List<Gym> getAllGyms(){
        return gymService.retrieveAllGyms();
    }
}
