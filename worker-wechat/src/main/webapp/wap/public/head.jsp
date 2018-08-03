<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="/WEB-INF/tld/c.tld" %>
<c:set var="ctx" value="${pageContext.request.contextPath}" />

<style type="text/css"> 
<!--
.wdddo{ background:url(${ctx}/wap/userCenter/images/hyzx_wddd_bg.jpg) center no-repeat; height:7rem;background-size: cover; overflow:hidden;margin:0; padding:20px;}
.wdddo .tx{border-radius: 50%;height:80px; width:80px; overflow:hidden; border:solid #FFF 1px; float:left;}
.wdddo .xm{ font-size:24px; color:#FFF; margin:20px 0 0 20px; float:left;}
.wdddo .xm span{ font-size:14px; color:#FFF;}
.wdddo .jf{ font-size:14px; color:#FFF; float:right; margin:20px 0 0 0;}
.wdddo .jf input{border-radius:5px; background:#FFF; border:0; color:#ff4400; width:80px; padding:5px;}
-->
</style>

<header>
<ul class="wdddo">
	<li class="tx"><img src="${sessionScope.wxmenber.headimgurl}" width="80" height="80" /></li>
    <li class="xm">${sessionScope.wxmenber.realName}</li>
</ul>
</header>


