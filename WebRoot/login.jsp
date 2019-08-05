<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<%@ include file="share.jsp"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
  	<style type="text/css">
  		form{
  			position: absolute;
  			left:400px;
  			top:250px;
  			font-size: 25px;
  			margin: 10px;
  		}
  		input{
  		height:30px;
  		width:200px;
  		}
  	</style>
  </head>
  
  <body>
    <form action="<c:url value="/PCLoginServlet" />" method="post">
    	登录名:<input type="text" name="userName" /><br/><br/>
    	密&nbsp码:<input type="password" name="userPsw" /><br /><br/>
    	<input type="submit" value="登录" style="width:100px;margin:0 20px;" />
    	<input type="submit" value="重置" style="width:100px;margin:0 20px;" />
    </form>
   <div id="others">
    	<p>错误信息：${sessionScope.msg }&nbsp;&nbsp;
</div>
  </body>
</html>
