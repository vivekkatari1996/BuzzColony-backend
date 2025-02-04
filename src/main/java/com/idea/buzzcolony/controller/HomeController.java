package com.idea.buzzcolony.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import springfox.documentation.annotations.ApiIgnore;

@Controller
@ApiIgnore
public class HomeController {

	@GetMapping("/")
	public String navigateToSwagger() {
		return "redirect:/swagger-ui.html";
	}

}
