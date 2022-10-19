package com.mithilesh.blog.security;

import com.mithilesh.blog.entity.User;
import com.mithilesh.blog.exception.ResourceNotFoundException;
import com.mithilesh.blog.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        //load user from database by username
        User user = this.userRepository.findByUserName(username).orElseThrow(() -> new ResourceNotFoundException("User", "User Name: " + username, 0));
        return user;
    }
}
