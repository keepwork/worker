<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="/WEB-INF/tld/extremecomponents.tld" prefix="ec"%>
<%@ taglib uri="/WEB-INF/tld/c.tld" prefix="c"%>
<c:set var="ctx" value="${pageContext.request.contextPath}" />
<HTML>
<HEAD>
	<TITLE>后台管理系统</TITLE>
	<meta http-equiv="content-type" content="text/html; charset=UTF-8">
	<meta http-equiv="X-UA-Compatible" content="IE=EmulateIE7" />
	<meta name=generator content="MSHTML 8.00.6001.18939">

	<link href="${ctx}/sys/css/public.css" rel="stylesheet" type="text/css">
	<link href="${ctx}/sys/css/style.css" rel="stylesheet" type="text/css">

	<script type="text/javascript" src="${ctx}/common/js/calendar/WdatePicker.js"></script>
	<script type="text/javascript" src="${ctx}/common/js/jquery-1.8.0.min.js" charset="utf-8"></script>
	<script type="text/javascript" src="${ctx}/js/mootools-release-1.11.js"></script>

	<script type="text/javascript">
			function getChecks(name)
			{
				var re = new Array();
				var ck = document.getElementsByName(name);
				for(var i=0; i<ck.length; i++)
				{
					if(ck[i].checked)
					{
						re.push(ck[i].value);
					}
				}
				return re;
			}
		
			function btn_delete_click(id)
			{
				var url = "${ctx}/pub/menber/beforeDelete.do";
				if(!$chk(id))
				{
					id = getChecks("ckids").join(",");
					if(!$chk(id))
					{
						alert("请选择一条或多条记录!");
						return;
					}
				}
				
				//if(window.confirm('你确定要删除吗？')){
					document.location.href=url + "?ids=" + id;
				//}
				
			}
			
			function btn_add_click(type)
			{
				var url = "${ctx}/pub/menber/beforeAdd.do?type="+type;
				document.location.href=url;
			}			
			
			function _exportData(){
				//获取查询的参数
				//var _param = "acName: "+jQuery("#acName_Id").val();
		  		//_param+=",beginTime_date: "+jQuery("#beginTime_").val();
		  		//_param+=",endTime_date: "+jQuery("#endTime_").val();
		  		//_param+=",ids: "+getChecks('ckids').join(";");
		  		//jQuery("#exportParam_").val(_param);
		  		//jQuery("#exBeginTime_").val(jQuery("#beginTime_").val());
		  		//jQuery("#exEndTime_").val(jQuery("#endTime_").val());
		  		jQuery("#exportFm_").submit();
			};
			
			//设置名称
			function setRealName(realName){
				jQuery("#realName_").val(realName);
			}
			function getRadioChecks(name) {
				var result = new Array();
				jQuery("[name = " + name + "]:radio").each(function() {
					if (jQuery(this).is(":checked")) {
						result.push(jQuery(this).attr("value"));
					}
				});
				return result;
			}
			function submitSelect(){
				var id = getRadioChecks("ckids");
				if(id==""){
					alert("请选择记录");
					return;
				}
				var realName = jQuery("#realName_").val();
				var data=id+"|"+realName;	
				//父页面回调方法，需个活动实现
				parent.window.menberSelectCallback(data);	
				parent.hide('cover1','menberSelect');
			}
		</script>
		
<body class="overfwidth">

<c:if test="${null eq listShowType}">
	<div class="barnavtop">您所在的位置：会员管理 > 会员列表</div>
</c:if>

<div id="workspace">
	<!--主体 开始-->
    <div id="container">
		<c:if test="${type eq '2'}">
		<div class="overf pt5 pb5">
			<span class="left"><a href="#" onclick="btn_delete_click()" class="sexybutton"><span><span>批量删除</span></span></a> </span>
			<span class="left ml10"><a class="sexybutton" href="#" onclick="btn_add_click('${type}')"><span><span>新增用户</span></span></a></span>
		</div>
		</c:if>
        <!--searchForm 开始-->
        <%--
        <div id="searchForm"></div>
        <div class="toolbar">
	        <c:if test="${listShowType eq 'window'}">
	        	<input id="realName_" name="realName_" value="" type="hidden"/>
				<a href="#"><span class="fillet_btn_01 mr20">
						<span id="onclickId" class="fillet_btn_01_left" onclick="submitSelect();">确认</span> 
				</span></a>
			</c:if>
			<c:if test="${null eq listShowType}">
	      
	        	<span class="left ml10"><a href="#" onclick="btn_delete_click()" class="sexybutton"><span><span>批量删除</span></span></a>  </span>  
	        
	        	
	        	<form id="exportFm_" action="${pageContext.request.contextPath}/pub/menber/exportMenbers.do" method="post" onsubmit="" target="hideframe"/>
				<input type="hidden" id="exportParam_" name="exportParam"/>
				<input type="hidden" id="exBeginTime_" name="exBeginTime"/>
				<input type="hidden" id="exEndTime_" name="exEndTime"/>

     			<span class="left ml10"><span><span>会员状态：
				<select id="isjoin" name="isjoin" class="select_2 va_mid" style="width: 100px;">
					<option value=""  selected="selected" >-全部-</option>
					<option value="0" >未加入</option>
					<option value="1" >已加入但未过等待期</option>
					<option value="2" >已过等待期</option>
				</select>
				&nbsp;</span></span></span>
				
				<span class="left ml10"><span><span>注册开始时间：
				<input id="beginTime_" type="text" name="beginTimeStr" 
					readonly="readonly" class="text_1 va_mid"
					onclick="WdatePicker({el:'beginTime_',dateFmt:'yyyy-MM-dd HH:mm:ss'})" style="width: 150px;"/>
				<img src="${ctx}/common/images/time.gif" style="vertical-align: middle"
					onclick="WdatePicker({el:'beginTime_',dateFmt:'yyyy-MM-dd HH:mm:ss'})" />
				&nbsp;</span></span></span> 
				
				<span class="left ml10"><span><span>&nbsp;注册结束时间：
				<input id="endTime_" type="text" name="endTimeStr" 
					readonly="readonly" class="text_1 va_mid" 
					onclick="WdatePicker({el:'endTime_',dateFmt:'yyyy-MM-dd HH:mm:ss'})" style="width: 150px;"/>
				<img src="${ctx}/common/images/time.gif" style="vertical-align: middle"
					onclick="WdatePicker({el:'endTime_',dateFmt:'yyyy-MM-dd HH:mm:ss'})" />
				&nbsp;</span></span></span> 
				
	        	<span class="left ml10"><a class="sexybutton" href="#" onclick="_exportData()"><span><span>导&nbsp;出</span></span></a></span> 
	        	</form>
	        	
	        	<span class="left ml10"><a class="sexybutton" href="#" onclick="btn_add_click()"><span><span>新增会员</span></span></a></span> 
	        	
	        	
	        </c:if>
        </div>
         --%>
        <!--searchForm 结束-->
        
        <!--CmsSiteList 开始-->
        <div class="eXtremeTable">
            <ec:table tableId="MenberList" items="list" method="post" var="m" action=""
			imagePath="${ctx}/images/table/*.gif" width="98%" rowsDisplayed="10" view="compact"
			filterRowsCallback="limit" sortRowsCallback="limit" retrieveRowsCallback="limit">
			<ec:row>
				
				<c:if test="${null eq listShowType}">
					<ec:column headerCell="selectAll" alias="ckids" filterable="false" width="1%" sortable="false">
					<input type="checkbox" name="ckids" value="${m.id}" />
					</ec:column>
				</c:if>
				<c:if test="${listShowType eq 'window'}">
					<ec:column alias="" filterable="false" width="1%" sortable="false">
					<input type="radio" name="ckids" id="ckids" value="${m.id}" onclick="setRealName('${m.realName }')"/>
					</ec:column>
				</c:if>
				
				<ec:column title="真实姓名" property="realName"></ec:column>
				
	        	<ec:column title="微信名" property="loginName"  width="7%"></ec:column>
				<%--
	        	<ec:column title="会员类型" property="type" filterable="false">
					<c:if test="${m.type eq '1'}">微信客户</c:if>
					<c:if test="${m.type eq '2'}">安装工</c:if>
				</ec:column>
				--%>
				<ec:column title="区域1" property="locationName"  width="10%"></ec:column>
	        	<ec:column title="手机" property="mobile"  width="7%"></ec:column>
	        	<ec:column title="身份证" property="pid"  width="15%"></ec:column>
				<ec:column title="注册时间" property="regTimeStr" filterable="false"  width="15%"></ec:column>
				<%--<ec:column title="余额" property="balanceFee" filterable="false"></ec:column>
				<ec:column title="积分" property="point" filterable="false"></ec:column> --%>
				<ec:column title="状态" property="status" filterable="false">
					<c:if test="${m.status eq '0'}"><font color="green">正常</font></c:if>
					<c:if test="${m.status eq '1'}"><font color="red">停用</font></c:if>
				</ec:column>

				<ec:column title="操作" property="EEE" sortable="false" filterable="false" width="20%">
					<%--
					<a class="sexybutton" href="${ctx}/pub/family/queryList.do?familyMenId=${m.id}"><span><span>托管</span></span></a>
					<a class="sexybutton" href="${ctx}/pub/point/queryList.do?menId=${m.id}"><span><span>积分</span></span></a>
					<a class="sexybutton" href="${ctx}/pub/charge/queryList.do?menId=${m.id}"><span><span>充值</span></span></a>
					<a class="sexybutton" href="${ctx}/pub/cut/queryList.do?menId=${m.id}"><span><span>扣款</span></span></a>
					<a class="sexybutton" href="${ctx}/pub/cash/queryList.do?menId=${m.id}"><span><span>提现</span></span></a>
					<a class="sexybutton" href="${ctx}/pub/share/queryList.do?menId=${m.id}"><span><span>好友</span></span></a>
					--%>

					<%--客户--%>
					<c:if test="${m.type eq '1'}">
						<a class="sexybutton" href="${ctx}/pub/order/queryList.do?menId=${m.id}"><span><span>我的订单</span></span></a>
					</c:if>

					<%--工人--%>
					<c:if test="${m.type eq '2'}">
						<a class="sexybutton" href="${ctx}/pub/order/queryList.do?workerId=${m.id}"><span><span>我的订单</span></span></a>
						<a class="sexybutton" href="${ctx}/pub/menber/beforeEdit.do?id=${m.id}"><span><span>修改</span></span></a>
						<a class="sexybutton" href="javascript:btn_delete_click('${m.id}')"><span><span>删除</span></span></a>
					</c:if>
				</ec:column>
				
				
			</ec:row>
		</ec:table>
        </div>
        
        <!--
		<c:if test="${null eq listShowType}">					
        	<div class="toolbar">
	        	<a href="#" onClick="btn_delete_click()" class="sexybutton"><span><span>批量删除</span></span></a>    
	        </div>
        </c:if>
        -->
        
    <!--CmsSiteList 结束-->
    </div>
    <!--主体 结束-->
</div>

<iframe name="hideframe" id="hideframe" width="0" height="0"></iframe>

</body>
</html>

