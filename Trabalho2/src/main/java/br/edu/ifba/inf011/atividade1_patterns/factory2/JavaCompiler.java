package br.edu.ifba.inf011.atividade1_patterns.factory2;

import java.io.File;
import java.io.IOException;

import br.edu.ifba.inf011.atividade1_patterns.interfaces.IBuilder;


public class JavaCompiler implements IBuilder{

	@Override
	public void compile(File file) {
		Process process = null;

		String compile = "javac " + file.getPath();
		//String execute = "javac " + file.getPath();
		
		try {
			process = Runtime.getRuntime().exec(compile);
		} catch (IOException e1) {
			e1.printStackTrace();
		}
    	//Process process2 = Runtime.getRuntime().exec(execute);
    	try {
			process.waitFor();
		} catch (InterruptedException e) {
			e.printStackTrace(); 
		}
    	System.out.println("Arquivo java compilado: status(" + process.exitValue()+")");
		
	}


}
