<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ page import= "cwu.jsj.entity.MeetingBean"%>  
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<%@ include file="../share.jsp"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
 <!--  <script type="text/javascript" src="../jquery-1.8.3.min.js"></script> -->
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
  		#update{
  		position: absolute;
  		width:150px;
  			left:400px;
  			top:800px;
  			font-size: 25px;
  		}
  		#reset{
  		position:absolute;
  		width:150px;
  			left:570px;
  			top:800px;
  			font-size: 25px;
  		}
  		#participantNum{
  		display:block;
  		}
  	</style>
  </head>
   <script type="text/javascript">
  
   window.onload = function(){
	//(meetingId);.onclick = function(){
	//location.href="MeetingDetail?action=post&meetingId="+meetingId;
	// 得到页面中所有input
	var allInputs = document.getElementsByTagName("input");
	var btn_update=document.getElementById("update");
	var submit=document.getElementById("submit");
	var reset=document.getElementById("reset");
	
	btn_update.onclick=function(){
	// 在所有Input中过滤出type="text"的元素，放到数组libIds中
	for (var i = 0; i < allInputs.length; i++) {
		if (allInputs[i].type === "text") {
			//alert(allInputs[i].value);
			allInputs[i].readOnly=false;
			submit.style.display="block";
			reset.style.display="block";
		} 
	};
    }
 	reset.onclick=function(){
 	/* for(var j = 0;j< allInputs.length; j++) {
    allInputs[i].value = ""; // 将每一个input的value置为空
	} */
	alert("111");
 	}
}
  </script>
  <body>
  <%MeetingBean meeting=new MeetingBean();
  	meeting=(MeetingBean)request.getAttribute("meeting");
  	String updateMsg=(String)request.getAttribute("updateMsg");
  	if(updateMsg!=null){
  	if(updateMsg.equals("1"))
  	out.println("<script>alert('更新成功！')</script>");
  	if(updateMsg.equals("0")){
  	out.println("<script>alert('更新失败！')</script>");
  		}
  	}
  	 %>
  	<span id="participantNum">目前报名人数为：${sessionScope.participantNum}人</span>
  <form action="UpdateMeeting?meetingId=<%=meeting.getMeetingId() %>" method="post" id="form">
    	会议名称:<input type="text" name="meetingName" value="<%=meeting.getMeetingName()%>" readonly/>  <br/><br/>
    	会议内容:<input  type="text" name="meetingContent" value="<%=meeting.getMeetingContent()%>" readonly/><br /><br/>
    	举办时间:<input type="text" name="meetingDate" value="<%=meeting.getMeetingDate()%>" readonly/><br/><br/>
    	主持人:<input  type="text" name="meetingCompere" value="<%=meeting.getMeetingCompere()%>" readonly/><br /><br/>
    	会议议程:<input type="text" name="meetingAgenda" value="<%=meeting.getMeetingAgenda()%>" readonly/><br/><br/>
    	举办地点:<input type="text" name="meetingPlace" value="<%=meeting.getMeetingPlace()%>" readonly/><br /><br/>
    	会议指南:<input  type="text" name="meetingGuide" value="<%=meeting.getMeetingGuide()%>" readonly/><br /><br/>
    	<input type="submit" id="submit" value="提交" style="width:200px;margin:0 20px;display:none" />
    </form>
    <button id="reset">重置</button>
    <button id="update">我要修改</button>
  </body>
</html>
