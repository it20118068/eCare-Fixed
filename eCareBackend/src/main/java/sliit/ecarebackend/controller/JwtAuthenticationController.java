//package sliit.ecarebackend.controller;
//
//import java.util.Objects;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.ResponseEntity;
//import org.springframework.security.authentication.AuthenticationManager;
//import org.springframework.security.authentication.BadCredentialsException;
//import org.springframework.security.authentication.DisabledException;
//import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
//import org.springframework.security.core.annotation.AuthenticationPrincipal;
//import org.springframework.security.oauth2.core.user.OAuth2User;
//import org.springframework.web.bind.annotation.CrossOrigin;
//import org.springframework.web.bind.annotation.RequestBody;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RequestMethod;
//import org.springframework.web.bind.annotation.RestController;
//
//import sliit.ecarebackend.dao.dto.AppUserDto;
//import sliit.ecarebackend.dao.dto.JwtRequest;
//import sliit.ecarebackend.service.impl.JwtUserDetailsService;
//
//
//@RestController
//@CrossOrigin
//public class JwtAuthenticationController {
//
//	@Autowired
//	private AuthenticationManager authenticationManager;
//
//
//	
//	@Autowired
//	private JwtUserDetailsService userDetailsService;
//
//	@RequestMapping(value = "/authenticate", method = RequestMethod.POST)
//	public ResponseEntity<?> createAuthenticationToken(@RequestBody JwtRequest authenticationRequest)
//			throws Exception {
//
//		authenticate(authenticationRequest.getUsername(), authenticationRequest.getPassword());
//
//		
//		AppUserDto userDto = userDetailsService.findUserByUsername(authenticationRequest.getUsername());
//		return ResponseEntity.ok(userDto);
//	}
//	
//	@RequestMapping(value = "/register", method = RequestMethod.POST)
//	public ResponseEntity<?> saveUser(@RequestBody AppUserDto user) throws Exception {
//		return ResponseEntity.ok(userDetailsService.save(user));
//	}
//
//	private void authenticate(String username, String password) throws Exception {
//		Objects.requireNonNull(username);
//		Objects.requireNonNull(password);
//
//		try {
//			authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
//		} catch (DisabledException e) {
//			throw new Exception("USER_DISABLED", e);
//		} catch (BadCredentialsException e) {
//			throw new Exception("INVALID_CREDENTIALS", e);
//		}
//	}
//	
//	@RequestMapping("/oauth2/login-success")
//    public ResponseEntity<String> loginSuccess(@AuthenticationPrincipal OAuth2User principal) {
//        // Handle the authenticated user, e.g., save user details to the database
//        // ...
//		System.out.println();
//
//        // You can redirect the user to a different page or return a response accordingly
//        return ResponseEntity.ok("Login successful!");
//    }
//	
//	@RequestMapping(value = "/sessionAuth", method = RequestMethod.POST)
//	public ResponseEntity<?> sessionAuth()
//			throws Exception {
//		return ResponseEntity.ok("Hello World");
//	}
//}
