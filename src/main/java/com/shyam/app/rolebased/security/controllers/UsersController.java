package com.shyam.app.rolebased.security.controllers;

import com.shyam.app.rolebased.security.dto.AuthRequest;
import com.shyam.app.rolebased.security.models.Users;
import com.shyam.app.rolebased.security.services.UsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/users")
public class UsersController {

    @Autowired
    private UsersService usersService;

    @PostMapping("/add")
    public ResponseEntity<String> addUser(@RequestBody Users users){
        return ResponseEntity.ok(usersService.addUser(users));
    }

    @PostMapping("/authenticate")
    public ResponseEntity<String> generateToken(@RequestBody AuthRequest authRequest){
        return ResponseEntity.ok(usersService.generateToken(authRequest));
    }
}
