<%@page import="cwu.jsj.entity.CarBean"%>
<%@page import="com.google.gson.reflect.TypeToken"%>
<%@page import="cwu.jsj.entity.SiteBean"%>
<%@page import="com.google.gson.Gson"%>
<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
			CarBean beanRequest = null;
			if(request.getSession().getAttribute("jsonStr")!=null)
			{
			String json = (String) request.getSession().getAttribute("jsonStr");
			
			Gson gson = new Gson();
			try{
			 beanRequest= gson.fromJson(json, new TypeToken<CarBean>(){}.getType());
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
		<form class="form-horizontal" action="<%=basePath%>createCar"
			onsubmit="return isSubmitNameNull()">
			<!--表单中的栅栏系统用form-horizontal，其他的用row -->
			<h1>车辆</h1>
			<div class="form-group">
				<!--control-label控制label和input之间的水平距离 -->
				<label class="col-md-2 control-label" for="carCompany">车辆公司名称</label>
				<div class="col-md-8">
					<input type="text" class="form-control" name="carCompany"
						id="carCompany"  placeholder="请输入车辆公司名称"  value="<%try{ %> <%=beanRequest.getCarCompany()%><%}catch(Exception e){
						} %>"/>

				</div>
				<div class="col-md-1">
					<font color="red">*</font>
				</div>

			</div>
			<div class="form-group">
				<center>
					<div class="col-md-4 pull-right">
						<input type="text" id="carCompanyNull"
							style="border-style:none;display:none" />
					</div>
				</center>
			</div>
			<div class="form-group" data-trigger="spinner">
				<label class="col-md-2 control-label" for="carPeopleNumber">可容纳人数</label>

				<div class="col-sm-1">
					<center>
						<button type="button" class="btn btn-xs btn-primary"
							onclick="addCarPeopleNumber()">+</button>
					</center>
					<input type="text" data-rule="quantity" class="form-control"
						name="carPeopleNumber" id="carPeopleNumber" value="1" />
					<center>
						<button type="button" class="btn btn-xs btn-primary"
							onclick="subCarPeopleNumber()">--</button>
					</center>
				</div>
			</div>
			<div class="form-group">
				<label class="col-md-2 control-label" for="carAvailableNumber">可预定数量</label>
				<div class="col-sm-1">
					<center>
						<button type="button" class="btn btn-xs btn-primary"
							onclick="addCarAvailableNumber()">+</button>
					</center>
					<input type="text" class="form-control" name="carAvailableNumber"
						id="carAvailableNumber"  value="1" />
					<center>
						<button type="button" class="btn btn-xs btn-primary"
							onclick="subCarAvailableNumber()">--</button>
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
				<label class="col-md-2 control-label" for="Address">车辆公司地址</label>
				<div class="col-md-8">
					<input type="text" class="form-control" name="Address"
						id="Address" placeholder="请输入车辆公司地址"  value="<%try{ %><%=beanRequest.getAddress()%><%}catch(Exception e){}%>"/>
				</div>
				<div class="col-md-1">
					<font color="red">*</font>
				</div>

			</div>
			<div class="form-group">
				<center>
					<div class="col-md-4 pull-right">
						<input type="text" id="AddressNull"
							style="border-style:none;display:none" />
					</div>
				</center>
			</div>
			<div class="form-group">
				<label class="col-md-2 control-label">上传车辆图片</label>
				<div class="col-md-10">
					<input type="file" />
					<p class="help-block">上传的图片类型只能是:.jpg/.gif/.png</p>
				</div>
			</div>

			<div class="form-group">
				<label class="col-md-2 control-label">车辆类型:</label>
				<div class="col-md-9">
					<label class="radio-inline" > <input type="radio"
						name="vehicleType" value="big" checked="checked" />中大型
					</label> <label class="radio-inline"> <input type="radio"
						name="vehicleType" value="luxury" />奢华商务型
					</label> <label class="radio-inline"> <input type="radio"
						name="vehicleType" value="suv" />运动型多用途
					</label> <label class="radio-inline"> <input type="radio"
						name="vehicleType" value="Roadster" />跑车
					</label>

				</div>
				<div class="col-md-1">
					<font color="red">*</font>
				</div>
			</div>

			<div class="form-group">
				<label class="col-md-2 control-label">车辆简介:</label>
				<div class="col-md-8">
					<textarea class="form-control" rows="5" placeholder="请输入车辆简介信息"></textarea>
				</div>
			</div>
			<div class="form-group">
				<label class="col-md-2 control-label" for="carPhoneNumber">车辆公司联系方式</label>
				<div class="col-md-8">
					<input type="text" class="form-control" name="carPhoneNumber"
						id="carPhoneNumber" placeholder="请输入车辆公司联系方式"  value="<%try{ %><%=beanRequest.getCarPhoneNumber()%><%}catch(Exception e){}%>"/>
				</div>

				<div class="col-md-1">
					<font color="red">*</font>
				</div>
			</div>
			<div class="form-group">
				<center>
					<div class="col-md-4 pull-right">
						<input type="text" id="carPhoneNumberNull"
							style="border-style:none;display:none" />
					</div>
				</center>
			</div>
			<div class="form-group">
				<div class="col-md-10 col-md-offset-2">
					<br>
					<center>
						<button type="submit" class="btn btn-lg btn-primary">添加车辆</button>
					</center>
				</div>
			</div>
		</form>

	</div>
	<br>
	<form>
		<script type="text/javascript">
			function addCarPeopleNumber() {
				var num = document.getElementById("carPeopleNumber");
				num.value = parseInt(num.value) + 1;
			}
			function subCarPeopleNumber() {
				var num = document.getElementById("carPeopleNumber");
				if (parseInt(num.value) > 0) {
					num.value = parseInt(num.value) - 1;
				}
			}
			function addCarAvailableNumber() {
				var num = document.getElementById("carAvailableNumber");
				num.value = parseInt(num.value) + 1;
			}
			function subCarAvailableNumber() {
				var num = document.getElementById("carAvailableNumber");
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
				document.getElementById("moneyNull").value = "车辆单价无效输入！";
				document.getElementById("moneyNull").style.color = "red";
				document.getElementById("moneyNull").style.border = "none";
				document.getElementById("moneyNull").style.fontSize = "20px";
				
				return false;
			}
			function checkMobile() {
			    var Number =document.getElementById("carPhoneNumber");
				var length = Number.value.length;
				if (length == 11
						&& /^(((13[0-9]{1})|(15[0-9]{1})|(18[0-9]{1})|(14[0-9]{1})|)+\d{8})$/
								.test(Number.value)) {
					return true;
				} else {
				document.getElementById("carPhoneNumberNull").style.display = "block";
				document.getElementById("carPhoneNumberNull").value = "电话号码无效输入！";
				document.getElementById("carPhoneNumberNull").style.color = "red";
				document.getElementById("carPhoneNumberNull").style.border = "none";
				document.getElementById("carPhoneNumberNull").style.fontSize = "20px";
					return false;
				}
			}
			function isSubmitNameNull() {
				return isExistNull("carCompany", "carCompanyNull")
						&& isExistNull("money", "moneyNull")
						&& isExistNull("Address", "AddressNull")
						&& isExistNull("carPhoneNumber","carPhoneNumberNull")
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
