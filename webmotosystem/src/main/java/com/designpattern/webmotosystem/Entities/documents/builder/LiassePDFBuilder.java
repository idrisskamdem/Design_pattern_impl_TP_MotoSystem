package com.designpattern.webmotosystem.Entities.documents.builder;

import com.designpattern.webmotosystem.Entities.documents.adapter.Document;
import com.designpattern.webmotosystem.Entities.documents.adapter.PdfAdapter;
import java.util.ArrayList;
import java.util.List;

public class LiassePDFBuilder implements LiasseBuilder {
    private List<Document> documents = new ArrayList<>();

    @Override
    public void buildDemandeImmatriculation() {
        Document doc = new PdfAdapter();
        doc.setContent("Demande d'immatriculation");
        documents.add(doc);
    }

    @Override
    public void buildCertificatCession() {
        Document doc = new PdfAdapter();
        doc.setContent("Certificat de cession");
        documents.add(doc);
    }

    @Override
    public void buildBonCommande() {
        Document doc = new PdfAdapter();
        doc.setContent("Bon de commande");
        documents.add(doc);
    }

    @Override
    public List<Document> getResult() {
        return documents;
    }
}
