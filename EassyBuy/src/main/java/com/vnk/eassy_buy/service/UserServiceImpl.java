package com.vnk.eassy_buy.service;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.vnk.eassy_buy.Entity.User;
import com.vnk.eassy_buy.config.JwtService;
import com.vnk.eassy_buy.constants.ResponseMessages;
import com.vnk.eassy_buy.constants.UserRoles;
import com.vnk.eassy_buy.dto.UserDto;
import com.vnk.eassy_buy.dto.UserRequest;
import com.vnk.eassy_buy.repository.UserRepository;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class UserServiceImpl implements UserService {
	
	private final UserRepository userRepository;
	private final PasswordEncoder passwordEncoder;
	private final JwtService jwtService;

	@Override
	public String register(UserDto dto) {
		if (userRepository.existsByMail(dto.getMail())) {
			throw new RuntimeException(ResponseMessages.USER_ALREADY_EXISTS.getMessage());
		}

		userRepository.save(User.builder().mail(dto.getMail()).mobile(dto.getMobile())
				.password(passwordEncoder.encode(dto.getPassword())).role(UserRoles.BUYER).username(dto.getUsername())
				.build());
		return ResponseMessages.USER_REGISTERED_SUCCESSFULLY.getMessage();
	}

	@Override
	public String login(UserRequest userRequest) {
		User user = userRepository.findByMail(userRequest.getMail())
				.orElseThrow(() -> new RuntimeException(ResponseMessages.INVALID_MAIL.getMessage()));
		boolean matches = passwordEncoder.matches(userRequest.getPassword(), user.getPassword());
		if (!matches) {
			throw new RuntimeException(ResponseMessages.INVALID_USERNAME_OR_PASSWORD.getMessage());
		}
		return jwtService.generateToken(userRequest.getMail(),UserRoles.BUYER.name());
	}

	@Override
	public String forgotPassword(UserRequest userRequest) {
		if (userRepository.existsByMail(userRequest.getMail())) {
			User user = userRepository.findByMail(userRequest.getMail()).orElseThrow(() -> {
				throw new RuntimeException(ResponseMessages.INVALID_MAIL.getMessage() );
			});

			return updatePassword(User.builder().id(user.getId()).mail(user.getMail()).mobile(user.getMobile())
					.password(passwordEncoder.encode(userRequest.getPassword())).role(user.getRole()).username(user.getUsername())
					.build());
		}
		throw new RuntimeException(ResponseMessages.INVALID_MAIL.getMessage());
	}

	private String updatePassword(User user) {
		userRepository.save(user);
		return  ResponseMessages.PASSWORD_CHANGED_SUCCESSFULLY.getMessage();
	}

}
