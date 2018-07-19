<%@ page language="java" pageEncoding="UTF-8" isELIgnored="false"%>
<%@ taglib prefix="c" uri="/WEB-INF/tld/c.tld" %>
<c:set var="ctx" value="${pageContext.request.contextPath}" />

<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta content="width=device-width, initial-scale=1.0, minimum-scale=1.0, maximum-scale=1.0, user-scalable=no" name="viewport">
<meta content="yes" name="apple-mobile-web-app-capable" />
<meta content="black" name="apple-mobile-web-app-status-bar-style" />
<meta content="telephone=no" name="format-detection" />
<title>订单详情</title>
<link rel="stylesheet" type="text/css" href="${ctx }/wap/workerCenter/css/base.css">
<link rel="stylesheet" type="text/css" href="${ctx }/wap/workerCenter/css/public.css">
<script type="text/javascript" src="${ctx }/wap/workerCenter/js/jquery.min.js" ></script>
<script src="${ctx }/wap/workerCenter/js/common.js"></script>
</head>

<body>
<header class="header" id="header">
<a href="javascript:history.go(-1)" target=_self class="back">返回</a>
<h1>订单详情</h1>
</header>
<!--header-end-->
<div class="container" id="container"> 
<div class="order-schedule-img"><img src="${ctx }/wap/workerCenter/images/1.png"></div>
<div class="order-confirm">
  <div class="order-num"><span class="fl">订单号：<em>${requestScope.order.orderSn}</em></span></div>
  <ul class="order-confirm-list clearfix order-cancel-list">
    <li><p>订单详情</p></li>
    <li><h2>客户姓名</h2><span>${requestScope.address.consignee}</span></li>
    <li><h2>联系电话</h2><span>${requestScope.address.mobile}</span></li>
    <li><h2>服务地址</h2><span>${requestScope.address.province}${requestScope.address.city}${requestScope.address.county}${requestScope.address.street}</span></li>
    <li><h2>服务类型</h2>
      <span>
        <c:if test="${requestScope.order.serviceType == '1'}">安装</c:if>
        <c:if test="${requestScope.order.serviceType == '2'}">维修</c:if>
        <c:if test="${requestScope.order.serviceType == '3'}">保养</c:if>
        <c:if test="${requestScope.order.serviceType == '4'}">测量</c:if>
        <c:if test="${requestScope.order.serviceType == '5'}">咨询</c:if>
      </span>
    </li>
    <li><h2>产品类型</h2><span>${requestScope.firstCateName}</span></li>
    <li><h2>服务时间</h2>
      <span>
        <c:if test="${requestScope.order.orderStatus == '1'}">师傅跟我确认</c:if>
        <c:if test="${requestScope.order.orderStatus == '2'}">师傅跟我确认</c:if>
        <c:if test="${requestScope.order.orderStatus == '3'}">${requestScope.order.sureTimeStr}</c:if>
        <c:if test="${requestScope.order.orderStatus == '4'}">${requestScope.order.sureTimeStr}</c:if>
        <c:if test="${requestScope.order.orderStatus == '5'}">${requestScope.order.sureTimeStr}</c:if>
        <c:if test="${requestScope.order.orderStatus == '6'}">${requestScope.order.sureTimeStr}</c:if>
      </span>
    </li>
    <li><h2>备注信息</h2><span>${requestScope.order.orderDesc}</span></li>
	  <li><h2>服务费用</h2><p><em>￥${requestScope.order.totalPrice}</em></p></li>
  </ul>
  <div style="margin-bottom: 0.3rem"></div>
  <ul class="order-confirm-list clearfix order-cancel-list">
    <li><p>师傅信息</p></li>
    <li><h2>师傅姓名</h2><span>${requestScope.worker.realName}</span></li>
    <li><h2>接单数量</h2><span>${requestScope.totalOrderNum}</span></li>
    <li><h2>好评指数</h2><span>${requestScope.positiveAppraiseRate}%</span></li>
    <li><h2>师傅头像</h2><span><img src="${sessionScope.worker.headimgurl}" style="width: 120px;height: 180px" /></span></li>
  </ul>
</div>
<!--order-confirm-end-->

</div>
<!--container-end-->
</body>
</html>
