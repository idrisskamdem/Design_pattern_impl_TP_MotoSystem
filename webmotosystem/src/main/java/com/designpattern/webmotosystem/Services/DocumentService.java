package com.designpattern.webmotosystem.Services;

import com.designpattern.webmotosystem.Entities.documents.adapter.Document;
import com.designpattern.webmotosystem.Entities.documents.builder.*;
import com.designpattern.webmotosystem.Entities.documents.singleton.LiasseDocuments;
import java.util.List;

import org.springframework.stereotype.Service;

@Service
public class DocumentService {

    // Générer une liasse PDF
    public List<Document> generatePDFDocuments() {
        LiasseBuilder builder = new LiassePDFBuilder();
        LiasseDirector director = new LiasseDirector(builder);
        List<Document> documents = director.construct();

        // Stocker dans le Singleton
        LiasseDocuments.getInstance().getDocuments().addAll(documents);

        // Imprimer chaque document
        for (Document doc : documents) {
            doc.print();
        }

        return documents;
    }

    // Générer une liasse HTML
    public List<Document> generateHTMLDocuments() {
        LiasseBuilder builder = new LiasseHTMLBuilder();
        LiasseDirector director = new LiasseDirector(builder);
        List<Document> documents = director.construct();

        // Stocker dans le Singleton
        LiasseDocuments.getInstance().getDocuments().addAll(documents);

        // Imprimer chaque document
        for (Document doc : documents) {
            doc.print();
        }

        return documents;
    }
}
