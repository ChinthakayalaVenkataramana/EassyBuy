package com.vnk.eassy_buy.service.address;

import java.util.List;

import org.springframework.stereotype.Service;

import com.vnk.eassy_buy.Entity.User;
import com.vnk.eassy_buy.Entity.Address.Address;
import com.vnk.eassy_buy.config.SecurityUtil;
import com.vnk.eassy_buy.constants.ResponseMessages;
import com.vnk.eassy_buy.dto.Address.AddressRequest;
import com.vnk.eassy_buy.dto.Address.AddressResponse;
import com.vnk.eassy_buy.repository.UserRepository;
import com.vnk.eassy_buy.repository.address.AddressRepository;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@AllArgsConstructor
@Slf4j
public class AddressServiceImpl implements AddressService {

	private final AddressRepository addressRepository;
	private final UserRepository userRepository;

	@Override
	public String addAddress(AddressRequest addressRequest) {
		String userName = SecurityUtil.userName();
		log.info("Adding address for user: {}", userName);
		if (userRepository.existsByMail(userName)) {

			addressRepository.save(Address.builder().alternateMobile(addressRequest.getAlternateMobile())
					.area(addressRequest.getArea()).city(addressRequest.getCity()).country(addressRequest.getCountry())
					.defaultAddress(true).fullName(addressRequest.getFullName()).houseNo(addressRequest.getHouseNo())
					.landmark(addressRequest.getLandmark()).mobile(addressRequest.getMobile())
					.pincode(addressRequest.getPincode()).state(addressRequest.getState())
					.street(addressRequest.getStreet()).user(userRepository.findByMail(userName).get()).build());
			log.info("Address added successfully for user: {}", userName);
			return ResponseMessages.ADDRESS_ADDED_SUCCESSFULLY.getMessage();
		}
		log.warn("Invalid user while adding address: {}", userName);
		return ResponseMessages.INVALID_USER.getMessage();
	}

	@Override
	public void defaultAddress(Integer id) {
		Address address = addressRepository.findById(id)
				.orElseThrow(() -> new RuntimeException(ResponseMessages.ADDRESS_NOT_FOUND.getMessage()));
		address.getUser().getId();
		List<Address> addresses = address.getUser().getAddress();

		addresses.stream().forEach(defaultAddress -> defaultAddress.setDefaultAddress(false));

		address.setDefaultAddress(true);
		addressRepository.save(address);

		log.info("Address {} set as default successfully", id);
	}

	@Override
	public void deleteAddress(Integer id) {
		String userName = SecurityUtil.userName();
		log.info("Deleting address");
		User user = userRepository.findByMail(userName).orElseThrow(() -> {
			log.warn("User not found while fetching addresses");
			return new RuntimeException(ResponseMessages.INVALID_USER.getMessage());
		});

		Address address = addressRepository.findById(id)
				.orElseThrow(() -> new RuntimeException(ResponseMessages.ADDRESS_NOT_FOUND.getMessage()));

		if (!user.getAddress().contains(address)) {
			log.warn("Address does not belong to current user");
			throw new RuntimeException(ResponseMessages.INVALID_USER.getMessage());
		}

		addressRepository.delete(address);
		log.info("Address deleted successfully");
	}

	@Override
	public List<AddressResponse> getAllAddress() {
		String userName = SecurityUtil.userName();
		log.info("Fetching all addresses for user: " + userName);
		User user = userRepository.findByMail(userName).orElseThrow(() -> {
			log.warn("User not found while fetching addresses");
			return new RuntimeException(ResponseMessages.INVALID_USER.getMessage());
		});
		log.debug("User found, mapping addresses to response");
		List<AddressResponse> addresses = user.getAddress().stream()
				.map(address -> AddressResponse.builder().id(address.getId())
						.alternateMobile(address.getAlternateMobile()).area(address.getArea()).city(address.getCity())
						.country(address.getCountry()).fullName(address.getFullName()).houseNo(address.getHouseNo())
						.landmark(address.getLandmark()).mobile(address.getMobile()).pincode(address.getPincode())
						.state(address.getState()).street(address.getStreet()).build())
				.toList();
		log.info("Successfully fetched {} addresses", addresses.size());
		return addresses;
	}

	@Override
	public AddressResponse addAddress(Integer id) {
		log.info("Fetching address");
		Address address = addressRepository.findById(id).orElseThrow(() -> {
			log.warn("Address not found");
			return new RuntimeException(ResponseMessages.ADDRESS_NOT_FOUND.getMessage());
		});
		log.debug("Address found, preparing address response");
		return AddressResponse.builder().id(address.getId()).alternateMobile(address.getAlternateMobile())
				.area(address.getArea()).city(address.getCity()).country(address.getCountry())
				.fullName(address.getFullName()).houseNo(address.getHouseNo()).landmark(address.getLandmark())
				.mobile(address.getMobile()).pincode(address.getPincode()).state(address.getState())
				.street(address.getStreet()).build();
	}
//Hello
}
