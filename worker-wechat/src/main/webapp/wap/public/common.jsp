<%@ page language="java" pageEncoding="UTF-8"%>
<%@page import="com.sinovatech.hd.tools.cache.ICache"%>
<%@page import="com.sinovatech.hd.tools.cache.CacheFactory"%>
<%@ taglib prefix="c" uri="/WEB-INF/tld/c.tld" %>
<c:set var="ctx" value="${pageContext.request.contextPath}" />

<%
	ICache cache = CacheFactory.newCache();
%>
<title><%=cache.get("seo_title")%></title>
<meta name="keywords" content="<%=cache.get("seo_keywords")%>" />
<meta name="description" content="<%=cache.get("seo_description")%>" />
<meta name="generator" content="<%=cache.get("seo_generator")%>" /> 
<meta name="viewport" content="width=device-width,height=device-height,inital-scale=1.0,maximum-scale=1.0,user-scalable=no;">
<meta name="apple-mobile-web-app-capable" content="yes">
<meta name="apple-mobile-web-app-status-bar-style" content="black">
<meta name="format-detection" content="telephone=no">

<link rel="stylesheet" href="${ctx}/wap/html/css/Basc.css" />
<link rel="stylesheet" href="${ctx}/wap/html/css/demo.css" />

<script type="text/javascript" charset="UTF-8" src="${ctx}/common/js/jquery-1.9.1.min.js" ></script>


<!--在线帮助 Start
<script type="text/javascript" src="${ctx}/wap/js/zxkf.js"></script>
<div id="KeFuDiv" class="KeFuDiv">
    <div><a href="#"><img src="${ctx}/wap/images/help.png" width="50" height="50" usemap="#Map" style="cursor:move;" onmousedown="MoveDiv(KeFuDiv,event);" title="拖动"></a></div>
  <map name="Map" id="Map">
    <area shape="circle" coords="32,0,0" href="javascript:" onclick="KeFuDiv.style.display='none';" title="关闭"/>
  </map>
</div>
<script>
//初始位置
gID("KeFuDiv").style.top = (document.documentElement.clientHeight - gID("KeFuDiv").offsetHeight)/1.2 +"px";
gID("KeFuDiv").style.left =(document.documentElement.clientWidth - gID("KeFuDiv").offsetWidth)/1.1 +"px";
//开始滚动
ScrollDiv('KeFuDiv');
</script>
<!--在线帮助 End-->



