package com.vnk.eassy_buy.service;

import com.vnk.eassy_buy.dto.UserDto;
import com.vnk.eassy_buy.dto.UserRequest;

public interface UserService {
	
	String register(UserDto dto);

	String login(UserRequest userRequest);
	
	String forgotPassword(UserRequest userRequest);
}
