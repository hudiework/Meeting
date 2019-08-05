<%@page import="cwu.jsj.entity.SiteBean"%>
<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ page language="java" import="cwu.jsj.dao.*"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
 <head>
	<meta charset="utf-8"> 
	<title>查看场地</title>
	<link rel="stylesheet" href="http://cdn.static.runoob.com/libs/bootstrap/3.3.7/css/bootstrap.min.css">  
	<script src="http://cdn.static.runoob.com/libs/jquery/2.1.1/jquery.min.js"></script>
	<script src="http://cdn.static.runoob.com/libs/bootstrap/3.3.7/js/bootstrap.min.js"></script>
</head>
  
  <body>
   <table class="table table-hover">
	<caption>场地</caption>
	<thead>
		<tr>
			<th>场地名字</th>
			<th>容纳人数</th>
			<th>场地可用数量</th>
			<th>单价</th>
			<th>场地类型</th>
			<th>场地隶属公司</th>
			<th>场地地址</th>
			<th>场馆联系方式</th>
		</tr>
	</thead>
	<tbody>
	<% 
	SiteTao siteDao = new SiteTao();
	siteDao.isSiteExist();
	List<SiteBean> siteBeans = new ArrayList();
	siteBeans = siteDao.getAllSite();
	for(SiteBean bean:siteBeans){
	
	%>
		<tr>
			<td><%=bean.getSiteName() %></td>
			<td><%=bean.getCapacity() %></td>
			<td><%=bean.getSiteAvailableNumber() %></td>
			<td><%=bean.getMoney() %></td>
			<td><%=bean.getSiteType() %></td>
			<td><%=bean.getAddress() %></td>
			<td><%=bean.getSitePhoneNumber() %></td>
			<td><a href="<%=basePath%>deleteSite?siteName='<%=bean.getSiteName()%>'&siteType='<%=bean.getSiteType()%>'">删除</a></td>
		</tr>
		<%} %>
		
	</tbody>
</table>
  </body>
</html>
