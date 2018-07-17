<%@ page language="java" pageEncoding="UTF-8" isELIgnored="false"%>
<%@ taglib prefix="c" uri="/WEB-INF/tld/c.tld" %>
<c:set var="ctx" value="${pageContext.request.contextPath}" />

<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1" />
	<meta name="viewport" content="width=device-width, initial-scale=1.0, user-scalable=no"/>
	<title>工作内容</title>
	<link rel="stylesheet" href="${ctx}/wap/html/css/Basc.css" />
	<link rel="stylesheet" href="${ctx}/wap/html/css/demo.css" />
	<link rel="stylesheet" href="${ctx}/wap/css/style.css" />
	<link rel="stylesheet" href="${ctx}/wap/css/jquery.alertable.css">
	<script src="${ctx}/wap/js/alertable.js"></script>
	<script src="${ctx}/wap/js/jquery.alertable.min.js"></script>


	<style type="text/css">
		#textarea{
			border: 2px solid #00A1E9;
		}
	</style>
	<script type="text/javascript">
        $(document).ready(function(){
//			$("#old_input").attr("checked","checked");
//			$("#new_input").attr("checked","");
		});

        function oldLable() {
			$("#textarea_div").hide();
        }

        function newLable() {
            $("#textarea_div").show();
        }

        function getQueryString(name) {
            var reg = new RegExp("(^|&)" + name + "=([^&]*)(&|$)", "i");
            var r = window.location.search.substr(1).match(reg);
            if (r != null) return unescape(r[2]);
            return null;
        }

        //已完成施工状态更新
        function workContentSubimt() {
            var orderId = getQueryString("orderId");
			var textareaState = $("#textarea_div").attr("style");
            var workContent = $("#textarea").val();
			if(textareaState == '' && workContent == ''){
				$.alertable.alert('新增工作内容不能为空!').always(function() {
					console.log('Alert dismissed');
				});
				return false;
			}
            $.ajax({
                url:"${ctx}/pub/order/workContentSubimt.do?type=wap&orderId="+orderId+"&workContent="+workContent,
                dataType:"text",
                async:false,
                success: function(data){
                    if(data == "success"){
                        $.alertable.alert('提交成功').always(function() {
                            history.go(-1);
                        });
                    }else{
                        $.alertable.alert('系统异常请重试!').always(function() {
                            console.log('Alert dismissed');
                        });
					}
                }
            });
        }
	</script>
</head>

<body>
	<header>
	  <ul>
	    <li class="back"><a href="javascript:history.go(-1);"><img src="${ctx}/wap/html/images/back.png" width="40" height="50" /></a></li>
	    <li class="logo">工作内容</li>
	    <li class="more">
			<%--
			<a href="${ctx}/pub/menber/centerInit.do?type=wap"><img src="${ctx}/wap/html/images/more.png" alt="" width="40" height="50" /></a>
			--%>
		</li>
	  </ul>
	</header>

	<div style="margin-left: 20px">
		<label class="demo--label" id="old" onclick="oldLable()">
			<input class="demo--radio" id="old_input" type="radio" name="demo-radio" checked="checked">
			<span class="demo--radioInput" ></span>按原计划
		</label>
		<br/>
		<label class="demo--label" id="new" onclick="newLable()">
			<input class="demo--radio" id="new_input" type="radio" name="demo-radio">
			<span class="demo--radioInput" ></span>调整计划
		</label>
	</div>

	<div id="textarea_div" style="display: none">
		<div  style="margin-left: 20px;margin-top: 20px;pxborder: 2px solid blue;" >
			<textarea id="textarea" cols="32" rows="5" maxlength="100"></textarea>
		</div>
	</div>

	<input class="submit" style="margin-top: 10px" type="button" value="提交" onclick="workContentSubimt()">

	<table height="100px;"><tr><td >&nbsp;</td></tr></table>
	
	<!-- 公用底部 -->
	<jsp:include page="../public/foot.jsp" flush="false">
		<jsp:param name="menu" value="fw" />
	</jsp:include>
</body>
</html>
