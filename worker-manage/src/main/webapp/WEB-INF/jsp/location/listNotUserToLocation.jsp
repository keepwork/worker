<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib uri="/WEB-INF/tld/extremecomponents.tld" prefix="ec"%>
<%@ taglib uri="/WEB-INF/tld/domain.tld" prefix="domain"%>
<HTML xmlns="http://www.w3.org/1999/xhtml">
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3c.org/TR/1999/REC-html401-19991224/loose.dtd">
<!-- saved from url=(0070)http://192.168.2.36:8011/cms/info/index.do -->
<HEAD>
<TITLE>后台网站管理系统</TITLE>
<meta http-equiv="content-type" content="text/html; charset=UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=EmulateIE7" />
<link href="${pageContext.request.contextPath}/sys/css/public.css" rel="stylesheet" type="text/css">
<link href="${pageContext.request.contextPath}/sys/css/style.css" rel="stylesheet" type="text/css">
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
			
		function submitForm()
		{
			id = getChecks("ckids").join(",");
			if(id == "")
			{
				alert("请选择一条或多条记录!");
				return false;
			}
					
			document.getElementById("hiddenSelUsers").value = id;
			document.getElementById("submitform").submit();
		}		
		</script>
<body class="overfwidth">
<div class="workspace left" style="width:100%;">        
        <div class="mb10 pt10"><form id="" action="" class="cmxform" method="post" name="formName">
        <fieldset>
        <legend>请选择您需要授予管理部门的用户</legend>
        </fieldset>
        </form></div>
        
        <!--框内内容 开始-->
        <div class="toolbar">
        <a href="#" class="sexybutton" onclick="submitForm();"><span><span>提交授权</span></span></a>
        <a href="${pageContext.request.contextPath}/sys/bmslocation/listBmsLocationView.do?id=${m.id}" class="sexybutton"><span><span>返回</span></span></a>        </div>
        <form method="post"
        action="${pageContext.request.contextPath}/sys/bmslocation/saveBmsLocationUser.do" id="submitform">
        <input type="hidden" name="locationid" value="${m.id}" />
		<input type="hidden" id="hiddenSelUsers" name="users" value="" />
        </form>
		<div class="eXtremeTable">
            <ec:table tableId="userlist" method="post" items="notCheckedUserList" var="e" action="" 
					imagePath="${pageContext.request.contextPath}/images/table/*.gif"
					width="100%" rowsDisplayed="10" view="compact" >
					<ec:row>
						<ec:column headerCell="selectAll" alias="ckids" width="1%"
							filterable="false" sortable="false">
							<input type="checkbox" name="ckids" value="${e.id}" />
						</ec:column>
						<ec:column title="登录名" property="userLoginName">
						</ec:column>
						<ec:column title="真实姓名" property="userRealName" />
						<ec:column title="性别" property="userSex" filterCell="droplist"
							filterOptions="BMSDOMAIN.SEX">
							<domain:viewDomain domain="SEX" value="${e.userSex}" />
						</ec:column>
						<ec:column title="状态" property="userStatus" filterCell="droplist"
							filterOptions="BMSDOMAIN.dataIsused">
							<domain:viewDomain domain="dataIsused" value="${e.userStatus}" />
						</ec:column>
					</ec:row>
				</ec:table>
        </div>
                      <div class="toolbar pt10">
        <a href="#" class="sexybutton" onclick="submitForm();"><span><span>提交授权</span></span></a>
        <a href="${pageContext.request.contextPath}/sys/bmslocation/listBmsLocationView.do?id=${m.id}" class="sexybutton"><span><span>返回</span></span></a>        </div>
        <!--框内内容 结束-->
</div>

</body>
</html>
