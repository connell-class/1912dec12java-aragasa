package com.datamanagement;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Properties;

public class ObjectReader 
{
	
	public static HashMap readFile (String s, Object o)

	{
		try 
		{
			FileInputStream f = new FileInputStream(new File(s));
			ObjectInputStream oI = new ObjectInputStream(f);
			
			HashMap <Object, Object> mapInFile = (HashMap <Object, Object>) oI.readObject();
			oI.close();
			
//			 for(Map.Entry<String,String> m :mapInFile.entrySet()){
//		            System.out.println(m.getKey()+" : "+m.getValue());
//			 }
			
			 o = mapInFile;
		} 
		catch (FileNotFoundException e) 
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		catch (IOException e) 
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		catch (ClassNotFoundException e) 
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return (HashMap) o;
	}
	
	public static HashMap loadFile(String s, Object o)
	{
		Properties properties = new Properties();
		
		try {
			properties.load(new FileInputStream(s));
			
			o = new HashMap<Object, Object>(properties);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return (HashMap) o;
		
	}
	
	public static ArrayList loadList (String s, Object o)
	{
		FileInputStream f;
		try {
			f = new FileInputStream(new File(s));
			ObjectInputStream oI = new ObjectInputStream(f);
			
			ArrayList<Object> listInFile = (ArrayList<Object>) oI.readObject();
			oI.close();
			
			o = listInFile;
			
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return (ArrayList) o;
			
	}
}
