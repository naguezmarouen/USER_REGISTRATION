package application.services.impl;

import application.entities.Country;
import application.entities.User;
import application.repositories.CountryRepository;
import application.repositories.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class UserServiceImplTest {

    @Mock
    private UserRepository userRepository;

    @Mock
    private CountryRepository countryRepository;

    @InjectMocks
    private UserServiceImpl userService;


    @Test
    void how_to_get_user() {
        // GIVEN
        Long userId = 1L;
        User mockUser = new User();
        mockUser.setUserId(userId);
        mockUser.setUserName("Marwen Naguez");
        when(userRepository.findById(userId)).thenReturn(Optional.of(mockUser));

        // WHEN
        User returnedUser = userService.getUserByID(userId);

        // THEN
        assertEquals(mockUser, returnedUser);
    }

    @Test
    void how_to_save_user() {
        // GIVEN
        User newUser = new User();
        newUser.setUserName("Marwen Naguez");
        Country country = new Country();
        country.setId(1L);
        newUser.setCountry(country);

        when(countryRepository.findById(1L)).thenReturn(Optional.of(country));
        when(userRepository.save(newUser)).thenReturn(newUser);

        // WHEN
        Long returnedUser = userService.saveUser(newUser);

        // THEN
        assertEquals(newUser.getUserId(), returnedUser);

        verify(userRepository, times(1)).save(newUser);
    }
}
