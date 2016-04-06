package com.angular.dashboard;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class DashboardController {

	private static Logger logger = LoggerFactory.getLogger(DashboardController.class);

	@RequestMapping(value = {"/dashboard"}, method = RequestMethod.GET)
	public String getDashboardPage(HttpServletRequest httpRequest, HttpServletResponse httpResponse, Model model) {
		return "pages/dashboard";
	}

	@RequestMapping(value = {""}, method = RequestMethod.GET)
	public String getHomePage(HttpServletRequest httpRequest, HttpServletResponse httpResponse, Model model) {
		return "redirect:/dashboard";
	}

}

