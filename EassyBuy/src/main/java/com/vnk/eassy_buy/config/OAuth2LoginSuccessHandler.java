package com.vnk.eassy_buy.config;

import java.io.IOException;

import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import com.vnk.eassy_buy.Entity.User;
import com.vnk.eassy_buy.constants.ResponseMessages;
import com.vnk.eassy_buy.repository.UserRepository;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class OAuth2LoginSuccessHandler extends SimpleUrlAuthenticationSuccessHandler {

	private final JwtService jwtService;
	private final UserRepository userRepository;

	@Override
	public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
			Authentication authentication) throws IOException, ServletException {

		OAuth2User oauth2User = (OAuth2User) authentication.getPrincipal();

		String email = oauth2User.getAttribute("email");

		User user = userRepository.findByMail(email)
				.orElseThrow(() -> new RuntimeException(ResponseMessages.INVALID_USER.getMessage()));
		String token = jwtService.generateToken(email, user.getRole().name());

		response.setContentType("text/plain");

        response.getWriter().write(
                "Google Login Successful\n\n"
                + "Email: " + email
                + "\n"
                + "Username: " + user.getUsername()
                + "\n"
                + "Role: " + user.getRole()
                + "\n\n"
                + "JWT Token:\n"
                + token
        );
		///getRedirectStrategy().sendRedirect(request, response, redirectUrl);
	}

}
