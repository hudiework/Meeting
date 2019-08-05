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
   
  <body>
  <form action="CreateNewMeeting" method="post">
    	会议名称:<input type="text" name="meetingName" />  <br/><br/>
    	会议内容:<input  type="text" name="meetingContent" /><br /><br/>
    	主持人:<input  type="text" name="meetingCompere" /><br /><br/>
    	举办时间:<input type="datetime" name="meetingDate" /><br/><br/>
    	结束时间:<input type="datetime" name="meetingOverDate" /><br/><br/>
    	会议议程:<input type="text" name="meetingAgenda"/><br/><br/>
    	举办地点:<input type="text" name="meetingPlace" /><br /><br/>
    	会议指南:<input  type="text" name="meetingGuide" /><br /><br/>
    	<button type="button" id="childMeeting">添加会议子项</button>
    	<div id="div_childMeeting" style="display:none">
    	会议主题：<input type="text" name="childMeetingName" /><br/><br/>
    	主持人：<input type="text" name="childMeetingCompere" /><br/><br/>
    	会议内容：<input type="text" name="childMeetingContent" /><br/><br/>
    	开始时间：<input type="time" name="childMeetingDate" /><br/><br/>
    	会议地点：<input type="text" name ="childMeetingPlace" /><br/><br/>
    	</div>
    	<input type="submit" value="提交" style="width:200px;margin:0 20px;" />
    	<input type="reset" ivalue="重置" style="width:200px;margin:0 20px;" />
    </form>
  </body>
  <script type="text/javascript">
  var btn_addChildMeeting=document.getElementById("childMeeting");
  var div_childMeeting=document.getElementById("div_childMeeting");
  btn_addChildMeeting.onclick=function(){
  //alert(this.innerHTML);
   if(this.innerHTML=="添加会议子项"){
  this.innerHTML="不添加子会议";
  div_childMeeting.style.display="block";
  }else{
  this.innerHTML="添加会议子项";
  div_childMeeting.style.display="none";
  } 
  
  };
  </script>
</html>
