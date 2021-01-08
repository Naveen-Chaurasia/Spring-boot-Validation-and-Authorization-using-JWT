package com.naveen.config;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Component;

import com.naveen.models.User;
import com.naveen.repo.UserRepository;

@Component
public class CustomAuthenticationProvider implements AuthenticationProvider {

	

	@Autowired
	private UserRepository userdao;

	@Override
	public Authentication authenticate(Authentication authentication) throws AuthenticationException {
		String name = authentication.getName();
		String password = authentication.getCredentials().toString();
		System.out.println("dskjfbasdfbasjd : " + name + "dbasdjhfbsadjfbd  :  " + password);
		if (shouldAuthenticateAgainstThirdPartySystem(name, password)) {

			return new UsernamePasswordAuthenticationToken(name, password, new ArrayList<>());
		}

		else {

			return null;
		}

	}

	private boolean shouldAuthenticateAgainstThirdPartySystem(String name, String password) {

		User user = userdao.findByName(name);

		if (user == null)
		{
			return false;
		}
		else
		{
			return true;
		}

	}

	@Override
	public boolean supports(Class<?> authentication) {

		return authentication.equals(UsernamePasswordAuthenticationToken.class);
	}

}
