package my.app.service;

import my.app.model.User;

import javax.persistence.criteria.CriteriaBuilder;
import java.util.List;
import java.util.Optional;

public interface UserService  {
    List<User> getAllUsers();
    List<User> getUsersPage(int page);
    List<User> getUsersPage(int page, int size);
    Optional<User> getUserById(long id);
    User addUser(User user);
    void updateUser(User user);
    void deleteUser(User user);
    long count();
}
