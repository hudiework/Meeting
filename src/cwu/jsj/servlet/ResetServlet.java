package cwu.jsj.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cwu.jsj.dao.RegisterDao;

public class ResetServlet extends HttpServlet {

	static int RESET_FAILED = 4;
    static int RESET_SUCCEEDED = 5;
    
	public ResetServlet() {  
        super();  
    }  
  
    protected void doGet(HttpServletRequest request, HttpServletResponse response)   
            throws ServletException, IOException {  
        doPost(request, response);    
        response.getWriter().append("Served at: ").append(request.getContextPath());  
    }  
      
    protected void doPost(HttpServletRequest request, HttpServletResponse response)   
            throws ServletException, IOException {  
        // �����ַ�    
        String responseMsg="FAILED";    
        // �����    
        PrintWriter out = response.getWriter();    
        // ���ñ�����ʽ    
        request.setCharacterEncoding("utf-8");      
        // ��ȡ�������    
        String mobilephone = request.getParameter("mobilephone");    
        String password = request.getParameter("password");  
        System.out.println("mobilephone:" + mobilephone + " --try to reset");  
    
        // ������ݿ�    
        RegisterDao ser = new RegisterDao();
        int value = ser.reset(mobilephone, password); 
        if(value == RESET_SUCCEEDED) {    
            responseMsg = "SUCCEEDED";    
        }  
        System.out.println("reset servlet responseMsg:" + responseMsg);    
        out.print(responseMsg);  
    }  

}
