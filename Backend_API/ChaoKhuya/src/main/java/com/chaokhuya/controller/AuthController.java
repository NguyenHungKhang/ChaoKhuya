package com.chaokhuya.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.chaokhuya.payload.ApiResponse;
import com.chaokhuya.payload.AuthRequest;
import com.chaokhuya.payload.AuthResponse;
import com.chaokhuya.payload.UserDto;
import com.chaokhuya.security.JwtToken;
import com.chaokhuya.service.imp.UserService;

@RestController
@RequestMapping("/auth")
public class AuthController {
	
	@Autowired
	private JwtToken jwtToken;
	
	@Autowired
	private UserDetailsService userDetailService;
	
	@Autowired
	private AuthenticationManager authenticationManager;
	
	@Autowired
	private UserService userService;
	
	@PostMapping("/login")
	public ResponseEntity<AuthResponse> createToken(
			@RequestBody AuthRequest request
			) throws Exception{
		
		this.authenticate(request.getUsername(),request.getPassword());
		UserDetails userDetails = this.userDetailService.loadUserByUsername(request.getUsername());
		String token = this.jwtToken.generateToken(userDetails);
		AuthResponse response = new AuthResponse();
		response.setToken(token);		
		return new ResponseEntity<AuthResponse>(response,HttpStatus.OK); 
	}
	
	private void authenticate(String username, String password) throws Exception {
		UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(username, password);
		try {
			this.authenticationManager.authenticate(usernamePasswordAuthenticationToken);				
		}
		catch(BadCredentialsException ex) {
			System.out.println("invalid details of user in request");
			throw new Exception("Exception message");
		}
	}
	
	@PostMapping("/register")
	public ResponseEntity<UserDto> registerNewUser(@RequestBody UserDto userDto){
		UserDto newUser = this.userService.add(userDto);
		return new ResponseEntity<UserDto>(newUser,HttpStatus.OK);
	}
	
}
