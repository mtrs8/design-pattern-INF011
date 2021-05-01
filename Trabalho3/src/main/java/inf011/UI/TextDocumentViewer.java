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
	private JScrollPane scrollPane_1;
	
	public TextDocumentViewer(File file) throws Exception {
		this.file = file;
		initComponent();
	}

	private void initComponent() throws Exception {
		//Objetos
		this.panel = new JPanel();
		this.scrollPane = new JScrollPane();
		this.docAdaptee = new ReadPDF(); // PluginName
		
		//Configurações da tela
		getContentPane().setLayout(null);
		getContentPane().add(panel);
		scrollPane = new JScrollPane(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);		
		scrollPane.setBounds(0, 261, 432, -261);
		getContentPane().add(scrollPane);
		setVisible(true);
		this.textPane = new JTextPane();
		textPane.setBounds(0, 0, 432, 261);
		getContentPane().add(textPane);
		//getContentPane().add(textPane);
		textPane.setFont(new Font("DejaVu Sans", Font.PLAIN, 14));
		textPane.setText(docAdaptee.openFile2(file));
		docAdaptee.openFile3(file);
		System.out.println(docAdaptee.openFile2(file));
	}
	
}
