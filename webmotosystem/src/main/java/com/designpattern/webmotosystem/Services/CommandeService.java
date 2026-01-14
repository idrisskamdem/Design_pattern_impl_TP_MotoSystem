package com.designpattern.webmotosystem.Services;

import com.designpattern.webmotosystem.Entities.Commande.*;
import com.designpattern.webmotosystem.Entities.Utilisateur;
import com.designpattern.webmotosystem.Entities.Vehicule.Vehicule;
import com.designpattern.webmotosystem.Repositories.CommandeRepository;
import com.designpattern.webmotosystem.factory.CommandeFactoryService;
import com.designpattern.webmotosystem.Services.taxe.TaxeStrategyFactory;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@AllArgsConstructor
public class CommandeService {

    private final CommandeRepository commandeRepository;
    private final CommandeFactoryService commandeFactoryService;
    private final TaxeStrategyFactory taxeStrategyFactory;
    private final UtilisateurService utilisateurService;
    private final VehiculeService vehiculeService;

    public Commande creerCommande(EnumCommande type,
                                  int clientId,
                                  Long vehiculeId,
                                  String paysLivraison) {

        Utilisateur client = utilisateurService.getUserById(clientId);
        Vehicule vehicule = vehiculeService.findById(vehiculeId)
                .orElseThrow(() -> new RuntimeException("Vehicule introuvable"));

        double montantBase = vehicule.getPrixBase();

        Commande commande = commandeFactoryService.createCommande(type, montantBase, paysLivraison);
        commande.setClient(client);
        commande.setVehicule(vehicule);

        double taxe = taxeStrategyFactory.getStrategy(paysLivraison).calculerTaxe(montantBase);
        double total = commande.calculerTotal(taxe);

        commande.setMontant(total);

        return commandeRepository.save(commande);
    }


    public Optional<Commande> getCommandeById(Long id) {
        return commandeRepository.findById(id);
    }

    public void changerEtat(Long id, EtatCommande nouvelEtat) {
        Commande commande = commandeRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Commande introuvable"));
        commande.changerEtatCommande(nouvelEtat);
        commandeRepository.save(commande);
    }
}
