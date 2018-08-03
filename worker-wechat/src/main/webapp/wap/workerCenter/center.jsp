<%@ page language="java" pageEncoding="UTF-8" isELIgnored="false"%>
<%@ taglib prefix="c" uri="/WEB-INF/tld/c.tld" %> 
<c:set var="ctx" value="${pageContext.request.contextPath}" />
<!DOCTYPE html>
<html>
<head>
	<jsp:include page="../public/common.jsp"></jsp:include>
	<!-- 公用JS|-->
	<link rel="stylesheet" href="${ctx}/wap/userCenter/css/style.css">
	<style>
		table{
			border-collapse:collapse;
			border:0px solid #999;
			width: 100%;
			height: 4rem;
			table-layout: fixed;
			background: rgba(14, 155, 202, 0.06);
		}
		table td{
			/*border-top:0;*/
			/*border-right:1px solid #999;*/
			/*border-bottom:1px solid #999;*/
			/*border-left:0;*/
			text-align: center;
		}
		/*table tr.lastrow td{*/
			/*border-bottom:0;*/
		/*}*/
		/*table tr td.lastCol{*/
			/*border-right:0;*/
		/*}*/
		em{
			color: #e27343;
		}
	</style>
</head>
<body>

	<!-- 公用头部-->
	<jsp:include page="../public/head.jsp"></jsp:include>

	<div class="notes" style="margin-top: 3.2rem;">
		<img src="${ctx}/wap/images/notes.png" width="26px" height="24px" />
		<%--<font size="2">年终奖提前发，80元现金拿回家!</font>--%>
		年终奖提前发，80元现金拿回家!
	</div>
	<table>
		<tr>
			<td>
				<span style="color: #222222;font-size: 0.6rem">历史产值</span>
				<br>
				<em>${order.historyOutputValue}</em>
			</td>
			<td>
				<span style="color: #222222;font-size: 0.6rem">总订单数</span>
				<br>
				<em>${order.totalOrderNum}</em>
			</td>
			<td class="lastCol">
				<span style="color: #222222;font-size: 0.6rem">好评率</span>
				<br>
				<em>${positiveAppraiseRate}%</em>
			</td>
		</tr>
		<tr class="lastrow">
			<td>
				<span style="color: #222222;font-size: 0.6rem">本月产值</span>
				<br>
				<em>${order.monthOutputValue}</em>
			</td>
			<td>
				<span style="color: #222222;font-size: 0.6rem">本月单数</span>
				<br>
				<em>${order.monthOrderNum}</em>
			</td>
			<td>
				<a href="${ctx}/pub/order/outputAndNumList.do?type=wap"><span style="color: #222222;font-size: 0.6rem">更多>></span></a>
			</td>
		</tr>
	</table>
	<ul class="hyzx1" >
	    <li onclick="javascript:window.location.href='${ctx}/pub/order/myOrderList.do?type=wap'" style="cursor:pointer;">
	    	<img src="${ctx}/wap/html/images/tx_07.png" />全部订单</li>
	    <li onclick="javascript:window.location.href='${ctx}/pub/menber/personalInfo.do?type=wap'" style="cursor:pointer;">
	    	<img src="${ctx}/wap/html/images/tx_09.png" />个人信息</li>
	</ul>
	
	<br><br><br><br>
	<!-- foot -->
	<jsp:include page="../public/foot.jsp" flush="false">
		<jsp:param name="menu" value="wd" />
	</jsp:include>
</body>
</html>