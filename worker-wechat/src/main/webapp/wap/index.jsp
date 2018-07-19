<%@ page language="java" pageEncoding="UTF-8" isELIgnored="false"%>
<%@ taglib prefix="c" uri="/WEB-INF/tld/c.tld" %>
<c:set var="ctx" value="${pageContext.request.contextPath}" />

<!DOCTYPE html>
<html>
<head>
	<jsp:include page="public/common.jsp"></jsp:include>

	<style type="text/css">
		.m_dibu {
			border-bottom: 1px solid #afafaf;
			border-top: 1px solid #afafaf;
			height: 68px;
			width: 100%;
		}
		.m_dibu a {
			width: 32%;
			display: block;
			line-height: 68px;
			text-align: center;
			float: left;
			font-family: Microsoft YaHei;
			font-size: 15px;
			font-weight: 600;
			color: #333;
		}
	</style>
</head>

<body>
	<header>
	  <ul>
	    <li class="back">&nbsp;</li>
	    <li class="logo">服务</li>
	    <li class="more">&nbsp;</li>
	  </ul>
	</header>
	        
	<div class="main">
	  <ul class="banner clearfix">
	    <li class="top"><a href="${ctx}/pub/goodCate/firstCates.do?type=wap&serviceType=1"><img src="${ctx}/wap/html/images/fw_03.png"></a></li>
	    <li class="bottom"><a href="${ctx}/pub/goodCate/firstCates.do?type=wap&serviceType=1">预约安装</a></li>
	  </ul>
	  <ul class="mainmenu">
		<li><a href="${ctx}/pub/goodCate/firstCates.do?type=wap&serviceType=2" ><p><img src="${ctx}/wap/html/images/fw_07.png"/><span>预约维修</span></p></a></li>
		<li><a href="${ctx}/pub/goodCate/firstCates.do?type=wap&serviceType=3" ><p><img src="${ctx}/wap/html/images/fw_10.png"/><span>预约保养</span></p></a></li>
		<li><a href="${ctx}/pub/goodCate/firstCates.do?type=wap&serviceType=4" ><p><img src="${ctx}/wap/html/images/fw_14.png"/><span>预约测量</span></p></a></li>
		<li><a href="${ctx}/pub/goodCate/firstCates.do?type=wap&serviceType=5" ><p><img src="${ctx}/wap/html/images/fw_15.png"/><span>预约咨询</span></p></a></li>
	  </ul>	

	  <ul style="margin-top: 13px;margin-left: 5px;"> 
		<li><img src="${ctx}/wap/images/banner1.jpg" width="100%" height="160" /></li>
	  </ul>

		<div class="m_dibu" style="border-bottom:1px dashed #CECECE; ">
			<a href="${ctx}/pub/article/articleDetail.do?type=wap&id=101">公司介绍<img src="${ctx}/wap/images/d_xian.png" width="1px" style="float:right"></a>
			<a href="${ctx}/pub/article/articleDetail.do?type=wap&id=102">联系我们<img src="${ctx}/wap/images/d_xian.png" width="1" style="float:right"></a>
			<a href="${ctx}/pub/article/articleDetail.do?type=wap&id=104">师傅招聘<img src="${ctx}/wap/images/d_xian.png" width="1" style="float:right"></a>
		</div>
		<!--
		<div class="m_dibu" style="border-top:0px; ">
			<a href="${ctx}/pub/article/articleDetail.do?type=wap&id=2">最新动态<img src="${ctx}/wap/images/d_xian.png" width="1px" style="float:right"></a>
			<a href="${ctx}/pub/article/articleDetail.do?type=wap&id=2">自营门店</a>
			<a href="${ctx}/pub/article/articleDetail.do?type=wap&id=2">淘宝店铺</a>
		</div>
		-->


		<br/>

		<!--
		<ul style="margin-top: 13px;margin-left: 5px;">
			<li style="margin-left: 15px;">©2018-2019 惠修房屋快修版权所有，并保留所有权利</li>
			<li style="margin-left: 85px;margin-top: 10px;">ICP备1111111-1号</li>
		</ul>
		<br/><br/>
		-->

		<!--置顶-->
		<div style="position: fixed;bottom: 50px;right: 10px;margin-bottom: 10px;"><a href="javascript:window.scrollTo(0,0);"><img src="${ctx}/wap/images/fanhui.png" width="42px"></a></div>

	</div>
	

	<!-- 公用底部 -->
	<jsp:include page="public/foot.jsp" flush="false">
		<jsp:param name="menu" value="fw" />
	</jsp:include>
	
</body>
</html>
