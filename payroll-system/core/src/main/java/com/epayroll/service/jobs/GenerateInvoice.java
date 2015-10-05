package com.epayroll.service.jobs;

import java.io.FileOutputStream;
import java.io.IOException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Font;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.Image;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.BaseFont;
import com.itextpdf.text.pdf.CMYKColor;
import com.itextpdf.text.pdf.PdfContentByte;
import com.itextpdf.text.pdf.PdfWriter;

public class GenerateInvoice {

	private static Logger logger = LoggerFactory.getLogger(GenerateInvoice.class);

	private BaseFont bfBold;
	private BaseFont bf;

	public static void main(String[] args) {
		logger.debug("Generating PDF Invoice");
		printPdf("InvoiceSample");
	}

	public static void printPdf(String pdfFilename) {
		GenerateInvoice generateInvoice = new GenerateInvoice();
		generateInvoice.createPDF(pdfFilename);
	}

	private void createPDF(String pdfFilename) {

		Document doc = new Document();
		PdfWriter docWriter = null;
		initializeFonts();

		try {
			String path = "C:\\" + pdfFilename + ".pdf";
			docWriter = PdfWriter.getInstance(doc, new FileOutputStream(path));
			doc.addAuthor("Surendra Jha");
			doc.addCreationDate();
			doc.addProducer();
			doc.addCreator("I-tech Software");
			doc.addTitle("Invoice");
			doc.setPageSize(PageSize.LETTER);
			doc.addSubject("Payroll Invoice");

			doc.open();

			// write header
			Paragraph header = new Paragraph("Payroll Invoice", FontFactory.getFont(
					FontFactory.HELVETICA, 16, Font.BOLD, new CMYKColor(255, 255, 255, 17)));
			header.setAlignment(1);
			doc.add(header);

			PdfContentByte cb = docWriter.getDirectContent();

			generateLayout(doc, cb);

			printData(doc, cb);

		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			if (doc != null) {
				doc.close();
			}
			if (docWriter != null) {
				docWriter.close();
			}
		}
	}

	private void printData(Document doc, PdfContentByte cb) {

		createContent(cb, 300, 700, "WeekLy", PdfContentByte.ALIGN_RIGHT);
		createContent(cb, 520, 700, "01/02/2013", PdfContentByte.ALIGN_RIGHT);
		createContent(cb, 520, 685, "01/25/2013", PdfContentByte.ALIGN_RIGHT);

	}

	private void generateLayout(Document doc, PdfContentByte cb) {
		try {
			cb.setLineWidth(1f);

			// Invoice Header box Text Headings
			createHeadings(cb, 200, 700, "Pay Frequency:");

			createHeadings(cb, 422, 700, "Period Start:");
			createHeadings(cb, 422, 685, "Period End:");

			createHeadings(cb, 120, 670, "Total Employee Tax : ");
			createHeadings(cb, 120, 655, "Total Employer Tax : ");
			createHeadings(cb, 120, 640, "Total Direct Diposit : ");
			createHeadings(cb, 120, 625, "Total Check Deposit : ");

			createHeadings(cb, 120, 610, "Grand Total : ");

			createHeadings(cb, 120, 590, "Payment Amount : ");
			createHeadings(cb, 120, 575, "Due Date : ");

			// add the images
			Image companyLogo = Image.getInstance("C:\\image.jpg");
			companyLogo.setAbsolutePosition(25, 700);
			companyLogo.scalePercent(25);
			doc.add(companyLogo);

		} catch (DocumentException dex) {
			dex.printStackTrace();
		} catch (Exception ex) {
			ex.printStackTrace();
		}

	}

	private void createHeadings(PdfContentByte cb, float x, float y, String text) {
		cb.beginText();
		cb.setFontAndSize(bfBold, 8);
		cb.setTextMatrix(x, y);
		cb.showText(text.trim());
		cb.endText();
	}

	private void createContent(PdfContentByte cb, float x, float y, String text, int align) {
		cb.beginText();
		cb.setFontAndSize(bf, 8);
		cb.showTextAligned(align, text.trim(), x, y, 0);
		cb.endText();
	}

	private void initializeFonts() {
		logger.debug(" initializeFonts ...");
		try {
			bfBold = BaseFont.createFont(BaseFont.HELVETICA_BOLD, BaseFont.CP1252,
					BaseFont.NOT_EMBEDDED);
			bf = BaseFont.createFont(BaseFont.HELVETICA, BaseFont.CP1252, BaseFont.NOT_EMBEDDED);
		} catch (DocumentException | IOException e) {
			logger.error("Exception in initializeFonts()..", e);
		}
	}
}