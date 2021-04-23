package br.edu.ifba.inf011.atividade1_patterns.factory2;

import java.io.File;

import javax.swing.JFrame;

import br.edu.ifba.inf011.atividade1_patterns.interfaces.IBuilder;
import br.edu.ifba.inf011.atividade1_patterns.interfaces.IFactory;

public class FactoryJava implements IFactory {
	private static FactoryJava instance = null;

	@Override
	public IBuilder criarCompilador() {
		return new JavaCompiler();
	}

	
	public static IFactory getInstance() {
		return instance == null ? new FactoryJava() : instance;
	}


	@Override
	public JFrame createTextEditor(File file) {
		return new JavaTextEditor(file);
	}

	
}
