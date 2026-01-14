package com.designpattern.webmotosystem.Entities.documents.builder;

import com.designpattern.webmotosystem.Entities.documents.adapter.Document;
import com.designpattern.webmotosystem.Entities.documents.adapter.HtmlDocument;
import java.util.ArrayList;
import java.util.List;

public class LiasseHTMLBuilder implements LiasseBuilder {
    private List<Document> documents = new ArrayList<>();

    @Override
    public void buildDemandeImmatriculation() {
        documents.add(new HtmlDocument("Demande d'immatriculation"));
    }

    @Override
    public void buildCertificatCession() {
        documents.add(new HtmlDocument("Certificat de cession"));
    }

    @Override
    public void buildBonCommande() {
        documents.add(new HtmlDocument("Bon de commande"));
    }

    @Override
    public List<Document> getResult() {
        return documents;
    }
}
