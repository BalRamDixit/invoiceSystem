package com.jjit.servlet.Invoice;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

public class ExpenseTypes {
	
	public static String[] getExpenseTypes()
	{
		String[] list=null;
		FileReader reader=null;
		try {
			File f=new File("..\\properties\\expenseType.properties");
			System.out.println("File is --> "+f.getCanonicalPath()+"     "+f.exists());
			reader=new FileReader("expenseType.properties");
			Properties proerties=new Properties();
			proerties.load(reader);
			System.out.println();
			String count=proerties.getProperty("count");
			list=new String[Integer.parseInt(count)];
			for(int i=1;i<=Integer.parseInt(count);i++)
			{
				list[i-1]=proerties.getProperty("expense"+i);
				System.out.println(" expense "+i+proerties.getProperty("expense"+i));
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return list;
	}

}
