<%@ page language="java" pageEncoding="UTF-8" isELIgnored="false"%>
<%@ taglib prefix="c" uri="/WEB-INF/tld/c.tld" %>
<c:set var="ctx" value="${pageContext.request.contextPath}" />

<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1" />
	<meta name="viewport" content="width=device-width, initial-scale=1.0, user-scalable=no"/>
	<title>惠达</title>
	<link rel="stylesheet" href="${ctx}/wap/html/css/Basc.css" />
	<link rel="stylesheet" href="${ctx}/wap/html/css/demo.css" />
	
  	<!-- 公用JS|CSS-->
	<link rel="stylesheet" href="${ctx}/wap/css/style.css">
	<link href="${ctx}/wap/css/cate.css" rel="stylesheet" type="text/css" />
	<link href="${ctx}/wap/css/iscroll.css" rel="stylesheet" type="text/css" />
	<script type="text/javascript" src="${ctx}/common/js/jquery-1.4.4.min.js" ></script>
	
	<!-- 省市区(县)三级联动 -->
	<script type="text/javascript" src="${ctx}/common/js/pccs.js" ></script>
	
	<style type="text/css" > 
		.qrddxxt table td input{width:170px; height:25px; line-height:25px; border:1px solid #e1e1e1;}
		.aabbcc input{border-radius:5px; width:30%; font-size:16px; margin:5px 1%;}
		.qrddxxf ul li{height:35px; line-height:35px; font-size:16px; }
		.bg{background: url(${ctx}/wap/userCenter/images/go.png) no-repeat 98% 13px;}
	</style>
	
	<script type="text/javascript">
		  function init(){
		  		document.getElementById("payType").value="2"; 
		  }
		  
		  //保存订单
		  function saveOrder(serviceType,firstCate,firstCateName,secondCate,secondCateName)
		  {
	  			var addressId = document.getElementById("addressValue");
	  			if(addressId==null || addressId.value==''){
		  			alert("请填写地址!");
				    return;
		  		}
		  		
		  		var payType = "";
		  		if(serviceType=='1'){
		  			var payTypeObj = document.getElementById("payType");
		  			payType = payTypeObj.value;
		  		}
		  		
		  		var orderDesc = document.getElementById("orderDesc");
	  			
		  		
		  		//提交订单前检查是否满足条件
				$.ajax({
					 type:"post",
					 dataType:"html",
					 url:"${ctx}/pub/order/checkOrder.do",
					 data:"type=wap",
					 async:false,
					 complete: function (XMLHttpRequest,textStatus) {
					    if(XMLHttpRequest.responseText!=""){
		                    if(XMLHttpRequest.responseText==0){
						 		window.location.href="${ctx}/pub/order/saveOrder.do?type=wap&addressId="+addressId.value+"&serviceType="+serviceType+"&fcCode="+firstCate+"&fcName="+firstCateName+"&scCode="+secondCate+"&scName="+secondCateName+"&payType="+payType+"&orderDesc="+orderDesc.value;
						 	}else if(XMLHttpRequest.responseText==5){
						 		alert("您的积分不足，请先充值或选择微信支付！");
						 	}else if(XMLHttpRequest.responseText==4){
						 		alert("请先登录微信！");
						 	}else{
						 		alert(XMLHttpRequest.responseText);
						 	}
		                }
					 }
				});
		  }
		  
		  //点击新增地址按钮
		  function addNewAddressInit()
		  {
			  	$("#addAddress").show();
			  	$("#addNewButton").hide();
			  	$("#saveNewButton").show();
			  	
        		setupPccs(); //加载 省市区(县)三级联动
		  }
		  
		  //点击取消地址按钮
		  function cancelNewAddressInit()
		  {
			  	$("#addAddress").hide();
			  	$("#addNewButton").show();
			  	$("#saveNewButton").hide();
		  }
		  
		  
		  //选择地址事件
		  function changeSelectAddress(id){
		  		var selectAddressDivs = document.getElementsByName("selectAddressDiv");
				for(var i=0;i<selectAddressDivs.length;i++)
				{
					selectAddressDivs[i].className = 'qrddxxo_t';
					selectAddressDivs[i].style.cssText='cursor:pointer;background-color: #ffffff';
				}
				var selectAddress = document.getElementById('selectAddress_'+id);
		  		selectAddress.className = 'qrddxxo_o';
		  		selectAddress.style.cssText = 'cursor:pointer;background-color: #E5F9F1';
		  		
		  		//赋值给隐藏字段
		  		var addressValue = document.getElementById("addressValue");
		  		addressValue.value = id;
		  }
		  
		  //保存新地址
		  function saveNewAddress(id,act)
		  {
			  	var consignee = document.getElementById('consignee');
			  	var mobile = document.getElementById('mobile');
			  	var province = document.getElementById('province');
			  	var city = document.getElementById('city');
			  	var county = document.getElementById('county');
			  	var street = document.getElementById('street');
			  	
			  	if(act=='add' || act=='update'){
					if(consignee.value == ''){
					 	alert("收货人姓名必须填写!");
					 	consignee.focus();
			    		return;
					}
					var reg_consignee = new RegExp("^[a-zA-Z\u4e00-\u9fa5]+$");
			        if(!reg_consignee.test(consignee.value))//汉字、字母
			        {
			            alert('请输入有效的姓名！');
			           	consignee.focus();
			            return false;
			        }
			        
					if(mobile.value == ''){
					 	alert("电话必须填写!");
					 	mobile.focus();
			    		return;
					}
					
					if(!/^1\d{10}$/g.test(mobile.value)){
						alert("手机号码不合法!");
						mobile.focus();
						return false;
					}
					
					if(province.value == '省份'){
					 	alert("必须选择省份!");
					 	province.focus();
			    		return;
					}
					//var reg_mobile = new RegExp("^((13[0-9])|(15[^4,\\D])|(18[0,5-9]))\\d{8}$");
			        //if(!reg_mobile.test(mobile.value))
			        //{
			        //    alert('请输入有效的手机号码！');
			        //   	mobile.focus();
			        //    return false;
			        //}
			        
					if(street.value == ''){
					 	alert("地址必须填写!");
					 	street.focus();
			    		return;
					}
					var reg_street = new RegExp("^[0-9a-zA-Z\u4e00-\u9fa5]+$");
			        if(!reg_street.test(street.value))//汉字、字母、数字
			        {
			            alert('请输入有效的地址！');
			           	street.focus();
			            return false;
			        }
			        //if(!street.value.match(/([0-9a-zA-Z\u4e00-\u9fa5]+)区([0-9a-zA-Z\u4e00-\u9fa5]+)路/))
			        //{
			        //    alert('请输入详细的收货人地址，必须包含xx区xx路！');
			        //   	street.focus();
			        //    return false;
			        //}
			        
			  	}
			  	
			  	if(act=='del'){
					if(!confirm("确定要删除吗？"))
 					{
 						return;
 					}
			  	}
				
				$.ajax({
					 type:"post",
					 dataType:"json",
					 url:"${ctx}/pub/addr/saveAddress.do",
					 data:"type=wap&consignee="+consignee.value+"&mobile="+mobile.value+"&province="+province.value+"&city="+city.value+"&county="+county.value+"&street="+street.value+"&act="+act+"&areaId="+id,
					 async:false,
					 complete: function (XMLHttpRequest,textStatus) {
					     if(XMLHttpRequest.responseText==""){
		                    return false;
		                 }
					     var returnData = eval('[' + XMLHttpRequest.responseText + ']');
		                
				         var DIV = document.getElementById("addressListDiv");
						 DIV.innerHTML="<h4>地址</h4>";
						 
					     $.each(returnData,function(i, n){
						     var html = "";
						     if(i==0){
						     	html +="<input style='display: none;' name='selectAddress' value='"+n.id+"' checked='checked' type='radio' />";
						     	html +="<div class='qrddxxo_o' style='cursor:pointer;background-color: #E5F9F1' name='selectAddressDiv' id='selectAddress_"+n.id+"' onclick=\"changeSelectAddress('"+n.id+"')\">";
						     	html +="<input type='hidden' id='addressValue' value='"+n.id+"'/>";
						     }else{
						     	html +="<input style='display: none;' name='selectAddress' value='"+n.id+"' type='radio' />";
						     	html +="<div class='qrddxxo_t' style='cursor:pointer;' name='selectAddressDiv' id='selectAddress_"+n.id+"' onclick=\"changeSelectAddress('"+n.id+"')\">";
						     }
						     
						     html +="<b>姓名：</b>"+n.consignee;
					         //html +="<a href='javascript:saveNewAddress(\"${addr.id}\",\"update\")' style='margin-left: 60px;'><font color='red'>修改</font></a>&nbsp;&nbsp;";
					         html +="<a href=\"javascript:saveNewAddress('"+n.id+"','del')\" style='margin-left: 150px;'><font color='red'>删除</font></a> ";
					         html +="<br />";
		        
						     html +="<b>电话：</b>"+n.mobile+"<br />";
						     html +="<b>地址：</b>"+n.province+n.city+n.county+n.street+"<br />";
						     html +="</div>";
						     DIV.innerHTML += html;
						     
						     if(i==0){
								changeSelectAddress(n.id);
							 }
					     })
					     
					     $("#addAddress").hide();
			  			 $("#addNewButton").show();
			  			 $("#saveNewButton").hide();
					 }
				});
		  }
		  
		  
		  function init(){
		  		document.getElementById("payType").value="3"; 
		  }
		  //选择支付方式
		  function choosePayMode(type,imageName){
				var selectPayMode2 = document.getElementById('payMode_2');
				var selectPayMode3 = document.getElementById('payMode_3');
				selectPayMode2.style.cssText="cursor:pointer;background-image:url('${ctx}/wap/images/point.png');width:160px; height:40px;";
				selectPayMode3.style.cssText="cursor:pointer;background-image:url('${ctx}/wap/images/weixin.png');width:160px; height:40px;";
				
				var selectPayMode = document.getElementById('payMode_'+type);
				selectPayMode.style.cssText="cursor:pointer;border-color: red;border-width: 2;background-image:url('${ctx}/wap/images/"+imageName+".png');width:160px; height:40px;";
				
				//赋值给隐藏字段
		  		var payType = document.getElementById("payType");
		  		payType.value = type;
		  }
	</script>  
</head>

<body onload="init()">
	<input type="hidden" id="shipFee" />
	<input type="hidden" id="totalFee" />
	
	<header>
	  <ul>
	    <li class="back"><a href="${ctx}/pub/goodCate/secondCates.do?type=wap&serviceType=${requestScope.serviceType}&firstCateCode=${firstCate.code}"><img src="${ctx}/wap/html/images/back.png" width="40" height="50" /></a></li>
	    <li class="logo">订单填写</li>
	    <li class="more">&nbsp;</li>
	  </ul>
	</header>
	
	<div class="qrddxxo" style="margin: 0px 0 0;" id="addressListDiv">
	    <h4>地址</h4>
	    <c:forEach items="${requestScope.addressList}" var="addr" varStatus="status">
			<c:if test="${status.index==0}">
				<input style="display: none;" name="selectAddress" value="${addr.id}" checked="checked" type="radio" />
				<div class="qrddxxo_o" style="cursor:pointer;background-color: #E5F9F1;" name='selectAddressDiv' id="selectAddress_${addr.id}" onclick="changeSelectAddress('${addr.id}')">
				<input type="hidden" id="addressValue" value="${addr.id}"/>
				<script type="text/javascript">
					changeSelectAddress('${addr.id}');
				</script>
			</c:if>
			<c:if test="${status.index!=0}">
				<input style="display: none;" name="selectAddress" value="${addr.id}" type="radio" />
				<div class="qrddxxo_t" style="cursor:pointer;" name='selectAddressDiv' id="selectAddress_${addr.id}" onclick="changeSelectAddress('${addr.id}')">
			</c:if>
		        <b>姓名：</b>${addr.consignee}
		        <!-- <a href="javascript:saveNewAddress('${addr.id}','update')" style="margin-left: 60px;"><font color="red">修改</font></a>&nbsp;&nbsp; -->
		        <a href="javascript:saveNewAddress('${addr.id}','del')" style="margin-left: 150px;"><font color="red">删除</font></a>
		        <br />
		        <b>电话：</b>${addr.mobile}<br />
		        <b>地址：</b>${addr.province}${addr.city}${addr.county}${addr.street}<br />
		    </div>
	    </c:forEach>
	</div>
	<ul class="qrddxxt" style="display: none;" id="addAddress">
				<table width="100%" border="0" cellspacing="0" cellpadding="0">
					<tr>
						<td width="28%" style="text-align:right;height:32px;"><font size="2">姓名：</font></td>
						<td width="72%">
							<input type="text" name="consignee" id="consignee" class="input" style="width:210px;border: 1px solid #e1e1e1;height: 30px;" /><font color="red">*</font>
						</td>
					</tr>
					<tr>
						<td  style="text-align:right;height:32px;"><font size="2">电话：</font></td>
						<td>
							<input type="text" name="mobile" id="mobile" class="input" style="width:210px;border: 1px solid #e1e1e1;height: 30px;" 
								onkeyup="this.value=this.value.replace(/[^0-9]/gi,''); "
								onafterpaste="this.value=this.value.replace(/[^0-9]/gi,'')" />
							<font color="red">*</font>
						</td>
					</tr>
					<tr style="margin-top: 5px;">
						<td style="text-align:right;height:32px;"><font size="2">地址：</font></td>
						<td>
							<select name="province" id="province"></select>
				            <select name="city" id="city"></select>
				            <select name="county" id="county"></select><br/>
							<textarea name="street" id="street" style="width:210px;border: 1px solid #e1e1e1;height: 50px;" class="input" cols="20" rows="2"></textarea><font color="red">*</font>
							<br><font size="2" color="red">地址格式：**路**号</font> 
						</td>
					</tr>
					<tr>
						<td height="10"></td>
					</tr>
				</table>
	</ul>
	
	<ul class="qrddxxt" id="addNewButton">
	<li><input class="submit" style="padding: 5px 11px;" type="button" name="newAdd" id="newAdd" value="新增地址" onclick="addNewAddressInit()"/></li>		
	</ul>
	<ul class="qrddxxt" id="saveNewButton" style="display: none;">
	<li style="margin-left: 10px;">
		<div class="aabbcc">
		<input class="submit" style="padding: 2px 11px;" type="button" name="newAdd" id="newAdd" value="保存" onclick="saveNewAddress('','add')"/>
		<input class="submit" style="padding: 2px 11px;" type="button" name="newCancel" id="newCancel" value="取消" onclick="cancelNewAddressInit()"/>
		</div>
	</li>		
	</ul>

	<div class="qrddxxf">
	    <h4 style="height:5px;padding:0px;">&nbsp;</h4>
	    <ul>
	        <li style="line-height: 11px;">描述<span style="float:right">
	        	<input name="orderDesc" class="px" id="orderDesc" value="" style="margin: -5px;width: 250px;" maxlength="20"></span></li>
	        <li>服务时间<span style="float:right">师傅跟我确认</span></li>
	    </ul>
	</div>
	
	<div class="qrddxxf">
	    <h4 style="height:5px;padding:0px;">&nbsp;</h4>
	    <ul>
	        <li>服务类型<span style="float:right">
		        <c:if test="${serviceType=='1'}">预约安装</c:if>
		        <c:if test="${serviceType=='2'}">预约维修</c:if>
		        <c:if test="${serviceType=='3'}">预约保养</c:if>
		        <c:if test="${serviceType=='4'}">预约测量</c:if>
		        <c:if test="${serviceType=='5'}">预约咨询</c:if>
	        </span></li>
	        <!-- <li class="bg">产品类型<span style="float:right">${firstCate.name}</span></li> -->
	        <li>产品类型<span style="float:right">${firstCate.name}</span></li>
	        <li>产品型号<span style="float:right">${secondCate.name}</span></li>
	    </ul>
	</div>
	
	<c:if test="${serviceType=='1'}">
	<div class="qrddxxf">
	    <h4>支付方式</h4>
	    <ul>
	        <!-- <li><input type="submit" name="payMode" id="payMode_2" value="" onclick="choosePayMode('2','point')"  style="cursor:pointer;border-color: red;border-width: 2;background-image:url('${ctx}/wap/images/point.png');width:160px; height:40px;" checked="checked" /></li> -->
	        <li><input type="submit" name="payMode" id="payMode_3" value="" onclick="choosePayMode('3','weixin')" style="cursor:pointer;border-color: red;border-width: 2;background-image:url('${ctx}/wap/images/weixin.png');width:160px; height:40px;" checked="checked"/></li>
	        <input type="hidden" id="payType" value="3"/>
	    </ul>
	</div>
	</c:if>
	
	<div class="qrddxxf">
	    <h4 style="height:5px;padding:0px;">&nbsp;</h4>
	    <ul>
	        <li>服务费用<span style="float:right">
	        <c:if test="${serviceType=='1'}">
		    	380元（收费说明）
		    </c:if>
		    <c:if test="${serviceType!='1'}">
		    	0元（收费说明）
		    </c:if>
	        </span></li>
	    </ul>
	</div>
	
	<div class="qrddxxf">
	    <h4 style="height:5px;padding:0px;">&nbsp;</h4>
	</div>
	
	<div class="mdxqh">
	    <c:if test="${serviceType=='1'}">
	    	<input class="submit" type="button" value="立即预约，去支付" onclick="saveOrder('${serviceType}','${firstCate.code}','${firstCate.name}','${secondCate.code}','${secondCate.name}')"/>
	    </c:if>
	    <c:if test="${serviceType!='1'}">
	    	<input class="submit" type="button" value="立即预约" onclick="saveOrder('${serviceType}','${firstCate.code}','${firstCate.name}','${secondCate.code}','${secondCate.name}')"/>
	    </c:if>
	</div>
	
	<table height="180px;"><tr><td >&nbsp;</td></tr></table>
	
	<!-- 公用底部 -->
	<jsp:include page="public/foot.jsp" flush="false">
		<jsp:param name="menu" value="fw" />
	</jsp:include>
	
</body>
</html>