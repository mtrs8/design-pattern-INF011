package br.edu.ifba.inf011.atividade1_patterns.controllers;

import java.io.File;
import java.lang.reflect.Constructor;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.swing.JFileChooser;
import javax.swing.JTextField;
import javax.swing.filechooser.FileNameExtensionFilter;

import br.edu.ifba.inf011.atividade1_patterns.interfaces.IPlugin;
import br.edu.ifba.inf011.atividade1_patterns.interfaces.IPluginController;

public class PluginController implements IPlugin, IPluginController {
	
	private List<IPlugin> loadedPlugins = new ArrayList<IPlugin>();
		private File diretorioAtual;
		private JFileChooser jfc;
		private JTextField textField = null;
	@Override
	public boolean initPlugin() {
		try {
			String filePath = "src\\main\\java\\br\\edu\\ifba\\inf011\\atividade1_patterns\\plugins\\";
			diretorioAtual = new File(filePath);
			String[] plugins = diretorioAtual.list();
			int i;
			URL[] jars = new URL[plugins.length];
			System.out.println("Plugins instalados: " + plugins.length);
			for(i=0; i<plugins.length; i++) {
				jars[i] = (new File(filePath + plugins[i])).toURI().toURL();
				//System.out.println(jars[i]);		
			}
			
			URLClassLoader ulc = new URLClassLoader(jars);
			for(i=0; i<plugins.length; i++) {
				String pluginName = plugins[i].split("\\.")[0];
				IPlugin plugin = null;
				int mod;
				if(isSubString(pluginName, FileController.getExtension(plugins[i]))){
					try {
						Class<?> pluginClass = Class.forName(pluginName.toLowerCase() + "." + pluginName, true, ulc);
						Constructor<?> pluginConstructor = pluginClass.getDeclaredConstructor(null);
						mod = pluginConstructor.getModifiers();
						if(Modifier.isPrivate(mod)) {
							Method[] pluginMethods = pluginClass.getDeclaredMethods();
							for(Method m : pluginMethods) {
								mod = m.getModifiers();
								if(Modifier.isStatic(mod)) {
									plugin = (IPlugin) m.invoke(null);
									break;
								}
							}
						} else
							plugin = (IPlugin) pluginClass.newInstance();
					} catch(ClassNotFoundException | InstantiationException | IllegalAccessException  ex) {
						Logger.getLogger(PluginController.class.getName()).log(Level.SEVERE, null, ex);
					}
				}
				if(plugin != null)
					if(plugin.initPlugin())
						loadedPlugins.add(plugin);
				return true;
			}
		} catch(Exception e) {
			e.printStackTrace();
		}
		return false;

	}

	@Override
	public List<IPlugin> getLoadedPlugins() {
		return loadedPlugins;
	}
	
	public <T> List<T> getLoadedPluginsByType(Class<T> interfaceFactory){
		List<T> loadedPlugins = new ArrayList<T>();
		for(IPlugin plugin : this.loadedPlugins) {
			if(interfaceFactory.isAssignableFrom(plugin.getClass())) 
				loadedPlugins.add((T) plugin);
		}
		
		return loadedPlugins;
	}
	
	private boolean isSubString(String base, String ext) {
		int M = base.length();
        int N = ext.length();
 
        for (int i = 0; i <= N - M; i++) {
            int j;
 
 
            for (j = 0; j < M; j++)
                if (base.charAt(i + j)
                    != ext.charAt(j))
                    break;
 
            if (j == M)
                return true;
        }
 
        return false;
	}

}
