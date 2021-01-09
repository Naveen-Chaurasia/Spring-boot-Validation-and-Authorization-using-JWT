package com.naveen.services;

import java.util.Collection;
import java.util.Collections;
import java.util.Objects;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.naveen.models.User;

public class UserDetailImplimentation implements UserDetails {

	private User user;



	public UserDetailImplimentation(User user) {
		super();
		this.user = user;
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {

//		System.out.println(user.getRole());
//		return Collections.singleton(new SimpleGrantedAuthority("ADMIN"));
		/*
		 * if("admin".equalsIgnoreCase(user.getRole())) { return
		 * Collections.singleton(new SimpleGrantedAuthority("ADMIN")); } else if
		 * ("user".equalsIgnoreCase(user.getRole())) { return Collections.singleton(new
		 * SimpleGrantedAuthority("USER")); }
		 */
		return Collections.singleton(new SimpleGrantedAuthority(
				Objects.nonNull(user.getRole())?user.getRole().toUpperCase():""));
		
		//return null;
	}
    
	
	
	
	@Override
	public String getPassword() {
		// TODO Auto-generated method stub
		return user.getPassword();
	}

	@Override
	public String getUsername() {
		// TODO Auto-generated method stub
		return  user.getName();
	}

	@Override
	public boolean isAccountNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isEnabled() {
		// TODO Auto-generated method stub
		return true;
	}

}
