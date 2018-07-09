<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="/WEB-INF/tld/extremecomponents.tld" prefix="ec"%>
<%@ taglib uri="/WEB-INF/tld/c.tld" prefix="c"%>
<c:set var="ctx" value="${pageContext.request.contextPath}" />
<HTML>
<HEAD>
	<TITLE>后台管理系统</TITLE>
	<meta http-equiv="content-type" content="text/html; charset=UTF-8">
	<meta http-equiv="X-UA-Compatible" content="IE=EmulateIE7" />
	<link href="${ctx}/sys/css/public.css" rel="stylesheet" type="text/css">
	<link href="${ctx}/sys/css/style.css" rel="stylesheet" type="text/css">
	<script type="text/javascript" src="${ctx}/common/js/jquery-1.4.4.min.js" ></script>
	<script type="text/javascript" src="${ctx}/js/mootools-release-1.11.js"></script>
	

<body class="overfwidth">

<div class="barnavtop">您所在的位置：会员管理 > 会员提现记录</div>
<div id="workspace">
	<!--主体 开始-->
    <div id="container">    
        <div class="toolbar">
        	<a href="${ctx}/pub/menber/queryList.do" class="sexybutton" ><span><span>返回</span></span></a> 
        </div>
        <!--searchForm 结束-->
        <!--CmsSiteList 开始-->
        
        <div class="eXtremeTable">
            <ec:table tableId="CashList" items="list" method="post" var="m" action=""
			imagePath="${ctx}/images/table/*.gif"
			width="98%" rowsDisplayed="10" view="compact"
			filterRowsCallback="limit" sortRowsCallback="limit"
			retrieveRowsCallback="limit">
			<ec:row>
				<ec:column title="提现时间" property="createTimeStr"></ec:column>
				<ec:column title="提现金额（元）" property="price"></ec:column>
				<ec:column title="备注" property="common"></ec:column>
				<ec:column title="状态" property="isDown">
					<c:if test="${m.isDown eq '1'}"><font color="red">申请中</font></c:if>
					<c:if test="${m.isDown eq '2'}"><font color="green">已批准</font></c:if>
					<c:if test="${m.isDown eq '3'}">已拒绝</c:if>
				</ec:column>
			</ec:row>
		</ec:table>
        </div>
        <div class="toolbar">
        	<a href="${ctx}/pub/menber/queryList.do" class="sexybutton" ><span><span>返回</span></span></a> 
        </div>
    <!--CmsSiteList 结束-->
    </div>
    <!--主体 结束-->
</div>

<iframe name="hideframe" id="hideframe" width="0" height="0"></iframe>

</body>
</html>
