package br.edu.ifba.inf011.atividade1_patterns.ui;

import java.awt.Component;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
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

import br.edu.ifba.inf011.atividade1_patterns.controllers.PluginController;
import br.edu.ifba.inf011.atividade1_patterns.factory1.CppTextEditor;
import br.edu.ifba.inf011.atividade1_patterns.factory1.FactoryCpp;
import br.edu.ifba.inf011.atividade1_patterns.factory2.FactoryJava;
import br.edu.ifba.inf011.atividade1_patterns.factory2.JavaTextEditor;
import br.edu.ifba.inf011.atividade1_patterns.interfaces.IBuilder;
import br.edu.ifba.inf011.atividade1_patterns.interfaces.IFactory;
import br.edu.ifba.inf011.atividade1_patterns.interfaces.IPlugin;

@SuppressWarnings("unchecked")
public class ViewMenu extends JFrame {

	private JPanel contentPane;
	private String extensao;
	private JTextField textField = null;
	private File file = null;
	private IBuilder build;
	private IFactory fabrica;
	private JLabel lblTitulo;
	private JButton btnOpenFile;
	private JButton btnCompile;
	private JFileChooser jfc; 
	private JFrame textArea;
	private IPlugin pluginController;
	
	
	public ViewMenu() throws Exception {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(300, 300, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		
		lblTitulo = new JLabel("Menu Principal");
		lblTitulo.setFont(new Font("Tahoma", Font.BOLD, 17));
		lblTitulo.setHorizontalAlignment(SwingConstants.CENTER);
		btnOpenFile = new JButton("Open File");
		btnCompile = new JButton(" Compile File");
		
		this.pluginController = new PluginController();
		btnOpenFile.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				btnOpenFile();
			}
		});
	
		btnCompile.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				try {
					btnCompile();
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});
		
		
		btnOpenFile.setAlignmentX(Component.RIGHT_ALIGNMENT);
		this.jfc = new JFileChooser();
		FileNameExtensionFilter filter = new FileNameExtensionFilter("codefiles", "java", "cpp");
		this.jfc.addChoosableFileFilter(filter);
		this.jfc.setFileFilter(filter);
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGap(25)
							.addComponent(btnOpenFile, GroupLayout.PREFERRED_SIZE, 166, GroupLayout.PREFERRED_SIZE)
							.addGap(18)
							.addComponent(btnCompile, GroupLayout.DEFAULT_SIZE, 175, Short.MAX_VALUE))
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGap(88)
							.addComponent(lblTitulo, GroupLayout.PREFERRED_SIZE, 243, GroupLayout.PREFERRED_SIZE)))
					.addContainerGap(40, GroupLayout.PREFERRED_SIZE))
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addComponent(lblTitulo, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
					.addGap(81)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(btnOpenFile, GroupLayout.PREFERRED_SIZE, 59, GroupLayout.PREFERRED_SIZE)
						.addComponent(btnCompile, GroupLayout.PREFERRED_SIZE, 60, GroupLayout.PREFERRED_SIZE))
					.addGap(102))
		);
		contentPane.setLayout(gl_contentPane);
	}
	
	public void createFactory() {
		 // Utilização de plugins na instanciação das fábricas
		//try {
			/*fabrica = pluginController.initPlugin();
			textArea = fabrica.createTextEditor(file);
			textArea.setVisible(true);
		} catch(Exception e) {
			JOptionPane.showMessageDialog(null, "Erro ao criar fábricas.\nTente novamente!");
		}
			*/
		 
		// Instância as fábricas de forma convencional
		if(getExtension().equalsIgnoreCase("java")){
			fabrica = FactoryJava.getInstance();//Singleton
			textArea = new JavaTextEditor(file);
			textArea = fabrica.createTextEditor(file);
			textArea.setVisible(true);
		} else if(getExtension().equalsIgnoreCase("cpp")){
			fabrica = FactoryCpp.getInstance();//Singleton
			textArea =  new CppTextEditor(file);
			textArea = fabrica.createTextEditor(file);
			textArea.setVisible(true);
		}
		
	}
	
	private void btnOpenFile() {
			try {					
				jfc.setFileSelectionMode(JFileChooser.FILES_ONLY);
				int i = jfc.showOpenDialog(null);
				if(i==1) textField.setText("");
				else file = jfc.getSelectedFile();
			} catch (Exception e1) {
				JOptionPane.showMessageDialog(null, "Please! Choose a file!");
			}
			createFactory();		
	}
	
	private void btnCompile() {
			if(file == null){
				JOptionPane.showMessageDialog(null, "Please! Choose a valid file!");
			} else {
				try{
					build = fabrica.criarCompilador();
					build.compile(file);
					JOptionPane.showMessageDialog(null, "File compiled successfully!!");
				} catch (Exception e1) {
					JOptionPane.showMessageDialog(null, "Error Compile!!");
				}				
			}
	}
	
	public String getExtension() {
		if(file != null)
			return FilenameUtils.getExtension(jfc.getSelectedFile().getPath());
		return null;
	}
		
}
