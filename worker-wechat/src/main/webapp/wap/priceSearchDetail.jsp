<%@ page language="java" pageEncoding="UTF-8" isELIgnored="false"%>
<%@ taglib prefix="c" uri="/WEB-INF/tld/c.tld" %>
<c:set var="ctx" value="${pageContext.request.contextPath}" />
<!DOCTYPE html>
<html>
<head>
	<jsp:include page="public/common.jsp"></jsp:include>
	<link rel="stylesheet" href="${ctx}/wap/css/priceSearch.css" />

	<script src="${ctx}/common/js/jQuery/jquery-1.7.2.min.js" type="text/javascript" charset="UTF-8"></script>
	<script src="${ctx}/wap/js/priceSearch.js" type="text/javascript" charset="UTF-8"></script>
</head>

<body>
	<!--<header>
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
	-->
	<header class="header" id="header">
		<a href="${ctx}/weixin/index.do?type=wap" target="_self" class="back">返回</a>
		<h1>收费查询</h1>
	</header>
	<div style="margin-top: 2rem"></div>
	<img src="${ctx}/wap/images/hyzx_wddd_bg2.jpg" style="height: 4rem;width: 100%;">

	<div class="Z_con2_2" style="height:auto">
			<div class="F_wd_top_con2_l" id="wrapper" style="height:auto">
				<ul class="sy">
					<c:forEach var="a" items="${requestScope.priceList}" varStatus="status">
						<li <c:if test="${status.index==0}">class="current"</c:if> >
							${a.title}
						</li>
					</c:forEach>
				</ul>
			</div>
			<div class="F_wd_top_con2_r" id="wrapper1" style="height:auto">

				<c:forEach var="a" items="${requestScope.priceList}" varStatus="status">
					<div class="content" style="font-size: 0.8rem;color:#333">
						<ul class="by nav-main"  <c:if test="${status.index==0}">style="display: block;"</c:if>  <c:if test="${status.index!=0}">style="display: none;"</c:if>  >
								<c:set var="newVariable" scope="page" value="${a.content}"/>
								<%
									String html = (String)pageContext.getAttribute("newVariable");
								%>
								<%=htmlspecialchars(html)%>
						</ul>
					</div>
				</c:forEach>
				<div style="height: 5rem;">&nbsp;</div>
			</div>


	</div>
  
	<table height="100px;"><tr><td >&nbsp;</td></tr></table>
	
	<!-- 公用底部 -->
	<jsp:include page="public/foot.jsp" flush="false">
		<jsp:param name="menu" value="priceSearch" />
	</jsp:include>
</body>
</html>
<%!
	private String htmlspecialchars(String str) {
		str = str.replaceAll("&lt;", "<");
		str = str.replaceAll("&gt;", ">");
		str = str.replaceAll("&quot;", "\"");
		str = str.replaceAll("&nbsp;", " ");
		str = str.replaceAll("&lt；", "<");
		str = str.replaceAll("&gt；", ">");
		str = str.replaceAll("&quot；", "\"");
		str = str.replaceAll("&nbsp；", " ");
		str = str.replaceAll("＝", "=");
		str = str.replaceAll("  ", "&nbsp;&nbsp;");
		str = str.replaceAll("；", ";");
		str = str.replaceAll("（", "(");
		str = str.replaceAll("）", ")");
		return str;
	}
%>