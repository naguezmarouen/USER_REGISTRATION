package application.controllers;

import application.entities.User;
import application.exceptions.UserNotAuthorizedException;
import application.exceptions.UserNotFoundException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import application.services.UserService;

import java.time.LocalDate;
import java.time.Period;

@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserService userService;
    private static final Logger logger = LogManager.getLogger(UserController.class);
    @GetMapping("/{id}")
    public User getUser(@PathVariable("id") Long id) throws UserNotFoundException {

        User user = userService.getUserByID(id);
        if (user == null){
            throw new UserNotFoundException("User not found");
        }
        logger.info("Récupération de l'utilisateur "+user.getUserName());
        return user;
    }


    @PostMapping("/create")
    public User saveUser(@RequestBody User user) throws UserNotAuthorizedException {

        if (user.getCountry().getId() == 1 && (calculAge(user.getBirthDateUser()))>= 18){
            logger.info("Création de l'utilisateur "+user.getUserName()+" éfféctué avec succés");
         return userService.saveUser(user);
        }else {
            logger.error("La création de l'utilisateur à échoué !");
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
