package com.models.users;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

import com.models.objects.OwnedCar;
import com.service.Car;
import com.service.Offer;
import com.service.SystemP;
import com.ui.Driver;

public class Customer extends User implements Serializable
{
	/**
	 * 
	 */
	HashMap<String, Object> cars = Driver.getCars();
	HashMap<String, String> cLogin = Driver.getCLogin();
	ArrayList<Offer> offers = Driver.getOffers();
	ArrayList<OwnedCar> owned = Driver.getOwned();
	ArrayList<Offer> payments = Driver.getPayments();
	
	
	private static final long serialVersionUID = -830215928620275621L;
	
	private String cUsername;
	private String cPassword;

	public String getUsername() {
		return cUsername;
	}

	public void setUsername(String username) {
		this.cUsername = username;
	}

	public String getPassword() {
		return cPassword;
	}

	public void setPassword(String password) {
		this.cPassword = password;
	}

	public Customer() {
		super();
		// TODO Auto-generated constructor stub
	}

	//since these 2 methods are like exactly the same maybe in both classes 
	//maybe I should just throw this in the parent class
	@Override
	public void login(Scanner scan) 
	{
		// TODO Auto-generated method stub
		//just copy from employee
		boolean moveOn = false;
		
		while(moveOn == false)
		{
		System.out.println("Enter your username: ");
		String uName = scan.nextLine();
		
		System.out.println("Enter your password: ");
		String pword = scan.nextLine();
		
		boolean passCheck = cLogin.containsKey(uName);
		boolean uNameCheck = cLogin.containsValue(pword);
		
			if(passCheck == true && uNameCheck == true)
			{
				moveOn = true;
				System.out.println("Login Successful");
				setUsername(uName);
				setPassword(pword);
			}
			else
			{
				System.out.println("Either Username or Password is incorrect");
			}
		}
	}

	@Override
	public void register(Scanner scan) {
		//compare to the map
		boolean moveOn = false;
		
		while (moveOn == false) 
		{
			System.out.println("Enter new User Name: ");
			String uName = scan.nextLine();
			setUsername(uName);
			
			boolean nameCheck = cLogin.containsKey(uName);
			if(nameCheck == true)
			{
				System.out.println("User already exists.");
			}
			else
			{
				System.out.println("Enter new password: ");
				String pword = scan.nextLine();
				setPassword(pword);
		
				cLogin.put(uName, pword);
			
				moveOn = true;
			}
		}
	}
	
	@Override
	public void viewCars() {
		System.out.println(cars);
	}
	
	public void makeOffer(Scanner scan) {
		//select car
		Offer o = new Offer();
		boolean moveOn = false;
		
		String uName = getUsername();
//		System.out.println(uName);
		o.setUserName(uName);
		
		while(moveOn == false)
		{
			viewCars();
			System.out.println("Enter VIN number to select a car");
			String vin = scan.nextLine();
			//need to check if the vin number is in the array list
			boolean checkVIN = cars.containsKey(vin);
			
			if(checkVIN == false)
			{
				System.out.println("Car does not exist");
			}
			
			else 
			{
				o.setVin(vin);
				
				Car c = new Car();
				c = (Car) cars.get(vin);
				o.setPrice(c.getPrice());
				
				//insert a float value for price
				System.out.println("Enter your bid");
				float bid = scan.nextFloat();
				o.setAmount(bid);
				
				offers.add(o);
				
				moveOn = true;
			}
			
		}
		
		//or make a map with the the  as a key howver u would need to add the vin number
		//rethink this map
		//map inside a map first map with the key for the first being the username and the second being the vin number
		
	}
	
	public void viewOwned() {
		String uName = getUsername();
		
		for(OwnedCar o: owned)
		{
			if(o.getUsername().equals(uName))
			{
				System.out.println(o);
			}
		}
				
		
	}
	
	public void viewPayments(Scanner scan) {
		//add the while loop
		String name = getUsername();
		boolean moveOn = false;
		
		while (moveOn == false)
		{
			System.out.println("Enter the VIN");
			String vin = scan.nextLine();
			
			System.out.println(name);
			
			Offer o = SystemP.findPayment(name, vin);
			
			if(o == null)
			{
				System.out.println("Invalid VIN");
			}
			
			else
			{
				boolean floatOn = false;
				
				while(floatOn == false)
				{
					System.out.println("Enter the amount of months for repayment");
					int months = scan.nextInt();
					
					if(months <= 0)
					{
						System.out.println("invalid entry");
					}
					
					else
					{
						moveOn = true;
						floatOn = true;
						float price = o.getPrice();
						float paid = o.getAmount();
					
						float monthly = SystemP.calculate(price, paid, months);
					
						if(monthly <= 0)
						{
							System.out.println("You have paid off this car");
							payments.remove(o);
						
						}
						else
						{	
							System.out.println("Your monthly payment for this car: " + monthly);
						}
					}
				}	
			}
		}
	}
}
