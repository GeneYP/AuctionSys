package com.gec.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
public class HelloController {
    @GetMapping("/findUsers")
    public List<String> findUsers(){
        List<String> userNames = new ArrayList<>();
        userNames.add("BITCH");
        userNames.add("Fuck");
        userNames.add("slut");
        return userNames;

    }
}
