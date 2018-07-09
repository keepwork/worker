<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib uri="/WEB-INF/tld/domain.tld" prefix="domain"%>
<%@ include file="../common/commonHeader.jsp"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3c.org/TR/1999/REC-html401-19991224/loose.dtd">
<!-- saved from url=(0070)http://192.168.2.36:8011/cms/info/index.do -->
<HTML xmlns="http://www.w3.org/1999/xhtml">
<HEAD>
	<TITLE>后台管理系统</TITLE>
	<meta http-equiv="content-type" content="text/html; charset=UTF-8">
	<meta http-equiv="X-UA-Compatible" content="IE=EmulateIE7" />
	<meta name=generator content="MSHTML 8.00.6001.18939">
	<link href="${pageContext.request.contextPath}/sys/css/public.css" rel="stylesheet" type="text/css">
	<link href="${pageContext.request.contextPath}/sys/css/style.css" rel="stylesheet" type="text/css">
	<script type="text/javascript" src="${pageContext.request.contextPath}/js/mootools-release-1.11.js"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath}/js/passwordRule.js"></script>
	
	<script type="text/javascript">
	function beforeSubmit()
	{
			//密码验证
			if(!loginPwd("oldPass","newPass1",1)){
				return false;
			}
			var oldPass = document.getElementById("oldPass");
			if(oldPass.value == "")
			{
				alert("请输入旧密码");
				oldPass.focus();
				return false;
			}
	
			var newPass1 = document.getElementById("newPass1");
			if(newPass1.value == "")
			{
				alert("请输入新密码");
				newPass1.focus();
				return false;
			}
			
			var newPass2 = document.getElementById("newPass2");
			if(newPass2.value != newPass1.value)
			{
				alert("两次新密码输入不匹配");
				newPass2.focus();
				return false;
			}
			document.frmApply.submit();
	}
	function resetText(){
		document.getElementById("oldPass").value="";
		document.getElementById("newPass1").value="";
		document.getElementById("newPass2").value="";
	}
	</script>
</HEAD>
<body class="overfwidth">

<div class="barnavtop">您所在的位置：更改密码</div>
<div id="workspace">
	<!--主体 开始-->
    <div id="container">
    	<!--按钮 开始-->  
        <!--按钮 结束-->  
        <!--框内内容 开始-->
          <div class="editspace">
            <form name="frmApply" class="cmxform"
							action="${pageContext.request.contextPath}/sys/bmsuser/changePassword.do"
							target="hideframe" method="post">
              <fieldset>
                <!--ol 开始-->
                <ol>
                  <li class="listyle_4">
                    <label class="left pt5"><em>*</em>原始密码：</label>
                    <h1 class="cmxformh1"> <span class="cmxformspan">
                      <input id="oldPass"  type="password" class="bgw" name="oldPass">
                    </span></h1>
                  <li class="listyle_4">
                    <label class="left pt5"><em>*</em>新密码：</label>
                    <h1 class="cmxformh1"> <span class="cmxformspan">
                      <input id="newPass1"  type="password" class="bgw" name="newPass1">
                    <em>密码长度6~16位，首字符为英文，必须包含数字。</em></span></h1>
                  </li>
                  
                  <li class="listyle_4">
                    <label class="left pt5"><em>*</em>请再次输入新密码：</label>
                    <h1 class="cmxformh1"> <span class="cmxformspan">
                      <input id="newPass2"  type="password" class="bgw" name="newPass2">
                    </span></h1>
                  </li>
                </ol>
                <!--ol 结束-->               
              </fieldset>
            </form>
          </div>
      <div class="toolbar" style="text-align:center" >
      	 <a href="#" class="sexybutton" onclick="beforeSubmit();return false"><span><span>保存</span></span></a>
         <a href="#" class="sexybutton" onclick="resetText();return false"><span><span>重置</span></span></a>
      </div>
        <!--框内内容 结束-->
            <!--按钮 开始--> 
            
             

      
            <!--按钮 结束-->  
            
  
    	<!--主体 结束-->
</div>
<iframe name="hideframe" id="hideframe" width="0" height="0"></iframe>
</body>
</html>
