package br.edu.ifba.inf011.atividade1_patterns.ui;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.filechooser.FileNameExtensionFilter;

import org.apache.commons.io.FilenameUtils;
import org.fife.ui.rsyntaxtextarea.SyntaxConstants;

import br.edu.ifba.inf011.atividade1_patterns.factory1.FactoryCpp;
import br.edu.ifba.inf011.atividade1_patterns.factory2.FactoryJava;
import br.edu.ifba.inf011.atividade1_patterns.factory2.JavaCompiler;
import br.edu.ifba.inf011.atividade1_patterns.interfaces.IBuilder;
import br.edu.ifba.inf011.atividade1_patterns.interfaces.IFactory;
import br.edu.ifba.inf011.atividade1_patterns.plugins.interfaces.IPlugin;

import javax.swing.BoxLayout;
import javax.swing.JEditorPane;
import javax.swing.JFileChooser;
import javax.swing.JScrollBar;
import javax.swing.JTextField;

import java.awt.Component;
import javax.swing.JButton;
import javax.swing.SwingConstants;
import java.awt.event.ActionListener;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.awt.event.ActionEvent;
import java.awt.FlowLayout;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.awt.GridLayout;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;
import java.awt.Font;

public class ViewMenu extends JFrame implements ActionListener {

	private JPanel contentPane;
	private String extensao;
	private JTextField textField = null;
	private File file = null;
	private IBuilder build;
	private IFactory fabrica;
	private JLabel lblTitulo;
	private JButton btnOpenFile;
	private JButton btnSaveFile;
	private JButton btnCompile;
	//private JFrame jframe;
	private JFileChooser jfc; 
	private TextEditor editor;
	private String syntaxJava = SyntaxConstants.SYNTAX_STYLE_JAVA;
	private String syntaxCpp = SyntaxConstants.SYNTAX_STYLE_CPLUSPLUS;
	
	
	public ViewMenu() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(300, 300, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		
		lblTitulo = new JLabel("Menu Principal");
		lblTitulo.setFont(new Font("Tahoma", Font.BOLD, 17));
		lblTitulo.setHorizontalAlignment(SwingConstants.CENTER);
		
		btnSaveFile = new JButton("Save File");
		btnOpenFile = new JButton("Open File");
		btnCompile = new JButton(" Compile File");
		btnOpenFile.addActionListener(this);
		btnSaveFile.addActionListener(this);
		btnCompile.addActionListener(this);
		
		
		btnOpenFile.setAlignmentX(Component.RIGHT_ALIGNMENT);
		this.jfc = new JFileChooser();
		FileNameExtensionFilter filter = new FileNameExtensionFilter("codefiles", "java", "cpp");
		this.jfc.addChoosableFileFilter(filter);
		this.jfc.setFileFilter(filter);
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGap(23)
					.addComponent(btnSaveFile, GroupLayout.DEFAULT_SIZE, 166, Short.MAX_VALUE)
					.addGap(48)
					.addComponent(btnCompile, GroupLayout.DEFAULT_SIZE, 175, Short.MAX_VALUE)
					.addContainerGap(12, Short.MAX_VALUE))
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGap(88)
					.addComponent(lblTitulo, GroupLayout.PREFERRED_SIZE, 243, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(93, Short.MAX_VALUE))
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGap(126)
					.addComponent(btnOpenFile, GroupLayout.PREFERRED_SIZE, 166, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(132, Short.MAX_VALUE))
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addComponent(lblTitulo, GroupLayout.DEFAULT_SIZE, 25, Short.MAX_VALUE)
					.addGap(62)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(btnSaveFile, GroupLayout.PREFERRED_SIZE, 44, GroupLayout.PREFERRED_SIZE)
						.addComponent(btnCompile, GroupLayout.PREFERRED_SIZE, 39, GroupLayout.PREFERRED_SIZE))
					.addGap(34)
					.addComponent(btnOpenFile, GroupLayout.DEFAULT_SIZE, 42, Short.MAX_VALUE)
					.addGap(44))
		);
		contentPane.setLayout(gl_contentPane);
		//contentPane.disable();
	}
		
		public void actionPerformed(ActionEvent e) {
			if(e.getSource() == btnOpenFile) {
				try {					
					jfc.setFileSelectionMode(JFileChooser.FILES_ONLY);
					int i = jfc.showOpenDialog(null);
					if(i==1) {
						textField.setText("");
					} else {
						file = jfc.getSelectedFile();
					}
					
				} catch (Exception e1) {
					JOptionPane.showMessageDialog(null, "Please! Choose a file!");
				}
				extensao = FilenameUtils.getExtension(jfc.getSelectedFile().getAbsolutePath());
				
				String strTemp = Character.toUpperCase(extensao.charAt(0)) + extensao.substring(1);
				System.out.print(strTemp);
				try {
					fabrica = (IFactory) Class.forName("Factory" + strTemp).newInstance();
				} catch (InstantiationException | IllegalAccessException | ClassNotFoundException e1) {
					e1.printStackTrace();
				}
			
				/*if(extensao.equalsIgnoreCase("java")){
					fabrica = new FactoryJava();
					editor = (TextEditor) fabrica.criarEditor(file, syntaxJava);
					editor.setVisible(true);
				} else if(extensao.equalsIgnoreCase("cpp")){
					fabrica = FactoryCpp.getInstance();
					editor = (TextEditor) fabrica.criarEditor(file, syntaxCpp);
					editor.setVisible(true);
				}*/
			}
			if(e.getSource() == btnCompile) {
				try{
					build = fabrica.criarCompilador();
					build.compile(file);
					JOptionPane.showMessageDialog(null, "File compiled successfully!!");
				} catch (Exception e1) {
					JOptionPane.showMessageDialog(null, "Error Compile!!");
				}
			} else if(file == null){
				JOptionPane.showMessageDialog(null, "Please! Choose a valid file!");
				new ViewMenu();
			}
			
			if(e.getSource() == btnSaveFile) {
				editor.saveFile();
				JOptionPane.showMessageDialog(null, "Saved successfully");
			}
			
		}
		
}
