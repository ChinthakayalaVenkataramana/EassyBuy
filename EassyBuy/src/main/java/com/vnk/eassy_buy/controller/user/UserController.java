package com.vnk.eassy_buy.controller.user;

import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.vnk.eassy_buy.dto.UserDto;
import com.vnk.eassy_buy.dto.UserRequest;
import com.vnk.eassy_buy.service.user.UserService;

import lombok.AllArgsConstructor;

@RestController
@RequestMapping("/auth")
@AllArgsConstructor
public class UserController {
	private final UserService userService;

	@PostMapping("/register")
	public ResponseEntity<String> register(@RequestBody UserDto userDto) {
		return new ResponseEntity<>(userService.register(userDto), HttpStatusCode.valueOf(201));
	}

	@PostMapping("/login")
	public ResponseEntity<String> login(@RequestBody UserRequest userRequest) {
		return new ResponseEntity<>(userService.login(userRequest), HttpStatus.OK);
	}

	@PutMapping("/forgot-password")
	public ResponseEntity<String> putMethodName(@RequestBody String mail) {
		return new ResponseEntity<>(userService.forgotPassword(mail), HttpStatus.OK);
	}

	@PostMapping("/verify")
	public ResponseEntity<String>otpVerification(@RequestParam String otp, @RequestParam String mail) throws Exception{
		return ResponseEntity.ok(userService.otpVerification(otp, mail));
	}

	@PostMapping("/resend-otp")
	public ResponseEntity<String> reSendOtp(@RequestParam String mail){
		return ResponseEntity.ok(userService.reSendOtp(mail,null));
	}
}
