package com.whatsapp.service.impl;

import com.whatsapp.config.TokenProvider;
import com.whatsapp.entity.User;
import com.whatsapp.exception.UserException;
import com.whatsapp.repository.UserRepository;
import com.whatsapp.request.UpdateUserRequest;
import com.whatsapp.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {


    @Autowired
    UserRepository userRepository;

    @Autowired
    TokenProvider tokenProvider;



    @Override
    public User findUserById(Integer id) throws UserException {
        Optional<User> user =  userRepository.findById(id);
       if(user.isPresent()){
           return user.get();
       }else {
           throw new UserException("User not found with id : "+ id);
       }
    }

    @Override
    public User findUserProfile(String jwt) throws UserException {
        String email = tokenProvider.getEmailFromToken(jwt);
        User user;
        if(email ==null){
            throw new BadCredentialsException("Received Invalid Token");

        }
        user = userRepository.findByEmail(email);
        if(user == null){
            throw new UserException("User Not found with email : " + email);
        }
        return user;
    }

    @Override
    public User updateUser(Integer id, UpdateUserRequest req) throws UserException {
        User user = this.findUserById(id);

        if (req.getFull_name() != null) {
            user.setFull_name(req.getFull_name());
        }
        if (req.getProfile_picture() != null) {
            user.setProfile_picture(req.getProfile_picture());
        }
        return this.userRepository.save(user);
    }

    @Override
    public List<User> searchUser(String query) {
        List<User> users = this.userRepository.searchUser(query);
        return users;
    }
}
