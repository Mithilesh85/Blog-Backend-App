package com.mithilesh.blog.service.impl;

import com.mithilesh.blog.config.AppConstants;
import com.mithilesh.blog.entity.Role;
import com.mithilesh.blog.entity.User;
import com.mithilesh.blog.exception.ResourceNotFoundException;
import com.mithilesh.blog.payload.UserDto;
import com.mithilesh.blog.repository.RoleRepository;
import com.mithilesh.blog.repository.UserRepository;
import com.mithilesh.blog.service.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private RoleRepository roleRepository;

    @Override
    public UserDto createUser(UserDto userDto) {
       User user=this.dtoToUser(userDto);
        User savedUser = this.userRepository.save(user);
        return this.userToDto(savedUser);
    }

    @Override
    public UserDto updateUser(UserDto userDto, int userId) {
        User user=this.userRepository.findById(userId).orElseThrow(()->new ResourceNotFoundException("User","Id",userId));

        user.setName(userDto.getName());
        user.setEmail(userDto.getEmail());
        user.setUserName(userDto.getUserName());
        user.setPassword(userDto.getPassword());
        user.setAbout(userDto.getAbout());
        User savedUser = this.userRepository.save(user);
        return this.userToDto(savedUser);
    }

    @Override
    public List<UserDto> getAllUsers() {
        List<User> users = this.userRepository.findAll();
        return users.stream().map((user) -> this.userToDto(user)).collect(Collectors.toList());

    }

    @Override
    public UserDto getUserById(int userId) {
        User user=this.userRepository.findById(userId).orElseThrow(()->new ResourceNotFoundException("User","Id",userId));
        return this.userToDto(user);
    }

    @Override
    public void deleteUser(int userId) {
        User user=this.userRepository.findById(userId).orElseThrow(()->new ResourceNotFoundException("User","Id",userId));
        this.userRepository.delete(user);
    }

    private User dtoToUser(UserDto userDto){
        User user=this.modelMapper.map(userDto,User.class);  // this is implementation of model mapper

        //this is manual transformation of one object to another
//        user.setUserId(userDto.getUserId());
//        user.setName(userDto.getName());
//        user.setEmail(userDto.getEmail());
//        user.setUserName(userDto.getUserName());
//        user.setPassword(userDto.getPassword());
//        user.setConfirmPassword(userDto.getConfirmPassword());
//        user.setAbout(userDto.getAbout());

        return user;
    }
    private UserDto userToDto(User user){
        UserDto userDto=this.modelMapper.map(user,UserDto.class);  // this is implementation of model mapper

        //this is manual transformation of one object to another
//        userDto.setUserId(user.getUserId());
//        userDto.setName(user.getName());
//        userDto.setEmail(user.getEmail());
//        userDto.setUserName(user.getUserName());
//        userDto.setPassword(user.getPassword());
//        userDto.setConfirmPassword(user.getConfirmPassword());
//        userDto.setAbout(user.getAbout());
        return userDto;
    }

    @Override
    public UserDto registerNewUser(UserDto userDto) {

        User user = this.modelMapper.map(userDto, User.class);

        // encoded the password
        user.setPassword(this.passwordEncoder.encode(user.getPassword()));

        // roles
        Role role = this.roleRepository.findById(AppConstants.NORMAL_USER).get();

        user.getRoles().add(role);

        User newUser = this.userRepository.save(user);

        return this.modelMapper.map(newUser, UserDto.class);
    }


}
