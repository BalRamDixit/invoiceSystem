<%@page import="org.json.simple.JSONArray"%>
<%@page import="org.json.simple.JSONObject"%>
<%@page import="java.util.HashMap"%>
<%@page import="jjit.DAO.DbAction"%>
<%@page import="java.sql.ResultSet"%>
<%
	ResultSet rs=DbAction.getpurchaseorderno();
	HashMap<Integer,Object> rows=new HashMap<Integer,Object>();
	JSONArray ja=new JSONArray();
	int i=0;
	while(rs.next())
	{
		HashMap<String,String> row=new HashMap<String,String>();
		String purchaseorderno=rs.getString("purchaseorderno");
		row.put("purchaseorderno",purchaseorderno);
		JSONObject ob=new JSONObject(row);
		ja.add(i, ob);
		i++;
	}
	
	out.println(ja);
%>