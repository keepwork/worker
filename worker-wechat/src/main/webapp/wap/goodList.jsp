<%@ page language="java" pageEncoding="UTF-8" isELIgnored="false"%>
<%@ taglib prefix="c" uri="/WEB-INF/tld/c.tld" %>
<%@ taglib prefix="fn" uri="/WEB-INF/tld/fn.tld" %>
<c:set var="ctx" value="${pageContext.request.contextPath}" />

<!DOCTYPE html>
<html lang="en">
<head>
  	  <!-- 公用JS|CSS-->
	  <jsp:include page="public/common.jsp"></jsp:include>
	  <link href="${ctx}/wap/css/cate.css" rel="stylesheet" type="text/css" />
	  <link href="${ctx}/wap/css/iscroll.css" rel="stylesheet" type="text/css" />
	  
	  <!-- 底部悬浮条 -->
	  <style type="text/css">
		.nextpage {
		 background:#85BB84;
		 color:#fff;
		    bottom: 0;
		    margin: 0 auto;
		    position: fixed;
		 height:40px;
		 opacity: .92;
		 filter: alpha(opacity=60);
		 line-height:40px;
		    width: 100%;
		    z-index: 999;
		    _bottom:auto;
		    _width: 100%;
		    _position: absolute;
		    _top:expression(eval(document.documentElement.scrollTop+document.documentElement.clientHeight-this.offsetHeight-(parseInt(this.currentStyle.marginTop,10)||0)-(parseInt(this.currentStyle.marginBottom,10)||0)));
		}
		.header {
		    position: relative;
		    height: 57px;
		    background: #09acf7;
		}
		.dl-menuwrapper .dl-menu {
	    	margin: 12px 0 0 0;
	    }
	  </style>

	  <style type="text/css" > 
  		.sl a{ background:#FFFFFF; border:1px solid #ccc; padding:0px 3px 0px 3px; text-decoration:none;}
  		.sl a:hover{ background:#FFFFFF; border:1px solid #f86400; text-decoration:none;}
  		.sl input{ width:20px; background:#FFFFFF; border:1px solid #989898; text-align:center; margin-left:2px; height:20px; line-height:20px;}
 	  </style> 
 	  
	  <script type="text/javascript" src="${ctx}/common/js/MapUtil.js" ></script>
	  <script type="text/javascript">
		// 存放被选中的商品
		var goodsMap = new Map();  
		// 存放被选中的商品总金额
		//window.totalPrice = 0.00;
		// 存放被选中的商品总数量
		//window.totalNum = 0;
		
		function init(){
			var amounts = document.getElementsByName("amount");
			for(var i=0;i<amounts.length;i++){
				amounts[i].value = 0;
			}
		}
	  </script>
	  
	  <script src="${ctx}/common/js/jquery.js" type="text/javascript" charset="UTF-8"></script>
	  <script type="text/javascript">
		// 根据一级菜单查询二级菜单，并查询出该二级菜单下所有的商品
		function fetchSecondCategory(firstCode){
			// 切换二级菜单的选中状态
			var ele = document.getElementsByTagName("li");
		    for(i=0;i<ele.length;i++){
		        if(ele[i].className=='jg'){
		            ele[i].className = 'lb';
		        }
		    }
			var firstCate = document.getElementById(firstCode);
			firstCate.className="jg";
			if(firstCode != '')
			{
				// 根据一级菜单查询二级菜单
		   		$.ajax({
					 type:"post",
					 dataType:"json",
					 url:"${ctx}/pub/goodCate/fetchSecondCategory.do",
					 data:"firstCode="+firstCode,
					 async:false,
					 //sucess : function(data){
					 //    var returnData1 = data.returnData;
					 complete: function (XMLHttpRequest,textStatus) {
					     if(XMLHttpRequest.response==""){
		                    return false;
		                 }
					     var returnData = eval('[' + XMLHttpRequest.response + ']');
		                
				         var UL = document.getElementById("secondCategoryUL");
						 UL.innerHTML="";
						
					     $.each(returnData,function(i, n){
						     UL.innerHTML +="<li id='"+n.code+"'><a href='#' onclick=\"fetchSecondCateGoods('"+n.code+"')\">"+n.name+"</a></li>";
					     })
					     UL.innerHTML += "<li></li>";
					     UL.innerHTML += "<li></li>";
					     UL.innerHTML += "<li></li>";
					 }
				});
		   		
		   		//查询出该一级菜单下所有的商品
		   		$.ajax({
					 type:"post",
					 dataType:"json",
					 url:"${ctx}/pub/goodCate/fetchFirstCateGoods.do",
					 data:"firstCode="+firstCode,
					 async:false,
					 //sucess : function(data){
					 //    var returnData = data.returnData;
					 //    if(returnData==""){
		              //      return false;
		              //   }
		             complete: function (XMLHttpRequest,textStatus) {
					     if(XMLHttpRequest.response==""){
		                    return false;
		                 }
					     var returnData = eval('[' + XMLHttpRequest.response + ']');
		                
				         var UL = document.getElementById("goodsUL");
						 UL.innerHTML="";
						 
						 $.each(returnData,function(i, n)
						 {
						     var _html = "<li>";
						     _html += "<div class='spxx' style='width:67%;'><img src='${ctx}/"+n.spic+"' width='100' height='74' />"+n.name+"<br /><span>"+n.descr1+"</span></div>";
						     _html += "<div class='je'>";
						     
						     if(parseInt(n.isbn)>0){
							     _html += "<div class='sl' style='margin-left: 10px;'>";
							  	 _html += "	<table><tr>";
							  	 _html += "	<td><a href='javascript:void(0)' onClick='javascript:down(\""+n.goodId+"\",\""+n.point+"\")'>-</a></td>";
							  	 _html += "	<td><input name='amount' id='amount_"+n.goodId+"' type='text' value='0' readonly='readonly' tyle='ime-mode:disabled' onkeydown='if(event.keyCode==13)event.keyCode=9' onKeyPress='if ((event.keyCode <48 || event.keyCode>57)) event.returnValue=false' onkeyup='this.value=this.value.replace(/\D/g,'')' /> ";
								 _html += "	  </td>";
							  	 _html += "	<td><a href='javascript:void(0)' onClick='javascript:up(\""+n.goodId+"\",\""+n.point+"\")'>+</a></td>";
							  	 _html += "	</tr></table>";
								 _html += "</div> ";
							 }else if(parseInt(n.isbn)<=0){
							 	_html += "<span style='font-size:15px;margin-left: 10px;'><font color='red' size='2'>已售罄</font></span>";
							 }
							 
							 _html += "<br />";
						     _html += "</div>";
						     
							 _html += "<div class='je' style='font-size:11px;width: 25%;float:left;margin-left: 11px;margin-top: 2px;'>";
					         _html += " 		<font color='red' style='margin: left;'>积分："+n.point+"</font>";
					         _html += "</div>";
					         
						     
						     _html += "</li>";
						     UL.innerHTML += _html;
					     })
					     
					     // 填充已选中的商品数量
					     if(goodsMap.elements.length>0){
					    	for (h = 0; h < goodsMap.elements.length; h++) {
					            var mkey = goodsMap.elements[h].key;
					            var mvalue = goodsMap.elements[h].value;
					            //console.info("key:"+mkey+",value:"+mvalue);
					            if(document.getElementById('amount_'+mkey) != null){
					            	document.getElementById('amount_'+mkey).value = mvalue;
					            }
					        } 
					     }
					 }
				});
				
			}
		}
		
		// 根据二级菜单查询商品列表
		function fetchSecondCateGoods(secondCode)
		{
			// 切换二级菜单的选中状态
			var ele = document.getElementsByTagName("li");
		    for(i=0;i<ele.length;i++){
		        if(ele[i].className=='jg'){
		            ele[i].className = '';
		        }
		    }
			var secondCate = document.getElementById(secondCode);
			secondCate.className = "jg";
			
			if(secondCode!='')
			{
				$.ajax({
					 type:"post",
					 dataType:"json",
					 url:"${ctx}/pub/goodCate/fetchSecondCateGoods.do",
					 data:"secondCode="+secondCode,
					 async:false,
					 //sucess : function(data){
					  //   var returnData = data.returnData;
					  //   if(returnData==""){
		               //     return false;
		              //   }
		             complete: function (XMLHttpRequest,textStatus) {
					     //if(XMLHttpRequest.response==""){
					     if(XMLHttpRequest.responseText==""){
		                    return false;
		                 }
					     var returnData = eval('[' + XMLHttpRequest.responseText + ']');
		                
						 var UL = document.getElementById("goodsUL");
						 UL.innerHTML="";
					
					     $.each(returnData,function(i, n)
					     {
						     var _html = "<li>";
						     _html += "<div class='spxx' style='width:67%;'>";
						     _html += "<a href='${ctx}/pub/good/goodDetail.do?type=wap&goodId="+n.goodId+"'>";
						     _html += "<img src='${ctx}/"+n.spic+"' width='100' height='74' />";
						     if(n.name.length>8){
						     	_html += n.name.substring(0,8)+"..";
						     }else{
						     	_html += n.name;
						     }
						     _html += "</a>";
						     _html += "<br /><span>"+n.descr1+"</span></div>";
						     _html += "<div class='je'>";
						  	 
						  	 if(parseInt(n.isbn)>0){
						  	 	 _html += "<div class='sl' style='margin-left: 10px;'>";
						  	 	 _html += "	<table><tr>";
						  	 	 _html += "	<td><a href='javascript:void(0)' onClick='javascript:down(\""+n.goodId+"\",\""+n.point+"\",\""+n.price+"\")'>-</a></td>";
							  	 _html += "	<td><input name='amount' id='amount_"+n.goodId+"' type='text' value='0' readonly='readonly' tyle='ime-mode:disabled' onkeydown='if(event.keyCode==13)event.keyCode=9' onKeyPress='if ((event.keyCode <48 || event.keyCode>57)) event.returnValue=false' onkeyup='this.value=this.value.replace(/\D/g,'')' /> ";
								 _html += "	  </td>";
							  	 _html += "	<td><a href='javascript:void(0)' onClick='javascript:up(\""+n.goodId+"\",\""+n.point+"\",\""+n.price+"\")'>+</a></td>";
						  	 	 _html += "	</tr></table>";
						  	 	 _html += "</div> ";
						  	 }else{
						  	 	 _html += "	<span style='font-size:15px;margin-left: 10px;'><font color='red' size='2'>已售罄</font></span>";
						  	 }
			  
						     _html += "<br />";
						     _html += "</div>";
						     
						     _html += "<div class='je' style='font-size:11px;width: 25%;float:left;margin-left: 11px;margin-top: 2px;'>";
					         _html += " 		<font color='red' style='margin: left;'>积分："+n.point+"</font>";
					         _html += "</div>";
	          
						     _html += "</li>";
						     UL.innerHTML += _html;
					     })
					     
					     // 填充已选中的商品数量
					     if(goodsMap.elements.length>0){
					    	for (h = 0; h < goodsMap.elements.length; h++) {
					            var mkey = goodsMap.elements[h].key;
					            var mvalue = goodsMap.elements[h].value;
					            //console.info("key:"+mkey+",value:"+mvalue);
					            if(document.getElementById('amount_'+mkey) != null){
					            	document.getElementById('amount_'+mkey).value = mvalue;
					            }
					        } 
					     }
					 }
				});
			}
		}
	  </script>
</head>

<body onload="javascript:init();">
	
	<!-- 公用头部 -->
	<jsp:include page="public/header.jsp"></jsp:include>
	
	<!-- 一级菜单 -->
	<ul class="wmxco" >
	    <c:forEach items="${requestScope.secondList}" var="first" varStatus="status">
	    	<c:if test="${status.index==0}"><li class="jg" id="${first.code}"></c:if>
	    	<c:if test="${status.index!=0}"><li class="lb" id="${first.code}"></c:if>
	    		<A href="#" class="level_2" onclick="fetchSecondCateGoods('${first.code}')" >
	    		<font color="#fffff">${first.name}</font>
	    		</A>
	    	</li>
	    </c:forEach>
	</ul>
	<div class="wmxct" style="margin-left: 10px;">
		<font color="red" size="2">*购物返利：购买成功后立即赠送5%的积分！</font>
	</div>
	

	<div class="wmxct" style="margin: 8px 0 0;">
	    <!-- 二菜单 
	    <ul class="fl" id="secondCategoryUL">
	        <c:forEach items="${requestScope.secondList}" var="second" varStatus="status">
	        	<li id="${second.code}"><A href="#" class="level_2" onclick="fetchSecondCateGoods('${second.code}')" >${second.name}</A></li>
	        </c:forEach>
	        <li></li>
	        <li></li>
	        <li></li>
	    </ul>
	    -->
	    <!-- 商品列表 -->
	    <ul class="fr" id="goodsUL"  style="width: 99%;">
	        <c:forEach items="${requestScope.goodsList}" var="good" varStatus="status">
	        <li>
	          <div class="spxx" style="width:67%;font-size: 13px;">
	          	<a href="${ctx}/pub/good/goodDetail.do?type=wap&goodId=${good.id}">
	          	<img src="${ctx}/ProductIMG/${good.spic}" width="100" height="74" />
	          	<c:choose>
					<c:when test="${fn:length(good.name) gt 8}">${fn:substring(good.name,0,8)}..</c:when>
					<c:otherwise>${good.name}</c:otherwise>
				</c:choose> 
				</a>
	          	<br />
	          	<span>${good.descr1}</span>
	          </div>
	          <div class="je" style="width: 25%;">
	         	<c:if test="${good.isbn>0}">
	         	<div class="sl" style="margin-left: 10px;">
		  		<table><tr> 
		  		<td><a href="javascript:void(0)" onClick="javascript:down('${good.id}','${good.point}','${good.price}')">-</a></td>
		  		<td><input name="amount" id="amount_${good.id}" type="text" value="0" readonly="readonly" tyle="ime-mode:disabled" onkeydown="if(event.keyCode==13)event.keyCode=9" onKeyPress="if ((event.keyCode <48 || event.keyCode>57)) event.returnValue=false" onkeyup="this.value=this.value.replace(/\D/g,'')" /> 
				  </td>
		  		<td><a href="javascript:void(0)" onClick="javascript:up('${good.id}','${good.point}','${good.price}')">+</a></td>
		  		</tr></table>
			  	</div>  
			  	</c:if>
			  	<c:if test="${good.isbn<=0}">
			  	<span style="font-size:15px;margin-left: 10px;"><font color="red" size="2">已售罄</font></span>
			  	</c:if>
	          </div>
	          
	          <br />
	          
	          <div class="je" style="font-size:11px;width: 25%;float:left;margin-left: 11px;margin-top: 2px;">
	          		<font color="red" style="margin: left;">积分：${good.point}</font><br/>
	          		<font color="red" style="margin: left;">单价：￥${good.price}</font>
	          </div>
	        </li>
	        </c:forEach>
	    </ul>
	</div>

	<div class="nextpage">
	<ul class="wmxch">
	    <li class="fl" style="background: #09acf7 none repeat scroll 0 0;">
	    &nbsp;金额：￥<span id="selectPrice" >0</span>&nbsp;|&nbsp;积分：<span id="selectPoint">0</span>
	    </li>
	    <li class="fr"><input type="button" style="font-size: 18px;"  value="选好了" onclick="javascript:window.location.href='${ctx}/pub/shopcar/shopCarView.do?type=wap'"/></li>
	</ul>
</div>

	<table height="100px;"><tr><td >&nbsp;</td></tr></table>

</body>
</html>
<script type="text/javascript">
	//商品购买数量增加
	function up(_goodId,_point,_price)
	{
		var amount=document.getElementById('amount_'+_goodId).value;
			amount=parseInt(amount)+1;
			document.getElementById('amount_'+_goodId).value=amount;
			
			var selectPoint = document.getElementById('selectPoint');
			var point = selectPoint.innerHTML;
			selectPoint.innerHTML = parseInt(point) + parseInt(_point);
			
			var selectPrice = document.getElementById('selectPrice');
			var price = selectPrice.innerHTML;
			selectPrice.innerHTML = parseFloat(price) + parseFloat(_price);
		
			//保存选中的商品到map中
			var goodNum = goodsMap.get(_goodId);
			if(null == goodNum){
				goodsMap.put(_goodId,1);
			}else{
				goodsMap.put(_goodId,parseInt(goodNum)+1);
			}
			//window.totalPrice += parseFloat(price);
			//window.totalNum += 1;
		
			// 修改购物车
			$.ajax({
				 type:"post",
				 dataType:"json",
				 url:"${ctx}/pub/shopcar/updateShopCar.do",
				 data:"goodId="+_goodId+"&shopType=add&type=wap",
				 async:false,
				 sucess : function(data){
				 }
			});
		
	}
	//商品购买数量减少
	function down(_goodId,_point,_price)
	{
			var amount=document.getElementById('amount_'+_goodId).value;
			if(parseInt(amount)>0)
			{
				var newAmount=parseInt(amount)-1;
				document.getElementById('amount_'+_goodId).value=newAmount;
			}
			if(parseInt(amount)>0)
			{
				var selectPoint = document.getElementById('selectPoint');
				var point = selectPoint.innerHTML;
				selectPoint.innerHTML = parseInt(point) - parseInt(_point);
				
				var selectPrice = document.getElementById('selectPrice');
				var price = selectPrice.innerHTML;
				selectPrice.innerHTML = parseFloat(price) - parseFloat(_price);
			
				
				//保存选中的商品到map中
				var goodNum = goodsMap.get(_goodId);
				if(null == goodNum){
					goodsMap.put(_goodId,1);
				}else{
					goodsMap.put(_goodId,parseInt(goodNum)-1);
				}
				//window.totalPrice -= parseFloat(price);
				//window.totalNum -= 1;
				
				// 修改购物车
				$.ajax({
					 type:"post",
					 dataType:"json",
					 url:"${ctx}/pub/shopcar/updateShopCar.do",
					 data:"goodId="+_goodId+"&shopType=del&type=wap",
					 async:false,
					 sucess : function(data){
					 }
				});
			}
			
	}
	
</script>
