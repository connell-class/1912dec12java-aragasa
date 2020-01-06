package com.service;

import java.util.ArrayList;
import java.util.HashMap;

import com.models.objects.OwnedCar;
import com.ui.Driver;

public class SystemP {

	static ArrayList<Offer> offers = Driver.getOffers();
	static ArrayList<Offer> payments = Driver.getPayments();
	static ArrayList<OwnedCar> owned = Driver.getOwned();
	
	public static void reject(String vin, String name, Object o){
		
//		reject all other offers by removing every entry that has that VIN value
//		remove from the ArrayList Cars
		HashMap<String, Object> cars = Driver.getCars();
		ArrayList<OwnedCar> owned = Driver.getOwned();
		
		OwnedCar ow = new OwnedCar();
		Car c = new Car();
		
		c = (Car) cars.get(vin);
		
		ow.setUsername(name);
		ow.setMake(c.getMake());
		ow.setModel(c.getModel());
		ow.setVin(vin);
		ow.setYear(c.getYear());
		
		
		owned.add(ow);
		cars.remove(vin);
//		remove from the Offers map
		ArrayList<Offer> offers = Driver.getOffers();
		payments.add((Offer) o);
		offers.remove(o);
//		add to owned ArrayList

	}
	
	public static float calculate(float price, float paid, int month)
	{
		
		//Price  - offerAmmount = amountDue
		float amountDue = price - paid;
		//amountDue / month = payment
		float payment = amountDue / month; 
		return payment; //payment
	}
	
	public static Offer findOffer(String name, String vin)
	{
		for(Offer offer: offers)
		{
			if(offer.getUserName().equals(name) && offer.getVin().equals(vin))
			{
				return offer;
			}
		}
		
		return null;
	}
	
	public static Offer findPayment(String name, String vin)
	{
		for(Offer offer : payments)
		{
			if(offer.getUserName().equals(name) && offer.getVin().equals(vin))
			{
				return offer;
			}
		}
		
		return null;
	}
	
}
