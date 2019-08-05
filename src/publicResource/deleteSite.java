package publicResource;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import cwu.jsj.dao.SiteTao;

@SuppressWarnings("serial")
public class deleteSite extends HttpServlet {

	/**
	 * Constructor of the object.
	 */
	public deleteSite() {
		super();
	}

	
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		String siteName =new String(request.getParameter("siteName").getBytes("iso8859-1"),"utf-8");
		String siteType =new String(request.getParameter("siteType").getBytes("iso8859-1"),"utf-8");
		SiteTao siteTao = new SiteTao();
		System.out.println("-----------------");
		System.out.println("siteName:"+siteName+"siteType:"+siteType);
		boolean createFlag =siteTao.deleteSiteBySiteName(siteName,siteType);
		if(createFlag){
			//定向到删除成功
			String path = request.getContextPath();
			String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
			response.sendRedirect(basePath+"createSource/updateSuccess.jsp");
		}else{
			//定向到删除失败
			String path = request.getContextPath();
			String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
			response.sendRedirect(basePath+"showMyResource/showMySite.jsp");
		}
		
		

		
	}

	
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
			doGet(request, response);
		
	}
}
