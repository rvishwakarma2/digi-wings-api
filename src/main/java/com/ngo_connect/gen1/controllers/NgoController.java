package com.ngo_connect.gen1.controllers;

import com.ngo_connect.gen1.models.Ngo;
import com.ngo_connect.gen1.models.Volunteer;
import com.ngo_connect.gen1.services.NgoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/v1")
public class NgoController {

    @Autowired
    NgoService ngoService;

    @PostMapping("/register-ngo")
    ResponseEntity<String> createNgo(@RequestBody Ngo ngo){
        ngoService.create(ngo);
        return new ResponseEntity<>("added successfully", HttpStatus.OK);
    }


    @PostMapping("/register-volunteer")
    ResponseEntity<String> volunteerNgo(@RequestBody Volunteer volunteer){
        ngoService.createVolunteer(volunteer);
        return new ResponseEntity<>("added successfully", HttpStatus.OK);
    }


}
