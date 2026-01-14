package com.designpattern.webmotosystem.Entities.documents.adapter;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.common.PDRectangle;
import org.apache.pdfbox.pdmodel.font.PDType1Font;
import org.apache.pdfbox.pdmodel.PDPageContentStream;

import java.io.IOException;

public class PdfAdapter implements Document {
    private String content;

    public PdfAdapter() {}

    @Override
    public void setContent(String content) {
        this.content = content;
    }

    @Override
    public void print() {
        try (PDDocument doc = new PDDocument()) {
            PDPage page = new PDPage(PDRectangle.A4);
            doc.addPage(page);

            PDPageContentStream cs = new PDPageContentStream(doc, page);

            // Position de départ
            float marginLeft = 50;
            float y = 750;

            // Titre principal
            cs.beginText();
            cs.setFont(PDType1Font.HELVETICA_BOLD, 18);
            cs.newLineAtOffset(marginLeft, y);
            cs.showText(content);
            cs.endText();

            y -= 40; // espace après le titre

            // Sections selon le type de document
            if (content.contains("Demande d'immatriculation")) {
                y = writeSection(cs, marginLeft, y, "Informations du propriétaire",
                        new String[]{"Nom: Jean Dupont", "Email: jean.dupont@example.com"});
                y = writeSection(cs, marginLeft, y, "Informations du véhicule",
                        new String[]{"Marque: Toyota", "Modèle: Corolla", "Année: 2022"});
            } else if (content.contains("Certificat de cession")) {
                y = writeSection(cs, marginLeft, y, "Vendeur",
                        new String[]{"Nom: Société Auto SA"});
                y = writeSection(cs, marginLeft, y, "Acheteur",
                        new String[]{"Nom: Jean Dupont"});
                y = writeSection(cs, marginLeft, y, "Véhicule",
                        new String[]{"Marque: Toyota Corolla", "Immatriculation: AB-123-CD"});
            } else if (content.contains("Bon de commande")) {
                y = writeSection(cs, marginLeft, y, "Client",
                        new String[]{"Nom: Jean Dupont"});
                y = writeSection(cs, marginLeft, y, "Vendeur",
                        new String[]{"Société: Auto SA"});
                y = writeSection(cs, marginLeft, y, "Détails du véhicule",
                        new String[]{"Marque: Toyota Corolla", "Prix: 20 000 €"});
            }

            cs.close();
            doc.save("document.pdf");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private float writeSection(PDPageContentStream cs, float x, float y, String title, String[] lines) throws IOException {
        // Titre de section
        cs.beginText();
        cs.setFont(PDType1Font.HELVETICA_BOLD, 14);
        cs.newLineAtOffset(x, y);
        cs.showText(title);
        cs.endText();

        y -= 20;

        // Contenu de section
        for (String line : lines) {
            cs.beginText();
            cs.setFont(PDType1Font.HELVETICA, 12);
            cs.newLineAtOffset(x + 20, y);
            cs.showText(line);
            cs.endText();
            y -= 15;
        }

        y -= 10; // espace entre sections
        return y;
    }

    @Override
    public String getContent() {
        return content;
    }

    @Override
    public String getType() {
        return "PdfAdapter";
    }
}