package br.edu.ifba.inf011.atividade1_patterns.factory2;

import java.io.File;

import javax.swing.JFrame;

import br.edu.ifba.inf011.atividade1_patterns.interfaces.IBuilder;
import br.edu.ifba.inf011.atividade1_patterns.interfaces.IFactory;
import br.edu.ifba.inf011.atividade1_patterns.ui.TextEditor;

public class FactoryJava implements IFactory {

	@Override
	public IBuilder criarCompilador() {
		return new JavaCompiler();
	}

	@Override
	public JFrame criarEditor(File file, String syntax) {
		return new TextEditor(file, syntax);
	}
	

	
}
