<%@ page language="java" pageEncoding="UTF-8" isELIgnored="false"%>
<%@ taglib prefix="c" uri="/WEB-INF/tld/c.tld" %>
<c:set var="ctx" value="${pageContext.request.contextPath}" />

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<!-- 公用JS|CSS-->
	<jsp:include page="public/common.jsp"></jsp:include>
	
	<link rel="stylesheet" href="${ctx }/common/css/pagination.css" />
	<script src="${ctx}/common/js/jquery.js" type="text/javascript" charset="UTF-8"></script>
    <script src="${ctx }/common/js/jquery.pagination.js" type="text/javascript" charset="UTF-8"></script>
    <script src="${ctx }/common/js/jquery-impromptu.3.2.js" type="text/javascript" charset="UTF-8"></script>
	<script type="text/javascript">
		function initData(pageIndex){
			var pageCount;
			$.ajax({
				type:"post",
				dataType:"json",
				url:"${ctx}/pub/complaint/listComplaintForPagination.do",
				data:"page="+pageIndex+"&pageCount=10",
				async:false,
				success: function(data){
					var resultData = data.pageData;
					var UL = document.getElementById("dataTable");
						UL.innerHTML="";
						
	    			pageCount = data.pageCount;
					$.each(resultData,function(i, n){
						var trs = "<li>";
							trs += "<div class='spmc' style='width: 40%;word-break:break-all;'>"+n.compTime+"</div>" ;
							trs += "<div class='sl' style='text-align: right;width:13%;word-break:break-all;'>";
							if(n.status==0){
			            		trs += "未处理";
			            	}else if(n.status==1){
			            		trs += "已处理";
			            	}
				            trs += "</div>";
							trs += "<div class='sl' style='text-align: right;width:31%;word-break:break-all;'>";
							trs += "  <a style='cursor:pointer;text-decoration: underline;' onclick='javascript:window.location.href=\"${ctx}/pub/complaint/complaintDetail.do?id="+n.id+"&type=wap\"' >";
							trs += "   查看详情</a>";
							trs += "</div>";
	  						trs += "</li>";
	            		$("#dataTable").append(trs);
					});
				}
			});
			$("#pagination").pagination(pageCount, {
                   callback: pageselectCallback,
                   prev_text: '<< 上一页',
                   next_text: '下一页 >>',
                   items_per_page:10,//每页显示记录条数   
		           num_display_entries:3,//连续分页主体部分分页条目数   
		           current_page:pageIndex,//当前页索引
		           num_edge_entries:2//两侧首尾分页条目数
            });
		}
		function pageselectCallback(page_id,jq) {
           initData(page_id);
       }
  </script> 
  

<body onload="javascript:initData(0)">

	<!-- 公用头部 -->
	<jsp:include page="public/header.jsp"></jsp:include>
	 
	<div class="abouto">
	<form id="messageForm" name="messageForm" action="${ctx}/pub/complaint/saveComplaint.do?type=wap" method="post" >
	<table width="100%" cellspacing="0" cellpadding="0" >
	   <tr>
	    	<td width="27%" ><font size="3"><strong>建议内容：</strong></font></td>
	   </tr>
	   <tr>
	    	<td width="60%" >
	        	<textarea name="content" id="content" cols="40" rows="8" style="width: 98%;border: 1px solid #e1e1e1;height: 90px;"></textarea>    
	       	</td>
	   </tr>
	   <tr>
	    <td colspan="2">
	    	<div style="margin: 5px 71px 0;">
		    	<input class="submit" type="submit" value="提交"  />
		    </div>
		</td>
	   </tr>
	</table>
	</form>
	</div>
	
	<ul class="wdddf" style="margin: -10px 0 0;">
	    <font size="2">
	    <li class="spmc" style='width: 40%;text-align: left;'>提交时间</li>
	    <li class="sl" style='width: 25%;text-align: left;margin-left:15px;'>状态</li>
	    <li class="sl" style='width: 25%;text-align: left;margin-left:7px;'>操作</li> 
	    </font>
	</ul>
	<ul class="wdddr" id="dataTable"></ul>
	
	<div id="pagination" class="flickr" style="text-align:right;"></div></div>
	
	<table height="120px;"><tr><td >&nbsp;</td></tr></table>
	
	<!-- 公用底部 -->
	<jsp:include page="public/foot.jsp"></jsp:include>
</body>
</html>