<%@ page language="java" pageEncoding="UTF-8" isELIgnored="false"%>
<%@ taglib prefix="c" uri="/WEB-INF/tld/c.tld" %>
<c:set var="ctx" value="${pageContext.request.contextPath}" />

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<!-- 公用JS|CSS-->
	<jsp:include page="../public/common.jsp"></jsp:include>
	<link rel="stylesheet" href="${ctx}/wap/userCenter/css/style.css">
	<script type="text/javascript">
		function isCardNo(card)  
		{  
		   // 身份证号码为15位或者18位，15位时全为数字，18位前17位为数字，最后一位是校验位，可能为数字或字符X  
		   var reg = /(^\d{15}$)|(^\d{18}$)|(^\d{17}(\d|X|x)$)/;  
		   if(reg.test(card) === false)  
		   {  
		       alert("身份证输入不合法");  
		       return  false;  
		   }  
		   return true;
		}

		function beforeSubmit(mobile_old,pid_old)
		{
			var mobile = document.getElementById("mobile");
			if(mobile.value!=''&&!/^1\d{10}$/g.test(mobile.value)){
				alert("手机号码格式不合法");
				return false;
			}
			
			var realName = document.getElementById('realName');
	    	if(realName.value == ''){
	    		alert("您的姓名不能为空");
	    		realName.focus();
	    		return;
	    	}
	    	
	    	var pid = document.getElementById("pid");
	    	if(pid.value == ''){
	    		alert("您的身份证不能为空");
	    		pid.focus();
	    		return;
	    	}
			if(!isCardNo(pid.value)){
				return false;
			}
			
			var email = document.getElementById("email");
			if(email.value == ''){
				alert("邮箱不能为空");
				email.focus();
				return false;
			}
			
			if((mobile_old != mobile.value) && (pid_old != pid.value)){
				//检查手机号码和身份证号码是否存在
				$.ajax({
					 type:"post",
					 dataType:"html",
					 url:"${ctx}/pc/checkLoginName.do",
					 data:"loginName="+mobile.value+"&pid="+pid.value,
					 async:false,
					 complete: function (XMLHttpRequest,textStatus) {
					    if(XMLHttpRequest.responseText!=""){
		                    if(XMLHttpRequest.responseText==1){
						 		alert("手机号码已经存在！");
								return false;
						 	}else if(XMLHttpRequest.responseText==2){
						 		alert("身份证号码已经存在！");
								return false;
						 	}else{
						 		document.frmApply.submit();
						 	}
		                }
					 }
				});
			}else if((mobile_old != mobile.value) && (pid_old == pid.value)){
				//检查手机号码是否存在
				$.ajax({
					 type:"post",
					 dataType:"html",
					 url:"${ctx}/pc/checkLoginName.do",
					 data:"loginName="+mobile.value,
					 async:false,
					 complete: function (XMLHttpRequest,textStatus) {
					    if(XMLHttpRequest.responseText!=""){
		                    if(XMLHttpRequest.responseText==1){
						 		alert("手机号码已经存在！");
								return false;
						 	}else{
						 		document.frmApply.submit();
						 	}
		                }
					 }
				});
			}else if((mobile_old == mobile.value) && (pid_old != pid.value)){
				//检查身份证号码是否存在
				$.ajax({
					 type:"post",
					 dataType:"html",
					 url:"${ctx}/pc/checkLoginName.do",
					 data:"pid="+pid.value,
					 async:false,
					 complete: function (XMLHttpRequest,textStatus) {
					    if(XMLHttpRequest.responseText!=""){
		                    if(XMLHttpRequest.responseText==2){
						 		alert("身份证号码已经存在！");
								return false;
						 	}else{
						 		document.frmApply.submit();
						 	}
		                }
					 }
				});
			}else{
				document.frmApply.submit();
			}
			
			
			
		}
	</script>
</head>

<body>
	<!-- 公用头部-->
	<jsp:include page="head.jsp"></jsp:include>

	<ul class="round">
	    <form name="frmApply" class="cmxform" action="${ctx}/pub/menber/editFront.do?type=wap" target="hideframe" method="post" >
			 <input type="hidden" name="id" id="id" value="${m.id}" />
			<li class="title">
				<a href="${ctx}/pub/menber/centerInit.do?type=wap" >返回</a>
				基本信息
			</li>
			<li class="nob">
			  <table class="kuang" border="0" cellpadding="0" cellspacing="0" width="100%">
					<tr>
						<th width="25%" align="right">*真实姓名：</th>
						<td width="75%">
							<c:if test="${null==m.realName || m.realName==''}">
								<input name="realName" class="px" id="realName" value="${m.realName}" placeholder="请输入您的真实姓名" type="text">
							</c:if>
							<c:if test="${null!=m.realName && m.realName!=''}">
								${m.realName}
								<input type="hidden" id="realName" name="realName" value="${m.realName}" />
							</c:if>
						</td>
					</tr>
				</table>
			</li>
			<li class="nob">
				<table class="kuang" border="0" cellpadding="0" cellspacing="0" width="100%">
					<tr>
						<th width="25%" align="right">*身份证号：</th>
						<td width="75%">
							<c:if test="${null==m.pid || m.pid==''}">
								<input name="pid" class="px" id="pid" value="${m.pid}" placeholder="请输入您的身份证" type="tel"
								onkeyup='this.value=this.value.replace(/[^A-Za-z0-9]/gi,""); '
								onafterpaste='this.value=this.value.replace(/[^A-Za-z0-9]/gi,"")'>
							</c:if>
							<c:if test="${null!=m.pid && m.pid!=''}">
								${m.pid}
								<input type="hidden" id="pid" name="pid" value="${m.pid}" />
							</c:if>
						</td>
					</tr>
				</table>
			</li>
			<li class="nob">
				<table class="kuang" border="0" cellpadding="0" cellspacing="0" width="100%">
					<tr>
						<th width="25%" align="right">*联系电话：</th>
						<td width="75%">
							<input name="mobile" class="px" id="mobile" value="${m.mobile}" placeholder="请输入您的联系电话" type="tel"
								onkeyup='this.value=this.value.replace(/[^0-9]/gi,""); '
								onafterpaste='this.value=this.value.replace(/[^0-9]/gi,"")'>
						</td>
					</tr>
				</table>
			</li>
	        <li class="nob">
				<table class="kuang" border="0" cellpadding="0" cellspacing="0" width="100%">
					<tr>
						<th width="25%" align="right">*电子邮箱：</th>
						<td width="75%"><input name="email" class="px" id="email" value="${m.email}" placeholder="请输入您的电子邮箱" ></td>
					</tr>
				</table>
			</li>
			
			<li class="title">紧急联系人信息</li>
			<li class="nob">
				<table class="kuang" border="0" cellpadding="0" cellspacing="0" width="100%">
					<tr>
						<th width="25%" align="right">姓名：</th>
						<td width="75%">
							<input name="realNameEmergency" class="px" id="realNameEmergency" value="${m.realNameEmergency}" placeholder="请输入紧急联系人的真实姓名" type="text">
						</td>
					</tr>
				</table>
			</li>
	        <li class="nob">
				<table class="kuang" border="0" cellpadding="0" cellspacing="0" width="100%">
					<tr>
						<th width="25%" align="right">联系电话：</th>
						<td width="75%">
							<input name="mobileEmergency" class="px" id="mobileEmergency" value="${m.mobileEmergency}" placeholder="请输入紧急联系人的联系电话" type="tel"
							    onkeyup='this.value=this.value.replace(/[^0-9]/gi,""); '
								onafterpaste='this.value=this.value.replace(/[^0-9]/gi,"")'>
						</td>
					</tr>
				</table>
			</li>
	    </form>
	</ul><br />
	
	<input class="submit" value="提交" type="button" onclick="beforeSubmit('${m.mobile}','${m.pid}');return false" />
	
	<br><br><br><br>
	<!-- foot -->
	<jsp:include page="../public/foot.jsp"></jsp:include>
	
	<iframe name="hideframe" id="hideframe" width="0" height="0"></iframe>
	
</body>
</html>
