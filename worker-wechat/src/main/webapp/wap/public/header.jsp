<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="/WEB-INF/tld/c.tld" %>
<c:set var="ctx" value="${pageContext.request.contextPath}" />

<header class="header">
		<ul class="yjrrs" style="width: 336px;">
	        <!-- 首页 -->
	        <c:if test="${pageTitle=='index'}">
        		 <li class="text">首页</li>
	        </c:if>
	        <!-- 其它页面 -->
	        <c:if test="${null==pageTitle || pageTitle!='index'}">
	        	<li class="data2" ><a href="javascript:window.history.back(-1);"><img src="${ctx}/wap/images/back.png" width="50" height="50" /></a></li>
	        	<li class="text2" >${pageTitle}</li>
	        </c:if>
	    </ul>
</header>



