package com.ui;

public class UI {

	public static void welcome()
	{
		System.out.println("Welcome to Joe's Car Lot: ");
		System.out.println("Type e if you are an employee or c if you are a customer");
	}
	
	public static void lOrR()
	{
		System.out.println("Type l to login or r to register");
	}
	
	public static void invalid()
	{
		System.out.println("Invalid Entry");
	}
	
	public static void eOptions()
	{
		System.out.println("Options: Add Car, Review Offers, Remove Cars, View Payments, or exit");
		System.out.println("Type: add, review, remove, view or exit");
	}
	
	public static void cOptions()
	{
		System.out.println("Options: View Cars, Make Offers, View Owned, View Payments, or Exit");
		System.out.println("Type: view, offer, owned, payments, or exit");
	}
}
