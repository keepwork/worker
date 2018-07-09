<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib uri="/WEB-INF/tld/c.tld" prefix="c"%>
<c:set var="ctx" value="${pageContext.request.contextPath}" />
<%@page import="com.shop.good.model.dto.GoodDTO"%>
<%@page import="com.sinovatech.common.config.GlobalConfig"%>
<%
	String serverDomain = GlobalConfig.getProperty("filePath", "prefix");
	System.out.println("===============================serverDomain:"+serverDomain);
%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<HEAD>
		<TITLE>后台管理系统</TITLE>
		<meta http-equiv="X-UA-Compatible" content="IE=EmulateIE7" />
		<meta name=generator content="MSHTML 8.00.6001.18939">
		<link href="${ctx}/sys/css/public.css" rel="stylesheet" type="text/css">
		<link href="${ctx}/sys/css/style.css" rel="stylesheet" type="text/css">
		<script type="text/javascript" src="${ctx}/js/strCheck.js" charset="gbk"></script>
		<script type="text/javascript" src="${ctx}/common/js/jquery-1.9.1.min.js"></script>
		
		<!-- 商品类型树 -->
		<link rel=StyleSheet type=text/css href="<c:url value="/common/dtree/dtree.css"/>">
		<script type="text/javascript" src="<c:url value="/common/dtree/dtree.js"/>" charset="UTF-8"></script>
		
		<!-- kindeditor -->
		<link rel="stylesheet" href="${ctx}/common/kindeditor-4.0.5/themes/default/default.css" />
		<link rel="stylesheet" href="${ctx}/common/kindeditor-4.0.5/plugins/code/prettify.css" />
		<script type="text/javascript" src="${ctx}/common/kindeditor-4.0.5/kindeditor.js" ></script>
		<script type="text/javascript" src="${ctx}/common/kindeditor-4.0.5/lang/zh_CN.js"></script>
		<script type="text/javascript" src="${ctx}/common/kindeditor-4.0.5/plugins/code/prettify.js"></script>
		<script type="text/javascript">
			KindEditor.ready(function(K) {
		   		var descr2Editor = K.create('textarea[name="descr2"]', {
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
			        alert("请选择商品分类");
			        return;
			     }
				 
				 var name = document.getElementById('name');
		    	 //var price = document.getElementById('price');
		    	 var point = document.getElementById('point');
		    	 if(name.value == ''){
		    		alert("商品名称不能为空");
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
		    	 //}
		    	 
		    	 //var descr2 = document.getElementById("descr2").value;
				// descr2 = descr2.replaceAll("<", "&lt;");
				// descr2 = descr2.replaceAll(">", "&gt;");
				// descr2 = descr2.replaceAll("\"", "&quot;");
				// descr2 = descr2.replaceAll(" ", "&nbsp;");
				// document.getElementById("descr2").value = descr2;
				
				 document.frmApply.submit();
			}
		</script>
		
		
		
		
		
		<!-- ============ 多图片上传swfupolad begin ============ -->
		<style type="text/css">
			 <!-- 商品图片上传弹出层 begin -->
			 .bg-tan {background-color:#000;filter:Alpha(opacity=40);-moz-opacity:0.4;opacity:0.4; position:fixed;_position:absolute;width:100%; height:100%;left:0;top:0;z-index:996; display:none;_height:expression(eval(document.documentElement.scrollTop+document.documentElement.clientHeight));}
			 .tanp{ position:absolute; right:10px; top:10px; cursor:pointer;}
			 .sport_rule{position:absolute;z-index:999;width:500px;left:0;top:0;background:#D3E0F2; display:none; overflow:hidden; zoom:1;  }
			 <!-- 商品图片上传弹出层 end -->
			 <!-- 商品图片展示列表 begin -->
			 p{margin:0;}
			.sc_demoimg{overflow:hidden;width:630px;}
			.sc_demoimg li{float:left;margin:3px;width:80px;height:100px;overflow:hidden;position:relative;line-height:20px;font-size:12px; text-align:center;border:2px solid #ccc;}
			.sc_demoimg li a{color:#ed4355; text-decoration:none;}
			.sc_demoimg li a:hover{color:#ed4355; text-decoration:underline;}
			.sc_demoimg li.hover{border:2px solid #f96d7d;}
			.sc_demoimg li i{ background:url(demo_imgon.gif) no-repeat;width:20px;height:20px; position:absolute;right:0px;bottom:20px; display:none;}
			.sc_demoimg li.hover i{ display:block;}
			<!-- 商品图片展示列表 end -->
		</style>
		<!-- <link href="${pageContext.request.contextPath}/swfupload/css/default.css" rel="stylesheet" type="text/css" /> -->
		<!-- <script type="text/javascript" src="http://www.gearcode.com/lib/swfupload/swfupload.js" type="text/javascript"></script>-->
	    <script type="text/javascript" src="${pageContext.request.contextPath}/swfupload/js/swfupload.js"></script>
	    <script type="text/javascript" src="${pageContext.request.contextPath}/swfupload/js/swfupload.queue.js"></script>
	    <script type="text/javascript" src="${pageContext.request.contextPath}/swfupload/js/fileprogress.js"></script>
	    <script type="text/javascript" src="${pageContext.request.contextPath}/swfupload/js/handlers.js"></script>
	    <style type="text/css">
			div.fieldset {
				border:  1px solid #afe14c;
				margin: 27px 0;
				padding: 20px 10px;
				width: 400px;
			}
			div.fieldset span.legend {
				position: relative;
				background-color: #FFF;
				padding: 3px;
				top: -30px;
				font: 700 14px Arial, Helvetica, sans-serif;
				color: #73b304;
			}
			.blue {
			    background-color: #f0f5ff;
			    border: 1px solid #cee2f2;
			}
			.progressContainer {
			    background-color: #f7f7f7;
			    border: 1px solid #e8e8e8;
			    margin: 5px;
			    overflow: hidden;
			    padding: 4px;
			}
	    </style>
	    <script type="text/javascript">
		 var upload1;
		 window.onload = function() {
			upload1 = new SWFUpload({
				//提交路径
				//upload_url: "http://192.168.1.99:8080/shltScgbBack/uploadProductImagesServlet?modeId="+$("#id").val(),
				upload_url: "<%=serverDomain%>/uploadProductImagesServlet?goodId="+$("#id").val(),
				//upload_url: "http://61.240.234.137:8080/shltScgbBack/uploadProductImagesServlet?modeId="+$("#id").val(),
				//upload_url: "http://127.0.0.1:8080/shltScgbBack/uploadProductImagesServlet;jsessionid=<%=request.getSession().getId()%>",
				//向后台传递额外的参数
				//post_params: {"modeId" : $("#id").val()},
				preserve_relative_urls: true,                 // 保留相对路径不做转换  
				//上传文件的名称
				//file_post_name: "file",
				// 下面自己按照字面意思理解
				file_size_limit : "2048",	// 2MB
				//file_types : "*.*",
				file_types : "*.jpg;*.png;*.gif",
				file_types_description : "Image Files",
				//上传文件选取的最大队列数
				file_upload_limit : "10",
				file_queue_limit : "0",
				// 事件处理
				file_dialog_start_handler : fileDialogStart,
				file_queued_handler : fileQueued,
				file_queue_error_handler : fileQueueError,
				file_dialog_complete_handler : fileDialogComplete,
				
				// 事件处理,可以自己在handlers.js里面扩充,极大的方便了开发者
				// 就是要在handlers里面定义如下的function,当然function里面可以什么也不干,或者用源代码自带的也行
				upload_start_handler : uploadStart,
				upload_progress_handler : uploadProgress,
				upload_error_handler : uploadError,
				upload_success_handler : uploadSuccess,
				upload_complete_handler : uploadComplete,
				
				// 按钮的处理
				button_placeholder_id : "spanButtonPlaceholder1",
				button_image_url : "<%=serverDomain%>/swfupload/images/XPButtonUploadText_61x22.png",
				button_text_style : "1111",
				button_width: 61,
				button_height: 22,
				//button_text: '<span class="theFont">浏览图片</span>' ,
				//button_text_style: ".theFont { font-size: 12; }" ,
				//button_text_left_padding: 12,
				//button_text_top_padding: 3, 

				// Flash Settings
				flash_url : "<%=serverDomain%>/swfupload/js/swfupload.swf",
				custom_settings : {
					progressTarget : "fsUploadProgress1",
					cancelButtonId : "btnCancel1"
				},
				// Debug Settings
				debug: false
			});
	     }
	     //关闭弹出窗，并刷新终端图片列表
		 function choosePicture(){   
  	    	$('.bg-tan,.sport_rule').hide();
  	    	window.location.href = "${pageContext.request.contextPath}/pub/good/beforeEdit.do?id="+$("#id").val();
  	   	 }
  	   	 //删除终端图片
		 function delPicture(imgId){   
  	    	if(confirm("你确定要删除图片吗?")){
  	    		$.ajax({
						 type:"post",
						 dataType:"html",
						 url:"${ctx}/pub/good/deleteProductImg.do",
						 data:"imgId="+imgId,
						 async:false,
						 complete: function (XMLHttpRequest,textStatus) {
						    if(XMLHttpRequest.responseText!=""){
			                    if(XMLHttpRequest.responseText==1){
							 		alert("删除成功！");
							 		window.location.href="${ctx}/pub/good/beforeEdit.do?id="+$("#id").val();
							 	}else{
							 		alert("删除失败！");
							 	}
			                }
						 }
					});
  	   	 	}
  	   	 }
  	   	 
  	   	 //图片上传弹出层操作 begin
  	   	 $('.bg-tan').hide();
		 $(".sport_rule").hide();
  	   	 function selectImg(){
  	   	 	var leftN=$(window).width()/2-378;
			var topN=$(window).height()/2-150;
			$('.bg-tan').show();
			$(".sport_rule").show().css({"top":topN,"left":leftN});
  	   	 }
		 function hideDiv(){
			$('.bg-tan,.sport_rule').hide();
			//choosePicture();
		 }
		 //图片上传弹出层操作 end
		 
   		</script>
	    <!-- ============ 多图片上传swfupolad end ============ -->
		

<body class="overfwidth">


   <!-- 多图片上传弹出层 begin -->
   <div class="bg-tan" ></div>
   <div class="sport_rule">
	  <a class="tanp" onclick="hideDiv()"><img src="${ctx}/images/closeTanChuDiv.gif" width="15" height="15" /></a>
	  	<table border="0" height="200" style="margin-left: 40px;">
	  	  <tr valign="top">
				<td>
					<div style="background:#D3E0F2;">
						<div style="padding-left: 5px;background:#D3E0F2;">
							<span id="spanButtonPlaceholder1"></span>
							<!--<input type="button" value="上传" onclick="upload1.addPostParam('idname',encodeURI(document.getElementById('myFileName').value));upload1.startUpload();"/>
							-->
							<input id="btnCancel1" type="button" value="Cancel Uploads" onclick="cancelQueue(upload1);" disabled="disabled" style="margin-left: 2px; height: 22px; font-size: 8pt;display: none;" />
						</div>
						<div class="fieldset flash" id="fsUploadProgress1" style="background:#D3E0F2;">
							<span class="legend">文件上传</span>
						</div>
						<div style="padding-left: 5px;background:#D3E0F2;">
							<input type="button" onclick="choosePicture()" value="完 成" style="margin-left: 2px; height: 26px; font-size: 10pt;">
						</div>
					</div>
				</td>
			</tr>
	  	</table>
   </div>
   <!-- 多图片上传弹出层 end -->	
   
   
<div id="mainDiv">
<div class="barnavtop">您所在的位置：配件管理 > 编辑配件</div>
	<!--主体 开始-->
	<form name="frmApply" class="cmxform" action="${ctx}/pub/good/edit.do" target="hideframe" method="post">
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
                    <label class="left" style="width: 17%;text-align: left;padding-top: 30px;">
						<jsp:include page="/WEB-INF/jsp/good/tree.jsp" flush="true" />
					</label>
                    <h1 class="cmxformh1" style="width: 80%;"> <span class="cmxformspan">
                      <ol>
		                  <li class="listyle_4">
		                    <label class="left pt5" style="width: 100px;"><em>*</em>配件名称：</label>
		                    <h1 class="cmxformh1"> <span class="cmxformspan" style="width: 600px;">
		                      <input type="text" id="name" name="name"  class="bgw" style="width: 400px;" value="${m.name}"/>
		                    </span></h1>
		                  </li>
		                  <li class="listyle_4">
		                    <label class="left pt5" style="width: 100px;"><em>*</em>配件型号：</label>
		                    <h1 class="cmxformh1"> <span class="cmxformspan" style="width: 600px;">
		                      <input type="text" id="code" name="code"  class="bgw" style="width: 400px;" value="${m.code}"/>
		                    </span></h1>
		                  </li>
		                  <li class="listyle_4">
		                    <label class="left pt5" style="width: 100px;"><em>*</em>配件图片：</label>
		                    <h1 class="cmxformh1"> <span class="cmxformspan">
		                      <input target="_self" type="button" value="上传图片" onclick="selectImg()"/>
		                    </span></h1>
		                  </li>
		                  <li class="listyle_4">
		                    <label class="left pt5" style="width: 100px;">配件图片展示：</label>
		                    <h1 class="cmxformh1"> <span class="cmxformspan">
		                      	<ul class="sc_demoimg baseball2">
								<c:if test="${not empty picList}">
								<c:forEach items="${picList}" var="p">
								<li>
							    	<p><img src="${pageContext.request.contextPath}/ProductIMG/${p.pic}" class="picture" style="width: 70px;height: 72px;" /></p>
							        <p><a href="#" onclick="delPicture('${p.id}')">删除</a></p>
							    </li> 
							    </c:forEach>
							    </c:if>
							    </ul>
		                    </span></h1>
		                  </li>
		                  
		                  
		                  <li class="listyle_4">
		                    <label class="left pt5" style="width: 100px;"><em>*</em>状态：</label>
		                    <h1 class="cmxformh1"> <span class="cmxformspan">
		                      		上架<input id="status" name="status" type="radio" value="0" <c:if test="${m.status=='0'}">checked</c:if> />
									下架<input id="status" name="status" type="radio" value="1" <c:if test="${m.status=='1'}">checked</c:if> />
									缺货<input id="status" name="status" type="radio" value="2" <c:if test="${m.status=='2'}">checked</c:if> />
		                    </span></h1>
		                  </li>
		                 
		                  <li class="listyle_4">
		                    <label class="left pt5" style="width: 100px;"><em>*</em>零售价（元）：</label>
		                    <h1 class="cmxformh1"> <span class="cmxformspan">
		                      <input type="text" id="price" name="price"  class="bgw" style="width: 400px;" value="${m.price}"
		                      onkeyup='this.value=this.value.replace(/[^0-9.]/gi,"");'
							  onafterpaste='this.value=this.value.replace(/[^0-9.]/gi,"")' maxlength="10" />
		                    </span></h1>
		                  </li>
		                   <!-- 
		                  <li class="listyle_4">
		                    <label class="left pt5" style="width: 100px;">市场价（元）：</label>
		                    <h1 class="cmxformh1"> <span class="cmxformspan">
		                      <input type="text" id="priceMarket" name="priceMarket"  class="bgw" style="width: 400px;" value="${m.priceMarket}"
		                      onkeyup='this.value=this.value.replace(/[^0-9.]/gi,"");'
							  onafterpaste='this.value=this.value.replace(/[^0-9.]/gi,"")' maxlength="10" />
		                    </span></h1>
		                  </li>
		                  <li class="listyle_4">
		                    <label class="left pt5" style="width: 100px;"><em>*</em>积分价：</label>
		                    <h1 class="cmxformh1"> <span class="cmxformspan">
		                      <input type="text" id="point" name=point  class="bgw" style="width: 400px;" value="${m.point}"
		                      onkeyup='this.value=this.value.replace(/[^0-9.]/gi,"");'
							  onafterpaste='this.value=this.value.replace(/[^0-9.]/gi,"")' maxlength="10" />
		                    </span></h1>
		                  </li>
		                  <li class="listyle_4">
		                    <label class="left pt5" style="width: 100px;"><em>*</em>库存：</label>
		                    <h1 class="cmxformh1"> <span class="cmxformspan">
		                      <input type="text" id="isbn" name="isbn"  class="bgw" style="width: 400px;" value="${m.isbn}"
		                      onkeyup='this.value=this.value.replace(/[^0-9]/gi,"");'
							  onafterpaste='this.value=this.value.replace(/[^0-9]/gi,"")' maxlength="10" />
		                    </span></h1>
		                  </li>
		                   -->
		                  <li class="listyle_4">
		                    <label class="left pt5" style="width: 100px;">排序：</label>
		                    <h1 class="cmxformh1"> <span class="cmxformspan">
		                      <input type="text" id="orderNum" name="orderNum"  class="bgw" style="width: 400px;" value="${m.orderNum}"
		                      onkeyup='this.value=this.value.replace(/[^0-9]/gi,"");'
							  onafterpaste='this.value=this.value.replace(/[^0-9]/gi,"")' maxlength="5" />
		                    </span></h1>
		                  </li>
		                  <li class="listyle_4 bordernone">
		                    <label class="left pt5" style="width: 100px;">配件简介：</label>
		                    <h1 class="cmxformh1"> <span class="cmxformspan">
		                    <textarea id="descr1" name="descr1" rows="8" style="width:400px;height:50px;">${m.descr1}</textarea>
		                    </span></h1>
		                  </li>
		                </ol>
                    </span></h1>
                  </li>
                <!--ol 结束-->               
              </fieldset>
              
          </div>
      	<div class="toolbar mb10">
        	<a href="#" class="sexybutton" onclick="beforeSubmit();return false"><span><span>保存</span></span></a>    
        	<a href="#" class="sexybutton"  onClick="rbutton()"><span><span>重置 </span></span></a>
        	<a href="#" class="sexybutton" onclick="location.href='${ctx}/pub/good/queryList.do'"><span><span>返回</span></span></a> 
        </div>
		</div>
	</form>
</div>
<iframe name="hideframe" id="hideframe" width="0" height="0"></iframe>
</body>
</html>


<c:if test="${cateMap!=null}">
<c:forEach items="${cateMap}" var="map" >
	<SCRIPT LANGUAGE="JavaScript">
		var boxs = document.getElementsByTagName('input');
		for(var b=0;b<boxs.length;b++){
			if(boxs[b].type=='checkbox'){
			   if(boxs[b].value == '${map.value}'){
			       boxs[b].checked = 'true';
			   }
			}
		}
	</SCRIPT>
</c:forEach>
</c:if>
