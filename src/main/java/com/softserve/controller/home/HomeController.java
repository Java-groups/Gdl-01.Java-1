package com.softserve.controller.home;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;

@Controller
public class HomeController {

	@GetMapping()
	public String welcome(@ModelAttribute ModelAttribute model) {
		return "some";
	}
}
