package cwu.jsj.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

import cwu.jsj.dao.FilesDao;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

public class ShowFilesServlet extends HttpServlet {

	/**
	 * Constructor of the object.
	 */
	public ShowFilesServlet() {
		super();
	}

	/**
	 * The doGet method of the servlet. <br>
	 *
	 * This method is called when a form has its tag value method equals to get.
	 * 
	 * @param request the request send by the client to the server
	 * @param response the response send by the server to the client
	 * @throws ServletException if an error occurred
	 * @throws IOException if an error occurred
	 */
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		this.doPost(request, response);
	}

	/**
	 * The doPost method of the servlet. <br>
	 *
	 * This method is called when a form has its tag value method equals to post.
	 * 
	 * @param request the request send by the client to the server
	 * @param response the response send by the server to the client
	 * @throws ServletException if an error occurred
	 * @throws IOException if an error occurred
	 */
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String responseMsg="";
		response.setCharacterEncoding("utf-8");
        // �����    
        PrintWriter out = response.getWriter();    
        // ���ñ�����ʽ    
        request.setCharacterEncoding("utf-8");      
        // ��ȡ�������    
        String id = request.getParameter("meetingId");
        int meetingId = Integer.parseInt(id);
		
		List<String> filelist = new ArrayList<String>();
		FilesDao ser = new FilesDao();
		filelist = ser.QueryAllFiles(meetingId);
		Gson gson = new Gson();
		
// 		JSONObject jsonObject=new JSONObject();  
//        jsonObject.put("filelist", filelist); 
       // String jsonString = jsonObject.toString();
		String jsonString= JSONArray.fromObject(filelist).toString();
        System.out.println(jsonString);
		out.write(jsonString);
		out.flush();
		out.close();
		
	}

}
