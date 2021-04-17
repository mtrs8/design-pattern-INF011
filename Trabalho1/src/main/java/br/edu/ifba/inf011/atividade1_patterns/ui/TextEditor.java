package br.edu.ifba.inf011.atividade1_patterns.ui;

import java.awt.BorderLayout;
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
import javax.swing.border.MatteBorder;
import java.awt.Color;
import javax.swing.border.BevelBorder;
import javax.swing.border.CompoundBorder;
import javax.swing.UIManager;

public class TextEditor extends JFrame {
	
	private JPanel cp;
	private RSyntaxTextArea textArea;
	private RTextScrollPane sp;
	private File file;
	private FileReader reader; 
	private BufferedReader br;
	
	public TextEditor(File file, String syntax){
		this.file = file;
		cp = new JPanel(new BorderLayout());
		cp.setBorder(new CompoundBorder(UIManager.getBorder("Button.border"), null));
	    setDefaultCloseOperation(EXIT_ON_CLOSE);
	    setTitle("Code Editor");
	    this.textArea = new RSyntaxTextArea(20, 60);
	    textArea.setCodeFoldingEnabled(true);
	    textArea.setSyntaxEditingStyle(syntax);
	    sp = new RTextScrollPane(textArea);
	    cp.add(sp);
	    this.setContentPane(cp);
	    setLocationRelativeTo(null);
	    textArea.setText(loadFile(file));
	    textArea.setVisible(true);
	    pack();
	}
	
	
	private String loadFile(File file) {
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
	
	
	public boolean saveFile() {
		try{
			 String str = this.textArea.getText();
			 BufferedWriter writer = new BufferedWriter(new FileWriter(this.file));
			 writer.write(str);		 
			 writer.close();
			 return true;
		 }catch (Exception e1) {
			 JOptionPane.showMessageDialog(null, e1.getMessage());
		}
		return false;
	}
	
}
