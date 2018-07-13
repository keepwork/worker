<%@ page language="java" pageEncoding="UTF-8" isELIgnored="false"%>
<%@ taglib prefix="c" uri="/WEB-INF/tld/c.tld" %>
<c:set var="ctx" value="${pageContext.request.contextPath}" />

<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1" />
	<meta name="viewport" content="width=device-width, initial-scale=1.0, minimum-scale=0.5, maximum-scale=2.0,user-scalable=no"/>
	<title>订单中心</title>
	<link rel="stylesheet" href="${ctx}/wap/html/css/Basc.css" />
	<link rel="stylesheet" href="${ctx}/wap/html/css/demo.css" />
	<script type="text/javascript" src="${ctx}/wap/html/js/jquery.min.js" charset="UTF-8"></script>
	<script type="text/javascript" src="${ctx}/wap/html/js/tytabs.jquery.min.js" charset="UTF-8"></script>
	<style>
		ul.tabs li {list-style-type:none;display:block;float:left;cursor:pointer; width:20%; height:50px; line-height:50px; text-align:center; font-size:16px;}
	</style>

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
	
	
	<link rel="stylesheet" href="${ctx }/common/css/pagination.css" />
    <script src="${ctx }/common/js/jquery.pagination.js" type="text/javascript" charset="UTF-8"></script>
    <script src="${ctx }/common/js/jquery-impromptu.3.2.js" type="text/javascript" charset="UTF-8"></script>
	<%--日期控件相关--%>
	<link href="${ctx }/wap/workerCenter/css/common.css" rel="stylesheet" type="text/css" />
	<script type="text/javascript" src="${ctx }/wap/workerCenter/js/date.js" ></script>
	<script type="text/javascript" src="${ctx }/wap/workerCenter/js/iscroll.js" ></script>

	<script type="text/javascript">
		function initData(pageIndex,status){
			var pageCount;
			$.ajax({
				type:"post",
				dataType:"json",
				url:"${ctx}/pub/order/listOrdersForPagination.do?type=wap",
				data:"page="+pageIndex+"&pageCount=20&status="+status,
				async:false,
				success: function(data){
					var returnData = data.pageData;
					var status = data.status;
					var userType = data.userType;

					document.getElementById("content1").innerHTML="";
					document.getElementById("content2").innerHTML="";
					document.getElementById("content3").innerHTML="";
					document.getElementById("content4").innerHTML="";
    				
					if(returnData==""){
	                    return false;
	                }
    				pageCount = data.pageCount;

    				var trs = "<ul class='hzjh'>";
    				$.each(returnData,function(i, n){
    						trs = trs + "<li onclick='javascript:window.location.href=\"${ctx}/pub/order/workerOrderView.do?type=wap&orderId=" + n.orderId + "\"' >";
						    
						    var orderStatus = "";
						    var timeTypeName = "";
						    var time = "";
						    if(n.orderStatus=='2'){
		            			orderStatus = "<font color='black'>待确认时间</font>";
                                timeTypeName = "派单时间";
                                time = n.takeTime;
		            		}else if(n.orderStatus=='3'){
		            			orderStatus = "<font color='black'>待上门</font>";
                                timeTypeName = "上门时间";
                                time = n.sureTime;
		            		}else if(n.orderStatus=='4'){
		            			orderStatus = "<font color='black'>已完成，待评价</font>";
                                timeTypeName = "完成时间";
                                time = n.endTime;
		            		}else if(n.orderStatus=='5'){
		            			orderStatus = "<font color='#999'>已关闭</font>";
                                timeTypeName = "完成时间";
                                time = n.endTime;
		            		}
						    trs = trs + "	<h3><span>" +orderStatus+ "</span>订单编号：" + n.orderSn + "</h3>";
						    trs = trs + "   <img src='${ctx}/wap/html/images/nopic.jpg' class='pic' />";
						    
						    if(n.serviceType=='1'){
		            			trs = trs + "    	<b>安装 "+n.firstCateName+"</b><br/>";
		            		}else if(n.serviceType=='2'){
		            			trs = trs + "    	<b>维修 "+n.firstCateName+"</b><br/>";
		            		}else if(n.serviceType=='3'){
		            			trs = trs + "    	<b>保养 "+n.firstCateName+"</b><br/>";
		            		}else if(n.serviceType=='4'){
		            			trs = trs + "    	<b>测量 "+n.firstCateName+"</b><br/>";
		            		}else if(n.serviceType=='5'){
		            			trs = trs + "    	<b>咨询 "+n.firstCateName+"</b><br/>";
		            		}

							trs = trs + timeTypeName+"："+time+"<br> ";
							if(n.serviceType=='1'){
		            			trs = trs + "<font color='red'>￥<span>100</span></font><br /> ";
		            		}

							if(n.orderStatus=='2'){
                                trs = trs + "<a href='#' class='button-info' id='sureTime' name='"+n.orderId+"'>确定时间</a><a href='#' class='button-none' >联系业主</a>";
							}else if(n.orderStatus=='3'){
                                trs = trs + "<a href='#' class='button-info' >确定签到</a>"
							}
							trs = trs + "</li>";
    				});
    				trs = trs + "</ul>";
    				
    				if(status=="9"){
    					$("#content1").append(trs);
    				}
    				if(status=="1"){
    					$("#content2").append(trs);
    				}
    				if(status=="2"){
    					$("#content3").append(trs);
    				}
    				if(status=="3"){
    					$("#content4").append(trs);
    				}
                    if(status=="4"){
                        $("#content5").append(trs);
                    }


			}
			});

			//确认时间按钮绑定日期插件
            $('#sureTime').date({theme:"datetime"},YCallback,NCallback);

			$("#pagination").pagination(pageCount, {
                   callback: pageselectCallback,
                   prev_text: '<< 上一页',
                   next_text: '下一页 >>',
                   items_per_page:20,//每页显示记录条数   
		           num_display_entries:2,//连续分页主体部分分页条目数   
		           current_page:pageIndex,//当前页索引
		           num_edge_entries:2//两侧首尾分页条目数
            });
		}

		function pageselectCallback(page_id,jq) {
           initData(page_id,'${requestScope.status}');
       }
       //日期选择确认回调事件，更新订单确认时间和状态
       function YCallback(sureTimeStr,orderId) {
           $.ajax({
               url:"${ctx}/pub/order/sureTimeUpdate.do?type=wap&orderId="+orderId+"&sureTimeStr="+sureTimeStr+":00",
               dataType:"text",
               async:false,
               success: function(data){
					if(data != "success"){
					    alert('系统异常请重试');
					}
                   window.location.reload();
               }
           });
       }
        //日期选择取消回调事件
       function NCallback(result) {
       }
  </script>  
  
</head>

<body onload="javascript:initData(0,'${requestScope.status}')" style="background-color: #EEF8E1;">

	<!-- 公用头部-->
	<jsp:include page="../userCenter/head.jsp"></jsp:include>
	<div id="datePlugin"></div>
	<!-- Tabs -->
	<div id="tabsholder">
	
	        <ul class="tabs">
	            <li id="tab1" onclick="initData(0,'9')" >全部</li>
	            <li id="tab2" onclick="initData(0,'1')" >待定时</li>
	            <li id="tab3" onclick="initData(0,'2')" >待上门</li>
	            <li id="tab4" onclick="initData(0,'3')" >已完成</li>
	            <li id="tab5" onclick="initData(0,'4')" >已关闭</li>
	        </ul>
	        <div class="contents marginbot">
		        <div id="content1" class="tabscontent">
		            <ul class="hzjh" id="dataTable" >
		                <!-- 
		                <li>
		                  <h3><span>待接单</span>订单编号：67986987</h3>
		                  <img src="${ctx}/wap/html/images/nopic.jpg" class="pic" />
		                  <b>马桶安装A234324</b><br />
		                  <time>下单时间：2016-9-12</time><br> 
		                  ￥<span>1</span>
		                </li>
		                 -->
		            </ul>
		        </div>
		        <div id="content2" class="tabscontent">
	            
	            </div>
	            <div id="content3" class="tabscontent">
	            
				</div>
	            <div id="content4" class="tabscontent">
	            
	           	</div>
				<div id="content5" class="tabscontent">

				</div>
	        </div>
	</div>
	<!-- /Tabs -->
	
	<div id="pagination" class="flickr" style="text-align:right;"></div>

	<table height="150px;"><tr><td >&nbsp;</td></tr></table>
  
	<!-- foot -->
	<jsp:include page="../public/foot.jsp" flush="false">
		<jsp:param name="menu" value="wd" />
	</jsp:include>
</body>
</html>
