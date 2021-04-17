package br.edu.ifba.inf011.atividade1_patterns.plugins;

import java.awt.List;
import java.io.File;
import java.lang.reflect.Constructor;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.ArrayList;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;

import br.edu.ifba.inf011.atividade1_patterns.plugins.interfaces.IPlugin;

public class PluginController implements IPlugin {

	private ArrayList<Object> loadedPlugins1 = new ArrayList<>();
	@Override
	public boolean initPlugin() {
		try {
			File currentDir = new File("./plugins");
			String[] plugins = currentDir.list();
			int i;
			URL[] jars = new URL[plugins.length];
			System.out.println("Encontrei " + plugins.length + " plugins instalados!");
			for (i = 0; i < plugins.length; i++)
				try {
					jars[i] = (new File("./plugins/" + plugins[i])).toURI().toURL();
				} catch (MalformedURLException ex) {
					Logger.getLogger(PluginController.class.getName()).log(Level.SEVERE, null, ex);
				}
			URLClassLoader ulc = new URLClassLoader(jars);
			for (i = 0; i < plugins.length; i++) {
				String pluginName = plugins[i].split("\\.")[0];
				IPlugin plugin = null;
				int modifiers;
				try {
					Class<?> pluginClass = Class.forName(pluginName.toLowerCase() + "." + pluginName, true, ulc);
					Constructor<?> pluginConstructor = pluginClass.getDeclaredConstructor(null);
					modifiers= pluginConstructor.getModifiers();
					if(Modifier.isPrivate(modifiers)) {
						Method[] pluginMethods= pluginClass.getDeclaredMethods();
						for(Method method: pluginMethods) {
							modifiers = method.getModifiers();
							if(Modifier.isStatic(modifiers)) {
								plugin = (IPlugin) method.invoke(null);
								break;
							}
						}
					}else 
						plugin = (IPlugin) pluginClass.newInstance();
				} catch (ClassNotFoundException | InstantiationException | IllegalAccessException ex) {
					Logger.getLogger(PluginController.class.getName()).log(Level.SEVERE, null, ex);
				}
				if (plugin != null)
					if (plugin.initPlugin())
						loadedPlugins1.add(plugin);
			}
			
			return true;	
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;		
	}

}
