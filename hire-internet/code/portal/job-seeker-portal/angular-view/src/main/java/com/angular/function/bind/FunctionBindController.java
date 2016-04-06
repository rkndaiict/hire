package com.angular.function.bind;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class FunctionBindController {

	@RequestMapping(value = "/functionbind", method = RequestMethod.GET)
	public String getHelloWorldPage(HttpServletRequest httpRequest, HttpServletResponse httpResponse, Model model) {
		return "pages/functionBind";
	}

	@RequestMapping(value = "/functionbindsample", method = RequestMethod.GET)
	public String getHelloWorldSamplePage(HttpServletRequest httpRequest, HttpServletResponse httpResponse, Model model) {
		return "pages/functionBindSample";
	}

}
