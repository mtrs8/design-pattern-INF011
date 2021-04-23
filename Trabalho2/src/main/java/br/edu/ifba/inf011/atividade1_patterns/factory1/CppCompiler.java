package br.edu.ifba.inf011.atividade1_patterns.factory1;

import java.io.File;
import java.io.IOException;

import br.edu.ifba.inf011.atividade1_patterns.interfaces.IBuilder;

public class CppCompiler implements IBuilder {
	private static CppCompiler instance = null;
	
	private CppCompiler() {}

	@Override
	public void compile(File file) throws IOException, InterruptedException {
			Process process = null;
			String compile = "g++ -o " + file.getPath();
			//String execute = "g++ -o " + file.getPath();
			
			try {
				process = Runtime.getRuntime().exec(compile);
			} catch (IOException e1) {
				throw new IOException(e1);
			}
	    	//Process process2 = Runtime.getRuntime().exec(execute);
	    	try {
				process.waitFor();
			} catch (InterruptedException e) {
				throw new InterruptedException();
			}
	    	System.out.println("Arquivo CPP compilado: status(" + process.exitValue()+")");
		
	}
	
	public static CppCompiler getInstance() {
		return instance == null ? new CppCompiler() : instance;
	}

}
