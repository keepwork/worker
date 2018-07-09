<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="/WEB-INF/tld/extremecomponents.tld" prefix="ec"%>
<%@ taglib uri="/WEB-INF/tld/c.tld" prefix="c"%>
<%@ taglib uri="/WEB-INF/tld/domain.tld" prefix="domain"%>
<c:set var="ctx" value="${pageContext.request.contextPath}" />
<HTML>
<HEAD>
	<TITLE>后台管理系统</TITLE>
	<meta http-equiv="content-type" content="text/html; charset=UTF-8">
	<meta http-equiv="X-UA-Compatible" content="IE=EmulateIE7" />
	<link href="${ctx}/sys/css/public.css" rel="stylesheet" type="text/css">
	<link href="${ctx}/sys/css/style.css" rel="stylesheet" type="text/css">
	<link rel="stylesheet" type="text/css" href="${ctx}/common/css/showhide.css"/>
	
	<script type="text/javascript" src="${ctx}/js/mootools-release-1.11.js"></script>
	<script type="text/javascript" src="${ctx}/common/js/jquery-1.4.4.min.js" ></script>
	<script type="text/javascript" src="${ctx}/common/js/showhide.js"></script>
	<script type="text/javascript">
	function openDiv(id) {
		document.getElementById("id").value=id;
		document.getElementById("auditDesc").value="";
		show('cover1','pop_sh','');
	}
	function submitAudit() {
		var auditDesc = document.getElementById("auditDesc");
    	if(auditDesc.value == ''){
    		alert("审核意见不能为空");
    		auditDesc.focus();
    		return;
    	}
    	document.frmExit.submit();
	}	
	</script>
	
<body class="overfwidth">

<div class="barnavtop">您所在的位置： 会员退出申请列表</div>
<div id="workspace">
	<!--主体 开始-->
    <div id="container">    
        <!--
        <div class="toolbar">
        	
        </div>
        -->
        <!--searchForm 结束-->
        <!--CmsSiteList 开始-->
        
        <div class="eXtremeTable">
            <ec:table tableId="ExitList" items="list" method="post" var="m" action=""
			imagePath="${ctx}/images/table/*.gif"
			width="98%" rowsDisplayed="10" view="compact"
			filterRowsCallback="limit" sortRowsCallback="limit"
			retrieveRowsCallback="limit">
			<ec:row>
				<ec:column title="申请时间" property="createTimeStr" filterable="false" width="8%"></ec:column>
				<ec:column title="会员姓名" property="menRealName" width="5%"></ec:column>
				<ec:column title="身份证" property="menPid"></ec:column>
				<ec:column title="收款人姓名" property="bankUserName" width="5%"></ec:column>
				<ec:column title="收款人电话" property="mobile"></ec:column>
				<ec:column title="银行名称" property="bankName" filterable="false"></ec:column>
				<ec:column title="银行卡号" property="bankNum" filterable="false" width="8%"></ec:column>
				<ec:column title="退出原因" property="content" filterable="false" width="15%"></ec:column>
				<ec:column title="审核时间" property="auditTimeStr" filterable="false" width="8%"></ec:column>
				<ec:column title="审核说明" property="auditDesc" filterable="false" ></ec:column>
				<ec:column title="状态" property="auditResult" filterable="false" width="5%">
					<c:if test="${m.auditResult eq '1'}"><font color="red">申请中</font></c:if>
					<c:if test="${m.auditResult eq '2'}"><font color="green">已批准</font></c:if>
					<c:if test="${m.auditResult eq '3'}">已拒绝</c:if>
				</ec:column>
				<ec:column title="操作" property="EEE" sortable="false" filterable="false" width="5%">
					<c:if test="${m.auditResult eq '1'}">
					<a class="sexybutton" href="javascript:void(0)" onclick="openDiv('${m.id }')">
						<span><span>审核</span></span>
					</a>
					</c:if>
				</ec:column>
			</ec:row>
		</ec:table>
        </div>
        <div class="toolbar">
        	
        </div>
    <!--CmsSiteList 结束-->
    </div>
    <!--主体 结束-->
</div>


<!--提示弹出层 开始-->
<div id="cover1"></div>
<div class="pop_555" id="pop_sh" style="width: 600px; height: 190px;">
	<div class="pop_555_t" style="width: 600px;">
		<!---pop_555_t-->
		<strong class="fl ml15">退出申请审核</strong> <span
			class="fr mr15 pop_close" onclick="hide('cover1','pop_sh');"></span>
	</div>
	<div class="pop_555_m text_c" style="width: 600px; padding-top: 20px">
		<!---pop_555_m-->
		<div id="win_publish_Id">
				<form name="frmExit" class="cmxform" action="${ctx}/pub/exit/audit.do" target="hideframe" method="post" >
				<table align="center">
					<input type="hidden" name="id" id="id" value=""/>
					<tr>
						<td align="right">审核结果：</td>
						<td align="left">
							<domain:radioDomain domain="cashAuditType" name="auditResult" uid="auditResult" value="2"/>
							<!-- 
							<input name="isDown" value="5" type="radio" checked />批准&nbsp;&nbsp;&nbsp;
							<input name="isDown" value="6" type="radio" />拒绝
							-->
						</td>
					</tr>
					<tr>
						<td align="right">审核说明：</td>
						<td align="left">
							<textarea id="auditDesc" name="auditDesc" cols="50" rows="2" maxlength="100"></textarea>
						</td>
					</tr>
				</table>
				<input id="" type="button" value="保存" onclick="submitAudit();" />
				</form>
		</div>
	</div>
</div>
<!--提示弹出层 结束-->

<iframe name="hideframe" id="hideframe" width="0" height="0"></iframe>

</body>
</html>
