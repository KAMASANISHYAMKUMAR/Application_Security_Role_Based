package com.shyam.app.rolebased.security.services.Impl;

import com.shyam.app.rolebased.security.dto.AuthRequest;
import com.shyam.app.rolebased.security.jwt.JwtService;
import com.shyam.app.rolebased.security.models.Users;
import com.shyam.app.rolebased.security.repository.UsersRepository;
import com.shyam.app.rolebased.security.services.MyUsersDetailsService;
import com.shyam.app.rolebased.security.services.UsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UsersServiceImpl implements UsersService {

    @Autowired
    private UsersRepository usersRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private JwtService jwtService;
    @Autowired
    private MyUsersDetailsService myUsersDetailsService;

    boolean flag;
    @Override
    public String addUser(Users users) {
        List<Users> userList = usersRepository.findAll();

        for (Users user : userList) {
            flag = user.getUsername().equals(users.getUsername());
        }

        if(flag) {
            return "User already exists";
        } else {
            users.setPassword(passwordEncoder.encode(users.getPassword()));
            usersRepository.save(users);
            return "User saved";
        }
    }

    @Override
    public String generateToken(AuthRequest authRequest) {

        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(authRequest.getUsername(),authRequest.getPassword()));
        if(authentication.isAuthenticated()) {
            UserDetails userDetails = myUsersDetailsService.loadUserByUsername(authRequest.getUsername());
            return jwtService.generateToken(userDetails);
        }else {
            throw new UsernameNotFoundException("Invalid User");
        }
    }
}
