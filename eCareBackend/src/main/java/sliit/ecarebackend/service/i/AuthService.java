package sliit.ecarebackend.service.i;

import org.springframework.security.core.userdetails.UserDetailsService;

import sliit.ecarebackend.dao.domain.AppUser;
import sliit.ecarebackend.dao.dto.AppUserDto;

public interface AuthService extends UserDetailsService{
	AppUser save(AppUserDto userDto);
}
