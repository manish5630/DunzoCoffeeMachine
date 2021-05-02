package CoffeeMachine;

import java.util.ArrayList;
import java.util.List;

public class Beverages {
	protected String name;
	protected List<Ingredients> ingredients;
	public Beverages(String name,List<String> ingrnames,List<Integer> ingrquantity)
	{
		this.name=name;
		ingredients=new ArrayList<Ingredients>();
		int i;
		for(i=0;i<ingrnames.size();i++)
		{
			ingredients.add(new Ingredients(ingrnames.get(i),ingrquantity.get(i)));
		}
	}
	
	public String getName()
	{
		return this.name;
	}
	public List<Ingredients> getlist()
	{
		return this.ingredients;
	}

}
