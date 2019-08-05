package cwu.jsj.servlet;

import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

import cwu.jsj.entity.DSTest;
import cwu.jsj.entity.MessageEntity;

public class InsertSecretaryServlet extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException {
         doPost(request, response);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		InputStream inputStream = request.getInputStream();
		String jsonstr = JSONServlet.inputStreamTOString(inputStream, "utf-8");
		Gson gson = new Gson();
		MessageEntity message = gson.fromJson(jsonstr, MessageEntity.class);
		DSTest ds = DSTest.getInstance();
		Boolean bool = ds.insertSecretary(message);
	    System.out.println(jsonstr);
	    System.out.println(bool);
	}
}
