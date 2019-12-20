package com.sai.easwer.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import com.sai.easwer.constants.ResponseStatus;
import com.sai.easwer.controller.UserContoller;
import com.sai.easwer.entity.UserDetails;
import com.sai.easwer.model.Response;
import com.sai.easwer.repository.UserRepository;

@RestController
public class UserService extends BaseService implements UserContoller
{

    @Autowired
    private UserRepository userRepository;

    @Override
    public ResponseEntity<Response> getUser(UUID userId)
    {
        List<UserDetails> users = new ArrayList<UserDetails>();
        if (userId == null)
        {
            createUser();
            users = userRepository.findAll();
        }
        else
        {
            Optional<UserDetails> user = userRepository.findById(userId);
            if (user == null)
            {
                return createResponse("Invalid user id.", ResponseStatus.FAILURE, null, HttpStatus.BAD_REQUEST);
            }
            else
            {
                users.add(user.get());
            }
        }
        return createResponse("Users found successfully.", ResponseStatus.SUCCESS, users, HttpStatus.OK);
    }

    public void createUser()
    {
        List<UserDetails> users = userRepository.findAll();
        if (users == null || users.isEmpty())
        {
            UserDetails user = new UserDetails();
            user.setId(UUID.randomUUID());
            user.setUsername("admin");
            user.setFirstName("ATT");
            user.setLastName("USA");
            user.setPassword("admin");
            userRepository.save(user);
        }
    }

}