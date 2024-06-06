package com.smart.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.smart.model.User;
import com.smart.service.UserService;

@Controller
public class UserController {

	@Autowired
	UserService userService;
	
	@RequestMapping("/")
	public String index(Model model)
	{
		model.addAttribute("user", new User());
		return "index";
	}
	
	@RequestMapping(method = RequestMethod.POST,value = "reg")
	public String reg(@ModelAttribute("user") User u,Model model)
	{
	
		userService.addOrUpdate(u);
		model.addAttribute("user", new User());
		model.addAttribute("msg", "registration success");
		return "index";
	}
	
	@RequestMapping("/display")
	public String disaply(Model model)
	{
		model.addAttribute("data", userService.viewAllUsers());
		return "display";
	}
	
	@RequestMapping("/delete")
	public String delete(@RequestParam("did") int id)
	{
		userService.deleteUser(id);
		return "redirect:display";
	}
	
	@RequestMapping("/edit")
	public String delete(@RequestParam("eid") int id,Model model)
	{
		
		model.addAttribute("user",userService.userBuId(id));
		return "index";
	}
	
	
}
