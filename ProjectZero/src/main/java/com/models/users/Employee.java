package com.models.users;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

import com.service.Car;
import com.service.Offer;
import com.service.SystemP;
import com.ui.Driver;

public class Employee extends User implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String eUserName;
	private String ePassword;

	HashMap<String, String> eLogin = Driver.getELogin();
	HashMap<String, Object> cars = Driver.getCars();
	ArrayList<Offer> offers = Driver.getOffers();
	ArrayList<Offer> payments = Driver.getPayments();
	
	public String getUserName() {
		return eUserName;
	}

	public void setUserName(String userName) {
		this.eUserName = userName;
	}

	public String getPassword() {
		return ePassword;
	}

	public void setPassword(String password) {
		this.ePassword = password;
	}

	public Employee() {
		super();
		// TODO Auto-generated constructor stub
	}

	//compare the maps key aka the username to the input and if true return the password
	@Override
	public void login(Scanner scan) {
		// TODO Auto-generated method stub
		//check the username and password from the file
		
		boolean moveOn = false;
		
		while(moveOn == false)
		{
		System.out.println("Enter your username: ");
		String uName = scan.nextLine();
		
		System.out.println("Enter your password: ");
		String pword = scan.nextLine();
		
		boolean passCheck = eLogin.containsKey(uName);
		boolean uNameCheck = eLogin.containsValue(pword);
		
			if(passCheck == true && uNameCheck == true)
			{
				moveOn = true;
				System.out.println("Login Successful");
				setUserName(uName);
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
		// TODO Auto-generated method stub
		//Make a map and throw it into a map, probably a hash map for speed
		//use serializable and throw that stuff into the text file
		
		boolean moveOn = false;
		
		while (moveOn == false) 
		{
			System.out.println("Enter new User Name: ");
			String uName = scan.nextLine();
			setUserName(uName);
			
			boolean nameCheck = eLogin.containsKey(uName);
			if(nameCheck == true)
			{
				System.out.println("User already exists.");
			}
			else
			{
				System.out.println("Enter new password: ");
				String pword = scan.nextLine();
				setPassword(pword);
		
				eLogin.put(uName, pword);
			
				moveOn = true;
			}
		}
		
	}
	
	public void addCar(Scanner scan) 
	{
		//Store this info in the car object
		
		boolean moveOn = false;
		Car c = new Car();
		
		while(moveOn == false)
		{
			System.out.println("Enter VIN: ");
			String vin = scan.nextLine();
			c.setVin(vin);
			
			boolean checkVIN = cars.containsKey(vin);
			
			if(checkVIN == true)
			{
				System.out.println("Car already exists on the lot. Please enter a new VIN");
			}
			else
			{
				System.out.println("Enter make: ");
				String make = scan.nextLine();
				c.setMake(make);
		
				System.out.println("Enter model: ");
				String model = scan.nextLine();
				c.setModel(model);
		
				System.out.println("Enter year: ");
				int year = scan.nextInt();
				c.setYear(year);
		
				System.out.println("Enter price: ");
				float price = scan.nextFloat();
				c.setPrice(price);
				
				scan.nextLine();
				cars.put(vin, c);
				moveOn = true;
			}
		}
		
	}
	
	//make a switch case depending on what they want to do with the selection
	
	@Override
	public String toString() {
		return "Employee [userName=" + eUserName + ", password=" + ePassword + "]";
	}

	public void reviewOffer(Scanner scan) 
	{
//		System.out.println(cars);
//		make selection on car using scanner
		//maybe seperate into 2 different methods: one for viewing the lot and one for reviewing offers
		//maybe put the view lot in the user abstract class
		//read the next float
		//make selection on whether u accept or reject
		//more logic
		//Set accepted to true
		
		boolean moveOn = false;
		
		while(moveOn == false)
		{
			System.out.println(offers);
			System.out.println("Enter the username");
			String uName = scan.nextLine();
			
			System.out.println("Enter the VIN");
			String vin = scan.nextLine();
			
			Offer o = SystemP.findOffer(uName, vin);
			
			if(o == null)
			{
				System.out.println("Offer does not exist");
			}
			else
			{
				boolean floatOn = false;
				
				while(floatOn == false)
				{
					System.out.println("Enter a for accept or d for decline");
					String descion = scan.nextLine().toLowerCase();
					
					if(descion.equals("a"))
					{
						//Then call systems reject method
						SystemP.reject(vin, uName, o);
						System.out.println("The offer has been accepted");
						moveOn = true;
						floatOn = true;
					}
					else if(descion.equals("d"))
					{
						System.out.println("The offer has been rejected");
						offers.remove(o);
						moveOn = true;
						floatOn = true;
					}
					else
					{
						System.out.println("Invalid Entry");
					}
				}			
			}
		}				
	}
	
	public void remove(Scanner scan)
	{
		System.out.println(cars);
		boolean moveOn = false;
		
		while(moveOn == false)
		{
			System.out.println("Enter the VIN of the car you wish to remove");
			String rVin = scan.nextLine();
			boolean vinCheck = cars.containsKey(rVin);
			
			if(vinCheck == true)
			{
				cars.remove(rVin);
				System.out.println("removed the car");
				moveOn= true;
			}
			else
			{
				System.out.println("Car does not exist");
			}
		}
	}
	
	public void viewPayments()
	{
		//print out only the values at the username key
		
		System.out.println(payments);
	}

	@Override
	public void viewCars() {
		// TODO Auto-generated method stub
		System.out.println(cars);
	}
	
}
