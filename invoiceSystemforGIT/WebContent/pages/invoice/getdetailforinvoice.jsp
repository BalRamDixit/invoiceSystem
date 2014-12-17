<%@page import="java.util.HashMap"%>
<%@page import="java.sql.ResultSet"%>
<%@page import="jjit.DAO.DbAction"%>
<%@page import="org.json.simple.JSONObject"%>
<%@page import="org.json.simple.parser.JSONParser"%>
<%
	String data = request.getParameter("data");
	JSONParser jb=new JSONParser();
	JSONObject jo=(JSONObject)jb.parse(data);
	String pono=(String)jo.get("pono");
	ResultSet rs=DbAction.getdetailforinvoicebypono(pono);
	HashMap<String,String> row=new HashMap<String,String>();
	if(rs.next())
	{
		String total=rs.getString("total");
		String stextra=rs.getString("stextra");
		String particular=rs.getString("particular");
		String paymentterms=rs.getString("paymentterms");
		row.put("total",total);
		row.put("stextra",stextra);
		row.put("particular",particular);
		row.put("paymentterms",paymentterms);
	}
	JSONObject send=new JSONObject(row);
	out.println(send);
%>