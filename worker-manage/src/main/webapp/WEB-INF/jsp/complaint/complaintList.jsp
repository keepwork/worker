<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="/WEB-INF/tld/extremecomponents.tld" prefix="ec"%>
<%@ taglib uri="/WEB-INF/tld/c.tld" prefix="c"%>
<c:set var="ctx" value="${pageContext.request.contextPath}" />

<HTML xmlns="http://www.w3.org/1999/xhtml">
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3c.org/TR/1999/REC-html401-19991224/loose.dtd">
<!-- saved from url=(0070)http://192.168.2.36:8011/cms/info/index.do -->
<HEAD>
<TITLE>后台管理系统</TITLE>
<meta http-equiv="content-type" content="text/html; charset=UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=EmulateIE7" />
<link href="${ctx}/sys/css/public.css" rel="stylesheet" type="text/css">
<link href="${ctx}/sys/css/style.css" rel="stylesheet" type="text/css">
<script type="text/javascript" src="${pageContext.request.contextPath}/js/mootools-release-1.11.js"></script>
<meta name=generator content="MSHTML 8.00.6001.18939">
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
				var url = "${pageContext.request.contextPath}/pub/complaint/beforeDelete.do";
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
			
			function btn_add_click()
			{
				var url = "${pageContext.request.contextPath}/pub/complaint/beforeAdd.do";
				document.location.href=url;
			}			
		</script>
<body class="overfwidth">

<div class="barnavtop">您所在的位置：投诉管理 > 投诉列表</div>
<div id="workspace">
	<!--主体 开始-->
    <div id="container">    
        <!--searchForm 开始-->
        
        <div id="searchForm">
        
        </div>
         <div class="toolbar">
        <a href="#" onclick="btn_delete_click()" class="sexybutton"><span><span>批量删除</span></span></a>    
               </div>
        <!--searchForm 结束-->
        <!--CmsSiteList 开始-->
        
        <div class="eXtremeTable">
            <ec:table tableId="ComplaintList" items="list" method="post" var="m" action=""
			imagePath="${pageContext.request.contextPath}/images/table/*.gif"
			width="98%" rowsDisplayed="10" view="compact"
			filterRowsCallback="limit" sortRowsCallback="limit"
			retrieveRowsCallback="limit">
			<ec:row>
				<ec:column headerCell="selectAll" alias="ckids" filterable="false"
					width="1%" sortable="false">
					<input type="checkbox" name="ckids" value="${m.id}" />
				</ec:column>
				<ec:column title="投诉人" property="menName"></ec:column>
				<ec:column title="投诉时间" property="compTime" filterable="false"></ec:column>
				<ec:column title="处理人" property="userName" filterable="false"></ec:column>
				<ec:column title="处理时间" property="commitTime" filterable="false"></ec:column>
				<ec:column title="处理状态" property="status" filterable="false">
					<c:if test="${m.status eq '0'}"><font color="red">未处理</font></c:if>
					<c:if test="${m.status eq '1'}"><font color="green">已处理</font></c:if>
				</ec:column>
				<ec:column title="操作" property="EEE" sortable="false"
					filterable="false" width="28%">
					<a class="sexybutton" href="${pageContext.request.contextPath}/pub/complaint/beforeEdit.do?id=${m.id}">
					<span><span>查看处理</span></span></a>
					<%--
					<a class="sexybutton" href="javascript:btn_delete_click('${m.id}')">
					<span><span>删除</span></span></a>
					 --%>
				</ec:column>
			</ec:row>
		</ec:table>
        </div>
        <div class="toolbar">
        	<a href="#" onClick="btn_delete_click()" class="sexybutton"><span><span>批量删除</span></span></a>
        </div>
    <!--CmsSiteList 结束-->
    </div>
    <!--主体 结束-->
</div>

<iframe name="hideframe" id="hideframe" width="0" height="0"></iframe>
</body>
</html>
