package com.designpattern.webmotosystem.Entities.documents.adapter;

import java.io.FileWriter;
import java.io.IOException;

public class HtmlDocument implements Document {
    private String content;

    public HtmlDocument(String content) {
        this.content = content;
    }

    @Override
    public void setContent(String content) {
        this.content = content;
    }

    @Override
    public void print() {
        try (FileWriter writer = new FileWriter("document.html")) {
            writer.write("<html><body>");
            writer.write("<h1>" + content + "</h1>");

            if (content.contains("Demande d'immatriculation")) {
                writer.write("<h2>Informations du propriétaire</h2>");
                writer.write("<p>Nom: Jean Dupont</p>");
                writer.write("<p>Email: jean.dupont@example.com</p>");
                writer.write("<h2>Informations du véhicule</h2>");
                writer.write("<p>Marque: Toyota</p>");
                writer.write("<p>Modèle: Corolla</p>");
                writer.write("<p>Année: 2022</p>");
            } else if (content.contains("Certificat de cession")) {
                writer.write("<h2>Vendeur</h2>");
                writer.write("<p>Nom: Société Auto SA</p>");
                writer.write("<h2>Acheteur</h2>");
                writer.write("<p>Nom: Jean Dupont</p>");
                writer.write("<h2>Véhicule</h2>");
                writer.write("<p>Marque: Toyota Corolla</p>");
                writer.write("<p>Immatriculation: AB-123-CD</p>");
            } else if (content.contains("Bon de commande")) {
                writer.write("<h2>Client</h2>");
                writer.write("<p>Nom: Jean Dupont</p>");
                writer.write("<h2>Vendeur</h2>");
                writer.write("<p>Société: Auto SA</p>");
                writer.write("<h2>Détails du véhicule</h2>");
                writer.write("<p>Marque: Toyota Corolla</p>");
                writer.write("<p>Prix: 20 000 €</p>");
            }

            writer.write("</body></html>");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public String getContent() {
        return content;
    }

    @Override
    public String getType() {
        return "HtmlDocument";
    }
}