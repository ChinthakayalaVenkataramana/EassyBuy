package com.vnk.eassy_buy.controller.address;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.vnk.eassy_buy.dto.Address.AddressRequest;
import com.vnk.eassy_buy.dto.Address.AddressResponse;
import com.vnk.eassy_buy.service.address.AddressService;

import lombok.AllArgsConstructor;

@RestController
@RequestMapping("/addresses")
@AllArgsConstructor
public class AddressController {
	private final AddressService addressService;

	@PostMapping
	public ResponseEntity<String> addAddress(@RequestBody AddressRequest addressRequest) {
		return new ResponseEntity<String>(addressService.addAddress(addressRequest), HttpStatus.CREATED);
	}

	@GetMapping
	public ResponseEntity<List<AddressResponse>> getMethodName(@RequestParam String param) {
		return new ResponseEntity<List<AddressResponse>>(addressService.getAllAddress(), HttpStatus.OK);
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<String> delete(@PathVariable Integer id) {
		return new ResponseEntity<String>("Address deleted successfully", HttpStatus.NO_CONTENT);
	}

	@PutMapping("/{id}/default")
	public ResponseEntity<String> defaultAddress(@RequestParam Integer id) {
		return ResponseEntity.ok("Address set as default successfully");
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<AddressResponse> getAddress(
	        @PathVariable Integer id) {

	    return ResponseEntity.ok(
	            addressService.addAddress(id)
	    );
	}
}
