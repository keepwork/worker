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
	    <li class="logo">收费查询</li>
	    <li class="more">
			<%--
			<a href="${ctx}/pub/menber/centerInit.do?type=wap"><img src="${ctx}/wap/html/images/more.png" alt="" width="40" height="50" /></a>
			--%>
		</li>
	  </ul>
	</header>
	-->
	<header class="header" id="header">
		<a href="${ctx}/weixin/index.do?type=wap" target="_self" class="back">返回</a>
		<h1>收费查询</h1>
	</header>

	<div style="margin-top: 2rem">
		<ul style="font-size: 18px;background: #71c3f530;">
			<br/>&nbsp;&nbsp;&nbsp;&nbsp;请选要查询的项目：
			<br/><br/>
		</ul>
	</div>

	<div class="xzfl">
	  <ul>
	  	<c:forEach var="a" items="${requestScope.cateList}">
	         <li class="l" onclick="javascript:window.location.href='${ctx}/pub/article/priceList.do?type=wap&cateCode=${a.code}'">
				 <br/>
	         	<a href="#" ><p><span style="color: #222222;font-size: 0.8rem;">${a.name}</span></p></a>
				 <br/>
	         </li>
	    </c:forEach>
	  </ul>

		<br/>
		<ul>
			<li class="l" onclick="javascript:window.location.href='${ctx}/pub/article/articleDetail.do?type=wap&id=103'">
				<br/>
				<a href="#" ><p><span style="color: #222222;font-size: 0.8rem;">报价说明</span></p></a>
				<br/>
			</li>
			<li class="l" onclick="javascript:window.location.href='${ctx}/pub/article/articleDetail.do?type=wap&id=105'">
				<br/>
				<a href="#" ><p><span style="color: #222222;font-size: 0.8rem;">保修说明</span></p></a>
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
				<a href="#" ><p><span style="color: #222222;font-size: 0.8rem;">扫码支付</span></p></a>
				<br/>
			</li>
		</ul>
	</div>


  
	<table height="100px;"><tr><td >&nbsp;</td></tr></table>
	
	<!-- 公用底部 -->
	<jsp:include page="public/foot.jsp" flush="false">
		<jsp:param name="menu" value="priceSearch" />
	</jsp:include>
</body>
</html>
