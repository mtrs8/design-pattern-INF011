package inf011.UI;

import javax.swing.JFrame;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.text.PDFTextStripper;
import org.apache.pdfbox.text.PDFTextStripperByArea;

import inf011.interfaces.IDocumentAdapter;
import inf011.models.PDFDocument;

import java.awt.Desktop;
import java.awt.HeadlessException;
import java.io.File;
import java.io.IOException;
import javax.swing.JLabel;
import java.awt.BorderLayout;
import javax.swing.JTextField;
import javax.swing.JTextArea;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.filechooser.FileNameExtensionFilter;

import java.awt.Font;
import javax.swing.JButton;
import javax.swing.JFileChooser;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.CardLayout;
import java.awt.GridLayout;
import javax.swing.JMenuBar;
import javax.swing.JPanel;
import org.eclipse.wb.swing.FocusTraversalOnArray;
import java.awt.Component;
import javax.swing.BoxLayout;
import javax.swing.JToolBar;
import java.awt.Color;
import javax.swing.DropMode;

public class ViewMenu extends JFrame {
	
	private File file;
	private PDDocument document;
	private IDocumentAdapter pdfDoc;
	private JPanel contentPane;
	private JTextField txtShowNameFile;
	private JLabel lblMenu;
	private JButton btnOpenFile;
	private JButton btnGetEditor;
	private JFileChooser jfc;
	
	public ViewMenu() {
		initComponent();
	}
	
	public void initComponent() {
		setTitle("PDF Viewer");
		setBackground(Color.BLACK);
		setBounds(300, 300, 547, 433);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		this.jfc = new JFileChooser();
		FileNameExtensionFilter filter = new FileNameExtensionFilter("pdf", "docx", "txt");
		jfc.setFileFilter(filter);
		this.lblMenu = new JLabel("Menu Principal");
		lblMenu.setBounds(0, 26, 516, 48);
		lblMenu.setFont(new Font("Liberation Serif", Font.BOLD, 24));
		lblMenu.setHorizontalAlignment(SwingConstants.CENTER);
		contentPane.add(lblMenu);
		btnOpenFile = new JButton("Escolher PDF");
		btnGetEditor = new JButton("Visualizar PDF");
		btnOpenFile.setBounds(160, 213, 212, 36);
		contentPane.add(btnOpenFile);
		txtShowNameFile = new JTextField();
		txtShowNameFile.setBounds(160, 152, 212, 29);
		contentPane.add(txtShowNameFile);
		txtShowNameFile.setColumns(10);
		btnGetEditor.setBounds(160, 272, 212, 36);
		contentPane.add(btnGetEditor);
		
		btnOpenFile.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				buscarArquivo();
			}
		});
		
		btnGetEditor.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				abrirEditor();
			}
		});
		
	}
	
	private void buscarArquivo() {
		int value = jfc.showOpenDialog(getParent());
		if(value == JFileChooser.APPROVE_OPTION) {
			this.file = jfc.getSelectedFile();
			this.txtShowNameFile.setText(file.getName());
			this.pdfDoc = new PDFDocument();
		}
	}
	
	private void abrirEditor() {
		//pdfDoc.open(file);
	}
}
