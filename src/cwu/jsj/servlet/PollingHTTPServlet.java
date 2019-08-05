package cwu.jsj.servlet;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;
import cwu.jsj.dao.HotelDao;
import cwu.jsj.dao.ParticipantsDao;
import cwu.jsj.dao.PlaceDao;
import cwu.jsj.entity.HotelBean;
import cwu.jsj.entity.PlaceBean;



public class PollingHTTPServlet extends HttpServlet{
	public static int BUFFER_SIZE = 4096; //buffer_size
	private static final long serialVersionUID = 1L;

	public PollingHTTPServlet() {
		// TODO Auto-generated constructor stub
		super();
	}

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		//设置输出流的编码方式
		response.setCharacterEncoding("utf-8");
        PrintWriter out = response.getWriter();  
		/*
		 * get方法可以直接获取request的参数 但是要保证名字一致
		 */
		List<String> msgList=new ArrayList<String>();
		String meetingId = request.getParameter("meetingId");
		System.out.println("从移动端获取的meetingId："+meetingId);
		//String password = request.getParameter("password");
		HotelDao hotelDao=new HotelDao();
		PlaceDao placeDao=new PlaceDao();
		ParticipantsDao participantsDao=new ParticipantsDao();
		
		List<HotelBean> hotelList=new ArrayList<HotelBean>();
		List<PlaceBean> placeList=new ArrayList<PlaceBean>();
		int hotelGalleryful=0;//目前预订的hotel房间可容纳的全部人数
		int placeGalleryful=0;//目前预订的place场地可容纳的全部人数
		int participantNum=0;//目前报名的人数
		
		try {
			participantNum=participantsDao.queryParticipantsNum(meetingId);
			hotelList=hotelDao.getHotelDetailByMeetingId(meetingId);
			placeList=placeDao.getPlaceDetailByMeetingId(meetingId);
			for(int i=0;i<hotelList.size();i++){
				//第一种类型的房间所容纳的全部人数
				hotelGalleryful=hotelList.get(i).getHotelRoomNum()*hotelList.get(i).getHotelGalleryful()+hotelGalleryful;
			}//遍历结束，得到hotel可容纳的全部人数。
			for(int i=0;i<placeList.size();i++){
				//第一种类型的会议室所容纳的全部人数
				placeGalleryful=placeList.get(i).getPlaceNum()*placeList.get(i).getPlaceGalleryful()+placeGalleryful;
			}//遍历结束，得到hotel可容纳的全部人数。
			
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if(participantNum >hotelGalleryful-3){
			msgList.add("剩余房间数量少于3个！");
		}
		if(participantNum>=placeGalleryful-5){
			msgList.add("会议场地最多还能容纳5人！");
		}
		PrintWriter printWriter = response.getWriter();
		/**
		 * 设置假数据：
		 */
		msgList.add("我是一条虚假信息！！");
		msgList.add("点我啊点我啊");
		msgList.add("啦啦啦啦哈哈哈哈");
		String jsonStr=JSONArray.fromObject(msgList).toString();
		System.out.println("输出打印msgList，看看是否乱码："+jsonStr);
		out.print(jsonStr);

	}
	
	/*post由于客户端以输出流的方法写出
	 * 所以要获取输入流
	 * 调用request.getInputSteam*/
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		InputStream inputStream = request.getInputStream();
		try {
			String str = inputStreamTOString(inputStream);
			PrintWriter printWriter = response.getWriter();
			printWriter.write(str);
		} catch (Exception e) {
		}	
	}
	public static String inputStreamTOString(InputStream in) throws Exception {
		ByteArrayOutputStream outStream = new ByteArrayOutputStream();
		byte[] data = new byte[BUFFER_SIZE];
		int count = -1;
		while ((count = in.read(data, 0, BUFFER_SIZE)) != -1)
			outStream.write(data, 0, count);
		return new String(outStream.toByteArray());
	}
}
