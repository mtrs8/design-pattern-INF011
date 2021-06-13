package presuntodecorator;
import interfaces.IPlugin;
import interfaces.Pizza;
import interfaces.PizzaDecorator;

public class PresuntoDecorator extends PizzaDecorator implements IPlugin{

	public PresuntoDecorator(Pizza pizzaDecorator) {
		super(pizzaDecorator);
	}

	@Override
	public boolean initialize() {
		return true;
	}
	
	@Override
	public void preparar() {
		super.preparar();
		System.out.println(" + presunto");
	}
	
	@Override
	public String getDescricao() {
		return "Presunto";
	}


}
