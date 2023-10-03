package sliit.ecarebackend.config;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.core.user.DefaultOAuth2User;
import org.springframework.security.web.DefaultRedirectStrategy;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import sliit.ecarebackend.dao.dto.AppUserDto;
import sliit.ecarebackend.repository.AppUserRepository;
import sliit.ecarebackend.service.i.AuthService;

@Component
public class CustomSuccessHandler implements AuthenticationSuccessHandler{

	@Autowired
	AppUserRepository userRepo;
	
	@Autowired
	AuthService authService;
		
	@Override
	public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
			Authentication authentication) throws IOException, ServletException {

		String redirectUrl = null;
		if(authentication.getPrincipal() instanceof DefaultOAuth2User) {
		DefaultOAuth2User  userDetails = (DefaultOAuth2User ) authentication.getPrincipal();
         String username = userDetails.getAttribute("email") !=null?userDetails.getAttribute("email"):userDetails.getAttribute("login")+"@gmail.com" ;
          if(userRepo.findByEmail(username) == null) {
        	  AppUserDto user = new AppUserDto();
        	  user.setEmail(username);
        	  user.setFirstName(userDetails.getAttribute("email") !=null?userDetails.getAttribute("email"):userDetails.getAttribute("login"));
        	  user.setPassword(("Dummy"));
        	  user.setUserRole(1);
        	  authService.save(user);
          }
		}  redirectUrl = "http://localhost:3000/";
		new DefaultRedirectStrategy().sendRedirect(request, response, redirectUrl);
	}

}
