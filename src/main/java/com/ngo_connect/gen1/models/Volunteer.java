package com.ngo_connect.gen1.models;

import com.ngo_connect.gen1.models.ngo.VolunteerResponses;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.Set;

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

    @Embedded
    Location location;

    @ManyToMany(mappedBy = "volunteerList")
    Set<Ngo> ngoList;

    @OneToMany(mappedBy = "volunteer",  cascade = CascadeType.ALL, orphanRemoval = true)
    List<VolunteerResponses> responsesList;
}
