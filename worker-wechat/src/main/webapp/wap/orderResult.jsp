<%@ page language="java" pageEncoding="UTF-8" isELIgnored="false"%>
<%@ taglib prefix="c" uri="/WEB-INF/tld/c.tld" %>
<c:set var="ctx" value="${pageContext.request.contextPath}" />

<!DOCTYPE html>
<html>
<head>
	<jsp:include page="public/common.jsp"></jsp:include>
</head>

<body>
	<%--<header>--%>
	  <%--<ul>--%>
	    <%--<li class="back"><a href="#"><img src="${ctx}/wap/html/images/back.png" width="40" height="50" /></a></li>--%>
	    <%--<li class="logo">订单结果</li>--%>
	    <%--<li class="more"><a href="#"><img src="${ctx}/wap/html/images/more.png" alt="" width="40" height="50" /></a></li>--%>
	  <%--</ul>--%>
	<%--</header>--%>

	<header class="header" id="header">
		<a href="${ctx}/weixin/index.do?type=wap" target="_self" class="back">返回</a>
		<h1>订单结果</h1>
	</header>
	<div style="margin-top: 2rem"></div>
	<main class="end">
	  <section class="one">订单已提交，等待派单...</section>
	  <section class="two">您可以到我的订单中查看订单的进度<br>页面可以关闭，不影响师傅接单。</section>
	  <section class="three"><a href="${ctx}/pub/order/myOrderList.do?type=wap" class="view">查看我的订单 >></a><a href="${ctx}/weixin/index.do">继续服务</a></section>
	</main>
  
	<!-- 公用底部 -->
	<jsp:include page="public/foot.jsp" flush="false">
		<jsp:param name="menu" value="fw" />
	</jsp:include>
</body>
</html>
