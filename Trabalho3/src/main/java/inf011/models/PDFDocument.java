package inf011.models;

import java.awt.Desktop;
import java.io.File;
import java.io.IOException;

import javax.swing.JFrame;

import org.apache.pdfbox.pdmodel.PDDocument;

import inf011.UI.TextDocumentEditor;
import inf011.interfaces.IDocument;

public class PDFDocument implements IDocument {
	private PDDocument doc;


	@Override
	public void open(File file) {
		if (file.exists()) {
            long startTime = System.currentTimeMillis();
            try {
				Desktop.getDesktop().open(file);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
            long endTime = System.currentTimeMillis();
            System.out.println("Total time taken to open file -> "+ file.getName() +" in "+ (endTime - startTime) +" ms");              
        } else {
            System.out.println("File not exits -> "+ file.getAbsolutePath());
        }
	}

	@Override
	public JFrame getEditor(File file) {
		return new TextDocumentEditor(file);
	}
	
}
