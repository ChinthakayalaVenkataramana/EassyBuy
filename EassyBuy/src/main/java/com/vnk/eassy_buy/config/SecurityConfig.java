package com.vnk.eassy_buy.config;

import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.jose.jws.MacAlgorithm;
import org.springframework.security.oauth2.jwt.JwtDecoder;
import org.springframework.security.oauth2.jwt.JwtEncoder;
import org.springframework.security.oauth2.jwt.NimbusJwtDecoder;
import org.springframework.security.oauth2.jwt.NimbusJwtEncoder;
import org.springframework.security.web.SecurityFilterChain;

import com.nimbusds.jose.jwk.source.ImmutableSecret;


@Configuration
@EnableWebSecurity
public class SecurityConfig {

	@Value("${jwt.secret}")
    private String secret;
	
	@Value("${security.public-endpoints}")
	private String[] endpoints;

    @Bean
    public SecretKey secretKey() {

        return new SecretKeySpec(
                secret.getBytes(),
                "HmacSHA256"
        );
    }

    @Bean
    public JwtEncoder jwtEncoder() {

        return new NimbusJwtEncoder(
                new ImmutableSecret<>(secretKey())
        );
    }

    @Bean
    public JwtDecoder jwtDecoder() {

        return NimbusJwtDecoder
                .withSecretKey(secretKey())
                .macAlgorithm(MacAlgorithm.HS256)
                .build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain securityFilterChain(
            HttpSecurity http
    ) throws Exception {

        http

            // REST API - no CSRF required
            .csrf(csrf -> csrf.disable())

            // Don't create HTTP sessions
            .sessionManagement(session ->
                    session.sessionCreationPolicy(
                            SessionCreationPolicy.STATELESS
                    )
            )

            // Authorization
            .authorizeHttpRequests(auth -> auth

                    // Only token-generation endpoint is public
                    .requestMatchers(endpoints).permitAll()

                    // Everything else requires JWT
                    .anyRequest().authenticated()
            )

            // JWT Bearer authentication
            .oauth2ResourceServer(oauth2 ->
                    oauth2.jwt(jwt -> {})
            )

            // Disable browser login
            .formLogin(form -> form.disable())

            // Disable HTTP Basic
            .httpBasic(basic -> basic.disable());

        return http.build();
       }
}

