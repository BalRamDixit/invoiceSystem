package com.jjit.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class InvoiceDao {

	
	public static ResultSet getdetailforinvoicebypono(String pono) {
		ResultSet rs = null;
		try {
			Connection con = DbConnect.getConnection();
			String query = "SELECT uuid,rate,duration,stextra,particular,paymentterms,purchaseorderno,currency FROM purchaseorder WHERE status='a' and purchaseorderno = '"
					+ pono + "'";
			Statement st = con.createStatement();
			rs = st.executeQuery(query);
		} catch (SQLException ex) {
			System.out.println("Exception is "+ex);
		}
		return rs;
	}

	public static ResultSet getpurchaseorderno() {
		ResultSet rs = null;
		try {
			Connection con = DbConnect.getConnection();
			String query = "SELECT purchaseorderno,paymentterms FROM purchaseorder WHERE status='a'";
			Statement st = con.createStatement();
			rs = st.executeQuery(query);
		} catch (SQLException ex) {
			System.out.println("Exception is "+ex);
		}
		return rs;
	}
	
	public static ResultSet getpurchaseorderlist() {
		ResultSet rs = null;
		try {
			Connection con = DbConnect.getConnection();
			String query = "SELECT uuid,purchaseorderno,particular FROM purchaseorder WHERE status='a' order by createdate desc";
			Statement st = con.createStatement();
			rs = st.executeQuery(query);
		} catch (SQLException ex) {
			System.out.println("Exception is "+ex);
		}
		return rs;
	}

	public static ResultSet get_purchaseorder1() {
		ResultSet rs = null;
		try {
			Connection con = DbConnect.getConnection();
			String query = "SELECT p.uuid,v.contactperson as vendorname,c.contactperson as clientname ,p.createdate,p.unit as xunit,p.rate,p.duration,p.startdate,p.enddate,p.particular,p.paymentterms,p.purchasestatus,p.paymentstatus,p.stextra,p.status FROM purchaseorder p,vendor v,client c where p.vendorid=v.uuid and p.clientid=c.uuid and p.status='a'";
			Statement st = con.createStatement();
			rs = st.executeQuery(query);
		} catch (SQLException ex) {
			System.out.println("Exception is "+ex);
		}
		return rs;
	}
	
	public static boolean insertInvoice(String invoiceno,String pono,String  sdate,String  taxvalue,String  total) {
		boolean status = false;
		try {
			Connection con = DbConnect.getConnection();
			String uuid = UuidGenerator.createUUID();
			String query = "insert into invoice(uuid,vendorid,clientid,createdate,unit,rate,duration,startdate,enddate,particular,paymentterms,purchaseorderno,purchasestatus,paymentstatus,stextra) values('"
					+ uuid
					+ "','"
					+ invoiceno
					+ "','"
					+ pono
					+ "','"
					+ sdate
					+ "','"
					+ taxvalue
					+ "','"
					+ total
					+ "')";
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
	
}