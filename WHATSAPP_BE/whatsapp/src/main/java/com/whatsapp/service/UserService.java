package com.whatsapp.service;

import com.whatsapp.entity.User;
import com.whatsapp.exception.UserException;
import com.whatsapp.request.UpdateUserRequest;
import jdk.jshell.spi.ExecutionControl;
import org.springframework.stereotype.Service;

import java.util.List;


public interface UserService {

    public User findUserById(Integer id) throws UserException;

    public User findUserProfile(String jwt) throws UserException;

    public User updateUser(Integer id, UpdateUserRequest req) throws UserException;

   // public User findUserById(Integer id);

    public List<User> searchUser(String query);
}
