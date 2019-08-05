<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>操作成功</title>
        <script type="text/javascript">     
            function countDown(secs,surl){     
                //alert(surl);     
                var jumpTo = document.getElementById('jumpTo');
                jumpTo.innerHTML=secs;  
                if(--secs>0){     
                    setTimeout("countDown("+secs+",'"+surl+"')",1000);     
                }     
                else{       
                    location.href=surl;     
                }     
            }     
        </script> 
<style type="text/css">
    body {
	background: url(img/teaSuccess.jpg);
	background-repeat: no-repeat;
}
    </style>
    </head>
    <body>
<center>
        <div style="margin-top:5%;">
            <div id="title" style="border-bottom:1px solid #C8DCEC; width: 50%;text-align: left; "><h3>更新提示：</h3></div>
            <span style="position:relative; left: -180px; top:40px;"><img src="<%=request.getContextPath()%>/img/right_big.gif" /></span> 
             <h3>更新成功！<span id="jumpTo" style="color:orange;">5</span>&nbsp;秒后自动跳转到更新界面</h3> 
            <script type="text/javascript">countDown(5,'../nowHostelResource.jsp');</script>  
            <h3><a href="<%=basePath %>nowHostelResource.jsp">[若没有自动跳转，请点击这里]</a> </h3>
        </div>
    </center>
</body>
</html>
