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
public class Volunteer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int id;
    String firstName;
    String lastName;
    String email;
    String mobile;
    String address;
    String password;
    @ManyToMany
    List<Ngo> ngoList;
}
