<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ page import="com.sinovatech.common.config.GlobalConfig"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>后台管理系统</title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />	
<meta http-equiv="X-UA-Compatible" content="IE=7" />
<link href="${pageContext.request.contextPath}/sys/css/login.css" rel="stylesheet" type="text/css" />
<script src="${pageContext.request.contextPath}/js/validate.js"></script>
<script src="${pageContext.request.contextPath}/js/csrfToken.js"></script>
<script src="${pageContext.request.contextPath}/common/js/jquery-1.9.1.min.js" type="text/javascript" charset="UTF-8"></script>
<script type="text/javascript">
	function beforeSubmit()
	{
		var userName = document.getElementById("userName_");
		if(userName.value == "")
		{
			//alert("请输入用户名！");
			//userName.focus();
			//return false;
		}
		if(!/^\w+$/g.test(userName.value)){
			//alert("请输入正确的用户名！");
			//userName.focus();
			//return false;
		}
		
		if(document.getElementById("userPass_").value == "")
		{
			if(document.getElementById("pwd_").value != ""){
				//alert("请在英文状态下重新输入密码！");
				//document.getElementById("pwd_").value="";
				//document.getElementById("pwd_").focus();
				//return false;
			}else{
				//alert("请输入密码！");
				//document.getElementById("pwd_").focus();
				//return false;
			}
		}
		
		var yzm = document.getElementById("yzm_");
		if(yzm.value == "")
		{
			//alert("请输入验证码！");
			//yzm.focus();
			//return false;
		}
		if(yzm.value.length != 5){
			//alert("请输入正确的验证码！");
			//yzm.focus();
			//return false;
		}
			
		
		updateForms("${csrftoken }");
		updateTags("${csrftoken }");
		document.getElementById("pwd_").value="";
		document.logonForm.submit();
	}
	function resetText(){
		document.getElementById("userName_").value="";
		document.getElementById("userPass_").value="";
		document.getElementById("pwd_").value="";
		document.getElementById("yzm_").value="";
	}
	function hiddenPass(evt){
		var pass = document.getElementById("pwd_");
		var j_pass = document.getElementById("userPass_");
		evt = (evt) ? evt : ((window.event) ? window.event : ""); //兼容IE和Firefox获得keyBoardEvent对象
		var keycode = evt.keyCode?evt.keyCode:evt.which;//兼容IE和Firefox获得keyBoardEvent对象的键值 
		
		if(keycode==9 || keycode==13){
			check();
　　		}
		var keychar=String.fromCharCode(keycode);
		if(pass.value.length>=j_pass.value.length){
			j_pass.value=j_pass.value+keychar;
		}else{
			j_pass.value=j_pass.value.substring(0,pass.value.length+1);
		}
	}           
	function clearPass(){
		$("#pwd_").val("");
		$("#userPass_").val("");
	}
	
	function checkChinese(){
	
			var pass = document.getElementById("pwd_");
		    var j_pass = document.getElementById("userPass_");

		if(document.getElementById("userPass_").value == "")
		{
			if(document.getElementById("pwd_").value != ""){
				alert("请在英文状态下重新输入密码！");
				document.getElementById("pwd_").value="";
				return false;
			}
		}
	} 
	
		function OnInput (event) {
		    
			event.target.value=event.target.value.replace(/./g,'*');
			checkChinese();

	}  
	

	
</script>
<%
	//去配置文件取验证码是否启用的配置，true为启用false为停用
	String useIdentify = GlobalConfig.getProperty("bms", "identify");
	//字符串转成布尔类型
	//Boolean UseIdentify=Boolean.parseBoolean(identify);
	
	String contextUrl = request.getContextPath();
	String url = GlobalConfig.getProperty("bms", "logo_login_url");
	String logo_login_url = contextUrl + url;

%>
</head>
<body scroll="no" id="body">
<style>
#cont0621{text-align:center;}
#page{margin:0 auto;}
</style>
		<div id="cont0621">
			<div style="height: 900px;" id="page">
				<form name="logonForm" method="post"
					action="${pageContext.request.contextPath}/sys/login/login.do">
					<input name="userPwd" id="userPass_" type="hidden" value=""/>
					<div id="logo">
						<img src="${pageContext.request.contextPath}/common/images/logo.png"  />
					</div>
					<div id="ap">
						<div class="err"></div>
						<div class="content">
							<div id="inp_zh">
								<input name="userName" id="userName_" type="text" value="admin"/>
								<!--<input name="userName" id="userName_" type="text" value="${userName }"/>-->
							</div>
							<div id="inp_mm">
								<input name="pwd" id="pwd_" type="text" style="ime-mode:disabled"
									onfocus="javascript:clearPass();"
									onkeyup="this.value=this.value.replace(/./g,'*');"
									onkeypress="javascript:hiddenPass(event)" 
									onkeyup="this.value=this.value.replace(/[\u4e00-\u9fa5]/g,'');"
								    oninput="javascript:OnInput(event);"
								    onkeypress="javascript:hiddenPass(event);"
								    onpaste="return false" 
								    onchange="javascript:checkChinese();"
									value="test123"
									/>
							</div>
							
							<div id="inp_yzm">
								<input id="yzm_" name="yzm" type="text" maxlength="5" value="test1"/>
								<img width="65px" height="25px"
									style="cursor: pointer; border: 1px solid #cc00; margin-left: 1px;"
									src="${pageContext.request.contextPath}/yzmservlet"
									onclick="var s=Math.random();this.src='${pageContext.request.contextPath}/yzmservlet?1='+s;"
									alt="看不清请刷新" />
							</div>
							
							<div id="inp_but">
								<input id="submit"
									src="${pageContext.request.contextPath}/sys/images/login.png"
									onClick="beforeSubmit();return false" type="image" />
								<input id="reset"
									src="${pageContext.request.contextPath}/sys/images/reset.png"
									onClick="resetText();return false" type="image" />
							</div>
						</div>
					</div>
				</form>
			</div>
		</div>
	</body>
</html>
<script language="javascript">
	if("${userName }"!=""){
		document.getElementById("pwd_").focus();
	}else{
		document.getElementById("userName_").focus();
	}
	if("${msgs }"!=""){
		alert("${msgs }");
	}
</script>
