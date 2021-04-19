package br.edu.ifba.inf011.atividade1_patterns.interfaces;

import java.io.File;

import javax.swing.JFrame;

public interface ITextEditor {
	
	public JFrame createTextEditor(File file);
	
	public boolean saveFile(File file);

}
