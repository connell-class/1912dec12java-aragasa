package com.ui;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;
import com.datamanagement.ObjectWriter;
import com.models.objects.OwnedCar;
import com.models.users.Customer;
import com.models.users.Employee;
import com.service.Offer;
import com.datamanagement.ObjectReader;

public class Driver 
{
	static ArrayList<Offer> offers = new ArrayList<>();
	static ArrayList<OwnedCar> owned = new ArrayList<>();
	static ArrayList<Offer> payments = new ArrayList<>();
	static HashMap<String, String> eLogin = new HashMap<>();
	static HashMap<String, String> cLogin = new HashMap<>();
	static HashMap<String, Object> cars = new HashMap<>();
	
	public static HashMap<String, String> getCLogin()
	{
		return cLogin;
	}
	
	public static HashMap<String, Object> getCars()
	{
		return cars;
	}
	
	public static HashMap<String, String> getELogin()
	{
		return eLogin;
	}
	
	public static ArrayList<Offer> getOffers() 
	{
		return offers;
	}
	
	public static ArrayList<OwnedCar> getOwned()
	{
		return owned;
	}
	
	public static ArrayList<Offer> getPayments()
	{
		return payments;
	}
	
	public static void loadTheStuff()
	{
		String eFile ="employees.properties";
		String carFile = "cars.txt";
		String oFile = "offerz.txt";
		String cFile = "customer.properties";
		String owFile = "Owned.txt";
		String pFile =  "payments.txt";
		
		eLogin = ObjectReader.loadFile(eFile, eLogin);
		cars = ObjectReader.readFile(carFile, cars);
		offers = ObjectReader.loadList(oFile, offers);
		cLogin = ObjectReader.loadFile(cFile, cLogin);
		owned = ObjectReader.loadList(owFile, owned);
		payments = ObjectReader.loadList(pFile , payments);
	}

	public static void main(String[] args) 
	{		
		Scanner s = new Scanner(System.in);
		
		String eFile ="employees.properties";
		String carFile = "cars.txt";
		String oFile = "offerz.txt";
		String cFile = "customer.properties";
		String owFile = "Owned.txt";
		String pFile =  "payments.txt";
		
		loadTheStuff();
	
		UI.welcome();
		
		String uOption = s.nextLine().toLowerCase();
		
		if(uOption.equals("e"))
		{
			Employee e = new Employee();
			boolean moveOn = false;
			
			while(moveOn == false)
			{
				UI.lOrR();
				String wOption1 = s.nextLine().toLowerCase();
				
				if(wOption1.equals("r"))
				{
					e.register(s);
					ObjectWriter.saveToFile(eFile, eLogin);
				}
				else if (wOption1.equals("l"))
				{
					e.login(s);
					moveOn=true;
				}
				else
				{
					UI.invalid();
				}
			}
			
			boolean floatOn = false;
			
			while(floatOn == false)
			{
				UI.eOptions();
				
				String eOptions = s.nextLine().toLowerCase();
				
				if(eOptions.equals("add"))
				{
					e.addCar(s);
					ObjectWriter.writeObjectToFile(cFile, cars);
				}
				else if(eOptions.equals("review"))
				{
					e.reviewOffer(s);
					ObjectWriter.writeObjectToFile(oFile, offers);
					ObjectWriter.writeObjectToFile(owFile, owned);
					ObjectWriter.writeObjectToFile(pFile, payments);
				}
				else if(eOptions.equals("remove"))
				{
					e.remove(s);
					ObjectWriter.writeObjectToFile(carFile, cars);
				}
				else if(eOptions.equals("view"))
				{
					e.viewCars();
				}
				else if(eOptions.equals("exit"))
				{
					floatOn = true;
					
				}
				else
				{
					UI.invalid();
				}
			}
		}
		
		else if(uOption.equals("c"))
		{
			boolean moveOn = false;
			Customer c = new Customer();
			
			while(moveOn == false)
			{
				UI.lOrR();
				String wOption2 = s.nextLine().toLowerCase();
				
				if(wOption2.equals("r"))
				{
					c.register(s);
					ObjectWriter.saveToFile(cFile, cLogin);
				}
				else if (wOption2.equals("l"))
				{
					c.login(s);
					moveOn=true;
				}
				else
				{
					UI.invalid();
				}
			}
			
			boolean floatOn = false;
			
			while(floatOn == false)
			{
				UI.cOptions();
				
				String cOptions = s.nextLine().toLowerCase();
				
				if(cOptions.equals("view"))
				{
					c.viewCars();
				}
				else if(cOptions.equals("offer"))
				{
					c.makeOffer(s);
					ObjectWriter.writeObjectToFile(oFile, offers);
				}
				else if(cOptions.equals("owned"))
				{
					c.viewOwned();
				}
				else if(cOptions.equals("payments"))
				{
					c.viewPayments(s);
					ObjectWriter.writeObjectToFile(pFile, payments);
				}
				else if(cOptions.equals("exit"))
				{
					floatOn = true;
				}
				else
				{
					UI.invalid();
				}
			}	
		}
	}
}



