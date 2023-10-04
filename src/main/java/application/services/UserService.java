package application.services;

import application.entities.User;

import java.util.List;

public interface UserService {
    User getUserByID(Long id);
    Long saveUser(User user);

    List<User> getUsers();
}
