package com.vnk.eassy_buy.controller.profile;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.vnk.eassy_buy.dto.profile.ProfileRequest;
import com.vnk.eassy_buy.dto.profile.ProfileResponse;
import com.vnk.eassy_buy.service.profile.ProfileService;

import lombok.AllArgsConstructor;

@RestController
@RequestMapping("/profile")
@AllArgsConstructor
public class ProfileController {
	private final ProfileService profileService;

	@PostMapping("/create-profile")
	public ResponseEntity<String> createProfile( @RequestBody ProfileRequest profileRequest) {

		return ResponseEntity.status(HttpStatus.CREATED).body(profileService.profile(profileRequest));
	}

	@GetMapping
	public ResponseEntity<ProfileResponse> getProfile() {

		return ResponseEntity.ok(profileService.getProfile());
	}

	@PutMapping("/update-profile")
	public ResponseEntity<String> updateProfile(@RequestBody ProfileRequest profileRequest) {

		return ResponseEntity.ok(profileService.profile(profileRequest));
	}

	@DeleteMapping("/delete-profile")
	public ResponseEntity<Void> deleteProfile() {

		profileService.deleteProfile();

		return ResponseEntity.noContent().build();
	}
}
