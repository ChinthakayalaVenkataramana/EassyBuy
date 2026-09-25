package com.vnk.eassy_buy.config;

import org.jspecify.annotations.Nullable;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.jwt.Jwt;

import lombok.NoArgsConstructor;

@NoArgsConstructor
public class SecurityUtil {

	private static Jwt getJwt() {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

		return (Jwt) authentication.getPrincipal();
	}

	public static String userName() {
		return getJwt().getSubject();
	}

	public static String getRole() {
		return getJwt().getClaimAsString("role");
	}

}
