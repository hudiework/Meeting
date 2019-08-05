<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ page import="cwu.jsj.entity.MeetingBean"%>
<%
	String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<%@ include file="../share.jsp"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<style type="text/css">
#div_meetingList {
	position: absolute;
	left: 380px;
	top: 200px;
	font-size: 20px;
	font-weight: 130;
}

#meetingId {
	display: none;
}
</style>

</head>
<body>
	<div id="div_meetingList">
			<%
			String deleteMsg=(String)request.getAttribute("deleteMsg");
			if(deleteMsg!=null){
				if(deleteMsg.equals("1")){ 
					//out.println("<script>window.location.reload();</script>");
					out.println("<script>alert('删除成功！')</script>");
					}
				if(deleteMsg.equals("0")){
					out.println("<script>alert('删除失败！！')</script>");
				}
			}
			 %>
		<ul id="ul_meetingList">
			<%
				int i = 0;
				String meetingName = "";//会议名
				String meetingContent = "";//会议内容
				String meetingDate = "";//会议日期
				int meetingId = 0;//会议id
				List<MeetingBean> meetingList = new ArrayList<MeetingBean>();
				meetingList = (List<MeetingBean>) request.getSession()
						.getAttribute("meetingList");
				for (i = 0; i < meetingList.size(); i++) {
					meetingName = meetingList.get(i).getMeetingName();
					meetingContent = meetingList.get(i).getMeetingContent();
					meetingDate = meetingList.get(i).getMeetingDate();
					meetingId = meetingList.get(i).getMeetingId();
			%>
			<li><span id="meetingId"><%=meetingId%></span><%=meetingName + ":" + meetingContent + "   "
						+ meetingDate%><button
					 onclick="detail(<%=meetingId %>)">查看</button>
				<button  onclick="delet(<%=meetingId %>)">删除</button></li>
			<%
				}
			%>
		</ul>
	</div>
</body>
<script type="text/javascript">
 	function delet(meetingId){
		alert(meetingId);
		location.href="DeleteMeeting?action=post&meetingId="+meetingId;
		
	};
	function detail(meetingId){
	alert(meetingId);
	location.href="MeetingDetail?action=post&meetingId="+meetingId;
	};
	//window.onload = function() {
	
		//var ul = document.getElementById("ul_meetingList");
		//循环遍历li	
		//var li_list = ul.childNodes;
		//(li_list[0].getElementsByTagName("button"));
		
		//for (var i = 0; i < li_list.length; i++) {
			//var btns = li_list[i].getElementsByTagName("button");
			//点击“查看”按钮，触发下面事件
			//alert(btns.length);
			/* btns[0].onclick = function() {
				var meetingId = this.parentNode.firstChild.firstChild.nodeValue;
				alert(meetingId);
			};

			//点击“删除”按钮，触发下面的事件：
			btns[1].onclic = function() {
				var MeetingId = this.parentNode.firstChild.firstChild.nodeValue;
				alert(MeetingId);
			}; */

		//};
		/* //为按钮“查看”添加点击事件
		btnDetail.onclick=function(){
		var meetingId=this.parentNode.innerHTML;
		alert(meetingId);
		location.href="MeetingDetail?action=post&meetingId="+meetingId;
		}
		//为按钮“删除”添加点击事件
		btnDelete.onclick=function(){
		var MeetingId=this.parentNode.firstChild.firstChild.nodeValue;
		alert(MeetingId);
		location.href="DeleteMeeting?action=post&meetingId="+MeetingId;
		}
		 */
</script>
</html>
