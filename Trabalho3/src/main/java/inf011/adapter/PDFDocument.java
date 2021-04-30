package inf011.adapter;

import java.io.File;

import javax.swing.JFrame;

import inf011.UI.TextDocumentEditor;
import inf011.adaptee.ReadPDF;
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
