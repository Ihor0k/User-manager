package my.app.controller;

import my.app.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import my.app.service.UserService;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping(path = "api/users")
public class UserController {
    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public ResponseEntity<List<User>> getAllUsers() {
        List<User> users = userService.getAllUsers();
        HttpHeaders headers = makeCountHeader();
        return new ResponseEntity<>(users, headers, HttpStatus.OK);
    }

    @GetMapping(params = "page")
    public ResponseEntity<List<User>> getUsersPage(@RequestParam("page") int page) {
        List<User> users = userService.getUsersPage(page);
        HttpHeaders headers = makeCountHeader();
        return new ResponseEntity<>(users, headers, HttpStatus.OK);
    }

    @GetMapping("{id}")
    public ResponseEntity<User> getUserById(@PathVariable long id) {
        Optional<User> maybeUser = userService.getUserById(id);
        return maybeUser
                .map(user -> new ResponseEntity<>(user, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PostMapping
    public ResponseEntity<User> addUser(@RequestBody User user) {
        User newUser = userService.addUser(user);
        return new ResponseEntity<>(newUser, HttpStatus.CREATED);
    }

    @PutMapping("{id}")
    public ResponseEntity<Void> updateUser(@PathVariable long id, @RequestBody User user) {
        user.setId(id);
        userService.updateUser(user);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable long id) {
        Optional<User> maybeUser = userService.getUserById(id);
        if (maybeUser.isPresent()) {
            userService.deleteUser(maybeUser.get());
            return new ResponseEntity<>(HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    private HttpHeaders makeCountHeader() {
        long count = userService.count();
        HttpHeaders headers = new HttpHeaders();
        headers.add("X-Total-Count", Long.toString(count));
        return headers;
    }
}
