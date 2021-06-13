
package application;

import interfaces.ICoreSystem;
import interfaces.IPluginController;
import interfaces.IUIController;

public class CoreSystem implements ICoreSystem{

	private static ICoreSystem instance = null;
	private static IUIController uiController;
	private static IPluginController pluginController;
	
    private CoreSystem() {}

    public static ICoreSystem getInstance(){
        if(instance == null){
            instance = new CoreSystem();
            uiController = new UIController();
            pluginController = new PluginController();
            pluginController.initialize();
            uiController.initialize();
        }
        return instance;
    }
    
    @Override
    public IUIController getUIController() {
       return uiController; 
    }

    @Override
    public IPluginController getPluginController() {
        return pluginController;
    }
    
}
