package com.java.ecommerce.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.java.ecommerce.DTO.LoginDto;
import com.java.ecommerce.Model.User;
import com.java.ecommerce.Repository.UserRepo;


import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;


@Service
public class UserService {
    
    @Autowired
    UserRepo userRepo;

    public boolean isuserPresent(String email) {
        if(userRepo.findByEmail(email) != null){
            return true;
        }
        return false;
    }

    public boolean isuserIdPresent(Integer userId){
        return userRepo.findById(userId).isPresent();
    }

    public User getUserById(Integer userId){
        return userRepo.findById(userId).get();
    }
    public void createNewUser(User user) {
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        String hashedPassword = passwordEncoder.encode(user.getPassword());
        user.setPassword(hashedPassword);
        userRepo.save(user);
    }

    public boolean validateCreds(LoginDto loginDto) {
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        User user = userRepo.findByEmail(loginDto.getEmail());
        if (user != null && passwordEncoder.matches(loginDto.getPassword(), user.getPassword())) {
            // Password is correct, proceed with login
            return true;
        } else {
            // Password is incorrect
            return false;
        }
    }

    
}
