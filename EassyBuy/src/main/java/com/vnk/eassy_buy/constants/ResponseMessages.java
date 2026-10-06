package com.vnk.eassy_buy.constants;

public enum ResponseMessages {

	USER_REGISTERED_SUCCESSFULLY("User registered successfully"), USER_ALREADY_EXISTS("User already exists"),
	INVALID_USERNAME_OR_PASSWORD("Invalid username or password"), INVALID_MAIL("Invalid mail"),
	PASSWORD_CHANGED_SUCCESSFULLY("Password changed successfully"),

	ADDRESS_ADDED_SUCCESSFULLY("Address added successfully"), INVALID_USER("Invalid user"),
	ADDRESS_NOT_FOUND("Address not found"),

	PROFILE_SAVED_SUCCESSFULLY("Profile saved successfully"), PROFILE_NOT_FOUND("Profile not found"),

	SELLER_REQUEST_SUBMITTED("Seller request submitted successfully. Waiting for admin approval."),
	SELLER_ALREADY_ACTIVE("You are already an active seller."),
	SELLER_REQUEST_ALREADY_PENDING("Your seller request is already pending."),
	SELLER_NOT_FOUND("Seller profile not found."), SELLER_UPDATED_SUCCESSFULLY("Seller updated successfully."),

	USER_ALREADY_ADMIN("User is already an admin"), ADMIN_CREATED("Admin created successfully"),
	ADMIN_UPDATED("Admin updated successfully"), ADMIN_NOT_FOUND("Admin not found"),
	USER_NOT_ADMIN("User is not an admin");

	private final String message;

	ResponseMessages(String message) {
		this.message = message;
	}

	public String getMessage() {
		return message;
	}
}
