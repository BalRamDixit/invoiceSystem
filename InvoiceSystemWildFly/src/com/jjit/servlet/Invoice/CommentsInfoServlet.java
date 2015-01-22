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
import org.json.simple.parser.JSONParser;

import com.jjit.pojo.ExpenseBo;
import com.jjit.pojo.PurchaseOrderBo;

/**
 * Servlet implementation class CommentsInfoServlet
 */
@WebServlet("/CommentsInfo")
public class CommentsInfoServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CommentsInfoServlet() {
        super();
        // TODO Auto-generated constructor stub
    }


    protected void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		PrintWriter out=response.getWriter();
		try {
			String action=request.getParameter("action");
			PurchaseOrderBo pobean=(PurchaseOrderBo)request.getSession().getAttribute("purchaseOrder");
			HashMap<Integer,String> ex=pobean.getComments();
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
						String expense=ex.get(e);
						HashMap<String,String> row=new HashMap<String,String>();
						row.put("cno",e+"");
						row.put("sno",(i+1)+"");
						row.put("comment",ex.get(e));
						
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
				String cno=request.getParameter("cno");
				String comment=request.getParameter("comment");
				ex.put(Integer.parseInt(cno), comment);
				//Return result to jTable
				HashMap<String,Object> jTableResult = new HashMap<String,Object>();
				jTableResult.put("Result","OK");
				JSONObject jb=new JSONObject(jTableResult);
				out.println(jb);
				
			}
			else if("delete".equals(action))
			{
				//Delete from database
				String cno=request.getParameter("cno");
				ex.remove(Integer.parseInt(cno));
				//Return result to jTable
				HashMap<String,Object> jTableResult = new HashMap<String,Object>();
				jTableResult.put("Result","OK");
				JSONObject jb=new JSONObject(jTableResult);
				out.println(jb);
				
			}
			else if("insert".equals(action))
			{
				//INsert into database
				String data=request.getParameter("data");
				JSONParser jp=new JSONParser();
				JSONObject jo=(JSONObject)jp.parse(data);
				String comment=(String)jo.get("comment");
				System.out.println(ex.size()+1+"    "+ comment);
				ex.put(ex.size()+1, comment);
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