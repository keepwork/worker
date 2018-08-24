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
		<script src="${ctx}/common/js/jquery.min.js"></script>
		<script src="${ctx}/common/js/jquery.myProgress.js"></script>
		<link rel="stylesheet" href="${ctx}/common/css/myProgress.css">
		<link rel="stylesheet" type="text/css" href="${ctx}/style/normalize.css" />
		<link rel="stylesheet" href="${ctx}/style/main.css">

	</head>
	<script>
        $(function () {
            $("#div3").myProgress({speed: 500, percent: ${projectProgress},width: "90%"});
        })
	</script>

	<body class="t2">
<!--主体 开始-->
<div class="breadcrumbs"><span>当前位置：</span><span>订单管理</span> &gt;&gt; <span>订单查看</span></div>
<div class="count"> 
	<div class="right_cont">
		<div id="leftTab1_Content0" class="">

			<!-- 施工进度 -->
			<div class="flex_box_top"><strong class="fl ml10 font14">施工进度</strong></div>
			<div style="margin: 20px 0">
				<div class="progress-out" id="div3">
					<div class="percent-show"><span>0</span>%</div>
					<div class="progress-in"></div>
				</div>
			</div>
			<!-- 订单基本信息 -->
			<div class="flex_box_top"><strong class="fl ml10 font14">订单基本信息</strong></div>
			<div id="image_container">
			 	<table width="100%" cellspacing="0" cellpadding="0" border="0" align="center" class="table001 text_l  mt5">
					<tr>
						<th width="12%" align="right">订单ID：</th>
						<td>${m.orderId}</td>
						<th width="12%" align="right">订单编号：</th>
						<td>${m.orderSn}</td>
						<th width="12%" align="right">订单状态：</th>
						<td>
							<%--<c:if test="${m.orderStatus eq '1'}">待接单</c:if>--%>
							<%--<c:if test="${m.orderStatus eq '2'}">已接单</c:if>--%>
							<%--<c:if test="${m.orderStatus eq '3'}">已确认</c:if>--%>
							<%--<c:if test="${m.orderStatus eq '4'}">已完成</c:if>--%>
							<%--<c:if test="${m.orderStatus eq '5'}">已取消</c:if>--%>


							<c:if test="${m.orderStatus eq '1'}"><font color="blue">待派单</font></c:if>
							<c:if test="${m.orderStatus eq '2'}">已派单</c:if>
							<c:if test="${m.orderStatus eq '3'}">已确认时间</c:if>
							<c:if test="${m.orderStatus eq '4'}">已上门</c:if>
							<c:if test="${m.orderStatus eq '5'}">已开始施工</c:if>
							<c:if test="${m.orderStatus eq '6'}">已完成施工</c:if>
							<c:if test="${m.orderStatus eq '7'}">已评价</c:if>
							<c:if test="${m.orderStatus eq '8'}"><font color="#999">已取消</font></c:if>
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
					<tr>
						<th width="12%" align="right">下单时间：</th>
						<td>${m.orderTimeStr}</td>
						<th width="12%" align="right">派单时间：</th>
						<td>${m.takeTimeStr}</td>
						<th width="12%" align="right">确认时间：</th>
						<td>${m.sureTimeStr}</td>
					</tr>
					<tr>
						<th width="12%" align="right">实际上门时间：</th>
						<td>${m.actualTimeStr}</td>
						<th width="12%" align="right">施工完成时间：</th>
						<td>${m.finishTimeStr}</td>
						<th width="12%" align="right">支付类型：</th>
						<td>
							<c:if test="${m.payType eq '1' }">
								一次性支付
							</c:if>
							<c:if test="${m.payType eq '2' }">
								分期支付
							</c:if>
							<%--<c:if test="${m.payType eq '1' && m.payTime1 eq null }">--%>
								<%--未支付--%>
							<%--</c:if>--%>
							<%--<c:if test="${m.payType eq '1' && m.payTime1 ne null }">--%>
								<%--已支付订单总额--%>
							<%--</c:if>--%>
							<%--<c:if test="${m.payType eq '2' && m.payTime1 eq null && m.payTime2 eq null && m.payTime1 eq null && m.payTime3 eq null }">--%>
								<%--未支付--%>
							<%--</c:if>--%>
							<%--<c:if test="${m.payType eq '2' && m.payTime1 ne null}">--%>
								<%--已支付定金、--%>
							<%--</c:if>--%>
							<%--<c:if test="${m.payType eq '2' && m.payTime2 ne null}">--%>
								<%--已支付中期款、--%>
							<%--</c:if>--%>
							<%--<c:if test="${m.payType eq '2' && m.payTime3 ne null}">--%>
								<%--已支付尾款--%>
							<%--</c:if>--%>
						</td>
					</tr>
					<tr>
						<c:if test="${m.payType eq '1' }">
							<th width="12%" align="right">总额支付状态</th>
							<td colspan="5">
								<c:if test="${m.payTime1 eq null }">
									<font color="red">未支付</font>
								</c:if>
								<c:if test="${m.payTime1 ne null }">
									<font color="green">已支付</font>
								</c:if>
							</td>
						</c:if>
						<c:if test="${m.payType eq '2' }">
							<th width="12%" align="right">定金支付状态：</th>
							<td>
								<c:if test="${m.payTime1 eq null }">
									<font color="red">未支付</font>
								</c:if>
								<c:if test="${m.payTime1 ne null }">
									<font color="green">已支付</font>
								</c:if>
							</td>
							<th width="12%" align="right">中期款支付状态：</th>
							<td>
								<c:if test="${m.payTime2 eq null }">
									<font color="red">未支付</font>
								</c:if>
								<c:if test="${m.payTime2 ne null }">
									<font color="green">已支付</font>
								</c:if>
							</td>
							<th width="12%" align="right">尾款支付状态：</th>
							<td>
								<c:if test="${m.payTime3 eq null }">
									<font color="red">未支付</font>
								</c:if>
								<c:if test="${m.payTime3 ne null }">
									<font color="green">已支付</font>
								</c:if>
							</td>
						</c:if>
					</tr>

					<tr>
						<th width="12%" align="right">协议书</th>
						<td>

							<%--<img src="${ctx}/images/wt.png" style="width:30%;height: 15%">--%>
							<c:if test="${m.protocolImgPath eq null}">
								<img src="${ctx}/images/wt.png" style="width:30%;height: 15%">
							</c:if>
							<c:if test="${m.protocolImgPath ne null}">
								<img src="http://www.zhixiu.xyz/${m.protocolImgPath}" style="width:30%;height: 15%">
							</c:if>
						</td>
						<th width="12%" align="right">报价单</th>
						<td>
							<c:if test="${m.quoteImgPath eq null}">
								<img src="${ctx}/images/wt.png" style="width:30%;height: 15%">
							</c:if>
							<c:if test="${m.quoteImgPath ne null}">
								<img src="http://www.zhixiu.xyz/${m.quoteImgPath}" style="width:30%;height: 15%">
							</c:if>
						</td>
						<th width="12%" align="right">服务单</th>
						<td>
							<c:if test="${m.serviceImgPath eq null}">
								<img src="${ctx}/images/wt.png" style="width:30%;height: 15%">
							</c:if>
							<c:if test="${m.serviceImgPath ne null}">
								<img src="http://www.zhixiu.xyz/${m.serviceImgPath}" style="width:30%;height: 15%">
							</c:if>
						</td>
					</tr>

				</table>
			</div>
				
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
						<td>${totalOrderNum}</td>
						<th width="12%" align="right">满意度：</th>
						<td>${positiveAppraiseRate}%</td>
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

<script src="${ctx}/js/BigPicture.js"></script>
<script>
    (function() {

        function setClickHandler(id, fn) {
            document.getElementById(id).onclick = fn;
        }

        setClickHandler('image_container', function(e) {
            e.target.tagName === 'IMG' && BigPicture({
                el: e.target,
                imgSrc: e.target.src.replace('_thumb', '')
            });
        });

    })();
</script>

</body>


</html>
