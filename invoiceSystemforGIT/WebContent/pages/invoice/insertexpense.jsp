<%@page import="java.util.Set"%>
<%@page import="jjit.POJO.ExpenseBO"%>
<%@page import="jjit.POJO.PurchaseOrderBO"%>
<%@page import="org.json.simple.JSONArray"%>
<%@page import="org.json.simple.JSONObject"%>
<%@page import="java.util.HashMap"%>
<%@page import="java.util.ArrayList"%>
<%@page import="java.sql.ResultSet"%>
<%@page import="jjit.DAO.DbAction"%>
<%
	String action=request.getParameter("action");
	PurchaseOrderBO pobean=(PurchaseOrderBO)request.getSession().getAttribute("purchaseOrder");
	HashMap<Integer,ExpenseBO> ex=pobean.getExpenses();
	if("list".equals(action))
	{
		String var1=request.getParameter("jtSorting");
		String var2=request.getParameter("jtStartIndex");
		String var3=request.getParameter("jtPageSize");
		int recordCount =ex.size();
		HashMap<Integer,Object> rows=new HashMap<Integer,Object>();
		int j=0;
		int i=0;
		Set <Integer> keys= ex.keySet();
		for(Integer e:keys)
		{
	if(i>=Integer.parseInt(var2))
	{
		ExpenseBO expense=ex.get(e);
		HashMap<String,String> row=new HashMap<String,String>();
		row.put("exno",(i+1)+"");
		row.put("exp",expense.getDetail());
		row.put("cost",expense.getCost());
		row.put("Inclusive",expense.getInclusive()+"");
		System.out.println("expno-- "+(i+1)+"     "+expense.getDetail()+"      "+expense.getCost()+"       "+expense.getInclusive());
		rows.put(j, row);
		    	j++;
	}
	if(i>Integer.parseInt(var2)+Integer.parseInt(var3))
	{
		break;
	}
	i++;
		}
		HashMap<String,Object> jTableResult = new HashMap<String,Object>();
		jTableResult.put("Result", "OK");
		jTableResult.put("TotalRecordCount",recordCount);
		jTableResult.put("Records" ,rows);
		JSONObject jb=new JSONObject(jTableResult);
		out.println(jb);
	}
	else if("update".equals(action))
	{
		//Update record in database
		String expno=request.getParameter("exno");
		String exp=request.getParameter("exp");
		String cost=request.getParameter("cost");
		String Inclusive=request.getParameter("Inclusive");
		
		ExpenseBO e=ex.get(Integer.parseInt(expno));
		HashMap<String,String> row=new HashMap<String,String>();
		//Return result to jTable
		HashMap<String,Object> jTableResult = new HashMap<String,Object>();
		jTableResult.put("Result","OK");
		JSONObject jb=new JSONObject(jTableResult);
		out.println(jb);
		
	}
	else if("delete".equals(action))
	{
		//Delete from database
		String expno=request.getParameter("exno");
		ex.remove(Integer.parseInt(expno)+1);
		//Return result to jTable
		HashMap<String,Object> jTableResult = new HashMap<String,Object>();
		jTableResult.put("Result","OK");
		JSONObject jb=new JSONObject(jTableResult);
		out.println(jb);
		
	}
%>
