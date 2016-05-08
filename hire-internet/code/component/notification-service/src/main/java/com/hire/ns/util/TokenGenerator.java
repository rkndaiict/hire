package com.hire.ns.util;

import java.util.UUID;

import org.springframework.stereotype.Service;

@Service
public class TokenGenerator {

	public String generateToken(){
		return  UUID.randomUUID().toString();
	}


}
