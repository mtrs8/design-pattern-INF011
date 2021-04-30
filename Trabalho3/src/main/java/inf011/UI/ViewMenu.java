package inf011.UI;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.filechooser.FileNameExtensionFilter;

import org.apache.commons.io.FilenameUtils;

import inf011.adaptee.ReadPDF;
import inf011.adapter.PDFDocument;
import inf011.interfaces.IDocumentAdaptee;
import inf011.interfaces.IDocumentAdapter;

public class ViewMenu extends JFrame {
	
	private static final long serialVersionUID = 1L;
	
	private File file;
	private IDocumentAdapter docAdapter;
	private IDocumentAdaptee docAdaptee;
	private JPanel contentPane;
	private JTextField txtShowNameFile;
	private JLabel lblMenu;
	private JButton btnOpenFile;
	private JButton btnGetEditor;
	private JFileChooser jfc;
	private JFrame pdfViewer;
	
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
		//FileNameExtensionFilter filter = new FileNameExtensionFilter("pdf", "docx", "txt");
		//jfc.setFileFilter(filter);
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
		this.docAdapter = new PDFDocument();
		this.pdfViewer = new JFrame();
		
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
			docAdapter.setAdaptee(FilenameUtils.getExtension(file.getName()));
		}
	}
	
	private void abrirEditor() {
		try {
			this.pdfViewer = docAdapter.getEditor(file);
			pdfViewer.setVisible(true);			
		} catch(Exception e) {
			JOptionPane.showMessageDialog(null, "File not Found!!");
		}
	}
}
