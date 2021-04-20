package br.edu.ifba.inf011.atividade1_patterns.controllers;

import java.io.File;

import org.apache.commons.io.FilenameUtils;

public class FileController {
	
	public static String getExtension(String filePath) {
		File file = new File(filePath);
		return FilenameUtils.getExtension(file.getPath());
	}	
}
