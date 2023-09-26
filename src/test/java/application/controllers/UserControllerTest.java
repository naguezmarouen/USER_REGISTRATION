package application.controllers;

import application.entities.Country;
import application.entities.User;
import application.exceptions.NotValidDataException;
import application.exceptions.UserNotAuthorizedException;
import application.exceptions.UserNotFoundException;
import application.services.UserService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class UserControllerTest {

    @Mock
    private UserService userService;

    @InjectMocks
    private UserController userController;


    @Test
    void how_to_get_user() throws UserNotFoundException {
        //GIVEN
        User mockUser = new User();
        mockUser.setUserId(1L);
        mockUser.setUserName("Marwen Naguez");
        when(userService.getUserByID(1L)).thenReturn(mockUser);

        //WHEN
        User returnedUser = userController.getUser(1L);

        //THEN
        assertEquals(mockUser, returnedUser);
    }

    @Test
    void how_to_save_user_correctly() throws UserNotAuthorizedException, NotValidDataException {
        //GIVEN
        User newUser = new User();
        newUser.setUserName("Marwen Naguez");
        Country country = new Country();
        country.setId(1L);
        newUser.setCountry(country);
        newUser.setBirthDateUser(LocalDate.of(2000, 1, 1));
        when(userService.saveUser(newUser)).thenReturn(newUser);

        //WHEN
        User returnedUser = userController.saveUser(newUser);

        //THEN
        assertEquals(newUser, returnedUser);

        verify(userService, times(1)).saveUser(newUser);
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
            userController.saveUser(newUser);
        } catch (UserNotAuthorizedException | NotValidDataException e) {
            //THEN
            assertEquals("Only adult French residents are allowed to create an account!", e.getMessage());
        }
    }

    @Test
    void how_to_save_user_not_authorized_country() {
        //GIVEN
        User newUser = new User();
        newUser.setUserName("Marwen Naguez");
        Country country = new Country();
        country.setId(500L);
        newUser.setCountry(country);
        newUser.setBirthDateUser(LocalDate.of(1990, 1, 1));

        //WHEN
        try {
            userController.saveUser(newUser);
        } catch (UserNotAuthorizedException | NotValidDataException e) {
            //THEN
            assertEquals("Only adult French residents are allowed to create an account!", e.getMessage());
        }
    }
    @Test
    void how_to_get_user_not_exist() {
        //GIVEN
        when(userService.getUserByID(1L)).thenReturn(null);

        //WHEN
        try {
            userController.getUser(1L);
        } catch (UserNotFoundException e) {

            //THEN
            assertEquals("User not found", e.getMessage());
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
            userController.saveUser(newUser);
        } catch (UserNotAuthorizedException | NotValidDataException e) {
            //THEN
            assertEquals("Veuillez vérifier les champs de saisi !", e.getMessage());
        }
    }
}
