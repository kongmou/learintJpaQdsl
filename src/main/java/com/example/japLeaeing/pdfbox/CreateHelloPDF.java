package com.example.japLeaeing.pdfbox;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.font.PDFont;
import org.apache.pdfbox.pdmodel.font.PDType1Font;
import org.apache.pdfbox.pdmodel.graphics.form.PDFormXObject;

/**
 * 〈〉
 *
 * @author chinasoft.jie.zhang
 * @create 2019/8/9
 * @since 1.0.0
 */
public class CreateHelloPDF {

    public static void createPdf(){
        PDDocument doc = null;
        PDPage page = null;

        try {
            doc = new PDDocument();
            page = new PDPage();
            doc.addPage(page);
            PDFont font = PDType1Font.HELVETICA_BOLD;
            PDPageContentStream content = new PDPageContentStream(doc, page);
            content.beginText();
            content.setFont(font, 12);
            content.moveTextPositionByAmount(100, 700);
            String txt = "Faith can move mountains";
            PDFormXObject pdFormXObject = new PDFormXObject(doc);


            content.endText();
            content.close();
            doc.save("D:/per.pdf");
            doc.close();
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public static void main(String[] args) {
        createPdf();
        System.err.println("success");
    }
}