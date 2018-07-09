<%@ page contentType="text/html; charset=UTF-8"%>
<html>
	<%
		String id=request.getParameter("domainId");
	 %>
	<frameset cols="210,*" name="topwin" id="topwin" framespacing="0" border="0"
		frameborder="0">
		<frame
			src="${pageContext.request.contextPath}/sys/bmsdomainvalue/queryBmsDomainvalueTree.do?domainId=<%=id%>"
			name="left" noresize marginwidth="0" marginheight="0" id="left">
			
		<frameset cols="9,*" framespacing="0" border="0" frameborder="0">
			<frame
				src="${pageContext.request.contextPath}/sys/main/main_middle.jsp"
				name="middle" noresize marginwidth="0" marginheight="0" id="middle"
				scrolling="no">
				
			<frame
				src="${pageContext.request.contextPath}/sys/bmsdomainvalue/queryBmsDomainvalue.do?domainId=<%=id%>"
				name="domainValue" noresize marginwidth="0" marginheight="0" id="main">
				
		</frameset>
		
	</frameset>
	<noframes>
		<body>
		</body>
	</noframes>
</html>
