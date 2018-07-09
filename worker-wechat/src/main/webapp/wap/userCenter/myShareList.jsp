<%@ page language="java" pageEncoding="UTF-8" isELIgnored="false"%>
<%@ taglib prefix="c" uri="/WEB-INF/tld/c.tld" %>
<c:set var="ctx" value="${pageContext.request.contextPath}" />

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<!-- 公用JS|CSS-->
	<jsp:include page="../public/common.jsp"></jsp:include>
	<link rel="stylesheet" href="${ctx}/wap/userCenter/css/style.css">
	<link rel="stylesheet" href="${ctx }/common/css/pagination.css" />
	<script src="${ctx}/common/js/jquery.js" type="text/javascript" charset="UTF-8"></script>
    <script src="${ctx}/common/js/jquery.pagination.js" type="text/javascript" charset="UTF-8"></script>
    <script src="${ctx}/common/js/jquery-impromptu.3.2.js" type="text/javascript" charset="UTF-8"></script>
    
	<script type="text/javascript">
		function initData(pageIndex){
			var pageCount;
			$.ajax({
				type:"post",
				dataType:"json",
				url:"${ctx}/pub/share/listMyShareMenbersForPagination.do",
				data:"page="+pageIndex+"&pageCount=20&type=wap",
				async:false,
				success: function(data){
					var returnData = data.pageData;
					var UL = document.getElementById("dataTable");
					UL.innerHTML="";
					
					if(returnData==""){
	                    return false;
	                }
    				pageCount = data.pageCount;
    
    				var html = "";
    				$.each(returnData,function(i, n){
					      html += "<li style='background:#FFF;'>";
					      html += "    <img src='"+n.headimgurl+"' class='pic' style='width: 80px;height: 60px;' />";
					      html += "    <b style='font-size: 12px;'>"+n.realName+"</b><br />";
					      html += "    <font size='2'>账户余额："+n.balanceFee+"元</font><br /> ";
					      html += "</li>";
					});
					UL.innerHTML = html;
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
           initData(page_id);
       }
  </script> 
	  
</head>

<body onload="javascript:initData(0)">
	
	<!-- 公用头部-->
	<jsp:include page="head.jsp"></jsp:include>
	

	<ul class="hzjh" id="dataTable" >
	    
	</ul>
	
	<div id="pagination" class="flickr" style="text-align:right;"></div></div>
	
	<table height="120px;"><tr><td >&nbsp;</td></tr></table>
	
	<!-- foot -->
	<jsp:include page="../public/foot.jsp"></jsp:include>
	
</body>
</html>