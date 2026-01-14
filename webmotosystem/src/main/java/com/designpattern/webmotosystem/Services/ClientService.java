package com.designpattern.webmotosystem.Services;

import com.designpattern.webmotosystem.Entities.Utilisateur;
import com.designpattern.webmotosystem.Entities.Role;
import com.designpattern.webmotosystem.Entities.client.ClientEntreprise;
import com.designpattern.webmotosystem.Entities.client.Filiale;
import com.designpattern.webmotosystem.Entities.client.Societe;
import com.designpattern.webmotosystem.Repositories.UtilisateurRespository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Service pour gerer les societes et leurs filiales.
 * Utilise le pattern Composite (Societe = Composite, Filiale = Leaf).
 */
@Service
@AllArgsConstructor
public class ClientService {

    private final UtilisateurRespository utilisateurRespository;

    /**
     * Retourne une societe avec ses filiales sous forme de Composite.
     */
    public ClientEntreprise construireSocieteAvecFiliales(int societeId) {
        Utilisateur societe = utilisateurRespository.findById(societeId)
                .orElseThrow(() -> new RuntimeException("Societe introuvable"));

        if (societe.getRole() != Role.SOCIETE) {
            throw new RuntimeException("Cet utilisateur n'est pas une societe");
        }

        Societe composite = new Societe(societe);

        // On recupere toutes les filiales rattachees a cette societe
        List<Utilisateur> filiales = utilisateurRespository.findByRole(Role.CLIENT);

        for (Utilisateur f : filiales) {
             composite.ajouter(new Filiale(f));
        }

        return composite;
    }
}
