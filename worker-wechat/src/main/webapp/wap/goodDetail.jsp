<%@ page language="java" pageEncoding="UTF-8" isELIgnored="false"%>
<%@ taglib prefix="c" uri="/WEB-INF/tld/c.tld" %>
<%@ taglib prefix="fn" uri="/WEB-INF/tld/fn.tld" %>
<c:set var="ctx" value="${pageContext.request.contextPath}" />
<%@page import="com.shop.good.model.dto.GoodDTO"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
  	  <!-- 公用JS|CSS-->
	  <jsp:include page="public/common.jsp"></jsp:include>
	  <link href="${ctx}/wap/css/cate.css" rel="stylesheet" type="text/css" />
	  <link href="${ctx}/wap/css/iscroll.css" rel="stylesheet" type="text/css" />
	  <link href="${ctx}/wap/css/gooddetail.css" rel="stylesheet" type="text/css" />
	  <script src="${ctx}/common/js/jquery.js" type="text/javascript" charset="UTF-8"></script>
</head>
<body style="background:#f0f2f5;">

	<!-- 公用头部 -->
	<jsp:include page="public/header.jsp"></jsp:include>

	<div class="content">
	  <section class="detphonebox bgfff">
	  	<div class="swiper-container2">
	        <div class="swiper-wrapper">
	            <c:forEach items="${requestScope.picList}" var="pic" varStatus="status">
					<div class="swiper-slide"><img src="${ctx}/ProductIMG/${pic.pic}" width="270" height="300"/></div>
				</c:forEach>
	            <!-- 
	            <div class="swiper-slide"><img src="${ctx}/wap/images/2.jpg" alt=""> </div>
	            <div class="swiper-slide"><img src="${ctx}/wap/images/3.jpg" alt=""> </div>
	             -->
	        </div>
	    </div>
	  </section>
	  <section class="detphonetext bgfff">
	    <p class="detwxts"> ${good.name} </p>
	    <p class="detname"> 简介：${good.descr1} </p>
	    <div class="detjg"> 积分价：<span>${good.point}</span> </div>
	    <div class="detjg"> 销售价：<span>￥${good.price}</span> </div>
	    <div class="detjg"> 
		     <dt>购买数量:</dt>
			 <dd><a href="javascript:void(0)" onClick="javascript:down('${good.id}')">-</a></dd>
			 <dd><input  name="amount"  id="amount" type="text"  value="1" tyle="ime-mode:disabled" onkeydown="if(event.keyCode==13)event.keyCode=9" onKeyPress="if ((event.keyCode <48 || event.keyCode>57)) event.returnValue=false" onkeyup="this.value=this.value.replace(/\D/g,'')" /> </dd>
			 <dd><a href="javascript:void(0)" onClick="javascript:up('${good.id}')">+</a></dd>
			 <dt>&nbsp;份</dt>
		</div>
	  </section>
	  
	  
	  <section class="detphonetext2 bgfff">
	    <br>
	    <% 
	    		GoodDTO good = (GoodDTO)request.getAttribute("good");
             	String htmlData = good.getDescr2() != null ? good.getDescr2() : "";
		%>
		<%=htmlspecialchars(htmlData)%>
	    <br><br><br><br>
	  </section>
	  
	  
	  
	  <div class="spbox-m"></div>
	  <!--立即抢购按钮 begin-->
	  <div class="gwfixe">
	  	<c:if test="${good.isbn>0}">
	    	<button class="buttonf fl" style="cursor:pointer" onClick="javascript:buyNow('${good.id}');">立即购买</button>
	    </c:if>
		<c:if test="${good.isbn<=0}">
			<button class="buttonf" >已售罄</button>
		</c:if>
	  </div>
	  <!--立即抢购按钮 end-->
	</div>

</body>
</html>
<script type="text/javascript">
	//商品购买数量增加
	function up(goodId)
	{
  		var amount = document.getElementById('amount');
  		var _amount = parseInt(amount.value)+1;
		// 修改购物车
		$.ajax({
			 type:"post",
			 dataType:"json",
			 url:"${ctx}/pub/shopcar/updateShopCar.do",
			 data:"goodId="+goodId+"&shopType=add&type=wap&amount="+_amount,
			 async:false,
			 complete: function (XMLHttpRequest,textStatus) {
			    if(XMLHttpRequest.responseText!="" && XMLHttpRequest.responseText==0){
			    	amount.value = _amount
			    }
			 }
		});
	}
	
	//商品购买数量减少
	function down(goodId)
	{
	  	var amount = document.getElementById('amount');
	  	var _amount = parseInt(amount.value)-1;
		// 修改购物车
		$.ajax({
			 type:"post",
			 dataType:"json",
			 url:"${ctx}/pub/shopcar/updateShopCar.do",
			 data:"goodId="+goodId+"&shopType=del&type=wap&amount="+_amount,
			 async:false,
			 complete: function (XMLHttpRequest,textStatus) {
			    if(XMLHttpRequest.responseText!="" && XMLHttpRequest.responseText==0){
			    	amount.value = _amount;
			    }
			 }
		});
	}
	//立即购买
	function buyNow(goodId)
	{
  		var amount = document.getElementById('amount').value;
  		$.ajax({
			 type:"post",
			 dataType:"json",
			 url:"${ctx}/pub/shopcar/updateShopCar.do",
			 data:"goodId="+goodId+"&shopType=add&type=wap&amount="+amount,
			 async:false,
			 complete: function (XMLHttpRequest,textStatus) {
			    if(XMLHttpRequest.responseText!="" && XMLHttpRequest.responseText==0){
			    	window.location.href="${ctx}/pub/shopcar/shopCarView.do?type=wap";
			    }
			 }
		});
	}
</script>
<%!
private String htmlspecialchars(String str) {
	str = str.replaceAll("&lt;", "<");
	str = str.replaceAll("&gt;", ">");
	str = str.replaceAll("&quot;", "\"");
	str = str.replaceAll("&nbsp;", " ");
	str = str.replaceAll("&lt；", "<");
	str = str.replaceAll("&gt；", ">");
	str = str.replaceAll("&quot；", "\"");
	str = str.replaceAll("&nbsp；", " ");
	str = str.replaceAll("＝", "=");
	str = str.replaceAll("  ", "&nbsp;&nbsp;");
	str = str.replaceAll("；", ";");
	str = str.replaceAll("（", "(");
	str = str.replaceAll("）", ")");
	return str;
}
%>