<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="/WEB-INF/tld/extremecomponents.tld" prefix="ec"%>
<%@ taglib uri="/WEB-INF/tld/c.tld" prefix="c"%>
<c:set var="ctx" value="${pageContext.request.contextPath}" />
<HTML>
<HEAD>
	<TITLE>后台管理系统</TITLE>
	<meta http-equiv="content-type" content="text/html; charset=UTF-8">
	<meta http-equiv="X-UA-Compatible" content="IE=EmulateIE7" />

	<link href="${ctx}/sys/css/public.css" rel="stylesheet" type="text/css">
	<link href="${ctx}/sys/css/style.css" rel="stylesheet" type="text/css">
	<link rel="stylesheet" type="text/css" href="${ctx}/common/css/showhide.css"/>

	<script type="text/javascript" src="${ctx}/common/js/calendar/WdatePicker.js"></script>
	<script type="text/javascript" src="${ctx}/js/mootools-release-1.11.js"></script>
	<script type="text/javascript" src="${ctx}/common/js/jquery-1.4.4.min.js" ></script>
	<script type="text/javascript" src="${ctx}/common/js/showhide.js"></script>

	<script type="text/javascript">
	var currUserLevel = '${currUserLevel}';//当前用户等级
	var currLocationId = '${currLocationId}';//当前用户部门ID
	var provinceId = '${order.province}';//省份ID
	var cityId = '${order.city}';//城市ID
	var teamId = '${order.team}';//队ID
	$(function() {
	    //根据用户等级初始化部门选择框
        var tdHtml = "";
        if(currUserLevel == "1"){ //管理员
            tdHtml = "<td>&emsp;&emsp;省份：<select id='province_select' name='province' class='select_2 va_mid' style='width: 100px;' onchange='provinceSelect()'><option value=''>-请选择-</option></select></td>" +
                "<td>&emsp;&emsp;&emsp;&emsp;地市：<select id='city_select' name='city' class='select_2 va_mid' style='width: 100px;' onchange='citySelect()'><option value=''>-请选择-</option></select></td></td>" +
                "<td>&emsp;&emsp;&emsp;&emsp;工队：<select id='team_select' name='team' class='select_2 va_mid' style='width: 100px;'><option value=''>-请选择-</option></select></td></td>";
        }else if(currUserLevel == "2"){
            tdHtml = "<td>&emsp;&emsp;&emsp;&emsp;城市：<select id='city_select' name='city' class='select_2 va_mid' style='width: 100px;' onchange='citySelect()'><option value=''>-请选择-</option></select></td>" +
                "<td>&emsp;&emsp;&emsp;&emsp;工队：<select id='team_select' name='team' class='select_2 va_mid' style='width: 100px;'><option value=''>-请选择-</option></select></td></td>";
        }else if(currUserLevel == "3"){
            tdHtml = "<td>&emsp;&emsp;&emsp;&emsp;工队：<select id='team_select' name='team' class='select_2 va_mid' style='width: 100px;'><option value=''>-请选择-</option></select></td>" ;
        }
        $("#location_tr").html(tdHtml);


		if(provinceId != "" || cityId !="" || teamId !=""){//判断查询时是否有部门条件
            //有部门查询条件，需初始化已选择部门的对应兄弟部门列表
			if(provinceId != ""){
                var optionHtml = getBrotherLocation(provinceId);
                $("#province_select").html(optionHtml);
                if(cityId == ""){//当只选择的省份这一级时，需要把当前选择省份对应的城市列表也初始化
                    var optionHtml = getChildLocation(provinceId);
                    $("#city_select").html(optionHtml);
				}
			}
			if(cityId != ""){
                var optionHtml = getBrotherLocation(cityId);
                $("#city_select").html(optionHtml);
                if(teamId == ""){//当只选择的城市这一级时，需要把当前选择城市对应的工队列表也初始化
                    var optionHtml = getChildLocation(cityId);
                    $("#team_select").html(optionHtml);
                }
			}
			if(teamId != ""){
                var optionHtml = getBrotherLocation(teamId);
                $("#team_select").html(optionHtml);
			}
		}else{
		    //无部门查询条件，只需初始化当前用户部门的子部门列表
            if(currUserLevel == "1"){ //管理员
				var optionHtml = getChildLocation(currLocationId);
				$("#province_select").html(optionHtml);
            }else if(currUserLevel == "2"){//省
                var optionHtml = getChildLocation(currLocationId);
                $("#city_select").html(optionHtml);
            }else if(currUserLevel == "3"){//市
                var optionHtml = getChildLocation(currLocationId);
                $("#team_select").html(optionHtml);
            }
		}
	});

    //获取兄弟部门列表选择项
	function getBrotherLocation(locationId) {
        var optionHtml = optionHtml = "<option value=''>-请选择-</option>";
        $.ajax({
            url:"${ctx}/pub/order/getBrotherLocation.do",
            data:{locationId:locationId},
            type:"post",
            dataType:"json",
            async:false,
            success: function(data){
				var returnData = data.pageData;
				$.each(returnData, function (idx, obj) {
					if(obj.id == locationId){
						optionHtml += "<option value='" + obj.id + "' selected='selected'>" + obj.name + "</option>";
					}else{
						optionHtml += "<option value='" + obj.id + "'>" + obj.name + "</option>";
					}
				});
            }
        });
        return optionHtml;
    }

    //获取子部门列表选择项
    function getChildLocation(locationId) {
        var optionHtml = "<option value=''>-请选择-</option>";
        $.ajax({
            url:"${ctx}/pub/order/getChildLocation.do",
            data:{locationId:locationId},
            type:"post",
            dataType:"json",
            async:false,
            success: function(data){
                var returnData = data.pageData;
				$.each(returnData, function (idx, obj) {
						optionHtml += "<option value='" + obj.id + "'>" + obj.name + "</option>";
				});
            }
        });
        return optionHtml;

    }

    //点击省份选择项事件
    function provinceSelect(){
	    var selectLocationId = $('#province_select option:selected') .val();
		if(selectLocationId == ""){
		    $("#city_select").html("<option value=''>-请选择-</option>");
		    $("#team_select").html("<option value=''>-请选择-</option>");
		}else{
            var optionHtml = getChildLocation(selectLocationId);
            $("#city_select").html(optionHtml);
            $("#team_select").html("<option value=''>-请选择-</option>");
		}
	}

    //点击地市选择项事件
    function citySelect(){
        var selectLocationId = $('#city_select option:selected') .val();
        if(selectLocationId == ""){
            $("#team_select").html("<option value=''>-请选择-</option>");
        }else{
            var optionHtml = getChildLocation(selectLocationId);
            $("#team_select").html(optionHtml);
        }
    }





	function submitQuery(){
		document.getElementById("searchForm").submit();
	};
	function resetQuery(){
		document.getElementById("searchForm").reset();
	};

    function openDiv(orderId,payType) {
        document.getElementById("orderId").value=orderId;
        //弹出框显示客户已选择的支付类型
		if(payType == "1"){
		   $("#customerPayType").text("一次性支付");
		}else{
            $("#customerPayType").text("分期支付");
		}
        show('cover1','pop_sh','');
    }

    function openDiv2(orderId) {
        document.getElementById("orderId2").value=orderId;
        show('cover2','pop_sh2','');
    }

    function openDiv3(orderId) {
        document.getElementById("orderId3").value=orderId;
        show('cover3','pop_sh3','');
    }

    function updateOrder() {
        var orderId = document.getElementById("orderId");
        var workerId = document.getElementById("workerId");
        var payType = document.getElementById("payType_id");
        var totalPrice = document.getElementById("totalPrice");
        var cost = document.getElementById("cost");
        var cycleInit = document.getElementById("cycleInit");
        if(workerId.value == ''){
            alert("请选择工人");
            workerId.focus();
            return;
        }
        if(payType.value == ''){
            alert("请选择支付类型");
            payType.focus();
            return;
        }
        if(totalPrice.value == ''){
            alert("订单金额不能为空");
            totalPrice.focus();
            return;
        }
        if(cost.value == ''){
            alert("成本不能为空");
            cost.focus();
            return;
        }
        if(cycleInit.value == ''){
            alert("周期天数不能为空");
            cycleInit.focus();
            return;
        }
        hide('cover1','pop_sh','');
        document.updateOrderForm.submit();

    }

    function addCycle() {
        var cycleAdd = document.getElementById("cycleAdd");
        if(cycleAdd.value == ''){
            alert("周期天数不能为空");
            cycleAdd.focus();
            return;
        }
        hide('cover2','pop_sh2','');
        document.orderCycleAddForm.submit();

    }

    function costEdit() {
        var editCost = document.getElementById("editCost");
        if(editCost.value == ''){
            alert("成本不能为空");
            editCost.focus();
            return;
        }
        hide('cover3','pop_sh3','');
        document.orderEditCostForm.submit();

    }

    function cancelOrder(orderId) {
		if(confirm("确定取消订单？")){
			window.location.href="${ctx}/pub/order/cancelOrder.do?orderId="+orderId;
		}
    }

	</script>
<body class="overfwidth">

<div class="barnavtop">您所在的位置：订单管理 > 订单列表</div>
<div id="workspace">
	<!--主体 开始-->
    <div id="container">    
        <!--searchForm 开始-->
        
        <div class="select_box">
        	<form id="searchForm" action="${ctx}/pub/order/queryList.do" method="post" onsubmit="">
			<table border="0" cellspacing="0" cellpadding="5" class="tb_ie6" style="font-size: 12px; width: 100%">
				<tr bgcolor="#f7f7f7">
					<td width="25%">
						订单编号：
						<input class="text_1 va_mid" type="text" id="orderSn"
							name="orderSn" maxlength="32" value="${order.orderSn}" style="width: 150px;"/>
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

				</tr>
				<tr bgcolor="#f7f7f7">
				<td>
					服务类型：
					<select id="serviceType" name="serviceType" class="select_2 va_mid" style="width: 100px;">
						<option value="" <c:if test = "${order.serviceType eq ''}">selected='selected'</c:if> >-请选择-</option>
						<option value="1" <c:if test = "${order.serviceType eq '1'}">selected='selected'</c:if> >安装</option>
						<option value="2" <c:if test = "${order.serviceType eq '2'}">selected='selected'</c:if> >维修</option>
						<option value="3" <c:if test = "${order.serviceType eq '3'}">selected='selected'</c:if> >保养</option>
						<option value="3" <c:if test = "${order.serviceType eq '4'}">selected='selected'</c:if> >测量</option>
						<option value="3" <c:if test = "${order.serviceType eq '5'}">selected='selected'</c:if> >咨询</option>
					</select>
				</td>
				<td>
					订单状态：
					<select id="orderStatus" name="orderStatus" class="select_2 va_mid" style="width: 100px;">
						<option value="" <c:if test = "${order.orderStatus eq ''}">selected='selected'</c:if> >-请选择-</option>
						<option value="1" <c:if test = "${order.orderStatus eq '1'}">selected='selected'</c:if> >待派单</option>
						<option value="2" <c:if test = "${order.orderStatus eq '2'}">selected='selected'</c:if> >已派单</option>
						<option value="3" <c:if test = "${order.orderStatus eq '3'}">selected='selected'</c:if> >已确认时间</option>
						<option value="4" <c:if test = "${order.orderStatus eq '4'}">selected='selected'</c:if> >已上门</option>
						<option value="5" <c:if test = "${order.orderStatus eq '5'}">selected='selected'</c:if> >已开始施工</option>
						<option value="6" <c:if test = "${order.orderStatus eq '6'}">selected='selected'</c:if> >已完成施工</option>
						<option value="7" <c:if test = "${order.orderStatus eq '7'}">selected='selected'</c:if> >已评价</option>
						<option value="8" <c:if test = "${order.orderStatus eq '8'}">selected='selected'</c:if> >已取消</option>
					</select>
				</td>

				<td>
					&emsp;&emsp;支付状态：
					<select id="payStatus" name="payStatus" class="select_2 va_mid" style="width: 100px;">
						<option value="" <c:if test = "${order.payStatus eq ''}">selected='selected'</c:if> >-请选择-</option>
						<option value="0" <c:if test = "${order.payStatus eq '0'}">selected='selected'</c:if> >已一次性支付</option>
						<option value="1" <c:if test = "${order.payStatus eq '1'}">selected='selected'</c:if> >已支付定金</option>
						<option value="2" <c:if test = "${order.payStatus eq '2'}">selected='selected'</c:if> >已支付中期</option>
						<option value="3" <c:if test = "${order.payStatus eq '3'}">selected='selected'</c:if> >已支付尾款</option>
					</select>
				</td>

				</tr>
				<tr id="location_tr" bgcolor="#f7f7f7">
				</tr>
				<tr bgcolor="#f7f7f7">
					<td>&emsp;</td>
					<td>&emsp;</td>
					<td align="center">
						&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
						<a class="sexybutton" onclick="submitQuery();return false;"><span><span>查询</span></span></a>
						<a class="sexybutton" onclick="resetQuery();"><span><span>清空</span></span></a>
						<%--<span class="fillet_btn_01 mr20">--%>
						<%--<span class="fillet_btn_01_left" onclick="exportData_();return false;">导出</span>--%>
						<%--</span>--%>
						<%--<input type="hidden" id="exportParams_" name="exportParams" value="${exportParams}" />--%>
					</td>
				</tr>

        	</table>
        	</form>
        </div>
        <!--searchForm 结束-->

		<!--统计开始 -->
		<table border="1" style="margin-top: 10px; margin-bottom: 2px;border-collapse:collapse;" width="100%">
			<tr>
				<th>订单总金额</th>
				<th>订单总成本</th>
				<th>订单总利润</th>
			</tr>
			<tr>
				<td align="center">${statistics.sumTotalPrice}</td>
				<td align="center">${statistics.sumCost}</td>
				<td align="center">${statistics.sumProfit}</td>
			</tr>
		</table>
		<!--统计结束 -->

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
				<%--
				<ec:column title="订单ID" property="orderId"></ec:column>
				--%>
				<ec:column title="订单编号" property="orderSn" width="7%"></ec:column>
				<ec:column title="区域" property="locationName" width="7%"></ec:column>
				<ec:column title="安装工姓名" property="workerName" width="10%"></ec:column>
				<ec:column title="服务类型" property="serviceType" filterable="false" width="5%">
					<c:if test="${m.serviceType eq '1'}">安装</c:if>
					<c:if test="${m.serviceType eq '2'}">维修</c:if>
					<c:if test="${m.serviceType eq '3'}">保养</c:if>
					<c:if test="${m.serviceType eq '4'}">测量</font></c:if>
					<c:if test="${m.serviceType eq '5'}">咨询</c:if>
				</ec:column>
				<ec:column title="会员姓名" property="menName" width="7%"></ec:column>
				<%--<ec:column title="手机" property="menMobile" width="7%"></ec:column>--%>
				<ec:column title="订单总额" property="totalPrice" filterable="false" width="7%"></ec:column>
				<ec:column title="成本" property="cost" filterable="false" width="7%"></ec:column>
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
				
				<ec:column title="订单状态" property="orderStatus" filterable="false" width="7%">
					<c:if test="${m.orderStatus eq '1'}"><font color="blue">待派单</font></c:if>
					<c:if test="${m.orderStatus eq '2'}">已派单</c:if>
					<c:if test="${m.orderStatus eq '3'}">已确认时间</c:if>
					<c:if test="${m.orderStatus eq '4'}">已上门</c:if>
					<c:if test="${m.orderStatus eq '5' && m.payStatus eq '0'}"><font color="red">待支付</font></c:if>
					<c:if test="${m.orderStatus eq '5' && m.payStatus eq '1'}"><font color="red">已支付</font></c:if>
					<c:if test="${m.orderStatus eq '6'}">已完成施工</c:if>
					<c:if test="${m.orderStatus eq '7'}"><font color="#999">已评价</font></c:if>
					<c:if test="${m.orderStatus eq '8'}"><font color="#999">已取消</font></c:if>
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
				<ec:column title="下单时间" property="orderTimeStr" filterable="false" width="8%"></ec:column>
				<ec:column title="计划工期(天)" property="cycleInit" filterable="false" width="7%"></ec:column>
				<ec:column title="新增工期(天)" property="cycleAdd" filterable="false" width="7%"></ec:column>
				<ec:column title="项目进度" property="projectProgress" filterable="false" width="7%"></ec:column>
				<ec:column title="操作" property="EEE" sortable="false" filterable="false" width="24%">
					<a class="sexybutton" href="${ctx}/pub/order/beforeView.do?orderId=${m.orderId}"><span><span>查看</span></span></a>
					<c:if test="${m.orderStatus ne '1'}">
						<a class="sexybutton" href="${ctx}/pub/order/beforeUpdate.do?orderId=${m.orderId}"><span><span>修改</span></span></a>
					</c:if>
					<c:if test="${m.orderStatus eq '1'}">
						<a class="sexybutton" href="javascript:void(0)" onclick="openDiv('${m.orderId}','${m.payType}')">
							<span><span>派单</span></span>
						</a>
					</c:if>
					<c:if test="${m.orderStatus eq '2' || m.orderStatus eq '3'}">
						<a class="sexybutton" href="javascript:void(0)" onclick="openDiv('${m.orderId }','${m.payType}')">
							<span><span>重新派单</span></span>
						</a>
					</c:if>
					<%--<c:if test="${m.orderStatus eq '1' || m.orderStatus eq '2' || m.orderStatus eq '3' || m.orderStatus eq '4'}">--%>
						<%--<a class="sexybutton" href="javascript:void(0)" onclick="cancelOrder('${m.orderId }')">--%>
							<%--<span><span>取消订单</span></span>--%>
						<%--</a>--%>
					<%--</c:if>--%>
					<%--<c:if test="${m.orderStatus eq '2' || m.orderStatus eq '3' || m.orderStatus eq '4'}">--%>
						<%--<a class="sexybutton" href="javascript:void(0)" onclick="openDiv3('${m.orderId }')">--%>
							<%--<span><span>修改成本</span></span>--%>
						<%--</a>--%>
					<%--</c:if>--%>
					<%--<c:if test="${m.orderStatus eq '4'}">--%>
						<%--<a class="sexybutton" href="javascript:void(0)" onclick="openDiv2('${m.orderId }')">--%>
							<%--<span><span>新增工期</span></span>--%>
						<%--</a>--%>
					<%--</c:if>--%>
					<c:if test="${m.orderStatus eq '6'}">
						<a class="sexybutton" href="${ctx}/pub/appraise/appraiseView.do?appraiseId=${m.appraiseId}">
							<span><span>评价详情</span></span>
						</a>
					</c:if>
				</ec:column>
			</ec:row>
		</ec:table>
        </div>
    	<!--CmsSiteList 结束-->

    </div>
    <!--主体 结束-->
</div>

<!--提示弹出层 开始-->
<div id="cover1"></div>
<div class="pop_555" id="pop_sh" style="width: 600px; height: 190px;">
	<div class="pop_555_t" style="width: 600px;">
		<!---pop_555_t-->
		<strong class="fl ml15">派单</strong> <span
			class="fr mr15 pop_close" onclick="hide('cover1','pop_sh');"></span>
	</div>
	<div class="pop_555_m text_c" style="width: 600px; padding-top: 20px">
		<!---pop_555_m-->
		<div id="win_publish_Id">
			<form name="updateOrderForm" class="cmxform" action="${ctx}/pub/order/updateOrderPrice.do" target="hideframe" method="post" >
				<input type="hidden" name="orderId" id="orderId" />
				<table align="center">
					<tr>
						<td align="right">指派给：</td>
						<td align="left">
							<select name="workerId" id="workerId">
								<option value="" >===请选择工人===</option>
								<c:forEach var="a" items="${requestScope.workerList}" varStatus="status">
									<option value="${a.id}"  >${a.realName}</option>
								</c:forEach>
							</select>
						</td>
					</tr>
					<tr>
						<td align="right">客户选择支付类型：</td>
						<td align="left">
							<div id="customerPayType"></div>
						</td>
					</tr>
					<tr>
						<td align="right">支付类型：</td>
						<td align="left">
							<select name="payType" id="payType_id">
								<option value="" >===请选择===</option>
								<option value="1" >一次性支付</option>
								<option value="2" >分期支付</option>
							</select>
						</td>
					</tr>
					<tr>
						<td align="right">订单金额：</td>
						<td align="left">
							<input type="text" name="totalPrice" id="totalPrice" class="bgw" />元
						</td>
					</tr>
					<tr>
						<td align="right">成本：</td>
						<td align="left">
							<input type="text" name="cost" id="cost" class="bgw" />元
						</td>
					</tr>
					<tr>
						<td align="right">工期天数：</td>
						<td align="left">
							<input type="text" name="cycleInit" id="cycleInit" class="bgw" />天
						</td>
					</tr>
				</table>
				<input id="" type="button" value="保存" onclick="updateOrder();" />
			</form>
		</div>
	</div>


</div>
<!--提示弹出层 结束-->
<!--提示弹出层2 开始-->
<div id="cover2"></div>
<div class="pop_555" id="pop_sh2" style="width: 600px; height: 190px;">
	<div class="pop_555_t" style="width: 600px;">
		<!---pop_555_t-->
		<strong class="fl ml15">新增工期</strong> <span
			class="fr mr15 pop_close" onclick="hide('cover2','pop_sh2');"></span>
	</div>
	<div class="pop_555_m text_c" style="width: 600px; padding-top: 20px">
		<!---pop_555_m-->
		<div id="cycle_add_id">
			<form name="orderCycleAddForm" class="cmxform" action="${ctx}/pub/order/orderCycleAdd.do" target="hideframe1" method="post" >
				<input type="hidden" name="orderId" id="orderId2"/>
				<table align="center">
					<tr>
						<td align="right">工期天数：</td>
						<td align="left">
							<input type="text" name="cycleAdd" id="cycleAdd" class="bgw" />天
						</td>
					</tr>
				</table>
				<input type="button" id="orderCycleAdd" value="提交" onclick="addCycle();" />
			</form>
		</div>
	</div>
</div>
<!--提示弹出层2 结束-->

<!--提示弹出层3 开始-->
<div id="cover3"></div>
<div class="pop_555" id="pop_sh3" style="width: 600px; height: 190px;">
	<div class="pop_555_t" style="width: 600px;">
		<!---pop_555_t-->
		<strong class="fl ml15">成本修改</strong> <span
			class="fr mr15 pop_close" onclick="hide('cover3','pop_sh3');"></span>
	</div>
	<div class="pop_555_m text_c" style="width: 600px; padding-top: 20px">
		<!---pop_555_m-->
		<div id="cost_edit_id">
			<form name="orderEditCostForm" class="cmxform" action="${ctx}/pub/order/editCost.do" target="hideframe2" method="post" >
				<input type="hidden" name="orderId" id="orderId3"/>
				<table align="center">
					<tr>
						<td align="right">成本：</td>
						<td align="left">
							<input type="text" name="cost" id="editCost" class="bgw" />元
						</td>
					</tr>
				</table>
				<input type="button" id="orderEditCost" value="提交" onclick="costEdit();" />
			</form>
		</div>
	</div>
</div>
<!--提示弹出层3 结束-->

<iframe name="hideframe" id="hideframe" width="0" height="0"></iframe>
<iframe name="hideframe1" id="hideframe1" width="0" height="0"></iframe>
<iframe name="hideframe2" id="hideframe2" width="0" height="0"></iframe>



</body>
</html>