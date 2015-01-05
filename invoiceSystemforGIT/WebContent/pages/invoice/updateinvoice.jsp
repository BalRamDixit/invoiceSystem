<%@page import="java.util.Set"%>
<%@page import="com.jjit.pojo.com.jjit.pojo.ExpenseBo"%>
<%@page import="com.jjit.pojo.com.jjit.pojo.PurchaseOrderBo"%>
<%@page import="java.util.HashMap"%>
<%@page import="java.sql.ResultSet"%>
<%@page import="com.jjit.dao.DbAction"%>
<%@page import="org.json.simple.JSONObject"%>
<%@page import="org.json.simple.parser.JSONParser"%>
<%
	PurchaseOrderBo pobean=(PurchaseOrderBo)request.getSession().getAttribute("purchaseOrder");
	String pototal=pobean.getActotal();
	System.out.println("actotal is -- "+pototal);
	String tax=pobean.getTax();
	String total=pobean.getTotal();
	String stextra=pobean.getStextra();
	if(stextra.equals("1"))
	{
		stextra="not included";
	}
	double inclusiveExpense=0;
	double exclusiveExpense=0;
	HashMap<Integer,ExpenseBo> expenses=pobean.getExpenses();
	boolean status=false;
	Set<Integer> keys= expenses.keySet();
	for(Integer e:keys)
	{
		if(expenses.get(e)!=null)
		{
	status=true;
	System.out.println("Object is saved");
	if(expenses.get(e).getInclusive())
	{
		inclusiveExpense=inclusiveExpense+Double.parseDouble(expenses.get(e).getCost());
	}
	else
	{
		exclusiveExpense=exclusiveExpense+Double.parseDouble(expenses.get(e).getCost());
	}
		}
	}
	System.out.println("INclusive --> "+inclusiveExpense+"\nExclusive --> "+exclusiveExpense);
	pototal=(Double.parseDouble(pototal)-inclusiveExpense)+"";
	System.out.println("pototal --> "+pototal+"\ngetpototal()--> "+pobean.getPototal());
	if(!(pototal.equals(Double.parseDouble(pobean.getPototal())+"")))
	{
		System.out.println("condition is true");
		if(stextra.equals("not included")){
	tax = 0.1236 * Double.parseDouble(pototal)+"";
	
		} else {
	Double xtotal = Double.parseDouble(pototal) / 1.1236;
	tax = Double.parseDouble(pototal) - xtotal+"";
	pototal=(Double.parseDouble(pototal) - Double.parseDouble(tax))+"";
		}
	}
	total=(Double.parseDouble(pototal)+inclusiveExpense+exclusiveExpense)+Double.parseDouble(tax)+"";
	HashMap<String,String> row=new HashMap<String,String>();
	row.put("total",total);
	row.put("pototal",pototal);
	row.put("tax",tax+"");
	JSONObject send=new JSONObject(row);
	out.println(send);
%>