<%@ page language="java" pageEncoding="UTF-8" isELIgnored="false"%>
<%@ taglib prefix="c" uri="/WEB-INF/tld/c.tld" %>
<c:set var="ctx" value="${pageContext.request.contextPath}" />

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<title>惠达</title>
	<meta name="viewport" content="width=device-width,height=device-height,inital-scale=1.0,maximum-scale=1.0,user-scalable=no;">
	<meta name="apple-mobile-web-app-capable" content="yes">
	<meta name="apple-mobile-web-app-status-bar-style" content="black">
	<meta name="format-detection" content="telephone=no">
	
	<!-- 公用JS|CSS-->
	<link rel="stylesheet" href="${ctx}/wap/userCenter/css/style.css">
	
	<!-- 弹窗提示 -->
	<link href="${ctx }/common/css/cssManage.css" rel="stylesheet" type="text/css" />
	<style type="text/css" > 
		div.jqi{width:215px;top:50px;}
	</style> 
	
	<link rel="stylesheet" href="${ctx }/common/css/pagination.css" />
    <script src="${ctx }/common/js/jquery.js" type="text/javascript" charset="UTF-8"></script>
    <script src="${ctx }/common/js/jquery.pagination.js" type="text/javascript" charset="UTF-8"></script>
    <script src="${ctx }/common/js/jquery-impromptu.3.2.js" type="text/javascript" charset="UTF-8"></script>
	<script type="text/javascript">
		function initData(pageIndex,type){
			var pageCount;
			$.ajax({
				type:"post",
				dataType:"json",
				url:"${ctx}/pub/order/listOrdersForPagination.do?type=wap",
				data:"page="+pageIndex+"&pageCount=10&orderType="+type,
				async:false,
				success: function(data){
					var returnData = data.pageData;
					
					var UL = document.getElementById("dataTable");
					UL.innerHTML="";
					
    				//document.getElementById("headimgurl").innerHTML="<img src='"+data.headimgurl+"' width='80' height='80' />";
    				//document.getElementById("loginName").innerHTML=data.loginName;
    				
					if(returnData==""){
	                    return false;
	                }
    				pageCount = data.pageCount;
    
					$.each(returnData,function(i, n){
						var trs = "<li>";
                     			//+"<div class='spmc' style='width: 20%;'>"+n.orderSn.substring(3,11)+"...</div>"
                     			trs = trs + "<div class='spmc' style='width: 35%;word-break:break-all;'>";
                     			trs = trs + "<a style='cursor:pointer;text-decoration: underline;' onclick='javascript:window.location.href=\"${ctx}/pub/order/myOrderView.do?type=wap&orderId=" + n.orderId + "\"' title='支付'>";
		            			trs = trs + n.orderSn;
		            			trs = trs + "</a></div>";
		            			
    							if(n.orderStatus=='1'){
			            			trs = trs + "<div class='dj' style='text-align: center;width:30%;'><font color='red'>进行中</font></div>";
			            		}else if(n.orderStatus=='2'){
			            			trs = trs + "<div class='dj' style='text-align: center;width:30%;'><font color='green'>已完成</font></div>";
			            		}else if(n.orderStatus=='3'){
			            			trs = trs + "<div class='dj' style='text-align: center;width:30%;'><font color='#999'>已关闭</font></div>";
			            		}
			            		
			            		trs = trs + "<div class='sl' style='text-align: right;width:32%;word-break:break-all;'>"+n.orderTime+"</div>";
	                   			trs = trs + "</li>";
                     		
            			$("#dataTable").append(trs);
				});
			}
			});
			$("#pagination").pagination(pageCount, {
                   callback: pageselectCallback,
                   prev_text: '<< 上一页',
                   next_text: '下一页 >>',
                   items_per_page:10,//每页显示记录条数   
		           num_display_entries:2,//连续分页主体部分分页条目数   
		           current_page:pageIndex,//当前页索引
		           num_edge_entries:2//两侧首尾分页条目数
            });
		}
		function pageselectCallback(page_id,jq) {
           initData(page_id,'${requestScope.type}');
       }
  </script>  
</head>

<body onload="javascript:initData(0,'${requestScope.type}')">

	<!-- 公用头部-->
	<jsp:include page="head.jsp"></jsp:include>
	
	
	<ul class="wdddt">
	    <li style="width: 22%;margin-top: 10px;">
	    	<a href="#" onclick="initData(0,'9')">全部</a> 
	    </li>
	    <li style="width: 22%;margin-top: 10px;margin-left: 15px;">
	    	<a href="#" onclick="initData(0,'1')">进行中</a>
	    </li>
	    <li style="width: 22%;margin-top: 10px;margin-left: 15px;"> 
	    	<a href="#" onclick="initData(0,'2')">已完成</a>
	    </li>
	    <li style="width: 22%;margin-top: 10px;margin-left: 15px;">
	    	<a href="#" onclick="initData(0,'3')">已关闭</a>
	    </li>
	</ul>
 
	<ul class="wdddf" style="margin: -1px 0 0;">
	    <font size="2">
	    <li class="spmc" style="width: 35%;">订单编号</li>
	    <li class="dj" style="width: 30%;">订单状态</li>
	    <li class="sl" style="width: 32%;">下单时间</li>
	    </font>
	</ul>
	<ul class="wdddr" id="dataTable">
	    <!-- 
	    <li>
	        <div class="spmc">123456</div>
	        <div class="sl">2015-09-12 22:10:12</div>
	        <div class="dj">待付款 </div>
	        <div class="bz">付款</div>
	    </li>
	     -->
	</ul>
	<div id="pagination" class="flickr" style="text-align:right;"></div>

	<table height="150px;"><tr><td >&nbsp;</td></tr></table>
	
	<!-- foot -->
	<jsp:include page="../public/foot.jsp"></jsp:include>
	
</body>
</html>
