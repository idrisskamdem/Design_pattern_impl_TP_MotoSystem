package com.designpattern.webmotosystem.Entities.Commande;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;

@Entity
@DiscriminatorValue("CREDIT")
public class CommandeCredit extends Commande {

    @Override
    public double calculerFraisSpecifique() {
        // exemple: frais de credit de 10% du montant base
        return getMontant() * 0.10;
    }
}
