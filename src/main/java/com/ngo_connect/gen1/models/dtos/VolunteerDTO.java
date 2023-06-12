package com.ngo_connect.gen1.models.dtos;

import com.ngo_connect.gen1.models.Location;
import com.ngo_connect.gen1.models.Ngo;
import com.ngo_connect.gen1.models.ngo.VolunteerResponses;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.Set;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class VolunteerDTO {
    //int id;
    String firstName;
    String lastName;
    String email;
    String mobile;
    String address;
    //String password;
    Location location;
    Set<Ngo> ngoList;
    List<VolunteerResponses> responsesList;
}
