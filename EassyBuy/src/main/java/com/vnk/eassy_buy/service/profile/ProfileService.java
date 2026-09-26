package com.vnk.eassy_buy.service.profile;

import com.vnk.eassy_buy.dto.profile.ProfileRequest;
import com.vnk.eassy_buy.dto.profile.ProfileResponse;

public interface ProfileService {
	
	String profile(ProfileRequest profileRequest);

	void deleteProfile();

	ProfileResponse getProfile();

}
