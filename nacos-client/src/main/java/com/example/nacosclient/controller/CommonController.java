package com.example.nacosclient.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author zhaozg
 * @version 1.0
 * @date 2019/6/28
 */
@RestController
@RequestMapping("")
public class CommonController {

    @GetMapping("client")
    public String getAccount() {

        return "client";
    }
}
