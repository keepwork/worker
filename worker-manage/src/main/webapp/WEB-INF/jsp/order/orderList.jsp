<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="/WEB-INF/tld/extremecomponents.tld" prefix="ec"%>
<%@ taglib uri="/WEB-INF/tld/c.tld" prefix="c"%>
<c:set var="ctx" value="${pageContext.request.contextPath}" />
<HTML>
<HEAD>
	<TITLE>后台管理系统</TITLE>
	<meta http-equiv="content-type" content="text/html; charset=UTF-8">
	<meta http-equiv="X-UA-Compatible" content="IE=EmulateIE7" />
	<meta name=generator content="MSHTML 8.00.6001.18939">
	
	<link href="${ctx}/sys/css/public.css" rel="stylesheet" type="text/css">
	<link href="${ctx}/sys/css/style.css" rel="stylesheet" type="text/css">
	<link rel="stylesheet" type="text/css" href="${ctx}/common/css/public2.css"/>
	<script type="text/javascript" src="${ctx}/js/mootools-release-1.11.js"></script>
	<script type="text/javascript" src="${ctx}/common/js/calendar/WdatePicker.js"></script>
	<script type="text/javascript" src="${ctx}/common/js/jquery.js" charset="UTF-8"></script>
	<script type="text/javascript" src="${ctx}/common/js/jquery-impromptu.3.2.js" charset="UTF-8"></script>
	<script type="text/javascript" src="${ctx}/common/js/showdiv.js"></script>
	
	<script type="text/javascript">
	function btn_add_click()
	{
		var url = "${ctx}/pub/menber/beforeAdd.do";
		document.location.href=url;
	}		
	
	function submitQuery(){
		document.getElementById("searchForm").submit();
	};
	function resetQuery(){
		document.getElementById("searchForm").reset();
	};
	</script>
<body class="overfwidth">

<div class="barnavtop">您所在的位置：订单管理 > 订单列表</div>
<div id="workspace">
	<!--主体 开始-->
    <div id="container">    
        <!--searchForm 开始-->
        
        <div class="select_box">
        	<form id="searchForm" action="${ctx}/pub/order/queryList.do" method="post" onsubmit="">
			<table border="0" cellspacing="0" cellpadding="5" class="tb_ie6" style="font-size: 12px;">
				<tr bgcolor="#f7f7f7">
					<td width="25%">
						&nbsp;&nbsp;订单编号：
						<input class="text_1 va_mid" type="text" id="orderSn"
							name="orderSn" maxlength="32" value="${order.orderSn}" style="width: 250px;"/>
					</td>
					<td width="20%">
						<a class="sexybutton" onclick="submitQuery();return false;"><span><span>查询</span></span></a>
					</td>
					<td width="55%">
						&nbsp;
					</td>
					<!-- 
					<td>
						订单状态：
						<select id="orderStatus" name="orderStatus" class="select_2 va_mid" style="width: 100px;">
							<option value="" <c:if test = "${order.orderStatus eq ''}">selected='selected'</c:if> >-请选择-</option>
							<option value="0" <c:if test = "${order.orderStatus eq '0'}">selected='selected'</c:if> >未处理</option>
							<option value="1" <c:if test = "${order.orderStatus eq '1'}">selected='selected'</c:if> >已处理</option>
							<option value="2" <c:if test = "${order.orderStatus eq '2'}">selected='selected'</c:if> >已发货</option>
							<option value="3" <c:if test = "${order.orderStatus eq '3'}">selected='selected'</c:if> >已完成</option>
							<option value="4" <c:if test = "${order.orderStatus eq '4'}">selected='selected'</c:if> >已取消</option>
						</select>
					</td>
					<td>
						支付方式：
						<select id="payType" name="payType" class="select_2 va_mid" style="width: 100px;">
							<option value="" <c:if test = "${order.payType eq ''}">selected='selected'</c:if> >-请选择-</option>
							<option value="1" <c:if test = "${order.payType eq '1'}">selected='selected'</c:if> >余额支付</option>
							<option value="2" <c:if test = "${order.payType eq '2'}">selected='selected'</c:if> >积分支付</option>
							<option value="3" <c:if test = "${order.payType eq '3'}">selected='selected'</c:if> >微信支付</option>
						</select>
					</td>
					<td>
						支付状态：
						<select id="payStatus" name="payStatus" class="select_2 va_mid" style="width: 100px;">
							<option value="" <c:if test = "${order.payStatus eq ''}">selected='selected'</c:if> >-请选择-</option>
							<option value="0" <c:if test = "${order.payStatus eq '0'}">selected='selected'</c:if> >未支付</option>
							<option value="1" <c:if test = "${order.payStatus eq '1'}">selected='selected'</c:if> >已支付</option>
						</select>
					</td>
					 -->
				</tr>
				<!-- 
				<tr bgcolor="#f7f7f7">
					<td>
						&nbsp;&nbsp;配送状态：
						<select id="shippingStatus" name="shippingStatus" class="select_2 va_mid" style="width: 100px;">
							<option value=""  <c:if test = "${order.shippingStatus eq ''}">selected='selected'</c:if> >-请选择-</option>
							<option value="0" <c:if test = "${order.shippingStatus eq '0'}">selected='selected'</c:if> >未配送</option>
							<option value="1" <c:if test = "${order.shippingStatus eq '1'}">selected='selected'</c:if> >已配送</option>
							<option value="1" <c:if test = "${order.shippingStatus eq '1'}">selected='selected'</c:if> >已送达</option>
						</select>
					</td>
					<td>
						下单开始时间：
						<input id="beginTime_" type="text" name="beginTimeStr" value="${order.beginTimeStr}"
							readonly="readonly" class="text_1 va_mid"
							onclick="WdatePicker({el:'beginTime_',dateFmt:'yyyy-MM-dd HH:mm:ss'})" style="width: 150px;"/>
						<img src="${ctx}/common/images/time.gif" style="vertical-align: middle"
							onclick="WdatePicker({el:'beginTime_',dateFmt:'yyyy-MM-dd HH:mm:ss'})" />
					</td>
					<td>
						&nbsp;下单结束时间：
						<input id="endTime_" type="text" name="endTimeStr" value="${order.endTimeStr}"
							readonly="readonly" class="text_1 va_mid"
							onclick="WdatePicker({el:'endTime_',dateFmt:'yyyy-MM-dd HH:mm:ss'})" style="width: 150px;"/>
						<img src="${ctx}/common/images/time.gif" style="vertical-align: middle"
							onclick="WdatePicker({el:'endTime_',dateFmt:'yyyy-MM-dd HH:mm:ss'})" />
					</td>
					<td width="25%">
						&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
						<a class="sexybutton" onclick="submitQuery();return false;"><span><span>查询</span></span></a>
						<a class="sexybutton" onclick="resetQuery();"><span><span>清空</span></span></a>
						<%-- 
						<span class="fillet_btn_01 mr20">
							<span class="fillet_btn_01_left" onclick="exportData_();return false;">导出</span>
						</span>
						<input type="hidden" id="exportParams_" name="exportParams" value="${exportParams}" />
						--%>
					</td>
				</tr>
				-->
        	</table>
        	</form>
        </div>
        <!--searchForm 结束-->
        
        <!--CmsSiteList 开始-->
        <div class="eXtremeTable">
            <ec:table tableId="OrderList" items="list" method="post" var="m" action=""
			imagePath="${ctx}/images/table/*.gif" width="98%" rowsDisplayed="10" view="compact"
			filterRowsCallback="limit" sortRowsCallback="limit" retrieveRowsCallback="limit">
			<ec:row>
				<ec:column headerCell="selectAll" alias="ckids" filterable="false"
					width="1%" sortable="false">
					<input type="checkbox" name="ckids" value="${m.orderId}" />
				</ec:column>
				<ec:column title="订单ID" property="orderId"></ec:column>
				<ec:column title="订单编号" property="orderSn"></ec:column>
				<ec:column title="服务类型" property="serviceType" filterable="false">
					<c:if test="${m.serviceType eq '1'}">安装</c:if>
					<c:if test="${m.serviceType eq '2'}">维修</c:if>
					<c:if test="${m.serviceType eq '3'}">保养</c:if>
					<c:if test="${m.serviceType eq '4'}">测量</font></c:if>
					<c:if test="${m.serviceType eq '5'}">咨询</c:if>
				</ec:column>
				<ec:column title="会员姓名" property="menName"></ec:column>
				<ec:column title="安装工姓名" property="workerName"></ec:column>
				<ec:column title="手机" property="menMobile"></ec:column>
				<%-- 
				<ec:column title="订单总额" property="totalPoint" filterable="false">
					<c:if test="${m.payType eq '2'}">
						${m.totalPoint}积分
					</c:if>
					<c:if test="${m.payType eq '3'}">
						￥${m.totalPrice}
					</c:if>
				</ec:column>
				--%>
				
				<ec:column title="订单状态" property="orderStatus" filterable="false">
					<c:if test="${m.orderStatus eq '1'}"><font color="red">待接单</font></c:if>
					<c:if test="${m.orderStatus eq '2'}">已接单</c:if>
					<c:if test="${m.orderStatus eq '3'}">已确认</c:if>
					<c:if test="${m.orderStatus eq '4'}"><font color="green">已完成</font></c:if>
					<c:if test="${m.orderStatus eq '5'}"><font color="#999">已取消</font></c:if>
				</ec:column>
				<%-- 
				<ec:column title="支付方式" property="payType" filterable="false">
					<c:if test="${m.payType eq '1'}">余额支付</c:if>
					<c:if test="${m.payType eq '2'}">积分支付</c:if>
					<c:if test="${m.payType eq '3'}">微信支付</c:if>
				</ec:column>
				<ec:column title="支付状态" property="payStatus" filterable="false">
					<c:if test="${m.payStatus eq '0'}"><font color="red">未支付</font></c:if>
					<c:if test="${m.payStatus eq '1'}"><font color="green">已支付</font></c:if>
				</ec:column>
				<ec:column title="配送状态" property="shippingPrice" filterable="false">
					<c:if test="${m.shippingStatus eq '0'}"><font color="red">未配送</font></c:if>
					<c:if test="${m.shippingStatus eq '1'}">已配送</c:if>
					<c:if test="${m.shippingStatus eq '2'}"><font color="green">已送达</font></c:if>
				</ec:column>
				--%>
				<ec:column title="下单时间" property="orderTimeStr" filterable="false"></ec:column>
				<ec:column title="操作" property="EEE" sortable="false"
					filterable="false" width="28%">
					<a class="sexybutton" href="${ctx}/pub/order/beforeView.do?orderId=${m.orderId}"><span><span>查看</span></span></a>
				</ec:column>
			</ec:row>
		</ec:table>
        </div>
    	<!--CmsSiteList 结束-->
    	
    </div>
    <!--主体 结束-->
</div>

<iframe name="hideframe" id="hideframe" width="0" height="0"></iframe>



</body>
</html>
<script type="text/javascript">
//动态加载弹出框Iframe内容
function AddConfigIframe(url,htmlId){
	jQuery("#"+htmlId).attr("src",url);
}	
</script>
