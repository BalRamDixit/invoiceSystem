package com.jjit.servlet.purchaseorder;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.ResultSet;
import java.util.HashMap;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import com.jjit.dao.PurchaseOrderDao;

/**
 * Servlet implementation class VendorList
 */
@WebServlet("/VendorList")
public class VendorList extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public VendorList() {
        super();
        // TODO Auto-generated constructor stub
    }

    protected void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		PrintWriter out=response.getWriter();
		try {
			ResultSet rs = PurchaseOrderDao.get_vendorsforpo();
			JSONArray ja = new JSONArray();
			int i = 0;
			while (rs.next()) {
				HashMap<String, String> row = new HashMap<String, String>();
				String uuid = rs.getString("uuid");
				String contactperson = rs.getString("contactperson");
				String email = rs.getString("email");
				String city = rs.getString("city");
				row.put("uuid", uuid);
				row.put("contactperson", contactperson + " - " + city);
				JSONObject jb = new JSONObject(row);
				ja.add(i, jb);
			}
			out.println(ja);
		} catch (Exception e) {
			// TODO: handle exception
		}
	}
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		processRequest(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		processRequest(request, response);
	}

}
