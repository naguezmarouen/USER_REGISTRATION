package application.services.impl;

import application.entities.Country;
import application.entities.User;
import application.repositories.CountryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import application.repositories.UserRepository;
import application.services.UserService;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final CountryRepository countryRepository;


    public UserServiceImpl(UserRepository userRepository, CountryRepository countryRepository) {
        this.userRepository = userRepository;
        this.countryRepository = countryRepository;
    }
    @Override
    public User getUserByID(Long id) {
        return userRepository.findById(id).orElse(null);
    }

    @Override
    public Long saveUser(User user) {
        Country country = countryRepository.findById(user.getCountry().getId()).orElse(null);
        user.setCountry(country);
        return userRepository.save(user).getUserId();
    }

    @Override
    public List<User> getUsers() {
        return userRepository.findAll();
    }
}
