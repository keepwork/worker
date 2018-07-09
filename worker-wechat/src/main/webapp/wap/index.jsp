<%@ page language="java" pageEncoding="UTF-8" isELIgnored="false"%>
<%@ taglib prefix="c" uri="/WEB-INF/tld/c.tld" %>
<c:set var="ctx" value="${pageContext.request.contextPath}" />

<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1" />
	<meta name="viewport" content="width=device-width, initial-scale=1.0, user-scalable=no"/>
	<title>首页</title>
	<link rel="stylesheet" href="${ctx}/wap/html/css/Basc.css" />
	<link rel="stylesheet" href="${ctx}/wap/html/css/demo.css" />
</head>

<body>
	<header>
	  <ul>
	    <li class="back">&nbsp;</li>
	    <li class="logo">服务</li>
	    <li class="more">&nbsp;</li>
	  </ul>
	</header>
	        
	<div class="main">
	  <ul class="banner clearfix">
	    <li class="top"><a href="${ctx}/pub/goodCate/firstCates.do?type=wap&serviceType=1"><img src="${ctx}/wap/html/images/fw_03.png"></a></li>
	    <li class="bottom"><a href="${ctx}/pub/goodCate/firstCates.do?type=wap&serviceType=1">预约安装</a></li>
	  </ul>
	  <ul class="mainmenu">
		<li><a href="${ctx}/pub/goodCate/firstCates.do?type=wap&serviceType=2" ><p><img src="${ctx}/wap/html/images/fw_07.png"/><span>预约维修</span></p></a></li>
		<li><a href="${ctx}/pub/goodCate/firstCates.do?type=wap&serviceType=3" ><p><img src="${ctx}/wap/html/images/fw_10.png"/><span>预约保养</span></p></a></li>
		<li><a href="${ctx}/pub/goodCate/firstCates.do?type=wap&serviceType=4" ><p><img src="${ctx}/wap/html/images/fw_14.png"/><span>预约测量</span></p></a></li>
		<li><a href="${ctx}/pub/goodCate/firstCates.do?type=wap&serviceType=5" ><p><img src="${ctx}/wap/html/images/fw_15.png"/><span>预约咨询</span></p></a></li>
	  </ul>	
	  <!-- 
	  <ul style="margin-top: 13px;margin-left: 5px;"> 
		<li><img src="${ctx}/wap/images/huida.png" width="385" height="160" /></li>
	  </ul>	
	   -->
	</div>
	

	<!-- 公用底部 -->
	<jsp:include page="public/foot.jsp" flush="false">
		<jsp:param name="menu" value="fw" />
	</jsp:include>
	
</body>
</html>
