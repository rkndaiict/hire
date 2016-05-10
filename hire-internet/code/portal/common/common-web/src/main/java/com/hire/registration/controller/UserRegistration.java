package com.hire.registration.controller;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.request.WebRequest;

import com.hire.dto.UserDTO;
import com.hire.registration.util.GenericResponse;
import com.hire.service.api.UserRegistrationService;

@RequestMapping(value = "/user/registration")
public class UserRegistration {
	
	@Autowired
	private UserRegistrationService userService;
	
	@RequestMapping(method = RequestMethod.GET)
	public String showRegistrationForm(WebRequest request, Model model) {
	    UserDTO userDto = new UserDTO();
	    model.addAttribute("user", userDto);
	    return "../registration";
	}
	
	@RequestMapping(method = RequestMethod.POST)
    @ResponseBody
	public GenericResponse registerUserAccount(@Valid final UserDTO accountDto, 
			final HttpServletRequest request) {

        userService.registerNewUserAccount(accountDto);
       
        return new GenericResponse("success");
    }

}
