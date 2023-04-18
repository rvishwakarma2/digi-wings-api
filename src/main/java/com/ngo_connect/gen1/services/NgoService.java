package com.ngo_connect.gen1.services;

import com.ngo_connect.gen1.models.Ngo;
import com.ngo_connect.gen1.models.Volunteer;
import com.ngo_connect.gen1.models.dtos.CredsDTO;
import com.ngo_connect.gen1.repositories.NgoRepository;
import com.ngo_connect.gen1.repositories.VolunteerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class NgoService {
    @Autowired
    NgoRepository nrepo;
    @Autowired
    VolunteerRepository vrepo;
    public void create(Ngo ngo) {
        nrepo.save(ngo);
    }

    public void createVolunteer(Volunteer volunteer) {
        vrepo.save(volunteer);
    }

    public void update(Ngo ngo) throws Exception {
        Optional<Ngo> ngo1 = nrepo.findById(ngo.getId());
        if(ngo1.isPresent()){
            ngo1.get();
        }else{
            throw new Exception("not found");
        }
    }

    public boolean validateCreds(CredsDTO creds) {
        String email = creds.getEmail();
        String pwd = creds.getPassword();
        if(email != null && email.matches("^(.+)@(.+)$")){
            if(pwd != null && !pwd.isBlank())
                return true;
        }
        return false;
    }
}
