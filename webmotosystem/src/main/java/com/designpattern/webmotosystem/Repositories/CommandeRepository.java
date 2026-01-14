package com.designpattern.webmotosystem.Repositories;

import com.designpattern.webmotosystem.Entities.Commande.Commande;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommandeRepository extends JpaRepository<Commande, Long> {
}
