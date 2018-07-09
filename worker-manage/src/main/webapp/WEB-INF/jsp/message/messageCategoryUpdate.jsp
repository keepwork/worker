<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib uri="/WEB-INF/tld/domain.tld" prefix="domain"%>
<%@ taglib uri="/WEB-INF/tld/messageCategory.tld" prefix="msg" %>
<%@ include file="../common/commonHeader.jsp"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3c.org/TR/1999/REC-html401-19991224/loose.dtd">
<!-- saved from url=(0070)http://192.168.2.36:8011/cms/info/index.do -->
<HTML xmlns="http://www.w3.org/1999/xhtml">
<HEAD>
<TITLE>后台管理系统</TITLE>
<meta http-equiv="content-type" content="text/html; charset=UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=EmulateIE7" />
<link href="${pageContext.request.contextPath}/sys/css/public.css" rel="stylesheet" type="text/css" />
<link href="${pageContext.request.contextPath}/sys/css/style.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="${pageContext.request.contextPath}/js/mootools-release-1.11.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/strCheck.js" charset="gbk"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/common/js/categoryTag.js"></script>
<meta name=generator content="MSHTML 8.00.6001.18939">
<script type="text/javascript">
<!--
	function beforeSubmit()
	{
		var str= new Array(new Array(),new Array());
		str[0]=['name','分类名称',60,1,1];
		str[1]=['descr','描述',500];
		var bool=checkStr(str);
		if(!bool){
			return false;
		}
		document.frmApply.submit();
	}
//-->
</script>
<meta name=generator content="MSHTML 8.00.6001.18939">
<body class="overfwidth">
<div id="mainDiv">
<div class="barnavtop">您所在的位置：消息管理 > 消息分类管理 > 编辑消息分类</div>
	<!--主体 开始-->
	<form name="frmApply" class="cmxform"
			action="${pageContext.request.contextPath}/pub/messageCate/edit.do"
			target="hideframe" method="post">
    <div id="container">
    	<!--按钮 开始-->  

        <!--按钮 结束-->  
        <!--框内内容 开始-->
          <div class="editspace">
              <fieldset>
                <!--ol 开始-->
                <ol>
                	<li class="listyle_4">
                    <label class="left pt5">上级分类：</label>
                    <h1 class="cmxformh1"> <span class="cmxformspan">
                      <msg:message id="parentCode" value="${m.parentCode}"/>
                    </span></h1>
                  </li>
                  <li class="listyle_4">
                    <input name="Button5223" type="reset" id="rbid_" value="重填" style="display: none;"> 
                    <label class="left pt5"><em>*</em>分类名称：</label>
                    <h1 class="cmxformh1"> <span class="cmxformspan">
                      <input type="text" id="name" class="bgw" name="name" value="${m.name}" />
                    </span></h1>
                  </li>
                  <li class="listyle_4">
                    <label class="left pt5">排序：</label>
                    <h1 class="cmxformh1"> <span class="cmxformspan">
                      <input type="text" id="orderNum" name="orderNum"  class="bgw" value="${m.orderNum}"/>
                    </span></h1>
                  </li>
                 <li class="listyle_4 bordernone">
                    <label class="left pt5">分类描述：</label>
                    <textarea name="descr" id="descr" cols="80" rows="3">${m.descr}</textarea>
                  </li>
                </ol>
                <!--ol 结束-->               
              </fieldset>
           
          </div>
      	<div class="toolbar mb10">
        	<a href="#" class="sexybutton" onclick="beforeSubmit();return false"><span><span>保存</span></span></a>    
        	<a href="#" class="sexybutton"  onClick="rbutton()"><span><span>重置 </span></span></a>
        	<a href="#" class="sexybutton" onclick="location.href='${pageContext.request.contextPath}/pub/messageCate/queryList.do'"><span><span>返回</span></span></a> 
        </div>
		</div>
	</form>
</div>
</body>
</html>
<iframe name="hideframe" id="hideframe" width="0" height="0"></iframe>
