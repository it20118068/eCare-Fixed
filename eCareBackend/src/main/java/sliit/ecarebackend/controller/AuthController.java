package sliit.ecarebackend.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import sliit.ecarebackend.dao.dto.AppUserDto;
import sliit.ecarebackend.service.i.AuthService;

@RestController
@CrossOrigin
public class AuthController {
	
	@Autowired
	AuthService authService;
	
	

	
	@RequestMapping(value = "/register", method = RequestMethod.POST)
	public ResponseEntity<?> saveUser(@RequestBody AppUserDto user) throws Exception {
		return ResponseEntity.ok(authService.save(user));
	}
	
	@RequestMapping(value = "/authenticate", method = RequestMethod.POST)
	public void loginUser(@RequestBody AppUserDto user){
		System.out.println("UserDTO"+user);
		authService.loadUserByUsername(user.getUsername());
	}
	

}
