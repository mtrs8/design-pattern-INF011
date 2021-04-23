package br.edu.ifba.inf011.atividade1_patterns.factory1;
import br.edu.ifba.inf011.atividade1_patterns.interfaces.IBuilder;
import br.edu.ifba.inf011.atividade1_patterns.interfaces.IFactory;

public class FactoryCpp implements IFactory {
	private static FactoryCpp instance = null;
	
	private FactoryCpp() {}

	@Override
	public IBuilder criarCompilador() {
		return CppCompiler.getInstance();
	}
	
	public static FactoryCpp getInstance() {
		return instance == null ? new FactoryCpp() : instance;
	}

}
