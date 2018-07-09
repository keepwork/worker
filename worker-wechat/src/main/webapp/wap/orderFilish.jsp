<%@ page language="java" pageEncoding="UTF-8" isELIgnored="false"%>
<%@ taglib prefix="c" uri="/WEB-INF/tld/c.tld" %>
<c:set var="ctx" value="${pageContext.request.contextPath}" />

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
  	<title>
  		<c:if test="${sessionScope.brandId==1 || sessionScope.brandId==3}">大锅灶连锁</c:if>
        <c:if test="${sessionScope.brandId==2}">小公海连锁</c:if>
  	</title>
	<meta name="viewport" content="width=device-width,height=device-height,inital-scale=1.0,maximum-scale=1.0,user-scalable=no;">
	<meta name="apple-mobile-web-app-capable" content="yes">
	<meta name="apple-mobile-web-app-status-bar-style" content="black">
	<meta name="format-detection" content="telephone=no">
	<link href="${ctx}/wap/css/cate.css" rel="stylesheet" type="text/css" />
	<link href="${ctx}/wap/css/iscroll.css" rel="stylesheet" type="text/css" />
</head>

<body>
<div class="top">
    <ul>
        <li class="logo" style="width: 100%;">订单完成</li>
        <!-- 
        <li class="more"><a href="#"><img src="${ctx}/wap/images/more.png" alt="" width="40" height="50" /></a></li>
    	-->
    </ul>
</div>

<!-- 
<div class="ddwco">
	&nbsp;
</div> -->

<table style="margin-top: 100px;"><tr>
<td align="right" style="width: 20%;">
	<img src="${ctx}/wap/images/icon_suc.png" />
</td>
<td align="left" style="width: 70%;">
	<div class="ddwct" align="left" style="margin-left: 0px;">
		<font size="4" color="green"><strong>
		<c:if test="${requestScope.orderType == '1'}">恭喜，下单成功！</c:if>
        <c:if test="${requestScope.orderType == '2'}">恭喜，预定成功！</c:if>
        </strong></font> 
	</div>
	<div class="ddwct" style="margin-left: 0px;">
	    <b>订单编号：</b>${requestScope.orderSn}<br />
	    <c:if test="${requestScope.orderType == '1'}">
	    	<b>支付方式：</b>现金支付<br />
	    	<b>下单时间：</b>${requestScope.orderTime}<br />
		</c:if>
		<br /> 
	    <span> 
	    	<a href="weixin.htm?method=myOrderList" style="color:#1369c0" >查看我的订单</a>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
			<a href="weixin.htm?method=storeList" style="color:#ff6600">继续订餐>></a>
	    </span>
	</div>
</td>
</tr></table>


	

	<!-- 公用底部 -->
	<jsp:include page="foot.jsp"></jsp:include>
	
</body>
</html>