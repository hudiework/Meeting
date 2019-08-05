package zmmNew;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;
import cwu.jsj.dao.MeetingPart_MyMeetingDao;
import cwu.jsj.entity.MeetingBean;

public class ParticipantMeetingServlet extends HttpServlet {

	/**
	 * Constructor of the object.
	 */
	public ParticipantMeetingServlet() {
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
		doPost(request, response);    
        response.getWriter().append("Served at: ").append(request.getContextPath());
		
	}

	
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// 输出流   设置编码 
        response.setCharacterEncoding("utf-8");
        PrintWriter out = response.getWriter();  
        // 设置输入流编码形式    
        request.setCharacterEncoding("utf-8");      
        // 获取传入数据    
        String mobilePhone = request.getParameter("mobilePhone");    
        if(mobilePhone==null||mobilePhone.isEmpty()){
        	System.out.print("未接收到客户端传来的数据！"); 
        	out.print("empty");//返回为空
        }else{
        System.out.println("meetingId:" + mobilePhone + " --获取到终端数据！！！");
        // 访问数据库    
        List<List<MeetingBean>> meetingList=new ArrayList<List<MeetingBean>>();
        ParticipantMeetingDao myDao = new ParticipantMeetingDao();
        try {
        	meetingList=myDao.queryParticipantOngingMeeting(mobilePhone);
        	
			System.out.println("获取到所有的会议信息："+meetingList.get(0).toString()+meetingList.get(1).toString());
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
       String jsonStr=JSONArray.fromObject(meetingList).toString();
       out.print(jsonStr);
        }
		
	}

	
	public void init() throws ServletException {
		// Put your code here
	}

}
