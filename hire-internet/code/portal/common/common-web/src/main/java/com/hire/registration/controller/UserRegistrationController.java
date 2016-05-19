package com.hire.registration.controller;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.request.WebRequest;

import com.common.base.constants.RegistrationLinkStatus;
import com.hire.dto.UserDTO;
import com.hire.registration.util.GenericResponse;
import com.hire.service.api.UserRegistrationServiceIntegration;
import com.hire.service.api.UserServiceIntegration;

@RequestMapping(value = "/user/registration")
@Controller
public class UserRegistrationController {
	
	@Autowired
	private UserRegistrationServiceIntegration userRegServiceIntegration;
	
	@Autowired
    private UserServiceIntegration userService;
	
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

        userRegServiceIntegration.registerNewUserAccount(accountDto);
       
        return new GenericResponse("success");
    }
	
	@RequestMapping(value = "/regitrationConfirm", method = RequestMethod.GET)
    public String confirmRegistration(final Model model, @RequestParam("token") final String token, 
    		@RequestParam("userId") final String userId) {
        final RegistrationLinkStatus result = userRegServiceIntegration.verifyToken(token);
        if (result == null) {
            model.addAttribute("error", "Could not verify Token");
            return "redirect:/login";
        }
        if (RegistrationLinkStatus.EXPIRED == result) {
            model.addAttribute("expired", true);
            model.addAttribute("token", token);
        }
        if(RegistrationLinkStatus.ACTIVE == result) {
        	if(userRegServiceIntegration.activateUser(userId)) {
        		model.addAttribute("message", "Verification Successfull");
        	}
        	else {
        		model.addAttribute("message", "Something went wrong, please contact Admin");
        	}
        	model.addAttribute("token", token);
        }
        
        return "";
    }
	
	@RequestMapping(value = "/resendRegistrationToken", method = RequestMethod.GET)
    @ResponseBody
	public GenericResponse resendRegistrationToken(final HttpServletRequest request, 
			@RequestParam("email") final String email) {
		
		UserDTO user = userService.findByUserEmail(email);
		if(user != null) {
			//generate new token and send mail
			
		}
		return null;
	}

}
