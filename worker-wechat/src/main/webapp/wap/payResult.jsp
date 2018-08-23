<%@ page language="java" pageEncoding="UTF-8" isELIgnored="false"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="/WEB-INF/tld/c.tld" %>
<c:set var="ctx" value="${pageContext.request.contextPath}" />

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
  	<title>响应页面</title>
	<meta name="viewport" content="width=device-width,height=device-height,inital-scale=1.0,maximum-scale=1.0,user-scalable=no;">
	<meta name="apple-mobile-web-app-capable" content="yes">
	<meta name="apple-mobile-web-app-status-bar-style" content="black">
	<meta name="format-detection" content="telephone=no">
	<link href="${ctx}/wap/css/cate.css" rel="stylesheet" type="text/css" />
	<link href="${ctx}/wap/css/iscroll.css" rel="stylesheet" type="text/css" />

<style type="text/css">
body table tr td {
	font-size: 14px;
	word-wrap: break-word;
	word-break: break-all;
	empty-cells: show;
}
</style>
</head>
<body>
	<div class="top">
	    <ul>
	        <li class="back"></li>
	        <li class="logo">
	        <img src="${ctx}${sessionScope.brand.pic}" alt="" width="114" height="50" />
	    </ul>
	</div>
	
	<div class="abouto">
		<br><br>
		<c:if test="${result=='0'}">
	        <p align="left" style="margin-left: 30px;"><font size="6" color="red"><strong>支付失败</strong></font></p>
	    	<br>
	    	<p align="left" style="margin-left: 30px;"><font size="2" color="red">请到我的订单再次支付或联系我们!</font></p>
	    </c:if>
	    <c:if test="${result=='1'}">
	        <p align="left" style="margin-left: 30px;"><font size="6" color="green"><strong>支付成功</strong></font></p>
	        <br>
	    </c:if>
		<p align="left" style="margin-left: 30px;">
			<c:if test="${orderSn!=null}">
				<font size="2">订单编号：${orderSn}</font><br>
			</c:if>
			<c:if test="${total!=null}">
				<font size="2">订单金额：${total}元</font><br>
			</c:if>
			<c:if test="${payWay!=null}">
				<font size="2">支付方式：${payWay}</font><br>
			</c:if>
			<a href="weixin.htm?method=storeList"><font size="2" color="orange">继续预约</font></a>
			&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
			<a href="http://www.zhixiu.xyz/weixin.htm?method=myOrderList&type=9"><font size="2" color="green">查看我的订单>></font></a>
		</p>
	</div>
	
	<table height="150px;"><tr><td >&nbsp;</td></tr></table>
	
	<!-- 公用底部 -->
	<jsp:include page="public/foot.jsp" flush="false">
		<jsp:param name="menu" value="fw" />
	</jsp:include>
</body>
</html>