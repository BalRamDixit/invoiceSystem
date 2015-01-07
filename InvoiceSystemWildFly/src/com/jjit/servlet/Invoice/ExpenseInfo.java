package com.jjit.servlet.Invoice;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Set;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.simple.JSONObject;

import com.jjit.pojo.ExpenseBo;
import com.jjit.pojo.PurchaseOrderBo;

/**
 * Servlet implementation class ExpenseInfo
 */
@WebServlet("/ExpenseInfo")
public class ExpenseInfo extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ExpenseInfo() {
        super();
        // TODO Auto-generated constructor stub
    }

    protected void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		PrintWriter out=response.getWriter();
		try {
			String action=request.getParameter("action");
			PurchaseOrderBo pobean=(PurchaseOrderBo)request.getSession().getAttribute("purchaseOrder");
			HashMap<Integer,ExpenseBo> ex=pobean.getExpenses();
			if("list".equals(action))
			{
				String var2=request.getParameter("jtStartIndex");
				String var3=request.getParameter("jtPageSize");
				int recordCount =ex.size();
				HashMap<Integer,Object> rows=new HashMap<Integer,Object>();
				int j=0;
				int i=0;
				Set <Integer> keys= ex.keySet();
				for(Integer e:keys)
				{
					if(i>=Integer.parseInt(var2)&&i<Integer.parseInt(var2)+Integer.parseInt(var3))
					{
						ExpenseBo expense=ex.get(e);
						HashMap<String,String> row=new HashMap<String,String>();
						row.put("exno",(i+1)+"");
						row.put("exp",expense.getDetail());
						row.put("cost",expense.getCost());
						row.put("Inclusive",expense.getInclusive()+"");
						rows.put(j, row);
				    	j++;
					}
					i++;
				}
				HashMap<String,Object> jTableResult = new HashMap<String,Object>();
				jTableResult.put("Result", "OK");
				jTableResult.put("TotalRecordCount",recordCount);
				jTableResult.put("Records" ,rows);
				JSONObject jb=new JSONObject(jTableResult);
				out.println(jb);
			}
			else if("update".equals(action))
			{
				//Update record in database
				String expno=request.getParameter("exno");
				String exp=request.getParameter("exp");
				String cost=request.getParameter("cost");
				String Inclusive=request.getParameter("Inclusive");
				
				ExpenseBo e=ex.get(Integer.parseInt(expno));
				HashMap<String,String> row=new HashMap<String,String>();
				//Return result to jTable
				HashMap<String,Object> jTableResult = new HashMap<String,Object>();
				jTableResult.put("Result","OK");
				JSONObject jb=new JSONObject(jTableResult);
				out.println(jb);
				
			}
			else if("delete".equals(action))
			{
				//Delete from database
				String expno=request.getParameter("exno");
				ex.remove(Integer.parseInt(expno)+1);
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
