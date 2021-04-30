package inf011.models;

import java.awt.image.BufferedImage;
import java.io.File;

import javax.imageio.ImageIO;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.rendering.PDFRenderer;

import inf011.interfaces.IDocumentAdaptee;

public class ReadPDF implements IDocumentAdaptee {

	@Override
	public void openFile(File file) {
		try {
			PDDocument document = PDDocument.load(file);
			String nameFile = file.getName();
			PDFRenderer renderer = new PDFRenderer(document);
			BufferedImage image = renderer.renderImage(0);
			File imagePdfPage1= new File("./image/" + nameFile + ".jpg");
			ImageIO.write(image, "JPEG", imagePdfPage1);
			Runtime.getRuntime().exec("rundll32 url.dll, FileProtocolHandler "+ imagePdfPage1);
			document.close();
		} catch (Exception e) {
			e.printStackTrace();
		}	
		
	}

}
