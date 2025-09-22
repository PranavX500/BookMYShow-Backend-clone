package com.example.BookMy_SHow.Service;

import com.example.BookMy_SHow.Dto.UserDto;
import com.example.BookMy_SHow.Model.User;
import com.example.BookMy_SHow.Repositery.UserRepositery;
import com.example.BookMy_SHow.exception.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UserService {
    @Autowired
    private UserRepositery userRepositery;

    public UserDto createUser(UserDto userDto){
        User user=maptoEnity(userDto);
        User saveduser=userRepositery.save(user);
        return mapToDto(saveduser);

    }
    private User maptoEnity(UserDto userDto){
        User user=new User();
        user.setId(userDto.getId());
        user.setEmail(userDto.getEmail());
        user.setName(userDto.getName());
        user.setPhoneNumber(userDto.getPhoneNumber());

        return  user;
    }
    private UserDto mapToDto(User user){
        UserDto userDto = new UserDto();
        userDto.setId(user.getId());
        userDto.setEmail(user.getEmail());
        userDto.setName(user.getName());
        userDto.setPhoneNumber(user.getPhoneNumber());

        return userDto;
    }

    public UserDto updateuser(Long id,UserDto userDto){
        User user=userRepositery.findById(id)
                .orElseThrow(()->new ResourceNotFoundException("Sorry user not found by "+id));
        User user1=maptoUpdate(user,userDto);
        user1=userRepositery.save(user1);
        return mapToDto(user1);

    }
    private User maptoUpdate(User user,UserDto userDto){
        user.setName(userDto.getName());
        user.setPhoneNumber(userDto.getPhoneNumber());
        user.setEmail(userDto.getEmail());

        return user;
    }
    public UserDto findbyId(Long id){
        User user=userRepositery.findById(id)
                .orElseThrow(()->new ResourceNotFoundException("User not found by "+id));
        return mapToDto(user);

    }
    public void deleteById(Long id) {
        boolean exists = userRepositery.existsById(id);
        if (!exists) {
            throw new ResourceNotFoundException("User not found with id: " + id);
        }
        userRepositery.deleteById(id);
    }
    public List<UserDto> getAllUsers() {
        List<User> users = userRepositery.findAll();
        return users.stream().map(this::mapToDto).collect(Collectors.toList());
    }
    public UserDto findByEmail(String email) {
        User user = userRepositery.findByEmail(email)
                .orElseThrow(() -> new ResourceNotFoundException("User not found with email: " + email));
        return mapToDto(user);
    }





}
