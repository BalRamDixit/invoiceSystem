<%@page import="java.util.HashMap"%>
<%@page import="java.sql.ResultSet"%>
<%@page import="com.jjit.dao.DbAction"%>
<%@page import="org.json.simple.JSONObject"%>
<%@page import="org.json.simple.parser.JSONParser"%>
<%
	String data = request.getParameter("data");
	JSONParser jb=new JSONParser();
	JSONObject jo=(JSONObject)jb.parse(data);
	String invoiceno=(String)jo.get("invoiceno");
	String pono=(String)jo.get("pono");
	String sdate=(String)jo.get("sdate");
	String taxvalue=(String)jo.get("taxvalue");
	String total=(String)jo.get("total");
	String exp1=(String)jo.get("exp1");
	String value1=(String)jo.get("value1");
	Boolean ch1=(Boolean)jo.get("ch1");
	String exp2=(String)jo.get("exp2");
	String value2=(String)jo.get("value2");
	Boolean ch2=(Boolean)jo.get("ch2");
	String exp3=(String)jo.get("exp3");
	String value3=(String)jo.get("value3");
	Boolean ch3=(Boolean)jo.get("ch3");
	String exp4=(String)jo.get("exp4");
	String value4=(String)jo.get("value4");
	Boolean ch4=(Boolean)jo.get("ch4");
	String exp5=(String)jo.get("exp5");
	String value5=(String)jo.get("value5");
	Boolean ch5=(Boolean)jo.get("ch5");
	System.out.println(ch1+"    "+ch2+"     "+ch3+"     "+ch4+"     "+ch5);
	if(exp1!=null && value1!=null)
	{
		
	}
	if(exp2!=null && value2!=null)
	{
		
	}
	if(exp3!=null && value3!=null)
	{
		
	}
	if(exp4!=null && value4!=null)
	{
		
	}
	if(exp5!=null && value5!=null)
	{
		
	}
	//boolean status=DbAction.insertInvoice(invoiceno, pono, sdate, taxvalue, total);
	if(exp1!=null && value1!=null)
	{
		//status =DbAction.putExpense(exp1,value1);
	}
	if(exp2!=null && value2!=null)
	{
		
	}
	if(exp3!=null && value3!=null)
	{
		
	}
	if(exp4!=null && value4!=null)
	{
		
	}
	if(exp5!=null && value5!=null)
	{
		
	}
%>