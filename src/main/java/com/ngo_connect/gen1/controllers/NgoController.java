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
import com.ngo_connect.gen1.models.ngo.VolunteerResponses;
import com.ngo_connect.gen1.services.AuthService;
import com.ngo_connect.gen1.services.NgoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/v1")
@CrossOrigin(origins = "http://localhost:3000")
public class NgoController {

    @Autowired
    NgoService ngoService;
    @Autowired
    AuthService authService;

    @GetMapping("/get-all-ngos")
    ResponseEntity<List<Ngo>> getAllNgos(){
        List<Ngo> ngoList = ngoService.getAllNgos();
        return new ResponseEntity<>(ngoList, HttpStatus.OK);
    }

    @GetMapping("/get-ngo-by-id")
    ResponseEntity<Ngo> getNgoById(@RequestParam int id){
        Ngo ngo = ngoService.getNgoById(id);
        if(ngo != null)
            return new ResponseEntity<>(ngo, HttpStatus.OK);
        return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
    }

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
    @DeleteMapping("/delete-ngo")
    ResponseEntity<MessageOnlyResponse> deleteNgo(@RequestParam int id) throws Exception {
        boolean deleted = ngoService.deleteNgo(id);
        if(deleted)
            return new ResponseEntity<>(new MessageOnlyResponse("deleted successfully"), HttpStatus.OK);
        return new ResponseEntity<>(new MessageOnlyResponse("failed to delete"), HttpStatus.BAD_REQUEST);
    }


    @PostMapping("/validate-ngo-creds-get-token")
    ResponseEntity<TokenDTO> validateNgoCredsGetToken(@RequestBody CredsDTO creds) throws Exception {
        TokenDTO tokenDTO = null;
        if(ngoService.validateCreds(creds))
            tokenDTO = authService.validateNgoCredsGetTokenService(creds);

        if(tokenDTO!=null)
            return new ResponseEntity<>(tokenDTO, HttpStatus.OK);
        throw new Exception("bad credentials");
    }



    @PostMapping("/create-or-update-volunteer-registration-form")
    ResponseEntity<MessageOnlyResponse> createOrUpdateVolunteerRegistrationForm(@RequestBody String regForm){
        Gson gson = new Gson();
        try{
            JsonObject jsonObject = JsonParser.parseString(regForm).getAsJsonObject();
            String response = ngoService.createOrupdateVolForm(jsonObject);
            System.out.println(jsonObject.toString());
            System.out.println(response);
            if(response != null){
                return new ResponseEntity<>(new MessageOnlyResponse(response), HttpStatus.OK);
            }
        }catch (IllegalStateException | JsonSyntaxException e){
            return new ResponseEntity<>(new MessageOnlyResponse("invalid form json"), HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(new MessageOnlyResponse("invalid request"), HttpStatus.BAD_REQUEST);
    }

    @GetMapping("/get-volunteer-registration-form-of-ngo-byId")
    ResponseEntity<MessageOnlyResponse> getVolunteerRegistrationForm(@RequestParam int id){
        String RegForm = ngoService.getVolRegFormById(id);
        if(RegForm != null){
            return new ResponseEntity<>(new MessageOnlyResponse(RegForm), HttpStatus.OK);
        }
        return new ResponseEntity<>(new MessageOnlyResponse("invalid request"), HttpStatus.BAD_REQUEST);
    }

    //volunteer management code

    @GetMapping("/volunteer-management/get-volunteer-requests")
    ResponseEntity<List<VolunteerResponses>> getVolunteerRequestsById(@RequestParam int ngoId) throws Exception {
        List<VolunteerResponses> volunteerResponses = ngoService.getVolunteerRequestsById(ngoId);
            return new ResponseEntity<>(volunteerResponses, HttpStatus.OK);
    }

    @PostMapping("/volunteer-management/manager")
    ResponseEntity<VolunteerResponses> volunteerResponseManager(@RequestParam int ngoId, @RequestParam int vrId, @RequestParam boolean isApproved) throws Exception {
        System.out.println(ngoId);
        System.out.println(vrId);
        System.out.println(isApproved);

        VolunteerResponses volunteerResponses = ngoService.volunteerResponseManager(ngoId, vrId, isApproved);
        return new ResponseEntity<>(volunteerResponses, HttpStatus.OK);

    }
}
