<%@page import="com.google.gson.reflect.TypeToken"%>
<%@page import="cwu.jsj.entity.HostelBean"%>
<%@page import="com.google.gson.Gson"%>
<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
			HostelBean beanRequest = null;
			if(request.getSession().getAttribute("jsonStr")!=null)
			{
			String json = (String) request.getSession().getAttribute("jsonStr");
			
			Gson gson = new Gson();
			try{
			 beanRequest= gson.fromJson(json, new TypeToken<HostelBean>(){}.getType());
			System.out.print(beanRequest);
			 }catch(Exception e){
			 
			 }
            }
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<base href="<%=basePath%>">

<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<!-- 上述3个meta标签*必须*放在最前面，任何其他内容都*必须*跟随其后！ -->

<meta charset="UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">

<link rel="stylesheet"
	href="<%=basePath%>bootstrap/css/bootstrap.min.css" />

<link
	href="http://netdna.bootstrapcdn.com/font-awesome/3.2.1/css/font-awesome.min.css"
	rel="stylesheet">

<!-- 警告：通过 file:// 协议（就是直接将 html 页面拖拽到浏览器中）访问页面时 Respond.js 不起作用 -->
<!--[if lt IE 9]>
      <script src="https://cdn.bootcss.com/html5shiv/3.7.3/html5shiv.min.js"></script>
      <script src="https://cdn.bootcss.com/respond.js/1.4.2/respond.min.js"></script>
    <![endif]-->

</head>
<body>
	<div class="container" style="background-color: #FFFFFF;">
		<form class="form-horizontal" action="<%=basePath%>createHotel"
			onsubmit="return isSubmitNameNull()">
			<!--表单中的栅栏系统用form-horizontal，其他的用row -->
			<h1>酒店</h1>
			<div class="form-group">
				<!--control-label控制label和input之间的水平距离 -->
				<label class="col-md-2 control-label" for="hotelName">酒店名称</label>
				<div class="col-md-8">
					<input type="text" class="form-control" name="hotelName"
						id="hotelName" placeholder="请输入酒店名称"  value="<%try{ %> <%=beanRequest.getHotelName()%><%}catch(Exception e){
						} %>"/>

				</div>
				<div class="col-md-1">
					<font color="red">*</font>
				</div>

			</div>
			<div class="form-group">
				<center>
					<div class="col-md-4 pull-right">
						<input type="text" id="hotelNameNull"
							style="border-style:none;display:none" />
					</div>
				</center>
			</div>
			<div class="form-group" data-trigger="spinner">
				<label class="col-md-2 control-label" for="roomPeopleNumber">房间可容纳人数</label>

				<div class="col-sm-1">
					<center>
						<button type="button" class="btn btn-xs btn-primary"
							onclick="addRoomPeopleNumber()">+</button>
					</center>
					<input type="text" data-rule="quantity" class="form-control"
						name="roomPeopleNumber" id="roomPeopleNumber" value="1" />
					<center>
						<button type="button" class="btn btn-xs btn-primary"
							onclick="subRoomPeopleNumber()">--</button>
					</center>
				</div>
			</div>
			<div class="form-group">
				<label class="col-md-2 control-label" for="roomAvailableNumber">可预定数量</label>
				<div class="col-sm-1">
					<center>
						<button type="button" class="btn btn-xs btn-primary"
							onclick="addRoomAvailableNumber()">+</button>
					</center>
					<input type="text" class="form-control" name="roomAvailableNumber"
						id="roomAvailableNumber" id="roomAvailableNumber" value="1" />
					<center>
						<button type="button" class="btn btn-xs btn-primary"
							onclick="subRoomAvailableNumber()">--</button>
					</center>
				</div>
			</div>
			<div class="form-group">
				<label class="col-md-2 control-label" for="money">单价/元</label>
				<div class="col-md-8">
					<input type="text" class="form-control" name="money" id="money"
						placeholder="请输入单价"  value="<%try{ %> <%=beanRequest.getMoney()%><%}catch(Exception e){} %>" />
				</div>
				<div class="col-md-1">
					<font color="red">*</font>
				</div>

			</div>
			<div class="form-group">
				<center>
					<div class="col-md-4 pull-right">
						<input type="text" id="moneyNull"
							style="border-style:none;display:none" />
					</div>
				</center>
			</div>
			<div class="form-group">
				<label class="col-md-2 control-label" for="hotelAddress">酒店地址</label>
				<div class="col-md-8">
					<input type="text" class="form-control" name="hotelAddress"
						id="hotelAddress" placeholder="请输入酒店地址"  value="<%try{ %><%=beanRequest.getHotelAddress()%><%}catch(Exception e){}%>"/>
				</div>
				<div class="col-md-1">
					<font color="red">*</font>
				</div>

			</div>
			<div class="form-group">
				<center>
					<div class="col-md-4 pull-right">
						<input type="text" id="hotelAddressNull"
							style="border-style:none;display:none" />
					</div>
				</center>
			</div>
			<div class="form-group">
				<label class="col-md-2 control-label" for="hotelCompany">酒店隶属</label>
				<div class="col-md-8">
					<input type="text" class="form-control" name="hotelCompany"
						id="hotelCompany" placeholder="请输入酒店所属公司"  value="<%try{ %><%=beanRequest.getHotelAddress()%><%}catch(Exception e){}%>"/>
				</div>
				<div class="col-md-1">
					<font color="red">*</font>
				</div>

			</div>
			<div class="form-group">
				<center>
					<div class="col-md-4 pull-right">
						<input type="text" id="hotelCompanyNull"
							style="border-style:none;display:none" />
					</div>
				</center>
			</div>
			<div class="form-group">
				<label class="col-md-2 control-label">上传酒店图片</label>
				<div class="col-md-10">
					<input type="file" />
					<p class="help-block">上传的图片类型只能是:.jpg/.gif/.png</p>
				</div>
			</div>

			<div class="form-group">
				<label class="col-md-2 control-label">房间类型:</label>
				<div class="col-md-9">
					<label class="radio-inline" > <input type="radio"
						name="roomType" value="standard" checked="checked" />标间
					</label> <label class="radio-inline"> <input type="radio"
						name="roomType" value="bigBed" />大床房
					</label> <label class="radio-inline"> <input type="radio"
						name="roomType" value="businesss" />商务房
					</label> <label class="radio-inline"> <input type="radio"
						name="roomType" value="lluxury" />奢华总统套间
					</label>

				</div>
				<div class="col-md-1">
					<font color="red">*</font>
				</div>
			</div>

			<div class="form-group">
				<label class="col-md-2 control-label">酒店简介:</label>
				<div class="col-md-8">
					<textarea class="form-control" rows="5" placeholder="请输入你的个人简介信息"></textarea>
				</div>
			</div>
			<div class="form-group">
				<label class="col-md-2 control-label" for="hotelPhoneNumber">酒店联系方式</label>
				<div class="col-md-8">
					<input type="text" class="form-control" name="hotelPhoneNumber"
						id="hotelPhoneNumber" placeholder="请输入酒店联系方式"  value="<%try{ %><%=beanRequest.getHotelPhoneNumber()%><%}catch(Exception e){}%>"/>
				</div>

				<div class="col-md-1">
					<font color="red">*</font>
				</div>
			</div>
			<div class="form-group">
				<center>
					<div class="col-md-4 pull-right">
						<input type="text" id="hotelPhoneNumberNull"
							style="border-style:none;display:none" />
					</div>
				</center>
			</div>
			<div class="form-group">
				<div class="col-md-10 col-md-offset-2">
					<br>
					<center>
						<button type="submit" class="btn btn-lg btn-primary">添加</button>
					</center>
				</div>
			</div>
		</form>

	</div>
	<br>
	<form>
		<script type="text/javascript">
			function addRoomAvailableNumber() {
				var num = document.getElementById("roomAvailableNumber");
				num.value = parseInt(num.value) + 1;
			}
			function subRoomAvailableNumber() {
				var num = document.getElementById("addRoomAvailableNumber");
				if (parseInt(num.value) > 0) {
					num.value = parseInt(num.value) - 1;
				}
			}
			function addRoomPeopleNumber() {
				var num = document.getElementById("roomPeopleNumber");
				num.value = parseInt(num.value) + 1;
			}
			function subRoomPeopleNumber() {
				var num = document.getElementById("roomPeopleNumber");
				if (parseInt(num.value) > 0) {
					num.value = parseInt(num.value) - 1;
				}
			}

			function isExistNull(a, b) {
				var nullValue = document.getElementById(a).value.replace(
						/(^\s*)|(\s*$)/g, '');
				if (nullValue == '' || nullValue == undefined
						|| nullValue == null) {//输入框中输入空格也为空
					var nullHotelValue = document.getElementById(b);
					nullHotelValue.style.display = "block";
					nullHotelValue.value = "输入不能为空！";
					nullHotelValue.style.color = "red";
					nullHotelValue.style.border = "none";
					nullHotelValue.style.fontSize = "20px";
					window.event.cancelBubble = true;
					return false;
				}
				window.event.cancelBubble = true;
				return true;

			}
			function isNumber() {
				var number = document.getElementById("money").value.replace(
						/(^\s*)|(\s*$)/g, '');
				if (parseFloat(number) > 0) {
					return true;
				}
				document.getElementById("moneyNull").style.display = "block";
				document.getElementById("moneyNull").value = "房间单价无效输入！";
				document.getElementById("moneyNull").style.color = "red";
				document.getElementById("moneyNull").style.border = "none";
				document.getElementById("moneyNull").style.fontSize = "20px";
				
				return false;
			}
			function checkMobile() {
			    var Number =document.getElementById("hotelPhoneNumber");
				var length = Number.value.length;
				if (length == 11
						&& /^(((13[0-9]{1})|(15[0-9]{1})|(18[0-9]{1})|(14[0-9]{1})|)+\d{8})$/
								.test(Number.value)) {
					return true;
				} else {
				document.getElementById("hotelPhoneNumberNull").style.display = "block";
				document.getElementById("hotelPhoneNumberNull").value = "电话号码无效输入！";
				document.getElementById("hotelPhoneNumberNull").style.color = "red";
				document.getElementById("hotelPhoneNumberNull").style.border = "none";
				document.getElementById("hotelPhoneNumberNull").style.fontSize = "20px";
					return false;
				}
			}
			function isSubmitNameNull() {
				return isExistNull("hotelName", "hotelNameNull")
						&& isExistNull("money", "moneyNull")
						&& isExistNull("hotelAddress", "hotelAddressNull")
						&& isExistNull("hotelCompany", "hotelCompanyNull")
						&& isExistNull("hotelPhoneNumber","hotelPhoneNumberNull")
						&& isNumber()
						&& checkMobile();
						
			}
		</script>



		<!-- jQuery (Bootstrap 的所有 JavaScript 插件都依赖 jQuery，所以必须放在前边) -->
		<script src="https://cdn.bootcss.com/jquery/1.12.4/jquery.min.js"></script>
		<!-- 加载 Bootstrap 的所有 JavaScript 插件。你也可以根据需要只加载单个插件。 -->
		<script
			src="https://cdn.bootcss.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
</body>
</html>
