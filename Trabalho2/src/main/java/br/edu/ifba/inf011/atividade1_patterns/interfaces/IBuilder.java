package br.edu.ifba.inf011.atividade1_patterns.interfaces;

import java.io.File;
import java.io.IOException;

import br.edu.ifba.inf011.atividade1_patterns.factory1.FactoryCpp;

public interface IBuilder {
	
	public void compile(File file);
	
}
