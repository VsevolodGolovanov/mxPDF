/*
 * $Id: Generic.java 3373 2008-05-12 16:21:24Z xlv $
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

package com.mxpdf.examples.objects.chunk;

import java.io.FileOutputStream;
import java.io.IOException;

import com.mxpdf.text.Chunk;
import com.mxpdf.text.Document;
import com.mxpdf.text.DocumentException;
import com.mxpdf.text.Paragraph;
import com.mxpdf.text.Rectangle;
import com.mxpdf.text.pdf.PdfContentByte;
import com.mxpdf.text.pdf.PdfPageEventHelper;
import com.mxpdf.text.pdf.PdfWriter;

/**
 * Demonstrates the use of the Generic PageEvent.
 * 

 */

public class Generic extends PdfPageEventHelper {
	/**
	 * Draws an ellipse if the text was ellipse, a box if the text was box.
	 * 
	 * @see com.mxpdf.text.pdf.PdfPageEventHelper#onGenericTag(com.mxpdf.text.pdf.PdfWriter, com.mxpdf.text.Document, com.mxpdf.text.Rectangle, java.lang.String)
	 */
	public void onGenericTag(PdfWriter writer, Document document, Rectangle rect, String text) {
		if ("ellipse".equals(text)) {
			PdfContentByte cb = writer.getDirectContent();
			cb.setRGBColorStroke(0xFF, 0x00, 0x00);
			cb.ellipse(rect.getLeft(), rect.getBottom() - 5f, rect.getRight(), rect.getTop());
			cb.stroke();
			cb.resetRGBColorStroke();
		}
		else if ("box".equals(text)) {
			PdfContentByte cb = writer.getDirectContentUnder();
			rect.setGrayFill(0.5f);
			cb.rectangle(rect);
		}
    }
	/**
	 * Generic page event.
	 * 
	 * @param args no arguments needed here
	 */
	public static void main(String[] args) {

		System.out.println("Generic");

		// step 1: creation of a document-object
		Document document = new Document();
		try {
			// step 2:
			// we create a writer that listens to the document
			PdfWriter writer = PdfWriter.getInstance(document,
					new FileOutputStream("Generic.pdf"));
			writer.setPageEvent(new Generic());
			
			// step 3: we open the document
			document.open();
			// step 4:
			Paragraph p = new Paragraph("Generic page event");
			document.add(p);
			Chunk box = new Chunk("box");
			box.setGenericTag("box");
			Chunk ellipse = new Chunk("ellipse");
			ellipse.setGenericTag("ellipse");
			p = new Paragraph("In this example, we will add chunks that are tagged as an ");
			p.add(ellipse);
			p.add(" and chunks that are tagged as a ");
			p.add(box);
			p.add(". Can you see the difference between ");
			Chunk c1 = new Chunk("this");
			c1.setGenericTag("box");
			Chunk c2 = new Chunk("that");
			c2.setGenericTag("ellipse");
			p.add(c1);
			p.add(" and ");
			p.add(c2);
			p.add("? One is a ");
			p.add(box);
			p.add("; the other an ");
			p.add(ellipse);
			document.add(p);
		} catch (DocumentException de) {
			System.err.println(de.getMessage());
		} catch (IOException ioe) {
			System.err.println(ioe.getMessage());
		}

		// step 5: we close the document
		document.close();
	}
}