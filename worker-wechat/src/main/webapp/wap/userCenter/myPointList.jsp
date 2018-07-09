<%@ page language="java" pageEncoding="UTF-8" isELIgnored="false"%>
<%@ taglib prefix="c" uri="/WEB-INF/tld/c.tld" %>
<c:set var="ctx" value="${pageContext.request.contextPath}" />

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<!-- 公用JS|CSS-->
	<jsp:include page="../public/common.jsp"></jsp:include>
	<link rel="stylesheet" href="${ctx}/wap/userCenter/css/style.css">
	<link rel="stylesheet" href="${ctx }/common/css/pagination.css" />
	<script src="${ctx}/common/js/jquery-1.9.1.min.js" type="text/javascript" charset="UTF-8"></script>
    <script src="${ctx }/common/js/jquery.pagination.js" type="text/javascript" charset="UTF-8"></script>
    <script src="${ctx }/common/js/jquery-impromptu.3.2.js" type="text/javascript" charset="UTF-8"></script>
	<script type="text/javascript">
		function initData(pageIndex){
			var pageCount;
			$.ajax({
				type:"post",
				dataType:"json",
				url:"${ctx}/pub/point/listMyPointsForPagination.do",
				data:"page="+pageIndex+"&pageCount=10&type=wap",
				async:false,
				success: function(data){
					var returnData = data.pageData;
					var UL = document.getElementById("dataTable");
					UL.innerHTML="";
					
					var html_ = "";
					html_+="<tr>";
			        html_+="  <td align='center'><strong>时间</strong></td>";
			        html_+="  <td align='center'><strong>积分</strong></td>";
			        html_+="  <td align='center'><strong>备注</strong></td>";
			        html_+="</tr>";
					
					
					if(returnData==""){
	                    return false;
	                }
    				pageCount = data.pageCount;
    
    				$.each(returnData,function(i, n){
					      html_+="<tr>";
					      html_+="    	<td bgcolor='#FFFFFF' align='center'>"+n.createTime+"</td>";
					      html_+="    	<td bgcolor='#FFFFFF' align='center'>"+n.point+"</td>";
					      html_+="    	<td bgcolor='#FFFFFF' align='center'>"+n.pointDesc+"</td>";
					      html_+="</tr>";
					});
					UL.innerHTML=html_;
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
           initData(page_id);
       }
	</script> 
</head>

<body onload="javascript:initData(0)">

	<!-- 公用头部-->
	<jsp:include page="head.jsp"></jsp:include>

	<ul class="round">
			<li class="title">
				<a href="${ctx}/pub/menber/centerInit.do?type=wap" >返回</a>
				<a href="${ctx}/pub/good/goodList.do?type=wap" >积分购物</a>
				<font size="2" color="red">我的积分：${m.point}</font>
			</li>
	</ul>
	
	<div class="yqy">
	<table width="100%" border="0" align="center" cellpadding="5" cellspacing="1" bgcolor="#dedede" id="dataTable">
		   		
	</table>
	<div id="pagination" class="flickr" style="text-align:right;margin-right: -6px;"></div>
	</div>
	
	<br>
	<div class="yqy">
		<font size="3" ><strong>积分政策：1元=100积分</strong></font><br>
		1、新注册成功，送100积分。<br>
		2、增加被托管人，送100积分。<br>
		3、邀请新人加入，送200积分。<br>
		4、每天签到，送10积分。<br>
	</div>
	
	<ul class="round">
			<li class="title">
				<!-- 
				<a href="${ctx}/pub/advert/advertList.do?type=wap&cate=2" >点广告赚积分</a>
				<a href="${ctx}/pub/menber/centerInit.do?type=wap" >积分兑换账户余额</a>
				 -->
				<font size="2" color="red">&nbsp;</font>
			</li>
	</ul>

	<br><br><br>
	<!-- foot -->
	<jsp:include page="../public/foot.jsp"></jsp:include>
	
</body>
</html>