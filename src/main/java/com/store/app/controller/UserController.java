package com.store.app.controller;

import com.store.app.entity.User;
import com.store.app.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
public class UserController {

    private UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @CrossOrigin(origins = "http://localhost:4200")
    @RequestMapping(value = "/", method = RequestMethod.POST)
    public ResponseEntity createUser(@RequestBody User user){
        return userService.createUser(user);
    }

    @CrossOrigin(origins = "http://localhost:4200")
    @RequestMapping(value = "/admin", method = RequestMethod.POST)
    public ResponseEntity createAdminUser(@RequestBody User user){
        return userService.createAdminUser(user);
    }

}
