package cwu.jsj.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cwu.jsj.dao.RegisterDao;

public class RegistServlet extends HttpServlet {
	
	static int LOGIN_FAILED = 0;  
    static int LOGIN_SUCCEEDED = 1;  
    static int REGISTER_FAILED = 2;  
    static int REGISTER_SUCCEEDED = 3;
    
	public RegistServlet() {  
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
        String responseMsg="";    
        // �����    
        PrintWriter out = response.getWriter();    
        // ���ñ�����ʽ    
        request.setCharacterEncoding("utf-8");      
        // ��ȡ�������    
        String mobilephone = request.getParameter("mobilephone");    
        String password = request.getParameter("password"); 
        String imagePath = request.getParameter("imagePath"); 
        System.out.println("mobilephone:" + mobilephone + " --try to regist");  
    
        // ������ݿ�    
        RegisterDao ser = new RegisterDao();
        int value = ser.register(mobilephone, password,imagePath); 
        if(value == REGISTER_SUCCEEDED) {    
            responseMsg = "SUCCEEDED";    
        } else if(value == REGISTER_FAILED){
        	responseMsg = "FAILED";
        } 
        System.out.println("register servlet responseMsg:" + responseMsg);    
        out.print(responseMsg);  
    }  
}
