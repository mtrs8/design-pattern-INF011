package inf011.UI;

import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import java.io.File;
import java.io.IOException;

import javax.swing.JScrollPane;
import javax.swing.JTextPane;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.text.PDFTextStripper;
import org.apache.pdfbox.text.PDFTextStripperByArea;

import inf011.adaptee.ReadPDF;
import inf011.interfaces.IDocumentAdaptee;
import java.awt.Font;

public class TextDocumentViewer extends JFrame {
	

	private static final long serialVersionUID = 1L;
	
	private JPanel panel;
	private File file;
	private IDocumentAdaptee docAdaptee;
	private JTextPane textPane;
	private JScrollPane scrollPane;
	
	public TextDocumentViewer(File file) throws Exception {
		this.file = file;
		initComponent();
	}

	private void initComponent() throws Exception {
		//Objetos
		this.panel = new JPanel();
		this.textPane = new JTextPane();
		this.scrollPane = new JScrollPane();
		this.docAdaptee = new ReadPDF();
		
		//Configurações da tela
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		getContentPane().setLayout(null);
		getContentPane().add(panel);
		//getContentPane().add(scrollPane);
		this.scrollPane.add(textPane);
		textPane.setFont(new Font("DejaVu Sans", Font.PLAIN, 14));
		textPane.setEditable(false);
		setVisible(true);
		textPane.setBounds(0, 0, 434, 261);
		getContentPane().add(textPane);
		textPane.setText(docAdaptee.openFile2(file));
		System.out.println(docAdaptee.openFile2(file));
	}
	
}
