package com.example.todoapp.views;

import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.Paragraph;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.html.Anchor;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextArea;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.server.StreamResource;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;

@Route("pdf")
public class PdfView extends VerticalLayout {

    public PdfView() {
        TextField titulo = new TextField("Título do PDF");
        TextArea conteudo = new TextArea("Conteúdo do PDF");
        conteudo.setHeight("150px");

        Button gerar = new Button("Gerar PDF", event -> {
            try {
                // Gerar o PDF diretamente aqui
                ByteArrayOutputStream baos = new ByteArrayOutputStream();
                PdfWriter writer = new PdfWriter(baos);
                PdfDocument pdf = new PdfDocument(writer);
                Document document = new Document(pdf);

                document.add(new Paragraph(titulo.getValue()).setBold().setFontSize(18));
                document.add(new Paragraph("\n"));
                document.add(new Paragraph(conteudo.getValue()));
                document.close();

                // Criar recurso para download
                StreamResource resource = new StreamResource("documento.pdf",
                        () -> new ByteArrayInputStream(baos.toByteArray()));

                Anchor link = new Anchor(resource, "📄 Descarregar PDF");
                link.getElement().setAttribute("download", true);
                add(link);

                Notification.show("✅ PDF criado com sucesso!");
            } catch (Exception e) {
                Notification.show("❌ Erro ao gerar PDF: " + e.getMessage());
            }
        });

        add(titulo, conteudo, gerar);
    }
}
