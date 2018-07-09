<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib uri="/WEB-INF/tld/c.tld" prefix="c"%>
<c:set var="ctx" value="${pageContext.request.contextPath}" />

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<HEAD>
		<TITLE>后台管理系统</TITLE>
		<meta http-equiv="content-type" content="text/html; charset=UTF-8">
		<meta http-equiv="X-UA-Compatible" content="IE=EmulateIE7" />
		<meta name=generator content="MSHTML 8.00.6001.18939">
		<link href="${ctx}/sys/css/public.css" rel="stylesheet" type="text/css">
		<link href="${ctx}/sys/css/style.css" rel="stylesheet" type="text/css">
		
		<!-- 图片上传 -->
		<script type="text/javascript" src="${ctx}/common/js/jquery-1.4.4.min.js" ></script>
		<script type="text/javascript" src="${ctx}/common/js/ajaxfileupload.js"></script>
		
		<!-- 商品类型树 -->
		<link rel=StyleSheet type=text/css href="<c:url value="/common/dtree/dtree.css"/>">
		<script type="text/javascript" src="<c:url value="/common/dtree/dtree.js"/>" charset="UTF-8"></script>
		
		<!-- kindeditor -->
		<link rel="stylesheet" href="${ctx}/common/kindeditor-4.0.5/themes/default/default.css" />
		<link rel="stylesheet" href="${ctx}/common/kindeditor-4.0.5/plugins/code/prettify.css" />
		<script type="text/javascript" src="${ctx}/common/kindeditor-4.0.5/kindeditor.js"></script>
		<script type="text/javascript" src="${ctx}/common/kindeditor-4.0.5/lang/zh_CN.js"></script>
		<script type="text/javascript" src="${ctx}/common/kindeditor-4.0.5/plugins/code/prettify.js"></script>
		<script type="text/javascript">
		  KindEditor.ready(function(K) {
				var descr2Editor = K.create('textarea[name="descr2"]', {
					cssPath : '${ctx}/common/kindeditor-4.0.5/plugins/code/prettify.css',
					uploadJson : '${ctx}/common/kindeditor-4.0.5/jsp/upload_json.jsp',
					fileManagerJson : '${ctx}/common/kindeditor-4.0.5/jsp/file_manager_json.jsp',
					allowFileManager : true,
					afterCreate : function() {
				     	this.sync();
				    },
				    afterBlur:function(){
			            this.sync();
			        } 
				});
				descr2Editor.sync();
			});
	    </script>
		<script type="text/javascript">
			String.prototype.replaceAll = function(reallyDo, replaceWith, ignoreCase) {  
			    if (!RegExp.prototype.isPrototypeOf(reallyDo)) {  
			        return this.replace(new RegExp(reallyDo, (ignoreCase ? "gi": "g")), replaceWith);  
			     } else {  
			        return this.replace(reallyDo, replaceWith);  
			     }  
			}
			function beforeSubmit()
			{
				 var boxs = document.getElementsByTagName('input');
			     var ids = '';
				 var checkbox = false;
				 for(var b=0;b<boxs.length;b++){
					if(boxs[b].type=='checkbox'&& boxs[b].checked){
						 checkbox = true;
					}
				 }
				 if(checkbox == false){
			        alert("请选择配件分类");
			        return;
			     }
				 
				 var name = document.getElementById('name');
		    	 //var price = document.getElementById('price');
		    	 //var point = document.getElementById('point');
		    	 if(name.value == ''){
		    		alert("配件名称不能为空");
		    		name.focus();
		    		return;
		    	 }
		    	 //if(price.value == ''){
		    	 //	alert("零售价不能为空");
		    	 //	price.focus();
		    	 //	return;
		    	 //}
		    	 //if(point.value == ''){
		    	//	alert("积分价不能为空");
		    	//	point.focus();
		    	//	return;
		    	// }
		    	 
		    	// var descr2 = document.getElementById("descr2").value;
				// descr2 = descr2.replaceAll("<", "&lt;");
				// descr2 = descr2.replaceAll(">", "&gt;");
				// descr2 = descr2.replaceAll("\"", "&quot;");
				// descr2 = descr2.replaceAll(" ", "&nbsp;");
				// document.getElementById("descr2").value = descr2;
				
				 document.frmApply.submit();
			}
		</script>
		
<body class="overfwidth">
<div id="mainDiv">
<div class="barnavtop">您所在的位置：配件管理 > 新增配件</div>
	<!--主体 开始-->
    <div id="container">
    	<!--按钮 开始-->  
 <div class="toolbar">
        <a href="#" class="sexybutton" onclick="beforeSubmit();return false"><span><span>保存</span></span></a>    
		<a href="#" class="sexybutton"  onClick="rbutton()"><span><span>重置 </span></span></a>
        	<a href="${ctx}/pub/article/queryList.do" class="sexybutton" ><span><span>返回</span></span></a> 
               </div>
        <!--按钮 结束-->
        <!--框内内容 开始-->
          <div class="editspace">
            <form name="frmApply" class="cmxform" action="${ctx}/pub/good/add.do"
							target="hideframe" method="post" onsubmit="return beforeSubmit(this);">
              <fieldset>
                <!--ol 开始-->
                <ol>
                  <li class="listyle_4">
                    <label class="left" style="width: 17%;text-align: left;padding-top: 30px;">
						<jsp:include page="/WEB-INF/jsp/good/tree.jsp" flush="true" />
					</label>
                    <h1 class="cmxformh1" style="width: 80%;"> <span class="cmxformspan">
                      <ol>
		                  <li class="listyle_4">
		                    <label class="left pt5" style="width: 100px;"><em>*</em>配件名称：</label>
		                    <h1 class="cmxformh1"> <span class="cmxformspan" style="width: 600px;">
		                      <input type="text" id="name" name="name"  class="bgw" style="width: 400px;"/>
		                    </span></h1>
		                  </li>
		                  <li class="listyle_4">
		                    <label class="left pt5" style="width: 100px;"><em>*</em>配件型号：</label>
		                    <h1 class="cmxformh1"> <span class="cmxformspan" style="width: 600px;">
		                      <input type="text" id="code" name="code"  class="bgw" style="width: 400px;"/>
		                    </span></h1>
		                  </li>
		                  <!-- 
		                  <li class="listyle_4">
		                    <label class="left pt5" style="width: 100px;"><em>*</em>商品图片：</label>
		                    <h1 class="cmxformh1"> <span class="cmxformspan">
		                      <input type="hidden" id="spic" name="spic" value="" />
		                      <img src="" id="spic_view" style="width: 50px;height: 50px;"/>
		                      <input type="file" id="imgFile" name="imgFile" onchange="uploadImg()"  />
		                    </span></h1>
		                  </li>
		                   -->
		                  <li class="listyle_4">
		                    <label class="left pt5" style="width: 100px;"><em>*</em>状态：</label>
		                    <h1 class="cmxformh1"> <span class="cmxformspan">
		                      		上架<input id="status" name="status" type="radio" value="0" checked />
									下架<input id="status" name="status" type="radio" value="1" />
									缺货<input id="status" name="status" type="radio" value="2" />
		                    </span></h1>
		                  </li>
		                  <li class="listyle_4">
		                    <label class="left pt5" style="width: 100px;"><em>*</em>零售价（元）：</label>
		                    <h1 class="cmxformh1"> <span class="cmxformspan">
		                      <input type="text" id="price" name="price"  class="bgw" style="width: 400px;"
		                      onkeyup='this.value=this.value.replace(/[^0-9.]/gi,"");'
							  onafterpaste='this.value=this.value.replace(/[^0-9.]/gi,"")' maxlength="10" />
		                    </span></h1>
		                  </li>
		                  <!-- 
		                  <li class="listyle_4">
		                    <label class="left pt5" style="width: 100px;">市场价（元）：</label>
		                    <h1 class="cmxformh1"> <span class="cmxformspan">
		                      <input type="text" id="priceMarket" name="priceMarket"  class="bgw" style="width: 400px;"
		                      onkeyup='this.value=this.value.replace(/[^0-9.]/gi,"");'
							  onafterpaste='this.value=this.value.replace(/[^0-9.]/gi,"")' maxlength="10" />
		                    </span></h1>
		                  </li>
		                  <li class="listyle_4">
		                    <label class="left pt5" style="width: 100px;"><em>*</em>积分价：</label>
		                    <h1 class="cmxformh1"> <span class="cmxformspan">
		                      <input type="text" id="point" name="point"  class="bgw" style="width: 400px;"
		                      onkeyup='this.value=this.value.replace(/[^0-9.]/gi,"");'
							  onafterpaste='this.value=this.value.replace(/[^0-9.]/gi,"")' maxlength="10" />
		                    </span></h1>
		                  </li>
		                  <li class="listyle_4">
		                    <label class="left pt5" style="width: 100px;"><em>*</em>库存：</label>
		                    <h1 class="cmxformh1"> <span class="cmxformspan">
		                      <input type="text" id="isbn" name="isbn"  class="bgw" style="width: 400px;" 
		                      onkeyup='this.value=this.value.replace(/[^0-9]/gi,"");'
							  onafterpaste='this.value=this.value.replace(/[^0-9]/gi,"")' maxlength="10" />
		                    </span></h1>
		                  </li>
		                   -->
		                  <li class="listyle_4">
		                    <label class="left pt5" style="width: 100px;">排序：</label>
		                    <h1 class="cmxformh1"> <span class="cmxformspan">
		                      <input type="text" id="orderNum" name="orderNum"  class="bgw" style="width: 400px;"
							  onkeyup='this.value=this.value.replace(/[^0-9]/gi,"");'
							  onafterpaste='this.value=this.value.replace(/[^0-9]/gi,"")' maxlength="5" />
		                    </span></h1>
		                  </li>
		                  <li class="listyle_4 bordernone">
		                    <label class="left pt5" style="width: 100px;">配件简介：</label>
		                    <h1 class="cmxformh1"> <span class="cmxformspan">
		                    <textarea id="descr1" name="descr1" rows="8" style="width:400px;height:50px;"></textarea>
		                    </span></h1>
		                  </li>
		                  <!-- 
		                  <li class="listyle_4 bordernone">
		                    <label class="left pt5" style="width: 100px;">详细介绍：</label>
		                    <h1 class="cmxformh1"> <span class="cmxformspan">
		                    <textarea id="descr2" name="descr2" rows="8" style="width:98%;height:300px;"></textarea>
		                    </span></h1>
		                  </li>
		                   -->
		                </ol>
                    </span></h1>
                  </li>
                <!--ol 结束-->               
              </fieldset>
            </form>
          </div>
      <div class="toolbar">
        <a href="#" class="sexybutton" onclick="beforeSubmit();return false"><span><span>保存</span></span></a>    
       <a href="#" class="sexybutton"  onClick="rbutton()"><span><span>重置 </span></span></a>
        <a href="${ctx}/pub/article/queryList.do" class="sexybutton"><span><span>返回</span></span></a>
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
		     					jQuery("#spic").val(n.formFile);
		     					jQuery("#spic_view").attr("src","${pageContext.request.contextPath}/"+n.formFile);
                        	}
                        	alert("上传文件成功！");
							return  true;
                        }
                 });
         }
   });
}
</script>
