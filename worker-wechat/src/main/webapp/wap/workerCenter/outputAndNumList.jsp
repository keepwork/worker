<%@ page language="java" pageEncoding="UTF-8" isELIgnored="false"%>
<%@ taglib prefix="c" uri="/WEB-INF/tld/c.tld" %>
<c:set var="ctx" value="${pageContext.request.contextPath}" />

<!DOCTYPE html>
<html>
<head>
	<jsp:include page="../public/common.jsp"></jsp:include>
	<style>
		.table{
			margin: 2rem 0;
			border-collapse:collapse;
			border:0px solid #999;
			width: 100%;
			height: 4rem;
			table-layout: fixed;
			background: rgba(14, 155, 202, 0.06);
		}
		.table td{
			border-top:0;
			border-right:1px solid #999;
			border-bottom:1px solid #999;
			border-left:0;
			text-align: center;
			font-size: 0.8rem;
			color: #222222;
		}
		table tr.lastrow td{
			border-bottom:0;
		}
		table tr td.lastCol{
			border-right:0;
		}
		em{
			font-size: 0.6rem;
			color: #e27343;
		}
	</style>
</head>

<body>
	<%--<header>--%>
	  <%--<ul>--%>
	    <%--<li class="back"><a href="${ctx}/weixin/index.do?type=wx"><img src="${ctx}/wap/html/images/back.png" width="40" height="50" /></a></li>--%>
	    <%--<li class="logo">月产值和订单</li>--%>
	    <%--<li class="more">--%>
			<%--&lt;%&ndash;--%>
			<%--<a href="${ctx}/pub/menber/centerInit.do?type=wap"><img src="${ctx}/wap/html/images/more.png" alt="" width="40" height="50" /></a>--%>
			<%--&ndash;%&gt;--%>
		<%--</li>--%>
	  <%--</ul>--%>
	<%--</header>--%>

	<header class="header" id="header">
		<a href="javascript:history.go(-1)" target="_self" class="back">返回</a>
		<h1>月产值和订单</h1>
	</header>

	<%--<div >--%>
		<%--<ul style="font-size: 0.6rem;background: #71c3f530;">--%>
			<%--<li>开始时间</li>--%>
			<%--<li>结束时间</li>--%>
		<%--</ul>--%>
	<%--</div>--%>

	<div class="xzfl">
		<table class="table">
			<tr>
				<td>月份</td>
				<td>产值</td>
				<td class="lastCol">订单</td>
			</tr>
			<c:forEach items="${list}" var="m" varStatus="stat">
				<tr <c:if test="${stat.last}" >class="lastrow" </c:if>>
					<td>
						<em>${m.month}</em>
					</td>
					<td>
						<em>${m.monthOutputValue}</em>
					</td>
					<td class="lastCol">
						<em>${m.monthOrderNum}</em>
					</td>
				</tr>
			</c:forEach>
		</table>
	</div>


  
	<table height="100px;"><tr><td >&nbsp;</td></tr></table>
	
	<!-- 公用底部 -->
	<jsp:include page="../public/foot.jsp" flush="false">
		<jsp:param name="menu" value="wd" />
	</jsp:include>
</body>
</html>
