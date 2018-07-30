<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib uri="/WEB-INF/tld/domain.tld" prefix="domain"%>
<%@ taglib uri="/WEB-INF/tld/c.tld" prefix="c"%>
<c:set var="ctx" value="${pageContext.request.contextPath}" />

<%@ include file="../common/commonHeader.jsp"%>
<HTML xmlns="http://www.w3.org/1999/xhtml">
<HEAD>
		<TITLE>后台管理系统</TITLE>
		<meta http-equiv="content-type" content="text/html; charset=UTF-8">
		<meta http-equiv="X-UA-Compatible" content="IE=EmulateIE7" />
		<link href="${ctx}/sys/css/public.css" rel="stylesheet" type="text/css">
		<link href="${ctx}/sys/css/style.css" rel="stylesheet" type="text/css">
        <script type="text/javascript" src="${ctx}/common/js/calendar/WdatePicker.js"></script>
		<script type="text/javascript" src="${ctx}/common/js/jquery.min.js" charset="gbk"></script>
		<meta name=generator content="MSHTML 8.00.6001.18939">
		<script type="text/javascript">
        var proportion1 = parseFloat('${proportion1}');//定金比例
        var proportion2 = parseFloat('${proportion2}');//中期款比例
        var proportion3 = parseFloat('${proportion3}');//尾款比例
        function recalculatePrice() {
            var totalPrice = $("#totalPrice_id").val();
            var payPrice1 = totalPrice*proportion1;
            var payPrice2 = totalPrice*proportion2;
            var payPrice3 = totalPrice*proportion3;
            $("#payPeice1_id").text(payPrice1);
            $("#payPeice2_id").text(payPrice2);
            $("#payPeice3_id").text(payPrice3);
        }
		function beforeSubmit()
		{
			document.frmApply.submit();
		}
        function cancelOrder(orderId) {
            if(confirm("确定取消订单？")){
                window.location.href="${ctx}/pub/order/cancelOrder.do?orderId="+orderId;
            }
        }
		</script>
<meta name=generator content="MSHTML 8.00.6001.18939">
<body class="overfwidth">
<div id="mainDiv">
<div class="barnavtop">您所在的位置： 会员管理 > 会员修改</div>
	<!--主体 开始-->
	<form name="frmApply" class="cmxform" action="${pageContext.request.contextPath}/pub/order/orderUpdate.do"
			target="hideframe" method="post">
    <div id="container">
    	<!--按钮 开始-->  

        <!--按钮 结束-->  
        <!--框内内容 开始-->
          <div class="editspace">
              <fieldset>
                <!--ol 开始-->
                <ol>
                  <li class="listyle_4">
                    <label class="left pt5">订单编号：</label>
                    <h1 class="cmxformh1"> <span class="cmxformspan">
                        ${m.orderSn}
                    </span></h1>
                  </li>
                    <li class="listyle_4">
                        <label class="left pt5">客户姓名：</label>
                        <h1 class="cmxformh1"> <span class="cmxformspan">
                            ${m.menName}
                        </span></h1>
                    </li>
                    <li class="listyle_4">
                        <label class="left pt5">客户电话：</label>
                        <h1 class="cmxformh1"> <span class="cmxformspan">
                            ${m.menMobile}
                        </span></h1>
                    </li>
                  <li class="listyle_4">
                    <label class="left pt5">服务类型：</label>
                    <h1 class="cmxformh1"> <span class="cmxformspan">
                        <c:if test="${m.serviceType eq '1'}">安装</c:if>
                        <c:if test="${m.serviceType eq '2'}">维修</c:if>
                        <c:if test="${m.serviceType eq '3'}">保养</c:if>
                        <c:if test="${m.serviceType eq '4'}">测量</c:if>
                        <c:if test="${m.serviceType eq '5'}">咨询</c:if>
                    </span></h1>
                  </li>
                    <li class="listyle_4">
                        <label class="left pt5">订单状态：</label>
                        <h1 class="cmxformh1">
                            <span class="cmxformspan">
                                <c:if test="${m.orderStatus eq '1'}"><font color="blue">待派单</font></c:if>
                                <c:if test="${m.orderStatus eq '2'}">已派单</c:if>
                                <c:if test="${m.orderStatus eq '3'}">已确认时间</c:if>
                                <c:if test="${m.orderStatus eq '4'}">已上门</c:if>
                                <c:if test="${m.orderStatus eq '5' && m.payStatus eq '0'}"><font color="red">待支付</font></c:if>
                                <c:if test="${m.orderStatus eq '5' && m.payStatus eq '1'}"><font color="red">已支付</font></c:if>
                                <c:if test="${m.orderStatus eq '6'}">已完成施工</c:if>
                                <c:if test="${m.orderStatus eq '7'}"><font color="#999">已评价</font></c:if>
                                <c:if test="${m.orderStatus eq '8'}"><font color="#999">已取消</font></c:if>
                            </span>
                        </h1>
                    </li>
                  <li class="listyle_4">
                        <label class="left pt5">支付类型：</label>
                        <h1 class="cmxformh1"> <span class="cmxformspan">
                            <%--<select name="payType" id="payType_id">--%>
								<%--<option value="" >===请选择工人===</option>--%>
								<%--<option value="1" <c:if test="${m.payType eq '1'}">selected</c:if>>一次性支付</option>--%>
								<%--<option value="2" <c:if test="${m.payType eq '2'}">selected</c:if>>分期支付</option>--%>
							<%--</select>--%>
                            <c:if test="${m.payType eq '1'}">一次性支付</c:if>
                            <c:if test="${m.payType eq '2'}">分期支付</c:if>
                        </span></h1>
                  </li>
                  <li class="listyle_4">
                    <label class="left pt5">订单总金额：</label>
                    <h1 class="cmxformh1"> <span class="cmxformspan">
                        <%--在已上门状态之前都可以修改订单金额--%>
                        <c:if test="${m.orderStatus == '2' || m.orderStatus == '3' }">
                            <input type="text" id="totalPrice_id" name="totalPrice"  class="bgw" value="${m.totalPrice}" onchange="recalculatePrice()" onkeyup="(this.v=function(){this.value=this.value.replace(/[^\d]/g, '');}).call(this)" onblur="this.v();" placeholder="请输入数字"/>
                        </c:if>
                        <c:if test="${m.orderStatus == '4' || m.orderStatus == '5' || m.orderStatus == '6' || m.orderStatus == '7' || m.orderStatus == '8'}">
                            ${m.totalPrice}
                        </c:if>

                        </span></h1>
                  </li>
                    <c:if test="${m.payType eq '1'}">
                        <li class="listyle_4">
                            <label class="left pt5">订单总金额支付时间：</label>
                            <h1 class="cmxformh1"> <span class="cmxformspan">
                                <c:if test="${m.payTime1Str eq ''}">
                                    <input id="payTime1_" type="text" name="payTime1Str"
                                           readonly="readonly" class="text_1 va_mid"
                                           onclick="WdatePicker({el:'payTime1_',dateFmt:'yyyy-MM-dd HH:mm:ss'})" style="width: 150px;"/>
                                    <img src="${ctx}/common/images/time.gif" style="vertical-align: middle"
                                         onclick="WdatePicker({el:'payTime1_',dateFmt:'yyyy-MM-dd HH:mm:ss'})" />
                                </c:if>
                                <c:if test="${m.payTime1Str ne ''} ">
                                    ${m.payTime1Str}
                                </c:if>
                                </span>
                            </h1>
                        </li>
                    </c:if>
                    <c:if test="${m.payType eq '2'}">
                        <li class="listyle_4">
                            <label class="left pt5">定金金额：</label>
                            <h1 class="cmxformh1"> <span class="cmxformspan" id="payPeice1_id">
                                    ${m.payPrice1}
                            </span></h1>
                        </li>
                        <li class="listyle_4">
                            <label class="left pt5">定金支付时间：</label>
                            <h1 class="cmxformh1"> <span class="cmxformspan">
                                <c:if test="${m.payTime1Str eq ''}">
                                    <input id="payTime1_" type="text" name="payTime1Str"
                                           readonly="readonly" class="text_1 va_mid"
                                           onclick="WdatePicker({el:'payTime1_',dateFmt:'yyyy-MM-dd HH:mm:ss'})" style="width: 150px;"/>
                                    <img src="${ctx}/common/images/time.gif" style="vertical-align: middle"
                                         onclick="WdatePicker({el:'payTime1_',dateFmt:'yyyy-MM-dd HH:mm:ss'})" />
                                </c:if>
                                <c:if test="${m.payTime1Str  ne ''}">
                                    ${m.payTime1Str}
                                </c:if>
                            </span></h1>
                        </li>
                        <li class="listyle_4">
                            <label class="left pt5">中期款金额：</label>
                            <h1 class="cmxformh1"> <span class="cmxformspan" id="payPeice2_id">
                                    ${m.payPrice2}
                            </span></h1>
                        </li>
                        <li class="listyle_4">
                            <label class="left pt5">中期款支付时间：</label>
                            <h1 class="cmxformh1"> <span class="cmxformspan">
                                <c:if test="${m.payTime2Str eq ''}">
                                    <input id="payTime2_" type="text" name="payTime2Str" value="${m.payTime2Str}"
                                           readonly="readonly" class="text_1 va_mid"
                                           onclick="WdatePicker({el:'payTime2_',dateFmt:'yyyy-MM-dd HH:mm:ss'})" style="width: 150px;"/>
                                    <img src="${ctx}/common/images/time.gif" style="vertical-align: middle"
                                         onclick="WdatePicker({el:'payTime2_',dateFmt:'yyyy-MM-dd HH:mm:ss'})" />
                                </c:if>
                                <c:if test="${m.payTime2Str  ne ''}">
                                    ${m.payTime2Str}
                                </c:if>
                            </span></h1>
                        </li>
                        <li class="listyle_4">
                            <label class="left pt5">尾款金额：</label>
                            <h1 class="cmxformh1"> <span class="cmxformspan" id="payPeice3_id">
                                    ${m.payPrice3}
                            </span></h1>
                        </li>
                        <li class="listyle_4">
                            <label class="left pt5">尾款支付时间：</label>
                            <h1 class="cmxformh1"> <span class="cmxformspan">
                                <c:if test="${m.payTime3Str eq ''}">
                                    <input id="payTime3_" type="text" name="payTime3Str" value="${m.payTime3Str}"
                                           readonly="readonly" class="text_1 va_mid"
                                           onclick="WdatePicker({el:'payTime3_',dateFmt:'yyyy-MM-dd HH:mm:ss'})" style="width: 150px;"/>
                                    <img src="${ctx}/common/images/time.gif" style="vertical-align: middle"
                                         onclick="WdatePicker({el:'payTime3_',dateFmt:'yyyy-MM-dd HH:mm:ss'})" />
                                </c:if>
                                <c:if test="${m.payTime3Str  ne ''}">
                                    ${m.payTime3Str}
                                </c:if>
                            </span></h1>
                        </li>
                        <li class="listyle_4">
                            <label class="left pt5">计划工期（天）：</label>
                            <h1 class="cmxformh1"> <span class="cmxformspan">
                                    ${m.cycleInit}
                            </span></h1>
                        </li>
                        <li class="listyle_4">
                            <label class="left pt5">新增工期（天）：</label>
                            <h1 class="cmxformh1"> <span class="cmxformspan">
                                    <input type="text" id="cycleAdd_id" name="cycleAdd"  class="bgw" value="${m.cycleAdd}" onkeyup="(this.v=function(){this.value=this.value.replace(/[^\d]/g, '');}).call(this)" onblur="this.v();" placeholder="请输入数字"/>
                            </span></h1>
                        </li>
                        <li class="listyle_4">
                            <label class="left pt5">成本：</label>
                            <h1 class="cmxformh1"> <span class="cmxformspan">
                                    <input type="text" id="cost_id" name="cost"  class="bgw" value="${m.cost}" onkeyup="(this.v=function(){this.value=this.value.replace(/[^\d]/g, '');}).call(this)" onblur="this.v();" placeholder="请输入数字"/>
                            </span></h1>
                        </li>
                    </c:if>
                </ol>
                <!--ol 结束-->               
              </fieldset>
           
          </div>
      	<div class="toolbar mb10">
        	<a href="#" class="sexybutton" onclick="beforeSubmit();return false"><span><span>保存</span></span></a>    
        	<%--<a href="#" class="sexybutton"  onClick="rbutton()"><span><span>重置 </span></span></a>--%>
        	<a href="#" class="sexybutton" onclick="location.href='${pageContext.request.contextPath}/pub/order/queryList.do'"><span><span>返回</span></span></a>
            <%--已开始施工之前都可以取消订单--%>
            <c:if test="${m.orderStatus eq '1' || m.orderStatus eq '2' || m.orderStatus eq '3' || m.orderStatus eq '4'}">
            <a class="sexybutton" href="javascript:void(0)" onclick="cancelOrder('${m.orderId }')">
            <span><span>取消订单</span></span>
            </a>
            </c:if>
        </div>
		</div>
	</form>
</div>

</body>
</html>
<iframe name="hideframe" id="hideframe" width="0" height="0"></iframe>
