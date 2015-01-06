package com.jjit.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class VendorDao {
	public static ResultSet get_vendors(String var1, String var2, String var3) {
		ResultSet rs = null;
		try {
			Connection con = DbConnect.getConnection();
			String query = "SELECT * FROM vendor where status='a' ORDER BY "
					+ var1 + " LIMIT " + var2 + "," + var3 + ";";
			Statement st = con.createStatement();
			rs = st.executeQuery(query);
		} catch (SQLException ex) {
			System.out.println("Exception is "+ex);
		}
		return rs;
	}

	public static boolean insertvendor(String clientname, String companyname,
			String contectno, String email, String address, String city,
			String url) {
		boolean status = false;
		try {
			Connection con = DbConnect.getConnection();
			String uuid = UuidGenerator.createUUID();
			String query = "insert into vendor(uuid,contactperson,company,contactno,email,address,city,url) values('"
					+ uuid
					+ "','"
					+ clientname
					+ "','"
					+ companyname
					+ "','"
					+ contectno
					+ "','"
					+ email
					+ "','"
					+ address
					+ "','"
					+ city + "','" + url + "')";
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

	public static boolean updatevendor(String uuid, String clientname,
			String companyname, String contectno, String email, String address,
			String city, String url) {
		boolean status = false;
		try {
			Connection con = DbConnect.getConnection();
			String query = "UPDATE vendor SET contactperson = '" + clientname
					+ "',company='" + companyname + "',contactno='" + contectno
					+ "',email='" + email + "',address='" + address
					+ "',city='" + city + "',url='" + url + "' where uuid='"
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

	public static boolean deletevendor(String uuid) {
		boolean status = false;
		try {
			Connection con = DbConnect.getConnection();
			String query = "update vendor set status='d' WHERE uuid = '" + uuid
					+ "'";
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

	public static int get_vendors_count() {
		int counts = 0;
		try {
			Connection con = DbConnect.getConnection();
			String query = "SELECT COUNT(*) AS RecordCount FROM vendor  where status='a'";
			Statement st = con.createStatement();
			ResultSet rs = st.executeQuery(query);
			if (rs.next()) {
				counts = rs.getInt(1);
			}

		} catch (SQLException ex) {
			System.out.println("Exception is "+ex);
		}
		return counts;
	}

	public static ResultSet get_vendors_lastrecord() {
		ResultSet rs = null;
		try {
			Connection con = DbConnect.getConnection();
			String query = "SELECT * FROM vendor WHERE uuid = LAST_INSERT_ID();";
			Statement st = con.createStatement();
			rs = st.executeQuery(query);

		} catch (SQLException ex) {
			System.out.println("Exception is "+ex);
		}
		return rs;
	}


}
