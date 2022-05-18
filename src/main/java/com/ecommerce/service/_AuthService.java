package com.ecommerce.service;

import com.ecommerce.jwt.UserPrincipal;

import com.ecommerce.model.User;
import com.ecommerce.service.impl.UserServiceImpl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
public class _AuthService implements UserDetailsService {
	
    @Autowired
    UserServiceImpl UserServiceImpl;
    
    private static final Logger logger = LoggerFactory.getLogger(_AuthService.class);
    
    @Override
    @Transactional
    public UserDetails loadUserByUsername(String username)
            throws UsernameNotFoundException {
        try {
            User user = UserServiceImpl.findByUsername(username);
            return UserPrincipal.create(user);
        } catch (Exception e) {
            throw new UsernameNotFoundException("User not found with Username : " + username);
        }
    }

    // This method is used by JWTAuthenticationFilter
    @Transactional
    public UserDetails loadUserById(Long id) {
        try {
            User user = UserServiceImpl.findUserById(id);
            return UserPrincipal.create(user);
        } catch (Exception e) {
            throw new UsernameNotFoundException("User not found with id : " + id);
        }
        //return UserPrincipal.create(user);
    }
}
