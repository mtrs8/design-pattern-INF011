package br.edu.ifba.inf011.atividade1_patterns.factory2;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import org.fife.ui.rsyntaxtextarea.RSyntaxTextArea;
import org.fife.ui.rsyntaxtextarea.SyntaxConstants;
import org.fife.ui.rtextarea.RTextScrollPane;

import javax.swing.border.CompoundBorder;
import javax.swing.UIManager;
import javax.swing.JButton;

public class JavaTextEditor extends JFrame {
	
	private static final long serialVersionUID = 1L;
	
	private JPanel cp;
	private RSyntaxTextArea textArea;
	private RTextScrollPane sp;
	private File file;
	private FileReader reader; 
	private BufferedReader br;
	private JButton btnSaveFile;
	
	public JavaTextEditor(File file){
		this.file = file;
		cp = new JPanel(new BorderLayout());
		cp.setBorder(new CompoundBorder(UIManager.getBorder("Button.border"), null));
	    setDefaultCloseOperation(EXIT_ON_CLOSE);
	    setTitle("Code Editor");
	    this.textArea = new RSyntaxTextArea(20, 60);
	    textArea.setCodeFoldingEnabled(true);
	    textArea.setSyntaxEditingStyle(SyntaxConstants.SYNTAX_STYLE_JAVA);
	    sp = new RTextScrollPane(textArea);
	    cp.add(sp);
	    this.setContentPane(cp);
	    
	    btnSaveFile = new JButton("Save");
	    btnSaveFile.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				try {
					saveFile();
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});
	    
	    cp.add(btnSaveFile, BorderLayout.SOUTH);
	    setLocationRelativeTo(null);
	    textArea.setText(loadFile());
	    textArea.setVisible(true);
	    pack();
	}
	
	
	private String loadFile() {
		String br2 = null;
		try {
			reader = new FileReader(file.getPath());
			br = new BufferedReader(reader);
			
			while((br2 = br.readLine()) != null) {
				this.textArea.append(br2 + "\n");
			}
		} catch(Exception e) {
			e.printStackTrace();
		}
		finally {
			try {
				this.br.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return this.textArea.getText();
	}

	private void saveFile(){
		try{
			 String str = this.textArea.getText();
			 BufferedWriter writer = new BufferedWriter(new FileWriter(this.file.getPath()));
			 writer.write(str);		 
			 writer.close();
			 JOptionPane.showMessageDialog(null, "File successfully saved!");
		 } catch (Exception e1) {
			 JOptionPane.showMessageDialog(null, "Error saving file!");
		}
	}
	
}
