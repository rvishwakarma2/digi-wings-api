package com.ngo_connect.gen1.repositories;

import com.ngo_connect.gen1.models.Ngo;
import com.ngo_connect.gen1.models.Volunteer;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VolunteerRepository extends CrudRepository<Volunteer, Integer> {
    public Volunteer findByEmail(String email);
}
