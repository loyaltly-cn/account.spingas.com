package com.spingas.account.springboot.controller;

import com.spingas.account.springboot.entity.User;
import com.spingas.account.springboot.services.UserServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class UserController {

    @Autowired
    UserServices services;

    @GetMapping(value = "/users")
    public List<User> findAll(){
        return services.findAll();
    }

    @PostMapping(value = "/autoUser")
    public List<User> auto(User user){
        return services.auto(user);
    }

    @PostMapping(value = "/users")
    public User add(User user){
        return services.add(user);
    }

    @PutMapping(value = "/users")
    public boolean update(User user){
        return services.update(user);
    }

    @DeleteMapping(value = "/users")
    public boolean del(User user){
        return services.del(user);
    }
}
