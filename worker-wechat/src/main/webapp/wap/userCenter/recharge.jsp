<%@ page language="java" pageEncoding="UTF-8" isELIgnored="false"%>
<%@ taglib prefix="c" uri="/WEB-INF/tld/c.tld" %>
<c:set var="ctx" value="${pageContext.request.contextPath}" />

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<!-- 公用JS|CSS-->
	<jsp:include page="../public/common.jsp"></jsp:include>
	<link rel="stylesheet" href="${ctx}/common/css/tab2.css">
	<link rel="stylesheet" href="${ctx}/wap/userCenter/css/style.css">
</head>

<body>

	<!-- 公用头部-->
	<jsp:include page="head.jsp"></jsp:include>
	
	
	<script src="${ctx}/common/js/tytabs.jquery.min.js" type="text/javascript" charset="UTF-8"></script>
	<script type="text/javascript">
	<!--
	$(document).ready(function(){
		$("#tabsholder").tytabs({
								tabinit:"1",
								fadespeed:"fast"
								});
		$("#tabsholder2").tytabs({
								prefixtabs:"tabz",
								prefixcontent:"contentz",
								classcontent:"tabscontent",
								tabinit:"3",
								catchget:"tab2",
								fadespeed:"normal"
								});
	});
	-->
	</script>
	<!--金额开始 -->
	<SCRIPT type=text/javascript>
	function selectTag(showContent,selfObj,price){
		document.getElementById("price1").value = price;
		
		// 操作标签
		var tag = document.getElementById("tags").getElementsByTagName("li");
		var taglength = tag.length;
		for(i=0; i<taglength; i++){
			tag[i].className = "";
		}
		selfObj.parentNode.className = "selectTag";
		// 操作内容
		for(i=0; j=document.getElementById("tagContent"+i); i++){
			j.style.display = "none";
		}
		document.getElementById(showContent).style.display = "block";
	}
	</SCRIPT>
	<!--金额结束 -->

	<ul class="round">
			<li class="title">
				<a href="${ctx}/pub/menber/centerInit.do?type=wap" >返回</a>
				正在为 <font color="red">${rechargeMenName}</font> 充值
			</li>
	</ul>
	
	<div class="wdzh" style="border-top: 0px;padding: 0px 0; */">
    <div class="zhxq" style="margin: 0px 0 0 0;">
					<!-- Tabs -->
					<div id="tabsholder">
						<ul class="tabs">
							<li id="tab1" style="width: 50%;">充值</li>
							<li id="tab2" style="width: 50%;">提现</li>
						</ul>
						<div class="contents marginbot" style="margin: 15px 1px;">
							<div id="content1" class="tabscontent">
								<div id="con">
									<ul id="tags">
										<!-- <li class="selectTag"> -->
										<li><a onclick="selectTag('tagContent0',this,'12')" href="javascript:void(0)">12元</a></li>
										<li><a onclick="selectTag('tagContent1',this,'30')" href="javascript:void(0)">30元</a></li>
										<li><a onclick="selectTag('tagContent2',this,'50')" href="javascript:void(0)">50元</a></li>
									</ul>
									<input name="price1" class="px" id="price1" value="" placeholder="请输入充值金额" type="text"
								  		style="width: 100%;padding: 12px 10px;"
								  		onkeyup='this.value=this.value.replace(/[^0-9.]/gi,""); '
										onafterpaste='this.value=this.value.replace(/[^0-9.]/gi,"")'>
								</div>
								<div style="margin:5px 0 10px 0;">
									单次充值不得低于12元，为了充分保障您的会员资格和减少频繁充值频率，建议您充值30元以上，且不超过100元。
								</div>
								<input class="submit" value="立即充值" type="button" onclick="rechange('${rechargeMenId}');return false">
							</div>
							<div id="content2" class="tabscontent">
								<div>
								  <input name="price2" class="px" id="price2" value="" placeholder="请输入提现金额" type="text"
								  		style="width: 91%;padding: 12px 10px;"
								  		onkeyup='this.value=this.value.replace(/[^0-9]/gi,""); '
										onafterpaste='this.value=this.value.replace(/[^0-9]/gi,"")'><br><br>
								  *收款人名：<br><input name="bankUserName" class="input_text" id="bankUserName" value="" placeholder="请输入收款人姓名" type="text"><br>
								  *银行名称：<br><input name="bankName" class="input_text" id="bankName" value="" placeholder="请输入收款银行名称" type="text"><br>
								  *银行卡号：<br><input name="bankNum" class="input_text" id="bankNum" value="" placeholder="请输入收款银行卡号" type="text"><br>
								</div>
								<div style="margin:5px 0 10px 0;">
									提现金额最低为50元，且每笔收取手续费为0.6%+2元，仅限会员本人银行卡。
								</div>
								<input class="submit" value="立即提现" type="button" onclick="tixian('${rechargeMenId}');return false">
							</div>
						</div>
					</div>

	</div>
	</div>
	
	<br><br><br>
	<!-- foot -->
	<jsp:include page="../public/foot.jsp"></jsp:include>

</body>
</html>
<script type="text/javascript">
		function rechange(rechargeMenId)
		{
	    	var price1 = document.getElementById("price1");
	    	if(price1.value == ''){
	    		alert("充值金额不能为空");
	    		price1.focus();
	    		return;
	    	}
	    	if(null==rechargeMenId || ""==rechargeMenId){
	    		alert("充值对象不存在，请重试!");
	    		return;
	    	}
			window.location.href="${ctx}/wxrechange.do?price="+price1.value+"&rechargeMenId="+rechargeMenId; 
		}
		function tixian(menId)
		{
	    	var price2 = document.getElementById("price2");
	    	if(price2.value == ''){
	    		alert("提现金额不能为空");
	    		price2.focus();
	    		return;
	    	}
	    	if(parseInt(price2.value)<50){
	    		alert("只能对50元以上的金额申请提现，并将收取5%的手续费!");
	    		price2.focus();
	    		return;
	    	}
	    	
	    	var bankUserName = document.getElementById('bankUserName');
	    	if(bankUserName.value == ''){
	    		alert("收款人姓名不能为空");
	    		bankUserName.focus();
	    		return;
	    	}
			var bankName = document.getElementById('bankName');
	    	if(bankName.value == ''){
	    		alert("收款银行名称不能为空");
	    		bankName.focus();
	    		return;
	    	}
	    	var bankNum = document.getElementById('bankNum');
	    	if(bankNum.value == ''){
	    		alert("收款银行卡号不能为空");
	    		bankNum.focus();
	    		return;
	    	}
	    	
	    	if(null==menId || ""==menId){
	    		alert("提现对象不存在，请重试!");
	    		return;
	    	}
	    	
	    	$.ajax({
				 type:"post",
				 dataType:"html",
				 url:"${ctx}/pub/cash/tixian.do",
				 data:"type=wap&price="+price2.value+"&menId="+menId+"&bankUserName="+bankUserName.value+"&bankName="+bankName.value+"&bankNum="+bankNum.value,
				 async:false,
				 complete: function (XMLHttpRequest,textStatus) {
				    if(XMLHttpRequest.responseText!=""){
	                    if(XMLHttpRequest.responseText==1){
					 		alert("申请提交成功，审核通过后我们将打款到您的微信账户！");
					 	}else if(XMLHttpRequest.responseText==2){
					 		alert("申请提交失败，您的余额不足！");
					 	}else if(XMLHttpRequest.responseText==4){
					 		alert("申请提交失败，您的余额不足100元！");
					 	}else if(XMLHttpRequest.responseText==3){
					 		alert("您当前已有提现申请，请等待审核结果！");
					 	}else{
					 		alert("申请提交失败！");
					 	}
	                }
				 }
			});
				
		}
</script>