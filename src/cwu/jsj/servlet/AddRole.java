package cwu.jsj.servlet;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.bind.annotation.W3CDomHandler;

import com.google.gson.Gson;

import cwu.jsj.dao.RoleDao;
import cwu.jsj.dao.WorkerDao;
import cwu.jsj.entity.RoleBean;

public class AddRole extends HttpServlet {

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

		Gson gson = new Gson();
		String jsonStr = inputStreamTOString(request.getInputStream(), "utf-8");
		System.out.print(jsonStr);
		RoleBean role = gson.fromJson(jsonStr, RoleBean.class);
		// 这里从客户端传来的role对象中的workId是手机号 需要查询一次
		RoleDao roleDao = new RoleDao();
		WorkerDao workerDao = new WorkerDao();
		try {
			role.setWorkerId(workerDao.selectIdByPhone(role.getWorkerId()));
			if (roleDao.addRole(role))
				jsonStr = "1";
			else
				jsonStr = "0";
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			jsonStr = "0";
		}

		PrintWriter out = response.getWriter();
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
