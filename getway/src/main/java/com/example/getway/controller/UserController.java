package com.example.getway.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;

/**
 * 通过token获取用户信息
 */
@RestController
public class UserController {



    @GetMapping("/user")
    public Principal user(Principal user){
        return user;
    }

}
