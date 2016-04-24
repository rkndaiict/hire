package com.hire.springsecurity.service;

import java.util.ArrayList;
import java.util.List;
 
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
 
@Component
public class CustomUserDetailsService implements UserDetailsService{
 
    @Autowired
    private UserServiceIntegration userService;
     
    @Transactional(readOnly=true)
    public UserDetails loadUserByUsername(String userId) throws UsernameNotFoundException {
    	
        UserDTO user = userService.findByUserId(userId);
        System.out.println("User : "+user);
        if(user==null){
            System.out.println("User not found");
            throw new UsernameNotFoundException("Username not found");
        }
            return new org.springframework.security.core.userdetails.User(user.getSsoId(), user.getPassword(), 
                 user.getStatus().equals("Active"), true, true, true, getGrantedAuthorities(user));
    }
 
     
    private List<GrantedAuthority> getGrantedAuthorities(UserDTO user){
        List<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();
         
        for(UserProfileDTO userProfile : user.getUserProfiles()){
            System.out.println("UserProfile : "+userProfile);
            authorities.add(new SimpleGrantedAuthority("ROLE_"+userProfile.getType()));
        }
        System.out.print("authorities :"+authorities);
        return authorities;
    }
     
}
