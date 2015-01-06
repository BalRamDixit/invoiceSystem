package com.jjit.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class ClientDao {
	
	public static ResultSet get_clients(String var1, String var2, String var3) {
		ResultSet result = null;
		try {
			Connection con = DbConnect.getConnection();
			String query = "SELECT * FROM client where status='a' ORDER BY "
					+ var1 + " LIMIT " + var2 + "," + var3 + ";";
			Statement st = con.createStatement();
			result = st.executeQuery(query);
		} catch (SQLException ex) {
			System.out
					.println("Erron in fetching data from client on get_Clients()\n"+ex);
		}
		return result;
	}

	public static boolean insertclient(String clientname, String companyname,
			String contectno, String email, String address, String city,
			String url) {
		boolean status = false;
		try {
			Connection con = DbConnect.getConnection();
			String uuid = UuidGenerator.createUUID();
			String query = "insert into client(uuid,contactperson,company,contactno,email,address,city,url) values('"
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
			System.out.println(query);
			Statement st = con.createStatement();
			int x = st.executeUpdate(query);
			if (x > 0) {
				status = true;
			}
		} catch (SQLException ex) {
			System.out
					.println("Erron in insert data into client on insertclient()\n"+ex);
		}
		return status;
	}

	public static boolean updateclient(String uuid, String clientname,
			String companyname, String contectno, String email, String address,
			String city, String url) {
		boolean status = false;
		try {
			Connection con = DbConnect.getConnection();
			String query = "UPDATE client SET contactperson = '" + clientname
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

	public static boolean deleteclient(String uuid) {
		boolean status = false;
		try {
			Connection con = DbConnect.getConnection();
			String query = "update client set status='d' WHERE uuid = '" + uuid
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

	public static int get_clients_count() {
		int records = 0;
		try {
			Connection con = DbConnect.getConnection();
			String query = "SELECT COUNT(*) AS RecordCount FROM client  where status='a'";
			Statement st = con.createStatement();
			ResultSet rs = st.executeQuery(query);
			if (rs.next()) {
				records = rs.getInt(1);
			}
		} catch (SQLException ex) {
			System.out.println("Exception is "+ex);
		}
		return records;
	}

	public static ResultSet get_clients_lastrecord() {
		ResultSet rs = null;
		try {
			Connection con = DbConnect.getConnection();
			String query = "SELECT * FROM client WHERE uuid = LAST_INSERT_ID();";
			Statement st = con.createStatement();
			rs = st.executeQuery(query);
		} catch (SQLException ex) {
			System.out.println("Exception is "+ex);
		}
		return rs;
	}



}
