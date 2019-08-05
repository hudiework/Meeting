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

<base href="<%=basePath%>">
  <head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <!-- The above 3 meta tags *must* come first in the head; any other head content must come *after* these tags -->
    <meta name="description" content="base">
    <meta name="author" content="hh">
    <link rel="icon" href=".<%=basePath%>img/favicon.ico">

    <title>全程营销会务管理系统</title>

    <link href="<%=basePath%>bootstrap/css/bootstrap.min.css" rel="stylesheet">
    <link href="<%=basePath%>bootstrap/css/ie10-viewport-bug-workaround.css" rel="stylesheet">
    <link href="<%=basePath%>bootstrap/css/cover.css" rel="stylesheet">

    <script src="%=basePath%>bootstrap/js/ie-emulation-modes-warning.js"></script>

  </head>

  <body>

    <div class="site-wrapper">

      <div class="site-wrapper-inner">

        <div class="cover-container">

          <div class="masthead clearfix">
            <div class="inner">
              <h3 class="masthead-brand">全程营销会务管理系统</h3>
              <nav>
                <ul class="nav masthead-nav">
                  <li class="active"><a href="#">登录</a></li>
                  <li><a href="#">注册</a></li>
                  <li><a href="#">帮助</a></li>
                </ul>
              </nav>
            </div>
          </div>

          <div class="inner cover">
            <h1 class="cover-heading">系统介绍</h1>
            <p class="lead">会议掌管是面向会议组织者的一款全程营销会务管理系统，通过对市场活动、营销会议的开始前准备、会议过程中、会议结束后的时间线索，管理协同相关事务的分工、开展、落实，各个会议环节的协同，各类会议数据的实时汇总分析。<p class="lead">
              <a href="<%=basePath%>nowHostelResource.jsp" class="btn btn-lg btn-default">进入系统</a>
            </p>
          </div>

          <div class="mastfoot">
            <div class="inner">
              <p>联系我们 <a href="#">123456@qq.com</a></p>
            </div>
          </div>

        </div>

      </div>

    </div>

    <!-- Bootstrap core JavaScript
    ================================================== -->
    <!-- Placed at the end of the document so the pages load faster -->
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>
    <script>window.jQuery || document.write('<script src="../../assets/js/vendor/jquery.min.js"><\/script>')</script>
    <script src="../../dist/js/bootstrap.min.js"></script>
    <!-- IE10 viewport hack for Surface/desktop Windows 8 bug -->
    <script src="../../assets/js/ie10-viewport-bug-workaround.js"></script>
  </body>
</html>
