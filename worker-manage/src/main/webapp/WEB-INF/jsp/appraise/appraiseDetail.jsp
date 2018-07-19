<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib uri="/WEB-INF/tld/domain.tld" prefix="domain"%>
<%@ taglib uri="/WEB-INF/tld/c.tld" prefix="c"%>
<c:set var="ctx" value="${pageContext.request.contextPath}" />

<%@ include file="../common/commonHeader.jsp"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3c.org/TR/1999/REC-html401-19991224/loose.dtd">
<!-- saved from url=(0070)http://192.168.2.36:8011/cms/info/index.do -->
<HTML xmlns="http://www.w3.org/1999/xhtml">
<HEAD>
<TITLE>后台管理系统</TITLE>
<meta http-equiv="content-type" content="text/html; charset=UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=EmulateIE7" />
<link href="${ctx}/sys/css/public.css" rel="stylesheet" type="text/css">
<link href="${ctx}/sys/css/style.css" rel="stylesheet" type="text/css">
<script type="text/javascript" src="${pageContext.request.contextPath}/js/mootools-release-1.11.js"></script>
<meta name=generator content="MSHTML 8.00.6001.18939">
<script type="text/javascript">
<!--
	function beforeSubmit()
	{
		document.frmApply.submit();
	}
//-->
</script>
<meta name=generator content="MSHTML 8.00.6001.18939">
<body class="overfwidth">
<div id="mainDiv">
<div class="barnavtop">您所在的位置： 订单中心 > 评价详情</div>
	<!--主体 开始-->
	<form name="frmApply" class="cmxform"
			action="${pageContext.request.contextPath}/pub/complaint/edit.do"
			target="hideframe" method="post">
    <div id="container">
    	<!--按钮 开始-->  

        <!--按钮 结束-->  
        <!--框内内容 开始-->
          <div class="editspace">
              <fieldset>
                <!--ol 开始-->
                <ol>
                <li class="listyle_4 bordernone">
                    <label class="left pt5">服务质量：</label>
                    <span style="line-height: 30px">${m.scoreZhiLiang}</span>
                    <br>
                    <label class="left pt5">服务态度：</label>
                    <span style="line-height: 30px">${m.scoreTaiDu}</span>
                    <br>
                    <label class="left pt5">准时情况：</label>
                    <span style="line-height: 30px">${m.scoreZhunShi}</span>
                </li>
                 <li class="listyle_4 bordernone">
                    <label class="left pt5">评价内容：</label>
                    <textarea name="content" id="content" cols="80" rows="3" readonly="readonly">${m.content}</textarea>
                 </li>
                <li class="listyle_4 bordernone">
                    <label class="left pt5">是否隐藏：</label>
                    <c:if test="${m.isShow == 1}">
                        <span style="line-height: 30px">否</span>
                    </c:if>
                    <c:if test="${m.isShow == '0'}">
                        <span style="line-height: 30px">是</span>
                    </c:if>
                </li>
                 <%--<li class="listyle_4 bordernone">--%>
                    <%--<label class="left pt5">回复内容：</label>--%>
                    <%--<textarea name="feedback" id="feedback" cols="80" rows="3">${m.feedback}</textarea>--%>
                  <%--</li>--%>
                </ol>
                <!--ol 结束-->               
              </fieldset>
           
          </div>
      	<div class="toolbar mb10">
            <c:if test="${m.isShow == 1}">
        	    <a href="#" class="sexybutton" onclick="location.href='${pageContext.request.contextPath}/pub/appraise/appraiseHidden.do'"><span><span>隐藏</span></span></a>
            </c:if>
        	<%--<a href="#" class="sexybutton"  onClick="rbutton()"><span><span>重置 </span></span></a>--%>
        	<a href="#" class="sexybutton" onclick="location.href='${pageContext.request.contextPath}/pub/order/queryList.do'"><span><span>返回</span></span></a>
        </div>
		</div>
	</form>
</div>
</body>
</html>
<iframe name="hideframe" id="hideframe" width="0" height="0"></iframe>
