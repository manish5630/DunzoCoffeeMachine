package CoffeeMachine;

public class Ingredients {
	
	private String name;
	private int quantity;
	Ingredients(String name,int quantity)
	{
		this.name=name;
		this.quantity=quantity;
	}
	public String getName()
	{
		return this.name;
	}
	public int getQuantity()
	{
		return this.quantity;
	}
	public void UpdateQuantity(int quantity)
	{
		this.quantity=quantity;
	}

}
