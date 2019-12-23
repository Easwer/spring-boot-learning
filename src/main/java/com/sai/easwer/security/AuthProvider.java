package com.sai.easwer.security;

import java.util.Collection;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationCredentialsNotFoundException;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.AuthenticationServiceException;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.stereotype.Component;

import com.sai.easwer.entity.UserDetails;
import com.sai.easwer.repository.UserRepository;
import com.sai.easwer.service.UserService;

@Component
public class AuthProvider implements AuthenticationProvider
{

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserService userService;

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException
    {

        userService.createDefaultUser();

        String username = authentication.getName();

        if (username == null || username.trim().equals(""))
        {
            throw new AuthenticationCredentialsNotFoundException("Username is empty.");
        }

        if (authentication.getCredentials() == null || authentication.getCredentials().toString().trim().equals(""))
        {
            throw new AuthenticationCredentialsNotFoundException("Password is empty.");
        }
        String password = authentication.getCredentials().toString();

        Optional<UserDetails> loginUser = userRepository.findByUsername(username);

        if (loginUser.isPresent())
        {
            if (loginUser.get().getPassword().equals(password))
            {
                Collection<? extends GrantedAuthority> authorities = AuthorityUtils.createAuthorityList("ROLE_USER");
                return new UsernamePasswordAuthenticationToken(username, password, authorities);
            }
            else
            {
                throw new BadCredentialsException("Authentication failed.");
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
        return authentication.equals(UsernamePasswordAuthenticationToken.class);
    }

}