package com.cdac.springbootdatabasesecuritydemo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ProductController {

	@GetMapping("/")
	public String welcomePage() {
		return "home";
	}
	
	@GetMapping("/new")
	public String newPage() {
		return "new_product";
	}
	
	@GetMapping("/edit")
	public String editPage() {
		return "edit_product";
	}
	
	@GetMapping("/delete")
	public String deletePage() {
		return "delete_product";
	}
	
	@GetMapping("/403")
	public String issuePage() {
		return "issue";
	}
}
