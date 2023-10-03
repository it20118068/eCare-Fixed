package sliit.ecarebackend.service.impl;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import sliit.ecarebackend.dao.domain.AppUser;
import sliit.ecarebackend.dao.dto.AppUserDto;
import sliit.ecarebackend.repository.AppUserRepository;
import sliit.ecarebackend.service.i.AuthService;

@Service
public class AuthServiceImpl implements AuthService{
	
	
	@Autowired 
	private AppUserRepository userRepo;
	
	private BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
		AppUser user = userRepo.findByEmail(email);
		if(user == null) {
			throw new UsernameNotFoundException("Invalid username or password.");
		}
		return new org.springframework.security.core.userdetails.User(user.getEmail(), user.getPassword(),new ArrayList<>());		

	}

	@Override
	public AppUser save(AppUserDto userDto) {
		AppUser newUser = new AppUser();
		if (userDto == null) {
			throw new UsernameNotFoundException("User cannot be null");
		}
		
		newUser.setUsername(userDto.getUsername());
		newUser.setFirstName(userDto.getFirstName());
		newUser.setLastName(userDto.getLastName());
		newUser.setEmail(userDto.getEmail());
		newUser.setMobile(userDto.getMobile());
		newUser.setUserRole(userDto.getUserRole());
		newUser.setPassword(passwordEncoder.encode(userDto.getPassword()));
		return userRepo.save(newUser);
	}

}
