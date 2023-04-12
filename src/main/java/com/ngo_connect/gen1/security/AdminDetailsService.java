//package com.ngo_connect.gen1.security;
//
//package com.cognizant.authorizationService.service;
//
//import java.util.ArrayList;
//
//import com.ngo_connect.gen1.repositories.NgoRepository;
//import com.ngo_connect.gen1.repositories.VolunteerRepository;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.core.userdetails.User;
//import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.security.core.userdetails.UserDetailsService;
//import org.springframework.security.core.userdetails.UsernameNotFoundException;
//import org.springframework.stereotype.Service;
//
//import com.cognizant.authorizationService.model.UserData;
//import com.cognizant.authorizationService.repository.UserRepository;
//
//
//@Service
//public class AdminDetailsService implements UserDetailsService {
//    private static final Logger LOGGER = LoggerFactory.getLogger(AdminDetailsService.class);
//
//    @Autowired
//    private VolunteerRepository vrepo;
//
//    @Autowired
//    private NgoRepository nrepo;
//    /**
//     * Get UserData using JpaRepository, to get data by username,
//     * throw exceptions if user not found
//     */
//    @Override
//    public UserDetails loadUserByUsername(String uid) {
//
//
//    }
//
//    public UserData getUserDataById(String id) {
//        LOGGER.info("START-END-getUserDataById");
//        return userRepository.findById(id).get();
//    }
//
//}
//
