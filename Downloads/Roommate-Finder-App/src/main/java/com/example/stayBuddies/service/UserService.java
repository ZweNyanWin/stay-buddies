package com.example.stayBuddies.service;

import com.example.stayBuddies.model.AppUser;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;


public interface UserService extends UserDetailsService {
    AppUser registerNewUser(AppUser user);
}