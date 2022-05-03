/*package com.naveen.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;



@Configuration
public class ApplicationSecurity extends WebSecurityConfigurerAdapter {
	@Override
	public void configure(WebSecurity web) throws Exception {
		web.ignoring().antMatchers("/**");

		
	}

}*/

package com.naveen.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.naveen.services.MyUserDetailsServices;

@Configuration
@EnableWebSecurity
public class ApplicationSecurity extends WebSecurityConfigurerAdapter{
	
	@Autowired
	private CustomJwtAuthenticationFilter customJwtAuthenticationFilter;
	
	@Autowired
  	private JwtAuthenticationEntryPoint unauthorizedHandler;
	
	@Autowired
	private MyUserDetailsServices userDetailsService;
	
	@Bean
	public PasswordEncoder passwordEncoder(){
		return new BCryptPasswordEncoder();
	}
	
	@Override
	public void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder());
	}
	
	@Bean
	@Override
	public AuthenticationManager authenticationManagerBean() throws Exception {
		return super.authenticationManagerBean();
	}
	
//	@Override
//	public void configure(WebSecurity web) throws Exception {
//		web.ignoring().antMatchers("/**");

	
	@Override
//	public void configure(HttpSecurity http) throws Exception {
//		
//		http.csrf().disable()
//		.authorizeRequests().antMatchers("/helloadmin").hasAnyRole("ADMIN")
//		.antMatchers("/authenticate").permitAll()
//		.antMatchers("/hellouser").hasAnyRole("USER","ADMIN")
//		.antMatchers("/adduser","/users","/deleteuser","/updateuser").permitAll().anyRequest().authenticated()
//		.and().exceptionHandling()
//        .authenticationEntryPoint(unauthorizedHandler).and().
//		// make sure we use stateless session; session won't be used to
//		// store user's state.
//		sessionManagement()
//		.sessionCreationPolicy(SessionCreationPolicy.STATELESS);
//
//// 		Add a filter to validate the tokens with every request
//		http.addFilterBefore(customJwtAuthenticationFilter, 
//		UsernamePasswordAuthenticationFilter.class);
//	}
	public void configure(HttpSecurity http) throws Exception {
		http.csrf().disable()
		.authorizeRequests().antMatchers("/helloadmin").hasRole("ADMIN","USER")
		.antMatchers("/hellouser").hasAnyRole("USER","ADMIN")
		.antMatchers("/authenticate","/adduser","/users","/deleteuser","/updateuser").permitAll().anyRequest().authenticated()
		//if any exception occurs call this
		.and().exceptionHandling()
        .authenticationEntryPoint(unauthorizedHandler).and().
		// make sure we use stateless session; session won't be used to
		// store user's state.
		sessionManagement()
		.sessionCreationPolicy(SessionCreationPolicy.STATELESS);

// 		Add a filter to validate the tokens with every request
		http.addFilterBefore(customJwtAuthenticationFilter, 
		UsernamePasswordAuthenticationFilter.class);
		
	}
	}


