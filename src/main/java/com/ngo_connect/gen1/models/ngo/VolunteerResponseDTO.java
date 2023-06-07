package com.ngo_connect.gen1.models.ngo;

import com.ngo_connect.gen1.models.Ngo;
import com.ngo_connect.gen1.models.Volunteer;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class VolunteerResponseDTO {
    int volId;
    int ngoId;
    String response;
}
