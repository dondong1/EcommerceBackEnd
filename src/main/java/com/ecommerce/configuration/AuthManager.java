package com.ecommerce.configuration;

import com.ecommerce.common.RequestPojo.LoginRequest;
import com.ecommerce.jwt.UserPrincipal;
import com.ecommerce.model.User;
import com.ecommerce.service.impl.UserServiceImpl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
@Configuration
public class AuthManager {
    @Autowired
    UserServiceImpl userServiceImpl;
    private static final Logger logger = LoggerFactory.getLogger(AuthManager.class);
    
    public Authentication authenticate(UsernamePasswordAuthenticationToken authentication) throws AuthenticationException {
        String username = authentication.getPrincipal() + "";
        String password = authentication.getCredentials() + "";
        User user;
        try {
            logger.info("user is going to validate(AuthManager) "+username);
            if (userServiceImpl == null) {
                logger.info("user found the error");
                throw new BadCredentialsException("1001");
            }
            user = userServiceImpl.findByUsername(username);
            if (user == null) {
                throw new BadCredentialsException("User Not found!!");
            }

            logger.info("from authentication "+password+" from db "+user.getPassword());
            if (!this.passwordMatch(password, user.getPassword())) {
                logger.info("Password is wrong for user .. "+user.getUsername());
                throw new BadCredentialsException("Username or password is wrong.");
            }

            return new UsernamePasswordAuthenticationToken(new UserPrincipal(user.getId(), username, password), password);
        } catch (Exception e) {
            logger.info("Error",e);
            throw new BadCredentialsException(e.getMessage());
        }

    }
    private Boolean passwordMatch(String rawPassword,String from_db_encoded) {
        return rawPassword.equals(from_db_encoded);
        // return BCrypt.checkpw(rawPassword.toString(),from_db_encoded);
    }
}
