package com.example.demo.control;

import com.example.demo.model.Muzej;
import com.example.demo.model.Ruta;
import com.example.demo.repo.MuzejRepo;
import com.example.demo.repo.RutaRepo;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.ByteArrayOutputStream;
import java.util.List;
import java.util.stream.Collectors;

@RestController
public class PDFController {

    @Autowired
    private RutaRepo rutaRepository;

    @Autowired
    private MuzejRepo muzejRepository;

    @GetMapping("/generate-pdf")
    public ResponseEntity<ByteArrayResource> generatePdf(@RequestParam("rutaId") int rutaId) {
        try {
            // Find Ruta
            Ruta ruta = rutaRepository.findById(rutaId)
                    .orElseThrow(() -> new RuntimeException("Ruta not found"));

            // Get all muzeji for this ruta
            List<Integer> muzejIds = ruta.getRutaHasMuzejs().stream()
                    .map(rutaHasMuzej -> rutaHasMuzej.getMuzej().getIdPERIOD())
                    .collect(Collectors.toList());
            List<Muzej> muzeji = muzejRepository.findAllById(muzejIds);

            // Create PDF
            Document document = new Document();
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            PdfWriter.getInstance(document, baos);

            document.open();
            document.add(new Paragraph("Opis Stanica za Ruta: " + ruta.getImeRute()));

            for (Muzej muzej : muzeji) {
                document.add(new Paragraph("Muzej: " + muzej.getNaziv()));
                document.add(new Paragraph("Opis:\n" + muzej.getTxt0() + "\n" + muzej.getTxt1() + "\n" + muzej.getTxt2()));
                document.add(new Paragraph("\n"));
            }

            document.close();
            ByteArrayResource resource = new ByteArrayResource(baos.toByteArray());

            HttpHeaders headers = new HttpHeaders();
            headers.add(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=stanice_opisi.pdf");
            headers.add(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_PDF_VALUE);

            return new ResponseEntity<>(resource, headers, HttpStatus.OK);
        } catch (DocumentException e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}

