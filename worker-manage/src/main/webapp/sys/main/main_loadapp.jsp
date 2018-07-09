<%@ page contentType="text/html; charset=UTF-8"%>
<%@page import="com.sinovatech.bms.adm.model.dto.TBmsRscFuncDTO"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
	<head>
		<title>正在加载... ...</title>
		<meta http-equiv="pragma" content="no-cache">
		<meta http-equiv="cache-control" content="no-cache">
		<meta http-equiv="expires" content="0">
	</head>
	<body style="padding: 100px;">
		<img border="0" alt="加载中" src="<%=request.getContextPath()%>/images/spinner.gif"  />正在加载.. ...
		<%
			TBmsRscFuncDTO rsc = (TBmsRscFuncDTO) request.getAttribute("rsc");
		%>
		<script type="text/javascript">
			location.href="${pageContext.request.contextPath}<%=rsc.getUrl()%>" ;
		</script>
	</body>
</html>