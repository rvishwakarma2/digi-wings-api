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
        ResponseEntity<String> validateVolunteerCredsGetToken(@RequestBody CredsDTO creds){
            TokenDTO tokenDTO = null;
            if(ngoService.validateCreds(creds))
                tokenDTO = authService.validateVolunteerCredsGetTokenService(creds);
            System.out.println(tokenDTO.toString());
            if(tokenDTO!=null)
                return new ResponseEntity<>(tokenDTO.toString(), HttpStatus.OK);
            return new ResponseEntity<>("bad credentials", HttpStatus.BAD_REQUEST);
        }

}


