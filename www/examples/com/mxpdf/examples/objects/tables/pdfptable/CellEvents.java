/*
 * $Id: CellEvents.java 3373 2008-05-12 16:21:24Z xlv $
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
package com.mxpdf.examples.objects.tables.pdfptable;

import java.io.FileOutputStream;

import com.mxpdf.text.Document;
import com.mxpdf.text.Image;
import com.mxpdf.text.PageSize;
import com.mxpdf.text.Rectangle;
import com.mxpdf.text.pdf.PdfContentByte;
import com.mxpdf.text.pdf.PdfPCell;
import com.mxpdf.text.pdf.PdfPCellEvent;
import com.mxpdf.text.pdf.PdfPTable;
import com.mxpdf.text.pdf.PdfWriter;

/**
 * General example using CellEvents.
 */
public class CellEvents implements PdfPCellEvent {

	/**
	 * @see com.mxpdf.text.pdf.PdfPCellEvent#cellLayout(com.mxpdf.text.pdf.PdfPCell,
	 *      com.mxpdf.text.Rectangle, com.mxpdf.text.pdf.PdfContentByte[])
	 */
	public void cellLayout(PdfPCell cell, Rectangle position,
			PdfContentByte[] canvases) {
		PdfContentByte cb = canvases[PdfPTable.TEXTCANVAS];
		cb.moveTo(position.getLeft(), position.getBottom());
		cb.lineTo(position.getRight(), position.getTop());
		cb.stroke();
	}

	/**
	 * General example using cell events.
	 * 
	 * @param args
	 *            no arguments needed
	 */
	public static void main(String[] args) {

		System.out.println("CellEvents");
		// step1
		Document document = new Document(PageSize.A4, 50, 50, 50, 50);
		try {
			// step2
			PdfWriter writer = PdfWriter.getInstance(document,
					new FileOutputStream("CellEvents.pdf"));
			// step3
			document.open();
			// step4
			CellEvents event = new CellEvents();
			Image im = Image.getInstance("otsoe.jpg");
			im.setRotationDegrees(30);
			PdfPTable table = new PdfPTable(4);
			table.addCell("text 1");
			PdfPCell cell = new PdfPCell(im, true);
			cell.setCellEvent(event);
			table.addCell(cell);
			table.addCell("text 3");
			im.setRotationDegrees(0);
			table.addCell(im);
			table.setTotalWidth(300);
			PdfContentByte cb = writer.getDirectContent();
			table.writeSelectedRows(0, -1, 50, 600, cb);
			table.setHeaderRows(3);
			document.add(table);
		} catch (Exception de) {
			de.printStackTrace();
		}
		// step5
		document.close();
	}
}