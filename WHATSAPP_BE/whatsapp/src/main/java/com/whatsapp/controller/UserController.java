package com.whatsapp.controller;


import com.whatsapp.entity.User;
import com.whatsapp.exception.UserException;
import com.whatsapp.request.UpdateUserRequest;
import com.whatsapp.response.ApiResponse;
import com.whatsapp.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/users")
public class UserController {


    @Autowired
    UserService userService;


    @GetMapping("/profile")
    public ResponseEntity<User> getUserProfileHandler(@RequestHeader("Authorization") String token) throws UserException {

        User user = this.userService.findUserProfile(token);
        return new ResponseEntity<User>(user, HttpStatus.OK);
    }

    @GetMapping("/{query}")
    public ResponseEntity<List<User>> searchUserHandler(@PathVariable("query") String query) {

        List<User> users = this.userService.searchUser(query);
        return new ResponseEntity<List<User>>(users, HttpStatus.OK);
    }

    @PutMapping("/update")
    public ResponseEntity<ApiResponse> updateUserHandler(@RequestBody UpdateUserRequest request,
                                                         @RequestHeader("Authorization") String token) throws UserException {

        User user = this.userService.findUserProfile(token);
        this.userService.updateUser(user.getId(), request);

        ApiResponse response = new ApiResponse();
        response.setMessage("User updated Successfully");
        response.setStatus(true);

        return new ResponseEntity<ApiResponse>(response, HttpStatus.ACCEPTED);
    }
}
