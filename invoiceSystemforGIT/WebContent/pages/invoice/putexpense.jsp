<%@page import="jjit.POJO.ExpenseBO"%>
<%@page import="jjit.POJO.PurchaseOrderBO"%>
<%@page import="java.util.HashMap"%>
<%@page import="java.sql.ResultSet"%>
<%@page import="jjit.DAO.DbAction"%>
<%@page import="org.json.simple.JSONObject"%>
<%@page import="org.json.simple.parser.JSONParser"%>
<%
	String data = request.getParameter("data");
	JSONParser jb=new JSONParser();
	JSONObject jo=(JSONObject)jb.parse(data);
	String exp=(String)jo.get("exp");
	String val=(String)jo.get("val");
	String inc=(String)jo.get("inc");
	PurchaseOrderBO pobean=(PurchaseOrderBO)request.getSession().getAttribute("purchaseOrder");
	ExpenseBO e=new ExpenseBO();
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
	HashMap<Integer,ExpenseBO> ex=pobean.getExpenses();
	ex.put(ex.size()+1, e);
	System.out.println("exno-- "+(ex.size())+"     "+e.getDetail()+"      "+e.getCost()+"       "+e.getInclusive());
%>