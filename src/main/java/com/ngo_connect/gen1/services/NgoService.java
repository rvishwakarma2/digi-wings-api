package com.ngo_connect.gen1.services;

import com.ngo_connect.gen1.models.Ngo;
import com.ngo_connect.gen1.models.Volunteer;
import com.ngo_connect.gen1.repositories.NgoRepository;
import com.ngo_connect.gen1.repositories.VolunteerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class NgoService {
    @Autowired
    NgoRepository nrepo;
    VolunteerRepository vrepo;
    public void create(Ngo ngo) {
        nrepo.save(ngo);
    }

    public void createVolunteer(Volunteer volunteer) {
        vrepo.save(volunteer);
    }
}
