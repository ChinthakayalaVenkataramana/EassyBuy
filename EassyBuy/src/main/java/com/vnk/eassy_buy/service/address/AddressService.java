package com.vnk.eassy_buy.service.address;

import java.util.List;

import com.vnk.eassy_buy.dto.Address.AddressRequest;
import com.vnk.eassy_buy.dto.Address.AddressResponse;

public interface AddressService {

	String addAddress(AddressRequest addressRequest);

	void defaultAddress(Integer id);

	void deleteAddress(Integer id);

	List<AddressResponse> getAllAddress();


	AddressResponse addAddress(Integer id);
}
