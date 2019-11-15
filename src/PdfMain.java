import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.Writer;

import javax.xml.parsers.ParserConfigurationException;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.font.PDFont;
import org.apache.pdfbox.pdmodel.font.PDType1Font;
import org.apache.pdfbox.pdmodel.graphics.image.PDImageXObject;
import org.fit.pdfdom.PDFDomTree;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import com.itextpdf.tool.xml.XMLWorkerHelper;

public class PdfMain {
	public static void main(String[] args) throws Exception {
		CreateTextPdf();
	}

	// Text를 Pdf로 변환
	@SuppressWarnings("deprecation")
	public static void CreateTextPdf() throws IOException {
		PDDocument pdf = new PDDocument();
		PDPage page = new PDPage();
		pdf.addPage(page);
		PDFont font = PDType1Font.COURIER_BOLD;
		float mat = 7;
		PDImageXObject pdImage = PDImageXObject.createFromFile("BLABLABLA.jpg", pdf);

		@SuppressWarnings("deprecation")
		PDPageContentStream contentStream = new PDPageContentStream(pdf, page,true,true);
		PdfPTable table = new PdfPTable(3);
		contentStream.beginText();
		contentStream.setFont(font, 12);
		contentStream.moveTextPositionByAmount(100, 700);
		contentStream.drawString("BLABLABLA");
		contentStream.endText();
		table.addCell("BLABLABLA");
		contentStream.drawImage(pdImage,60,60);
		contentStream.close();
		pdf.save("BLABLABLA.pdf");
		pdf.close();
	}

	// Pdf를 Html로 변환
	public static void PdftoHtml() throws IOException, ParserConfigurationException {
		PDDocument htmltoPDf = PDDocument.load(new File("BLABLABLA.pdf"));
		Writer output = new PrintWriter("BLABLABLA.html", "utf-8");
		new PDFDomTree().writeText(htmltoPDf, output);
		output.close();
	}

	// Html을 Pdf로 변환
	public static void HtmlToPdf() throws DocumentException, IOException {
		Document document = new Document();
		PdfWriter writer = PdfWriter.getInstance(document, new FileOutputStream("BLABLABLA.pdf"));
		document.open();
		XMLWorkerHelper.getInstance().parseXHtml(writer, document, new FileInputStream("BLABLABLA.html"));
		document.close();
	}
}
