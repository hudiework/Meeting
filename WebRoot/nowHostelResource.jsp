
<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@page language="java" import="cwu.jsj.dao.HostelDao"%>
<%@page language="java" import="cwu.jsj.entity.HostelBean"%>
<%@page language="java" import="com.google.gson.*"%>
<%
	String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<base href="<%=basePath%>">

<title>全程营销会务管理系统</title>
<style>
.test {
	position: absolute;
	top: 50%;
	left: 50%;
	transform: translate(-50%, -50%);
}
</style>

<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<!-- The above 3 meta tags *must* come first in the head; any other head content must come *after* these tags -->
<meta name="description" content="tableTest">
<meta name="author" content="hh">
<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
<link rel="icon" href="<%=basePath%>img/favicon.ico">
<link href="<%=basePath%>bootstrap/css/bootstrap.min.css"
	rel="stylesheet">

<!-- IE10 viewport hack for Surface/desktop Windows 8 bug -->
<link href="<%=basePath%>bootstrap/css/ie10-viewport-bug-workaround.css"
	rel="stylesheet">

<!-- Custom styles for this template -->
<link href="<%=basePath%>bootstrap/css/dashboard.css" rel="stylesheet">
<script src="<%=basePath%>bootstrap/js/ie-emulation-modes-warning.js"></script>

</head>

<body>
	<nav class="navbar navbar-inverse navbar-fixed-top">
	<div class="container-fluid">
		<div class="navbar-header">
			<button type="button" class="navbar-toggle collapsed"
				data-toggle="collapse" data-target="#navbar" aria-expanded="false"
				aria-controls="navbar">
				<span class="icon-bar"></span> <span class="icon-bar"></span> <span
					class="icon-bar"></span>
			</button>
			<a class="navbar-brand" href="<%=basePath%>nowHostelResource.jsp">全程营销会务管理系统</a>
		</div>
		<div id="navbar" class="navbar-collapse collapse">
			<ul class="nav navbar-nav navbar-right">
				<li><a href="<%=basePath%>deleteResource/deleteHostel.jsp">更改酒店</a></li>
				<li><a href="<%=basePath%>showMyResource/showMySite.jsp">更改场地</a></li>
				<li><a href="<%=basePath%>showMyResource/showMyCar.jsp">更改车辆</a></li>
				<li><a href="">帮助</a></li>
			</ul>
			<form class="navbar-form navbar-right">
				<input type="text" class="form-control" placeholder="查找...">
			</form>
		</div>
	</div>
	</nav>

	<div class="container-fluid">
		<div class="row">
			<div class="col-sm-3 col-md-2 sidebar">
				<ul class="nav nav-sidebar">
					<li class="active"><a
						href="<%=basePath%>createSource/addPublicSource.jsp">创建酒店地资源 </a></li>
					<li><a href="<%=basePath%>createSource/addSiteResource.jsp">创建场地资源</a></li>
					<li><a href="<%=basePath%>createSource/addCarResource.jsp">创建车辆资源</a></li>
					<li><a href="<%=basePath%>signUpList/lookSignUp.jsp">查看报名信息</a></li>
				</ul>
			</div>
			<div class="col-sm-9 col-sm-offset-3 col-md-10 col-md-offset-2 main">
				<h1 class="page-header">创建资源模块</h1>

				<div class="row placeholders">
					<div class="col-xs-6 col-sm-3 placeholder">
						<img src="<%=basePath%>img/hotel.jpg" width="200" height="200"
							class="img-responsive" alt="Generic placeholder thumbnail">
						<h4>创建酒店资源</h4>
						<span class="text-muted">HOTEL</span>
					</div>
					<div class="col-xs-6 col-sm-3 placeholder">
						<img src="<%=basePath%>img/site.jpg" width="200" height="200"
							class="img-responsive" alt="Generic placeholder thumbnail">
						<h4>创建场地资源</h4>
						<span class="text-muted">SITE</span>
					</div>
					<div class="col-xs-6 col-sm-3 placeholder">
						<img src="<%=basePath%>img/car.jpg" width="200" height="200"
							class="img-responsive" alt="Generic placeholder thumbnail">
						<h4>创建车辆资源</h4>
						<span class="text-muted">CAR</span>
					</div>

				</div>

				<h2 class="sub-header">酒店列表</h2>
				<form class="table table-striped"
					action="<%=basePath%>nowHostelResource.jsp">
					<div class="table-responsive">
						<table class="table table-striped ">
							<thead>
								<tr>
									<th>酒店名称</th>
									<th>房间可容纳人数</th>
									<th>可预定数量</th>
									<th>单价</th>
									<th>酒店地址</th>
									<th>联系酒店</th>
									<th>房间类型</th>
									<th>酒店所属公司</th>
								</tr>
							</thead>


							<%
								int pageNow=1;//当前页码
																																						int rowCount=0;//总行数
																																						int pageSize=5;//每页多少条
																																						int pageCount=0;//总页数
																																					
																																						String pageNowString = request.getParameter("pageNow");
																																						System.out.print("pageNowString:pageNowString:pageNowString"+pageNowString);
																																						try{
																																						if(pageNowString.length()>0){
																																						System.out.print("________获取到pageNow的值！！！！");
																																								pageNow=Integer.parseInt(request.getParameter("pageNow"));
																																								}
																																								}catch(Exception e){
																																								}
																																								System.out.print("pageNow:"+pageNow);
																																								 HostelDao hostelDao = new HostelDao();
																																								hostelDao.isHotelExist();
																																								List<HostelBean> listhh=hostelDao.getAllHotel();
																																								System.out.print(listhh);
																																	                          pageCount = listhh.size()/pageSize;
																																							String sqlSentence="select * from hotel  limit "+(pageNow-1)*pageSize+","+pageSize;
																																							List<HostelBean> listHotel = hostelDao.exeSql(sqlSentence);
																																							// List<HostelBean> listHotel = hostelDao.getAllHotel();
																																							for(HostelBean hotel:listHotel){
							%>
							<tbody>
								<tr>
									<td><%=hotel.getHotelName()%></td>
									<td><%=hotel.getRoomPeopleNumber()%></td>
									<td><%=hotel.getRoomAvailableNumber()%></td>
									<td><%=hotel.getMoney()%></td>
									<td><%=hotel.getHotelAddress()%></td>
									<td><%=hotel.getHotelPhoneNumber()%></td>
									<td>
										<%
											String roomType=hotel.getRoomType();
																																																																					 switch(roomType.length()){
																																																																					 case 8: 
																																																																					 roomType="标间";
																																																																					 break;
																																																																					 case 6:
																																																																					  roomType="大床房";
																																																																					 break;
																																																																					  case 9:
																																																																					  roomType="商务间";
																																																																					 break;
																																																																					 case 7:
																																																																					  roomType="奢华总统套间";
																																																																					 break; 
																																																																					 }
										%><%=roomType%></td>
									<td><%=hotel.getHotelCompany()%></td>
									<td><a
										href="<%=basePath%>deleteHostel?hotelName='<%=hotel.getHotelName()%>'&roomType='<%=hotel.getRoomType()%>'">删除</a></td>
									<%
										}
									%>
								</tr>
								<div class="footer navbar-fixed-bottom">
									<nav aria-label="Page navigation navbar-fixed-bottom">
									<center>
										<ul class="pagination ">
											<li class="disabled"><span aria-label="Previous">
													<span aria-hidden="true">&laquo;</span>
											</span></li>



											<%
												if(pageNow<=3){
																																																for(int i=1;i<=pageNow+3;i++){
											%>
											<%="<li><a href='"%><%=basePath%><%="nowHostelResource.jsp?pageNow="+i%><%="'>"+i+"</a>  </li>"%>
											<%
												}
																																														}
																																															else if(pageNow>3&&pageNow<pageCount-3){
																																																for(int i=pageNow-3;i<=pageNow+3;i++){
											%>

											<%="<li><a href='"%><%=basePath%>
											<%="nowHostelResource.jsp?pageNow="%><%=i%><%="'>"+i+"</a>   </li>"%>

											<%
												}
																																															}else if(pageNow>=pageCount-3){
																																															for(int i=pageNow-3;i<=pageCount;i++){
											%>

											<%="<li><a href='"%><%=basePath%>
											<%="nowHostelResource.jsp?pageNow="%><%=i%><%="'>"+i+"</a> </li>"%>
											<%
												}
																																																}
											%>
											<li class="disabled"><a href="#" aria-label="Next">
													<span aria-hidden="true">&raquo;</span>
											</a></li>
										</ul>
									</center>
									</nav>
								</div>

							</tbody>


						</table>



					</div>
					<div class="form-group ">
						<div class="col-md-10 col-md-offset-2 pull-right ">
							<center>

								跳转到:<input type="text" name="pageNow" />
								<button class="btn btn-sm btn-primary" type="submit">GO</button>
						</div>
					</div>
					</center>
				</form>
			</div>

		</div>
	</div>

	<script
		src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>
	<script>
		window.jQuery
				|| document
						.write('<script src="http://localhost:8080/myServer/bootstrap/js/jquery.min.js"><\/script>')
	</script>
	<script src="<%=basePath%>bootstrap/js/bootstrap.min.js"></script>
	<script src="<%=basePath%>bootstrap/js/jquery.pagination.js"></script>
	<!-- Just to make our placeholder images work. Don't actually copy the next line! -->
	<script src="<%=basePath%>bootstrap/js/holder.min.js"></script>
	<!-- IE10 viewport hack for Surface/desktop Windows 8 bug -->
	<script src="<%=basePath%>bootstrap/js/ie10-viewport-bug-workaround.js"></script>


</body>
</html>
