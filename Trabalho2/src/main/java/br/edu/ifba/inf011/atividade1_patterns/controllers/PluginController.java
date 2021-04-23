package br.edu.ifba.inf011.atividade1_patterns.controllers;

import java.io.File;
import java.lang.reflect.Constructor;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.swing.JFileChooser;
import javax.swing.JTextField;
import javax.swing.filechooser.FileNameExtensionFilter;

import br.edu.ifba.inf011.atividade1_patterns.interfaces.IFactory;
import br.edu.ifba.inf011.atividade1_patterns.interfaces.IPlugin;

public class PluginController implements IPlugin {
	
	private IFactory fabrica;
	
	@Override
	public IFactory initPlugin() throws Exception {
		try {
			/*Caminho relativo dos plugins:
			 * 	Como utilizo Windows, tive que passar o caminho desde a raiz do projeto.
			 *  O ./plugins não funcionou para mim.   
			 */
			String filePath = "./src/main/java/br/edu/ifba/inf011/atividade1_patterns/plugins/"; 
			File diretorioAtual = new File(filePath);
			String[] plugins = diretorioAtual.list();
			int i;
			URL[] jars = new URL[plugins.length];
			System.out.println("Plugins instalados: " + plugins.length);
			List<URL> jarsList = Arrays.asList(jars);
			
			URLClassLoader ulc = new URLClassLoader(jars);
			for(i=0; i<plugins.length; i++) {
				String pluginName = plugins[i].split("\\.")[0];
				Class<?> pluginClass = Class.forName(pluginName);
				Method pluginMethod = pluginClass.getDeclaredMethod("getInstance");
				fabrica = (IFactory) pluginMethod.invoke(null);
			}
		} catch(ClassNotFoundException ex) {
			throw ex;
		} catch(Exception e){
			e.printStackTrace();
		}
		return fabrica;
	}
}
