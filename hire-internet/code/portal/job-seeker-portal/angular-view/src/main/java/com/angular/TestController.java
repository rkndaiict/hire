package com.angular;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.angular.domain.TestDomain;
import com.angular.domain.TestResponseDomain;

@Controller
public class TestController {

	private static Logger logger = LoggerFactory.getLogger(TestController.class);
	
	@RequestMapping(value = "/testPage", method = RequestMethod.GET)
	public String getTestPage(HttpServletRequest httpRequest, HttpServletResponse httpResponse, Model model) {
		TestDomain testDomain = prepareTestDomain();
		model.addAttribute("testDomain", testDomain);
		return "testPage";
	}
	
	@RequestMapping(value = "/testPage", method = RequestMethod.POST)
	public @ResponseBody TestResponseDomain postTestPage() {
		logger.info("Reaching in welcome page");
		TestResponseDomain testResponseDomain = prepareTestResponseDomain();
		return testResponseDomain;
	}
	
	private TestDomain prepareTestDomain(){
		TestDomain testDomain = new TestDomain();
		testDomain.setState(true);
		testDomain.setStateValue("TestDomain");
		return testDomain;
	}

	private TestResponseDomain prepareTestResponseDomain(){
		TestResponseDomain testResponseDomain = new TestResponseDomain();
		testResponseDomain.setResponseState(true);
		testResponseDomain.setResponseText("testResponseDomain");
		return testResponseDomain;
	}

}
