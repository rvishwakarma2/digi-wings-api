package com.ngo_connect.gen1.models.dtos;

import com.ngo_connect.gen1.models.Volunteer;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TokenDTO {
    String token;
    NgoDTO ngoDTO;
    VolunteerDTO volunteerDTO;
}
