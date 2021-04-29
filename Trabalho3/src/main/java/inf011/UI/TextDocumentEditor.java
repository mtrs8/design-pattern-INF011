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

public class TextDocumentEditor extends JFrame {
	private JPanel panel;
	private File file;
	
	public TextDocumentEditor(File file) {
		this.file = file;
		initComponent();
	}

	private void initComponent() {
		this.panel = new JPanel();
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		getContentPane().add(panel, BorderLayout.CENTER);
		//setContentPane(panel);
		JScrollPane scrollPane = new JScrollPane();
		panel.add(scrollPane);
		
	}
	
	private void readPDF() throws Exception {
		
        try (PDDocument document = PDDocument.load(file)) {
            document.getClass();
            if (!document.isEncrypted()) {
                PDFTextStripperByArea stripper = new PDFTextStripperByArea();
                stripper.setSortByPosition(true);
                PDFTextStripper tStripper = new PDFTextStripper();
                String pdfFileInText = tStripper.getText(document);
                String lines[] = pdfFileInText.split("\\r?\\n");
                for (String line : lines) {
                    System.out.println(line);
                }
            }
        }
	}
}
