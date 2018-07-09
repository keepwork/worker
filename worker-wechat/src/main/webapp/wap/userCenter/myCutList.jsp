<%@ page language="java" pageEncoding="UTF-8" isELIgnored="false"%>
<%@ taglib prefix="c" uri="/WEB-INF/tld/c.tld" %>
<c:set var="ctx" value="${pageContext.request.contextPath}" />
<%
	String menId = (String)request.getAttribute("menId");
%>
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
		function initData(pageIndex,menId){
			var pageCount;
			$.ajax({
				type:"post",
				dataType:"json",
				url:"${ctx}/pub/cut/listMyCutForPagination.do",
				data:"page="+pageIndex+"&pageCount=10&type=wap&menId="+menId,
				async:false,
				success: function(data){
					var returnData = data.pageData;
					var UL = document.getElementById("dataTable");
					UL.innerHTML="";
					
					var html_ = "";
					html_+="<tr style='height: 20px;'>";
			        html_+="  <td align='center'><strong>扣款类型</strong></td>";
			        html_+="  <td align='center'><strong>扣款金额（元）</strong></td>";
			        html_+="  <td align='center'><strong>扣款时间</strong></td>";
			        html_+="</tr>";
					
					
					if(returnData==""){
	                    return false;
	                }
    				pageCount = data.pageCount;
    
    				$.each(returnData,function(i, n){
					      html_+="<tr style='height: 30px;'>";
					      html_+="    	<td bgcolor='#FFFFFF' align='center'>"+n.type+"</td>";
					      html_+="    	<td bgcolor='#FFFFFF' align='center'>"+n.price+"</td>";
					      html_+="    	<td bgcolor='#FFFFFF' align='center'>"+n.createTime+"</td>";
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
           initData(page_id,'<%=menId%>');
       }
	</script> 
</head>

<body onload="javascript:initData(0,'<%=menId%>')">

	<!-- 公用头部-->
	<jsp:include page="head.jsp"></jsp:include>

	<ul class="round">
			<li class="title">
				<a href="${ctx}/pub/menber/centerInit.do?type=wap" >返回用户中心</a>
				<!-- 
				<a href="${ctx}/pub/good/goodList.do?type=wap" >积分购物</a>-->
				<font size="2" color="red">&nbsp;</font>
				 
			</li>
	</ul>
	
	<div class="yqy">
	<table width="100%" border="0" align="center" cellpadding="5" cellspacing="1" bgcolor="#dedede" id="dataTable">
		   		
	</table>
	<div id="pagination" class="flickr" style="text-align:right;margin-right: -6px;"></div>
	</div>
	
	<br><br><br>
	<!-- foot -->
	<jsp:include page="../public/foot.jsp"></jsp:include>
	
</body>
</html>