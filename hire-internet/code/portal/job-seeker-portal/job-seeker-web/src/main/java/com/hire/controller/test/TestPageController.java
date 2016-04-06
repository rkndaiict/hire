package com.hire.controller.test;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping(value = "/test")
public class TestPageController {

	private static Logger logger = LoggerFactory.getLogger(TestPageController.class);

	@RequestMapping(method=RequestMethod.GET)
	public String getHomePage(HttpServletRequest httpRequest, Model model){
		logger.info("Reaching in welcome page");
		return "test";
	}

	@RequestMapping(value="value", method=RequestMethod.GET)
	public @ResponseBody String getHomePageTextInJson(HttpServletRequest httpRequest, Model model){
		logger.info("Reaching in welcome page");
		return "test";
	}

}
