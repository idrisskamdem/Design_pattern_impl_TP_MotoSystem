package com.designpattern.webmotosystem.Repositories;

import com.designpattern.webmotosystem.Entities.Vehicule.Vehicule;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VehiculeRepository extends JpaRepository<Vehicule, Long> {
}