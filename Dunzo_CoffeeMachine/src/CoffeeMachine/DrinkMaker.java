package CoffeeMachine;

import java.util.List;

public class DrinkMaker extends Thread{

	private int outlet;
	private Beverages drink;
	private CoffeeMachine coffeeMachine;
	DrinkMaker(int outlet,Beverages drink,CoffeeMachine coffeeMachine)
	{
		super();
		this.drink=drink;
		this.outlet=outlet;
		this.coffeeMachine = coffeeMachine;
	}
	@Override
	public void run()
	{
		
// Checking if another drink is being made on same outlet			
		int OutletStatus = coffeeMachine.threadStatus.get(outlet);
		while(OutletStatus==1)
		{
			OutletStatus = coffeeMachine.threadStatus.get(outlet);
		}
		coffeeMachine.threadStatus.replace(outlet,1);
		
synchronized (coffeeMachine.ingredientsMap) {
	
// Checking the Ingredients for availability and if they are sufficient to make drink	
		List<Ingredients> ingr = drink.getlist();
		for(Ingredients in : ingr)
		{
			if(!coffeeMachine.ingredientsMap.containsKey(in.getName()))
			{
				System.out.println(drink.getName()+" can not be prepared because "+in.getName()+
						" is not available");
				
				return ;
			}
			Ingredients machineIngr = (Ingredients)coffeeMachine.ingredientsMap.get(in.getName());
			if(machineIngr.getQuantity()<in.getQuantity())
			{
				System.out.println(drink.getName()+" can not be prepared because "+in.getName()+
						" is not sufficient");
				
				return;
			}
			
		}
		
// Updating Ingredients according to the drink requirement		
			for(Ingredients in : ingr)
			{
				Ingredients machineIngr = (Ingredients)coffeeMachine.ingredientsMap.get(in.getName());
				machineIngr.UpdateQuantity(machineIngr.getQuantity()-in.getQuantity());
				coffeeMachine.ingredientsMap.replace(machineIngr.getName(),machineIngr);
				
			}
}
	
// Mixing Ingredients to make drink
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		coffeeMachine.threadStatus.replace(outlet,0);
		System.out.println(drink.getName()+" is prepared "+"on outlet "+outlet);
		
	}
	
	
}
