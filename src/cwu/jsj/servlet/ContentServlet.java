package cwu.jsj.servlet;

import java.io.IOException;
import java.io.InputStream;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

import cwu.jsj.entity.ContentEntity;
import cwu.jsj.entity.DSTest;

public class ContentServlet extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException {
         doPost(request, response);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		InputStream inputStream = request.getInputStream();
		String jsonstr = JSONServlet.inputStreamTOString(inputStream, "utf-8");
		System.out.println(jsonstr);
		Gson gson = new Gson();
		ContentEntity content=gson.fromJson(jsonstr,ContentEntity.class);
		DSTest ds = DSTest.getInstance();
		Boolean bool = ds.insert(content);
	    System.out.println(bool);
	    response.getOutputStream().write(bool.toString().getBytes("utf-8"));
	}
}
