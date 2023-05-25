package com.ngo_connect.gen1.controllers;

import com.ngo_connect.gen1.models.MessageOnlyResponse;
import com.ngo_connect.gen1.models.Ngo;
import com.ngo_connect.gen1.models.Volunteer;
import com.ngo_connect.gen1.models.dtos.CredsDTO;
import com.ngo_connect.gen1.models.dtos.TokenDTO;
import com.ngo_connect.gen1.services.AuthService;
import com.ngo_connect.gen1.services.NgoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/v1")
@CrossOrigin(origins = "http://localhost:3000")
public class NgoController {

    @Autowired
    NgoService ngoService;
    @Autowired
    AuthService authService;

    @PostMapping("/register-ngo")
    ResponseEntity<MessageOnlyResponse> createNgo(@RequestBody Ngo ngo){
        ngoService.create(ngo);
        return new ResponseEntity<>(new MessageOnlyResponse("added successfully"), HttpStatus.OK);
    }

    @PutMapping("/update-ngo")
    ResponseEntity<String> updateNgo(@RequestBody Ngo ngo) throws Exception {
        ngoService.update(ngo);
        return new ResponseEntity<>("updated successfully", HttpStatus.OK);
    }


    @PostMapping("/register-volunteer")
    ResponseEntity<String> volunteerNgo(@RequestBody Volunteer volunteer){
        ngoService.createVolunteer(volunteer);
        return new ResponseEntity<>("added successfully", HttpStatus.OK);
    }

    @PostMapping("/validate-ngo-creds-get-token")
    ResponseEntity<String> validateNgoCredsGetToken(@RequestBody CredsDTO creds){
        TokenDTO tokenDTO = null;
        if(ngoService.validateCreds(creds))
            tokenDTO = authService.validateNgoCredsGetTokenService(creds);

        if(tokenDTO!=null)
            return new ResponseEntity<>(tokenDTO.toString(), HttpStatus.OK);
        return new ResponseEntity<>("bad credentials", HttpStatus.BAD_REQUEST);
    }

    @PostMapping("/validate-volunteer-creds-get-token")
    ResponseEntity<String> validateVolunteerCredsGetToken(@RequestBody CredsDTO creds){
        TokenDTO tokenDTO = null;
        if(ngoService.validateCreds(creds))
            tokenDTO = authService.validateVolunteerCredsGetTokenService(creds);

        if(tokenDTO!=null)
            return new ResponseEntity<>(tokenDTO.toString(), HttpStatus.OK);
        return new ResponseEntity<>("bad credentials", HttpStatus.BAD_REQUEST);
    }

}
