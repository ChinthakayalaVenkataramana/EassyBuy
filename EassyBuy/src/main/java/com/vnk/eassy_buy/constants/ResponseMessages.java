package com.vnk.eassy_buy.constants;

public enum ResponseMessages {
	
    USER_REGISTERED_SUCCESSFULLY("User registered successfully"),
    USER_ALREADY_EXISTS("User already exists"),
    INVALID_USERNAME_OR_PASSWORD("Invalid username or password"),
    INVALID_MAIL("Invalid mail"),
    PASSWORD_CHANGED_SUCCESSFULLY("Password changed successfully"),
    
	ADDRESS_ADDED_SUCCESSFULLY("Address added successfully"),
	INVALID_USER("Invalid user"),
	ADDRESS_NOT_FOUND("Address not found");

    private final String message;

    ResponseMessages(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}
