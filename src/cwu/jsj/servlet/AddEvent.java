package cwu.jsj.servlet;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;
import zmmNew.ChildMeetingBean;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import cwu.jsj.dao.MeetingDao;
import cwu.jsj.entity.MeetingBean;
import cwu.jsj.entity.WorkerBean;

public class AddEvent extends HttpServlet {

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
		String jsonStr = "";
		PrintWriter out = response.getWriter();
		request.setCharacterEncoding("utf-8");

		String meetingStr = request.getParameter("meeting");
		System.out.println("meetingStr:" + meetingStr);
		MeetingBean meeting = gson.fromJson(meetingStr, MeetingBean.class);

		String childMeetingListStr = request.getParameter("childMeetingList");
		System.out.println("childMeetingListStr:" + childMeetingListStr);
		

		String workerSetStr = request.getParameter("workerSet");
		HashSet<WorkerBean> workerSet = gson.fromJson(workerSetStr,
				new TypeToken<HashSet<WorkerBean>>() {
				}.getType());

		String leaderSetStr = request.getParameter("leaderSet");
		HashSet<WorkerBean> leaderSet = gson.fromJson(leaderSetStr,
				new TypeToken<HashSet<WorkerBean>>() {
				}.getType());

		String secretarySetStr = request.getParameter("secretarySet");
		HashSet<WorkerBean> secretarySet = gson.fromJson(secretarySetStr,
				new TypeToken<HashSet<WorkerBean>>() {
				}.getType());

		MeetingDao serv = new MeetingDao();
		int meetingId = serv.addMeeting(meeting);
		System.out.println("插入的主会议id是：" + meetingId);
		if (meetingId == 0) {
			System.out.println("主会议插入失败");
			jsonStr = "failure";
		} else {
			if (childMeetingListStr=="") {
				System.out.println("此会议没有子会议");
			} else {
				List<ChildMeetingBean> childMeetingList = gson.fromJson(
						childMeetingListStr, new TypeToken<List<ChildMeetingBean>>() {
						}.getType());
				for (ChildMeetingBean childMeet : childMeetingList) {
					jsonStr = serv.InsertChild(meetingId, childMeet);
				}
			}

			for (WorkerBean w : workerSet) {
				jsonStr = serv.InsertWorkers(meetingId, w, "普通工作人员");
				//通知他：
				
				ServletContext application =this.getServletContext();    //this是这个页面
				 RequestDispatcher rd = application.getRequestDispatcher("/AssignRolesServlet");
				 request.setAttribute("role","普通工作人员");
				 request.setAttribute("meetingId", meetingId);
				 rd.forward(request, response);
				
			}
			for (WorkerBean l : leaderSet) {
				jsonStr = serv.InsertWorkers(meetingId, l, "主管");
				
//通知他：
				
				ServletContext application =this.getServletContext();    //this是这个页面
				 RequestDispatcher rd = application.getRequestDispatcher("/AssignRolesServlet");
				 request.setAttribute("role","主管");
				 request.setAttribute("meetingId", meetingId);
				 rd.forward(request, response);
			}
			for (WorkerBean s : secretarySet) {
				jsonStr = serv.InsertWorkers(meetingId, s, "秘书");
				
//通知他：
				
				ServletContext application =this.getServletContext();    //this是这个页面
				 RequestDispatcher rd = application.getRequestDispatcher("/AssignRolesServlet");
				 request.setAttribute("role", "秘书");
				 request.setAttribute("meetingId", meetingId);
				 rd.forward(request, response);
			}

		}

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

	public Map<String, Object> toMap(Object object) {
		Map<String, Object> data = new HashMap<String, Object>();
		// 将json字符串转换成jsonObject
		JSONObject jsonObject = JSONObject.fromObject(object);
		Iterator ite = jsonObject.keys();
		// 遍历jsonObject数据,添加到Map对象
		while (ite.hasNext()) {
			String key = ite.next().toString();
			String value = jsonObject.get(key).toString();
			data.put(key, value);
		}
		// 或者直接将 jsonObject赋值给Map
		// data = jsonObject;
		return data;
	}

}
