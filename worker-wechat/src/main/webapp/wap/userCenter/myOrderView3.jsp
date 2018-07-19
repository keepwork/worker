<%@ page language="java" pageEncoding="UTF-8" isELIgnored="false"%>
<%@ taglib prefix="c" uri="/WEB-INF/tld/c.tld" %>
<c:set var="ctx" value="${pageContext.request.contextPath}" />

<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1" />
	<meta name="viewport" content="width=device-width, initial-scale=1.0, user-scalable=no"/>
	<title>惠达</title>
	<link rel="stylesheet" href="${ctx}/wap/html/css/Basc.css" />
	<link rel="stylesheet" href="${ctx}/wap/html/css/demo.css" />
	
  	<!-- 公用JS|CSS-->
	<link href="${ctx}/wap/css/cate.css" rel="stylesheet" type="text/css" />
	<link href="${ctx}/wap/css/iscroll.css" rel="stylesheet" type="text/css" />
	<script type="text/javascript" src="${ctx}/common/js/jquery-1.4.4.min.js" ></script>
	
	<style type="text/css" > 
		.qrddxxf ul li{height:35px; line-height:35px; font-size:16px;}
	</style>
	
</head>

<body>
	<!-- 公用头部-->
	<jsp:include page="head.jsp"></jsp:include>
	
	<div class="qrddxxf" style="margin-top: 70px;">
	    <h4 style="height:5px;padding:0px;">&nbsp;</h4>
	    <ul style="padding-bottom: 25px;">
	        <li>地址</li>
	        <li><span style="padding-left: 30px;">${requestScope.address.consignee}</span><span style="padding-left: 100px;">${requestScope.address.mobile}</span></li>
	        <li><span style="padding-left: 30px;">${requestScope.address.province}${requestScope.address.city}${requestScope.address.county}${requestScope.address.street}</span></li>
	    </ul>
	</div>
	
	<div class="qrddxxf">
	    <h4 style="height:5px;padding:0px;">&nbsp;</h4>
	    <ul>
	        <li>描述<span style="float:right">${requestScope.order.orderDesc}</span></li>
	        <hr size=1 style="color: black;border-style:dotted;" width="100%">
	        <li>服务时间
	       		<c:if test="${requestScope.order.orderStatus == '1'}"><span style="float:right">师傅跟我确认</c:if>
	       		<c:if test="${requestScope.order.orderStatus == '2'}"><span style="float:right">师傅跟我确认</c:if>
	       		<c:if test="${requestScope.order.orderStatus == '3'}"><span style="float:right">${requestScope.order.sureTimeStr}</c:if>
	       		<c:if test="${requestScope.order.orderStatus == '4'}"><span style="float:right">${requestScope.order.sureTimeStr}</c:if>
	       		<c:if test="${requestScope.order.orderStatus == '5'}"><span style="float:right">${requestScope.order.sureTimeStr}</c:if>
	       		<c:if test="${requestScope.order.orderStatus == '6'}"><span style="float:right">${requestScope.order.sureTimeStr}</c:if>
	        </span></li>
	        
	        <c:if test="${requestScope.order.orderStatus != '1'}">
	        	<li>服务师傅<span style="float:right">${requestScope.worker.realName}</span></li>
	        	<li><span style="float:right">接单数：100&nbsp;满意度：90%</span></li>
	        	<li><span style="float:right">${requestScope.worker.mobile}</span></li>
	        	<li style="height: 70px;"><span style="float:right;padding-right: 154px;"><img src="${sessionScope.wxmenber.headimgurl}" width="80" height="80" /></span></li>
	        </c:if>
	    </ul>
	</div> 
	
	<div class="qrddxxf">
	    <h4 style="height:5px;padding:0px;">&nbsp;</h4>
	    <ul>
	        <li>预约服务类型<span style="float:right">
	        	<c:if test="${requestScope.order.serviceType == '1'}">安装</c:if>
		        <c:if test="${requestScope.order.serviceType == '2'}">维修</c:if>
		        <c:if test="${requestScope.order.serviceType == '3'}">保养</c:if>
		        <c:if test="${requestScope.order.serviceType == '4'}">测量</c:if>
		        <c:if test="${requestScope.order.serviceType == '5'}">咨询</c:if>
	        </span></li>
	        <li>预约产品类型<span style="float:right">${requestScope.firstCateName}</span></li>
	    </ul>
	</div>
	
	<div class="qrddxxf">
	    <h4 style="height:5px;padding:0px;">&nbsp;</h4>
	    <ul>
			<li>服务费用<span style="float:right">0元<br></span></li>
			<li>&nbsp;<span style="float:right">收费说明</span></li>
			<hr size=1 style="color: black;border-style:dotted;" width="100%">
			<li>支付方式<span style="float:right">微信支付</span></li>
	        <c:if test="${requestScope.order.orderStatus == '4'}"><li>回访结果<span style="float:right">很满意</span></li></c:if>
	    </ul>
	</div>
	
	<div style="background-color: #E3E3E3;">
	    <h4 style="height:20px;">&nbsp;</h4>
	    <span style="padding-left: 37px;">
	    	<c:if test="${requestScope.order.orderStatus == '1'}">
	    	<span ><img src="${ctx}/wap/userCenter/images/u105.png" style="width: 30px;height: 30px;"/></span>
	    	<span style="padding-left: 50px;"><img src="${ctx}/wap/userCenter/images/u71.png" /></span>
	    	<span style="padding-left: 50px;"><img src="${ctx}/wap/userCenter/images/u71.png" /></span>
	    	<span style="padding-left: 50px;"><img src="${ctx}/wap/userCenter/images/u71.png" /></span>
	    	</c:if>
	    	<c:if test="${requestScope.order.orderStatus == '2'}">
	    	<span style="padding-left: 50px;"><img src="${ctx}/wap/userCenter/images/u71.png" /></span>
	    	<span style="padding-left: 50px;"><img src="${ctx}/wap/userCenter/images/u105.png" style="width: 30px;height: 30px;"/></span>
	    	<span style="padding-left: 50px;"><img src="${ctx}/wap/userCenter/images/u71.png" /></span>
	    	<span style="padding-left: 50px;"><img src="${ctx}/wap/userCenter/images/u71.png" /></span>
	    	</c:if>
	    	<c:if test="${requestScope.order.orderStatus == '3'}">
	    	<span style="padding-left: 50px;"><img src="${ctx}/wap/userCenter/images/u71.png" /></span>
	    	<span style="padding-left: 50px;"><img src="${ctx}/wap/userCenter/images/u71.png" /></span>
	    	<span style="padding-left: 50px;"><img src="${ctx}/wap/userCenter/images/u105.png" style="width: 30px;height: 30px;"/></span>
	    	<span style="padding-left: 50px;"><img src="${ctx}/wap/userCenter/images/u71.png" /></span>
	    	</c:if>
	    	<c:if test="${requestScope.order.orderStatus == '5'}">
	    	<span style="padding-left: 50px;"><img src="${ctx}/wap/userCenter/images/u71.png" /></span>
	    	<span style="padding-left: 50px;"><img src="${ctx}/wap/userCenter/images/u71.png" /></span>
	    	<span style="padding-left: 50px;"><img src="${ctx}/wap/userCenter/images/u71.png" /></span>
	    	<span style="padding-left: 50px;"><img src="${ctx}/wap/userCenter/images/u105.png" style="width: 30px;height: 30px;"/></span>
	    	</c:if>
	    </span>
	    <br><br>
	    <span style="padding-left: 20px;">
	    	<span>&nbsp;等待派单&nbsp;</span>
			<span style="padding-left: 20px;">待确认时间</span>
			<span style="padding-left: 20px;">待上门服务</span>
			<span style="padding-left: 20px;">&nbsp;服务完成&nbsp;</span>
	    </span>
	</div>
	
	<div style="background-color: #E3E3E3;">
	    <h4 style="height:105px;padding:0px;">&nbsp;</h4>
	</div>
	
	<!-- foot -->
	<jsp:include page="../public/foot.jsp" flush="false">
		<jsp:param name="menu" value="wd" />
	</jsp:include>
	
</body>
</html>