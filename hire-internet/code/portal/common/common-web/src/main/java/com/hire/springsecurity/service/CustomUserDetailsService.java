package com.hire.springsecurity.service;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.hire.dto.UserDTO;
import com.hire.dto.UserProfileDTO;
import com.hire.service.api.UserServiceIntegration;
import com.service.usermanagement.impl.UserDataServiceImpl;
 
@Component
public class CustomUserDetailsService implements UserDetailsService{
 
	public static Logger logger = LoggerFactory.getLogger(CustomUserDetailsService.class);
	
    @Autowired
    private UserServiceIntegration userService;
     
    @Transactional(readOnly=true)
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
    	
        UserDTO userDTO = userService.findByUserName(username);
        logger.info("User : "+userDTO);
        if(userDTO==null){
            System.out.println("User not found");
            logger.error("User not found");
            throw new UsernameNotFoundException("Username not found");
        }
            return new org.springframework.security.core.userdetails.User(userDTO.getUserName(), userDTO.getPassword(), 
                 userDTO.getStatus().equals("Active"), true, true, true, getGrantedAuthorities(userDTO));
    }
 
     
    private List<GrantedAuthority> getGrantedAuthorities(UserDTO user){
        List<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();
         
        String userProfile = user.getUserProfile();
        logger.debug("UserProfile : "+userProfile);
        authorities.add(new SimpleGrantedAuthority("ROLE_"+userProfile));
        
        logger.info("authorities :"+authorities);
        return authorities;
    }
     
}
