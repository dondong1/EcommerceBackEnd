package com.ecommerce.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.ecommerce.common.RequestPojo.ApiResponse;
import com.ecommerce.common.RequestPojo.LoginRequest;
import com.ecommerce.model.User;
import com.ecommerce.service.UserService;
import com.ecommerce.service._AuthService;

@RestController
@RequestMapping(value = "/api")
@CrossOrigin(origins = "*")
public class UserController {

	@Autowired
	private UserService userService;

	@Autowired
	private _AuthService userservice;

	private static final Logger logger = LoggerFactory.getLogger(_GenearlController.class);

	@PostMapping("login/user")//post and get
	public ResponseEntity<?> userLogin(@RequestBody LoginRequest loginRequest) {
		try {
			String token = userservice.login(loginRequest.getUsername(), loginRequest.getPassword());
			return new ResponseEntity<String>(token, HttpStatus.OK);
		} catch (Exception e) {
			logger.info("Error in authenticateUser ", e);
			return ResponseEntity.badRequest().body(new ApiResponse(e.getMessage(), ""));
		}
	}

	@PostMapping("/addUser")
	public User addUser(@RequestBody User user) {
		return userService.addUser(user);
	}

	@GetMapping("/admin/findAllUsers")
	public List<User> findAllUsers() {
		return userService.findAllUsers();
	}

	@PutMapping("/editUser/{id}")
	public User editUser(@RequestBody User user, @PathVariable long id) {
		return userService.editUser(user, id);
	}

	@GetMapping("/findUserById/{id}")
	public User findUserById(@PathVariable long id) {
		return userService.findUserById(id);
	}

	@DeleteMapping("/deleteUser/{id}")
	public void deleteUser(@PathVariable long id) {
		userService.deleteUser(id);
	}

	@GetMapping("/findByUsername/{username}")
	public User findByUsername(@PathVariable String username) {
		return userService.findByUsername(username);
	}
}
