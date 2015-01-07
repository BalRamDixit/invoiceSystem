package com.jjit.servlet.Invoice;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import com.jjit.pojo.ExpenseBo;
import com.jjit.pojo.PurchaseOrderBo;

/**
 * Servlet implementation class InsertExpense
 */
@WebServlet("/InsertExpense")
public class InsertExpenseServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public InsertExpenseServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

    protected void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		PrintWriter out=response.getWriter();
		try {
			String data = request.getParameter("data");
			JSONParser jb=new JSONParser();
			JSONObject jo=(JSONObject)jb.parse(data);
			String exp=(String)jo.get("exp");
			String val=(String)jo.get("val");
			String inc=(String)jo.get("inc");
			PurchaseOrderBo pobean=(PurchaseOrderBo)request.getSession().getAttribute("purchaseOrder");
			ExpenseBo e=new ExpenseBo();
			e.setCost(val);
			e.setDetail(exp);
			if(inc.equals("true"))
			{
				e.setInclusive(true);
			}
			else
			{
				e.setInclusive(false);
			}
			HashMap<Integer,ExpenseBo> ex=pobean.getExpenses();
			ex.put(ex.size()+1, e);
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
