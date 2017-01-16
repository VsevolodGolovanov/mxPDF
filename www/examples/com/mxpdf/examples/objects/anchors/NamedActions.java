/*
 * $Id: NamedActions.java 3373 2008-05-12 16:21:24Z xlv $
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

package com.mxpdf.examples.objects.anchors;

import java.io.FileOutputStream;

import com.mxpdf.text.Chunk;
import com.mxpdf.text.Document;
import com.mxpdf.text.Element;
import com.mxpdf.text.PageSize;
import com.mxpdf.text.Paragraph;
import com.mxpdf.text.Phrase;
import com.mxpdf.text.pdf.PdfAction;
import com.mxpdf.text.pdf.PdfPTable;
import com.mxpdf.text.pdf.PdfWriter;

/**
 * Creates a documents with different named actions.
 * 

 */

public class NamedActions {

	/**
	 * Creates a document with Named Actions.
	 * 
	 * @param args
	 *            no arguments needed
	 */
	public static void main(String[] args) {

		System.out.println("Named Actions");

		// step 1: creation of a document-object
		Document document = new Document(PageSize.A4, 50, 50, 50, 50);

		try {

			// step 2: we create a writer that listens to the document
			PdfWriter.getInstance(document,
					new FileOutputStream("NamedActions.pdf"));
			// step 3: we open the document
			document.open();
			// step 4: we add some content
			Paragraph p = new Paragraph(new Chunk("Click to print")
					.setAction(new PdfAction(PdfAction.PRINTDIALOG)));
			PdfPTable table = new PdfPTable(4);
			table.getDefaultCell().setHorizontalAlignment(Element.ALIGN_CENTER);
			table.addCell(new Phrase(new Chunk("First Page")
					.setAction(new PdfAction(PdfAction.FIRSTPAGE))));
			table.addCell(new Phrase(new Chunk("Prev Page")
					.setAction(new PdfAction(PdfAction.PREVPAGE))));
			table.addCell(new Phrase(new Chunk("Next Page")
					.setAction(new PdfAction(PdfAction.NEXTPAGE))));
			table.addCell(new Phrase(new Chunk("Last Page")
					.setAction(new PdfAction(PdfAction.LASTPAGE))));
			for (int k = 1; k <= 10; ++k) {
				document.add(new Paragraph("This is page " + k));
				document.add(Chunk.NEWLINE);
				document.add(table);
				document.add(p);
				document.newPage();
			}
		} catch (Exception de) {
			de.printStackTrace();
		}

		// step 5: we close the document
		document.close();

	}
}