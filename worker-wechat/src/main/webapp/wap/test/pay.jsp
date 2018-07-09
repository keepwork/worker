<%@ page language="java" pageEncoding="UTF-8" isELIgnored="false"%>
<%@ taglib prefix="c" uri="/WEB-INF/tld/c.tld" %>
<c:set var="ctx" value="${pageContext.request.contextPath}" />

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<!-- 公用JS|CSS-->
	<jsp:include page="../public/common.jsp"></jsp:include>
	<link href="${ctx}/wap/css/cate.css" rel="stylesheet" type="text/css" />
	<link href="${ctx}/wap/css/iscroll.css" rel="stylesheet" type="text/css" />
	<script type="text/javascript" src="${ctx}/wap/js/jquery.js"></script>
</head>

<body>
	<!-- 公用头部 -->
	<jsp:include page="../public/header.jsp"></jsp:include>
	
	<%
		System.out.println("微信支付....................................................开始支付");
		String orderSn = request.getParameter("orderSn");
		String totalFee = request.getParameter("totalFee");
		
    	String appId = request.getParameter("appid")+"";
		String timeStamp = request.getParameter("timeStamp")+"";
		String nonceStr = request.getParameter("nonceStr")+"";
		String packageValue = request.getParameter("package")+"";
		String paySign = request.getParameter("sign")+"";
		System.out.println("");
		System.out.println("appId:"+appId);
		System.out.println("timeStamp:"+timeStamp);
		System.out.println("nonceStr:"+nonceStr);
		System.out.println("packageValue:"+packageValue);
		System.out.println("paySign:"+paySign);
		System.out.println("");
	%>
	
	<script type="text/javascript">
	  	//调用微信JS api 支付
	  	function callpay(){
			 WeixinJSBridge.invoke('getBrandWCPayRequest',{
		  		 "appId":"<%=appId%>",
		  		 "timeStamp":"<%=timeStamp%>", //时间戳
		  		 "nonceStr":"<%=nonceStr%>", //随机串
		  		 "package":"prepay_id=<%=packageValue%>",
		  		 "signType":"MD5", //微信签名方式
		  		 "paySign":"<%=paySign%>" //微信签名
	   			},function(res){
					//WeixinJSBridge.log(res.err_msg);
	 				alert("6,"+res.err_code +","+ res.err_desc +","+ res.err_msg);
		            if(res.err_msg == "get_brand_wcpay_request:ok"){
		                //alert("微信支付成功!"); 
		                window.location.href='http://www.dxtzh.org/pub/order/myOrderList.do?type=wap'; 
		            }else if(res.err_msg == "get_brand_wcpay_request:cancel"){  
		                alert("用户取消支付!");  
		            }else{  
		                alert("支付失败!");  
		            }  
	   			}
			 );
	  	}
	</script>
</head>

<body>
	<div class="top">
	    <ul>
	        <li class="back"><a href="javascript:window.history.back(-1);"><img src="${ctx}/wap/images/back.png" width="40" height="50" /></a></li>
	        <li class="logo">微信支付</li>
	    </ul>
	</div>

	<div class="ddwco"><b>订单总金额：</b><font color="red">￥<%= Double.parseDouble(totalFee) %> </font>元</div>

	<div class="ddwch">
	    <div class="ddwct">
		    <b>订单编号：</b><%=orderSn%><br />
		    <b>支付状态：</b>未支付<br />
		</div>
	</div>

	<div class="abouto">
  		<input type="button" class="submit" value="开始支付" onclick="callpay()">
  	</div>
  	
	
	<table height="150px;"><tr><td >&nbsp;</td></tr></table>
	
	<!-- 公用底部 -->
	<jsp:include page="../public/foot.jsp"></jsp:include>
	
</body>
</html>