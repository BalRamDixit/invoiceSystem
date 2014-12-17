package jjit.DAO;

import java.util.UUID;

public class uuid_generator {
	
	public static String createUUID()
	{
		String key="";
		key=UUID.randomUUID().toString().replaceAll("-", "");
		return key;
	}
}
