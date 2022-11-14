package com.userms.UserMs.security;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;

import javax.servlet.FilterChain;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.fasterxml.jackson.core.exc.StreamReadException;
import com.fasterxml.jackson.databind.DatabindException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.userms.UserMs.model.LoginModel;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

public class AuthenticationFilter extends UsernamePasswordAuthenticationFilter {

	@Override
	public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) {

		LoginModel cred = null;
		try {
			cred = new ObjectMapper().readValue(request.getInputStream(), LoginModel.class);
		} catch (StreamReadException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (DatabindException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return getAuthenticationManager().authenticate(
				new UsernamePasswordAuthenticationToken(cred.getEmail(), cred.getPassword(), new ArrayList<>()));

	}

	@Override
	protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain,
			Authentication authResult) {
		String email = ((User)authResult.getPrincipal()).getUsername();
		
		String token = Jwts.builder()
				       .setSubject(email)
				       .setExpiration(new Date(System.currentTimeMillis() + 600000L))
				       .signWith(SignatureAlgorithm.HS512, "This is my Secret Key")
				       .compact();
		
		response.setHeader("token", token);

	}

}
