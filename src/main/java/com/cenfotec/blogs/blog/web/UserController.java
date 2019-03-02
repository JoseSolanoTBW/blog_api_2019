package com.cenfotec.blogs.blog.web;

import com.cenfotec.blogs.blog.domain.User;
import com.cenfotec.blogs.blog.service.UserService;
import com.cenfotec.blogs.blog.utils.Status;
import javassist.NotFoundException;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/user")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/all")
    public List<User> getAll() {
        return userService.findAll();
    }

    @CrossOrigin
    @PostMapping("/sing-in")
    public User postUser(@RequestBody User user) {
        return userService.save(user);
    }

    @GetMapping("/log-in")
    public User getUser(@RequestParam("nickName") String nickName ) throws NotFoundException {
        return userService.getUser(nickName);
    }



}
