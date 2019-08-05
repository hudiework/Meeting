package cwu.jsj.servlet;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

import cwu.jsj.dao.RegisterDao;
import cwu.jsj.dao.WorkerDao;
import cwu.jsj.entity.WorkerBean;

public class CheckPhone extends HttpServlet {

	/**
	 * Destruction of the servlet. <br>
	 */
	public void destroy() {
		super.destroy(); // Just puts "destroy" string in log
		// Put your code here
	}

	/**
	 * The doGet method of the servlet. <br>
	 * 
	 * This method is called when a form has its tag value method equals to get.
	 * 
	 * @param request
	 *            the request send by the client to the server
	 * @param response
	 *            the response send by the server to the client
	 * @throws ServletException
	 *             if an error occurred
	 * @throws IOException
	 *             if an error occurred
	 */
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String jsonStr = "";
		response.setCharacterEncoding("utf-8");
		PrintWriter out = response.getWriter();
		request.setCharacterEncoding("utf-8");
		String phone = request.getParameter("phone");

		WorkerBean worker = new WorkerBean();
		RegisterDao registerDao = new RegisterDao();
		WorkerDao workdao = new WorkerDao();
		if (registerDao.checkPhone(phone)) {
			try {
				worker = workdao.queryWorkerByPhone(phone);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			if (worker != null){
				jsonStr = new Gson().toJson(worker);
			}	
			else
				jsonStr = "1";
		} else
			jsonStr = "0";
		System.out.println("打印输出worker:");
		out.print(jsonStr);
		out.flush();
		out.close();
	}

	public static String inputStreamTOString(InputStream in, String encoding)
			throws IOException {
		ByteArrayOutputStream outStream = new ByteArrayOutputStream();
		byte[] data = new byte[4096];
		int count = -1;
		while ((count = in.read(data, 0, 4096)) != -1)
			outStream.write(data, 0, count);
		return new String(outStream.toByteArray(), encoding);
	}

	/**
	 * The doPost method of the servlet. <br>
	 * 
	 * This method is called when a form has its tag value method equals to
	 * post.
	 * 
	 * @param request
	 *            the request send by the client to the server
	 * @param response
	 *            the response send by the server to the client
	 * @throws ServletException
	 *             if an error occurred
	 * @throws IOException
	 *             if an error occurred
	 */
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		doGet(request, response);
	}

	/**
	 * Initialization of the servlet. <br>
	 * 
	 * @throws ServletException
	 *             if an error occurs
	 */
	public void init() throws ServletException {
		// Put your code here
	}

}
