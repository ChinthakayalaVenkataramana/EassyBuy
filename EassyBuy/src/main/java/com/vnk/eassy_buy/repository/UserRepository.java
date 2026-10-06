package com.vnk.eassy_buy.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.vnk.eassy_buy.Entity.user.User;
import com.vnk.eassy_buy.constants.UserRoles;


public interface UserRepository extends JpaRepository<User, Integer> {

	Optional<User> findByMail(String mail);

    boolean existsByMail(String mail);
    
    List<User> findByRole(UserRoles userRoles);

}
