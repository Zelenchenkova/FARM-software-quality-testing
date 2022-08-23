package com.example.farmingproject.service;

import com.example.farmingproject.repository.UserRepository;
import com.example.farmingproject.security.MyUserDetails;
import com.example.farmingproject.security.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

@Component
public class UserDetailsServiceImpl implements UserDetailsService {
    private UserRepository userRepository;

    @Autowired
    public void setUserRepository(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.getUserByUsername(username);

        if(user==null){
            throw new UsernameNotFoundException("Could not find user!");
        }
        return new MyUserDetails(user);
    }
}

