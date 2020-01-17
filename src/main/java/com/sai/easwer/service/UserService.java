package com.sai.easwer.service;

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
        if (userId == null)
        {
            createDefaultUser();
            List<UserDetails> users = userRepository.findAll();

            if (users.isEmpty())
            {
                return createResponse("No users found.", ResponseStatus.SUCCESS, null, HttpStatus.NO_CONTENT);
            }

            return createResponse("Users found successfully.", ResponseStatus.SUCCESS, users, HttpStatus.OK);
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
                return createResponse("User found successfully.", ResponseStatus.SUCCESS, user, HttpStatus.OK);
            }
        }
    }

    public void createDefaultUser()
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

    @Override
    public ResponseEntity<Response> createUser(UserDetails user)
    {
        try
        {
            validateInput(user);

            user.setId(UUID.randomUUID());

            userRepository.save(user);
        }
        catch (IllegalArgumentException e)
        {
            return createResponse(e.getMessage(), ResponseStatus.FAILURE, null, HttpStatus.BAD_REQUEST);
        }
        catch (Exception e)
        {
            return createResponse("Invalid Input.", ResponseStatus.FAILURE, null, HttpStatus.BAD_REQUEST);
        }
        return createResponse("Users created successfully.", ResponseStatus.SUCCESS, user, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<Response> updateUser(UserDetails user)
    {
        try
        {
            validateInput(user);
            if (user.getId() != null)
            {
                Optional<UserDetails> userDetails = userRepository.findById(user.getId());
                if (userDetails.isPresent())
                {
                    userRepository.save(user);
                }
                else
                {
                    throw new IllegalArgumentException("User not found.");
                }
            }
            else
            {
                throw new IllegalArgumentException("User not found.");
            }
        }
        catch (IllegalArgumentException e)
        {
            return createResponse(e.getMessage(), ResponseStatus.FAILURE, null, HttpStatus.BAD_REQUEST);
        }
        catch (Exception e)
        {
            return createResponse("Invalid Input.", ResponseStatus.FAILURE, null, HttpStatus.BAD_REQUEST);
        }
        return createResponse("Users created successfully.", ResponseStatus.SUCCESS, user, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<Response> deleteUser(UUID userId)
    {
        Optional<UserDetails> userDetails = userRepository.findById(userId);
        if (userDetails.isPresent())
        {
            userRepository.delete(userDetails.get());
        }
        else
        {
            return createResponse("User not found.", ResponseStatus.FAILURE, null, HttpStatus.BAD_REQUEST);
        }
        return createResponse("Users deleted successfully.", ResponseStatus.SUCCESS, null, HttpStatus.OK);
    }

    private void validateInput(UserDetails user) throws Exception
    {
        if (user == null)
        {
            throw new IllegalArgumentException("Invalid Input.");
        }

        if (user.getUsername() == null || user.getUsername().trim().equals(""))
        {
            throw new IllegalArgumentException("Username cannot be empty");
        }

        if (user.getFirstName() == null || user.getFirstName().trim().equals(""))
        {
            throw new IllegalArgumentException("First name cannot be empty");
        }

        if (user.getLastName() == null || user.getLastName().trim().equals(""))
        {
            throw new IllegalArgumentException("Last Name cannot be empty");
        }

        if (user.getPassword() == null || user.getPassword().trim().equals(""))
        {
            throw new IllegalArgumentException("Password cannot be empty");
        }
    }

    @Override
    public ResponseEntity<Response> login(String username, String password) {
        if( null == username) {
            return createResponse("Username cannot be empty.", ResponseStatus.FAILURE, null, HttpStatus.BAD_REQUEST);
        }

        if (null == password) {
            return createResponse("Password cannot be empty.", ResponseStatus.FAILURE, null, HttpStatus.BAD_REQUEST);
        }

        createDefaultUser();
        Optional<UserDetails> user = userRepository.findByUsername(username);
        if(user.isPresent()) {
            if(!user.get().getPassword().equals(password)) {
                return createResponse("Authentication Error.", ResponseStatus.FAILURE, null, HttpStatus.FORBIDDEN);
            }
        } else {
            return createResponse("Invalid User details.", ResponseStatus.FAILURE, null, HttpStatus.BAD_REQUEST);
        }
        return createResponse("Login successfull.", ResponseStatus.SUCCESS, user.get(), HttpStatus.OK);
    }

}