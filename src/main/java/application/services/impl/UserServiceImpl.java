package application.services.impl;

import application.entities.Country;
import application.entities.User;
import application.repositories.CountryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import application.repositories.UserRepository;
import application.services.UserService;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private CountryRepository countryRepository;
    @Override
    public User getUserByID(Long id) {
        return userRepository.findById(id).orElse(null);
    }

    @Override
    public User saveUser(User user) {
        Country country = countryRepository.findById(user.getCountry().getId()).orElse(null);
        user.setCountry(country);
        return userRepository.save(user);
    }
}
