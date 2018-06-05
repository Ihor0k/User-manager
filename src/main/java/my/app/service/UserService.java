package my.app.service;

import my.app.model.User;

import java.util.List;
import java.util.Optional;

public interface UserService  {
    List<User> getAllUsers();
    List<User> getUsersPage(int page);
    Optional<User> getUserById(long id);
    User addUser(User user);
    void updateUser(User user);
    void deleteUser(User user);
}
