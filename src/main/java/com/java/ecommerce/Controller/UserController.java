package com.java.ecommerce.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.java.ecommerce.Common.APIResponse;
import com.java.ecommerce.DTO.LoginDto;
import com.java.ecommerce.Model.User;
import com.java.ecommerce.Service.UserService;

import jakarta.validation.Valid;

@RestController
public class UserController {
    
    @Autowired
    UserService userService;

    @PostMapping(path = "/users/signup")
    public ResponseEntity<APIResponse> signUp(@Valid @RequestBody User user){
        if(userService.isuserPresent(user.getEmail())){
            return new ResponseEntity<>(new APIResponse(false, "User Email Address already present"),HttpStatus.CONFLICT);
        }
        userService.createNewUser(user);
        return new ResponseEntity<>(new APIResponse(true, "User registration successfull"),HttpStatus.CREATED);
    }

    @PostMapping(path = "/users/login")
    public ResponseEntity<APIResponse> login(@Valid @RequestBody LoginDto loginDto){
        if(!userService.isuserPresent(loginDto.getEmail())){
            return new ResponseEntity<>(new APIResponse(false, "User Email Address not present"),HttpStatus.UNAUTHORIZED);
        }
        if(userService.validateCreds(loginDto)){
            return new ResponseEntity<>(new APIResponse(true, "User Logged is Successfully"),HttpStatus.OK);
        }
        return new ResponseEntity<>(new APIResponse(false, "Invalid Credentials"),HttpStatus.UNAUTHORIZED);
    }
}
