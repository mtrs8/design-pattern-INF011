package br.edu.ifba.inf011.atividade1_patterns.factory2;

import java.io.File;
import java.io.IOException;

import br.edu.ifba.inf011.atividade1_patterns.interfaces.IBuilder;


public class JavaCompiler implements IBuilder{

	@Override
	public void compile(File file) throws IOException, InterruptedException {
		Process process = null;

		String compile = "javac " + file.getPath();
		//String execute = "javac " + file.getPath();
		
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
    	System.out.println("Arquivo java compilado: status(" + process.exitValue()+")");
		
	}


}
