package inf011.UI;

import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import java.io.File;
import java.io.IOException;

import javax.swing.JScrollPane;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.text.PDFTextStripper;
import org.apache.pdfbox.text.PDFTextStripperByArea;

import inf011.interfaces.IDocumentAdaptee;

import javax.swing.JTextPane;

public class TextDocumentEditor extends JFrame {
	private JPanel panel;
	private File file;
	private IDocumentAdaptee docAdaptee;
	
	public TextDocumentEditor(File file) {
		this.file = file;
		initComponent();
	}

	private void initComponent() {
		this.panel = new JPanel();
		panel.setBounds(0, 0, 434, 261);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		getContentPane().setLayout(null);
		getContentPane().add(panel);
		JScrollPane scrollPane = new JScrollPane();
		panel.add(scrollPane);
		
	}
	
	private String[] readPDF() throws Exception {
		String[] text = null;
        try (PDDocument document = PDDocument.load(file)) {
            document.getClass();
            if (!document.isEncrypted()) {
                PDFTextStripperByArea stripper = new PDFTextStripperByArea();
                stripper.setSortByPosition(true);
                PDFTextStripper tStripper = new PDFTextStripper();
                String pdfFileInText = tStripper.getText(document);
                text = pdfFileInText.split("\\r?\\n");   
            }
            return text;
        }
	}
}
