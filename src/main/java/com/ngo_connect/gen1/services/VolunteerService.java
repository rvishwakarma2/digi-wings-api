package com.ngo_connect.gen1.services;

import com.ngo_connect.gen1.models.Ngo;
import com.ngo_connect.gen1.models.Volunteer;
import com.ngo_connect.gen1.models.dtos.CredsDTO;
import com.ngo_connect.gen1.repositories.NgoRepository;
import com.ngo_connect.gen1.repositories.VolunteerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class VolunteerService {

    @Autowired
    VolunteerRepository vrepo;


    public void createVolunteer(Volunteer volunteer) {
        vrepo.save(volunteer);
    }

    public void update(Volunteer v) throws Exception {
        Optional<Volunteer> v1 = vrepo.findById(v.getId());
        if(v1.isPresent()){
            v1.get();
        }else{
            throw new Exception("not found");
        }
    }


    public List<Volunteer> getAllVolunteers() {
        Iterable<Volunteer> vols = vrepo.findAll();
        List<Volunteer> volunteerList = new ArrayList<>();
        vols.forEach(v -> volunteerList.add(v));
        return volunteerList;
    }
}
