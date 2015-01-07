package com.jjit.servlet.vendor;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.ResultSet;
import java.util.HashMap;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.simple.JSONObject;

import com.jjit.dao.VendorDao;


/**
 * Servlet implementation class VendorInfo
 */
@WebServlet("/VendorInfo")
public class VendorInfo extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public VendorInfo() {
        super();
        // TODO Auto-generated constructor stub
    }

    protected void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		PrintWriter out=response.getWriter();
		try {
			String action=request.getParameter("action");
			if("list".equals(action))
			{
				String var1=request.getParameter("jtSorting");
				String var2=request.getParameter("jtStartIndex");
				String var3=request.getParameter("jtPageSize");
				int recordcount = VendorDao.get_vendors_count();
				ResultSet rs = VendorDao.get_vendors(var1,var2,var3);
				HashMap<Integer,Object> rows=new HashMap<Integer,Object>();
				int i=0;
				while(rs.next())
				{
					HashMap<String,String> row=new HashMap<String,String>();
					row.put("uuid",rs.getString(1));
					row.put("contactperson",rs.getString(2));
					row.put("company",rs.getString(3));
					row.put("contactno",rs.getString(4));
					row.put("email",rs.getString(5));
					row.put("address",rs.getString(6));
					row.put("city",rs.getString(7));
					row.put("url",rs.getString(8));
					rows.put(i, row);
				    i++;
				}
				HashMap<String,Object> jTableResult = new HashMap<String,Object>();
				jTableResult.put("Result", "OK");
				jTableResult.put("TotalRecordCount",recordcount);
				jTableResult.put("Records" ,rows);
				JSONObject jb=new JSONObject(jTableResult);
				out.println(jb);
			}
			else if("create".equals(action))
			{
				//Insert record into database
				System.out.println("hiii");
				String clientname=request.getParameter("contactperson");
				String companyname=request.getParameter("company");
				String contectno=request.getParameter("contactno");
				String email=request.getParameter("email");
				String address=request.getParameter("address");
				String city=request.getParameter("city");
				String url=request.getParameter("url");
				boolean status=VendorDao.insertvendor(clientname,companyname,contectno,email,address,city,url);
				//Get last inserted record (to return to jTable)
				ResultSet rs = VendorDao.get_vendors_lastrecord();
				HashMap<String,String> row=new HashMap<String,String>();
				while(rs.next())
				{
					row.put("uuid",rs.getString(1));
					row.put("contactperson",rs.getString(2));
					row.put("company",rs.getString(3));
					row.put("contactno",rs.getString(4));
					row.put("email",rs.getString(5));
					row.put("address",rs.getString(6));
					row.put("city",rs.getString(7));
					row.put("url",rs.getString(8));
				}
				HashMap<String,Object> jTableResult = new HashMap<String,Object>();
				jTableResult.put("Result","OK");
				jTableResult.put("Record",row);
				JSONObject jb=new JSONObject(jTableResult);
				out.println(jb);
			}
			else if("update".equals(action))
			{
				//Update record in database
				String uuid=request.getParameter("uuid");
				String clientname=request.getParameter("contactperson");
				String companyname=request.getParameter("company");
				String contectno=request.getParameter("contactno");
				String email=request.getParameter("email");
				String address=request.getParameter("address");
				String city=request.getParameter("city");
				String url=request.getParameter("url");
				boolean status = VendorDao.updatevendor(uuid,clientname,companyname,contectno,email,address,city,url);

				//Return result to jTable
				HashMap<String,Object> jTableResult = new HashMap<String,Object>();
				jTableResult.put("Result","OK");
				JSONObject jb=new JSONObject(jTableResult);
				out.println(jb);
				
			}
			else if("delete".equals(action))
			{
				//Delete from database
				String uuid=request.getParameter("uuid");
				boolean result =VendorDao.deletevendor(uuid);
				//Return result to jTable
				HashMap<String,Object> jTableResult = new HashMap<String,Object>();
				jTableResult.put("Result","OK");
				JSONObject jb=new JSONObject(jTableResult);
				out.println(jb);
				
			}
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
