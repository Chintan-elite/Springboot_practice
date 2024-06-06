package com.apigateway.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class FallBackController {
	
	@GetMapping("/userServiceFallback")
	public String userServiceFallback() {
		return "User service down at this time !!!";
	}
	
	@GetMapping("/contectServiceFallback")
	public String contectServiceFallback()
	{
		return "Contact service down at this time !!!";
	}
}
