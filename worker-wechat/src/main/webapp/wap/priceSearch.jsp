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
	    <li class="logo">选择服务项目</li>
	    <li class="more">
			<%--
			<a href="${ctx}/pub/menber/centerInit.do?type=wap"><img src="${ctx}/wap/html/images/more.png" alt="" width="40" height="50" /></a>
			--%>
		</li>
	  </ul>
	</header>
	
	<div class="xzfl">
	  <ul>
	  	<c:forEach var="a" items="${requestScope.cateList}">
	         <li class="l" onclick="javascript:window.location.href='${ctx}/pub/article/priceList.do?type=wap&cateCode=${a.code}'">
				 <br/>
	         	<a href="#" ><p><span><strong>${a.name}</strong></span></p></a>
				 <br/>
	         </li>
	    </c:forEach>
	  </ul>

		<br/>
		<ul>
			<li class="l" onclick="javascript:window.location.href='${ctx}/pub/article/articleDetail.do?type=wap&id=103'">
				<br/>
				<a href="#" ><p><span><strong>报价说明</strong></span></p></a>
				<br/>
			</li>
			<li class="l" onclick="javascript:window.location.href='${ctx}/pub/article/articleDetail.do?type=wap&id=105'">
				<br/>
				<a href="#" ><p><span><strong>保修说明</strong></span></p></a>
				<br/>
			</li>
			<%--
			<li class="l" onclick="javascript:window.location.href='${ctx}/pub/article/articleDetail.do?type=wap&id=106'">
				<br/>
				<a href="#" ><p><span><strong>材料说明</strong></span></p></a>
				<br/>
			</li>
			--%>
			<li class="l" onclick="javascript:window.location.href='${ctx}/pub/article/articleDetail.do?type=wap&id=107'">
				<br/>
				<a href="#" ><p><span><strong>扫码支付</strong></span></p></a>
				<br/>
			</li>
		</ul>
	</div>


  
	<table height="100px;"><tr><td >&nbsp;</td></tr></table>
	
	<!-- 公用底部 -->
	<jsp:include page="public/foot.jsp" flush="false">
		<jsp:param name="menu" value="fw" />
	</jsp:include>
</body>
</html>
