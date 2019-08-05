package publicResource;

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

import cwu.jsj.dao.HostelDao;
import cwu.jsj.entity.HostelBean;

@SuppressWarnings("serial")
public class GetHotelListServlet extends HttpServlet {

	/**
	 * Constructor of the object.
	 */
	public GetHotelListServlet() {
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
		
        
	}
		
	

	
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		// 输出流   设置编码 
        response.setCharacterEncoding("utf-8");
        PrintWriter out = response.getWriter();  
        
        // 访问数据库    
        List<HostelBean> hotelList=new ArrayList<HostelBean>();
        HostelDao myDao = new HostelDao();
        try {
        	hotelList=myDao.getAllHotel();
        	if(hotelList==null||hotelList.size()==0){
        		//暂无数据
        		out.print("0");
        		return;
        	}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
       Gson gson = new Gson();
       String backJson = gson.toJson(hotelList);
       System.out.println("获取到我的所有酒店："+backJson);
       out.print(backJson);
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
