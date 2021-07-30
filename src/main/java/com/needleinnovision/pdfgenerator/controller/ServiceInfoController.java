package com.needleinnovision.pdfgenerator.controller;

import com.lowagie.text.DocumentException;
import com.needleinnovision.pdfgenerator.service.PdfService;
import com.needleinnovision.pdfgenerator.service.ServiceInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.springframework.web.bind.annotation.RestController;

@RestController
public class ServiceInfoController {
    @Autowired
    private ServiceInfoService serviceInfoService;
    @Autowired
    private PdfService pdfService;


    @GetMapping("/")
    public void downloadPDFResource(HttpServletResponse response) {
        try {
            Path file = Paths.get(pdfService.generatePdf().getAbsolutePath());
            if (Files.exists(file)) {
                response.setContentType("application/pdf");
                response.addHeader("Content-Disposition",
                        "attachment; filename=" + file.getFileName());
                Files.copy(file, response.getOutputStream());
                response.getOutputStream().flush();
            }
        } catch (DocumentException | IOException exception) {
            exception.printStackTrace();
        }
    }

}
