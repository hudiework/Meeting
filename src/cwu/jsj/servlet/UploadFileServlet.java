package cwu.jsj.servlet;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.Writer;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import net.sf.json.JSON;
import net.sf.json.JSONArray;
import net.sf.json.JSONException;
import net.sf.json.JSONObject;
import cwu.jsj.dao.FilesDao;

@MultipartConfig
public class UploadFileServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;
	static int SAVEFILE_FAILED = 6;
	static int SAVEFILE_SUCCEEDED = 7;
	String fileName;
	int meetingId;

	public UploadFileServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		this.doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		System.out.println("doPost==");
		request.setCharacterEncoding("utf-8");
		// �����ַ�
		String responseMsg = "FAILED";

		// ��ȡfile�����part��ע��Ҫ��Android��һ��
		Part part = request.getPart("file");
		// ��ȡ����ͷ������ͷ�ĸ�ʽ��form-data; name="file"; filename="snmp4j--api.zip"
		String header = part.getHeader("content-disposition");
		System.out.println(header);
		fileName = getFileName(header);
		
		
		String id = request.getParameter("position");
		meetingId = Integer.parseInt(id); 
		

		// ������ݿ�
		FilesDao ser = new FilesDao();
		//���ļ���������
		if(ser.QueryFile(fileName,meetingId)==true){
			int value = ser.PostFiles(fileName,meetingId);
			if (value == SAVEFILE_SUCCEEDED) {
				responseMsg = "SUCCEEDED";
			}
			System.out.println("savefile responseMsg:" + responseMsg);
		}else{
			//ͬ���ļ������
			ser.UpdateFile(fileName);
		}	
		// �����
		PrintWriter out = response.getWriter();
		out.print(responseMsg);

		// �洢·��
		//String savePath = "H:/HuiYiGuanLi/upload";
		String savePath = "E:/Tomcat8/apache-tomcat-8.5.29-windows-x64/apache-tomcat-8.5.29/webapps/myServer/upload";
		//String savePath="D:/张萌萌/apache-tomcat-7.0.4/webapps/myServer/upload";
		// ���ļ�д��ָ��·��
		part.write(savePath + File.separator + fileName);
		response.setCharacterEncoding("UTF-8");
		PrintWriter writer = response.getWriter();
		writer.print("上传成功");
		
	}

	public String getFileName(String header) {
		/**
		 * header Ϊ form-data; name="file"; filename="dial.png" String[]
		 * tempArr1 = header.split(";");����ִ����֮���ڲ�ͬ��������£�tempArr1��������������������
		 * ������google������£�tempArr1={form-data,name="file",filename=
		 * "snmp4j--api.zip"} IE������£�tempArr1={form-data,name="file",filename=
		 * "E:\snmp4j--api.zip"}
		 */
		String[] tempArr1 = header.split(";");
		/**
		 * ������google������£�tempArr2={filename,"snmp4j--api.zip"}
		 * IE������£�tempArr2={filename,"E:\snmp4j--api.zip"}
		 */
		String[] tempArr2 = tempArr1[2].split("=");
		// ��ȡ�ļ�����ݸ����������д��
		String fileName = tempArr2[1].substring(
				tempArr2[1].lastIndexOf("\\") + 1).replaceAll("\"", "");
		return fileName;
	}
	
	

}