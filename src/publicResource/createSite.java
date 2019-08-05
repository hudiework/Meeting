package publicResource;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import cwu.jsj.dao.SiteTao;
import cwu.jsj.entity.SiteBean;

@SuppressWarnings("serial")
public class createSite extends HttpServlet {
	public createSite() {
		super();
	}

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		String siteName =new String(request.getParameter("siteName").getBytes("iso8859-1"),"utf-8");
		String capacity =request.getParameter("capacity");
		String siteAvailableNumber =request.getParameter("siteAvailableNumber");
		String money =request.getParameter("money");
		String address =new String(request.getParameter(""
				+ "Address").getBytes("iso8859-1"),"utf-8");
		String sitePhoneNumber =request.getParameter("sitePhoneNumber");
		String siteType =request.getParameter("siteType");
		String siteCompany =new String(request.getParameter("siteCompany").getBytes("iso8859-1"),"utf-8");
		SiteBean site = new SiteBean(siteName, Integer.parseInt(capacity), money, address, Integer.parseInt(siteAvailableNumber), siteType, sitePhoneNumber, "", "", 0, siteCompany, "", Float.parseFloat("0"), 0);
		SiteTao siteDao = new SiteTao();
		siteDao.isSiteExist();;
		boolean createFlag =siteDao.createSite(site);
		if(createFlag){
			//定向到插入成功
			String path = request.getContextPath();
			
			String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
			
			
			response.sendRedirect(basePath+"createSource/updateSuccess.jsp");
			
		}else{
			//定向到插入失败
			String path = request.getContextPath();
			String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
			response.sendRedirect(basePath+"createSource/addSiteResource.jsp");
		}
		
		


	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);

	}

}
