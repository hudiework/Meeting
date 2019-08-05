<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<style>
h1 {
	font-size: 50px;
	font-weight: 100;
	margin: 80px 30px 30px 100px;
}
#nav ul {
	margin: 30px 20px auto 60px;
}

#nav li {
	list-style: none;
	font-size: 30px;
	font-weight: 400;
	padding: 10px;
}

#nav a {
	color: black;
	text-decoration: none;
}

#nav {
	position: absolute;
	text-justify: auto;
	text-align: justify;
	width: 300px;
	height: 600px;
	border-right: 1px solid;
	text-justify: auto;
}
#others{
  			position: absolute;
  			top:80px;
  			right:800px;
  			font-size: 25px;
  			font-weight: 100; 			
  			}
  		a{
  		text-decoration: none;
  		color: black;}
</style>
<h1>会议管理系统（V1.0）</h1>
<hr />
<div id="nav">
		<ul>
			<a href="<c:url value='/index.jsp'/>"><li>首 页</li></a>
			<a href="<c:url value='/MeetingDetail/CreateNewMeeting.jsp' />"><li>创办会议</li></a>
			<a href="<c:url value='/bookList.jsp' />"><li>查找会议</li></a>
			<a href="<c:url value='/bookList.jsp' />"><li>公共资源</li></a>
			<a href="<c:url value='/bookList.jsp' />"><li>酒店预订</li></a>
			<a href="<c:url value='/login.jsp' />"><li>退 出</li></a>
		</ul>
</div>
<%--=request.getSession().getAttribute("loginname") --%>
<div id="others">
    	<p>欢迎您:${sessionScope.loginname }&nbsp;&nbsp;
    	<a href="<c:url value='/login.jsp' />">退出</a>
</div>
