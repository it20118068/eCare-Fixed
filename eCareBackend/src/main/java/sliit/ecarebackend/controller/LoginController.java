package sliit.ecarebackend.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import sliit.ecarebackend.dao.dto.AppUserDto;
import sliit.ecarebackend.repository.AppUserRepository;
import sliit.ecarebackend.service.i.AuthService;



@Controller
@RequestMapping("/login")
public class LoginController {
	@Autowired
	AuthService authService;
	
	
	@Autowired
	AppUserRepository userRepo;
    
    @ModelAttribute("user")
    public AppUserDto userLoginDTO() {
        return new AppUserDto();
    }
    
	@GetMapping
	public String login() {
		return "login";
	}
	
	@PostMapping
	public void loginUser(@ModelAttribute("user") 
	AppUserDto userLoginDTO) {
		System.out.println("UserDTO"+userLoginDTO);
		authService.loadUserByUsername(userLoginDTO.getUsername());
	}
	
}
