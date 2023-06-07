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
public class VolunteerResponses {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int id;

    @ManyToOne
            @JoinColumn(name = "ngo_id")
    Ngo ngo;

    @ManyToOne
            @JoinColumn(name = "volunteer_id")
    Volunteer volunteer;

    String response;

    boolean isApproved;

    boolean isRejected;

    boolean isActive;
}
