<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib uri="/WEB-INF/tld/domain.tld" prefix="domain"%>
<%@ taglib uri="/WEB-INF/tld/c.tld" prefix="c"%>
<c:set var="ctx" value="${pageContext.request.contextPath}" />
<%@ include file="../common/commonHeader.jsp"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3c.org/TR/1999/REC-html401-19991224/loose.dtd">
<!-- saved from url=(0070)http://192.168.2.36:8011/cms/info/index.do -->
<HTML xmlns="http://www.w3.org/1999/xhtml">
<HEAD>
		<TITLE>后台管理系统</TITLE>
		<meta http-equiv="content-type" content="text/html; charset=UTF-8">
		<meta http-equiv="X-UA-Compatible" content="IE=EmulateIE7" />
		<meta name=generator content="MSHTML 8.00.6001.18939">
        <link href="${pageContext.request.contextPath}/sys/css/public.css" rel="stylesheet" type="text/css" />
        <link href="${pageContext.request.contextPath}/sys/css/style.css" rel="stylesheet" type="text/css" />
		
		<!-- 图片上传 -->
		<script type="text/javascript" src="${ctx}/common/js/jquery-1.4.4.min.js" ></script>
		<script type="text/javascript" src="${ctx}/common/js/ajaxfileupload.js"></script>
		
	    <script type="text/javascript" src="${ctx}/common/js/Base64.js"></script>
		<script type="text/javascript">
			function beforeSubmit(cate)
			{
				var title = document.getElementById('title');
		    	if(title.value == ''){
		    		alert("广告标题不能为空");
		    		title.focus();
		    		return;
		    	}
		    	
		    	if(cate=='1'){
		    		var content = document.getElementById('content');
			    	if(content.value == ''){
			    		alert("广告内容不能为空");
			    		content.focus();
			    		return;
			    	}
			    	
			    	var content = document.getElementById("content").value;
					content = utf16to8(content);
					content = base64encode(content);
					document.getElementById("content").value = content;
		    	}
		
		    	if(cate=='2'){
		    		var pic = document.getElementById('pic');
			    	if(pic.value == ''){
			    		alert("必须选择一张图片");
			    		pic.focus();
			    		return;
			    	}
		    	}
		    	
				document.frmApply.submit();
			}
		</script>
<body class="overfwidth">
<div id="mainDiv">
<div class="barnavtop">您所在的位置：广告管理 > 新增广告</div>
	<!--主体 开始-->
    <div id="container">
    	<!--按钮 开始-->  
 <div class="toolbar">
        <a href="#" class="sexybutton" onclick="beforeSubmit('${cate}');return false"><span><span>保存</span></span></a>    
		<a href="#" class="sexybutton"  onClick="rbutton()"><span><span>重置 </span></span></a>
        	<a href="${ctx}/pub/advert/queryList.do?cate=${cate}" class="sexybutton" ><span><span>返回</span></span></a> 
               </div>
        <!--按钮 结束-->
        <!--框内内容 开始-->
          <div class="editspace">
            <form name="frmApply" class="cmxform" action="${ctx}/pub/advert/add.do?cate=${cate}"
							target="hideframe" method="post" onsubmit="return beforeSubmit(this);">
              <fieldset>
                <!--ol 开始-->
                <ol>
                  <li class="listyle_4">
                    <label class="left pt5"><em>*</em>广告类型：</label>
                    <h1 class="cmxformh1"> <span class="cmxformspan" style="width: 60%;">
                      <domain:radioDomain domain="advertType" name="type" uid="type" value="wap" />
                    </span></h1>
                  </li>
                  <li class="listyle_4">
                    <label class="left pt5"><em>*</em>标题：</label>
                    <h1 class="cmxformh1"> <span class="cmxformspan">
                      <input type="text" id="title" name="title"  class="bgw" />
                    </span></h1>
                  </li>
                  <li class="listyle_4">
                    <label class="left pt5"><em>*</em>链接：</label>
                    <h1 class="cmxformh1"> <span class="cmxformspan">
                      <input type="text" id="url" name="url"  class="bgw" />
                    </span></h1>
                  </li>
                  
                  <!-- 图片广告 -->
                  <c:if test="${cate=='2'}">
                  <li class="listyle_4"> 
                    <label class="left pt5" >图片：</label>
                    <h1 class="cmxformh1"> <span class="cmxformspan">
                      <input type="hidden" id="pic" name="pic" value="" />
                      <img src="" id="sPic_view" style="width: 50px;height: 50px;"/>
                      <input type="file" id="imgFile" name="imgFile" onchange="uploadImg()"  />
                    </span></h1>
                  </li>
                  </c:if>
         
        		 <!-- 联盟广告 -->
                 <c:if test="${cate=='1'}">
                 <li class="listyle_4 bordernone">
                    <label class="left pt5"><em>*</em>内容：</label>
                    <h1 class="cmxformh1"> <span class="cmxformspan">
                    <textarea id="content" name="content" rows="8" style="width:600px;height:200px;"></textarea>
                    </span></h1>
                  </li>
                  </c:if>
                  
                  <li class="listyle_4">
                    <label class="left pt5"><em>*</em>排序：</label>
                    <h1 class="cmxformh1"> <span class="cmxformspan">
                      <input type="text" id="orderNum" name="orderNum"  class="bgw" value="0"/>
                    </span></h1>
                  </li>
                </ol>
                <!--ol 结束-->               
              </fieldset>
            </form>
          </div>
      <div class="toolbar">
        <a href="#" class="sexybutton" onclick="beforeSubmit('${cate}');return false"><span><span>保存</span></span></a>    
       <a href="#" class="sexybutton"  onClick="rbutton()"><span><span>重置 </span></span></a>
        <a href="${ctx}/pub/advert/queryList.do?cate=${cate}" class="sexybutton"><span><span>返回</span></span></a>
               </div>
              </div>
</div>

	<iframe name="hideframe" id="hideframe" width="0" height="0"></iframe>
</body>
</html>
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
    
    var url ='${pageContext.request.contextPath}/sys/upolad/uploadImg.do?filePath=advert&r='+new Date().getTime();
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