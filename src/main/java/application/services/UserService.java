package application.services;

import application.entities.User;

public interface UserService {
    User getUserByID(Long id);
    User saveUser(User user);
}
