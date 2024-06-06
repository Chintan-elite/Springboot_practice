package com.smart.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.smart.entities.User;
import com.smart.helper.Message;
import com.smart.repo.UserRepo;

import jakarta.servlet.http.HttpSession;



@Controller
public class HomeCotroller {
	
	@Autowired
	UserRepo userRepo;
	
	@RequestMapping("/")
	public String home(Model model)
	{
		model.addAttribute("title", "Home - smart contact manager");
		return "home";
	}
	
	@RequestMapping("/about")
	public String about(Model model)
	{
		model.addAttribute("title", "About - smart contact manager");
		return "about";
	}
	
	@RequestMapping("/signup")
	public String signup(Model model)
	{
		model.addAttribute("title", "Signup - smart contact manager");
		model.addAttribute("user", new User());
		return "signup";
	}
	
	@RequestMapping(value = "do_register", method = RequestMethod.POST)
	public String doRegister(@ModelAttribute("user") User user, @RequestParam(value = "agreement", defaultValue = "false") boolean agreement, Model model,HttpSession session)
	{
		try {
			if(!agreement)
			{
				//System.out.println("you dont accept terms and conditions");
				throw new Exception("you dont accept terms and conditions");
			}
			user.setRole("ROLE_USER");
			user.setEnable(true);
			user.setImageUrl("default.png");
			User result =  userRepo.save(user);
			model.addAttribute("user", result);
			session.setAttribute("message", new Message("Registration successfully !!!!", "alert-success"));
			return "signup";
		} catch (Exception e) {
			
			model.addAttribute("user",user);
			session.setAttribute("message", new Message("Somethin went Wrong !!!! "+e.getMessage(), "alert-danger"));
			return "signup";
		}
	}
	
	
	
	
	
	
}
