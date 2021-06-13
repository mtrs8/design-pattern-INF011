package pizzabasica;
import interfaces.IPlugin;
import interfaces.Pizza;

public class PizzaBasica implements IPlugin, Pizza{
	
	@Override
	public boolean initialize() {
		return true;
	}

	@Override
	public void preparar() {
		System.out.println("Pizza Básica:\nMassa + Molho + Queijo ");
	}

	@Override
	public String getDescricao() {
		return "Pizza Básica (Massa + Molho + Queijo)";
	}

}
