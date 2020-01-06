package com.users;

import static org.junit.Assert.assertEquals;
import java.util.ArrayList;
import org.junit.Test;
import com.service.Offer;
import com.service.SystemP;
import com.ui.Driver;

public class SystemPTest 
{
	
	@Test
	public void testCalculate ()
	{
		assertEquals (10, SystemP.calculate(30, 10, 2), 0.1);
	}
	
	@Test
	public void testFindOffer ()
	{
		Offer o1 = new Offer();
		ArrayList<Offer> offers = new ArrayList<>();
		Driver.loadTheStuff();

		o1.setAmount(444);
		o1.setPrice(400);
		o1.setUserName("a");
		o1.setVin("444");
		
		
		assertEquals(o1, SystemP.findOffer("a", "444"));
	}
	
	@Test
	public void testFindPayment()
	{
		Offer o1 = new Offer();
		ArrayList<Offer> payments = new ArrayList<>();
		Driver.loadTheStuff();

		o1.setAmount(100);
		o1.setPrice(1000);
		o1.setUserName("DO NOT ACCEPT");
		o1.setVin("TEST");
		
		assertEquals(o1, SystemP.findPayment("DO NOT ACCEPT", "TEST"));

	}
}
