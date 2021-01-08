package com.naveen.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.naveen.models.User;
import com.naveen.repo.UserRepository;

@Service
public class MyUserDetailsServices implements UserDetailsService {

	
	@Autowired
	private  UserRepository userdao;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		
		User user=userdao.findByName(username);
		if(user==null)
			throw new UsernameNotFoundException("user 404");
		else 
			
		return new UserDetailImplimentation(user);
			
	}
	
	
	

}
