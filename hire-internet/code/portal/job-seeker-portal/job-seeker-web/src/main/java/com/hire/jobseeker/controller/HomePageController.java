package com.hire.jobseeker.controller;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping(value = "/home")
public class HomePageController {

	private static Logger logger = LoggerFactory.getLogger(HomePageController.class);

	@RequestMapping(method=RequestMethod.GET)
	public String getHomePage(HttpServletRequest httpRequest, Model model){
		logger.info("Reaching in welcome page");
		return "home";
	}
	
	
}
