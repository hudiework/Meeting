package cwu.jsj.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;
import cwu.jsj.dao.MeetingDao;
import cwu.jsj.entity.MeetingBean;

public class AssignRolesServlet extends HttpServlet {

	/**
	 * Constructor of the object.
	 */
	public AssignRolesServlet() {
		super();
	}

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
	 * @param request the request send by the client to the server
	 * @param response the response send by the server to the client
	 * @throws ServletException if an error occurred
	 * @throws IOException if an error occurred
	 */
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

	
	}

	/**
	 * The doPost method of the servlet. <br>
	 *
	 * This method is called when a form has its tag value method equals to post.
	 * 
	 * @param request the request send by the client to the server
	 * @param response the response send by the server to the client
	 * @throws ServletException if an error occurred
	 * @throws IOException if an error occurred
	 */
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String role=(String) request.getAttribute("role");
		String meetingId=(String) request.getAttribute("meetingId");
		MeetingDao meetingDao=new MeetingDao();
		MeetingBean meeting=meetingDao.selectMeetingByMeetingId(meetingId);
		meeting.setMyRole(role);
		response.setCharacterEncoding("utf-8");
		PrintWriter out = response.getWriter();
		request.setCharacterEncoding("utf-8");
		 String jsonStr=JSONArray.fromObject(meeting).toString();
	        System.out.println("获取到所有的会议信息："+jsonStr);
	        out.print(jsonStr);
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
