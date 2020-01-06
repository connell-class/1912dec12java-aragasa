package com.datamanagement;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

public class ObjectWriter 
{
	
	public static void writeObjectToFile(String s, /*HashMap<String, String> map*/ Object o)
	{
		try 
		{
			FileOutputStream f = new FileOutputStream(new File(s));
			ObjectOutputStream oo = new ObjectOutputStream(f);
//			PrintWriter pw = new PrintWriter(f);
			
//			for(Map.Entry<String,String> m :map.entrySet()){
//	            pw.println(m.getKey()+"="+m.getValue());
//	        }
			
//			pw.close();
			oo.writeObject(o);
			oo.close();
//			System.out.println("sucsess");
		} 
		catch (FileNotFoundException e) 
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public static void saveToFile(String s, Object o)
	{
		Properties properties = new Properties();
		
		properties.putAll((Map<? extends Object, ? extends Object>) o);
		
		try 
		{
			FileOutputStream f = new FileOutputStream(new File (s));
			properties.store(f, null);
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
		
		
	}
}

