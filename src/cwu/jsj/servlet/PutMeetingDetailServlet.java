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

import com.google.gson.Gson;

import cwu.jsj.dao.HotelDao;
import cwu.jsj.dao.ParticipantsDao;
import cwu.jsj.dao.PlaceDao;
import cwu.jsj.entity.HotelBean;
import cwu.jsj.entity.PlaceBean;



public class PutMeetingDetailServlet extends HttpServlet {

	/**
	 * Constructor of the object.
	 */
	public PutMeetingDetailServlet() {
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
		 	// 获取的酒店预订信息； 
	        List<HotelBean> hotelList=new ArrayList<HotelBean>();   
	        //获取场地预订情况
	        List<PlaceBean> placeList=new ArrayList<PlaceBean>();
	        // 输出流    
	        response.setCharacterEncoding("utf-8");
	        PrintWriter out = response.getWriter();  
	        // 设置编码形式    
	        request.setCharacterEncoding("utf-8");      
	        // 获取传入数据    
	        String meetingId = request.getParameter("meetingId");    
	        //jsonString
	        Gson gson=new Gson();
	        String jsonStr="";
	        if(meetingId==null||meetingId.isEmpty()){
	        	out.print("未接收到客户端传来的数据！");  
	        }else{
	        System.out.println("meetingId:" + meetingId + " --获取到终端数据！！！");
	    
	        // 访问数据库    
	        HotelDao hotelDao=new HotelDao();
			PlaceDao placeDao=new PlaceDao();
	        try {
				hotelList=hotelDao.getHotelDetailByMeetingId(meetingId);
				placeList=placeDao.getPlaceDetailByMeetingId(meetingId);
				String hotel;
				String place;
				if (hotelList.size()==0&&placeList.size()==0){
					out.print("0");//暂无信息
				}else if(hotelList.size()==0&&placeList.size()!=0){
					place= gson.toJson(placeList);
					out.print("10");
					out.print(place);
				}else if(hotelList.size()!=0&&placeList.size()==0){
					hotel= gson.toJson(hotelList);
					out.print("01");
					out.print(hotel);
				}else{
					 hotel=gson.toJson(hotelList);
					 place=gson.toJson(placeList);
					 out.print("11");
					out.print(hotel);
					out.print("0");
					out.print(place);
				}
				
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	        }
	}

	public void init() throws ServletException {
		// Put your code here
	}

}
