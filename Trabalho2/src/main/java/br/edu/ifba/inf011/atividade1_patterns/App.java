package br.edu.ifba.inf011.atividade1_patterns;

import java.awt.EventQueue;
import java.io.File;

import org.apache.commons.io.FilenameUtils;

import br.edu.ifba.inf011.atividade1_patterns.ui.ViewMenu;


public class App{
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ViewMenu frame = new ViewMenu();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
}
