package com.vnk.eassy_buy.config;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.security.oauth2.client.oidc.userinfo.OidcUserRequest;
import org.springframework.security.oauth2.client.oidc.userinfo.OidcUserService;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.oidc.user.OidcUser;
import org.springframework.stereotype.Service;

import com.vnk.eassy_buy.Entity.User;
import com.vnk.eassy_buy.constants.LoginProvider;
import com.vnk.eassy_buy.constants.UserRoles;
import com.vnk.eassy_buy.repository.UserRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class GoogleOAuth2UserService extends OidcUserService {
	private final UserRepository userRepository;

	@Override
	public OidcUser loadUser(OidcUserRequest  userRequest) throws OAuth2AuthenticationException {

		OidcUser user = super.loadUser(userRequest);

		String email = user.getAttribute("email");
		String name = user.getAttribute("name");

		if (email == null) {
			throw new OAuth2AuthenticationException("Google account email not available");
		}

		Optional<User> existingUser = userRepository.findByMail(email);

		// New Google user
		if (existingUser.isEmpty()) {

			User newUser = User.builder().mail(email).mobile(null)
					.provider(new ArrayList<>(List.of(LoginProvider.GOOGLE))).password(null).username(name)
					.role(UserRoles.BUYER).build();

			userRepository.save(newUser);

		} else {

			// Existing user
			User existing = existingUser.get();

			if (existing.getProvider() == null) {

				existing.setProvider(new ArrayList<>(List.of(LoginProvider.GOOGLE)));

				userRepository.save(existing);

			} else if (!existing.getProvider().contains(LoginProvider.GOOGLE)) {

				existing.getProvider().add(LoginProvider.GOOGLE);

				userRepository.save(existing);
			}
		}
		return user;
	}

}
