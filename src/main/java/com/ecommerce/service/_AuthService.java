package com.ecommerce.service;

import com.ecommerce.common.RequestPojo.ApiResponse;
import com.ecommerce.configuration.AuthManager;
import com.ecommerce.jwt.JwtTokenProvider;
import com.ecommerce.jwt.UserPrincipal;

import com.ecommerce.model.User;
import com.ecommerce.service.impl.UserServiceImpl;

import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.HashMap;

import javax.transaction.Transactional;

@Service
public class _AuthService implements UserDetailsService {

	@Autowired
	UserService userService;

	@Autowired
	AuthManager aMan;
	  
    @Autowired
    JwtTokenProvider tokenProvider;

	private static final Logger logger = LoggerFactory.getLogger(_AuthService.class);

	public String login(String username, String password) {
		try {
			Authentication authentication =  aMan.authenticate(new UsernamePasswordAuthenticationToken(username, password));
			SecurityContextHolder.getContext().setAuthentication(authentication);
			String token = tokenProvider.generateToken(authentication);
			JSONObject objToken =  this.getUserResponse(token);
			if (objToken == null) {
				throw new Exception("Error while generating Response");
			}
			return objToken.toString();
		} catch (Exception e) {
			logger.info("Error in authenticateUser ", e);
			return null;
		}
	}

	@Override
	@Transactional
	public UserDetails loadUserByUsername(String username)
			throws UsernameNotFoundException {
		try {
			User user = userService.findByUsername(username);
			return UserPrincipal.create(user);
		} catch (Exception e) {
			throw new UsernameNotFoundException("User not found with Username : " + username);
		}
	}

	// This method is used by JWTAuthenticationFilter
	@Transactional
	public UserDetails loadUserById(Long id) {
		try {
			User user = userService.findUserById(id);
			return UserPrincipal.create(user);
		} catch (Exception e) {
			throw new UsernameNotFoundException("User not found with id : " + id);
		}
		//return UserPrincipal.create(user);
	}

	private JSONObject getUserResponse(String token) {
		try {
			User user = userService.findUserById(_getUserId());
			HashMap<String,String> response = new HashMap<String,String>();
			response.put("user_id", ""+_getUserId());
			response.put("email", user.getEmail());
			response.put("name", user.getUsername());

			JSONObject obj = new JSONObject();

			obj.put("user_profile_details",response);
			obj.put("token", token);
			return obj;
		} catch (Exception e) {
			logger.info("Error in getUserResponse ",e);
		}
		return null;
	}

	private long _getUserId() {
		logger.info("user id validating. "+ SecurityContextHolder.getContext().getAuthentication());
		UserPrincipal userPrincipal = (UserPrincipal) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		logger.info("(LoginController)user id is "+userPrincipal.getId());
		return userPrincipal.getId();
	}
}
