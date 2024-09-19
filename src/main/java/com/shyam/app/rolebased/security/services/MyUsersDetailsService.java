package com.shyam.app.rolebased.security.services;

import com.shyam.app.rolebased.security.models.Users;
import com.shyam.app.rolebased.security.models.UsersPrinciple;
import com.shyam.app.rolebased.security.repository.UsersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class MyUsersDetailsService implements UserDetailsService {

    @Autowired
    private UsersRepository usersRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<Users> users = this.usersRepository.findByUsername(username);
        return users.map(UsersPrinciple::new).orElseThrow(()->new UsernameNotFoundException("User not found "+username));
    }
}
