package cwu.jsj.servlet;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

import cwu.jsj.entity.DSTest;
import cwu.jsj.entity.MessageEntity;


public class JSONServlet extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		doPost(request,response);
	}
	
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
         InputStream inputStream=request.getInputStream();
         String jsonstr = inputStreamTOString(inputStream,"utf-8");
         System.out.println(jsonstr); 
         DSTest ds=DSTest.getInstance();
         List<MessageEntity> list=ds.allMessage(jsonstr);
         Gson gson=new Gson();
         String str=gson.toJson(list);
         response.getOutputStream().write(str.getBytes("utf-8"));
		 System.out.println(str); 
	}
	
	public static String inputStreamTOString(InputStream in, String encoding)
			throws IOException {
		ByteArrayOutputStream outStream = new ByteArrayOutputStream();
		byte[] data = new byte[4096];
		int count = -1;
		while ((count = in.read(data, 0,4096)) != -1)
			outStream.write(data, 0, count);
		return new String(outStream.toByteArray(), encoding);
	}
	
}
