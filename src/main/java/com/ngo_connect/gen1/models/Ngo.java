package com.ngo_connect.gen1.models;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Ngo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int id;
    String registrationNumber;
    String name;
    String email;
    String mobile;
    String address;
    String password;
}
