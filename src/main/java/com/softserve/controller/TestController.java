package com.softserve.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import com.softserve.service.RoleServices;

@Controller
public class TestController {

	@Autowired
	private RoleServices roleServices;
	
	
	@GetMapping(name = "/")
	public String getAll(){
		return "index";
	}
}
