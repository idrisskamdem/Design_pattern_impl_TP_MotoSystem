package com.designpattern.webmotosystem.Controller;

import com.designpattern.webmotosystem.Entities.Role;
import com.designpattern.webmotosystem.Entities.Utilisateur;
import com.designpattern.webmotosystem.Services.UtilisateurService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@Slf4j
@RestController
@AllArgsConstructor
@RequestMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
public class UtilisateurController {

    private final UtilisateurService utilisateurService;

    @PostMapping(path = "inscription")
    public void inscription(@RequestBody Utilisateur utilisateur){
        log.info("Inscription utilisateur {}", utilisateur.getEmail());
        utilisateurService.inscription(utilisateur);
    }

    @PostMapping(path = "activation")
    public void activation(@RequestBody Map<String,String> activation){
        utilisateurService.activation(activation);
    }

    // Chercher par ID
    @GetMapping("/{id}")
    public Utilisateur getUserById(@PathVariable int id){
        return utilisateurService.getUserById(id);
    }

    // Chercher par email
    @GetMapping("/email/{email}")
    public Utilisateur getUserByEmail(@PathVariable String email){
        return utilisateurService.getUserByEmail(email);
    }

    // Afficher tous les utilisateurs
    @GetMapping
    public List<Utilisateur> getAllUsers(){
        return utilisateurService.getAllUsers();
    }

    // Afficher les utilisateurs par rôle
    @GetMapping("/searchByRole/{role}")
    public List<Utilisateur> getUsersByRole(@PathVariable Role role){
        return utilisateurService.getUsersByRole(role);
    }
}
