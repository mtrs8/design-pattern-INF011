package inf011.adaptee;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.rendering.PDFRenderer;
import org.apache.pdfbox.text.PDFTextStripper;
import org.apache.pdfbox.text.PDFTextStripperByArea;

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

	@Override
	public String openFile2(File file) throws IOException {
		try (PDDocument document = PDDocument.load(file)) {
            document.getClass();
            if (!document.isEncrypted()) {
                PDFTextStripperByArea stripper = new PDFTextStripperByArea();
                stripper.setSortByPosition(true);
                PDFTextStripper tStripper = new PDFTextStripper();
                return tStripper.getText(document);
            }
          return "PDF not found!";
	
        }
	}
     
}
