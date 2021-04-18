package br.edu.ifba.inf011.atividade1_patterns.factory1;

import java.io.File;
import java.io.IOException;

import javax.swing.JFrame;

import br.edu.ifba.inf011.atividade1_patterns.interfaces.IBuilder;
import br.edu.ifba.inf011.atividade1_patterns.interfaces.IFactory;
import br.edu.ifba.inf011.atividade1_patterns.ui.TextEditor;

public class FactoryCpp implements IFactory {

	@Override
	public IBuilder criarCompilador() {
		return CppCompiler.getInstance();
	}

	@Override
	public JFrame criarEditor(File file, String syntax) {
		return new TextEditor(file, syntax);
	}

}
