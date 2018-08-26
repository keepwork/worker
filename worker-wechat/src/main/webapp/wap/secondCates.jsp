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
	    <li class="back"><a href="${ctx}/pub/goodCate/firstCates.do?type=wap&serviceType=${requestScope.serviceType}"><img src="${ctx}/wap/html/images/back.png" width="40" height="50" /></a></li>
	    <li class="logo">开始预约</li>
	    <li class="more"><a href="${ctx}/pub/menber/centerInit.do?type=wap"><img src="${ctx}/wap/html/images/more.png" alt="" width="40" height="50" /></a></li>
	  </ul>
	</header>
	-->
	<header class="header" id="header">
		<a href="${ctx}/pub/goodCate/firstCates.do?type=wap&serviceType=${requestScope.serviceType}" target="_self" class="back">返回</a>
		<h1>开始预约</h1>
	</header>
	<div style="margin-top: 2rem"></div>

	<div class="xzxh">
		<ul style="font-size: 0.8rem;margin-top: 1px;background: #71c3f530;">
				<br/>&nbsp;&nbsp;&nbsp;&nbsp;已选服务类型：
				<c:if test="${requestScope.serviceType eq '1'}">安装</c:if>
				<c:if test="${requestScope.serviceType eq '2'}">维修</c:if>
				<c:if test="${requestScope.serviceType eq '3'}">保养</c:if>
				<c:if test="${requestScope.serviceType eq '4'}">测量</c:if>
				<c:if test="${requestScope.serviceType eq '5'}">施工</c:if>
			<br/>&nbsp;&nbsp;&nbsp;&nbsp;已选服务项目：${firstCate.name}
			<br/><br/>
		</ul>


	  <ul style="margin-top: 10px;background: #71c3f530;">
		<%--
	  	<c:forEach var="a" items="${requestScope.secondCateList}">
	         <li class="l" onclick="javascript:window.location.href='${ctx}/pub/order/orderWrite.do?type=wap&serviceType=${requestScope.serviceType}&firstCateCode=${requestScope.firstCateCode}&secondCateCode=${a.code}'">
	         	<a href="#" ><p><img src="${ctx}/${firstCate.pic}" /><span>${a.name}</span></p></a>
	         </li>
	    </c:forEach>
	    --%>

		<br/>
			<a href="tel:4006010731"><input class="submit" type="button" value="电话咨询" /></a>

		<%--<c:if test="${serviceType!='5'}">--%>
			<br/>
			<input class="submit" type="button" value="立即预约" onclick="javascript:window.location.href='${ctx}/pub/order/orderWrite.do?type=wap&serviceType=${serviceType}&firstCateCode=${firstCate.code}'" />
		<%--</c:if>--%>
		<br/>

	  </ul>	
	</div>
  
	<table height="100px;"><tr><td >&nbsp;</td></tr></table>
	
	<!-- 公用底部 -->
	<jsp:include page="public/foot.jsp" flush="false">
		<jsp:param name="menu" value="fw" />
	</jsp:include>
</body>
</html>
<SCRIPT type=text/javascript>
    function tel(){
        alert("拨打电话");

    }
</SCRIPT>
