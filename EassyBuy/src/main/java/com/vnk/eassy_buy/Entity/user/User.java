package com.vnk.eassy_buy.Entity.user;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import com.vnk.eassy_buy.Entity.Address.Address;
import com.vnk.eassy_buy.Entity.profile.Profile;
import com.vnk.eassy_buy.Entity.seller.Seller;
import com.vnk.eassy_buy.constants.LoginProvider;
import com.vnk.eassy_buy.constants.UserRoles;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Builder
@Getter
@Setter
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
	
	@ElementCollection(fetch = FetchType.EAGER)
	@Enumerated(EnumType.STRING)
	private Set<UserRoles> role;

	@ElementCollection(fetch = FetchType.EAGER)
	@Enumerated(EnumType.STRING)
	private List<LoginProvider> provider = new ArrayList<>();

	@OneToMany(mappedBy = "user", orphanRemoval = true, cascade = CascadeType.ALL)
	private List<Address> address;

	@OneToOne(mappedBy = "user", orphanRemoval = true, cascade = CascadeType.ALL)
	private Profile profile;
	
	@OneToOne(mappedBy = "user", orphanRemoval = true, cascade = CascadeType.ALL)
	private Seller seller;
	
}
