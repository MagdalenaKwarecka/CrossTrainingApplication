package com.kvarek.workout.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

@Service
public class SecurityServiceImpl implements SecurityService {

    private AuthenticationManager authenticationManager;

    @Qualifier("personService")
    @Autowired
    private UserDetailsService personDetailsService;


    private static final Logger logger = LoggerFactory.getLogger(SecurityServiceImpl.class);

    @Override
    public String findLoggedInLogin(){
        Object personDetails = SecurityContextHolder.getContext().getAuthentication().getDetails();
        if (personDetails instanceof UserDetails) {
            return ((UserDetails)personDetails).getUsername();
        }
        return null;
    }

    @Override
    public void autoLogin(String login, String password){
        UserDetails personDetails = personDetailsService.loadUserByUsername(login);
        UsernamePasswordAuthenticationToken loginPasswordAuthenticationToken
                = new UsernamePasswordAuthenticationToken(personDetails, password,personDetails.getAuthorities());

        authenticationManager.authenticate(loginPasswordAuthenticationToken);

        if (loginPasswordAuthenticationToken.isAuthenticated()){
            SecurityContextHolder.getContext().setAuthentication(loginPasswordAuthenticationToken);
            logger.debug(String.format("Auto login %s successfully!", login));
        }
    }

}
