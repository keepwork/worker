<%@ page language="java" pageEncoding="UTF-8" isELIgnored="false"%>
<%@ taglib prefix="c" uri="/WEB-INF/tld/c.tld" %>
<c:set var="ctx" value="${pageContext.request.contextPath}" />
<%@page import="com.pub.article.model.dto.ArticleDTO"%>

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
			<li class="logo">${requestScope.title}</li>
			<li class="more">
				<%--
				<a href="${ctx}/pub/menber/centerInit.do?type=wap"><img src="${ctx}/wap/html/images/more.png" alt="" width="40" height="50" /></a>
				--%>
			</li>
		</ul>
	</header>


	<div style="margin:0px 0 0 0; padding:20px;line-height:26px;letter-spacing: 1px; word-spacing:1px;text-align:justify; text-justify:inter-ideograph;">
	<% 
         ArticleDTO article = (ArticleDTO)request.getAttribute("article");
         String htmlData = article.getContent() != null ? article.getContent() : "";
	%>
	<%=htmlspecialchars(htmlData)%>
	<br><br><br></div>

	<!-- 公用底部 -->
	<jsp:include page="public/foot.jsp" flush="false">
		<jsp:param name="menu" value="fw" />
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
<script type="text/javascript">
	var imgObj = document.getElementsByTagName("img");
	for (var i = 0; i < imgObj.length; i++) {
	    var img = imgObj[i];
	    if(parseInt(img.clientWidth)>320){
	    	img.style.width = "100%";
	    }
	}
</script>