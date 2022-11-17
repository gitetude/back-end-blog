package sn.supdeco.blog.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.AllArgsConstructor;
import sn.supdeco.blog.services.UserService;
import sn.supdeco.blog.services.dtos.RegisterRequest;
import sn.supdeco.blog.services.dtos.UserLoginDto;
import sn.supdeco.blog.services.impl.UserServiceImpl;

@RestController
@RequestMapping("/api/v1/users")
@AllArgsConstructor
public class UserController {
    private final UserService userService;

    @Autowired
    private UserServiceImpl authService;

    @PostMapping("/signup")
    public ResponseEntity signup(@RequestBody RegisterRequest registerRequest) {
        authService.signup(registerRequest);
        return new ResponseEntity(HttpStatus.OK);
    }
    @PostMapping("/login")
    public String login(@RequestBody UserLoginDto loginRequest) {
        return authService.login(loginRequest);
    }
}
