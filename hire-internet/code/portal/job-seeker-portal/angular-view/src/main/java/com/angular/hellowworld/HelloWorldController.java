package com.angular.hellowworld;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class HelloWorldController {

	private static Logger logger = LoggerFactory.getLogger(HelloWorldController.class);
	
	@RequestMapping(value = "/helloWorld", method = RequestMethod.GET)
	public String getHelloWorldPage(HttpServletRequest httpRequest, HttpServletResponse httpResponse, Model model) {
		return "pages/helloWorld";
	}

	@RequestMapping(value = "/helloWorldSample", method = RequestMethod.GET)
	public String getHelloWorldSamplePage(HttpServletRequest httpRequest, HttpServletResponse httpResponse, Model model) {
		return "pages/helloWorldSample";
	}

}
