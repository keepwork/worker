<%@ page contentType="text/html; charset=UTF-8"%>
<html>
	<frameset cols="210,*" name="topwin" id="topwin" framespacing="0" border="0"
		frameborder="0">
		<frame
			src="${pageContext.request.contextPath}/sys/bmsrscfunc/queryBmsRscFunc.do"
			name="left" noresize marginwidth="0" marginheight="0" id="left">
		<frameset cols="9,*" framespacing="0" border="0" frameborder="0">
			<frame
				src="${pageContext.request.contextPath}/sys/main/main_middle.jsp"
				name="middle" noresize marginwidth="0" marginheight="0" id="middle"
				scrolling="auto">
			<frame src="${pageContext.request.contextPath}/sys/main/homeBmsRscFunc.jsp"
				name="welcome" noresize marginwidth="0" marginheight="0" id="main">
		</frameset>
	</frameset>
	<noframes>
		<body>
		</body>
	</noframes>
</html>