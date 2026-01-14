package com.designpattern.webmotosystem.DTO;

public class CommandeResponse {
    private Long id;
    private String numCommande;
    private String dateCommande;
    private double montant;
    private String etatCommande;
    private String paysLivraison;

    private String clientNom;
    private String clientEmail;

    private VehiculeCommandeResponse vehicule;

    // Getters / Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public String getNumCommande() { return numCommande; }
    public void setNumCommande(String numCommande) { this.numCommande = numCommande; }
    public String getDateCommande() { return dateCommande; }
    public void setDateCommande(String dateCommande) { this.dateCommande = dateCommande; }
    public double getMontant() { return montant; }
    public void setMontant(double montant) { this.montant = montant; }
    public String getEtatCommande() { return etatCommande; }
    public void setEtatCommande(String etatCommande) { this.etatCommande = etatCommande; }
    public String getPaysLivraison() { return paysLivraison; }
    public void setPaysLivraison(String paysLivraison) { this.paysLivraison = paysLivraison; }
    public String getClientNom() { return clientNom; }
    public void setClientNom(String clientNom) { this.clientNom = clientNom; }
    public String getClientEmail() { return clientEmail; }
    public void setClientEmail(String clientEmail) { this.clientEmail = clientEmail; }
    public VehiculeCommandeResponse getVehicule() { return vehicule; }
    public void setVehicule(VehiculeCommandeResponse vehicule) { this.vehicule = vehicule; }
}
