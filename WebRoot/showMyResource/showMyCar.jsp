<%@page import="cwu.jsj.entity.CarBean"%>
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
	<title>查看车辆</title>
	<link rel="stylesheet" href="http://cdn.static.runoob.com/libs/bootstrap/3.3.7/css/bootstrap.min.css">  
	<script src="http://cdn.static.runoob.com/libs/jquery/2.1.1/jquery.min.js"></script>
	<script src="http://cdn.static.runoob.com/libs/bootstrap/3.3.7/js/bootstrap.min.js"></script>
</head>
  
  <body>
   <table class="table table-hover">
	<caption>车辆</caption>
	<thead>
		<tr>
			<th>车辆公司名称</th>
			<th>可乘坐人数</th>
			<th>车辆剩余数量</th>
			<th>单价</th>
			<th>车辆类型</th>
			<th>车辆公司地址</th>
			<th>车辆公司联系方式</th>
		</tr>
	</thead>
	<tbody>
	<% 
	CarTao carDao = new CarTao();
	carDao.isCarExist();
	List<CarBean> carBeans = new ArrayList();
	carBeans = carDao.getAllCar();
	for(CarBean bean:carBeans){
	
	%>
		<tr>
			<td><%=bean.getCarCompany() %></td>
			<td><%=bean.getCarPeopleNumber() %></td>
			<td><%=bean.getCarAvailableNumber() %></td>
			<td><%=bean.getMoney() %></td>
			<td><%=bean.getVehicleType() %></td>
			<td><%=bean.getAddress() %></td>
			<td><%=bean.getCarPhoneNumber() %></td>
			<td><a href="<%=basePath%>deleteSite?carCompany='<%=bean.getCarCompany()%>'&vehicleType='<%=bean.getVehicleType()%>'">删除</a></td>
		</tr>
		<%} %>
		
	</tbody>
</table>
  </body>
</html>
