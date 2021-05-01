package inf011.adaptee;

import java.awt.Desktop;
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
                String text = tStripper.getText(document); 
                //System.out.println(text);
                return text;
            }
          return "PDF not found!";
	
        }
	}

	@Override
	public void openFile3(File file) {
		if (file.exists()) {
            long startTime = System.currentTimeMillis();
            try {
				Desktop.getDesktop().open(file);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
            long endTime = System.currentTimeMillis();
            System.out.println("Tempo total -> "+ file.getName() +" in "+ (endTime - startTime) +" ms");              
        } else {
            System.out.println("File not exits -> "+ file.getAbsolutePath());
        }
	}
     
}
