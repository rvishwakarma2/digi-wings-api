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

    public List<Ngo> getAllNgos() {
        Iterable<Ngo> Ngos = nrepo.findAll();
        List<Ngo> ngoList = new ArrayList<>();
        Ngos.forEach(v -> ngoList.add(v));
        return ngoList;
    }

    public boolean deleteNgo(int id) {
        Optional<Ngo> optionalNgo = nrepo.findById(id);
        if (optionalNgo.isPresent()) {
            nrepo.delete(optionalNgo.get());
            return true;
        }
        return false;
    }

    public Ngo getNgoById(int id) {
        Optional<Ngo> ngoOptional = nrepo.findById(id);
        if(ngoOptional.isPresent()) return ngoOptional.get();
        return null;
    }
}
