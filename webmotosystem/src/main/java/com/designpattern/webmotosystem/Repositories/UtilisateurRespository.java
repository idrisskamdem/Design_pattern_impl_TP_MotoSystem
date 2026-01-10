package com.designpattern.webmotosystem.Repositories;

import com.designpattern.webmotosystem.Entities.Utilisateur;
import com.designpattern.webmotosystem.Entities.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.List;

@Repository
public interface UtilisateurRespository extends JpaRepository<Utilisateur,Integer> {
    Optional<Utilisateur> findByEmail(String email);

    List<Utilisateur> findByRole(Role role);
}
