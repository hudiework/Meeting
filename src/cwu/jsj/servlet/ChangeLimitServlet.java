package cwu.jsj.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cwu.jsj.dao.FunctionDao;
import cwu.jsj.dao.ParticipantsDao;

/**
 * 该servlet用于返回更改worker权限是否成功的信息！！
 *
 */
public class ChangeLimitServlet extends HttpServlet {

	
	public ChangeLimitServlet() {
		super();
	}

	
	public void destroy() {
		super.destroy(); // Just puts "destroy" string in log
		// Put your code here
	}

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		/*
		 * get方法可以直接获取request的参数 但是要保证名字一致
		 */
		String workerId = request.getParameter("workerId");
		String functionId = request.getParameter("functionId");
		//连接数据库，Update信息
		FunctionDao ser = new FunctionDao();
		boolean isSuccess=false;
		try {
			isSuccess=ser.updateValidByWorkerIdAndFunctionId(workerId, functionId);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		PrintWriter printWriter = response.getWriter();
		printWriter.print(isSuccess);
	}

	
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		out.println("<!DOCTYPE HTML PUBLIC \"-//W3C//DTD HTML 4.01 Transitional//EN\">");
		out.println("<HTML>");
		out.println("  <HEAD><TITLE>A Servlet</TITLE></HEAD>");
		out.println("  <BODY>");
		out.print("    This is ");
		out.print(this.getClass());
		out.println(", using the POST method");
		out.println("  </BODY>");
		out.println("</HTML>");
		out.flush();
		out.close();
	}

	/**
	 * Initialization of the servlet. <br>
	 *
	 * @throws ServletException if an error occurs
	 */
	public void init() throws ServletException {
		// Put your code here
	}

}
