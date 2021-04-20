package br.edu.ifba.inf011.atividade1_patterns.factory2;

import java.io.File;

import javax.swing.JFrame;

import br.edu.ifba.inf011.atividade1_patterns.interfaces.IBuilder;
import br.edu.ifba.inf011.atividade1_patterns.interfaces.IFactory;
import br.edu.ifba.inf011.atividade1_patterns.interfaces.ITextEditor;

public class FactoryJava implements IFactory {
	private static FactoryJava instance = null;
	//private ITextEditor editor;

	@Override
	public IBuilder criarCompilador() {
		return new JavaCompiler();
	}

	
	public static IFactory getInstance() {
		return instance == null ? new FactoryJava() : instance;
	}
	
}
