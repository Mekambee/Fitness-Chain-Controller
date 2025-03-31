package com.fitnesschain.backend.services;


import com.fitnesschain.backend.exceptions.InvalidTimeRangeException;
import com.fitnesschain.backend.exceptions.ResourceNotFoundException;
import com.fitnesschain.backend.models.Gym;
import com.fitnesschain.backend.repositories.GymRepository;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class GymServiceTest {
    @Mock
    private GymRepository gymRepository;

    @InjectMocks
    private GymService gymService;

    @Test
    void retrieveAllGymsShouldReturnProperList() {
        Gym gym1 = new Gym();
        Gym gym2 = new Gym();

        List<Gym> gyms = List.of(gym1, gym2);

        when(gymRepository.findAll()).thenReturn(gyms);

        List<Gym> result = gymService.retrieveAllGyms();

        assertThat(result)
                .isNotNull()
                .isNotEmpty()
                .isEqualTo(gyms);
    }

    @Test
    void retrieveAllGymsShouldReturnEmptyList() {
        when(gymRepository.findAll()).thenReturn(List.of());

        List<Gym> result = gymService.retrieveAllGyms();

        assertThat(result)
                .isNotNull()
                .isEmpty();

    }

    @Test
    void shouldReturnGymWhenIdExists() {
        Gym gym = new Gym(
                15L,
                "Gym Max",
                "Main Street 123",
                "123456789",
                "info@gymmax.com",
                LocalTime.MIDNIGHT,
                LocalTime.NOON,
                new ArrayList<>(),
                new ArrayList<>(),
                new ArrayList<>()
        );

        when(gymRepository.findById(gym.getId())).thenReturn(Optional.of(gym));

        Gym result = gymService.retrieveGymById(15L);

        verify(gymRepository, times(1)).findById(gym.getId());

        assertThat(result)
                .isNotNull()
                .isEqualTo(gym);
    }

    @Test
    void retrieveGymByIdShouldThrowAnExceptionWhenNotGymWithThatIdFound(){
        when(gymRepository.findById(1L)).thenReturn(Optional.empty());

        assertThatThrownBy(()-> gymService.retrieveGymById(1L))
                .isInstanceOf(ResourceNotFoundException.class)
                .hasMessage("There is no gym with such ID");

        verify(gymRepository, times(1)).findById(1L);
    }

    @Test
    void createGymShouldSaveObjectAndReturnTheSame(){
        Gym gym = new Gym(
                null,
                "Gym Max",
                "Main Street 123",
                "123456789",
                "info@gymmax.com",
                LocalTime.MIDNIGHT,
                LocalTime.NOON,
                new ArrayList<>(),
                new ArrayList<>(),
                new ArrayList<>()
        );

        when(gymRepository.save(gym)).thenReturn(gym);

        Gym result = gymService.createGym(gym);

        verify(gymRepository, times(1)).save(gym);

        assertThat(result)
                .isNotNull()
                .isEqualTo(gym);
    }

    @Test
    void notEnoughTimeGapBeetweenClosingAndOpeningShouldThrowAnException(){
        Gym gym = new Gym(
                15L,
                "Gym Max",
                "Main Street 123",
                "123456789",
                "info@gymmax.com",
                LocalTime.of(12,30,0),
                LocalTime.of(13,30,0),
                new ArrayList<>(),
                new ArrayList<>(),
                new ArrayList<>()
        );

        assertThatThrownBy(()->gymService.createGym(gym))
                .isInstanceOf(InvalidTimeRangeException.class)
                .hasMessage("Gym must be opened at least 6 hours, up to 24");
    }

    @Test
    void earlierClosingTimeThanOpeningShouldThrowAnException(){
        Gym gym = new Gym(
                15L,
                "Gym Max",
                "Main Street 123",
                "123456789",
                "info@gymmax.com",
                LocalTime.of(13,30,0),
                LocalTime.of(12,30,0),
                new ArrayList<>(),
                new ArrayList<>(),
                new ArrayList<>()
        );

        assertThatThrownBy(()->gymService.createGym(gym))
                .isInstanceOf(InvalidTimeRangeException.class)
                .hasMessage("Opening time must be earlier than closing time.");
    }

    @Test
    void existingGymShouldBeDeleted(){

        when(gymRepository.existsById(1L)).thenReturn(true);

        gymService.deleteGym(1L);

        verify(gymRepository, times(1)).existsById(1L);
        verify(gymRepository, times(1)).deleteById(1L);
    }

    @Test
    void tryingToDeleteNonExistingGymShouldThrowAnException(){
        when(gymRepository.existsById(15L)).thenReturn(false);

        assertThatThrownBy(()->gymService.deleteGym(15L))
                .isInstanceOf(ResourceNotFoundException.class)
                .hasMessage("There is no gym with such ID");
    }

}
