<%@ page language="java" pageEncoding="UTF-8" isELIgnored="false"%>
<%@ taglib prefix="c" uri="/WEB-INF/tld/c.tld" %>
<%@ taglib prefix="fmt" uri="/WEB-INF/tld/fmt.tld" %>
<c:set var="ctx" value="${pageContext.request.contextPath}" />

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<!-- 公用JS|CSS-->
	<jsp:include page="../public/common.jsp"></jsp:include>
	<link rel="stylesheet" href="${ctx}/common/css/tab2.css">
	<link rel="stylesheet" href="${ctx}/wap/userCenter/css/style.css">
	<script type="text/javascript">
		function beforeExit(menId)
		{
	    	if(window.confirm('1,您退出后，将不再享有保障。再次加入，将重新计算等待期。2,您的账户余额将退还到您指定的银行卡，但要收取1%+2元/笔的手续费。您坚持要退出吗？')){
				window.location.href = "${ctx}/pub/exit/exitPlanInit.do?type=wap&menId="+menId;
			}
		}
	</script>
</head>

<body>

	<!-- 公用头部-->
	<jsp:include page="head.jsp"></jsp:include>

	<ul class="round">
			<li class="title">
				<a href="${ctx}/pub/menber/centerInit.do?type=wap" >返回</a>
				我的余额：<font size="3" color="red">${menber.balanceFee}</font>元
			</li>
	</ul>
	
	
	<table class="kuang" border="0" cellpadding="0" cellspacing="0" width="100%" style="font-size: 15px;">
			<tr style="height: 35px;">
				<th width="30%" align="right">真实姓名：</th>
				<td width="70%">${menber.realName}</td>
			</tr>
			<tr style="height: 35px;">
				<th width="30%" align="right">身份证号：</th>
				<td width="70%">${menber.pid}</td>
			</tr>
			<tr style="height: 35px;">
				<th width="30%" align="right">账户余额：</th>
				<td width="70%">${menber.balanceFee}</td>
			</tr>
			<tr style="height: 35px;">
				<th width="30%" align="right">账户充值：</th>
				<td width="70%"><a href="${ctx}/pub/menber/recharge.do?type=wap&menId=${menber.id}" ><font color="blue">马上充值</font></a></td>
			</tr>
			<tr style="height: 35px;">
				<th width="30%" align="right">扣款记录：</th>
				<td width="70%"><a href="${ctx}/pub/cut/myCutList.do?type=wap&menId=${menber.id}" ><font color="blue">查看</font></a></td>
			</tr>
			<tr style="height: 35px;">
				<th width="30%" align="right">充值记录：</th>
				<td width="70%"><a href="${ctx}/pub/charge/myChargeList.do?type=wap&menId=${menber.id}" ><font color="blue">查看</font></a></td>
			</tr>
			<tr style="height: 35px;">
				<th width="30%" align="right">保障内容：</th>
				<td width="70%"><a href="${ctx}/pub/event/planDetail.do?type=wap" target="_blank"><font color="blue">计划详情</font></a></td>
			</tr>
			<c:if test="${menber.isjoin==0}">
			<tr style="height: 35px;">
				<th width="30%" align="right">入会状态：</th>
				<td width="70%">未加入</td>
			</tr>
			<tr style="height: 35px;">
				<td colspan="2" style="padding-left: 50px;">
					<font color="red">原因：</font>首次充值，须保证账户余额至少有12元；<br>
				</td>
			</tr>
			</c:if>
			<c:if test="${menber.isjoin==1 || menber.isjoin==2}">
			<tr style="height: 35px;">
				<th width="30%" align="right">入会状态：</th>
				<td width="70%">您已经有效加入</td>
			</tr>
			<tr style="height: 35px;">
				<th width="30%" align="right">加入时间：</th>
				<td width="70%">
					<fmt:formatDate value="${menber.joinTime}" pattern="yyyy-MM-dd HH:mm:ss" /> 
				</td>
			</tr>
			<tr style="height: 35px;">
				<th width="30%" align="right">保障生效：</th>
				<td width="70%">${sessionScope.effectTime}&nbsp;至&nbsp;50周岁</td>
			</tr>
			<tr style="height: 35px;">
				<th width="12%" align="right">退出计划：</th>
				<td width="75%"><a href="#" onclick="beforeExit('${menber.id}');"><font color="blue">申请退出</font></a></td>
			</tr>
			</c:if>
	</table>
	
	
	
	<br><br><br>
	<!-- foot -->
	<jsp:include page="../public/foot.jsp"></jsp:include>

</body>
</html>
