<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@page import="java.util.List"%>
<%-- <%@page import="com.sinovatech.bms.adm.model.dto.TBmsDeptDTO"%>--%>
<HTML xmlns="http://www.w3.org/1999/xhtml">
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3c.org/TR/1999/REC-html401-19991224/loose.dtd">
<!-- saved from url=(0070)http://192.168.2.36:8011/cms/info/index.do -->
<HEAD>
<TITLE>后台管理系统</TITLE>
<meta http-equiv="content-type" content="text/html; charset=UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=EmulateIE7" />
<link href="${pageContext.request.contextPath}/sys/css/public.css" rel="stylesheet" type="text/css" />
<link href="${pageContext.request.contextPath}/sys/css/style.css" rel="stylesheet" type="text/css" />
<script src="${pageContext.request.contextPath}/common/xloadtree/xtree.js"></script>
<link type="text/css" rel="stylesheet"
			href="${pageContext.request.contextPath}/common/xloadtree/xtree.css" />
<meta name=generator content="MSHTML 8.00.6001.18939">
	<script type="text/javascript">
			function get(id){
				return document.getElementById(id);
			}
			function sel(id)
			{
				parent.domainValue.location.href='${pageContext.request.contextPath}/sys/bmsdomainvalue/queryBmsDomainvalue.do?parentId='+id;
			}
			
			var webFXTreeConfig = {
				rootIcon        : '${pageContext.request.contextPath}/common/xloadtree/images/foldericon.png',
				openRootIcon    : '${pageContext.request.contextPath}/common/xloadtree/images/openfoldericon.png',
				folderIcon      : '${pageContext.request.contextPath}/common/xloadtree/images/foldericon.png',
				openFolderIcon  : '${pageContext.request.contextPath}/common/xloadtree/images/openfoldericon.png',
				fileIcon        : '${pageContext.request.contextPath}/common/xloadtree/images/file.png',
				iIcon           : '${pageContext.request.contextPath}/common/xloadtree/images/I.png',
				lIcon           : '${pageContext.request.contextPath}/common/xloadtree/images/L.png',
				lMinusIcon      : '${pageContext.request.contextPath}/common/xloadtree/images/Lminus.png',
				lPlusIcon       : '${pageContext.request.contextPath}/common/xloadtree/images/Lplus.png',
				tIcon           : '${pageContext.request.contextPath}/common/xloadtree/images/T.png',
				tMinusIcon      : '${pageContext.request.contextPath}/common/xloadtree/images/Tminus.png',
				tPlusIcon       : '${pageContext.request.contextPath}/common/xloadtree/images/Tplus.png',
				blankIcon       : '${pageContext.request.contextPath}/common/xloadtree/images/blank.png',
				defaultText     : '节点',
				defaultAction   : '${pageContext.request.contextPath}/common/xloadtree/javascript:void(0);',
				defaultBehavior : 'classic',
				usePersistence	: true
			};
		
			var root;
			
			<%=request.getAttribute("tree")%>
			
			function addNode(id,text)
			{
				if (root.getSelected()) 
				{
					root.getSelected().add(new WebFXTreeItem(text,"javascript:sel('" + id + "')"));
				}
			}	
		</script>
<body class="overfwidth">
        <!--框内内容 结束-->
            <!--按钮 开始--> 
            
            <div class="overf pb5">
           <h1> <span class="left pt5">字典值定义</span></h1>
             </div> 
             
 <div style="overflow:hidden">
 <div style="float:left;">
 <a href="${pageContext.request.contextPath}/sys/bmsdomainvalue/queryBmsDomainvalueTree.do">刷新树</a>
 <script type="text/javascript">
							document.write(root);
						</script>
 </div>
              </div>
</body>
</html>
