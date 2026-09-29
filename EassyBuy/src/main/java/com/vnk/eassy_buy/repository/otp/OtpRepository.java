package com.vnk.eassy_buy.repository.otp;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.vnk.eassy_buy.Entity.otp.Otp;

public interface OtpRepository extends JpaRepository<Otp, Integer> {
	Optional<Otp> findByMailAndOtp(String mail, String otp);
	boolean existsByMail(String mail);
	Optional<Otp> findByMail(String mail);
}
