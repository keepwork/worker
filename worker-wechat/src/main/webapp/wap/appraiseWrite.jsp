<%@ page language="java" pageEncoding="UTF-8" isELIgnored="false"%>
<%@ taglib prefix="c" uri="/WEB-INF/tld/c.tld" %>
<c:set var="ctx" value="${pageContext.request.contextPath}" />

<!DOCTYPE html>
<html>

	<head>
		<meta charset="utf-8">
		<meta name="viewport" content="initial-scale=1.0, maximum-scale=1.0, user-scalable=no" />
		<title>评价</title>
		<link rel="stylesheet" href="${ctx}/wap/css/apprise.css" type="text/css"/>
		<script type="text/javascript" src="${ctx}/wap/js/jquery-1.8.2.min.js" ></script>
	</head>
	
	<body>
		<!--头部  star
		<header>
			<a href="javascript:history.go(-1);">
				<div class="_left"><img src="html/images/back.png" width="50" height="40"></div>
				   评价
			</a>
		</header>
		-->
		<header class="header" id="header">
			<a href="javascript:history.go(-1);" target="_self" class="back">返回</a>
			<h1>评价</h1>
		</header>
		<!--头部 end-->
        <!--内容 star-->
		<div class="contaniner fixed-cont">
			<!--1-->
			<section class="assess">
				<p>
					<textarea rows="7" placeholder="师傅的服务如何，服务周到吗，服务态度好吗，技术过关吗？～～"></textarea>
				</p>
				<!--<ul>
					<li>评价</li>
					<li class="assess-right">
						<div><input type="radio" name="radio" value="radio1"/><img src="images/hua.png"/></div>
						<div><input type="radio" name="radio" value="radio2"/><img src="images/huah.png"/></div>
						<div><input type="radio" name="radio" value="radio3"/><img src="images/huae.png"</div>
					</li>
				</ul>-->
			</section>
			
			<!--2-->
			<section class="main">
			<div class="main-wrap">
				<span class="revtit">服务质量:</span>
				<div id="mydiv1" currentIndex="0" class="mydiv">
	    			<ul class="star_ul">
				        <li num="1"><img src="images/star_empty.png" class="xing_hui"/></li>
				        <li num="2"><img src="images/star_empty.png"  class="xing_hui"/></li>
				        <li num="3"><img src="images/star_empty.png"  class="xing_hui"/></li>
				        <li num="4"><img src="images/star_empty.png"  class="xing_hui"/></li>
				        <li num="5"><img src="images/star_empty.png"  class="xing_hui"/></li>
			   		</ul>
				</div>
			</div>
			
			<div class="main-wrap">
				<span class="revtit">服务态度:</span>
				<div id="mydiv2" currentIndex="0" class="mydiv">
	    			<ul class="star_ul">
				        <li num="1"><img src="images/star_empty.png" class="xing_hui"/></li>
				        <li num="2"><img src="images/star_empty.png"  class="xing_hui"/></li>
				        <li num="3"><img src="images/star_empty.png"  class="xing_hui"/></li>
				        <li num="4"><img src="images/star_empty.png"  class="xing_hui"/></li>
				        <li num="5"><img src="images/star_empty.png"  class="xing_hui"/></li>
			   		</ul>
				</div>
			</div>
		
			<div class="main-wrap">
				<span class="revtit">准时情况:</span>
				<div id="mydiv3" currentIndex="0" class="mydiv">
	    			<ul class="star_ul">
				        <li num="1"><img src="images/star_empty.png" class="xing_hui"/></li>
				        <li num="2"><img src="images/star_empty.png"  class="xing_hui"/></li>
				        <li num="3"><img src="images/star_empty.png"  class="xing_hui"/></li>
				        <li num="4"><img src="images/star_empty.png"  class="xing_hui"/></li>
				        <li num="5"><img src="images/star_empty.png"  class="xing_hui"/></li>
			   		</ul>
				</div>
			</div>
	</section>
		</div>
		<!--内容 end-->
        
        <!--底部 star-->
		<footer class="assess-footer fixed-footer ">
			<ul>
				<!--<li>
					<input type="checkbox" id="ass" />
					<label for="ass" onselectstart="return false">匿名评价</label>
				</li>-->
				<input type="button" onclick="saveApprise()" value="发表评论" />
			</ul>
		</footer>
       <!--底部  end-->
		<script type="text/javascript">
        var isclick = false;
        function change(mydivid,num) {
            if (!isclick) {
                var tds = $("#"+mydivid+" ul li");
                for (var i = 0; i < num; i++) {
                    var td = tds[i];
                    $(td).find("img").attr("src","images\/star_full.png");
                }
                var tindex = $("#"+mydivid).attr("currentIndex");
                tindex = tindex==0?0:tindex+1;
                for (var j = num; j < tindex; j++) {
                    var td = tds[j];
                    $(td).find("img").attr("src","images\/star_empty.png");
                }
                $("#"+mydivid).attr("currentIndex",num);
            }
        }
        function repeal(mydivid,num) {
            if (!isclick) {
                var tds = $("#"+mydivid+" ul li");
                var tindex = $("#"+mydivid).attr("currentIndex");
                tindex = tindex==0?0:tindex+1;
                for (var i = tindex; i < num; i++) {
                    var td = tds[i];
                    $(td).find("img").attr("src","images\/star_empty.png");
                }
                $("#"+mydivid).attr("currentIndex",num);
            }
        }
        function change1(mydivid,num) {
            if (!isclick) {
                change(mydivid,num);

            }
            else {
                alert("Sorry,You had clicked me!");
            }
        }
        $(function(){
            initEvent('mydiv1');
            initEvent('mydiv2');
            initEvent('mydiv3');
        });
        function initEvent(mydivid) {
            var tds = $("#"+mydivid+" ul li");
            for (var i = 0; i < tds.length; i++) {
                var td = tds[i];
                $(td).live('mouseover',function(){var num = $(this).attr("num");change(mydivid,num);});
                $(td).live('click',function(){var num = $(this).attr("num");change1(mydivid,num);});
            }
        }

		//获取URL参数
        function getQueryString(name) {
            var reg = new RegExp("(^|&)" + name + "=([^&]*)(&|$)", "i");
            var r = window.location.search.substr(1).match(reg);
            if (r != null) return unescape(r[2]);
            return null;
        }

        //保存评价
        function saveApprise()
        {
            var appriseDesc = $("textarea").val();
            var orderId = getQueryString('orderId');
            var zhiliangScore = $("#mydiv1").attr("currentIndex");
            if(zhiliangScore == '0'){
				alert('请对服务质量打分!');
                return false;
            }
            var taiduScore = $("#mydiv2").attr("currentIndex");
            if(taiduScore == '0'){
                alert('请对服务态度打分!');
                return false;
            }
            var zhunshiScore = $("#mydiv3").attr("currentIndex");
            if(zhunshiScore == '0'){
                alert('请对准时情况打分!');
                return false;
            }

            //提交订单前检查是否满足条件
            $.ajax({
                type:"post",
                dataType:"text",
                url:"${ctx}/pub/appraise/saveAppraise.do?type=wap",
                data:{orderId:orderId,appriseDesc:appriseDesc,zhiliangScore:zhiliangScore,taiduScore:taiduScore,zhunshiScore:zhunshiScore},
                async:false,
                success: function(data){
					if(data == 'success'){
					    alert('提交成功');
                        history.go(-1);
					}else{
                        alert('系统异常请重试');
					}
                }
            });
        }

		</script>
	</body>

</html>