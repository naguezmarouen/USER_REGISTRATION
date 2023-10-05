package application.services;

import application.entities.User;
import application.exceptions.NotValidDataException;
import application.exceptions.UserNotAuthorizedException;

import java.util.List;

public interface UserService {
    User getUserByID(Long id);
    Long saveUser(User user) throws UserNotAuthorizedException, NotValidDataException;

    List<User> getUsers();
}
