<%@page import="org.json.simple.JSONArray"%>
<%@page import="org.json.simple.JSONObject"%>
<%@page import="java.util.HashMap"%>
<%@page import="jjit.DAO.DbAction"%>
<%@page import="java.sql.ResultSet"%>
<%
	ResultSet rs=DbAction.getpurchaseorderlist();
	HashMap<Integer,Object> rows=new HashMap<Integer,Object>();
	JSONArray ja=new JSONArray();
	int i=0;
	while(rs.next())
	{
		HashMap<String,String> row=new HashMap<String,String>();
		String purchaseorderno=rs.getString("purchaseorderno");
		String uuid=rs.getString("uuid");
		String particular=rs.getString("particular");
		String p=particular;
		if(particular.length()>6)
		{
			p=particular.substring(0,6);
		}
		row.put("uuid",uuid);
		row.put("purchaseorderno",purchaseorderno);
		row.put("particular",p);
		JSONObject ob=new JSONObject(row);
		ja.add(i, ob);
		i++;
	}
	
	out.println(ja);
%>