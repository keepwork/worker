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
<script type="text/javascript" src="${ctx}/js/mootools-release-1.11.js"></script>
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
				var url = "${ctx}/pub/family/beforeDelete.do";
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
			
			function btn_add_click(familyMenId)
			{
				var url = "${ctx}/pub/family/beforeAdd.do?familyMenId="+familyMenId;
				document.location.href=url;
			}			
		</script>
<body class="overfwidth">

<div class="barnavtop">您所在的位置：会员亲友管理 > 会员亲友列表</div>
<div id="workspace">
	<!--主体 开始-->
    <div id="container">    
        <div class="toolbar">
        <span class="left ml10"><a href="#" onclick="btn_delete_click()" class="sexybutton"><span><span>批量删除</span></span></a>  </span>  
        <span class="left ml10"><a class="sexybutton" href="#" onclick="btn_add_click('${familyMenId}')"><span><span>新增代管账户</span></span></a></span> 
        <a href="${ctx}/pub/menber/queryList.do" class="sexybutton" ><span><span>返回</span></span></a> 
        </div>
        <!--searchForm 结束-->
        <!--CmsSiteList 开始-->
        
        <div class="eXtremeTable">
            <ec:table tableId="FamilyList" items="list" method="post" var="m" action=""
			imagePath="${ctx}/images/table/*.gif"
			width="98%" rowsDisplayed="10" view="compact"
			filterRowsCallback="limit" sortRowsCallback="limit"
			retrieveRowsCallback="limit">
			<ec:row>
				<ec:column headerCell="selectAll" alias="ckids" filterable="false"
					width="1%" sortable="false">
					<input type="checkbox" name="ckids" value="${m.id}" />
				</ec:column>
				<ec:column title="真实姓名" property="realName"></ec:column>
				<ec:column title="身份证" property="pid"></ec:column>
				<ec:column title="入会时间" property="regTimeStr"></ec:column>
				<ec:column title="入会天数" property="days"></ec:column>
				<ec:column title="余额" property="balanceFee"></ec:column>
				<ec:column title="操作" property="EEE" sortable="false"
					filterable="false" width="28%">
					<a class="sexybutton" href="${ctx}/pub/family/beforeEdit.do?id=${m.id}&familyMenId=${familyMenId}"><span><span>修改</span></span></a>
					<a class="sexybutton" href="javascript:btn_delete_click('${m.id}')"><span><span>解除托管</span></span></a>
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
