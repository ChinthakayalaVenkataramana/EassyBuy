package com.vnk.eassy_buy.dto.Address;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@AllArgsConstructor
@Builder
public class AddressResponse {

	private Integer id;
	
	private String fullName;

	private Long mobile;

	private Long alternateMobile;

	private String houseNo;

	private String street;

	private String area;

	private String city;

	private String state;

	private String country;

	private String pincode;

	private String landmark;

}
