<%@ page language="java" pageEncoding="UTF-8" isELIgnored="false"%>
<%@ taglib prefix="c" uri="/WEB-INF/tld/c.tld" %>
<c:set var="ctx" value="${pageContext.request.contextPath}" />

<!DOCTYPE html>
<html>
<head>
	<jsp:include page="public/common.jsp"></jsp:include>
	<link rel="stylesheet" type="text/css" href="${ctx }/wap/workerCenter/css/public.css">
	<script type="text/javascript" charset="UTF-8" src="${ctx}/wap/js/TouchSlide.1.1.js" ></script>
	<script type="text/javascript" charset="UTF-8" src="${ctx}/wap/js/scroll.js" ></script>
	<script type="text/javascript">
		$(function(){
			$("div.zjmmbox").myScroll({
				speed:40, //数值越大，速度越慢
				rowHeight:34 //li的高度
			});
		});
	</script>
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
		.zjmd{background:#e5f9b5;border-radius:10px;width:100%;margin:0 auto;padding:0.2rem 0}
		.zjmd h2{color:#e2473e; margin-top:10px;text-align:center;}
		.zjmd .zjmmbox {background:#fff; border:1px solid #898989;height:3rem; overflow:hidden;width:90%;margin:0.5em auto}
		.zjmd .zjmmbox ul{margin:15px}
		.zjmd .zjmmbox ul li{line-height:34px;font-size: 0.6rem;}
		.zjmd .zjmmbox ul li span{padding:0 30px;}
		.zpxx span{color:#e2473e;font-size: 1.5em;}
	</style>
</head>

<body>
	<header>
	  <ul>
		  <li class="back"><img src="${ctx}/wap/images/logo.png" style="width: 2.6rem;height: 2rem;margin-top: 0.2rem;"/></li>
		  <li class="logo"><strong><font size="5">88修房屋快修</font></strong></li>
	    <li class="more">&nbsp;</li>
	  </ul>
	</header>

	<!--
	<header class="header" id="header">
		<a href="javascript:history.go(-1)" target=_self class="back">返回</a>
		<h1>88修</h1>
	</header>
	<div style="margin-top: 2rem"></div>
	-->
	<!--Banner-->
	<div class="banner" id="index-banner">
		<div class="banner-pics">
			<ul class="banner-pics-list OnSale-banner">
				<li><a href="#"><img src="${ctx}/wap/images/ind-banner1.jpg" alt=""/></a></li>
				<li><a href="#"><img src="${ctx}/wap/images/ind-banner2.png" alt=""/></a></li>
				<li><a href="#"><img src="${ctx}/wap/images/ind-banner3.png" alt=""/></a></li>
			</ul>
		</div>
		<ul class="banner-tit">
			<li class="on"></li>
			<li></li>
			<li></li>
		</ul>
	</div>
	<script type="text/javascript">
        TouchSlide({
            slideCell:"#index-banner",
            titCell:".banner-tit li",//开启自动分页 autoPage:true ，此时设置 titCell 为导航元素包裹层
            mainCell:".banner-pics-list",
            effect:"left",
            autoPlay:true,//自动播放
            //autoPage:true, //自动分页
        })
	</script>
	<!--banner-end-->
	        
	<div class="main">
	<!--
	  <ul class="banner clearfix">
	    <li class="top"><a href="${ctx}/pub/goodCate/firstCates.do?type=wap&serviceType=1"><img src="${ctx}/wap/html/images/fw_07.png"></a></li>
	    <li class="bottom"><a href="${ctx}/pub/goodCate/firstCates.do?type=wap&serviceType=1">预约安装</a></li>
	  </ul>
     -->
	  <ul class="mainmenu" >
		<li><a href="${ctx}/pub/goodCate/firstCates.do?type=wap&serviceType=5" ><p><img src="${ctx}/wap/html/images/fw_07.png"/><span style="font-size:0.8rem">预约施工</span></p></a></li>
		<li><a href="${ctx}/pub/goodCate/firstCates.do?type=wap&serviceType=2" ><p><img src="${ctx}/wap/html/images/fw_03.png"/><span style="font-size:0.8rem">预约维修</span></p></a></li>
		<li><a href="${ctx}/pub/goodCate/firstCates.do?type=wap&serviceType=3" ><p><img src="${ctx}/wap/html/images/fw_10.png"/><span style="font-size:0.8rem">预约保养</span></p></a></li>
		<li><a href="${ctx}/pub/goodCate/firstCates.do?type=wap&serviceType=4" ><p><img src="${ctx}/wap/html/images/fw_14.png"/><span style="font-size:0.8rem">预约测量</span></p></a></li>
		<li><a href="${ctx}/pub/goodCate/firstCates.do?type=wap&serviceType=1" ><p><img src="${ctx}/wap/html/images/fl_15.png"/><span style="font-size:0.8rem">预约安装</span></p></a></li>
		<li><a href="javacript:void(0);" ><p><img src="${ctx}/wap/html/images/fl_20.png"/><span style="font-size:0.8rem">全部类型</span></p></a></li>
	  </ul>


	  <%--<ul style="margin-top: 13px;margin-left: 5px;"> --%>
		<%--<li><img src="${ctx}/wap/images/banner1.jpg" width="100%" height="160" /></li>--%>
	  <%--</ul>--%>

		<!--二手置换 货运配送开始-->
		<div class="ind-item" style="margin-top: 0.4rem;">
			<a href="${ctx}/wap/book1.jsp">
				<div class="ind-item-img"><img src="${ctx}/wap/images/book_.jpg" style="display: block; width: 100%;height: auto;"></div>
				<div class="ind-item-txt">
					<h3 class="red">装修指南</h3>
					<p>装修知识知多少？不知道这些就out了</p>
				</div>
			</a>
		</div>
		<div class="ind-item">
			<a href="${ctx}/wap/book2.jsp">
				<div class="ind-item-img"><img src="${ctx}/wap/images/book.jpg" style="display: block; width: 100%;height: auto;"></div>
				<div class="ind-item-txt">
					<h3 class="green">选材经验</h3>
					<p>现在装修用的防水材料，要注意什么？</p>
				</div>
			</a>
		</div>
		<!--二手置换 货运配送结束-->

		<!--中奖名单转盘 开始-->
		<div class="zjmd">
			<h2>最新订单</h2>
			<div class="zjmmbox">
				<ul>
					<li>139****2100<span>长沙</span>墙面粉刷</li>
					<li>135****3826<span>长沙</span>水电维修</li>
					<li>139****1262<span>长沙</span>门窗维修</li>
					<li>189****5529<span>长沙</span>厨房改造</li>
					<li>132****2415<span>长沙</span>打孔安转</li>
					<li>180****8813<span>长沙</span>泥瓦维修</li>
					<li>151****1156<span>长沙</span>防水补漏</li>
					<li>136****7815<span>长沙</span>水电维修</li>
					<li>152****4156<span>长沙</span>水电维修</li>
					<li>188****3441<span>长沙</span>局部改造</li>
					<li>133****1818<span>长沙</span>地板维修</li>
					<li>189****5966<span>长沙</span>墙面粉刷</li>
					<li>138****5326<span>长沙</span>家具维修</li>
					<li>186****8915<span>长沙</span>门窗维修</li>
					<li>159****1222<span>长沙</span>整体翻新</li>
					<li>135****7715<span>长沙</span>水电维修</li>
					<li>181****6833<span>长沙</span>厨卫漏水</li>
					<li>156****9071<span>长沙</span>地板打蜡</li>
					<li>180****4689<span>长沙</span>水电维修</li>
					<li>182****1398<span>长沙</span>水电维修</li>
					<li>137****3900<span>长沙</span>管道疏通</li>
					<li>155****9923<span>长沙</span>防水补漏</li>
					<li>186****2110<span>长沙</span>墙面粉刷</li>
					<li>155****1466<span>长沙</span>水电维修</li>
					<li>158****1262<span>长沙</span>门窗维修</li>
					<li>135****8829<span>长沙</span>厨房改造</li>
					<li>132****1286<span>长沙</span>打孔安转</li>
					<li>180****2284<span>长沙</span>泥瓦维修</li>
					<li>139****1515<span>长沙</span>防水补漏</li>
					<li>136****9903<span>长沙</span>水电维修</li>
					<li>189****7816<span>长沙</span>水电维修</li>
					<li>156****2263<span>长沙</span>局部改造</li>
					<li>151****3562<span>长沙</span>地板维修</li>
					<li>133****4422<span>长沙</span>墙面粉刷</li>
					<li>139****4862<span>长沙</span>家具维修</li>
					<li>186****3806<span>长沙</span>门窗维修</li>
					<li>186****9026<span>长沙</span>整体翻新</li>
					<li>159****8511<span>长沙</span>水电维修</li>
					<li>183****6033<span>长沙</span>厨卫漏水</li>
					<li>134****2366<span>长沙</span>地板打蜡</li>
					<li>159****6859<span>长沙</span>水电维修</li>
					<li>188****6235<span>长沙</span>水电维修</li>
					<li>182****5552<span>长沙</span>管道疏通</li>
					<li>139****3710<span>长沙</span>防水补漏</li>

				</ul>
			</div>
		</div>
		<!--中奖名单转盘 结束-->

		<div style="width: 100%;text-align: center;background-color:#fff">
			<div style="width: 100%;height: auto;overflow: hidden;clear: both;display: block;">
				<img src="${ctx}/wap/images/ywlc.jpg" style="max-width: 100%;overflow: hidden;"/>
			</div>
		</div>

		<div class="m_dibu" style="border-bottom:1px dashed #CECECE;">
			<a href="${ctx}/pub/article/articleDetail.do?type=wap&id=101">公司介绍<img src="${ctx}/wap/images/d_xian.png" width="1px" style="float:right"></a>
			<a href="${ctx}/pub/article/articleDetail.do?type=wap&id=102">联系我们<img src="${ctx}/wap/images/d_xian.png" width="1" style="float:right"></a>
			<a href="${ctx}/pub/article/articleDetail.do?type=wap&id=104">师傅招聘</a>
		</div>
		<div class="m_dibu" style="border-top:0px; ">
			<a href="${ctx}/pub/article/articleDetail.do?type=wap&id=107">扫码支付<img src="${ctx}/wap/images/d_xian.png" width="1px" style="float:right"></a>
			<a href="${ctx}/pub/article/articleDetail.do?type=wap&id=103">报价说明<img src="${ctx}/wap/images/d_xian.png" width="1px" style="float:right"></a>
			<a href="${ctx}/pub/article/articleDetail.do?type=wap&id=105">保修说明</a>
		</div>

		<ul style="padding-top:10px; margin-bottom:0px;font-family:微软雅黑; font-size:12px;background: #f5f5f5;text-align:center;">
			<li>©2018-2019 88修房屋快修版权所有，并保留所有权利</li>
			<li>
				湘ICP备18016571号-1
				<!-- cnzz -->
				<script type="text/javascript">var cnzz_protocol = (("https:" == document.location.protocol) ? " https://" : " http://");document.write(unescape("%3Cspan id='cnzz_stat_icon_1259784926'%3E%3C/span%3E%3Cscript src='" + cnzz_protocol + "s11.cnzz.com/z_stat.php%3Fid%3D1259784926%26show%3Dpic' type='text/javascript'%3E%3C/script%3E"));</script>
			</li>
		</ul>

		<!--置顶-->
		<div style="position: fixed;bottom: 50px;right: 10px;margin-bottom: 10px;">
			<a href="javascript:window.scrollTo(0,0);"><img src="${ctx}/wap/images/fanhui.png" width="42px"></a></div>

	</div>
	

	<!-- 公用底部 -->
	<jsp:include page="public/foot.jsp" flush="false">
		<jsp:param name="menu" value="fw" />
	</jsp:include>


</body>
</html>
