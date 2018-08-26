<%@ page language="java" pageEncoding="UTF-8" isELIgnored="false"%>
<%@ taglib prefix="c" uri="/WEB-INF/tld/c.tld" %> 
<c:set var="ctx" value="${pageContext.request.contextPath}" />
<!DOCTYPE html>
<html>
<head>
	<jsp:include page="../public/common.jsp"></jsp:include>
	<!-- 公用JS|-->
	<link rel="stylesheet" href="${ctx}/wap/userCenter/css/style.css">
</head>
<body>

	<!-- 公用头部-->
	<jsp:include page="../public/head.jsp"></jsp:include>

	<div class="notes" style="margin-top: 3.2rem;">
		<img src="${ctx}/wap/images/notes.png" width="26px" height="24px" />
		<%--<font size="2">年终奖提前发，80元现金拿回家!</font>--%>
		88修全国服务热线：400-601-0731
	</div>

	<ul class="hyzx1" >
	    <li onclick="javascript:window.location.href='${ctx}/pub/order/myOrderList.do?type=wap'" style="cursor:pointer;">
	    	<img src="${ctx}/wap/html/images/tx_07.png" />全部订单</li>
	    <%--<li onclick="javascript:window.location.href='#'" style="cursor:pointer;">--%>
	    	<%--<img src="${ctx}/wap/html/images/tx_13.png" />常用地址</li>--%>
		<li onclick="javascript:window.location.href='${ctx}/pub/menber/personalInfo.do?type=wap'" style="cursor:pointer;">
			<img src="${ctx}/wap/html/images/tx_09.png" />个人信息</li>
		<li onclick="javascript:window.location.href='${ctx}/pub/article/articleDetail.do?type=wap&id=107'" style="cursor:pointer;">
			<img src="${ctx}/wap/html/images/tx_13.png" />扫码支付</li>
	</ul>
	
	<br><br><br><br>
	<!-- foot -->
	<jsp:include page="../public/foot.jsp" flush="false">
		<jsp:param name="menu" value="wd" />
	</jsp:include>
</body>
</html>