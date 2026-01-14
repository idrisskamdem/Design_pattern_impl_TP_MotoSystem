package com.designpattern.webmotosystem.Services;

import com.designpattern.webmotosystem.Entities.Utilisateur;
import com.designpattern.webmotosystem.Entities.Validation;
import com.designpattern.webmotosystem.Entities.Role;
import com.designpattern.webmotosystem.Repositories.UtilisateurRespository;
import lombok.AllArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.List;
import java.util.Map;

@AllArgsConstructor
@Service
public class UtilisateurService {
    private final UtilisateurRespository utilisateurRespository;
    private final ValidationService validationService;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    public void inscription(Utilisateur utilisateur){
        utilisateur.setPassword(bCryptPasswordEncoder.encode(utilisateur.getPassword()));

        if(!utilisateur.getEmail().contains("@") || !utilisateur.getEmail().contains(".")){
            throw new RuntimeException("Adresse mail invalide");
        }

        if(utilisateurRespository.findByEmail(utilisateur.getEmail()).isPresent()){
            throw new RuntimeException("Cette adresse mail est déjà utilisée");
        }

        utilisateur = utilisateurRespository.save(utilisateur);
        validationService.enregister(utilisateur);
    }

    public List<Utilisateur> getAllUsers(){
        return utilisateurRespository.findAll();
    }

    public Utilisateur getUserById(int id){
        return utilisateurRespository.findById(id)
                .orElseThrow(() -> new RuntimeException("Utilisateur introuvable"));
    }

    public Utilisateur getUserByEmail(String email){
        return utilisateurRespository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("Utilisateur introuvable"));
    }

    public List<Utilisateur> getUsersByRole(Role role){
        return utilisateurRespository.findByRole(role);
    }

    public void activation(Map<String,String> activation){
        Validation validation = validationService.lireEnFonctionDuCode(activation.get("code"));

        if(Instant.now().isAfter(validation.getExpiration())){
            throw new RuntimeException("Votre code a expiré");
        }

        Utilisateur utilisateurActive = utilisateurRespository.findById(validation.getUtilisateur().getId())
                .orElseThrow(() -> new RuntimeException("Utilisateur inconnu"));

        utilisateurActive.setActif(true);
        utilisateurRespository.save(utilisateurActive);

        validation.setActivation(Instant.now());
        validationService.updateValidation(validation);
    }

    // Nouvelle methode pour redemander un code
    public void resendActivation(String email){
        Utilisateur utilisateur = utilisateurRespository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("Utilisateur introuvable"));

        if(utilisateur.isActif()){
            throw new RuntimeException("Utilisateur déjà activé");
        }


        validationService.enregister(utilisateur); // genere et envoie un nouveau code
    }
}
