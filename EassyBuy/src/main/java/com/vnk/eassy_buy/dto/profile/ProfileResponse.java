package com.vnk.eassy_buy.dto.profile;

import java.time.LocalDate;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ProfileResponse {
	
	private Long mobileNo;

	private String mail;

	private String username;

	private String profileImage;

	private LocalDate dateOfBirth;

	private String gender;
}
