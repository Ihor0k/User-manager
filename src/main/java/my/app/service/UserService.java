package my.app.service;

import my.app.model.User;

import javax.persistence.criteria.CriteriaBuilder;
import java.util.List;
import java.util.Optional;

public interface UserService  {
    List<User> getAllUsers(User template);
    List<User> getUsersPage(int page, User template);
    List<User> getUsersPage(int page, int size, User template);
    Optional<User> getUserById(long id);
    User addUser(User user);
    void updateUser(User user);
    void deleteUser(User user);
    long count();
}
