<%@page import="com.google.gson.reflect.TypeToken"%>
<%@page import="com.google.gson.Gson"%>
<%@page import="java.io.PrintWriter"%>
<%@ page language="java" import="javax.servlet.http.HttpServletRequest"%>
<%@ page language="java" import="javax.servlet.http.HttpServletResponse"%>
<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ page language="java" import="cwu.jsj.entity.HostelBean"%>
<%@ page language="java" import="cwu.jsj.dao.*"%>
<%@ page language="java" import="java.io.*"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE html>
<html>
<head>
<title>根据酒店名称删除酒店</title>
<script type="text/javascript"
	src="http://www.daimajiayuan.com/download/jquery/jquery-1.10.2.min.js"></script>
<script type="text/javascript"
	src="http://cdn.bootcss.com/bootstrap-select/2.0.0-beta1/js/bootstrap-select.js"></script>
<link rel="stylesheet" type="text/css"
	href="http://cdn.bootcss.com/bootstrap-select/2.0.0-beta1/css/bootstrap-select.css">



<!-- 3.0 -->
<link
	href="http://netdna.bootstrapcdn.com/bootstrap/3.0.0/css/bootstrap.min.css"
	rel="stylesheet">
<script
	src="http://netdna.bootstrapcdn.com/bootstrap/3.0.0/js/bootstrap.min.js"></script>

<!-- 2.3.2  
    <link href="http://netdna.bootstrapcdn.com/twitter-bootstrap/2.3.2/css/bootstrap-combined.min.css" rel="stylesheet">  
    <script src="http://netdna.bootstrapcdn.com/twitter-bootstrap/2.3.2/js/bootstrap.js"></script>  
    -->
<script type="text/javascript">
	$(window).on('load', function() {

		$('.selectpicker').selectpicker({
			'selectedText' : 'cat'
		});

		// $('.selectpicker').selectpicker('hide');  
	});
</script>
</head>
<body>
<form class="form-horizontal" role="form" action="<%=basePath %>SelectHostel" >
	<label for="id_select">酒店名称</label>
	<select id="id_select" name="id_select"
		class="selectpicker bla bla bli" multiple data-live-search="true">
		<%
		HostelDao hostelDao = new HostelDao();
		hostelDao.isHotelExist();
		List<HostelBean> listHotel = hostelDao.getAllHotel();
		int flag=0;
		for(HostelBean hotel:listHotel){
		flag  = flag+ 1;
		 %>
		<option <%if(flag==1){%> <%="selected='selected'" %> <%
		}%>
			><%=hotel.getHotelName() %></option>
		<%} %>
	</select>
	<div class="container">
			<div class="form-group ">
			<button type="submit" class="btn btn-md btn-primary">查找</button>
				<label for="bs3Select" class="col-lg-2 control-label">房间类型</label>
				<div class="col-lg-6">
					<select id="bs3Select" name ="bs3Select" class="selectpicker show-tick form-control"
						multiple data-live-search="true">
						<%
						ArrayList<String> list = new ArrayList<String>();
   						 list.add("standard");
   						 list.add("bigBed");
  						 list.add("businesss");
  						 list.add("lluxury");
  						 String roomType = "";
  						 int roomflag=0;
  						 for(String name:list){
  						 roomflag  = roomflag+ 1;
  						 switch(name.length()){
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
  						 
  						 }%>
  						 <option <%
  						 if(roomType.equals("标间")){
  						  %><%="selected='selected'" %><%} %>><%=roomType %></option>
  						 <%} %>
					</select>
					
				</div>
			</div>
			</form>
			<h2 class="sub-header">酒店列表</h2>
				<div class="table-responsive">
					<table class="table table-striped">
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
					
						<tbody>
							
							<tr><%
							
							Gson gson = new Gson();
						    String JsonString = (String)request.getSession().getAttribute("hotel");
						    try{
							List<HostelBean>  hostelBeanlist = gson.fromJson(JsonString,new TypeToken<List<HostelBean>>(){}.getType());
							System.out.print(hostelBeanlist.toString());
							for(HostelBean hotel:hostelBeanlist){
							 %>
							
								<td><%=hotel.getHotelName()%></td>
								<td><%=hotel.getRoomPeopleNumber()%></td>
								<td><%=hotel.getRoomAvailableNumber()%></td>
								<td><%=hotel.getMoney()%></td>
								<td><%=hotel.getHotelAddress()%></td>
								<td><%=hotel.getHotelPhoneNumber()%></td>
								<td><% roomType=hotel.getRoomType();
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
								 
								
								%><%=roomType %></td>
								<td><%=hotel.getHotelCompany()%></td>
								<%String json =gson.toJson(hotel) ;
								request.getSession().setAttribute("jsonStr", json);
								%>
								<td><a href="<%=basePath%>createSource/addPublicSource.jsp">更改</a></td>
							</tr>
							<%}
							}catch(Exception e){
							
							}%> 
						</tbody>
					</table>
				</div>
			
	</div>

</body>

</html>
