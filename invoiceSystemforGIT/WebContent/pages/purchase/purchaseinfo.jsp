<%@page import="org.json.simple.JSONArray"%>
<%@page import="org.json.simple.JSONObject"%>
<%@page import="java.util.HashMap"%>
<%@page import="java.util.ArrayList"%>
<%@page import="java.sql.ResultSet"%>
<%@page import="com.jjit.dao.DbAction"%>
<%
   	String action=request.getParameter("action");
	if("list".equals(action))
	{
		String var1=request.getParameter("jtSorting");
		String var2=request.getParameter("jtStartIndex");
		String var3=request.getParameter("jtPageSize");
		int recordCount  = DbAction.get_purchaseOrder_count();
		ResultSet rs=DbAction.get_purchaseOrder(var1,var2,var3);
		HashMap<Integer,Object> rows=new HashMap<Integer,Object>();
		int i=0;
		while(rs.next())
		{
			HashMap<String,String> row=new HashMap<String,String>();
			row.put("uuid",rs.getString(1));
			row.put("vendorname",rs.getString(2));
			row.put("clientname",rs.getString(3));
			row.put("createdate",rs.getString(4));
			String unit="Hours";
			if("1".equals(rs.getString(5)))
				unit="Days";
			row.put("xunit",unit);
			row.put("rate",rs.getString(6));
			row.put("duration",rs.getString(7));
			row.put("startdate",rs.getString(8));
			row.put("enddate",rs.getString(9));
			String particular=rs.getString(10);
			row.put("particular",DbAction.getFormatedParticularwithLink(particular, rs.getString(1)));
			row.put("purchaseorderno",rs.getString(12));
			String pstatus="";
			if("a".equals(rs.getString(13)))
			{
				pstatus="Done";
			}
			else if("d".equals(rs.getString(13)))
			{
				pstatus="In progress";
			}
			else
			{
				pstatus="Not Done";
			}
				
			row.put("paymentstatus",pstatus);
			rows.put(i,row);
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
		String uuid=request.getParameter("uuid");
		String unit=request.getParameter("unit");
		String rate=request.getParameter("rate");
		String duration=request.getParameter("duration");
		String createdate=request.getParameter("createdate");
		String startdate=request.getParameter("startdate");
		String enddate=request.getParameter("enddate");
		String particular=request.getParameter("particular");
		String paymentterms=request.getParameter("paymentterms");
		String purchaseorderno=request.getParameter("purchaseorderno");
		boolean result = DbAction.updatepurchaseOrder(uuid, unit, rate, duration, createdate, startdate, enddate, particular, paymentterms, purchaseorderno);
		HashMap<String,Object> jTableResult = new HashMap<String,Object>();
		jTableResult.put("Result","OK");
		JSONObject jb=new JSONObject(jTableResult);
		out.println(jb);
	}
	else if("delete".equals(action))
	{
		String uuid=request.getParameter("uuid");
		boolean result = DbAction.deletepurchaseOrder(uuid);
		HashMap<String,Object> jTableResult = new HashMap<String,Object>();
		jTableResult.put("Result","OK");
		JSONObject jb=new JSONObject(jTableResult);
		out.println(jb);
	}
%>
