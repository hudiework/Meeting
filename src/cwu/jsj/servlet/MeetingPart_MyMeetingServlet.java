package cwu.jsj.servlet;

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
import cwu.jsj.dao.MeetingPart_MyMeetingDaoAgain;
import cwu.jsj.entity.MeetingBean;

public class MeetingPart_MyMeetingServlet extends HttpServlet {

	/**
	 * Constructor of the object.
	 */
	public MeetingPart_MyMeetingServlet() {
		super();
	}

	/**
	 * Destruction of the servlet. <br>
	 */
	public void destroy() {
		super.destroy(); // Just puts "destroy" string in log
		// Put your code here
	}

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
        // 设置编码形式    
        request.setCharacterEncoding("utf-8");      
        // 获取传入数据    
        String registerMobilePhone = request.getParameter("registerMobilePhone");    
        if(registerMobilePhone==null||registerMobilePhone.isEmpty()){
        	System.out.print("未接收到客户端传来的数据！"); 
        	out.print("empty");//返回为空
        }else{
        System.out.println("registerMobilePhone:" + registerMobilePhone + " --获取到终端数据！！！");
        // 访问数据库    
       List<List<MeetingBean>> meetingList =new ArrayList<List<MeetingBean>>();
        MeetingPart_MyMeetingDaoAgain myDao = new MeetingPart_MyMeetingDaoAgain();
        try {
			meetingList=myDao.queryWorkerMeeting(registerMobilePhone);
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        String jsonStr=JSONArray.fromObject(meetingList).toString();
        System.out.println("获取到所有的会议信息："+jsonStr);
        out.print(jsonStr);
        }

	}

	
	public void init() throws ServletException {
		// Put your code here
	}

}
