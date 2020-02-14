package com.revature.movie.web.controllers;


import com.revature.movie.model.User;
import com.revature.movie.services.UserService;
import com.revature.movie.web.dtos.Credentials;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/test")
public class TestController {

    private UserService userService;

    @Autowired
    public TestController(UserService service) {
        super();
        this.userService = service;
    }

    @PostMapping(produces=MediaType.APPLICATION_JSON_VALUE, consumes=MediaType.APPLICATION_JSON_VALUE)
    public User authenticate(@RequestBody Credentials creds) {


        return userService.auth(creds);
    }
}
