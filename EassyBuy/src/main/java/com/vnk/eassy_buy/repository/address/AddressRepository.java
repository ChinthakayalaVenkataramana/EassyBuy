package com.vnk.eassy_buy.repository.address;

import org.springframework.data.jpa.repository.JpaRepository;

import com.vnk.eassy_buy.Entity.Address.Address;

public interface AddressRepository extends JpaRepository<Address, Integer> {

}
