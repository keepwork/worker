<%@ page language="java" pageEncoding="UTF-8" isELIgnored="false"%>
<%@ taglib prefix="c" uri="/WEB-INF/tld/c.tld" %>
<c:set var="ctx" value="${pageContext.request.contextPath}" />
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<meta name="Keywords" content="咨询诊断价格">
	<meta name="Description" content="">
	<meta name="viewport" content="width=device-width,initial-scale=1.0,maximum-scale=1.0,minimum-scale=1.0,user-scalable=0">
	<meta name="apple-touch-fullscreen" content="yes">
	<meta name="apple-mobile-web-app-capable" content="yes">
	<meta name="apple-mobile-web-app-status-bar-style" content="black">
	<meta name="format-detection" content="telephone=no">

	<title>惠修</title>

	<link rel="stylesheet" href="${ctx}/wap/html/css/Basc.css" />
	<link rel="stylesheet" href="${ctx}/wap/html/css/demo.css" />
	<link rel="stylesheet" href="${ctx}/wap/css/priceSearch.css" />

	<script src="${ctx}/common/js/jQuery/jquery-1.7.2.min.js" type="text/javascript" charset="UTF-8"></script>
	<script src="${ctx}/wap/js/priceSearch.js" type="text/javascript" charset="UTF-8"></script>
</head>

<body>
	<header>
	  <ul>
	    <li class="back"><a href="${ctx}/pub/articleCate/articleCateList.do?parentCode=3&type=wap"><img src="${ctx}/wap/html/images/back.png" width="40" height="50" /></a></li>
	    <li class="logo">选择服务项目</li>
		  <li class="more">
			  <%--
              <a href="${ctx}/pub/menber/centerInit.do?type=wap"><img src="${ctx}/wap/html/images/more.png" alt="" width="40" height="50" /></a>
              --%>
		  </li>
	  </ul>
	</header>

	<img src="${ctx}/wap/images/hyzx_wddd_bg2.jpg">

	<div class="Z_con2_2" style="height: 33.825em;">
			<div class="F_wd_top_con2_l" id="wrapper" style="height: 628.6em;">
				<ul class="sy">
					<c:forEach var="a" items="${requestScope.priceList}" varStatus="status">
						<li <c:if test="${status.index==0}">class="current"</c:if> >
							${a.title}
						</li>
					</c:forEach>
				</ul>
			</div>
			<div class="F_wd_top_con2_r" id="wrapper1" >

				<c:forEach var="a" items="${requestScope.priceList}" varStatus="status">
					<div class="content">
						<ul class="by nav-main"  <c:if test="${status.index==0}">style="display: block;"</c:if>  <c:if test="${status.index!=0}">style="display: none;"</c:if>  >
							<li class="F_wd_top_con2_r_borb1 F_wd_top_con2_r_borb2" id="box-1">
								<p style=" padding-top:0px; margin-top:0px; height:auto; color:#ed7e0a;">
										${a.content}
								</p>
							</li>
						</ul>
					</div>
				</c:forEach>

			</div>

	</div>
  
	<table height="100px;"><tr><td >&nbsp;</td></tr></table>
	
	<!-- 公用底部 -->
	<jsp:include page="public/foot.jsp" flush="false">
		<jsp:param name="menu" value="fw" />
	</jsp:include>
</body>
</html>
