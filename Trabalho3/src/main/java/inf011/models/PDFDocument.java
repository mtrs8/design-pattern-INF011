package inf011.models;

import java.awt.Desktop;
import java.io.File;
import java.io.IOException;

import javax.swing.JFrame;

import org.apache.pdfbox.pdmodel.PDDocument;

import inf011.UI.TextDocumentEditor;
import inf011.interfaces.IDocumentAdaptee;
import inf011.interfaces.IDocumentAdapter;

public class PDFDocument implements IDocumentAdapter {
	
	private IDocumentAdaptee docAdaptee;

	@Override
	public JFrame getEditor(File file) {
		return new TextDocumentEditor(file);
	}

	@Override
	public void setAdaptee(String extensionType) {
		if(extensionType.equalsIgnoreCase("pdf"))
			docAdaptee = (IDocumentAdaptee) new ReadPDF();
		
	}
	
}
