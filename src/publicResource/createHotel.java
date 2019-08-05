package publicResource;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cwu.jsj.dao.HostelDao;
import cwu.jsj.entity.HostelBean;

@SuppressWarnings("serial")
public class createHotel extends HttpServlet {

	/**
	 * Constructor of the object.
	 */
	public createHotel() {
		super();
	}

	
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		String hotelName =new String(request.getParameter("hotelName").getBytes("iso8859-1"),"utf-8");
		String roomPeopleNumber =request.getParameter("roomPeopleNumber");
		String roomAvailableNumber =request.getParameter("roomAvailableNumber");
		String money =request.getParameter("money");
		String hotelAddress =new String(request.getParameter("hotelAddress").getBytes("iso8859-1"),"utf-8");
		String hotelPhoneNumber =request.getParameter("hotelPhoneNumber");
		String roomType =request.getParameter("roomType");
		String hotelCompany =new String(request.getParameter("hotelCompany").getBytes("iso8859-1"),"utf-8");
		HostelBean hotel = new HostelBean(hotelName, Integer.parseInt(roomPeopleNumber), 0, Integer.parseInt(roomAvailableNumber), null, null, money, hotelAddress, hotelPhoneNumber, roomType, hotelCompany, null , 0, 0);
		System.out.println(hotel.toString());
		
		HostelDao hostelDao = new HostelDao();
		hostelDao.isHotelExist();
		boolean createFlag =hostelDao.createHotel(hotel);
		if(createFlag){
			//定向到插入成功
			String path = request.getContextPath();
			
			String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
			
			
			response.sendRedirect(basePath+"createSource/updateSuccess.jsp");
			
		}else{
			//定向到插入失败
			String path = request.getContextPath();
			String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
			response.sendRedirect(basePath+"createSource/addPublicSource.jsp");
		}
		
		

	
	}


	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request,response);

		
	}



}
