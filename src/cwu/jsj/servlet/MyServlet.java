package cwu.jsj.servlet;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

import cwu.jsj.entity.ContentEntity;
import cwu.jsj.entity.DSTest;

public class MyServlet extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		doPost(request,response);
	}
	
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
         InputStream inputStream=request.getInputStream();
         String tag = JSONServlet.inputStreamTOString(inputStream,"utf-8");
         DSTest ds=DSTest.getInstance();
         List<ContentEntity> list=ds.allContent(tag);
         Gson gson=new Gson();
         String str=gson.toJson(list);
         response.getOutputStream().write(str.getBytes("utf-8"));
		 System.out.println(str); 
	}
}
