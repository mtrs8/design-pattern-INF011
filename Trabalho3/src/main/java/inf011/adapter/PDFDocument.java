package inf011.adapter;

import java.io.File;

import javax.swing.JFrame;

import inf011.UI.TextDocumentViewer;
import inf011.adaptee.ReadPDF;
import inf011.interfaces.IDocumentAdaptee;
import inf011.interfaces.IDocumentAdapter;

public class PDFDocument implements IDocumentAdapter {
	
	public static IDocumentAdaptee documentAdaptee;

	@Override
	public JFrame getEditor(File file) throws Exception {
		return new TextDocumentViewer(file);
	}

	@Override
	public void setAdaptee(String extensionType) {
		if(extensionType.equalsIgnoreCase("pdf"))
			documentAdaptee = new ReadPDF();
	}
	
}
