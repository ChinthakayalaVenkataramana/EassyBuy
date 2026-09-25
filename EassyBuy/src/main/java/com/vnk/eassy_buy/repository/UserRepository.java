package com.vnk.eassy_buy.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.vnk.eassy_buy.Entity.User;

public interface UserRepository extends JpaRepository<User, Integer> {
	
	Optional<User> findByMail(String mail);

    boolean existsByMail(String mail);

}
