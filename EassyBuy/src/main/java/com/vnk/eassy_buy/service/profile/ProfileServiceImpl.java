package com.vnk.eassy_buy.service.profile;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.vnk.eassy_buy.Entity.User;
import com.vnk.eassy_buy.Entity.profile.Profile;
import com.vnk.eassy_buy.config.SecurityUtil;
import com.vnk.eassy_buy.constants.ResponseMessages;
import com.vnk.eassy_buy.dto.profile.ProfileRequest;
import com.vnk.eassy_buy.dto.profile.ProfileResponse;
import com.vnk.eassy_buy.repository.UserRepository;
import com.vnk.eassy_buy.repository.profile.ProfileRepository;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@AllArgsConstructor
@Slf4j
public class ProfileServiceImpl implements ProfileService {
	private final ProfileRepository profileRepository;
	private final UserRepository userRepository;

	@Override
	public String profile(ProfileRequest profileRequest) {
		String userName = SecurityUtil.userName();
		log.info("Adding profile for user: {}", userName);
		try {
			User user = userRepository.findByMail(userName)
					.orElseThrow(() -> new RuntimeException(ResponseMessages.INVALID_USER.getMessage()));

			log.info("Profile details - DateOfBirth: {}, Gender: {}", profileRequest.getDateOfBirth(),
					profileRequest.getGender());
			Profile profile = user.getProfile();
			if (profile == null) {
				log.info("Profile not found. Creating profile for user: {}", userName);
				profile = Profile.builder().user(user).dateOfBirth(profileRequest.getDateOfBirth())
						.gender(profileRequest.getGender()).profileImage(profileRequest.getProfileImage()).build();
			} else {
				log.info("Profile already exists. Updating profile for user: {}", userName);
				profile = Profile.builder().user(user).dateOfBirth(profileRequest.getDateOfBirth())
						.gender(profileRequest.getGender()).profileImage(profileRequest.getProfileImage())
						.id(profile.getId()).build();
			}
			profileRepository.save(profile);
			log.info("Profile created successfully for user id: {}", user.getId());
			return ResponseMessages.PROFILE_SAVED_SUCCESSFULLY.getMessage();

		} catch (Exception exception) {
			log.error("Failed to add profile for user: {}", SecurityUtil.userName(), exception);

			throw exception;
		}
	}

	@Override
	@Transactional
	public void deleteProfile() {
		String userName = SecurityUtil.userName();
		User user = userRepository.findByMail(userName).orElseThrow(() -> {
			log.error("Invalid user: {}", userName);
			return new RuntimeException(ResponseMessages.INVALID_USER.getMessage());
		});

		if (user.getProfile() == null) {
			log.error("Profile not found for user: {}", userName);
			throw new RuntimeException(ResponseMessages.PROFILE_NOT_FOUND.getMessage());
		}

		Profile profile = user.getProfile();
		log.info("Deleting profile for user: {}, profileId: {}", userName, profile.getId());
		profileRepository.delete(profile);
		user.setProfile(null);	
		log.info("Profile deleted successfully for user: {}", userName);
	}

	@Override
	public ProfileResponse getProfile() {
		String userName = SecurityUtil.userName();
		log.info("Fetching profile for user: {}", userName);
		User user = userRepository.findByMail(userName).orElseThrow(() -> {
			log.error("Invalid user: {}", userName);
			return new RuntimeException(ResponseMessages.INVALID_USER.getMessage());
		});

		Profile profile = user.getProfile();
		ProfileResponse profileResponse = new ProfileResponse(user.getMobile(), user.getMail(), user.getUsername(),
				profile != null ? profile.getProfileImage() : null, profile != null ? profile.getDateOfBirth() : null,
				profile != null ? profile.getGender() : null);
		log.info("Profile fetched successfully for user: {}", userName);
		return profileResponse;
	}

}
