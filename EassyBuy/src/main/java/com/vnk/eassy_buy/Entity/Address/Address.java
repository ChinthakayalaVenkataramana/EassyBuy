package com.vnk.eassy_buy.Entity.Address;

import com.vnk.eassy_buy.Entity.User;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Address {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
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

    private Boolean defaultAddress;
    
    @ManyToOne
    @JoinColumn(nullable = false, name = "user_id")
    private User user;
}