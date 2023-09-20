package com.example.miniproject.controller;

import com.example.miniproject.model.User;
import com.example.miniproject.model.request.LoginRequest;
import com.example.miniproject.model.response.LoginResponse;
import com.example.miniproject.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.Optional;

@RestController
@RequestMapping(path = "/auth/users/")
public class UserController {

    private UserService userService;


    @Autowired
    public void setUserService(UserService userService) {
        this.userService = userService;
    }


    @PostMapping(path = "/register/") // http://localhost:9092/auth/users/register/
    public User createUser(@RequestBody User userObject) {
        return userService.createUser(userObject);
    }


    @PostMapping(path = "/login/")
    public ResponseEntity<LoginResponse> loginUser(@RequestBody LoginRequest loginRequest) {

        Optional<String> jwtToken = userService.loginUser(loginRequest);

        if (jwtToken.isPresent()) {
            return ResponseEntity.ok(new LoginResponse(jwtToken.get()));
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(new LoginResponse("Authentication failed"));
        }
    }


}
