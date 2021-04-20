package br.edu.ifba.inf011.atividade1_patterns.ui;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.filechooser.FileNameExtensionFilter;

import org.apache.commons.io.FilenameUtils;
import org.fife.ui.rsyntaxtextarea.SyntaxConstants;

import br.edu.ifba.inf011.atividade1_patterns.controllers.PluginController;
import br.edu.ifba.inf011.atividade1_patterns.factory1.FactoryCpp;
import br.edu.ifba.inf011.atividade1_patterns.factory2.FactoryJava;
import br.edu.ifba.inf011.atividade1_patterns.factory2.JavaCompiler;
import br.edu.ifba.inf011.atividade1_patterns.factory2.JavaTextEditor;
import br.edu.ifba.inf011.atividade1_patterns.interfaces.IBuilder;
import br.edu.ifba.inf011.atividade1_patterns.interfaces.IFactory;
import br.edu.ifba.inf011.atividade1_patterns.interfaces.IPlugin;
import br.edu.ifba.inf011.atividade1_patterns.interfaces.IPluginController;
import br.edu.ifba.inf011.atividade1_patterns.interfaces.ITextEditor;

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
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.List;
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
	private JButton btnSaveFile;
	private JButton btnCompile;
	private JFileChooser jfc; 
	private ITextEditor editor;
	private IPluginController pluginController;
	private List<IPlugin> plugins;
	
	
	public ViewMenu() throws Exception {
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
		
		this.plugins = new ArrayList();
		this.pluginController = new PluginController();
		btnOpenFile.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				btnOpenFile();
			}
		});
		btnSaveFile.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				btnSaveFile();
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
	}
	
	public void createFactory() {
		try {
			pluginController.initPlugin();
			plugins = pluginController.getLoadedPlugins();
			for(IPlugin p : plugins)
				System.out.println(p);
			
			Class metaClass =  Class.forName("Factory" + getExtension());
			Method[] methods = metaClass.getDeclaredMethods();
			for(Method m : methods)
				System.out.println(m.getName() + " - " + Modifier.isStatic(m.getModifiers()));
			Method getInstanceMethod = metaClass.getDeclaredMethod("getInstance", Object.class);
			Object value = getInstanceMethod.invoke(metaClass.newInstance(), "");
			
		} catch(Exception e) {
			JOptionPane.showMessageDialog(null, "Erro ao criar fábricas.\nTente novamente!");
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
			editor = (ITextEditor) editor.createTextEditor(file);		
	}
	
	private void btnSaveFile() {
		try {
			if(editor.saveFile(file))
				JOptionPane.showMessageDialog(null, "Saved successfully");
		} catch(Exception e) {
			JOptionPane.showMessageDialog(null, "Error saving file");			
		}
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
		if(file !=null)
			return FilenameUtils.getExtension(jfc.getSelectedFile().getPath());
		return null;
	}
		
}
