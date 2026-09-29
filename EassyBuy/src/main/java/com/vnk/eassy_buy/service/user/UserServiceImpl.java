package com.vnk.eassy_buy.service.user;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.vnk.eassy_buy.Entity.User;
import com.vnk.eassy_buy.Entity.otp.Otp;
import com.vnk.eassy_buy.config.JwtService;
import com.vnk.eassy_buy.config.util.OtpUtil;
import com.vnk.eassy_buy.constants.LoginProvider;
import com.vnk.eassy_buy.constants.ResponseMessages;
import com.vnk.eassy_buy.constants.TemplateType;
import com.vnk.eassy_buy.constants.UserRoles;
import com.vnk.eassy_buy.dto.UserDto;
import com.vnk.eassy_buy.dto.UserRequest;
import com.vnk.eassy_buy.notification.email.EmailService;
import com.vnk.eassy_buy.repository.UserRepository;
import com.vnk.eassy_buy.repository.otp.OtpRepository;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@AllArgsConstructor
@Slf4j
public class UserServiceImpl implements UserService {

	private static final int OTP_EXPIRY_MINUTES = 1;

	private final UserRepository userRepository;
	private final PasswordEncoder passwordEncoder;
	private final JwtService jwtService;
	private final OtpRepository otpRepository;
	private final EmailService emailService;

	@Override
	public String register(UserDto dto) {
		if (userRepository.existsByMail(dto.getMail())) {
			throw new RuntimeException(ResponseMessages.USER_ALREADY_EXISTS.getMessage());
		}
		sendOtp(dto.getMail(), dto.getUsername());
		userRepository.save(User.builder().mail(dto.getMail()).mobile(dto.getMobile())
				.password(passwordEncoder.encode(dto.getPassword())).role(UserRoles.BUYER).username(dto.getUsername())
				.provider(List.of(LoginProvider.LOCAL)).build());
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
		return jwtService.generateToken(userRequest.getMail(), user.getRole().name());
	}

	@Override
	public String forgotPassword(UserRequest userRequest) {
		if (userRepository.existsByMail(userRequest.getMail())) {
			User user = userRepository.findByMail(userRequest.getMail()).orElseThrow(() -> {
				throw new RuntimeException(ResponseMessages.INVALID_MAIL.getMessage());
			});

			return updatePassword(User.builder().id(user.getId()).mail(user.getMail()).mobile(user.getMobile())
					.password(passwordEncoder.encode(userRequest.getPassword())).role(user.getRole())
					.username(user.getUsername()).build());
		}
		throw new RuntimeException(ResponseMessages.INVALID_MAIL.getMessage());
	}

	private String updatePassword(User user) {
		userRepository.save(user);
		return ResponseMessages.PASSWORD_CHANGED_SUCCESSFULLY.getMessage();
	}

	private void sendOtp(String mail, String username) {
		String genarateOtp = OtpUtil.genarateOtp();
		otpRepository.save(Otp.builder().attempts(0).expiryTime(LocalDateTime.now().plusMinutes(OTP_EXPIRY_MINUTES))
				.mail(mail).otp(genarateOtp).verified(false).build());

		Map<String, Object> data = new HashMap<>();

		data.put("name", username);
		data.put("otp", genarateOtp);
		data.put("expiryMinutes", OTP_EXPIRY_MINUTES);

		try {
			emailService.sendTemplateEmail(mail, "EassyBuy - Email Verification OTP", TemplateType.OTP.getTemplate(),
					data);
		} catch (IOException e) {
			log.error("Failed to send OTP email to: {}", mail, e);
		}
	}

	@Override
	public String otpVerification(String otp, String mail) throws Exception {

		if (!otpRepository.existsByMail(mail)) {
			log.warn("OTP record not found for user: {}", mail);
			return "OTP not found";
		}

		Otp otpEntity = otpRepository.findByMailAndOtp(mail, otp).orElseThrow(() -> new Exception("Invalid OTP"));

		if (otpEntity.getVerified()) {
			log.warn("OTP already verified for user: {}", mail);
			return "OTP already verified";
		}
		
		if (otpEntity.getExpiryTime().isBefore(LocalDateTime.now())) {

			log.warn("OTP expired for user: {}", mail);

			return "OTP expired";
		}
		
		otpEntity.setVerified(true);
		otpRepository.save(otpEntity);
		log.info("OTP verified successfully for user: {}", mail);
		return "OTP verified Successfully";
	}

	@Override
	public String reSendOtp(String mail) {
		if (userRepository.existsByMail(mail)) {
			Otp optionalOtp = otpRepository.findByMail(mail)
			        .orElseThrow(() -> new RuntimeException("OTP not found"));
			if (!optionalOtp.getVerified()) {
				String name = mail.substring(0, mail.indexOf("@"));
				sendOtp(mail, name);
				return "OTP resent successfully";
			}
			return "OTP already verified";
		}
		return "Invalid Mail";
	}
}
