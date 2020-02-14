package com.revature.movie.services;

import com.revature.movie.exceptions.AuthenticationException;
import com.revature.movie.exceptions.BadRequestException;
import com.revature.movie.model.User;
import com.revature.movie.model.UserRole;
import com.revature.movie.repos.UserRepos;

import com.revature.movie.web.dtos.Credentials;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class UserService {

    private UserRepos userRepo;

    @Autowired
    public UserService(UserRepos repo) {
        super();
        this.userRepo = repo;
    }

    @Transactional(readOnly=true)
    public List<User> getAllUsers() {
        return userRepo.findAll();
    }

    @Transactional(readOnly=true)
    public User authenticate(Credentials creds) {

        if (creds == null || creds.getUsername() == null || creds.getPassword() == null
            || creds.getUsername().equals("") || creds.getPassword().equals(""))
        {
            throw new BadRequestException("Invalid credentials object provided!");
        }

        User retrievedUser = userRepo.findUserByCredentials(creds);

        if (retrievedUser == null) {
            throw new AuthenticationException();
        }

        return retrievedUser;

    }

    @Transactional
    public void register(User newUser) {

        // validation would go here...

        newUser.setRole(UserRole.BASIC_USER);
        userRepo.register(newUser);

    }
}
