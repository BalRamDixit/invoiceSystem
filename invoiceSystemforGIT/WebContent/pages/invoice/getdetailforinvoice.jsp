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
	String pono=(String)jo.get("pono");
	PurchaseOrderBo pobean=(PurchaseOrderBo)request.getSession().getAttribute("purchaseOrder");
	ResultSet rs=DbAction.getdetailforinvoicebypono(pono);
	HashMap<String,String> row=new HashMap<String,String>();
	if(rs.next())
	{
		String uuid=rs.getString("uuid"); 
		String rate=rs.getString("rate");
		String duration=rs.getString("duration");
		String stextra=rs.getString("stextra");
		String particular=rs.getString("particular");
		String paymentterms=rs.getString("paymentterms");
		String total=Double.parseDouble(rate)*Double.parseDouble(duration)+"";
		String pototal=new String(total);
		double tax;
		if(stextra.equals("1")){
	tax = 0.1236 * Double.parseDouble(total);
	total = (Double.parseDouble(total) + tax)+"";
		} else {
	Double xtotal = Double.parseDouble(total) / 1.1236;
	tax = Double.parseDouble(total) - xtotal;
	pototal=(Double.parseDouble(total) - tax)+"";
		}
		total=(int)Math.round(Double.parseDouble(total))+"";
		pototal=(int)Math.round(Double.parseDouble(pototal))+"";
		tax=(int)Math.round(tax);
		pobean.setUuid(uuid);
		pobean.setRate(rate);
		pobean.setDuration(duration);
		pobean.setParticular(particular);
		pobean.setPaymentterms(paymentterms);
		pobean.setStextra(stextra);
		pobean.setTax(tax+"");
		pobean.setTotal(total);
		pobean.setActotal(total);
		pobean.setPototal(pototal);
		request.getSession(true).setAttribute("purchaseOrder", pobean);
		row.put("total",total);
		row.put("pototal",pototal);
		row.put("tax",tax+"");
		row.put("stextra",stextra);
		row.put("particular",particular);
		row.put("paymentterms",paymentterms);
	}
	JSONObject send=new JSONObject(row);
	out.println(send);
%>