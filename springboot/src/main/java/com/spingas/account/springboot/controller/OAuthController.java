package com.spingas.account.springboot.controller;

import com.spingas.account.springboot.entity.User;
import com.spingas.account.springboot.services.OAuthServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class OAuthController {

    @Autowired
    OAuthServices services;

    @PostMapping(value = "/login")
    public Object login(User user){
        return services.login(user);
    }

    @PostMapping(value = "/decrypt")
    public Object decrypt(String cipher){
        return services.decrypt(cipher);
    }

}
