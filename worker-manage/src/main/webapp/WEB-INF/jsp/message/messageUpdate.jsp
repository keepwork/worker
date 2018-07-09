<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib uri="/WEB-INF/tld/domain.tld" prefix="domain"%>
<%@ taglib uri="/WEB-INF/tld/messageCategory.tld" prefix="msg" %>
<%@ taglib uri="/WEB-INF/tld/c.tld" prefix="c"%>
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
		
		<script type="text/javascript">
		function beforeSubmit()
		{
			var title = document.getElementById('title');
	    	if(title.value == ''){
	    		alert("消息标题不能为空");
	    		title.focus();
	    		return;
	    	}
	    	var content_ = document.getElementById('content');
	    	if(content_.value == ''){
	    		alert("消息内容不能为空");
	    		content_.focus();
	    		return;
	    	}
			document.frmApply.submit();
		}
		</script>
<meta name=generator content="MSHTML 8.00.6001.18939">
<body class="overfwidth">
<div id="mainDiv">
<div class="barnavtop">您所在的位置：消息管理 > 编辑消息</div>
	<!--主体 开始-->
	<form name="frmApply" class="cmxform" action="${ctx}/pub/message/edit.do" target="hideframe" method="post">
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
                    <label class="left pt5"><em>*</em>消息分类：</label>
                    <h1 class="cmxformh1"> <span class="cmxformspan">
                      <msg:message id="catCode" value="${m.catCode}"/>
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
                      <input type="file" id="upFile" name="upFile" onchange="uploadImg()"  />
                    </span></h1>
                  </li>
                  <li class="listyle_4">
                    <label class="left pt5">排序：</label>
                    <h1 class="cmxformh1"> <span class="cmxformspan">
                      <input type="text" id="orderNum" class="bgw" name="orderNum" value="${m.orderNum}" />
                    </span></h1>
                  </li>
                 <li class="listyle_4 bordernone">
                    <label class="left pt5">内容：</label>
                    <h1 class="cmxformh1"> <span class="cmxformspan">
	                    <textarea id="content" name="content" style="width:600px;height:200px;">
							${m.content}
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
        	<a href="#" class="sexybutton" onclick="location.href='${ctx}/pub/message/queryList.do'"><span><span>返回</span></span></a> 
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
	var fieldId = "upFile";
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
    
    var url ='${pageContext.request.contextPath}/sys/upolad/uploadImg.do?filePath=message&r='+new Date().getTime();
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
