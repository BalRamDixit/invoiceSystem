package com.jjit.servlet.purchaseorder;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.ResultSet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.jjit.dao.PurchaseOrderDao;

/**
 * Servlet implementation class ParticularInfo
 */
@WebServlet("/ParticularInfo")
public class ParticularInfoServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ParticularInfoServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

    protected void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		PrintWriter out=response.getWriter();
		try {
			String uuid=request.getParameter("uuid");
			ResultSet rs=PurchaseOrderDao.getParticular(uuid);
			if(rs.next())
			{
				out.println("<h2>Particular</h2>");
				out.println("<h3><pre>"+rs.getString(1)+"</pre></h3>");
				
				out.println("<h2>PaymentTerms</h2>");
				out.println("<h3><pre>"+rs.getString(2)+"</pre></h3>");
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
