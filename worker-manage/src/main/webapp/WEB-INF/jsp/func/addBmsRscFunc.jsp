<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib uri="/WEB-INF/tld/domain.tld" prefix="domain"%>
<%@ taglib uri="/WEB-INF/tld/view.tld" prefix="view"%>
<%@ include file="../common/commonHeader.jsp"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3c.org/TR/1999/REC-html401-19991224/loose.dtd">
<!-- saved from url=(0070)http://192.168.2.36:8011/cms/info/index.do -->
<HTML xmlns="http://www.w3.org/1999/xhtml">
<HEAD>
<TITLE>后台管理系统</TITLE>
<meta http-equiv="content-type" content="text/html; charset=UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=EmulateIE7" />
<link href="${pageContext.request.contextPath}/sys/css/public.css" rel="stylesheet" type="text/css">
<link href="${pageContext.request.contextPath}/sys/css/style.css" rel="stylesheet" type="text/css">
<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery-1.3.2.min.js"></script>
<meta name=generator content="MSHTML 8.00.6001.18939">
<script type="text/javascript">
<!--
	function beforeSubmit()
	{
		if(document.getElementById("name_").value == "")
		{
			alert("功能名称不能为空");
			return false;
		}
		
		if(document.getElementById("name_").value.split(" ").length>1){
			alert("功能名称不能有空格");
			return false;
		}
		
		if(document.getElementById("icon_").value.length<1){
			alert("显示图标不能为空");
			return false;
		}
		
		if(document.getElementById("menuFunc_").value=="1"){
			if(document.getElementById("sortid_").value.length<1){
				alert("顺序值不能为空");
				return false;
			}
		}
		
		if(document.getElementById("remark_").value.length >400)
		{
			alert("备注不能多余400个字符");
			return false;
		}
		
		document.frmApply.submit();
	}
	
	function $(id)
	{
		return document.getElementById(id);
	}
	
	function isMenuFuncChg(s)
	{
		$("mutResourceDiv").style.display = (s==0)?"block":"none";
		$("linkaddrDeiv").style.display = (s==1)?"block":"none";
		$("linkaddrDeiv2").style.display = (s==1)?"block":"none";
		$("linkaddrDeiv4").style.display = (s==1)?"block":"none";
		$("icon_").value=(s==1)?"/images/1j4.gif":"/images/2j21.gif";
	}
	
	function isAuditDefine(checkBox){
		var divNode=checkBox.parentNode.parentNode.parentNode.nextSibling;

		divNode.style.display =(checkBox.checked)?"block":"none" ;
 		var childs=checkBox.parentNode.childNodes;
 		for(var i=0;i< childs.length;i++){
 			if(childs[i].name=='logPoint'){
 				childs[i].value=checkBox.checked;
 			}
 		}
	}
	
	function rbutton()
	{
	    document.getElementById("rbid_").click();
	}
	
	function addDiv(){
		var a = document.getElementById("resouce_content_div").innerHTML;
		var b = document.getElementById("resource_souce_div").innerHTML;
		document.getElementById("resouce_content_div").innerHTML=a+b;
	}
	
	function clearNoNum(obj) 
	{  
	   var re = /([0-9]{3})[0-9]*/;
	   obj.value = obj.value.replace(re,"$1");
	   obj.value = obj.value.replace(/[^\d]/g,"");  //清除“数字”和“.”以外的字符 
	   obj.value = obj.value.replace(/^0/g,"");  //验证第一个字符是数字而不是0 
	   //obj.value = obj.value.replace(/\.{2,}/g,"."); //只保留第一个. 清除多余的. 
	} 

	
//-->
</script>
</HEAD>

<body class="overfwidth">
<div class="barnavtop">您所在的位置：用户管理子系统 &gt; 功能模块管理&gt; 增加功能模块</div>
<div class="workspace1 left" style="padding:20px;">


<div class="editspace">
  <form name="frmApply" action="${pageContext.request.contextPath}/sys/bmsrscfunc/addRscFunc.do"
					target="hideframe" method="post" class="cmxform">
              <fieldset>
                <!--ol 开始-->
                <ol>
                  <li class="listyle_4">
                    <label class="left pt5"><em>*</em>功能名称：</label>
                    <h1 class="cmxformh1"> <span class="cmxformspan">
                      <input name="name" id="name_" value="${m.name }" type="text" maxlength="30" class="bgw" >
                    </span>
                    </h1> 
                   </li> 
                   
                  <li class="listyle_4">
                    <label class="left pt5"><em>*</em>显示图标：</label>
                    <h1 class="cmxformh1"> <span class="cmxformspan">
                      <input name="icon" id="icon_" value="${empty m.icon?'/images/1j4.gif': m.icon}" type="text" maxlength="30" class="bgw" >
                    </span></h1> 
                  </li>
                  
                   <li class="listyle_4" id="linkaddrDeiv4">
                     <label class="left pt5"><em>*</em>顺序值：</label>
                    <h1 class="cmxformh1"> <span class="cmxformspan">
                      <input name="sortid" id="sortid_" value="" type="text" maxlength="30" class="bgw" onkeyup="clearNoNum(this);" >
                    </span>
                    </h1>  
                   </li> 
                  
                  <li class="listyle_4 bordernone">
                     <label class="left pt5">是否菜单：</label>
                        <span>
                        <domain:selectDomain value="1"
									onchange="isMenuFuncChg(this.value)" domain="YORN"
									name="menuFunc" uid="menuFunc_" />
								&nbsp;
								&nbsp;
								&nbsp;
                        </span>
                  </li>
                  
                  <li class="listyle_4" id="linkaddrDeiv">
                    <label class="left pt5">菜单地址：</label>
                    <h1 class="cmxformh1"> <span class="cmxformspan">
                    <input name="url" id="url_" maxlength="150" value="${m.url }" type="text" 
									class="wid350" size="150" style="width: 400px">
                    </span></h1> 
                   </li> 
                   
                   <li class="listyle_4 bordernone">
                    <label class="left pt5">描述：</label>
                    <textarea name="remark" id="remark_" cols="80" rows="3">${m.remark}</textarea>
                  </li>
                   
                  
                </ol>
                <!--ol 结束-->               
              </fieldset>
              
              
              <table width="100%" cellspacing="0" cellpadding="0" border="0" >
                    <tbody>
						<tr align="center" bgcolor="#f7f7f7">
							<td colspan="4">
							<div class="toolbar">
       							<a href="#" class="sexybutton"
       										onClick="beforeSubmit();return false"><span><span>提交 </span></span></a> 
       						    <a href="#" class="sexybutton"
        									onClick="rbutton()"><span><span>重置 </span></span></a>
        					    <input name="Button5223" type="reset" id="rbid_" value="重填" style="display: none;">
                                <a href="#"   onClick="history.back();"  class="sexybutton"><span><span>返回</span></span></a> 
                             </div>
							</td>
						</tr>
			  </table>
              
               
     </form>
</div>
    
	
	<iframe name="hideframe" id="hideframe" width="0" height="0"></iframe>
</body>
</HTML>