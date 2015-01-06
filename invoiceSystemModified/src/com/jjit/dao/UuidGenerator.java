package com.jjit.dao;

import java.util.UUID;

public class UuidGenerator {
	
	public static String createUUID()
	{
		String key="";
		key=UUID.randomUUID().toString().replaceAll("-", "");
		return key;
	}
	public static String changeToDDMMYYYY(String date)
	{
		String newdate="";
		String[] parts=date.split("-");
		newdate=parts[2]+"-"+parts[1]+"-"+parts[0];
		return newdate;
	}
}
