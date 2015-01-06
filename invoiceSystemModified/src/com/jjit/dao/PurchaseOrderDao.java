package com.jjit.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class PurchaseOrderDao {
	
	public static ResultSet get_purchaseOrder(String var1, String var2,
			String var3) {
		ResultSet rs = null;
		try {
			Connection con = DbConnect.getConnection();
			String query = "SELECT p.uuid,v.contactperson as vendorname,c.contactperson as clientname ,p.createdate,p.unit as xunit,p.rate,p.duration,p.startdate,p.enddate,p.particular,p.paymentterms,p.purchaseorderno,p.paymentstatus FROM purchaseorder p,vendor v,client c where p.vendorid=v.uuid and p.clientid=c.uuid and p.status='a' ORDER BY p."
					+ var1 + " LIMIT " + var2 + "," + var3 + ";";
			Statement st = con.createStatement();
			rs = st.executeQuery(query);
		} catch (SQLException ex) {

		}
		return rs;
	}
//
//	public static boolean insertpurchaseOrder(String vendorid, String clientid,
//			String unit, String rate, String duration, String startdate,
//			String enddate, String particular, String paymentterms,
//			String purchaseorderno) {
//		boolean status = false;
//		try {
//			Connection con = DbConnect.getConnection();
//			String uuid = UuidGenerator.createUUID();
//			String query = "insert into purchaseorder(uuid,vendorid,clientid,createdate,unit,rate,duration,startdate,enddate,particular,paymentterms,purchaseorderno) values('"
//					+ uuid
//					+ "','"
//					+ vendorid
//					+ "','"
//					+ clientid
//					+ "',cur_date(),'"
//					+ unit
//					+ "','"
//					+ rate
//					+ "','"
//					+ duration
//					+ "','"
//					+ startdate
//					+ "','"
//					+ enddate
//					+ "','"
//					+ particular
//					+ "','" + paymentterms + "','" + purchaseorderno + "')";
//			Statement st = con.createStatement();
//			int x = st.executeUpdate(query);
//			if (x > 0) {
//				status = true;
//			}
//		} catch (SQLException ex) {
//			System.out.println("Exception is "+ex);
//		}
//		return status;
//	}

	public static boolean updatepurchaseOrder(String uuid, String unit,
			String rate, String duration, String createdate, String startdate,
			String enddate, String particular, String paymentterms,
			String purchaseorderno) {
		boolean status = false;
		try {
			Connection con = DbConnect.getConnection();
			String query = "UPDATE purchaseorder SET createdate= '"
					+ createdate + "',unit= '" + unit + "',rate= '" + rate
					+ "',duration= '" + duration + "',startdate= '" + startdate
					+ "',enddate= '" + enddate + "',particular= '" + particular
					+ "',paymentterms= '" + paymentterms
					+ "'+purchaseorderno= '" + purchaseorderno
					+ "' where uuid='" + uuid + "'";
			Statement st = con.createStatement();
			int x = st.executeUpdate(query);
			if (x > 0) {
				status = true;
			}
		} catch (SQLException ex) {
			System.out.println("Exception is "+ex);
		}
		return status;
	}

	public static boolean deletepurchaseOrder(String uuid) {
		boolean status = false;
		try {
			Connection con = DbConnect.getConnection();
			String query = "update purchaseorder set status='d' WHERE uuid = '"
					+ uuid + "'";
			Statement st = con.createStatement();
			int x = st.executeUpdate(query);
			if (x > 0) {
				status = true;
			}
		} catch (SQLException ex) {
			System.out.println("Exception is "+ex);
		}
		return status;
	}

	public static int get_purchaseOrder_count() {
		int count = 0;
		try {
			Connection con = DbConnect.getConnection();
			String query = "SELECT COUNT(*) AS RecordCount FROM purchaseorder where status='a'";
			Statement st = con.createStatement();
			ResultSet rs = st.executeQuery(query);
			if (rs.next()) {
				count = rs.getInt(1);
			}
		} catch (SQLException ex) {
			System.out.println("Exception is "+ex);
		}
		return count;
	}

	public static ResultSet get_purchaseOrder_lastrecord() {
		ResultSet rs = null;
		try {
			Connection con = DbConnect.getConnection();
			String query = "SELECT * FROM purchaseorder WHERE uuid = LAST_INSERT_ID();";
			Statement st = con.createStatement();
			rs = st.executeQuery(query);
		} catch (SQLException ex) {

		}
		return rs;
	}
	public static ResultSet get_clientsforpo() {
		ResultSet rs = null;
		try {
			Connection con = DbConnect.getConnection();
			String query = "SELECT * FROM client where status='a' ";
			Statement st = con.createStatement();
			rs = st.executeQuery(query);
		} catch (SQLException ex) {
			System.out.println("Exception is "+ex);
		}
		return rs;
	}

	public static ResultSet get_vendorsforpo() {
		ResultSet rs = null;
		try {
			Connection con = DbConnect.getConnection();
			String query = "SELECT * FROM vendor where status='a'";
			Statement st = con.createStatement();
			rs = st.executeQuery(query);
		} catch (SQLException ex) {
			System.out.println("Exception is "+ex);
		}
		return rs;
	}
	public static ResultSet getParticular(String uuid) {
		ResultSet rs = null;
		try {
			Connection con = DbConnect.getConnection();
			String query = "select particular,paymentterms from purchaseorder where uuid='"
					+ uuid + "'";
			Statement st = con.createStatement();
			rs = st.executeQuery(query);
		} catch (SQLException ex) {
			System.out.println("Exception is "+ex);
		}
		return rs;
	}

	public static boolean insertPurchaseOrder(String vendorid,
			String clientid, String unittype, String rate, String duration,
			String sdate, String edate, String particular, String pterms,
			String pono, String postatus, String pstatus,String stextra,String cdate,String currency) {
		boolean status = false;
		try {
			Connection con = DbConnect.getConnection();
			String uuid = UuidGenerator.createUUID();
			String query = "insert into purchaseorder(uuid,vendorid,clientid,createdate,unit,rate,duration,startdate,enddate,particular,paymentterms,purchaseorderno,purchasestatus,paymentstatus,stextra,currency) values('"
					+ uuid
					+ "','"
					+ vendorid
					+ "','"
					+ clientid
					+ "','"+cdate+"','"
					+ unittype
					+ "','"
					+ rate
					+ "','"
					+ duration
					+ "','"
					+ sdate
					+ "','"
					+ edate
					+ "','"
					+ particular
					+ "','"
					+ pterms
					+ "','"
					+ pono
					+ "','"
					+ postatus + "','" + pstatus + "','"+stextra+"','"+currency+"')";
			System.out.println(query);
			Statement st = con.createStatement();
			int x = st.executeUpdate(query);
			if (x > 0) {
				status = true;
			}
		} catch (SQLException ex) {
			System.out.println("Exception is "+ex);
		}
		return status;
	}

	public static String getFormatedParticularwithLink(String text, String uuid) {
		String t=text;
		if(text.length()>6)
		{
			t=text.substring(0, 6);
		}
		String formated_particular = "<a href='#' class='popuplink' onclick='getParticular(\"" + uuid
				+ "\")'>"+t
				+ "...</a>";
		
		return formated_particular;

	}

}
