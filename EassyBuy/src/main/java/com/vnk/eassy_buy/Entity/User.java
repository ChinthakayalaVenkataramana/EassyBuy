package com.vnk.eassy_buy.Entity;

import java.util.List;

import com.vnk.eassy_buy.Entity.Address.Address;
import com.vnk.eassy_buy.constants.UserRoles;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Builder
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class User {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	@Column(nullable = false, unique = true)
	private String mail;

	private Long mobile;

	private String username;

	private String password;

	@Enumerated(EnumType.STRING)
	private UserRoles role;
	
	@OneToMany(mappedBy = "user",orphanRemoval = true,cascade = CascadeType.ALL)
	private List<Address> address;
}
