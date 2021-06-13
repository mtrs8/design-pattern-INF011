package calabresadecorator;

import interfaces.IPlugin;
import interfaces.Pizza;
import interfaces.PizzaDecorator;

public class CalabresaDecorator extends PizzaDecorator implements IPlugin {

	public CalabresaDecorator(Pizza pizzaDecorator) {
		super(pizzaDecorator);
	}

	@Override
	public boolean initialize() {
		return true;
	}
	
	@Override
	public void preparar() {
		System.out.println("+ calabresa");
	}

	@Override
	public String getDescricao() {
		return "Calabresa";
	}




}
