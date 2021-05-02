package CoffeeMachine;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class CoffeeMachine {
	
	private int nOutlets;
	public Map<String,Object> ingredientsMap;
	public Map<Integer,Integer> threadStatus;
	private Set<String> beveragesMap;

// CoffeeMachine Constructor	
	 public CoffeeMachine(int nOutlets)
	{
		this.nOutlets=nOutlets;
		ingredientsMap = new HashMap<String,Object>();
		beveragesMap = new HashSet<String>();
		
		beveragesMap.add("hot_tea");
		beveragesMap.add("hot_coffee");
		beveragesMap.add("black_tea");
		beveragesMap.add("green_tea");
		
		threadStatus = new HashMap<>();
		int i;
		for(i=1;i<=nOutlets;i++)
		{
			threadStatus.put(i,0);
		}
		
	}
	
	 public void addIngredients(String name,int quantity)
	 {
		 ingredientsMap.put(name, new Ingredients(name,quantity));
	 }
	 public void refillIngredients(String name,int quantity)
	 {
		   if(ingredientsMap.containsKey(name))
		   {
			   ingredientsMap.replace(name, new Ingredients(name,quantity));
			   System.out.println("refilled Ingredient "+name+" successfully");
			   return ;
		   }
		   System.out.println("Ingredient "+name+ " is not addedd so can not refill it");
		   
		   
	 }
	public int getOutlets()
	{
		return this.nOutlets;
	}

	// Method to show Supported Drinks by Machine
	public void showSupportedDrink()
	{
		Set<String>keys = beveragesMap;
		Iterator<String> it = keys.iterator();
		System.out.println("The Machine can provide below drinks:");
		while(it.hasNext())
		{
			System.out.println(it.next());
		}
 
	}
	
// Method for making Beverages 	
	public void makeBeverages(int outlet,Beverages drink)
	{
		if(outlet>nOutlets || outlet<=0)
		{
			System.out.println("Outlet "+outlet+"does not exist in Machine");
		}
		if(!beveragesMap.contains(drink.getName()))
		{
			System.out.println("Drink "+drink.getName()+" is not supported by this machine");
			return ;
		}
		
// Starting thread in parallel to make drink 		
		DrinkMaker maker = new DrinkMaker(outlet, drink, this);
		maker.start();
		
		
	}
	

}
