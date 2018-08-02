<%@ page language="java" pageEncoding="UTF-8" isELIgnored="false"%>
<%@ taglib prefix="c" uri="/WEB-INF/tld/c.tld" %>
<%@ taglib uri="/WEB-INF/tld/domain.tld" prefix="domain"%>
<c:set var="ctx" value="${pageContext.request.contextPath}" />

<!DOCTYPE html>
<html>
<head>
	<jsp:include page="public/common.jsp"></jsp:include>
	<link rel="stylesheet" href="${ctx}/wap/userCenter/css/style.css">
	<script type="text/javascript">
		function isCardNo(card)  
		{  
		   // 身份证号码为15位或者18位，15位时全为数字，18位前17位为数字，最后一位是校验位，可能为数字或字符X  
		   var reg = /(^\d{15}$)|(^\d{18}$)|(^\d{17}(\d|X|x)$)/;  
		   if(reg.test(card) === false)  
		   {  
		       alert("身份证号输入不合法");
		       return  false;  
		   }  
		   return true;
		}

		function beforeSubmit()
		{
			var realName = document.getElementById('realName');
	    	if(realName.value == ''){
	    		alert("会员姓名不能为空");
	    		realName.focus();
	    		return;
	    	}
	    	
	    	var pid = document.getElementById("pid");
	    	if(pid.value == ''){
	    		alert("身份证号不能为空");
	    		pid.focus();
	    		return;
	    	}
			if(!isCardNo(pid.value)){
                pid.focus();
	    	    return false;
			}
			
			var mobile = document.getElementById("mobile");
			if(mobile.value == ""){
				alert("请输入联系电话！");
				mobile.focus();
				return false;
			}
			if(mobile.value!=''&&!/^1\d{10}$/g.test(mobile.value)){
				alert("联系电话不合法!");
				mobile.focus();
				return false;
			}
			
			var email = document.getElementById("email");
			if(email.value != ""){
				if(email.value!=''&&!/^[a-zA-Z0-9_+.-]+\@([a-zA-Z0-9-]+\.)+[a-zA-Z0-9]{2,4}$/g.test(email.value)){
					alert("邮箱地址格式不合法");
                    email.focus();
					return false;
				}
			}
			
			var detailAddr = document.getElementById('detailAddr');
	    	if(detailAddr.value == ''){
	    		alert("技能描述不能为空");
                detailAddr.focus();
	    		return;
	    	}

			document.frmApply.submit();
		}
	</script>

	<style type="text/css">
	.list-block input[type=text], 
	.list-block input[type=password], 
	.list-block input[type=email], 
	.list-block input[type=tel], 
	.list-block input[type=url], 
	.list-block input[type=date], 
	.list-block input[type=datetime-local], .list-block input[type=time], 
	.list-block input[type=number], .list-block input[type=search], 
	.list-block select, .list-block textarea {
    -webkit-appearance: none;
    -moz-appearance: none;
    -ms-appearance: none;
    appearance: none;
    box-sizing: border-box;
    border: 1;
    background: 0 0;
    border-radius: 0;
    box-shadow: none;
    display: block;
    padding: 0 0 0 .25rem;
    margin: 0;
    width: 100%;
    height: 2.15rem;
    color: #3d4145;
    font-size: .85rem;
    font-family: inherit;
}
	/*下拉框样式*/
	.divselect{
		width: 95%;
		height: 99%;
		border-radius: 5px;
		box-shadow: 0 0 5px #ccc;
		position: relative;
	}
	select{
		border: none;
		outline: none;
		line-height: 2rem;
		appearance: none;
		-webkit-appearance: none;
		-moz-appearance: none;
		padding-left: 60px;
	}

	.divselect:after{
		  content: "∨";
		  width: 1rem;
		  height: 0.6rem;
		  <%--background: url(${ctx}/wap/images/xiala.png) no-repeat center;--%>
		  position: absolute;
		  right: 0.4rem;
		  top: 26%;
	  pointer-events: none;
	}
	</style>
</head>

<body>
	<!-- 公用头部-->
	<header>
		<ul>
			<li class="back">&nbsp;</li>
			<li class="logo">工人申请</li>
			<li class="more">&nbsp;</li>
		</ul>
	</header>

<div class="list-block" >
	<ul class="round">
	    <form name="frmApply" class="cmxform" action="${ctx}/pub/menber/workApplySave.do?type=wap" target="hideframe" method="post" >
			<input type="hidden" name="salesMenId" id="salesMenId" value="${sessionScope.wxmenber.id}" />
			<li class="title"><a href="${ctx}/pub/menber/centerInit.do?type=wap">首页</a>在此申请成为工人</li>
			<li class="nob" style="height: 3rem;font-size: 16px;">
				<table class="kuang" border="0" cellpadding="0" cellspacing="0" width="100%">
					<tr>
						<td width="32%" ><em style="color: red;">*</em>真实姓名：</td>
						<td width="68%" align="left">
							<input name="realName" class="input_text" id="realName" value="" placeholder="请输入真实姓名，必填" type="text" maxlength="50"></td>
					</tr>
				</table>
			</li>
			<li class="nob" style="height: 3rem;font-size: 16px;">
				<table class="kuang" border="0" cellpadding="0" cellspacing="0" width="100%">
					<tr>
						<td width="32%"><em style="color: red;">*</em>身份证号：</td>
						<td width="68%" align="left">
							<input name="pid" class="input_text" id="pid" value="" placeholder="请输入身份证号，必填" type="text" maxlength="50"></td>
					</tr>
				</table>
			</li>
			<li class="nob" style="height: 3rem;font-size: 16px;">
				<table class="kuang" border="0" cellpadding="0" cellspacing="0" width="100%">
					<tr>
						<td width="32%"><em style="color: red;">*</em>手机号码：</td>
						<td width="68%" align="left">
							<input name="mobile" class="input_text" id="mobile" value="" placeholder="请输入手机号码，必填" type="text" maxlength="50"></td>
					</tr>
				</table>
			</li>
			<li class="nob" style="height: 3rem;font-size: 16px;">
				<table class="kuang" border="0" cellpadding="0" cellspacing="0" width="100%">
					<tr>
						<td width="32%"><em style="color: red;">*</em>工作种类：</td>
						<td width="68%" align="left">
							<div class="divselect">
								<domain:selectDomain name="workType" uid="workType_" domain="workType" />
							</div>
						</td>
					</tr>
				</table>
			</li>
			<li class="nob" style="height: 3rem;font-size: 16px;">
				<table class="kuang" border="0" cellpadding="0" cellspacing="0" width="100%">
					<tr>
						<td width="32%"><em style="color: red;">*</em>工作年限：</td>
						<td width="68%" align="left">
							<input name="workYears" class="input_text" id="workYears" value="" placeholder="请输入工作年限，必填" type="text" maxlength="100"></td>
					</tr>
				</table>
			</li>
			<li class="nob" style="height: 3rem;font-size: 16px;">
				<table class="kuang" border="0" cellpadding="0" cellspacing="0" width="100%">
					<tr>
						<td width="32%">&nbsp;电子邮箱：</td>
						<td width="68%" align="left">
							<input name="email" class="input_text" id="email" value="" placeholder="请输入电子邮箱，非必填" type="text" maxlength="50"></td>
					</tr>
				</table>
			</li>
			<li class="nob" style="height: 12rem;font-size: 16px;">
				<table class="kuang" border="0" cellpadding="0" cellspacing="0" width="100%">
					<tr>
						<td width="32%" valign="top"><em style="color: red;">*</em>技能描述：</td>
						<td width="68%" align="left">
						<textarea name="detailAddr" id="detailAddr" rows="8" style="border: 1px solid #e1e1e1;height: 8rem;width: 100%;" type="text" maxlength="400"
								  placeholder="请填写您擅长的技能，我们会根据此项来准确为您派单，必填"></textarea>
						</td>
					</tr>
				</table>
			</li>
	    </form>
	</ul>
	</div>


	<input class="submit" value="提交申请" type="button" onclick="beforeSubmit();return false" />
	
	<br><br><br>

	<!-- 公用底部 -->
	<jsp:include page="public/foot.jsp" flush="false">
		<jsp:param name="menu" value="workApply" />
	</jsp:include>
	
	<iframe name="hideframe" id="hideframe" width="0" height="0"></iframe>
	
</body>
</html>