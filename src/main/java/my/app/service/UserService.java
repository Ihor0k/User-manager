package my.app.service;

import my.app.model.User;

import java.util.List;

public interface UserService  {
    List<User> getAllUsers();
    User getUserById(long id);
    User addUser(User user);
    void updateUser(User user);
    void deleteUser(long id);
}
