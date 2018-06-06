package my.app.service;

import my.app.model.User;
import my.app.model.UserSpecification;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import my.app.repository.UserRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService{
    private final static int DEFAULT_PAGE_SIZE = 5;

    private final UserRepository userRepository;

    @Autowired
    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public List<User> getAllUsers(User template) {
        Specification<User> specification = new UserSpecification(template);
        return userRepository.findAll(specification);
    }

    @Override
    public List<User> getUsersPage(int page, User template) {
        return getUsersPage(page, DEFAULT_PAGE_SIZE, template);
    }

    @Override
    public List<User> getUsersPage(int page, int size, User template) {
        Specification<User> specification = new UserSpecification(template);
        List<User> list = new ArrayList<>();
        userRepository.findAll(specification, PageRequest.of(page, size)).forEach(list::add);
        return list;
    }

    @Override
    public Optional<User> getUserById(long id) {
        return userRepository.findById(id);
    }

    @Override
    public User addUser(User user) {
        return userRepository.save(user);
    }

    @Override
    public void updateUser(User user) {
        userRepository.save(user);
    }

    @Override
    public void deleteUser(User user) {
        userRepository.delete(user);
    }

    @Override
    public long count() {
        return userRepository.count();
    }
}
