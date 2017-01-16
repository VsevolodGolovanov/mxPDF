/*
 * $Id: FormRadioButton.java 3373 2008-05-12 16:21:24Z xlv $
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

package com.mxpdf.examples.forms;


import java.io.FileOutputStream;
import java.io.IOException;

import com.mxpdf.text.Document;
import com.mxpdf.text.DocumentException;
import com.mxpdf.text.PageSize;
import com.mxpdf.text.Rectangle;
import com.mxpdf.text.pdf.PdfAnnotation;
import com.mxpdf.text.pdf.PdfAppearance;
import com.mxpdf.text.pdf.PdfContentByte;
import com.mxpdf.text.pdf.PdfFormField;
import com.mxpdf.text.pdf.PdfWriter;

/**
 * Generates an Acroform with a RadioButton

 */
public class FormRadioButton {
    /**
     * Generates an Acroform with a RadioButton
     * @param args no arguments needed here
     */
    public static void main(String[] args) {
        
        System.out.println("RadioButton");
        
        // step 1: creation of a document-object
        Document document = new Document(PageSize.A4);
        
        try {
            
            // step 2:
            PdfWriter writer = PdfWriter.getInstance(document, new FileOutputStream("radiobutton.pdf"));
            
            // step 3: we open the document
            document.open();
            
            // step 4:
            PdfContentByte cb = writer.getDirectContent();
            cb.moveTo(0, 0);
            PdfFormField radio = PdfFormField.createRadioButton(writer, true);
            PdfAppearance tpOff = cb.createAppearance(20, 20);
            PdfAppearance tpOn = cb.createAppearance(20, 20);
            
            tpOff.circle(10, 10, 9);
            tpOff.stroke();

            tpOn.circle(10, 10, 9);
            tpOn.stroke();
            tpOn.circle(10, 10, 3);
            tpOn.fillStroke();

            radio.setFieldName("CreditCard");
            radio.setValueAsName("MasterCard");

            PdfFormField radio1 = PdfFormField.createEmpty(writer);
            radio1.setWidget(new Rectangle(100, 700, 120, 720), PdfAnnotation.HIGHLIGHT_INVERT);
            radio1.setAppearanceState("MasterCard");
            radio1.setAppearance(PdfAnnotation.APPEARANCE_NORMAL, "Off", tpOff);
            radio1.setAppearance(PdfAnnotation.APPEARANCE_NORMAL, "MasterCard", tpOn);
            radio.addKid(radio1);
            
            PdfFormField radio2 = PdfFormField.createEmpty(writer);
            radio2.setWidget(new Rectangle(100, 660, 120, 680), PdfAnnotation.HIGHLIGHT_INVERT);
            radio2.setAppearanceState("Off");
            radio2.setAppearance(PdfAnnotation.APPEARANCE_NORMAL, "Off", tpOff);
            radio2.setAppearance(PdfAnnotation.APPEARANCE_NORMAL, "Visa", tpOn);
            radio.addKid(radio2);
            
            PdfFormField radio3 = PdfFormField.createEmpty(writer);
            radio3.setWidget(new Rectangle(100, 620, 120, 640), PdfAnnotation.HIGHLIGHT_INVERT);
            radio3.setAppearanceState("Off");
            radio3.setAppearance(PdfAnnotation.APPEARANCE_NORMAL, "Off", tpOff);
            radio3.setAppearance(PdfAnnotation.APPEARANCE_NORMAL, "American", tpOn);
            radio.addKid(radio3);
            
            writer.addAnnotation(radio);
            
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