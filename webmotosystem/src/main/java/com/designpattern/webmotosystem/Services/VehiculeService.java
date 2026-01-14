package com.designpattern.webmotosystem.Services;

import com.designpattern.webmotosystem.Entities.Vehicule.*;
import com.designpattern.webmotosystem.Repositories.VehiculeRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class VehiculeService {

    private final VehiculeRepository vehiculeRepository;

    public VehiculeService(VehiculeRepository vehiculeRepository) {
        this.vehiculeRepository = vehiculeRepository;
    }

    // Récupérer tous les véhicules
    public List<Vehicule> findAll() {
        return vehiculeRepository.findAll();
    }

    // Récupérer un véhicule par ID
    public Optional<Vehicule> findById(Long id) {
        return vehiculeRepository.findById(id);
    }

    // Enregistrer un nouveau véhicule
    public Vehicule save(Vehicule vehicule) {
        return vehiculeRepository.save(vehicule);
    }

    // Mettre à jour un véhicule existant
    public Vehicule update(Long id, Vehicule vehicule) {
        return vehiculeRepository.findById(id)
                .map(existing -> {
                    // Champs communs
                    existing.setReference(vehicule.getReference());
                    existing.setModele(vehicule.getModele());
                    existing.setMarque(vehicule.getMarque());
                    existing.setAnnee(vehicule.getAnnee());
                    existing.setCouleur(vehicule.getCouleur());
                    existing.setQteStock(vehicule.getQteStock());
                    existing.setPrixBase(vehicule.getPrixBase());
                    existing.setDateArrivee(vehicule.getDateArrivee());
                    existing.setEstSolde(vehicule.isEstSolde());
                    existing.setKilometrage(vehicule.getKilometrage());
                    existing.setStatus(vehicule.getStatus());
                    existing.setImages(vehicule.getImages());

                    // Champs spécifiques selon le type
                    if (existing instanceof AutomobileElectrique autoExist &&
                            vehicule instanceof AutomobileElectrique autoNew) {
                        autoExist.setBatterieKwh(autoNew.getBatterieKwh());
                    }

                    if (existing instanceof AutomobileEssence autoExist &&
                            vehicule instanceof AutomobileEssence autoNew) {
                        // pas de champ spécifique pour l'instant
                    }

                    if (existing instanceof ScooterElectrique scooterExist &&
                            vehicule instanceof ScooterElectrique scooterNew) {
                        scooterExist.setBatterieKwh(scooterNew.getBatterieKwh());
                    }

                    if (existing instanceof ScooterEssence scooterExist &&
                            vehicule instanceof ScooterEssence scooterNew) {
                        scooterExist.setInjectionDirecte(scooterNew.isInjectionDirecte());
                    }

                    return vehiculeRepository.save(existing);
                })
                .orElseThrow(() -> new RuntimeException("Vehicule not found"));
    }


    public void delete(Long id) {
        vehiculeRepository.deleteById(id);
    }
}
