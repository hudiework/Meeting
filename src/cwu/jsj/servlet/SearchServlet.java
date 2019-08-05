package cwu.jsj.servlet;

import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

import cwu.jsj.entity.DSTest;
import cwu.jsj.entity.MeetingBean;
import cwu.jsj.entity.MessageEntity;

public class SearchServlet extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException {
         doPost(request, response);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String type=request.getParameter("type");
		String content=request.getParameter("content");
		System.out.println("type="+type+"&content="+content);
		Gson gson = new Gson();
		DSTest ds=DSTest.getInstance();
		List<MeetingBean> list=new ArrayList<>();
		if(type.equals("0")){
			int number=Integer.parseInt(content);
			list=ds.serachNumber(number);
		}else{
			list=ds.serachName(content);
		}
        String str=gson.toJson(list);
        response.getOutputStream().write(str.getBytes("utf-8"));
		System.out.println(str); 
	}
}
