<%@ page language="java" pageEncoding="UTF-8" isELIgnored="false"%>
<%@ taglib prefix="c" uri="/WEB-INF/tld/c.tld" %>
<c:set var="ctx" value="${pageContext.request.contextPath}" />

<!DOCTYPE html>
<html>
<head>
	<jsp:include page="public/common.jsp"></jsp:include>
</head>

<body>
	<!--<header>
	  <ul>
	    <li class="back"><a href="${ctx}/weixin/index.do?type=wx"><img src="${ctx}/wap/html/images/back.png" width="40" height="50" /></a></li>
	    <li class="logo">选择服务项目</li>
	    <li class="more"><a href="${ctx}/pub/menber/centerInit.do?type=wap"><img src="${ctx}/wap/html/images/more.png" alt="" width="40" height="50" /></a></li>
	  </ul>
	</header>
	-->
	<header class="header" id="header">
		<a href="${ctx}/weixin/index.do?type=wx" target=_self class="back">返回</a>
		<h1>选择服务项目</h1>
	</header>
	<div style="margin-top: 2rem"></div>
	
	<div class="xzfl">
		<ul style="background: #71c3f530;">
				<li style="margin-left: 0.4rem;font-size: 0.8rem; ">
					<br/>已选服务类型：
					<c:if test="${requestScope.serviceType eq '1'}">安装</c:if>
					<c:if test="${requestScope.serviceType eq '2'}">维修</c:if>
					<c:if test="${requestScope.serviceType eq '3'}">保养</c:if>
					<c:if test="${requestScope.serviceType eq '4'}">测量</c:if>
					<c:if test="${requestScope.serviceType eq '5'}">施工</c:if>
					<br/><br/>
				</li>
		</ul>
		<br/>

		<ul style="margin-top: -1rem">
	  	<c:forEach var="a" items="${requestScope.firstCateList}">
	         <li class="l" onclick="javascript:window.location.href='${ctx}/pub/goodCate/secondCates.do?type=wap&serviceType=${requestScope.serviceType}&firstCateCode=${a.code}'">
	         	<a href="#" ><img src="${ctx}/${a.pic}" width="60" height="60"/><span style="font-size: 0.7rem;color: #222222;">${a.name}</span></a>
	         </li>
	    </c:forEach>
	  </ul>	
	</div>
  
	<table height="50px;"><tr><td >&nbsp;</td></tr></table>
	
	<!-- 公用底部 -->
	<jsp:include page="public/foot.jsp" flush="false">
		<jsp:param name="menu" value="fw" />
	</jsp:include>
</body>
</html>
