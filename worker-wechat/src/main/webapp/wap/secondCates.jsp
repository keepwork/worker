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
	    <li class="back"><a href="${ctx}/pub/goodCate/firstCates.do?type=wap&serviceType=${requestScope.serviceType}"><img src="${ctx}/wap/html/images/back.png" width="40" height="50" /></a></li>
	    <li class="logo">开始预约</li>
	    <li class="more"><a href="${ctx}/pub/menber/centerInit.do?type=wap"><img src="${ctx}/wap/html/images/more.png" alt="" width="40" height="50" /></a></li>
	  </ul>
	</header>

	<div class="xzxh">
		<ul style="font-size: 18px;margin-top: 10px;background: #71c3f530;">
				<br/>&nbsp;&nbsp;&nbsp;&nbsp;已选服务类型：<strong>
				<c:if test="${requestScope.serviceType eq '1'}">安装</c:if>
				<c:if test="${requestScope.serviceType eq '2'}">维修</c:if>
				<c:if test="${requestScope.serviceType eq '3'}">保养</c:if>
				<c:if test="${requestScope.serviceType eq '4'}">测量</c:if>
				<c:if test="${requestScope.serviceType eq '5'}">咨询</c:if>
				</strong><br/><br/>
		</ul>
		<br/>

		<ul style="font-size: 18px;margin-top: 10px;background: #71c3f530;">

			<br/>&nbsp;&nbsp;&nbsp;&nbsp;已选服务项目：<strong> ${firstCate.name}</strong>
			<br/><br/>
		</ul>
		<br/>

	  <ul style="margin-top: 10px;background: #71c3f530;">
		<%--
	  	<c:forEach var="a" items="${requestScope.secondCateList}">
	         <li class="l" onclick="javascript:window.location.href='${ctx}/pub/order/orderWrite.do?type=wap&serviceType=${requestScope.serviceType}&firstCateCode=${requestScope.firstCateCode}&secondCateCode=${a.code}'">
	         	<a href="#" ><p><img src="${ctx}/${firstCate.pic}" /><span>${a.name}</span></p></a>
	         </li>
	    </c:forEach>
	    --%>

		<br/>
		<input class="submit" type="button" value="电话咨询" onclick="tel()"/>
		<c:if test="${serviceType!='5'}">
			<br/>
			<input class="submit" type="button" value="立即预约" onclick="javascript:window.location.href='${ctx}/pub/order/orderWrite.do?type=wap&serviceType=${serviceType}&firstCateCode=${firstCate.code}'" />
		</c:if>
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
