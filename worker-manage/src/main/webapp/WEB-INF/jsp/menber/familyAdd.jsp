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
		<meta name=generator content="MSHTML 8.00.6001.18939">
		<link href="${ctx}/sys/css/public.css" rel="stylesheet" type="text/css">
		<link href="${ctx}/sys/css/style.css" rel="stylesheet" type="text/css">
		<script type="text/javascript" src="${ctx}/js/mootools-release-1.11.js"></script>
		<script type="text/javascript" src="${ctx}/js/strCheck.js" charset="gbk"></script>
		
		<script type="text/javascript" src="${ctx}/common/js/jquery-1.4.4.min.js" ></script>
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
			//var str= new Array(new Array(),new Array());
			//str[0]=['realName','',10,1,1,1,1];
			//str[1]=['pid','',18,1,1,1,1];
			//var bool=checkStr(str);
			//if(!bool){
			//	return false;
			//}
			
			var realName = document.getElementById('realName');
	    	if(realName.value == ''){
	    		alert("被托管账户姓名不能为空");
	    		realName.focus();
	    		return;
	    	}
	    	var pid = document.getElementById('pid');
	    	if(pid.value == ''){
	    		alert("被托管账户身份证不能为空");
	    		pid.focus();
	    		return;
	    	}
			
			if(!isCardNo(pid.value)){
				return false;
			}
			
			//检查身份证是否存在
			$.ajax({
				 type:"post",
				 dataType:"html",
				 url:"${ctx}/pc/checkLoginName.do",
				 data:"pid="+pid.value,
				 async:false,
				 complete: function (XMLHttpRequest,textStatus) {
				    if(XMLHttpRequest.responseText!=""){
	                    if(XMLHttpRequest.responseText==2){
					 		alert("该身份证已经存在！");
					 		pid.focus();
					 		return;
					 	}else{
				    		document.frmApply.submit();
				    	}
	                }
				 }
			});
			
		}
		</script>
<body class="overfwidth">
<div id="mainDiv">
<div class="barnavtop">您所在的位置：托管账户管理 > 新增托管账户</div>
	<!--主体 开始-->
    <div id="container">
    	<!--按钮 开始-->  
 		<div class="toolbar">
        	<a href="#" class="sexybutton" onclick="beforeSubmit();return false"><span><span>保存</span></span></a>    
			<a href="#" class="sexybutton"  onClick="rbutton()"><span><span>重置 </span></span></a>
        	<a href="${ctx}/pub/family/queryList.do?familyMenId=${familyMenId}" class="sexybutton" ><span><span>返回</span></span></a> 
        </div>
        <!--按钮 结束-->
        <!--框内内容 开始-->
          <div class="editspace">
             <form name="frmApply" class="cmxform" action="${ctx}/pub/family/add.do"
							target="hideframe" method="post" onsubmit="return beforeSubmit(this);">
			 <input type="hidden" name="familyMenId" id="familyMenId" value="${familyMenId}" />
              <fieldset>
                <!--ol 开始-->
                <ol>
                  <li class="listyle_4">
                    <label class="left pt5"><em>*</em>被托管账户姓名：</label>
                    <h1 class="cmxformh1"> <span class="cmxformspan">
                      <input type="text" id="realName" name="realName"  class="bgw" />
                    </span></h1>
                  </li>
                  <li class="listyle_4">
                    <label class="left pt5"><em>*</em>被托管账户身份证：</label>
                    <h1 class="cmxformh1"> <span class="cmxformspan">
                      <input type="text" id="pid" name="pid"  class="bgw" />
                    </span></h1>
                  </li>
                </ol>
                <!--ol 结束-->               
              </fieldset>
            </form>
          </div>
      <div class="toolbar">
        <a href="#" class="sexybutton" onclick="beforeSubmit();return false"><span><span>保存</span></span></a>    
        <a href="#" class="sexybutton"  onClick="rbutton()"><span><span>重置 </span></span></a>
        <a href="${ctx}/pub/family/queryList.do" class="sexybutton"><span><span>返回</span></span></a>
      </div>
</div>
</div>

	<iframe name="hideframe" id="hideframe" width="0" height="0"></iframe>
</body>
</html>
