package publicResource;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import cwu.jsj.dao.CarTao;
import cwu.jsj.entity.CarBean;

@SuppressWarnings("serial")
public class createCar extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		String carCompany =new String(request.getParameter("carCompany").getBytes("iso8859-1"),"utf-8");
		String carPeopleNumber =request.getParameter("carPeopleNumber");
		String carAvailableNumber =request.getParameter("carAvailableNumber");
		String money =request.getParameter("money");
		String address =new String(request.getParameter(""
				+ "Address").getBytes("iso8859-1"),"utf-8");
		String carPhoneNumber =request.getParameter("carPhoneNumber");
		String vehicleType =request.getParameter("vehicleType");
		System.out.print("----createCar------");
		CarBean carBean = new CarBean(0, Integer.parseInt(carPeopleNumber), "", Integer.parseInt(carAvailableNumber), "", money, address, carPhoneNumber, vehicleType, "", "", "", carCompany, Float.parseFloat("0"), 0);
		CarTao carDao = new CarTao();
		carDao.isCarExist();
		boolean createFlag =carDao.createCar(carBean);
		if(createFlag){
			//定向到插入成功
			String path = request.getContextPath();
			String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
			response.sendRedirect(basePath+"createSource/updateSuccess.jsp");
			
		}else{
			//定向到插入失败
			String path = request.getContextPath();
			String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
			response.sendRedirect(basePath+"createSource/addCarResource.jsp");
		}
	}

	
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
		
	}

	
}
