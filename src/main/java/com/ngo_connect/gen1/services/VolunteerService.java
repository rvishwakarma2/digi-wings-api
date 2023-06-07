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
public class VolunteerService {

    @Autowired
    VolunteerRepository vrepo;


    @Autowired
    NgoService ngoService;

    public void createVolunteer(Volunteer volunteer) {
        vrepo.save(volunteer);
    }

    public void update(Volunteer v) throws Exception {
        Optional<Volunteer> v1 = vrepo.findById(v.getId());
        if (v1.isPresent()) {
            v1.get();
        } else {
            throw new Exception("not found");
        }
    }

    public Volunteer getVolunteerById(int id) throws Exception {
        Optional<Volunteer> v1 = vrepo.findById(id);
        if (v1.isPresent()) {
            return v1.get();
        }
        throw new Exception("volunteer id not found");
    }


    public List<Volunteer> getAllVolunteers() {
        Iterable<Volunteer> vols = vrepo.findAll();
        List<Volunteer> volunteerList = new ArrayList<>();
        vols.forEach(v -> volunteerList.add(v));
        return volunteerList;
    }



     public List<Ngo> findNearbyNgos(Volunteer vol, double maxDistanceInKms) {

        List<Ngo> allNgo = ngoService.getAllNgos();
        List<Ngo> nearbyNgo = new ArrayList<>();
        double maxDistance = maxDistanceInKms;

        double volLatitude = vol.getLocation().getLatitude();
        double volLongitude = vol.getLocation().getLongitude();

// Iterate over all users to find nearby users
        for (Ngo ngo : allNgo) {


// Calculate the distance between the authenticated user and the current user
            double distance = calculateDistance(
                    volLatitude, volLongitude,
                    ngo.getLocation().getLatitude(), ngo.getLocation().getLongitude());

// Check if the distance is within the maximum distance
            if (distance <= maxDistance) {
                nearbyNgo.add(ngo);
            }
        }

        return nearbyNgo;
    }

     public List<Volunteer> findNearbyVolunteers(Volunteer vol, double maxDistanceInKms) {

        List<Volunteer> allVolunteers = this.getAllVolunteers();
        List<Volunteer> nearbyVols = new ArrayList<>();
        double maxDistance = maxDistanceInKms;

        double volLatitude = vol.getLocation().getLatitude();
        double volLongitude = vol.getLocation().getLongitude();

// Iterate over all users to find nearby users
        for (Volunteer vol2 : allVolunteers) {


// Calculate the distance between the authenticated user and the current user
            double distance = calculateDistance(
                    volLatitude, volLongitude,
                    vol2.getLocation().getLatitude(), vol2.getLocation().getLongitude());

// Check if the distance is within the maximum distance
            if (distance <= maxDistance) {
                nearbyVols.add(vol2);
            }
        }

        return nearbyVols;
    }

     double calculateDistance(double lat1, double lon1, double lat2, double lon2) {
// Radius of the Earth in kilometers
        double earthRadius = 6371;

        double dLat = Math.toRadians(lat2 - lat1);
        double dLon = Math.toRadians(lon2 - lon1);

        double a = Math.sin(dLat / 2) * Math.sin(dLat / 2)
                + Math.cos(Math.toRadians(lat1))
                * Math.cos(Math.toRadians(lat2))
                * Math.sin(dLon / 2) * Math.sin(dLon / 2);

        double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));

// Calculate the distance in kilometers
        double distance = earthRadius * c;

        return distance;
    }

    public boolean submitNgoRegForm(Ngo ngo, Volunteer vol, String response) {

        return false;

    }
}
