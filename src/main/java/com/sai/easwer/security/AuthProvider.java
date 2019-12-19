package com.sai.easwer.security;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationCredentialsNotFoundException;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.AuthenticationServiceException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Component;

import com.sai.easwer.entity.UserDetails;
import com.sai.easwer.repository.UserRepository;

@Component
public class AuthProvider implements AuthenticationProvider
{

    @Autowired
    private UserRepository userRepository;

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException
    {
        String username = authentication.getName();
        String password = authentication.getCredentials().toString();

        if (username == null || username.trim().equals(""))
        {
            throw new AuthenticationCredentialsNotFoundException("Username is empty.");
        }

        if (password == null || password.trim().equals(""))
        {
            throw new AuthenticationCredentialsNotFoundException("Password is empty.");
        }

        Optional<UserDetails> loginUser = userRepository.findByUsername(username);

        if (loginUser.isPresent())
        {
            if (loginUser.get().getPassword().equals(password))
            {
                return new UsernamePasswordAuthenticationToken(username, password);
            }
            else
            {
                throw new AuthenticationServiceException("Authentication failed.");
            }
        }
        else
        {
            throw new AuthenticationServiceException("Invalid username.");
        }
    }

    @Override
    public boolean supports(Class<?> authentication)
    {
        return true;
    }

}