package com.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class WebController {
	
	@RequestMapping("/HelloWorld.html")
	public String helloWorld() {
		return "HelloWorld";
	}
	
}
