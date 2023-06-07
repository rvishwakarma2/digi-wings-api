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

    @Embedded
    Location location;

    @ManyToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinTable(name = "ngo_volunteer",
            joinColumns = @JoinColumn(name = "ngo_id"),
            inverseJoinColumns = @JoinColumn(name = "volunteer_id"))
    Set<Volunteer> volunteerList;

    @Column(name = "REGFORMFORVOL")
    String regFormForVol;   //we will store a json

    @OneToMany(mappedBy = "ngo", cascade = CascadeType.ALL, orphanRemoval = true)
    List<VolunteerResponses> volunteerResponsesList;
}
