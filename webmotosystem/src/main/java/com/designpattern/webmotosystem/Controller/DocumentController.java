package com.designpattern.webmotosystem.Controller;

import com.designpattern.webmotosystem.Entities.documents.adapter.Document;
import com.designpattern.webmotosystem.Entities.documents.adapter.HtmlDocument;
import com.designpattern.webmotosystem.Entities.documents.adapter.PdfAdapter;
import com.designpattern.webmotosystem.Services.DocumentService;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

@RestController
public class DocumentController {

    private final DocumentService documentService;

    public DocumentController(DocumentService documentService) {
        this.documentService = documentService;
    }

    // 👉 Endpoints groupés (JSON pour prévisualisation)
    @GetMapping("/documents/pdf")
    public List<Document> generatePDFDocuments() {
        return documentService.generatePDFDocuments();
    }

    @GetMapping("/documents/html")
    public List<Document> generateHTMLDocuments() {
        return documentService.generateHTMLDocuments();
    }

    @GetMapping("/documents/all")
    public List<Document> generateAllDocuments() {
        List<Document> htmlDocs = documentService.generateHTMLDocuments();
        List<Document> pdfDocs = documentService.generatePDFDocuments();
        htmlDocs.addAll(pdfDocs);
        return htmlDocs;
    }

    // 👉 Téléchargement direct PDF
    @GetMapping("/documents/pdf/demande/download")
    public ResponseEntity<byte[]> downloadDemandeImmatriculationPDF() throws IOException {
        PdfAdapter doc = new PdfAdapter();
        doc.setContent("Demande d'immatriculation (PDF)");
        doc.print();

        Path path = Paths.get("document.pdf");
        byte[] fileBytes = Files.readAllBytes(path);

        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=demande_immatriculation.pdf")
                .header(HttpHeaders.CONTENT_TYPE, "application/pdf")
                .body(fileBytes);
    }

    @GetMapping("/documents/pdf/cession/download")
    public ResponseEntity<byte[]> downloadCertificatCessionPDF() throws IOException {
        PdfAdapter doc = new PdfAdapter();
        doc.setContent("Certificat de cession (PDF)");
        doc.print();

        Path path = Paths.get("document.pdf");
        byte[] fileBytes = Files.readAllBytes(path);

        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=certificat_cession.pdf")
                .header(HttpHeaders.CONTENT_TYPE, "application/pdf")
                .body(fileBytes);
    }

    @GetMapping("/documents/pdf/commande/download")
    public ResponseEntity<byte[]> downloadBonCommandePDF() throws IOException {
        PdfAdapter doc = new PdfAdapter();
        doc.setContent("Bon de commande (PDF)");
        doc.print();

        Path path = Paths.get("document.pdf");
        byte[] fileBytes = Files.readAllBytes(path);

        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=bon_commande.pdf")
                .header(HttpHeaders.CONTENT_TYPE, "application/pdf")
                .body(fileBytes);
    }

    // 👉 Téléchargement direct HTML
    @GetMapping("/documents/html/demande/download")
    public ResponseEntity<byte[]> downloadDemandeImmatriculationHTML() throws IOException {
        HtmlDocument doc = new HtmlDocument("Demande d'immatriculation (HTML)");
        doc.print();

        Path path = Paths.get("document.html");
        byte[] fileBytes = Files.readAllBytes(path);

        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=demande_immatriculation.html")
                .header(HttpHeaders.CONTENT_TYPE, "text/html")
                .body(fileBytes);
    }

    @GetMapping("/documents/html/cession/download")
    public ResponseEntity<byte[]> downloadCertificatCessionHTML() throws IOException {
        HtmlDocument doc = new HtmlDocument("Certificat de cession (HTML)");
        doc.print();

        Path path = Paths.get("document.html");
        byte[] fileBytes = Files.readAllBytes(path);

        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=certificat_cession.html")
                .header(HttpHeaders.CONTENT_TYPE, "text/html")
                .body(fileBytes);
    }

    @GetMapping("/documents/html/commande/download")
    public ResponseEntity<byte[]> downloadBonCommandeHTML() throws IOException {
        HtmlDocument doc = new HtmlDocument("Bon de commande (HTML)");
        doc.print();

        Path path = Paths.get("document.html");
        byte[] fileBytes = Files.readAllBytes(path);

        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=bon_commande.html")
                .header(HttpHeaders.CONTENT_TYPE, "text/html")
                .body(fileBytes);
    }

    // 👉 Téléchargement groupé ZIP
    @GetMapping("/documents/zip/download")
    public ResponseEntity<byte[]> downloadAllDocumentsAsZip() throws IOException {
        List<Document> htmlDocs = documentService.generateHTMLDocuments();
        List<Document> pdfDocs = documentService.generatePDFDocuments();

        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        ZipOutputStream zos = new ZipOutputStream(baos);

        // Ajouter les fichiers HTML
        for (Document doc : htmlDocs) {
            HtmlDocument htmlDoc = new HtmlDocument(doc.getContent());
            htmlDoc.print();
            Path path = Paths.get("document.html");
            byte[] fileBytes = Files.readAllBytes(path);

            String fileName = doc.getContent().replaceAll("\\s+", "_").toLowerCase() + ".html";
            zos.putNextEntry(new ZipEntry(fileName));
            zos.write(fileBytes);
            zos.closeEntry();
        }

        // Ajouter les fichiers PDF
        for (Document doc : pdfDocs) {
            PdfAdapter pdfDoc = new PdfAdapter();
            pdfDoc.setContent(doc.getContent());
            pdfDoc.print();
            Path path = Paths.get("document.pdf");
            byte[] fileBytes = Files.readAllBytes(path);

            String fileName = doc.getContent().replaceAll("\\s+", "_").toLowerCase() + ".pdf";
            zos.putNextEntry(new ZipEntry(fileName));
            zos.write(fileBytes);
            zos.closeEntry();
        }

        zos.close();

        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=documents.zip")
                .header(HttpHeaders.CONTENT_TYPE, "application/zip")
                .body(baos.toByteArray());
    }
}
