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

public class ParticipantGetMyChildMeetingServlet extends HttpServlet {

	/**
	 * Constructor of the object.
	 */
	public ParticipantGetMyChildMeetingServlet() {
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
        // 设置输入流编码形式    
        request.setCharacterEncoding("utf-8");      
        // 获取传入数据    
        String participantTelNum = request.getParameter("participantTelNum");    
       String mainMeetingId=request.getParameter("mainMeetingId");
        if(participantTelNum==null||participantTelNum.isEmpty()){
        	System.out.print("未接收到客户端传来的数据！"); 
        	out.print("empty");//返回为空
        }else{
        System.out.println("participantTelNum:" + participantTelNum + " --获取到终端数据！！！");
        // 访问数据库    
        List<ChildMeetingBean> childMeetingList=new ArrayList<ChildMeetingBean>();
        ParticipantChildMeetingDao myDao = new ParticipantChildMeetingDao();
        try {
        	childMeetingList=myDao.getMyChildmeetingByParticipantTelNum(participantTelNum, mainMeetingId);
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
       String jsonStr=JSONArray.fromObject(childMeetingList).toString();
       System.out.println("获取到我的子会议："+jsonStr);
       out.print(jsonStr);
        }
		
	}

	
	public void init() throws ServletException {
		// Put your code here
	}

}
