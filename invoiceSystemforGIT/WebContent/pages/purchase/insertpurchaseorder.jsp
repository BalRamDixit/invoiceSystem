
<%@page import="org.json.simple.parser.JSONParser"%>
<%@page import="org.json.simple.JSONObject"%>
<%@page import="com.jjit.dao.DbAction"%>
<%
	String data = request.getParameter("data");
	JSONParser parse = new JSONParser();
	Object ob = parse.parse(data);
	JSONObject jb = (JSONObject) ob;
	String vendorid = (String) jb.get("vendorid");
	String clientid = (String) jb.get("clientid");
	String unittype = (String) jb.get("unittype");
	String rate = (String) jb.get("rate");
	String duration = (String) jb.get("duration");
	String sdate = (String) jb.get("sdate");
	String edate = (String) jb.get("edate");
	String particular = (String) jb.get("particular");
	String pterms = (String) jb.get("pterms");
	String pono = (String) jb.get("pono");
	String postatus = (String) jb.get("postatus");
	String pstatus = (String) jb.get("pstatus");
	String stextra=(String)jb.get("stextra");
	boolean status = DbAction.insert_purchase_order(vendorid, clientid,
			unittype, rate, duration, sdate, edate, particular, pterms,
			pono, postatus, pstatus,stextra);
	out.println("hii");
%>