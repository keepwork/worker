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
	<link href="${pageContext.request.contextPath}/sys/css/public.css" rel="stylesheet" type="text/css">
	<link href="${pageContext.request.contextPath}/sys/css/style.css" rel="stylesheet" type="text/css">
	<script type="text/javascript" src="${pageContext.request.contextPath}/js/mootools-release-1.11.js"></script>
	<meta name=generator content="MSHTML 8.00.6001.18939">
	<script type="text/javascript">
				//选择日期导入日志
				function btnImportLogClick()
				{
					var importDateStr = document.getElementById("importDateStr").value;
					var checkResult = checkDateStr(importDateStr);
					if(checkResult){
					$.ajax({
						url:'${pageContext.request.contextPath}/sso/admin/ssoProp/importLogs.do?importDateStr='+importDateStr,
						type:'post',
						async:false,
						//dataType:'json',
						success:function(data){
							alert(data);
						},error:function(data){
						}
					});
				}
				
				//导入当天日志
				function btnImportTodayLogClick()
				{
					$.ajax({
						url:'${pageContext.request.contextPath}/sso/admin/ssoProp/importTodayLogs.do',
						type:'post',
						async:false,
						//dataType:'json',
						success:function(data){
							alert(data);
						},error:function(data){
						}
					});
				}	
				
				function checkDateStr(importDateStr)
				{
					var a = /^(\d{4})-(\d{2})-(\d{2})$/;
					if (!a.test(importDateStr)) { 
						alert("日期格式不正确!") 
						return false;
					} 
					
					var currentDate = new Date();
					var year = currentDate.getFullYear();
					var month = currentDate.getMonth();
					month = month + 1;
					if(month<=9){
						month = "0"+month;
					}
					var day = currentDate.getDate();
					if(day<=9){
						day = "0"+day;
					}
					var currentDateStr = year+"-"+month+"-"+day;  
					if (currentDateStr == importDateStr) { 
						alert("日期不能为今天!") 
						return false;
					} 
					
					var importDate = new Date(Date.parse(importDateStr.replace(/-/g,"/")));
					var days = parseInt((currentDate.getTime()-importDate.getTime()) / (1000 * 60 * 60 * 24));
					var times = currentDate.getTime()-importDate.getTime();
					if( (days>28*3) || (times<0) ){
						alert("日期范围应在今天前的三个月之内!");
						return false;
					}
					
					return true 
				}
			}			
		</script>
<body class="overfwidth">

<div class="barnavtop">您所在的位置：文章管理 > 文章列表</div>
<div id="workspace">
	<!--主体 开始-->
    <div id="container">    
        <!--searchForm 开始-->
        
        <div id="searchForm">
        		<table width="50%">
				<tr><td bgcolor="#F7F7F7" colspan="2">手动导入日志</td></tr>
				<tr>
				  	<td>
				  		导入日期：<input name="importDateStr" id="importDateStr" type="text" size="15" value="" maxlength="10">
				  		<font color="red">格式为：2015-01-01</font>
				  	</td>
				  	<td>
				  		<input type="button" onclick="btnImportLogClick()" value="开始导入日志"></input>
				  		<input type="button" onclick="btnImportTodayLogClick()" value="导入今天日志"></input>
				  	</td>
				</tr>
				</table>
        </div>
        <div class="toolbar">
        	<a href="#" onclick="btn_delete_click()" class="sexybutton"><span><span>批量删除</span></span></a>    
        </div>
        <!--searchForm 结束-->
        <!--CmsSiteList 开始-->
        
        <div class="eXtremeTable">
            <ec:table tableId="ArticleList" items="list" method="post" var="m" action=""
			imagePath="${pageContext.request.contextPath}/images/table/*.gif"
			width="98%" rowsDisplayed="30" view="compact"
			filterRowsCallback="limit" sortRowsCallback="limit"
			retrieveRowsCallback="limit">
			<ec:row>
				<ec:column headerCell="selectAll" alias="ckids" filterable="false"
					width="1%" sortable="false">
					<c:if test="${m.catCode!='1'}">
						<input type="checkbox" name="ckids" value="${m.id}" />
					</c:if>
				</ec:column>
				<ec:column title="文章标题" property="title">
					<a href="${pageContext.request.contextPath}/pub/article/beforeEdit.do?id=${m.id}">${m.title} </a>
				</ec:column>
				<ec:column title="文章分类" property="catName" filterable="false"></ec:column>
				<ec:column title="排序" property="asc" filterable="false"></ec:column>
				<ec:column title="操作" property="EEE" sortable="false"
					filterable="false" width="28%">
					<a class="sexybutton" href="${pageContext.request.contextPath}/pub/article/beforeEdit.do?id=${m.id}">
					<span><span>编辑</span></span></a>
					<c:if test="${m.catCode!='1'}">
						<a class="sexybutton" href="javascript:btn_delete_click('${m.id}')">
						<span><span>删除</span></span></a>
					</c:if>
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

<form name="frmRedis" class="cmxform" action="${pageContext.request.contextPath}/pub/article/synRedis.do" target="hideframe" method="post">
</form>

<iframe name="hideframe" id="hideframe" width="0" height="0"></iframe>
</body>
</html>
