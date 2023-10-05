package application.services.impl;

import application.entities.Country;
import application.entities.User;
import application.exceptions.NotValidDataException;
import application.exceptions.UserNotAuthorizedException;
import application.exceptions.UserNotFoundException;
import application.repositories.CountryRepository;
import application.repositories.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.util.List;
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
    void how_to_get_all_user() throws UserNotFoundException {
        //GIVEN
        User mockUser1 = new User();
        mockUser1.setUserId(1L);
        mockUser1.setUserName("Marwen Naguez");
        User mockUser2 = new User();
        mockUser2.setUserId(1L);
        mockUser2.setUserName("Laurent");
        List<User> users = List.of(mockUser1,mockUser2);

        when(userService.getUsers()).thenReturn(users);

        //WHEN
        List<User> returnedUsers = userService.getUsers();

        //THEN
        assertEquals(users, returnedUsers);
    }


    @Test
    void how_to_save_user() throws UserNotAuthorizedException, NotValidDataException {
        // GIVEN
        User newUser = new User();
        newUser.setUserName("Marwen Naguez");
        Country country = new Country();
        country.setId(1L);
        newUser.setCountry(country);
        newUser.setBirthDateUser(LocalDate.of(1990,11,23));

        when(countryRepository.findById(1L)).thenReturn(Optional.of(country));
        when(userRepository.save(newUser)).thenReturn(newUser);

        // WHEN
        Long returnedUser = userService.saveUser(newUser);

        // THEN
        assertEquals(newUser.getUserId(), returnedUser);

        verify(userRepository, times(1)).save(newUser);
    }

    @Test
    void how_to_save_user_not_authorized_age() {
        //GIVEN
        User newUser = new User();
        newUser.setUserName("Marwen Naguez");
        Country country = new Country();
        country.setId(1L);
        newUser.setCountry(country);
        newUser.setBirthDateUser(LocalDate.of(2080, 1, 1));

        //WHEN
        try {
            userService.saveUser(newUser);
        } catch (UserNotAuthorizedException | NotValidDataException e) {
            //THEN
            assertEquals("Please check the input fields!", e.getMessage());
        }
    }
    @Test
    void how_to_save_user_not_authorized_country() {
        //GIVEN
        User newUser = new User();
        newUser.setUserName("Marwen Naguez");
        Country country = new Country();
        country.setId(2L);
        newUser.setCountry(country);
        newUser.setBirthDateUser(LocalDate.of(1990, 1, 1));
        when(countryRepository.findById(2L)).thenReturn(Optional.of(country));

        //WHEN
        try {
            userService.saveUser(newUser);
        } catch (UserNotAuthorizedException | NotValidDataException e) {
            //THEN
            assertEquals("Only adult French residents are allowed to create an account!", e.getMessage());
        }
    }

    @Test
    void how_to_save_user_with_empty_data() {
        //GIVEN
        User newUser = new User();
        Country country = new Country();
        country.setId(1L);
        newUser.setCountry(country);
        newUser.setBirthDateUser(LocalDate.of(1990, 1, 1));

        //WHEN
        try {
            userService.saveUser(newUser);
        } catch (UserNotAuthorizedException | NotValidDataException e) {
            //THEN
            assertEquals("Please check the input fields!", e.getMessage());
        }
    }
}
