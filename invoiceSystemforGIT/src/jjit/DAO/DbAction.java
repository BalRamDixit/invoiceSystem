package jjit.DAO;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class DbAction {

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
					.println("Erron in fetching data from client on get_Clients()");
		}
		return result;
	}

	public static boolean insertclient(String clientname, String companyname,
			String contectno, String email, String address, String city,
			String url) {
		boolean status = false;
		try {
			Connection con = DbConnect.getConnection();
			String uuid = uuid_generator.createUUID();
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
					.println("Erron in insert data into client on insertclient()");
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

		}
		return rs;
	}

	public static ResultSet get_vendors(String var1, String var2, String var3) {
		ResultSet rs = null;
		try {
			Connection con = DbConnect.getConnection();
			String query = "SELECT * FROM vendor where status='a' ORDER BY "
					+ var1 + " LIMIT " + var2 + "," + var3 + ";";
			Statement st = con.createStatement();
			rs = st.executeQuery(query);
		} catch (SQLException ex) {

		}
		return rs;
	}

	public static boolean insertvendor(String clientname, String companyname,
			String contectno, String email, String address, String city,
			String url) {
		boolean status = false;
		try {
			Connection con = DbConnect.getConnection();
			String uuid = uuid_generator.createUUID();
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

		}
		return rs;
	}

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

	public static boolean insertpurchaseOrder(String vendorid, String clientid,
			String unit, String rate, String duration, String startdate,
			String enddate, String particular, String paymentterms,
			String purchaseorderno) {
		boolean status = false;
		try {
			Connection con = DbConnect.getConnection();
			String uuid = uuid_generator.createUUID();
			String query = "insert into purchaseorder(uuid,vendorid,clientid,createdate,unit,rate,duration,startdate,enddate,particular,paymentterms,purchaseorderno) values('"
					+ uuid
					+ "','"
					+ vendorid
					+ "','"
					+ clientid
					+ "',cur_date(),'"
					+ unit
					+ "','"
					+ rate
					+ "','"
					+ duration
					+ "','"
					+ startdate
					+ "','"
					+ enddate
					+ "','"
					+ particular
					+ "','" + paymentterms + "','" + purchaseorderno + "')";
			Statement st = con.createStatement();
			int x = st.executeUpdate(query);
			if (x > 0) {
				status = true;
			}
		} catch (SQLException ex) {

		}
		return status;
	}

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

	public static ResultSet getdetailforinvoicebypono(String pono) {
		ResultSet rs = null;
		try {
			Connection con = DbConnect.getConnection();
			String query = "SELECT rate*duration as total,stextra,particular,paymentterms,purchaseorderno FROM purchaseorder WHERE status='a' and purchaseorderno = '"
					+ pono + "'";
			Statement st = con.createStatement();
			rs = st.executeQuery(query);
		} catch (SQLException ex) {

		}
		return rs;
	}

	public static ResultSet getpurchaseorderno() {
		ResultSet rs = null;
		try {
			Connection con = DbConnect.getConnection();
			String query = "SELECT purchaseorderno FROM purchaseorder WHERE status='a'";
			Statement st = con.createStatement();
			rs = st.executeQuery(query);
		} catch (SQLException ex) {

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

		}
		return rs;
	}

	public static boolean insert_purchase_order(String vendorid,
			String clientid, String unittype, String rate, String duration,
			String sdate, String edate, String particular, String pterms,
			String pono, String postatus, String pstatus,String stextra) {
		boolean status = false;
		try {
			Connection con = DbConnect.getConnection();
			String uuid = uuid_generator.createUUID();
			String query = "insert into purchaseorder(uuid,vendorid,clientid,createdate,unit,rate,duration,startdate,enddate,particular,paymentterms,purchaseorderno,purchaseorderstatus,paymentstatus,stextra) values('"
					+ uuid
					+ "','"
					+ vendorid
					+ "','"
					+ clientid
					+ "',curdate(),'"
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
					+ postatus + "','" + pstatus + "','"+stextra+"')";
			System.out.println(query);
			Statement st = con.createStatement();
			int x = st.executeUpdate(query);
			if (x > 0) {
				status = true;
			}
		} catch (SQLException ex) {

		}
		return status;
	}

	public static ResultSet get_purchaseorder1() {
		ResultSet rs = null;
		try {
			Connection con = DbConnect.getConnection();
			String query = "SELECT p.uuid,v.contactperson as vendorname,c.contactperson as clientname ,p.createdate,p.unit as xunit,p.rate,p.duration,p.startdate,p.enddate,p.particular,p.paymentterms,p.purchaseorderno,p.paymentstatus,p.stextra,p.status FROM purchaseorder p,vendor v,client c where p.vendorid=v.uuid and p.clientid=c.uuid and p.status='a'";
			Statement st = con.createStatement();
			rs = st.executeQuery(query);
		} catch (SQLException ex) {

		}
		return rs;
	}

	public static String getFormatedParticularwithLink(String text, String uuid) {
		String formated_particular = text.substring(0, 6)
				+ "<a href='#subbody4' data-rel='popup' class='ui-btn ui-btn-inline ui-corner-all' onclick='getParticular(\"" + uuid
				+ "\")'>..more</a>";
		
		return formated_particular;

	}
}