<%@ page language="java" import="java.util.*" contentType="text/html; charset=utf-8"%>
<%@ taglib uri="/WEB-INF/tld/extremecomponents.tld" prefix="ec"%>
<%@ taglib uri="/WEB-INF/tld/c.tld" prefix="c"%>
<c:set var="ctx" value="${pageContext.request.contextPath}" />

<!DOCTYPE html>
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
		<title></title>
		<link rel="stylesheet" type="text/css" href="${ctx}/common/css/public2.css"/>
		<link rel="stylesheet" type="text/css" href="${ctx}/common/css/style2.css" />
		<link href="${ctx}/sys/css/public.css" rel="stylesheet" type="text/css">
	</head>

<body class="t2">
<!--主体 开始-->
<div class="breadcrumbs"><span>当前位置：</span><span>订单管理</span> &gt;&gt; <span>订单查看</span></div>
<div class="count"> 
	<div class="right_cont">
		<div id="leftTab1_Content0" class="">
				
				<!-- 订单基本信息 -->
				<div class="flex_box_top"><strong class="fl ml10 font14">订单基本信息</strong></div>
			 	<table width="100%" cellspacing="0" cellpadding="0" border="0" align="center" class="table001 text_l  mt5">
					<tr>
						<th width="12%" align="right">订单ID：</th>
						<td>${m.orderId}</td>
						<th width="12%" align="right">订单编号：</th>
						<td>${m.orderSn}</td>
						<th width="12%" align="right">订单状态：</th>
						<td>
							<c:if test="${m.orderStatus eq '1'}">待接单</c:if>
							<c:if test="${m.orderStatus eq '2'}">已接单</c:if>
							<c:if test="${m.orderStatus eq '3'}">已确认</c:if>
							<c:if test="${m.orderStatus eq '4'}">已完成</c:if>
							<c:if test="${m.orderStatus eq '5'}">已取消</c:if>
						</td>
						
					</tr>
					<tr>
						<th width="12%" align="right">下单时间：</th>
						<td>${m.orderTimeStr}</td>
						<th width="12%" align="right">接单时间：</th>
						<td>${m.takeTimeStr}</td>
						<th width="12%" align="right">确认时间：</th>
						<td>${m.sureTimeStr}</td>
					</tr>
					<tr>
						<th width="12%" align="right">结单时间：</th>
						<td>${m.endTimeStr}</td>
						<th width="12%" align="right">服务时间：</th>
						<td>
							<c:if test="${m.orderStatus == '1'}">师傅跟我确认</c:if>
				       		<c:if test="${m.orderStatus == '2'}">师傅跟我确认</c:if>
				       		<c:if test="${m.orderStatus == '3'}">${m.sureTimeStr}</c:if>
				       		<c:if test="${m.orderStatus == '4'}">${m.sureTimeStr}</c:if>
				       		<c:if test="${m.orderStatus == '5'}">${m.sureTimeStr}</c:if>
						</td>
						<th width="12%" align="right">支付方式：</th>
						<td>
							<c:if test="${m.payType eq '1'}">余额支付</c:if>
							<c:if test="${m.payType eq '2'}">积分支付</c:if>
							<c:if test="${m.payType eq '3'}">微信支付</c:if>
						</td>
					</tr>
					<tr>
						<th width="12%" align="right">预约服务类型：</th>
						<td>
							<c:if test="${m.serviceType == '1'}">预约安装</c:if>
					        <c:if test="${m.serviceType == '2'}">预约维修</c:if>
					        <c:if test="${m.serviceType == '3'}">预约保养</c:if>
					        <c:if test="${m.serviceType == '4'}">预约测量</c:if>
					        <c:if test="${m.serviceType == '5'}">预约咨询</c:if>
						</td>
						<th width="12%" align="right">预约产品类型：</th>
						<td>${m.firstCateName}</td>
						<th width="12%" align="right">预约产品型号：</th>
						<td>${m.secondCateName}</td>
					</tr>
				</table>
				
				<!-- 服务师傅信息 -->
				<div class="flex_box_top mt15"><strong class="fl ml10 font14">服务师傅信息</strong></div>
				<table width="100%" cellspacing="0" cellpadding="0" border="0" align="center" class="table001 text_l  mt5">
					<tr>
						<th width="12%" align="right">师傅姓名：</th>
						<td>${worker.realName}</td>
						<th width="12%" align="right">师傅电话：</th>
						<td>${worker.mobile}</td>
						<th width="12%" align="right">师傅头像：</th>
						<td><img src="${worker.headimgurl}" width="80" height="80" /></td>
					</tr>
					<tr>
						<th width="12%" align="right">接单数：</th>
						<td>100</td>
						<th width="12%" align="right">满意度：</th>
						<td>90%</td>
						<th width="12%" align="right">&nbsp;</th>
						<td>&nbsp;</td>
					</tr>
				</table>
				
				<!-- 客户信息 -->
				<div class="flex_box_top mt15"><strong class="fl ml10 font14">客户信息</strong></div>
				<table width="100%" cellspacing="0" cellpadding="0" border="0" align="center" class="table001 text_l  mt5">
					<tr>
						<th width="12%" align="right">客户姓名：</th>
						<td>${addr.consignee}</td>
						<th width="12%" align="right">客户电话：</th>
						<td>${addr.mobile}</td>
					</tr>
					<tr>
						<th width="12%" align="right">客户地址：</th>
						<td colspan="3">${addr.street}</td>
					</tr>
				</table>
				
				
				<!-- 总费用 -->
				<c:if test="${m.serviceType == '1'}">
				<div class="flex_box_top mt15"><strong class="fl ml10 font14">总费用</strong></div>
				<table width="100%" cellspacing="0" cellpadding="0" border="0" align="center" class="table001 text_l  mt5">
					<tr>
						<th width="12%" align="left">
								总费用：<font color="red">￥${m.totalPrice}</font> 
						</th>
					</tr>
				</table>
				</c:if>
				
				
				<div class="mt20 mb10 text_c">
					<a href="#" class="sexybutton" onclick="location.href='${ctx}/pub/order/queryList.do'"><span><span>返回</span></span></a>
				</div>
				
		</div>
	</div>
	<div class="clear"></div>
</div>
<!--主体 结束--> 

</body>

</html>
