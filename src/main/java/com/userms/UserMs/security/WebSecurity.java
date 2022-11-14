package com.userms.UserMs.security;

import javax.servlet.Filter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.userms.UserMs.service.UserService;

@SuppressWarnings("deprecation")
@Configuration
@EnableWebSecurity
public class WebSecurity extends WebSecurityConfigurerAdapter {
	
	@Autowired
	private UserService userService;
	@Autowired
	private BCryptPasswordEncoder bCryptPasswordEncoder;

	protected void configure(HttpSecurity http) throws Exception {

		http.csrf().disable();
		http.authorizeRequests().antMatchers("/user/**").permitAll()
		.and().addFilter(authenticationFilter());

	}

	private Filter authenticationFilter() throws Exception {
		AuthenticationFilter authFilter=new AuthenticationFilter();
		authFilter.setAuthenticationManager(authenticationManager());
		authFilter.setFilterProcessesUrl("/users/login");
		return authFilter;
	}
	
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(userService).passwordEncoder(bCryptPasswordEncoder);
	}

}
