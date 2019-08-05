<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ page import= "cwu.jsj.entity.MeetingBean"%>  
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'MyJsp.jsp' starting page</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->

  </head>
  
  <body>
  <%MeetingBean meeting=new MeetingBean();
  	meeting=(MeetingBean)request.getAttribute("meeting"); %>
    This is my JSP page. <br>
    <div id="others">
   <c:if test="${sessionScope.updateMsg==null}">
   <script type="text/javascript">alert("lalalala")</script>
   </c:if>  
   <c:if test="${sessionScope.updateMsg=='1'} ">
   <script>alert("11111")</script>
   </c:if> 	
   <c:if test="${sessionScope.updateMsg=='1'} ">
   <script type="text/javascript">alert("0000")</script>
   </c:if>
</div>
  </body>
</html>
