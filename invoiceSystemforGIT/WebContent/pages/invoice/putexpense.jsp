<%@page import="com.jjit.pojo.com.jjit.pojo.ExpenseBo"%>
<%@page import="com.jjit.pojo.com.jjit.pojo.PurchaseOrderBo"%>
<%@page import="java.util.HashMap"%>
<%@page import="java.sql.ResultSet"%>
<%@page import="com.jjit.dao.DbAction"%>
<%@page import="org.json.simple.JSONObject"%>
<%@page import="org.json.simple.parser.JSONParser"%>
<%
	String data = request.getParameter("data");
	JSONParser jb=new JSONParser();
	JSONObject jo=(JSONObject)jb.parse(data);
	String exp=(String)jo.get("exp");
	String val=(String)jo.get("val");
	String inc=(String)jo.get("inc");
	PurchaseOrderBo pobean=(PurchaseOrderBo)request.getSession().getAttribute("purchaseOrder");
	ExpenseBo e=new ExpenseBo();
	e.setCost(val);
	e.setDetail(exp);
	if(inc.equals("true"))
	{
		e.setInclusive(true);
	}
	else
	{
		e.setInclusive(false);
	}
	HashMap<Integer,ExpenseBo> ex=pobean.getExpenses();
	ex.put(ex.size()+1, e);
	System.out.println("exno-- "+(ex.size())+"     "+e.getDetail()+"      "+e.getCost()+"       "+e.getInclusive());
%>