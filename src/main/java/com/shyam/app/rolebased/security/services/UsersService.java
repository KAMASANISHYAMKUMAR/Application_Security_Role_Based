package com.shyam.app.rolebased.security.services;

import com.shyam.app.rolebased.security.dto.AuthRequest;
import com.shyam.app.rolebased.security.models.Users;

public interface UsersService {
    public String addUser(Users users);
    public String generateToken(AuthRequest authRequest);
}
