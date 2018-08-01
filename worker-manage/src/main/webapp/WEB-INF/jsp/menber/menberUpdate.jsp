<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib uri="/WEB-INF/tld/domain.tld" prefix="domain"%>
<%@ taglib uri="/WEB-INF/tld/c.tld" prefix="c"%>
<c:set var="ctx" value="${pageContext.request.contextPath}" />

<%@ include file="../common/commonHeader.jsp"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3c.org/TR/1999/REC-html401-19991224/loose.dtd">
<HTML xmlns="http://www.w3.org/1999/xhtml">
<HEAD>
		<TITLE>后台管理系统</TITLE>
		<meta http-equiv="content-type" content="text/html; charset=UTF-8">
		<meta http-equiv="X-UA-Compatible" content="IE=EmulateIE7" />
		<link href="${ctx}/sys/css/public.css" rel="stylesheet" type="text/css">
		<link href="${ctx}/sys/css/style.css" rel="stylesheet" type="text/css">
		<script type="text/javascript" src="${ctx}/js/mootools-release-1.11.js"></script>
		<script type="text/javascript" src="${ctx}/js/passwordRule.js" charset="GBK"></script>
		<script type="text/javascript" src="${ctx}/js/strCheck.js" charset="gbk"></script>
		<meta name=generator content="MSHTML 8.00.6001.18939">
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
		function beforeSubmit()
		{
			var str= new Array(new Array(),new Array());
			//str[0]=['loginName','登录名',50,1,1,1,1];
			str[0]=['mobile','手机',50];
			var bool=checkStr(str);
			if(!bool){
				return false;
			}
			
			
			//密码验证
			var pwd1=document.getElementById("password").value;
			var pwd2=document.getElementById("userLoginPassword_").value;
			if(pwd1!=pwd2 && pwd1!='A000111222333'){
				if(!loginPwd("loginName","userLoginPassword_")){
					return false;
				}
			}
		
			var email = document.getElementById("email");
			if(email.value!=''&&!/^[a-zA-Z0-9_+.-]+\@([a-zA-Z0-9-]+\.)+[a-zA-Z0-9]{2,4}$/g.test(email.value)){
				alert("邮箱地址格式不合法");
				return false;
			}	
			
			var mobile = document.getElementById("mobile");
			if(mobile.value!=''&&!/^1\d{10}$/g.test(mobile.value)){
				alert("手机号码格式不合法");
				return false;
			}
			
			var pid = document.getElementById("pid").value;
			if(!isCardNo(pid)){
				return false;
			}
			
			document.frmApply.submit();
		}

        function selLocation()
        {
            showSelDiv("locationDiv",false)
        }
        function showSelDiv(n,showMain)
        {
            document.getElementById("mainDiv").style.display = showMain?"block":"none";
            document.getElementById(n).style.display = showMain?"none":"block";
        }
        function selLocationSuc(id,name)
        {
            document.getElementById("locationName_").value = name;
            document.getElementById("locationid_").value = id;
            showSelDiv('locationDiv',true);
        }

		</script>
<meta name=generator content="MSHTML 8.00.6001.18939">
<body class="overfwidth">
<div id="mainDiv">
<div class="barnavtop">您所在的位置： 会员管理 > 会员修改</div>
	<!--主体 开始-->
	<form name="frmApply" class="cmxform" action="${pageContext.request.contextPath}/pub/menber/edit.do"
			target="hideframe" method="post">
	<!--<input type="hidden" name="id" id="id" value="${m.id}" />-->
	<input type="hidden" id="password" value="${m.password}" />
    <div id="container">
    	<!--按钮 开始-->  

        <!--按钮 结束-->  
        <!--框内内容 开始-->
          <div class="editspace">
              <fieldset>
                <!--ol 开始-->
                <ol>
                  <li class="listyle_4">
                    <label class="left pt5"><em>*</em>手机：</label>
                    <h1 class="cmxformh1"> <span class="cmxformspan">
                      <input type="text" id="mobile" name="mobile"  class="bgw" value="${m.mobile}"
                      			onkeyup='this.value=this.value.replace(/[^0-9]/gi,""); '
								onafterpaste='this.value=this.value.replace(/[^0-9]/gi,"")'>
                    </span></h1>
                  </li>
                  <li class="listyle_4">
                    <label class="left pt5"><em>*</em>身份证号：</label>
                    <h1 class="cmxformh1"> <span class="cmxformspan">
                      <input type="text" id="pid" name="pid"  class="bgw" value="${m.pid}"/>
                    </span></h1>
                  </li>
                  <li class="listyle_4">
                    <label class="left pt5"><em>*</em>密码：</label>
                    <h1 class="cmxformh1"> <span class="cmxformspan">
                      <input type="password" id="userLoginPassword_" name="password" value="A000111222333" class="bgw" name="input">
                    <em>密码长度6~16位，首字符为英文，必须包含数字。</em></span></h1>
                  </li>
                  <li class="listyle_4">
                    <label class="left pt5">微信openid：</label>
                    <h1 class="cmxformh1"> <span class="cmxformspan">
                    	${m.openId}
                      <!-- <input type="text" id="openId" name="openId"  class="bgw" value="${m.openId}"/>
                    <em>不要轻易修改，只有在合并账户的时候才有可能改动。</em> -->
                    </span></h1>
                  </li>
                  <li class="listyle_4">
                    <label class="left pt5">微信名：</label>
                    <h1 class="cmxformh1"> <span class="cmxformspan">
                      <input type="text" id="loginName" name="loginName"  class="bgw" value="${m.loginName}"/>
                    </span></h1>
                  </li>
                  <li class="listyle_4">
                    <label class="left pt5"><em>*</em>会员类型：</label>
                    <h1 class="cmxformh1"> <span class="cmxformspan" style="width: 300px;">
                      <domain:radioDomain domain="menberType" name="type" uid="type" value="${m.type}" />
                    </span></h1>
                  </li>
                    <li class="listyle_4">
                        <label class="mt5 left"><em>*</em>所属部门：</label>
                        <h1 class="cmxformh1"> <span class="cmxformspan">
                        <input type="text" id="locationName_" class="bgw" disabled="disabled" readonly="readonly" value="${m.tbTBmsLocationDTO.name}" />
					    <input id="locationid_" type="hidden" name="locationid" value="${m.tbTBmsLocationDTO.id}" />
                        <a href="javascript:selLocation();"><img align="absmiddle" class="hand" src="${pageContext.request.contextPath}/sys/images/2j13.gif" /></a>
                        </span></h1>
                    </li>
                  <li class="listyle_4">
                    <label class="left pt5"><em>*</em>状态：</label>
                    <h1 class="cmxformh1"> <span class="cmxformspan" style="width: 200px;">
                      <domain:radioDomain domain="dataIsused" name="status" uid="status" value="${m.status}" />
                    </span></h1>
                  </li>
                  <li class="listyle_4">
                    <label class="left pt5">性别：</label>
                    <h1 class="cmxformh1"> <span class="cmxformspan" style="width: 200px;">
                      <domain:radioDomain domain="SEX" name="sex" uid="sex" value="${m.sex}" />
                    </span></h1>
                  </li>
                  <li class="listyle_4">
                    <label class="left pt5">真实姓名：</label>
                    <h1 class="cmxformh1"> <span class="cmxformspan">
                      <input type="text" id="realName" name="realName"  class="bgw" value="${m.realName}" />
                    </span></h1>
                  </li>
                  <li class="listyle_4">
                    <label class="left pt5">电子邮箱：</label>
                    <h1 class="cmxformh1"> <span class="cmxformspan">
                      <input type="text" id="email" name="email"  class="bgw" value="${m.email}"/>
                    </span></h1>
                  </li>
                  <li class="listyle_4">
                    <label class="left pt5">邮编：</label>
                    <h1 class="cmxformh1"> <span class="cmxformspan">
                      <input type="text" id="postCode" name="postCode"  class="bgw" value="${m.postCode}"/>
                    </span></h1>
                  </li>
                    <li class="listyle_4">
                        <label class="left pt5">注册时间：</label>
                        <h1 class="cmxformh1"> <span class="cmxformspan">
                    	${m.regTimeStr}
                    </span></h1>
                    </li>
                  <li class="listyle_4">
                    <label class="left pt5">详细地址：</label>
                    <h1 class="cmxformh1"> <span class="cmxformspan">
                      <textarea id="detailAddr" name="detailAddr" cols="50" rows="2">${m.detailAddr}</textarea>
                    </span></h1>
                  </li>
                </ol>
                <!--ol 结束-->               
              </fieldset>
           
          </div>
      	<div class="toolbar mb10">
        	<a href="#" class="sexybutton" onclick="beforeSubmit();return false"><span><span>保存</span></span></a>    
        	<a href="#" class="sexybutton"  onClick="rbutton()"><span><span>重置 </span></span></a>
        	<a href="#" class="sexybutton" onclick="location.href='${pageContext.request.contextPath}/pub/menber/queryList.do?type=${m.type}'"><span><span>返回</span></span></a>
        </div>
		</div>
	</form>
</div>

<div id="locationDiv" style="display: none; text-align: center;">
    <iframe src="${pageContext.request.contextPath}/sys/bmscommon/selLocation.do" width="100%" height="100%" frameborder="0" scrolling="yes"></iframe>
</div>

</body>
</html>
<iframe name="hideframe" id="hideframe" width="0" height="0"></iframe>
