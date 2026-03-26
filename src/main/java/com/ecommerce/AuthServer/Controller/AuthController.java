package com.ecommerce.AuthServer.Controller;


import  com.ecommerce.AuthServer.Model.User;
import  com.ecommerce.AuthServer.Service.UserService;
import  com.ecommerce.AuthServer.Service.UserService2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private UserService userService;

    @Autowired
    private UserService2 userService2;

    private BCryptPasswordEncoder encoder = new BCryptPasswordEncoder(12);

    @PostMapping("/register")
    public String AddUser(@RequestBody User user)
    {
        user.setPassword(encoder.encode(user.getPassword()));
        return userService.AddUser(user);
    }

    @PostMapping("/login")
    public String login(@RequestBody User user)
    {
        return userService2.verify(user);
    }


    @GetMapping
    public List<User> GetAllUser()
    {
        return userService.GetAll();
    }

    @GetMapping("/test")
    public String test()
    {
        return "You have hit the auth server";
    }


}
