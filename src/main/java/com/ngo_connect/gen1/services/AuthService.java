package com.ngo_connect.gen1.services;

import com.ngo_connect.gen1.models.Ngo;
import com.ngo_connect.gen1.models.Volunteer;
import com.ngo_connect.gen1.models.dtos.CredsDTO;
import com.ngo_connect.gen1.models.dtos.NgoDTO;
import com.ngo_connect.gen1.models.dtos.TokenDTO;
import com.ngo_connect.gen1.models.dtos.VolunteerDTO;
import com.ngo_connect.gen1.repositories.NgoRepository;
import com.ngo_connect.gen1.repositories.VolunteerRepository;
import com.ngo_connect.gen1.security.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

@Service
public class AuthService {
    @Autowired
    NgoRepository nrepo;

    @Autowired
    VolunteerRepository vrepo;

    @Autowired
    JwtUtil jwtUtil;

    public TokenDTO validateNgoCredsGetTokenService(CredsDTO creds) throws Exception {
        TokenDTO tokenDTO = new TokenDTO();
        Ngo ngo = null;
        String token = null;
        ngo = nrepo.findByEmail(creds.getEmail());
        if (ngo != null) {
            if (ngo.getPassword().equals(creds.getPassword())) {
                User ngoUser = new User(ngo.getEmail(), ngo.getPassword(), new ArrayList<>());
                token = jwtUtil.generateToken(ngoUser);
                tokenDTO.setNgo(new NgoDTO(ngo.getRegistrationNumber(), ngo.getName(), ngo.getEmail(), ngo.getMobile(), ngo.getAddress(), ngo.getLocation()));
                tokenDTO.setToken(token);
                System.out.println(tokenDTO);
                return tokenDTO;
            }
            throw new Exception("invalid password");
        }
        throw new Exception("invalid email");
    }

    public TokenDTO validateVolunteerCredsGetTokenService(CredsDTO creds) throws Exception {
        TokenDTO tokenDTO = new TokenDTO();
        Volunteer volunteer = null;
        String token = null;
        volunteer = vrepo.findByEmail(creds.getEmail());
        if (volunteer != null) {
            if (volunteer.getPassword().equals(creds.getPassword())) {
                User volunteerUser = new User(volunteer.getEmail(), volunteer.getPassword(), new ArrayList<>());
                token = jwtUtil.generateToken(volunteerUser);
                tokenDTO.setVolunteer(new VolunteerDTO(volunteer.getFirstName(),volunteer.getLastName(), volunteer.getEmail(), volunteer.getMobile(), volunteer.getAddress(), volunteer.getLocation(), new HashSet<>(), new ArrayList<>()));
                tokenDTO.setToken(token);
                return tokenDTO;
            }
            throw new Exception("volunteer password invalid");
        }
        throw new Exception("volunteer email not found");
    }
}