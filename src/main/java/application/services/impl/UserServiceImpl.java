package application.services.impl;

import application.controllers.UserController;
import application.entities.Country;
import application.entities.User;
import application.exceptions.NotValidDataException;
import application.exceptions.UserNotAuthorizedException;
import application.repositories.CountryRepository;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Service;
import application.repositories.UserRepository;
import application.services.UserService;

import java.time.LocalDate;
import java.time.Period;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final CountryRepository countryRepository;

    private static final Logger logger = LogManager.getLogger(UserController.class);

    public UserServiceImpl(UserRepository userRepository, CountryRepository countryRepository) {
        this.userRepository = userRepository;
        this.countryRepository = countryRepository;
    }
    @Override
    public User getUserByID(Long id) {
        return userRepository.findById(id).orElse(null);
    }

    @Override
    public Long saveUser(User user) throws UserNotAuthorizedException, NotValidDataException {
        Country country = countryRepository.findById(user.getCountry().getId()).orElse(null);
        user.setCountry(country);
        checkUser(user);
        return userRepository.save(user).getUserId();
    }

    @Override
    public List<User> getUsers() {
        return userRepository.findAll();
    }

    private void checkUser(User user)  throws UserNotAuthorizedException, NotValidDataException {
        if (user.getCountry() == null || user.getUserName() == null || user.getBirthDateUser() == null){
            logger.error("Please fill in all required fields!");
            throw new NotValidDataException("Please check the input fields!");
        }
        if (user.getCountry().getId() == 1 && (calculAge(user.getBirthDateUser()))>= 18){
            logger.info("User "+user.getUserName()+" successfully created");
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
