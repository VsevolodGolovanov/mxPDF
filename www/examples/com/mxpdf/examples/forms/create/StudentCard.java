/*
 * $Id: StudentCard.java 3373 2008-05-12 16:21:24Z xlv $
 *
 * This code is part of the 'iText Tutorial'.
 * You can find the complete tutorial at the following address:

 *
 * This code is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.
 *
 * itext-questions@lists.sourceforge.net
 */

package com.mxpdf.examples.forms.create;


import java.awt.Color;
import java.io.FileOutputStream;
import java.io.IOException;

import com.mxpdf.text.Document;
import com.mxpdf.text.DocumentException;
import com.mxpdf.text.Element;
import com.mxpdf.text.Font;
import com.mxpdf.text.FontFactory;
import com.mxpdf.text.Image;
import com.mxpdf.text.Paragraph;
import com.mxpdf.text.Rectangle;
import com.mxpdf.text.pdf.Barcode;
import com.mxpdf.text.pdf.BarcodeEAN;
import com.mxpdf.text.pdf.PdfContentByte;
import com.mxpdf.text.pdf.PdfPTable;
import com.mxpdf.text.pdf.PdfWriter;

/**
 * Generates a StudentCard

 */
public class StudentCard {
    /**
     * Generates a StudentCard
     * @param args no arguments needed here
     */
    public static void main(String[] args) {
        
        System.out.println("StudentCard");
        
        // step 1: creation of a document-object
        Rectangle rect = new Rectangle(243, 153);
        rect.setBackgroundColor(new Color(0xFF, 0xFF, 0xCC));
        Document document = new Document(rect, 10, 10, 10, 10);
        
        try {
            
            // step 2:
            PdfWriter writer = PdfWriter.getInstance(document, new FileOutputStream("studentcard.pdf"));
            
            // step 3: we open the document
            document.open();
            
            // step 4:
            Font font = FontFactory.getFont(FontFactory.HELVETICA, 14, Font.BOLD, Color.BLUE);
            Paragraph p = new Paragraph("Ghent University", font);
            p.setAlignment(Element.ALIGN_CENTER);
            document.add(p);
            PdfContentByte cb = writer.getDirectContent();
            Font f = FontFactory.getFont(FontFactory.HELVETICA, 8);
            PdfPTable outertable = new PdfPTable(3);
            outertable.setTotalWidth(200);
            outertable.getDefaultCell().setBorder(Rectangle.NO_BORDER);
            float[] outer = { 60, 25, 15 };
            outertable.setWidths(outer);
            PdfPTable innertable = new PdfPTable(2);
            float[] inner = {35, 65};
            innertable.setWidths(inner);
            innertable.addCell(new Paragraph("name:", f));
            innertable.addCell(new Paragraph("Bruno Lowagie", f));
            innertable.addCell(new Paragraph("date of birth:", f));
            innertable.addCell(new Paragraph("June 10th, 1970", f));
            innertable.addCell(new Paragraph("Study Program:", f));
            innertable.addCell(new Paragraph("master in civil engineering", f));
            innertable.addCell(new Paragraph("option:", f));
            innertable.addCell(new Paragraph("architecture", f));
            outertable.addCell(innertable);
			outertable.getDefaultCell().setBackgroundColor(new Color(0xFF, 0xDE, 0xAD));
			outertable.addCell(Image.getInstance("bruno.jpg"));
            BarcodeEAN codeEAN = new BarcodeEAN();
            codeEAN.setCodeType(Barcode.EAN13);
            codeEAN.setCode("8010012529736");
			Image imageEAN = codeEAN.createImageWithBarcode(cb, null, null);            
			imageEAN.setRotationDegrees(90);
			outertable.getDefaultCell().setBackgroundColor(Color.WHITE);
			outertable.addCell(imageEAN);
            outertable.writeSelectedRows(0, -1, 20, 100, writer.getDirectContent());
        }
        catch(DocumentException de) {
            System.err.println(de.getMessage());
        }
        catch(IOException ioe) {
            System.err.println(ioe.getMessage());
        }
        
        // step 5: we close the document
        document.close();
    }
}