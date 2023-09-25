package application.controllers;

import application.entities.Country;
import application.entities.User;
import application.exceptions.UserNotAuthorizedException;
import application.exceptions.UserNotFoundException;
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
    @GetMapping("/{id}")
    public User getUser(@PathVariable("id") Long id) throws UserNotFoundException {

        User user = userService.getUserByID(id);
        if (user == null){
            throw new UserNotFoundException("User not found");
        }
        return user;
    }


    @PostMapping("/create")
    public User saveUser(@RequestBody User user) throws UserNotAuthorizedException {

        if (user.getCountry().getId() == 1 && (calculAge(user.getBirthDateUser()))>= 18){
         return userService.saveUser(user);
        }else {
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
