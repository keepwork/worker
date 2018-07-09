<%@ page contentType="text/html; charset=UTF-8"%>
<jsp:directive.page import="com.sinovatech.common.web.action.CommonMapping"/>
<jsp:directive.page import="com.sinovatech.common.web.action.ActionConstent"/>
<jsp:directive.page import="org.apache.commons.lang.StringUtils"/>
<%@taglib prefix="c" uri="/WEB-INF/tld/c.tld"%>

<%
	CommonMapping m = (CommonMapping)request.getAttribute("mping");
	System.out.println(m.getMsg());
	
	if(ActionConstent.ALERT.equals(m.getType()))
	{//alert提示方式
%>
		<script type="text/javascript">
<%
		if(!StringUtils.isBlank(m.getMsg())){
%>
			alert("<%=m.getMsg()%>");
<%	
		}
		if(!StringUtils.isBlank(m.getRedirect())){
%>
			parent.location.href="${pageContext.request.contextPath}<%=m.getRedirect()%>";
<%
		}
%>
		</script>
<%
	}
	else{
	
	}
	if(!StringUtils.isBlank(m.getScriptElse())){
%>
	<script type="text/javascript">
	 <%=m.getScriptElse()%>
	</script>
<%			
}
%>
<b style="color: red;">${mping.msg}</b><br />
<a href="javascript:history.go(-1);">返回</a>
