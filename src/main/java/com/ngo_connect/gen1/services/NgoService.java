package com.ngo_connect.gen1.services;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.ngo_connect.gen1.models.Ngo;
import com.ngo_connect.gen1.models.Volunteer;
import com.ngo_connect.gen1.models.dtos.CredsDTO;
import com.ngo_connect.gen1.models.ngo.VolunteerResponses;
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

    public String createOrupdateVolForm(JsonObject jsonObject) {
        JsonElement jsonElement = jsonObject.get("ngoId");
        Optional<Ngo> optionalNgo = nrepo.findById(jsonElement.getAsInt());
        if(optionalNgo.isPresent()){
            Ngo ngo = optionalNgo.get();
            ngo.setRegFormForVol(jsonObject.toString());
            nrepo.save(ngo);
            return jsonObject.toString();
        }
        return null;
    }

    public String getVolRegFormById(int id) {
        Optional<Ngo> optional = nrepo.findById(id);
        if(optional.isPresent()){
            return optional.get().getRegFormForVol();
        }
        return null;
    }

    public List<VolunteerResponses> getVolunteerRequestsById(int ngoId) throws Exception {
        Optional<Ngo> optional = nrepo.findById(ngoId);
        if(optional.isPresent()){
            List<VolunteerResponses> volunteerResponses = optional.get().getVolunteerResponsesList();
            return volunteerResponses;
        }
        throw new Exception("ngo id is invalid");
    }

    public VolunteerResponses volunteerResponseManager(int ngoId, int vrId, boolean isApproved) throws Exception {
        Optional<Ngo> optional = nrepo.findById(ngoId);
        VolunteerResponses vr= null;
        if(optional.isPresent()){
            List<VolunteerResponses> volunteerResponses = optional.get().getVolunteerResponsesList();
            for(VolunteerResponses vrs: volunteerResponses){
                if(vrs.getId() == vrId){
                    if(isApproved)
                        vrs.setApproved(true);
                    else
                        vrs.setApproved(false);
                    vr = vrs;
                }
            }
        }else{
            throw new Exception("invalid ngo id");
        }
        return vr;
    }
}
