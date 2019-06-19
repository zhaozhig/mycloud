package com.example.auth.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author zhaozg
 * @version 1.0
 * @date 2019/6/19
 */
@RestController
@RequestMapping("")
public class LogController {


    @GetMapping("getInfo")
    public String getAccount(String info) {

        return "yes";
    }
}
