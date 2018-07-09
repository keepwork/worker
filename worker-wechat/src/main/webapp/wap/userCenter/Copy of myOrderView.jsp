<%@ page language="java" pageEncoding="UTF-8" isELIgnored="false"%>
<%@ taglib prefix="c" uri="/WEB-INF/tld/c.tld" %>
<%@ taglib prefix="fn" uri="/WEB-INF/tld/fn.tld" %>
<c:set var="ctx" value="${pageContext.request.contextPath}" />

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<title>惠达</title>
	<meta name="viewport" content="width=device-width,height=device-height,inital-scale=1.0,maximum-scale=1.0,user-scalable=no;">
	<meta name="apple-mobile-web-app-capable" content="yes">
	<meta name="apple-mobile-web-app-status-bar-style" content="black">
	<meta name="format-detection" content="telephone=no">
	
	<!-- 公用JS|CSS-->
	<link rel="stylesheet" href="${ctx}/wap/css/style.css">
	<link rel="stylesheet" href="${ctx}/wap/userCenter/css/style.css">
</head>

<body>
	<!-- 公用头部-->
	<jsp:include page="head.jsp"></jsp:include>

<!-- 
<div class="ddwco">您提交的订单已生成！</div> -->


<div class="ddwch">
    <h4>订单详情</h4>
    <div class="ddwct">
	    <b>订单编号：</b>${requestScope.order.orderSn}<br />
	    <b>下单时间：</b>${requestScope.order.orderTimeStr}<br />
	    
        <b>服务类型：</b><c:if test="${requestScope.order.serviceType == '1'}">预约安装</c:if>
        <c:if test="${requestScope.order.serviceType == '2'}">预约维修</c:if>
        <c:if test="${requestScope.order.serviceType == '3'}">预约保养</c:if>
        <c:if test="${requestScope.order.serviceType == '4'}">预约测量</c:if>
        <c:if test="${requestScope.order.serviceType == '5'}">预约咨询</c:if>
        <br />
        <b>产品分类：</b>${requestScope.firstCateName}<br />
        <b>产品类型：</b>${requestScope.secondCateName}<br />
        
        <!-- 
        <c:if test="${requestScope.order.orderType != '2'}">
	    <b>支付方式：</b>
	    <c:if test="${requestScope.order.payType == '1'}">余额支付</c:if>
        <c:if test="${requestScope.order.payType == '2'}">积分支付</c:if>
        <c:if test="${requestScope.order.payType == '3'}">微信支付</c:if>
        </c:if>
        <br />
        
        <c:if test="${requestScope.order.orderType != '2'}">
	    <b>支付状态：</b>
	    <c:if test="${requestScope.order.payStatus == '0'}">未支付</c:if>
        <c:if test="${requestScope.order.payStatus == '1'}">已支付</c:if>
        </c:if>
         -->
         
	</div>
	
    <h4>地址信息</h4>
    <div class="ddwch_c">
        <b>地址：</b>${requestScope.address.province}${requestScope.address.city}${requestScope.address.county}${requestScope.address.street}<br />
        <b>姓名：</b>${requestScope.address.consignee}<br />
        <b>电话：</b>${requestScope.address.mobile}<br />
    </div>
    
</div>


<!-- 
<ul class="ddwcf">
    <li class="spmc">商品名称</li>
    <li class="sl" style="width: 11%;">数量</li>
    <li class="dj" style="width: 28%;">单价</li>
    <li class="bz" style="width: 15%;">总价</li>
</ul>

<ul class="ddwcr">
    <c:forEach var="item" items="${requestScope.itemList}">
		<li>
	        <div class="spmc">
	        	<a href="${ctx}/pub/good/goodDetail.do?type=wap&goodId=${item.goodId}">
	        	<c:choose>
					<c:when test="${fn:length(item.goodName) gt 8}">${fn:substring(item.goodName,0,8)}..</c:when>
					<c:otherwise>${item.goodName}</c:otherwise>
				</c:choose> 
				</a>
			</div>
	        <div class="sl" style="width: 11%;">${item.amount}</div>
	        <div class="dj" style="width: 28%;">
	        	<c:if test="${requestScope.order.payType == '2'}">
	        	${item.point/item.amount}
	        	</c:if>
	        	<c:if test="${requestScope.order.payType == '3'}">
	        	￥${item.price/item.amount}
	        	</c:if>
	        </div>
	        <div class="bz" style="width: 15%;">
	        	<c:if test="${requestScope.order.payType == '2'}">
	        	${item.point}
	        	</c:if>
	        	<c:if test="${requestScope.order.payType == '3'}">
	        	￥${item.price}
	        	</c:if>
	        </div>
	    </li>	
	</c:forEach>
</ul>

<ul class="ddwcs">
    <li class="fr" style="width: 100%;">
	    订单总计：
    </li>
</ul> 
 -->
 
	<ul class="round">
			<li class="title">
				<a href="${ctx}/pub/order/myOrderList.do?type=wap" >返回</a>
				<font size="2" color="red">&nbsp;</font>
			</li>
	</ul>
	
	
	<table height="150px;"><tr><td >&nbsp;</td></tr></table>
	
	<!-- foot -->
	<jsp:include page="../public/foot.jsp"></jsp:include>
	
</body>
</html>