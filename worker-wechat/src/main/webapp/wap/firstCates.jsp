<%@ page language="java" pageEncoding="UTF-8" isELIgnored="false"%>
<%@ taglib prefix="c" uri="/WEB-INF/tld/c.tld" %>
<c:set var="ctx" value="${pageContext.request.contextPath}" />

<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1" />
	<meta name="viewport" content="width=device-width, initial-scale=1.0, user-scalable=no"/>
	<title>惠修</title>
	<link rel="stylesheet" href="${ctx}/wap/html/css/Basc.css" />
	<link rel="stylesheet" href="${ctx}/wap/html/css/demo.css" />
</head>

<body>
	<header>
	  <ul>
	    <li class="back"><a href="${ctx}/weixin/index.do?type=wx"><img src="${ctx}/wap/html/images/back.png" width="40" height="50" /></a></li>
	    <li class="logo">选择产品分类</li>
	    <li class="more"><a href="${ctx}/pub/menber/centerInit.do?type=wap"><img src="${ctx}/wap/html/images/more.png" alt="" width="40" height="50" /></a></li>
	  </ul>
	</header>
	
	<div class="xzfl">
	  <h3>已选服务类型：
	  	<c:if test="${requestScope.serviceType eq '1'}">安装</c:if>
		<c:if test="${requestScope.serviceType eq '2'}">维修</c:if>
		<c:if test="${requestScope.serviceType eq '3'}">保养</c:if>
		<c:if test="${requestScope.serviceType eq '4'}">测量</c:if>
		<c:if test="${requestScope.serviceType eq '5'}">咨询</c:if>
	  </h3>
	  <ul>
	  	<c:forEach var="a" items="${requestScope.firstCateList}">
	         <li class="l" onclick="javascript:window.location.href='${ctx}/pub/goodCate/secondCates.do?type=wap&serviceType=${requestScope.serviceType}&firstCateCode=${a.code}'">
	         	<a href="#" ><p><img src="${ctx}/${a.pic}" width="60" height="60"/><span><strong>${a.name}</strong></span></p></a>
	         </li>
	    </c:forEach>
	  </ul>	
	</div>
  
	<table height="100px;"><tr><td >&nbsp;</td></tr></table>
	
	<!-- 公用底部 -->
	<jsp:include page="public/foot.jsp" flush="false">
		<jsp:param name="menu" value="fw" />
	</jsp:include>
</body>
</html>
