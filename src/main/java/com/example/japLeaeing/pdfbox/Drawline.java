package com.example.japLeaeing.pdfbox;

import java.io.IOException;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.documentinterchange.taggedpdf.PDTableAttributeObject;

/**
 * Create a blank PDF and write the contents to a file.
 */
public final class Drawline
{
    private Drawline()
    {

    }

    @SuppressWarnings("deprecation")
    public static void main(String[] args) throws IOException
    {
        String filename = "D:/per.pdf";

        PDDocument doc = new PDDocument();
        PDTableAttributeObject pdTableAttributeObject = new PDTableAttributeObject();
        pdTableAttributeObject.setHeaders(new String[]{"test1", "test2"});
        pdTableAttributeObject.setRowSpan(2);
        try
        {
            // a valid PDF document requires at least one page
            PDPage blankPage = new PDPage();
            doc.addPage(blankPage);
            PDPageContentStream contentStream = new PDPageContentStream(doc, blankPage);
            contentStream.setStrokingColor(66, 177, 230);
            contentStream.drawLine(100, 100, 200, 100);
            contentStream.drawLine(20, 20, 800, 800);
            contentStream.close();
            doc.save(filename);

        }
        finally
        {
            doc.close();
        }
    }
}