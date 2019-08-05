<%@page import="cwu.jsj.entity.ParticipantBean"%>
<%@page import="cwu.jsj.dao.ParterDao"%>
<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!doctype html>
<html lang="en">

  <head>
    <base href="<%=basePath%>">
    
    <title>全程营销会务管理系统</title>
    
<meta charset="utf-8" />
	<link rel="icon"  href="<%=basePath%>img/favicon.ico">
	<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1" />
	
	<meta content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=0" name="viewport" />
    <link href="<%=basePath%>bootstrap/css/bootstrap.css" rel="stylesheet" />
    <link href="<%=basePath%>bootstrap/css/fresh-bootstrap-table.css" rel="stylesheet" />
    <link href="http://maxcdn.bootstrapcdn.com/font-awesome/4.2.0/css/font-awesome.min.css" rel="stylesheet">
    <link href='http://fonts.googleapis.com/css?family=Roboto:400,700,300' rel='stylesheet' type='text/css'>
  </head>
  
 <body>

<div class="wrapper">
    <div class="fresh-table full-color-blue full-screen-table">
        <div class="toolbar">
            <button id="alertBtn" class="btn btn-default">查看报名信息</button>
        </div>
        
        <table id="fresh-table" class="table">
            <thead>
                <th data-field="participantName">参会者姓名</th>
            	<th data-field="participantMeetingId" data-sortable="true">会议号</th>
            	<th data-field="participantPosition" data-sortable="true">举办地址</th>
            	<th data-field="participantCompany" data-sortable="true">参会者隶属</th>
            	<th data-field="participantMobilePhone" data-sortable="true">参会者联系方式</th>
            	<th data-field="participantChoseChildMeeting" data-sortable="true">子会议号</th>
            	<th data-field="participantDemaind" data-sortable="true">特殊需求</th>
            	<th data-field="participantTag" data-sortable="true">是否签到</th>
            	<!-- <th data-field="actions" data-formatter="operateFormatter" data-events="operateEvents">Actions</th>
             --></thead>
            <%
            ParterDao dao = new ParterDao();
            List<ParticipantBean> list = new ArrayList();
            list = dao.lookAllSignUp();
            for(ParticipantBean participant:list){
           
             %>
            <tbody>
                <tr>
                	<td><%=participant.getParticipantName()%></td>
                	<td><%=participant.getParticipantMeetingId()%></td>
                	<td><%=participant.getParticipantPosition()%></td>
                	<td><%=participant.getParticipantCompany()%></td>
                	<td><%=participant.getParticipantMobilePhone()%></td>
                	<td><%=participant.getParticipantChoseChildMeeting()%></td>
                	<td><%=participant.getParticipantDemaind()%></td>
                	<td><% if(participant.getParticipantTag()==1){%><%="是" %><%}else{ %><%="否" %><%} %></td>
                
                </tr>
               
              <%} %>
            </tbody>
        </table>
    </div>
    
</div>


</body>
	<script type="text/javascript" src="<%=basePath %>bootstrap/js/jquery-1.11.2.min.js"></script>
    <script type="text/javascript" src="<%=basePath %>bootstrap/js/bootstrap.js"></script>
    <script type="text/javascript" src="<%=basePath %>bootstrap/js/bootstrap-table.js"></script>
       <script type="text/javascript">
        var $table = $('#fresh-table'),
            $alertBtn = $('#alertBtn'), 
            full_screen = false,
            window_height;
            
        $().ready(function(){
            
            window_height = $(window).height();
            table_height = window_height - 20;
            
            
            $table.bootstrapTable({
                toolbar: ".toolbar",

                showRefresh: true,
                search: true,
                showToggle: true,
                showColumns: true,
                pagination: true,
                striped: true,
                sortable: true,
                height: table_height,
                pageSize: 25,
                pageList: [25,50,100],
                
                formatShowingRows: function(pageFrom, pageTo, totalRows){
                    //do nothing here, we don't want to show the text "showing x of y from..." 
                },
                formatRecordsPerPage: function(pageNumber){
                    return pageNumber + " rows visible";
                },
                icons: {
                    refresh: 'fa fa-refresh',
                    toggle: 'fa fa-th-list',
                    columns: 'fa fa-columns',
                    detailOpen: 'fa fa-plus-circle',
                    detailClose: 'fa fa-minus-circle'
                }
            });
            
            window.operateEvents = {
                'click .like': function (e, value, row, index) {
                    alert('You click like icon, row: ' + JSON.stringify(row));
                    console.log(value, row, index);
                },
                'click .edit': function (e, value, row, index) {
                    alert('You click edit icon, row: ' + JSON.stringify(row));
                    console.log(value, row, index);    
                },
                'click .remove': function (e, value, row, index) {
                    $table.bootstrapTable('remove', {
                        field: 'id',
                        values: [row.id]
                    });
            
                }
            };
            
            $alertBtn.click(function () {
                alert("You pressed on Alert");
            });
        
            
            $(window).resize(function () {
                $table.bootstrapTable('resetView');
            });    
        });
        

        function operateFormatter(value, row, index) {
            return [
                '<a rel="tooltip" title="Like" class="table-action like" href="javascript:void(0)" title="Like">',
                    '<i class="fa fa-heart"></i>',
                '</a>',
                '<a rel="tooltip" title="Edit" class="table-action edit" href="javascript:void(0)" title="Edit">',
                    '<i class="fa fa-edit"></i>',
                '</a>',
                '<a rel="tooltip" title="Remove" class="table-action remove" href="javascript:void(0)" title="Remove">',
                    '<i class="fa fa-remove"></i>',
                '</a>'
            ].join('');
        }
       
    </script>
</html>

