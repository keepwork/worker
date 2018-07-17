<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib uri="/WEB-INF/tld/domain.tld" prefix="domain"%>
<%@ taglib uri="/WEB-INF/tld/articleCategory.tld" prefix="cat" %>
<%@ taglib uri="/WEB-INF/tld/c.tld" prefix="c"%>
<%@page import="com.pub.article.model.dto.ArticleDTO"%>
<c:set var="ctx" value="${pageContext.request.contextPath}" />
<%@ include file="../common/commonHeader.jsp"%>
<HTML>
<HEAD>
		<TITLE>后台管理系统</TITLE>
		<meta http-equiv="X-UA-Compatible" content="IE=EmulateIE7" />
		<meta name=generator content="MSHTML 8.00.6001.18939">
		<link href="${ctx}/sys/css/public.css" rel="stylesheet" type="text/css">
		<link href="${ctx}/sys/css/style.css" rel="stylesheet" type="text/css">
		<script type="text/javascript" src="${ctx}/common/js/categoryTag.js"></script>
		
		<!-- 图片上传 -->
		<script type="text/javascript" src="${ctx}/common/js/jquery-1.4.4.min.js" ></script>
		<script type="text/javascript" src="${ctx}/common/js/ajaxfileupload.js"></script>
		
		<!-- kindeditor -->
		<link rel="stylesheet" href="${ctx}/common/kindeditor-4.0.5/themes/default/default.css" />
		<link rel="stylesheet" href="${ctx}/common/kindeditor-4.0.5/plugins/code/prettify.css" />
		<script type="text/javascript" src="${ctx}/common/kindeditor-4.0.5/kindeditor.js" ></script>
		<script type="text/javascript" src="${ctx}/common/kindeditor-4.0.5/lang/zh_CN.js"></script>
		<script type="text/javascript" src="${ctx}/common/kindeditor-4.0.5/plugins/code/prettify.js"></script>
		<script type="text/javascript">
			KindEditor.ready(function(K) {
		   		var contentEditor = K.create('textarea[name="content"]', {
					cssPath : '${ctx}/common/kindeditor-4.0.5/plugins/code/prettify.css',
					uploadJson : '${ctx}/common/kindeditor-4.0.5/jsp/upload_json.jsp',
					fileManagerJson : '${ctx}/common/kindeditor-4.0.5/jsp/file_manager_json.jsp',
					allowFileManager : true,
					allowPreviewEmoticons : true,
					afterCreate : function() {
				     	this.sync();
				    },
				    afterBlur:function(){
			            this.sync();
			        }  
				});
				contentEditor.sync();
		   });
	    </script>
	    
		<script type="text/javascript">
		<!--
		
			String.prototype.replaceAll = function(reallyDo, replaceWith, ignoreCase) {  
			    if (!RegExp.prototype.isPrototypeOf(reallyDo)) {  
			        return this.replace(new RegExp(reallyDo, (ignoreCase ? "gi": "g")), replaceWith);  
			     } else {  
			        return this.replace(reallyDo, replaceWith);  
			     }  
			}

			function beforeSubmit()
			{
				var title = document.getElementById('title');
		    	if(title.value == ''){
		    		alert("文章标题不能为空");
		    		title.focus();
		    		return;
		    	}
		    	
		    	var content_ = document.getElementById('content');
		    	if(content_.value == ''){
		    		alert("文章内容不能为空");
		    		content_.focus();
		    		return;
		    	}
				
				var content = document.getElementById("content").value;
				content = content.replaceAll("<", "&lt;");
				content = content.replaceAll(">", "&gt;");
				content = content.replaceAll("\"", "&quot;");
				content = content.replaceAll(" ", "&nbsp;");
				//content = content.replaceAll("&nbsp；", "&nbsp");
				//content = content.replaceAll("&nbsp;", "&nbsp");
				document.getElementById("content").value = content;
				
				document.frmApply.submit();
			}
		//-->
		</script>
<meta name=generator content="MSHTML 8.00.6001.18939">
<body class="overfwidth">
<div id="mainDiv">
<div class="barnavtop">您所在的位置：文章管理 > 编辑文章</div>
	<!--主体 开始-->
	<form name="frmApply" class="cmxform" action="${ctx}/pub/article/edit.do" target="hideframe" method="post">
	<input type="hidden" name="id" id="id" value="${m.id}" />
    <div id="container">
    	<!--按钮 开始-->  

        <!--按钮 结束-->  
        <!--框内内容 开始-->
          <div class="editspace">
              <fieldset>
                <!--ol 开始-->
                <ol>
                	<li class="listyle_4">
                    <label class="left pt5"><em>*</em>文章分类：</label>
                    <h1 class="cmxformh1"> <span class="cmxformspan">
                      <cat:category id="catCode" value="${m.catCode}"/>
                    </span></h1>
                  </li>
                  <li class="listyle_4">
                    <label class="left pt5"><em>*</em>标题：</label>
                    <h1 class="cmxformh1"> <span class="cmxformspan">
                      <input type="text" id="title" class="bgw" name="title" value="${m.title}" />
                    </span></h1>
                  </li>
                  <li class="listyle_4">
                    <label class="left pt5" >图片：</label>
                    <h1 class="cmxformh1"> <span class="cmxformspan">
                      <input type="hidden" id="pic" name="pic" value="${m.pic}" />
                      <img src="${ctx}/${m.pic}" id="pic_view" style="width: 50px;height: 50px;"/>
                      <input type="file" id="imgFile" name="imgFile" onchange="uploadImg()"  />
                    </span></h1>
                  </li>
					<li class="listyle_4">
						<label class="left pt5" ><em>*</em>状态：</label>
						<h1 class="cmxformh1"> <span class="cmxformspan">
		                      		可用<input id="status" name="status" type="radio" value="1" <c:if test="${m.status=='1'}">checked</c:if> />
									禁用<input id="status" name="status" type="radio" value="0" <c:if test="${m.status=='0'}">checked</c:if> />
		                    </span></h1>
					</li>
					<li class="listyle_4">
						<label class="left pt5" >排序：</label>
						<h1 class="cmxformh1"> <span class="cmxformspan">
		                      <input type="text" id="orderNum" name="orderNum"  class="bgw" value="${m.orderNum}"
									 onkeyup='this.value=this.value.replace(/[^0-9]/gi,"");'
									 onafterpaste='this.value=this.value.replace(/[^0-9]/gi,"")' maxlength="5" />
		                    </span></h1>
					</li>
                 <li class="listyle_4 bordernone">
                    <label class="left pt5">内容：</label>
                    <h1 class="cmxformh1"> <span class="cmxformspan">
                    <%
                    	ArticleDTO article = (ArticleDTO)request.getAttribute("m");
                    	String htmlData = article.getContent() != null ? article.getContent() : "";
					%>
                    <textarea id="content" name="content" cols="100" rows="8" style="width:600px;height:600px;visibility:hidden;">
						<%=htmlspecialchars(htmlData)%>
					</textarea>
					</span></h1>
                  </li>
                </ol>
                <!--ol 结束-->               
              </fieldset>
           
          </div>
      	<div class="toolbar mb10">
        	<a href="#" class="sexybutton" onclick="beforeSubmit();return false"><span><span>保存</span></span></a>    
        	<a href="#" class="sexybutton"  onClick="rbutton()"><span><span>重置 </span></span></a>
        	<a href="#" class="sexybutton" onclick="location.href='${ctx}/pub/article/queryList.do'"><span><span>返回</span></span></a> 
        </div>
		</div>
	</form>
</div>
</body>
</html>
<iframe name="hideframe" id="hideframe" width="0" height="0"></iframe>
<script type="text/javascript">
//上传商品图片
function uploadImg()
{
	var fieldId = "imgFile";
	var jpgname = jQuery("#"+fieldId).val();
	if(jpgname != null && jpgname!=""){
		var lastname = jpgname.toLowerCase().substr(jpgname.lastIndexOf(".")); 
	    if(lastname!=".jpg"&&lastname!=".jpeg"&&lastname!=".gif"&&lastname!=".png"&&lastname!=".bmp"){
	       alert("请选择jpg .jpeg .gif .png .bmp格式的图片上传！");
	       return;
	    }
    }else{
       alert("请选择文件！");
       return;
    }
    
    var url ='${pageContext.request.contextPath}/sys/upolad/uploadImg.do?filePath=article&r='+new Date().getTime();
	jQuery.ajaxFileUpload({
         async:false,
         url:url,
         secureuri:false,
         fileElementId:fieldId,
         dataType: 'json',
         success: function (data,status){
                 jQuery(data).each(function(i,n){
                        if(n.maxSize!=null && n.maxSize!=''){
                        	alert("图片大小不能大于"+n.maxSize+"kb！");
                        	return false;
                        }else if (n.maxWh!=null && n.maxWh!=''){
                        	alert("图片宽度不能大于"+n.maxWh+"px！");
                        	return false;
                        }else if (n.maxHt!=null && n.maxHt!=''){
                        	alert("图片高度不能大于"+n.maxHt+"px！");
                        	return false;
                     	}else{
                        	if(n.formFile!=null&&n.formFile!=""){
		     					jQuery("#pic").val(n.formFile);
		     					jQuery("#pic_view").attr("src","${pageContext.request.contextPath}/"+n.formFile);
                        	}
                        	alert("上传文件成功！");
							return  true;
                        }
                 });
         }
   });
}
</script>
<%!
private String htmlspecialchars(String str) {
	str = str.replaceAll("&lt;", "<");
	str = str.replaceAll("&gt;", ">");
	str = str.replaceAll("&quot;", "\"");
	str = str.replaceAll("&nbsp;", " ");
	str = str.replaceAll("&lt；", "<");
	str = str.replaceAll("&gt；", ">");
	str = str.replaceAll("&quot；", "\"");
	str = str.replaceAll("&nbsp；", " ");
	str = str.replaceAll("＝", "=");
	str = str.replaceAll("  ", "&nbsp;&nbsp;");
	str = str.replaceAll("；", ";");
	str = str.replaceAll("（", "(");
	str = str.replaceAll("）", ")");
	return str;
}
%>