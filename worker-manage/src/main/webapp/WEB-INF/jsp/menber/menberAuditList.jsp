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
	<meta name=generator content="MSHTML 8.00.6001.18939">

	<link href="${ctx}/sys/css/public.css" rel="stylesheet" type="text/css">
	<link href="${ctx}/sys/css/style.css" rel="stylesheet" type="text/css">
	<link rel="stylesheet" type="text/css" href="${ctx}/common/css/showhide.css"/>

	<script type="text/javascript" src="${ctx}/common/js/calendar/WdatePicker.js"></script>
	<script type="text/javascript" src="${ctx}/common/js/jquery-1.8.0.min.js" charset="utf-8"></script>
	<script type="text/javascript" src="${ctx}/js/mootools-release-1.11.js"></script>
	<script type="text/javascript" src="${ctx}/common/js/showhide.js"></script>

	<script type="text/javascript">
            function openDiv(id) {
                document.getElementById("id").value=id;
                document.getElementById("fax").value="";
                show('cover1','pop_sh','');
            }

            function submitAudit() {
                var fax = document.getElementById("fax");
                if(fax.value == ''){
                    alert("审核意见不能为空");
                    fax.focus();
                    return;
                }
                document.frmCash.submit();
            }
	</script>
		
<body class="overfwidth">

<div class="barnavtop">您所在的位置：会员管理 > 工人申请列表</div>

<div id="workspace">
        <!--CmsSiteList 开始-->
        <div class="eXtremeTable">
            <ec:table tableId="MenberList" items="list" method="post" var="m" action=""
			imagePath="${ctx}/images/table/*.gif" width="98%" rowsDisplayed="10" view="compact"
			filterRowsCallback="limit" sortRowsCallback="limit" retrieveRowsCallback="limit">
			<ec:row>

				<ec:column title="真实姓名" property="realName"></ec:column>
	        	<ec:column title="微信名" property="loginName"  width="7%"></ec:column>
				<ec:column title="区域1" property="locationName"  width="10%"></ec:column>
	        	<ec:column title="手机" property="mobile"  width="7%"></ec:column>
	        	<ec:column title="身份证" property="pid"  width="15%"></ec:column>
				<ec:column title="申请时间" property="joinTimeStr" width="15%"></ec:column>
				<ec:column title="审核结果" property="isjoin" filterable="false">
					<c:if test="${m.isjoin eq '1'}">已批准</c:if>
					<c:if test="${m.isjoin eq '0'}"><font color="red">未批准</font></c:if>
				</ec:column>
				<ec:column title="审核说明" property="fax"  width="10%"></ec:column>

				<ec:column title="操作" property="EEE" sortable="false" filterable="false" width="20%">
						<a class="sexybutton" href="javascript:void(0)" onclick="openDiv('${m.id }')">
							<span><span>审核</span></span>
						</a>
				</ec:column>

			</ec:row>
		</ec:table>
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
		<strong class="fl ml15">工人申请审核</strong> <span
			class="fr mr15 pop_close" onclick="hide('cover1','pop_sh');"></span>
	</div>
	<div class="pop_555_m text_c" style="width: 600px; padding-top: 20px">
		<!---pop_555_m-->
		<div id="win_publish_Id">
			<form name="frmCash" class="cmxform" action="${ctx}/pub/menber/workAudit.do" target="hideframe" method="post" >
				<table align="center">
					<input type="hidden" name="id" id="id" value=""/>
					<tr>
						<td align="right">审核结果：</td>
						<td align="left">
							<domain:radioDomain domain="workAuditType" name="isjoin" uid="isjoin" value="1"/>
						</td>
					</tr>
					<tr>
						<td align="right">审核意见：</td>
						<td align="left">
							<textarea id="fax" name="fax" cols="50" rows="2" maxlength="100"></textarea>
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

