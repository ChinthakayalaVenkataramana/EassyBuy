package com.vnk.eassy_buy.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.vnk.eassy_buy.dto.UserDto;
import com.vnk.eassy_buy.dto.UserRequest;
import com.vnk.eassy_buy.service.UserService;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.PathVariable;

@RestController
@RequestMapping("/auth")
@AllArgsConstructor
public class UserController {
	private final UserService userService;

	@PostMapping("/register")
	public ResponseEntity<String> register(@RequestBody UserDto userDto) {
		return new ResponseEntity<String>(userService.register(userDto), HttpStatusCode.valueOf(201));
	}

	@PostMapping("/login")
	public ResponseEntity<String> login(@RequestBody UserRequest userRequest) {
		return new ResponseEntity<String>(userService.login(userRequest), HttpStatus.OK);
	}

	@PutMapping("/update_password")
	public ResponseEntity<String> putMethodName(@RequestBody UserRequest userRequest) {
		return new ResponseEntity<String>(userService.forgotPassword(userRequest), HttpStatus.OK);
	}
	
}
