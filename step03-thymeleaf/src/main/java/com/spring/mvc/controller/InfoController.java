package com.spring.mvc.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.spring.mvc.dto.Info;

@Controller
public class InfoController {
	@GetMapping(value = "/input-form")
	public String inputForm() {
		return "input";
	}
	
	@PostMapping(value = "/input-data")
	public String dataForm(Info info, Model model) {
		System.out.println(info);
	    model.addAttribute("info", info);
	    return "output";
	}
}
