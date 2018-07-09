<%@ page language="java" pageEncoding="UTF-8" isELIgnored="false"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="/WEB-INF/tld/c.tld" %>
<c:set var="ctx" value="${pageContext.request.contextPath}" />

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
  	<title>订单列表</title>
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
	        <li class="back"><a href="javascript:window.history.back(-1);"><img src="${ctx}/wap/images/back.png" width="40" height="50" /></a></li>
	        <li class="logo">测试我的订单</li>
	    </ul>
	</div>
	
	<ul class="wdddo">
	    <li class="tx" id="headimgurl">
	    	<img src="${sessionScope.wxmenber.headimgurl}" width="80" height="80" />
	    </li>
	    <li class="xm" id="loginName">
	    	<!-- 陈昕 <br /> <span>ID:jhk879</span> -->
	    	${sessionScope.wxmenber.loginName}
	    </li>
	    <!-- <li class="jf">本店积分:1000<br /><input type="submit" name="button2" id="button2" value="每日签到" /></li> -->
	</ul>
	
	<ul class="wdddt">
	    <li>
	    	<a href="#" onclick="initData(0,'9')">
	    		<img src="${ctx}/wap/images/qb.png" width="22" height="22" /><br />全部
	    	</a> 
	    </li>
	    <li style="margin-left: 15px;">
	    	<a href="#" onclick="initData(0,'2')">
	    		<img src="${ctx}/wap/images/dfk.png" width="22" height="22" /><br />待付款
	    	</a>
	    </li>
	    <li style="margin-left: 15px;"> 
	    	<a href="#" onclick="initData(0,'3')">
	    		<img src="${ctx}/wap/images/dps.png" width="22" height="22" /><br />待派送
	    	</a>
	    </li>
	    <li style="margin-left: 15px;">
	    	<a href="#" onclick="initData(0,'6')">
	    		<img src="${ctx}/wap/images/psz.png" width="22" height="22" /><br />待收货
	    	</a>
	    </li>
	    <li style="margin-left: 15px;">
	    	<a href="#" onclick="initData(0,'5')">
	    		<img src="${ctx}/wap/images/ypj.png" width="22" height="22" /><br />已完成
	    	</a>
	    </li>
	</ul>
 
	<ul class="wdddf">
	    <font size="2">
	    <li class="spmc" style="width: 29%;">订单编号</li>
	    <li class="sl" style="width: 27%;">下单时间</li>
	    <li class="dj" style="width: 21%;">订单状态</li>
	    <li class="bz" style="width: 22%;">操作</li>
	    </font>
	</ul>
	<ul class="wdddr" id="dataTable">
	    <li>
	        <div class='spmc' style='width: 28%;'>"123456</div>
	        <div class='sl' style='text-align: right;width:27%;'>2015-09-12 22:10:12</div>
	        <div class='dj' style='text-align: center;width:20%;margin-left:5px;'><font color='green'>待付款</font> </div>
	        <div class='bz' style='text-align: center;width:22%;margin-left:2px;'>
	        	<a style="cursor:pointer;text-decoration: underline;" 
	        	onclick="javascript:window.location.href='http://www.xiaogongh.com/wxtopay.do?orderId=308'" >支付</div>
	    </li>
	</ul> 

	<table height="150px;"><tr><td >&nbsp;</td></tr></table>
	
	<!-- 公用底部 -->
	<jsp:include page="../foot.jsp"></jsp:include>
	
</body>
</html>