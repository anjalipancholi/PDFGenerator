package com.needleinnovision.pdfgenerator.service;

import com.lowagie.text.DocumentException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Service;
import org.thymeleaf.context.Context;
import org.thymeleaf.spring5.SpringTemplateEngine;
import org.xhtmlrenderer.pdf.ITextRenderer;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;

@Service
public class PdfService {
    @Autowired
    private ServiceInfoService serviceInfoService;
    @Autowired
    private SpringTemplateEngine templateEngine;

    public File generatePdf() throws IOException, DocumentException {
        Context context = getContext();
        String html = loadAndFillTemplate(context);
        return renderPdf(html);
    }


    private File renderPdf(String html) throws IOException, DocumentException {
        File file = File.createTempFile("Service Export", ".pdf");
        try (
                OutputStream outputStream = new FileOutputStream(file);) {
            ITextRenderer renderer = new ITextRenderer();
            renderer.setDocumentFromString(html, new ClassPathResource("/templates/").getURL().toExternalForm());
            renderer.layout();
            renderer.createPDF(outputStream);
        }
        return file;
    }

    private Context getContext() {
        Context context = new Context();
        context.setVariable("serviceInfo", serviceInfoService.getServices());
        return context;
    }

    private String loadAndFillTemplate(Context context) {
        return templateEngine.process("ServiceExport", context);
    }
}
