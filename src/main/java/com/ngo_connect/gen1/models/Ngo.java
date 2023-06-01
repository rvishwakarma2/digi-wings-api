package com.ngo_connect.gen1.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Ngo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int id;
    @Column(name = "REGISTRATIONNUMBER")
    String registrationNumber;
    String name;
    String email;
    String mobile;
    String address;
    String password;
    @ManyToMany
    List<Volunteer> volunteerList;
    @Column(name = "REGFORMFORVOL")
    String regFormForVol;   //we will store a json
}
