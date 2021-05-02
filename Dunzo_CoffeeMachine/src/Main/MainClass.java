package Main;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.json.JSONException;
import org.json.JSONObject;

import CoffeeMachine.Beverages;
import CoffeeMachine.CoffeeMachine;
/* There are two test_case file , namely test_case1.txt and test_case2.txt it can be run to test the code*/
/* To test the code on test_case2.txt , Please replace 
 *   reader = new BufferedReader(new FileReader("test_case1.txt"));
 *     
 *     by 
 *     
 *     reader = new BufferedReader(new FileReader("test_case2.txt"));
 *     and run the project
 *     
 *     
 *   To parse the data java-json.jar library is used   
 * 
 */


public class MainClass {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		
// Executing test_case1.txt file 
	
//Reading test_case file 		
		BufferedReader reader = null;
		try {
			reader = new BufferedReader(new FileReader("test_case1.txt"));
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		String json = "";
		try {
		    StringBuilder sb = new StringBuilder();
		    String line = reader.readLine();

		    while (line != null) {
		        sb.append(line);
		        sb.append("\n");
		        line = reader.readLine();
		    }
		    json = sb.toString();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
		    try {
				reader.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	
// Parsing the test_case file JSON		
		try {
			JSONObject testcase = new JSONObject(json);
			JSONObject machine = testcase.getJSONObject("machine");
			int numOutlets = machine.getJSONObject("outlets").getInt("count_n");
			
			CoffeeMachine coffeeMachine = new CoffeeMachine(numOutlets);
			JSONObject totalItemsQuantity = machine.getJSONObject("total_items_quantity");
			Iterator<String> it = totalItemsQuantity.keys();
			while(it.hasNext())
			{
				String key = it.next();
				coffeeMachine.addIngredients(key,totalItemsQuantity.getInt(key));
			}
			coffeeMachine.showSupportedDrink();
			System.out.println("--------------------------------------------------------\n");
			
			JSONObject beverages = machine.getJSONObject("beverages");
			it = beverages.keys();
			int outlets=0;
			while(it.hasNext())
			{
				String beverageName = it.next();
				
				//System.out.println("main "+beverageName);
				
				JSONObject beverageIngr = beverages.getJSONObject(beverageName);
				Iterator<String> it1 = beverageIngr.keys();
				List<String> ingrNames = new ArrayList<String>();
				List<Integer> ingrQuantity = new ArrayList<Integer>();
				while(it1.hasNext())
				{
					String ingred = it1.next();
					ingrNames.add(ingred);
					ingrQuantity.add(beverageIngr.getInt(ingred));
					
				}
				
				// Making Beverages on different outlets in round-robin fashion
				Beverages beverage = new Beverages(beverageName, ingrNames, ingrQuantity);
				coffeeMachine.makeBeverages((outlets%numOutlets)+1, beverage);
				outlets++;
				
			}
			
			
			
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		

	}

}
