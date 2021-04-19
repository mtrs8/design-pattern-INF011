package br.edu.ifba.inf011.atividade1_patterns.interfaces;

import java.util.List;

public interface IPluginController {
	
	public boolean initPlugin();
	
	public List<IPlugin> getLoadedPlugins();
	
	public <T> List<T> getLoadedPluginsByType(Class<T> interfaceFactory);
	
	
}
