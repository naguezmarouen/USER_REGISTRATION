package application.controllers;

import application.entities.User;
import application.exceptions.NotValidDataException;
import application.exceptions.UserNotAuthorizedException;
import application.exceptions.UserNotFoundException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import application.services.UserService;

import java.time.LocalDate;
import java.time.Period;
import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {

    private final UserService userService;
    private static final Logger logger = LogManager.getLogger(UserController.class);

    public UserController(UserService userService) {
        this.userService = userService;
    }
    @GetMapping("/{id}")
    public User getUser(@PathVariable("id") Long id) throws UserNotFoundException {

        User user = userService.getUserByID(id);
        if (user == null){
            throw new UserNotFoundException("User not found");
        }
        logger.info("Retrieving the user "+user.getUserName());
        return user;
    }

    @GetMapping("/users")
    public List<User> getUsers() {
        logger.info("Retrieving all users ");
        return userService.getUsers();
    }


    @PostMapping("/create")
    public Long saveUser(@RequestBody User user) throws UserNotAuthorizedException, NotValidDataException {

        if (user.getCountry() == null || user.getUserName() == null || user.getBirthDateUser() == null){
            logger.error("Please fill in all required fields!");
            throw new NotValidDataException("Please check the input fields!");
        }
        if (user.getCountry().getId() == 1 && (calculAge(user.getBirthDateUser()))>= 18){
            logger.info("User "+user.getUserName()+" successfully created");
            return userService.saveUser(user);
        }else {
            logger.error("The user creation failed!");
            throw new UserNotAuthorizedException("Only adult French residents are allowed to create an account!");
        }
    }

    private int calculAge(LocalDate birthDate){
        // Date actuelle
        LocalDate dateActuelle = LocalDate.now();

        // Calcul de la période entre la date de naissance et la date actuelle
        Period difference = Period.between(birthDate, dateActuelle);

        // Récupération de l'âge
        return difference.getYears();
    }
}
