package com.store.app.controller;

import com.store.app.entity.User;
import com.store.app.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/users")
public class UserController {

    private UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @RequestMapping(value = "/", method = RequestMethod.POST)
    public ResponseEntity createUser(@RequestBody User user){
        return userService.createUser(user);
    }

    @RequestMapping(value = "/admin", method = RequestMethod.POST)
    public ResponseEntity createAdminUser(@RequestBody User user){
        return userService.createAdminUser(user);
    }

}
