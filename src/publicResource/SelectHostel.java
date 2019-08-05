package publicResource;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import cwu.jsj.dao.HostelDao;
import cwu.jsj.entity.HostelBean;
@SuppressWarnings("serial")
public class SelectHostel extends HttpServlet {
	public SelectHostel() {
		super();
	}
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		String hotelName =new String(request.getParameter("id_select").getBytes("iso8859-1"),"utf-8");
		String roomType =new String(request.getParameter("bs3Select").getBytes("iso8859-1"),"utf-8");
		if(roomType.equals("标间")){
			roomType = "standard";
		}else if(roomType.equals("大床房")){
			roomType = "bigBed";
		}else if(roomType.equals("大床房")){
			roomType = "business";
		}else if(roomType.equals("大床房")){
			roomType = "luxury";
		}
		System.out.println("-------------");
		System.out.println("hotelName:"+hotelName+"roomType:"+roomType);
		HostelDao dao = new HostelDao();
		try {
			
			List<HostelBean> listHotel=dao.getAllHotel();
			Gson gson = new Gson();
			String JsonString = gson.toJson(listHotel);
			System.out.println(listHotel);
			System.out.println("JsonString"+JsonString);
			List<HostelBean>  hostelBeanlist = gson.fromJson(JsonString,new TypeToken<List<HostelBean>>() {  
            }.getType());
			System.out.println(hostelBeanlist.toString());
			request.getSession().setAttribute("hotel", JsonString);
			request.getRequestDispatcher("deleteResource/deleteHostel.jsp").forward(request,response);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}


	
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		doGet(request, response);
	}

	

}
