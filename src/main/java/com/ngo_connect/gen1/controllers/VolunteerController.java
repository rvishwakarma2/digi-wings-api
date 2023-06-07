package com.ngo_connect.gen1.controllers;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.JsonSyntaxException;
import com.ngo_connect.gen1.models.MessageOnlyResponse;
import com.ngo_connect.gen1.models.Ngo;
import com.ngo_connect.gen1.models.Volunteer;
import com.ngo_connect.gen1.models.dtos.CredsDTO;
import com.ngo_connect.gen1.models.dtos.TokenDTO;
import com.ngo_connect.gen1.models.ngo.VolunteerResponseDTO;
import com.ngo_connect.gen1.services.AuthService;
import com.ngo_connect.gen1.services.NgoService;
import com.ngo_connect.gen1.services.VolunteerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/v1")
@CrossOrigin(origins = "http://localhost:3000")
public class VolunteerController {

        @Autowired
        VolunteerService vService;
        @Autowired
        NgoService ngoService;
        @Autowired
        AuthService authService;

        @GetMapping("/get-all-volunteers")
        ResponseEntity<List<Volunteer>> getAllVolunteers(){
            List<Volunteer> volList = vService.getAllVolunteers();
            return new ResponseEntity<>(volList, HttpStatus.OK);
        }

        @PutMapping("/update-volunteer")
        ResponseEntity<String> updateVolunteer(@RequestBody Volunteer volunteer) throws Exception {
            vService.update(volunteer);
            return new ResponseEntity<>("updated successfully", HttpStatus.OK);
        }

        @PostMapping("/register-volunteer")
        ResponseEntity<String> volunteerNgo(@RequestBody Volunteer volunteer){
            vService.createVolunteer(volunteer);
            return new ResponseEntity<>("added successfully", HttpStatus.OK);
        }

        @PostMapping("/validate-volunteer-creds-get-token")
        ResponseEntity<TokenDTO> validateVolunteerCredsGetToken(@RequestBody CredsDTO creds) throws Exception {
            TokenDTO tokenDTO = null;
            if(ngoService.validateCreds(creds))
                tokenDTO = authService.validateVolunteerCredsGetTokenService(creds);
            if(tokenDTO!=null)
                return new ResponseEntity<>(tokenDTO, HttpStatus.OK);
            throw new Exception("invalid credentials");
        }

    @GetMapping("/get-nearby-ngo")
    ResponseEntity<List<Ngo>> getNearByNgo(@RequestParam int vId, @RequestParam double maxDistance) throws Exception {
        Volunteer volunteer = vService.getVolunteerById(vId);
        List<Ngo> ngoList = vService.findNearbyNgos(volunteer, maxDistance);
        return new ResponseEntity<>(ngoList, HttpStatus.OK);
    }

    @GetMapping("/get-nearby-vol")
    ResponseEntity<List<Volunteer>> getNearByVol(@RequestParam int vId, @RequestParam double maxDistance) throws Exception {
        Volunteer volunteer = vService.getVolunteerById(vId);
        List<Volunteer> volList = vService.findNearbyVolunteers(volunteer, maxDistance);
        return new ResponseEntity<>(volList, HttpStatus.OK);
    }

    @PostMapping("/submit-ngo-reg-form")
    ResponseEntity<MessageOnlyResponse> submitNgoRegForm(@RequestBody VolunteerResponseDTO vrd) throws Exception {
        Ngo ngo = ngoService.getNgoById(vrd.getNgoId());
        Volunteer vol = vService.getVolunteerById(vrd.getVolId());
        boolean res = vService.submitNgoRegForm(ngo, vol, vrd.getResponse());

        if (res) {
            return new ResponseEntity<>(new MessageOnlyResponse("submission successful"), HttpStatus.OK);
        }

        return new ResponseEntity<>(new MessageOnlyResponse("submission failed"), HttpStatus.OK);

    }
}


