package com.naveen.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.naveen.config.AuthenticateRequest;
import com.naveen.config.AuthenticateResponse;
import com.naveen.config.CustomAuthenticationProvider;
import com.naveen.config.JwtUtil;
import com.naveen.services.MyUserDetailsServices;



@RestController
public class AuthentiacationController {
	@Autowired
	private CustomAuthenticationProvider authenticationManager;
	
	@Autowired
	private MyUserDetailsServices userDetailsService;
	
	@Autowired
	private JwtUtil jwtTokenUtil;
	
	@RequestMapping({"/hellouser"})
	public String helloUser(){
		return "Hello User";
	}
	
	@RequestMapping({"/helloadmin"})
	public String helloAdmin(){
		return "Hello Admin";
	}
	
	@RequestMapping(value = "/authenticate", method = RequestMethod.POST)
	public ResponseEntity<?> createAuthenticationToken(@RequestBody AuthenticateRequest authenticationRequest) throws Exception {
		try {
			System.out.println("Authenticating: "+authenticationRequest.getUsername()+" pwd:"+authenticationRequest.getPassword());
			authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(authenticationRequest.getUsername(), authenticationRequest.getPassword()));
		} catch (DisabledException e) {
			throw new Exception("USER_DISABLED", e);
		} catch (BadCredentialsException e) {
			throw new Exception("INVALID_CREDENTIALS", e);
		}
	System.out.println("****************************************");
	
		final UserDetails userDetails = userDetailsService
				.loadUserByUsername(authenticationRequest.getUsername());
		System.out.println("###############################################");
		System.out.println("userDetails:"+userDetails);
		final String token = jwtTokenUtil.generateToken(userDetails);
		System.out.println("token:"+token);
		System.out.println("+++++++++++++++++++++++++++++");
		System.out.println("token_VAlues:"+jwtTokenUtil.getAllClaimsFromToken(token));
		
		return ResponseEntity.ok(new AuthenticateResponse(token));
	}

}