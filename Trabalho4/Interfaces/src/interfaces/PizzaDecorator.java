package interfaces;

public abstract class PizzaDecorator implements Pizza{
	
	protected Pizza pizzaDecorator;
	
	public PizzaDecorator(Pizza pizzaDecorator) {
		this.setPizzaDecorator(pizzaDecorator);
	}
	
	public void setPizzaDecorator(Pizza pizzaDecorator) {
		this.pizzaDecorator = pizzaDecorator;
	}
	
	@Override
	public void preparar() {
		pizzaDecorator.preparar();
	}
	
}
