<%@ page language="java" pageEncoding="UTF-8" isELIgnored="false"%>
<%@ taglib prefix="c" uri="/WEB-INF/tld/c.tld" %>
<c:set var="ctx" value="${pageContext.request.contextPath}" />

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<!-- 公用JS|CSS-->
	<jsp:include page="public/common.jsp"></jsp:include>
</head>
<body>

	<!-- 公用头部 -->
	<jsp:include page="public/header.jsp"></jsp:include>
	
	<div class="abouto">
	<table width="100%" cellspacing="0" cellpadding="0" style="font-size: 12;">
	   <tr>
		    <td width="27%" height="30" >提交时间：</td>
		    <td width="60%">${requestScope.complaint.compTimeStr}</td>
	   </tr>
	   <tr>
	    	<td width="27%" height="30">我的建议：</td>
	    	<td width="60%" >${requestScope.complaint.content}</td>
	   </tr>
	   <tr>
	    	<td width="27%" height="30">状&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;态：</td>
	    	<td width="60%" >
	    		<c:if test="${requestScope.complaint.status == '0'}">未处理</c:if>
	    		<c:if test="${requestScope.complaint.status == '1'}">已处理</c:if>
	    	</td>  
	   </tr>
	   <c:if test="${requestScope.complaint.status == '1'}">
	   <tr>
	    	<td width="27%" height="30">处理时间：</td>
	    	<td width="60%" >${requestScope.complaint.commitTimeStr}</td>
	   </tr>
	   <tr>
	    	<td width="27%" height="30">回复内容：</td>
	    	<td width="60%" >${requestScope.complaint.feedback}</td>
	   </tr>
	   </c:if>
	</table>
	</div>
	
	<!-- 公用底部 -->
	<jsp:include page="public/foot.jsp"></jsp:include>
</body>
</html>