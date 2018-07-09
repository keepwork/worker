<%@ page language="java" pageEncoding="UTF-8" isELIgnored="false"%>
<%@ taglib prefix="c" uri="/WEB-INF/tld/c.tld" %>
<c:set var="ctx" value="${pageContext.request.contextPath}" />

<%
	String menu = request.getParameter("menu");
System.out.println(menu);
%>
<footer>      
  <ul>
	<li onclick="javascript:window.location.href='${ctx}/weixin/index.do?type=wx'"><a href="#">
		<%if(menu.equals("fw")){%>
			<img src="${ctx}/wap/html/images/fl_24.png" width="20" height="19">
			<br/><label><font color="#218FFF">服务</font></label>
		<%}else if(menu.equals("wd")){%>
			<img src="${ctx}/wap/html/images/cd_24.png" width="20" height="19">
			<br/><label><font color="black">服务</font></label>
		<%}%>
	</a></li>
	<li onclick="javascript:window.location.href='${ctx}/pub/menber/centerInit.do?type=wap'"><a href="#">
		<%if(menu.equals("fw")){%>
			<img src="${ctx}/wap/html/images/fl_26.png" width="20" height="19">
			<br/><label><font color="black">我的</font></label>
		<%}else if(menu.equals("wd")){%>
			<img src="${ctx}/wap/html/images/cd_26.png" width="20" height="19">
			<br/><label><font color="#218FFF">我的</font></label>
		<%}%>
	</a></li>
  </ul>
</footer>

