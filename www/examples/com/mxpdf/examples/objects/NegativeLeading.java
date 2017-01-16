/*
 * $Id: NegativeLeading.java 3373 2008-05-12 16:21:24Z xlv $
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

package com.mxpdf.examples.objects;

import java.io.FileOutputStream;
import java.io.IOException;

import com.mxpdf.text.Document;
import com.mxpdf.text.DocumentException;
import com.mxpdf.text.Phrase;
import com.mxpdf.text.pdf.PdfWriter;

/**
 * Shows the effect of a negative leading.
 * 

 */

public class NegativeLeading {

	/**
	 * Demonstrates what happens if you choose a negative leading.
	 * 
	 * @param args
	 *            no arguments needed here
	 */
	public static void main(String[] args) {

		System.out.println("Negative Leading");

		// step 1: creation of a document-object
		Document document = new Document();
		try {
			// step 2:
			// we create a writer that listens to the document
			PdfWriter.getInstance(document, new FileOutputStream(
					"NegativeLeading.pdf"));

			// step 3: we open the document
			document.open();
			// step 4:
			document.add(new Phrase(16, "\n\n\n"));
			document
					.add(new Phrase(
							-16,
							"Hello, this is a very long phrase to show you the somewhat odd effect of a negative leading. You can write from bottom to top. This is not fully supported. It's something between a feature and a bug."));
		} catch (DocumentException de) {
			System.err.println(de.getMessage());
		} catch (IOException ioe) {
			System.err.println(ioe.getMessage());
		}

		// step 5: we close the document
		document.close();
	}
}