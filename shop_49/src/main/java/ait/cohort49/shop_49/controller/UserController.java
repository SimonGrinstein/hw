package ait.cohort49.shop_49.controller;


import ait.cohort49.shop_49.model.entity.User;
import ait.cohort49.shop_49.service.interfaces.UserService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user")
@Tag(name = "User controller", description = "Controller for User")
public class UserController {

    private final UserService userService;
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping
    public User register(@RequestBody User user) {
        System.out.println("register---------------------- " + user.getUsername());
        System.out.println("register---------------------- " + user.getPassword());
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        encoder.encode(user.getPassword());
        userService.register(user);
        return user;
    }

    @GetMapping
    public List<User> getUsers() {
        System.out.println("------------------ getUsers ---------------------- ");
        return userService.findAll();
    }

}
