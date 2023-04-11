package com.ngo_connect.gen1.repositories;

import com.ngo_connect.gen1.models.Ngo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface NgoRepository extends CrudRepository<Ngo, Integer> {
}
