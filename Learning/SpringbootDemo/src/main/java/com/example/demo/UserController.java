package com.example.demo;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;


@Controller
public class UserController {
	
	@GetMapping("/home")
	public String index(Model model)
	{
		return "home";
	}
}
