package com.blog.app.security;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

@Component
public class JwtAuthenticationFilter extends OncePerRequestFilter {

	@Autowired
	UserDetailsService userDetailsService;
	
	@Autowired
	JwtTokenHelper jwtTokenHelper;
	
	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		
		
		//1.get token
		String requestToken = request.getHeader("Authorization");
		
		String username = null;
		String token = null;
		
		
		if(requestToken != null && requestToken.startsWith("Bearer"))
		{
			
			token = requestToken.substring(7);
			
			try {
				username = this.jwtTokenHelper.extractUsername(token);
				System.out.println(username);
			} catch (Exception e) {
				e.printStackTrace();
			}
			
		}
		else
		{
			System.out.println("not bearer");
		}
		
		//validate token
		
		if(username!=null && SecurityContextHolder.getContext().getAuthentication()==null)
		{
			UserDetails userDetails = this.userDetailsService.loadUserByUsername(username);
			if(this.jwtTokenHelper.validateToken(token, userDetails))
			{
				UsernamePasswordAuthenticationToken  authenticationToken = new UsernamePasswordAuthenticationToken(userDetails,null,userDetails.getAuthorities());
				authenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
				
				SecurityContextHolder.getContext().setAuthentication(authenticationToken);
			}
			else
			{
				System.out.println("invalid token");
			}
		}
		else
		{
			System.out.println("uname or context is null");
		}
		
		filterChain.doFilter(request, response);
		
	}

	
	
}
