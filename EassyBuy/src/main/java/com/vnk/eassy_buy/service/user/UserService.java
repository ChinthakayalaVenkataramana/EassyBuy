package com.vnk.eassy_buy.service.user;

import com.vnk.eassy_buy.dto.UserDto;
import com.vnk.eassy_buy.dto.UserRequest;

public interface UserService {
	
	String register(UserDto dto);

	String login(UserRequest userRequest);
	
	String forgotPassword(UserRequest userRequest);
	
	String otpVerification(String otp, String mail) throws Exception;
	
	String reSendOtp(String mail);
}
