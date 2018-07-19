<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="/WEB-INF/tld/extremecomponents.tld" prefix="ec"%>
<%@ taglib uri="/WEB-INF/tld/c.tld" prefix="c"%>
<HTML xmlns="http://www.w3.org/1999/xhtml">
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3c.org/TR/1999/REC-html401-19991224/loose.dtd">
<!-- saved from url=(0070)http://192.168.2.36:8011/cms/info/index.do -->
<HEAD>
<TITLE>后台管理系统</TITLE>
<meta http-equiv="content-type" content="text/html; charset=UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=EmulateIE7" />
<link href="${pageContext.request.contextPath}/sys/css/public.css" rel="stylesheet" type="text/css" />
<link href="${pageContext.request.contextPath}/sys/css/style.css" rel="stylesheet" type="text/css" />
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
		
			function btn_delete_click(id,cate)
			{
				var url = "${pageContext.request.contextPath}/pub/advert/beforeDelete.do";
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
					document.location.href=url + "?ids=" + id + "&cate=" + cate;
				//}
				
			}
			
			function btn_add_click(cate)
			{
				var url = "${pageContext.request.contextPath}/pub/advert/beforeAdd.do?cate="+cate;
				document.location.href=url;
			}			
		</script>
<body class="overfwidth">

<div class="barnavtop">您所在的位置：广告管理 > 广告列表</div>
<div id="workspace">
	<!--主体 开始-->
    <div id="container">    
        <!--searchForm 开始-->
        
        <div id="searchForm">
        
        </div>
         <div class="toolbar">
        <a href="#" onclick="btn_delete_click()" class="sexybutton"><span><span>批量删除</span></span></a>    
        <a href="#" class="sexybutton" onclick="btn_add_click('${cate}')"><span><span>新增广告</span></span></a> 
               </div>
        <!--searchForm 结束-->
        <!--CmsSiteList 开始-->
        
        <div class="eXtremeTable">
            <ec:table tableId="ArticleList" items="list" method="post" var="m" action=""
			imagePath="${pageContext.request.contextPath}/images/table/*.gif"
			width="98%" rowsDisplayed="10" view="compact"
			filterRowsCallback="limit" sortRowsCallback="limit"
			retrieveRowsCallback="limit">
			<ec:row>
				<ec:column headerCell="selectAll" alias="ckids" filterable="false"
					width="1%" sortable="false">
						<input type="checkbox" name="ckids" value="${m.id}" />
				</ec:column>
				<ec:column title="广告标题" property="title">
					<a href="${pageContext.request.contextPath}/pub/advert/beforeEdit.do?id=${m.id}">${m.title} </a>
				</ec:column>
				<ec:column title="广告类型" property="type" filterable="false">
					<c:if test="${m.type eq '1'}">wap广告</c:if>
					<c:if test="${m.type eq '2'}">web广告</c:if>
				</ec:column>
				<ec:column title="点击次数" property="count" filterable="false"></ec:column>
				<ec:column title="排序" property="orderNum" filterable="false"></ec:column>
				<ec:column title="操作" property="EEE" sortable="false"
					filterable="false" width="28%">
					<a class="sexybutton" href="${pageContext.request.contextPath}/pub/advert/beforeEdit.do?id=${m.id}">
					<span><span>编辑</span></span></a>
					<a class="sexybutton" href="javascript:btn_delete_click('${m.id}','${cate}')">
					<span><span>删除</span></span></a>
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
