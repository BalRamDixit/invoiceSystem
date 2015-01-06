package com.jjit.servlet.Invoice;

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

import com.jjit.dao.InvoiceDao;
import com.jjit.pojo.PurchaseOrderBo;

/**
 * Servlet implementation class PurchaseOrderNo
 */
@WebServlet("/PurchaseOrderNo")
public class PurchaseOrderNo extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public PurchaseOrderNo() {
        super();
        // TODO Auto-generated constructor stub
    }

    protected void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		PrintWriter out=response.getWriter();
		try {
			ResultSet rs=InvoiceDao.getpurchaseorderno();
			HashMap<Integer,Object> rows=new HashMap<Integer,Object>();
			JSONArray ja=new JSONArray();
			int i=0;
			while(rs.next())
			{
				HashMap<String,String> row=new HashMap<String,String>();
				String purchaseorderno=rs.getString("purchaseorderno");
				String paymentterms=rs.getString("paymentterms");
				String pterms=paymentterms;
				if(paymentterms.length()>6)
				{
					pterms=paymentterms.substring(0,6);
				}
				row.put("purchaseorderno",purchaseorderno);
				row.put("paymentterms",pterms);
				JSONObject ob=new JSONObject(row);
				ja.add(i, ob);
				i++;
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
