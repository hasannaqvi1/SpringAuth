package com.cg.Bis.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cg.Bis.model.User;
import com.cg.Bis.service.RegistrationService;

@EnableAutoConfiguration
@RestController
public class RegistrationController {
	
	@Autowired
	private RegistrationService service;
	
	@RequestMapping("/")
	public String hello() {
		return "Hello World";
	}
	@PostMapping("/register")
	@CrossOrigin("http://localhost:4200")
public User register(@RequestBody User user) throws Exception {
		String tempEmailId=user.getEmailId();
		if(tempEmailId !=null && !"".equals(tempEmailId)) {
			User userObj=service.fetchUserByEmailId(tempEmailId);
			if(userObj!=null) {
				throw new Exception("User with "+tempEmailId+"already exists");
			}
		}
	User userObj=null;
	userObj = service.saveUser(user);
	return userObj;
}
	@PostMapping("/login")
	@CrossOrigin("http://localhost:4200")
	public User loginUser(@RequestBody User user) throws Exception {
		String tempEmailId=user.getEmailId();
		String tempPass=user.getPassword();
		User userObj=null;
		if(tempEmailId!=null&&tempPass!=null) {
			service.fetchUserByEmailIdAndPassword(tempEmailId, tempPass);
		}
		if(userObj==null) {
			throw new Exception("Bad Credentials");
		}
		return userObj;
	}
}