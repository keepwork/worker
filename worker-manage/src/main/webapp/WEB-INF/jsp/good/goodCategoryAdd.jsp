<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib uri="/WEB-INF/tld/domain.tld" prefix="domain"%>
<%@ taglib uri="/WEB-INF/tld/goodCategory.tld" prefix="cat" %>
<%@ taglib prefix="c" uri="/WEB-INF/tld/c.tld" %>
<c:set var="ctx" value="${pageContext.request.contextPath}" />

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3c.org/TR/1999/REC-html401-19991224/loose.dtd">
<!-- saved from url=(0070)http://192.168.2.36:8011/cms/info/index.do -->
<HTML xmlns="http://www.w3.org/1999/xhtml">
<HEAD>
	<TITLE>后台管理系统</TITLE>
	<meta http-equiv="content-type" content="text/html; charset=UTF-8">
	<meta http-equiv="X-UA-Compatible" content="IE=EmulateIE7" />
	<link href="${ctx}/sys/css/public.css" rel="stylesheet" type="text/css">
	<link href="${ctx}/sys/css/style.css" rel="stylesheet" type="text/css">
	
	<!-- 图片上传 -->
	<script type="text/javascript" src="${ctx}/common/js/jquery-1.4.4.min.js" ></script>
	<script type="text/javascript" src="${ctx}/common/js/ajaxfileupload.js"></script>
		
	<script type="text/javascript" src="${ctx}/js/mootools-release-1.11.js"></script>
	<script type="text/javascript" src="${ctx}/js/strCheck.js" charset="gbk"></script>
	<script type="text/javascript" src="${ctx}/common/js/categoryTag.js"></script>
	<meta name=generator content="MSHTML 8.00.6001.18939">
	<script type="text/javascript">
	<!--
		function beforeSubmit()
		{
			var str= new Array(new Array(),new Array());
			str[0]=['name','分类名称',60,1,1];
			str[1]=['descr','描述',500];
			var bool=checkStr(str);
			if(!bool){
				return false;
			}
			document.frmApply.submit();
		}
	//-->
	</script>
<body class="overfwidth">
<div id="mainDiv">
<div class="barnavtop">您所在的位置：商品管理 > 商品分类管理 > 新增商品分类</div>
	<!--主体 开始-->
    <div id="container">
    	<!--按钮 开始-->  
 <div class="toolbar">
        <a href="#" class="sexybutton" onclick="beforeSubmit();return false"><span><span>保存</span></span></a>    
		<a href="#" class="sexybutton"  onClick="rbutton()"><span><span>重置 </span></span></a>
        	<a href="${ctx}/pub/goodCate/queryList.do" class="sexybutton" ><span><span>返回</span></span></a> 
               </div>
        <!--按钮 结束-->  
        <!--框内内容 开始-->
          <div class="editspace">
            <form name="frmApply" class="cmxform" action="${ctx}/pub/goodCate/add.do"
							target="hideframe" method="post" onsubmit="return beforeSubmit(this);">
              <fieldset>
                <!--ol 开始-->
                <ol>
                  <li class="listyle_4">
                    <label class="left pt5">上级分类：</label>
                    <h1 class="cmxformh1"> <span class="cmxformspan">
                      <cat:category id="parentCode" value="" />
                    </span></h1>
                  </li>
                  <li class="listyle_4">
                    <input name="Button5223" type="reset" id="rbid_" value="重填" style="display: none;">
                    <label class="left pt5"><em>*</em>分类名称：</label>
                    <h1 class="cmxformh1"> <span class="cmxformspan">
                      <input type="text" id="name" name="name"  class="bgw" />
                    </span></h1>
                  </li>
                  <li class="listyle_4">
                    <label class="left pt5" >分类图片：</label>
                    <h1 class="cmxformh1"> <span class="cmxformspan">
                      <input type="hidden" id="pic" name="pic" value="" />
                      <img src="" id="pic_view" style="width: 50px;height: 50px;"/>
                      <input type="file" id="upFile" name="upFile" onchange="uploadImg()"  />
                    </span></h1>
                  </li>
                    <li class="listyle_4">
                        <label class="left pt5" ><em>*</em>状态：</label>
                        <h1 class="cmxformh1"> <span class="cmxformspan">
		                      		可用<input id="status" name="status" type="radio" value="1" checked />
									禁用<input id="status" name="status" type="radio" value="0" />
                        </span></h1>
                    </li>
                    <li class="listyle_4">
                        <label class="left pt5" >排序：</label>
                        <h1 class="cmxformh1"> <span class="cmxformspan">
		                      <input type="text" id="orderNum" name="orderNum"  class="bgw"
                                     onkeyup='this.value=this.value.replace(/[^0-9]/gi,"");'
                                     onafterpaste='this.value=this.value.replace(/[^0-9]/gi,"")' maxlength="5" />
		                    </span></h1>
                    </li>
                 <li class="listyle_4 bordernone">
                    <label class="left pt5">分类描述：</label>
                    <textarea name="descr" id="descr" cols="80" rows="3"></textarea>
                  </li>
                </ol>
                <!--ol 结束-->               
              </fieldset>
            </form>
          </div>
      <div class="toolbar">
        <a href="#" class="sexybutton" onclick="beforeSubmit();return false"><span><span>保存</span></span></a>    
       <a href="#" class="sexybutton"  onClick="rbutton()"><span><span>重置 </span></span></a>
        <a href="${ctx}/pub/goodCate/queryList.do" class="sexybutton"><span><span>返回</span></span></a>
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
    
    var url ='${pageContext.request.contextPath}/sys/upolad/uploadImg.do?filePath=good&r='+new Date().getTime();
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
