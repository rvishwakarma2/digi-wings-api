package com.ngo_connect.gen1.models.dtos;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class NgoDTO {
    //int id;
    String registrationNumber;
    String name;
    String email;
    String mobile;
    String address;
    //String password;
}
