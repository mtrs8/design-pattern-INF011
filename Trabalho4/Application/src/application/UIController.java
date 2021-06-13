package application;

import interfaces.IUIController;
import interfaces.Pizza;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.lang.reflect.Constructor;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.ArrayList;
import java.util.List;
import javax.swing.DefaultListModel;
import javax.swing.JOptionPane;

public class UIController implements IUIController {

	@Override
	public boolean initialize() {
		view = new ViewMenu();
		view.setVisible(true);
		List<String> listItensDecorators = CoreSystem.getInstance().getPluginController().getPluginsDecorators();
		String[] plugins = CoreSystem.getInstance().getPluginController().getPlugins();
		URL[] jars = CoreSystem.getInstance().getPluginController().getJars();
		URLClassLoader ulc = new URLClassLoader(jars);
		loadItensComboBox(ulc);
		activeButtons(false);

		view.getComboBoxTypePizzas().removeAllItems();
		for (String comboBox : comboItens)
			view.getComboBoxTypePizzas().addItem(comboBox);
		view.getComboBoxTypePizzas().addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				itemSelectedCombox = (String) view.getComboBoxTypePizzas().getSelectedItem();
				if (!itemSelectedCombox.equalsIgnoreCase("<Escolha o tipo de pizza>")) {
					if(view.getModelList().size()==0) {
						view.getDecoratorAvailable().setModel(view.getModelList());
						for (String itemDecorator : listItensDecorators)
							view.getModelList().addElement(itemDecorator);
						activeButtons(true);						
					}
				} else {
					activeButtons(false);
					view.getModelList().removeAllElements();
					view.getModelChoosen().removeAllElements();
				}
			}
		});
		view.getUpIngredients().addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				String selectedItem = view.getDecoratorChosen().getSelectedValue();
				int itemIndex = view.getDecoratorChosen().getSelectedIndex();
				DefaultListModel model = (DefaultListModel) view.getDecoratorChosen().getModel();
				if (itemIndex > 0)
					upDownChangeComponents(itemIndex, selectedItem, itemIndex - 1, model);
			}
		});
		view.getDownIngredients().addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				String selectedDecorator = view.getDecoratorChosen().getSelectedValue();
				int itemIndexDecorator = view.getDecoratorChosen().getSelectedIndex();
				DefaultListModel model = (DefaultListModel) view.getDecoratorChosen().getModel();
				if (itemIndexDecorator < model.getSize() - 1)
					upDownChangeComponents(itemIndexDecorator, selectedDecorator, itemIndexDecorator + 1, model);
			}
		});
		view.getAddIngredients().addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				int itemIndex = view.getDecoratorChosen().getSelectedIndex();
				if (itemIndex != -1) {
					view.setModelChoosen((DefaultListModel) view.getDecoratorChosen().getModel());
					view.getModelChoosen().remove(itemIndex);
				}	
			}
		});
		view.getRemoveIngredients().addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				view.getModelChoosen().addElement(view.getDecoratorAvailable().getSelectedValue());
				view.getDecoratorChosen().setModel(view.getModelChoosen());
			}
		});
		view.getPreparePizza().addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				int ingredientsChoosen = view.getModelChoosen().getSize();
				List<String> newInstances = new ArrayList<>();
				for (int i = 0; i < ingredientsChoosen; i++)
					for (int j = 0; j < plugins.length; j++) {
						String pluginName = plugins[j].split("\\.")[0];
						if (view.getModelChoosen().getElementAt(i).equals(plugins[j].split("D")[0])) {
							newInstances.add(pluginName);
							break;
						}
					}
				Pizza pizza = preparePizza(ulc, newInstances);
				pizza.preparar();
				JOptionPane.showMessageDialog(view, "Pizza preparada com sucesso!! ");
				view.getModelChoosen().removeAllElements();
			}
		});
		return true;
	}

	private void activeButtons(boolean state) {
		view.getUpIngredients().setEnabled(state);
		view.getDownIngredients().setEnabled(state);
		view.getAddIngredients().setEnabled(state);
		view.getRemoveIngredients().setEnabled(state);
		view.getPreparePizza().setEnabled(state);
	}

	private void loadItensComboBox(URLClassLoader ulc) {
		comboItens.add("<Escolha o tipo de pizza>");
		List<String> decorators = CoreSystem.getInstance().getPluginController().getPluginsPizzas();
		for (String decorator : decorators) {
			Pizza pizza = null;
			try {
				Class<?> pluginClass = Class.forName(decorator.toLowerCase() + "." + decorator, true, ulc);
				pizza = (Pizza) pluginClass.newInstance();
				comboItens.add(pizza.getDescricao());
				listPizzas.add(pizza);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	private Pizza preparePizza(URLClassLoader ulc, List<String> list) {
		Pizza pizza = pizzaChoosen();
		for (String objects : list) {
			try {
				Class<?> pluginClass = Class.forName(objects.toLowerCase() + "." + objects, true, ulc);
				Constructor<?>[] pluginConstructors = pluginClass.getDeclaredConstructors();
				for (Constructor<?> constructor : pluginConstructors)
					pizza = (Pizza) constructor.newInstance(pizza);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return pizza;
	}

	private Pizza pizzaChoosen() {
		for (Pizza pizza : listPizzas)
			if (pizza.getDescricao().equalsIgnoreCase(itemSelectedCombox))
				return pizza;
		return null;
	}

	private void upDownChangeComponents(int itemIndexDecorator, String selectedDecorator,
				int controlIndexDecorator, DefaultListModel<String> model) throws  ArrayIndexOutOfBoundsException{
		model.remove(itemIndexDecorator);
		model.add(controlIndexDecorator, selectedDecorator);
		view.getDecoratorChosen().setSelectedIndex(controlIndexDecorator);
	}

	private ViewMenu view;
	private List<String> comboItens = new ArrayList<>();
	private List<Pizza> listPizzas = new ArrayList<>();
	private String itemSelectedCombox = null;
}
