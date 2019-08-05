package publicResource;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cwu.jsj.dao.HostelDao;

@SuppressWarnings("serial")
public class deleteHostel extends HttpServlet {

	
	public deleteHostel() {
		super();
	}

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		String hotelName =new String(request.getParameter("hotelName").getBytes("iso8859-1"),"utf-8");
		String roomType =new String(request.getParameter("roomType").getBytes("iso8859-1"),"utf-8");
		HostelDao hostelDao  = new HostelDao();
		boolean createFlag =hostelDao.deleteHotelByName(hotelName, roomType);
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
		doGet(request, response);
	}
	
/*	private void readForm(HttpServletRequest request) {  
	    // TODO Auto-generated method stub  
	    Enumeration<String> e = request.getParameterNames();  
	    while (e.hasMoreElements()) {  
	        String parameterName = (String) e.nextElement();  
	        String parameterValue= request.getParameter(parameterName);  
	        request.setAttribute(parameterName, parameterValue); 
	        System.out.println(request.getAttribute(parameterName+":"+parameterValue));
	    }  
	      
	} */
	

	

}
