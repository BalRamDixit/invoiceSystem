<%@page import="jjit.POJO.PurchaseOrderBO"%>
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
		String paymentterms=rs.getString("paymentterms");
		String pterms=paymentterms;
		if(paymentterms.length()>6)
		{
	pterms=paymentterms.substring(0,6);
		}
		row.put("purchaseorderno",purchaseorderno);
		row.put("paymentterms",pterms);
		JSONObject ob=new JSONObject(row);
		ja.add(i, ob);
		i++;
	}
	PurchaseOrderBO pobean=new PurchaseOrderBO();
	request.getSession(true).setAttribute("purchaseOrder", pobean);
	out.println(ja);
%>