<%@ page language="java" pageEncoding="UTF-8" isELIgnored="false"%>
<%@ taglib prefix="c" uri="/WEB-INF/tld/c.tld" %>
<c:set var="ctx" value="${pageContext.request.contextPath}" />

<%
	String menu = request.getParameter("menu");
//System.out.println(menu);
%>

<!--footer-->
<footer class="footer" id="footer">
	<ul class="footnav box-flex">
		<%if(menu.equals("fw")){%>
			<li class="on"><a href="${ctx}/weixin/index.do?type=wx" class="home"><i></i><span class="full-block">首页</span></a></li>
		<%}else{%>
			<li><a href="${ctx}/weixin/index.do?type=wx" class="home"><i></i><span class="full-block">首页</span></a></li>
		<%}%>

		<%if(menu.equals("workApply")){%>
			<li class="on"><a href="${ctx}/pub/menber/workApply.do?type=wap" class="foot-worker"><i></i><span class="full-block">工人入驻</span></a></li>
		<%}else{%>
			<li><a href="${ctx}/pub/menber/workApply.do?type=wap" class="foot-worker"><i></i><span class="full-block">工人入驻</span></a></li>
		<%}%>

		<%if(menu.equals("priceSearch")){%>
			<li class="on"><a href="${ctx}/pub/articleCate/articleCateList.do?parentCode=3&type=wap" class="foot-order"><i></i><span class="full-block">收费查询</span></a></li>
		<%}else{%>
			<li><a href="${ctx}/pub/articleCate/articleCateList.do?parentCode=3&type=wap" class="foot-order"><i></i><span class="full-block">收费查询</span></a></li>
		<%}%>

		<%if(menu.equals("wd")){%>
			<li class="on"><a href="${ctx}/pub/menber/centerInit.do?type=wap" class="my"><i></i><span class="full-block">我的</span></a></li>
		<%}else{%>
			<li><a href="${ctx}/pub/menber/centerInit.do?type=wap" class="my"><i></i><span class="full-block">我的</span></a></li>
		<%}%>
	</ul>
</footer>
<!--footer-end-->

<%--<footer>--%>
	<%--<ul>--%>
		<%--<li onclick="javascript:window.location.href='${ctx}/weixin/index.do?type=wx'"><a href="#">--%>
			<%--<%if(menu.equals("fw")){%>--%>
			<%--<img src="${ctx}/wap/html/images/fl_24.png" width="20" height="19">--%>
			<%--<br/><label><font color="#218FFF">服务</font></label>--%>
			<%--<%}else{%>--%>
			<%--<img src="${ctx}/wap/html/images/cd_24.png" width="20" height="19">--%>
			<%--<br/><label><font color="black">服务</font></label>--%>
			<%--<%}%>--%>
		<%--</a></li>--%>
		<%--<li onclick="javascript:window.location.href='${ctx}/pub/articleCate/articleCateList.do?parentCode=3&type=wap'"><a href="#">--%>
			<%--<%if(menu.equals("sfcx")){%>--%>
			<%--<img src="${ctx}/wap/html/images/fl_24.png" width="20" height="19">--%>
			<%--<br/><label><font color="#218FFF">收费查询</font></label>--%>
			<%--<%}else{%>--%>
			<%--<img src="${ctx}/wap/html/images/cd_24.png" width="20" height="19">--%>
			<%--<br/><label><font color="black">收费查询</font></label>--%>
			<%--<%}%>--%>
		<%--</a></li>--%>
		<%--<li onclick="javascript:window.location.href='${ctx}/pub/menber/centerInit.do?type=wap'"><a href="#">--%>
			<%--<%if(menu.equals("fw")){%>--%>
			<%--<img src="${ctx}/wap/html/images/fl_26.png" width="20" height="19">--%>
			<%--<br/><label><font color="black">我的</font></label>--%>
			<%--<%}else{%>--%>
			<%--<img src="${ctx}/wap/html/images/cd_26.png" width="20" height="19">--%>
			<%--<br/><label><font color="#218FFF">我的</font></label>--%>
			<%--<%}%>--%>
		<%--</a></li>--%>
	<%--</ul>--%>
<%--</footer>--%>



