<%@ page language="java" pageEncoding="UTF-8" isELIgnored="false"%>
<%@ taglib prefix="c" uri="/WEB-INF/tld/c.tld" %>
<c:set var="ctx" value="${pageContext.request.contextPath}" />

<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1" />
	<meta name="viewport" content="width=device-width, initial-scale=1.0, user-scalable=no"/>
	<title>选择产品型号</title>
	<link rel="stylesheet" href="${ctx}/wap/html/css/Basc.css" />
	<link rel="stylesheet" href="${ctx}/wap/html/css/demo.css" />
	<link rel="stylesheet" href="${ctx}/wap/css/jquery.alertable.css">
	<script src="${ctx}/wap/js/alertable.js"></script>
	<script src="${ctx}/wap/js/jquery.alertable.min.js"></script>
	<script type="text/javascript" src="${ctx}/wap/html/js/tytabs.jquery.min.js" charset="UTF-8"></script>
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
					
					document.getElementById("content1").innerHTML="";
					document.getElementById("content2").innerHTML="";
					document.getElementById("content3").innerHTML="";
					document.getElementById("content4").innerHTML="";
					document.getElementById("content5").innerHTML="";

					if(returnData==""){
	                    return false;
	                }
    				pageCount = data.pageCount;
    				
    				var trs = "<ul class='hzjh'>";
    				$.each(returnData,function(i, n){
    						trs = trs + "<li onclick='javascript:window.location.href=\"${ctx}/pub/order/myOrderView.do?type=wap&orderId=" + n.orderId + "\"' >";
						    
						    var orderStatus = "";
						    if(n.orderStatus=='1'){
		            			orderStatus = "<font color='black'>待派单</font>";
		            		}else if(n.orderStatus=='2'){
		            			orderStatus = "<font color='black'>已派单</font>";
		            		}else if(n.orderStatus=='3'){
		            			orderStatus = "<font color='black'>已确认时间</font>";
		            		}else if(n.orderStatus=='4'){
		            			orderStatus = "<font color='black'>已上门</font>";
		            		}else if(n.orderStatus=='5'){
                                orderStatus = "<font color='black'>已开始施工</font>";
                            }else if(n.orderStatus=='6'){
                                orderStatus = "<font color='black'>已完成施工</font>";
		            		}else if(n.orderStatus=='7'){
                                orderStatus = "<font color='green'>已评价</font>";
                            }else if(n.orderStatus=='8'){
                                orderStatus = "<font color='red'>已取消</font>";
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
		            		
							trs = trs + "下单时间："+n.orderTime+"<br> ";
							if(n.serviceType!='5'){
		            			trs = trs + "<font color='red'>￥<span>"+n.totalPrice+"</span></font><br /> ";
		            		}
							//alert(n.desc2 == 'null');
		            		//按钮显示模块
                            if(n.orderStatus=='3'){//已确认时间，显示已上门按钮
                                trs = trs + "<a href='#' onclick='newconfirm(event,this)' class='button-info' id='sureArriveHome'  name='"+n.orderId+"'>师傅已到</a>";
							}else if(n.orderStatus=='6'){//已完成施工，显示评价按钮
								trs = trs + "<a href='#' onclick='goAppraise(event,this)'class='button-info' name='"+n.orderId+"'>&emsp;评价&emsp;</a>"
							}
							trs = trs + "</li>";
    				});
    				trs = trs + "</ul>";
    				
    				if(status=="9"){//全部
    					$("#content1").append(trs);
    				}
    				if(status=="1"){//进行中
    					$("#content2").append(trs);//（除已完成施工的）
    				}
    				if(status=="2"){//待支付（未完成最后一次支付的）
    					$("#content3").append(trs);
    				}
    				if(status=="3"){//待评价（已完成施工的）
    					$("#content4").append(trs);
    				}
                    if(status=="4"){//已完成(已评价和已取消的)
                        $("#content5").append(trs);
                    }
    				
			}
			});
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
		//确认弹框
        function newconfirm(e,obj) {
            e.stopPropagation();//阻止点击事件向上冒泡
		    var id = $(obj).attr("id");
            var orderId = $(obj).attr('name');
		    if(id == "sureArriveHome"){
                $.alertable.confirm('确定师傅已上门？').then(function() {
                    sureArriveHome(orderId);
                }, function() {
                    console.log('Confirmation canceled');
                });
			}else if(id == "cancelOrder"){
                $.alertable.confirm('确定取消订单？').then(function() {
                    cancelOrder(orderId);
                }, function() {
                    console.log('Confirmation canceled');
                });
			}


        }
        //确认师傅已上门
        function sureArriveHome(orderId) {
            $.ajax({
                url:"${ctx}/pub/order/sureArriveHomeUpdateStatus.do?type=wap&orderId="+orderId,
                dataType:"text",
                async:false,
                success: function(data){
                    if(data != "success"){
                        $.alertable.alert('系统异常请重试!').always(function() {
                            console.log('Alert dismissed');
                        });
                    }
                    window.location.reload();
                }
            });
        }

        //取消订单
        function cancelOrder(orderId) {
            $.ajax({
                url:"${ctx}/pub/order/cancelOrder.do?type=wap&orderId="+orderId,
                dataType:"text",
                async:false,
                success: function(data){
                    if(data != "success"){
                        $.alertable.alert('系统异常请重试!').always(function() {
                            console.log('Alert dismissed');
                        });
                    }
                    window.location.reload();
                }
            });
        }

        //去评价
        function goAppraise(e,obj) {
            e.stopPropagation();//阻止点击事件向上冒泡
            var orderId = $(obj).attr('name');
            window.location.href='${ctx}/wap/appraiseWrite.jsp?type=wap&orderId='+orderId;
        }

  </script>
</head>

<body onload="javascript:initData(0,'${requestScope.status}')" style="background-color: #EEF8E1;">

	<!-- 公用头部-->
	<jsp:include page="head.jsp"></jsp:include>

	<!-- Tabs -->
	<div id="tabsholder">
	
	        <ul class="tabs">
	            <li id="tab1" onclick="initData(0,'9')" >全部</li>
	            <li id="tab2" onclick="initData(0,'1')" >进行中</li>
	            <li id="tab3" onclick="initData(0,'2')" >待支付</li>
	            <li id="tab4" onclick="initData(0,'3')" >待评价</li>
	            <li id="tab5" onclick="initData(0,'4')" >已完成</li>
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

	<!--刷新页面-->
	<div style="position: fixed;bottom: 50px;right: 10px;margin-bottom: 10px;"><a href="javascript:window.location.reload();"><img src="${ctx}/wap/images/shuaxin.png" width="42px"></a></div>
	<div id="pagination" class="flickr" style="text-align:right;"></div>

	<table height="150px;"><tr><td >&nbsp;</td></tr></table>
  
	<!-- foot -->
	<jsp:include page="../public/foot.jsp" flush="false">
		<jsp:param name="menu" value="wd" />
	</jsp:include>
</body>

</html>
