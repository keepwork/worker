<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib uri="/WEB-INF/tld/domain.tld" prefix="domain"%>
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
	    
		<script type="text/javascript">
			function synRedis(){
				document.frmSeo.submit();
			}
			
			function beforeSubmit()
			{
				var title = document.getElementById('title');
		    	if(title.value == ''){
		    		alert("title不能为空");
		    		title.focus();
		    		return;
		    	}
		    	
		    	var keywords = document.getElementById('keywords');
		    	if(keywords.value == ''){
		    		alert("keywords不能为空");
		    		keywords.focus();
		    		return;
		    	}
		    	
		    	var description = document.getElementById('description');
		    	if(description.value == ''){
		    		alert("description不能为空");
		    		description.focus();
		    		return;
		    	}
		    	
		    	var generator = document.getElementById('generator');
		    	if(generator.value == ''){
		    		alert("generator不能为空");
		    		generator.focus();
		    		return;
		    	}
				
				document.frmApply.submit();
			}
		</script>
<meta name=generator content="MSHTML 8.00.6001.18939">
<body class="overfwidth">
<div id="mainDiv">
<div class="barnavtop">您所在的位置：SEO管理 > 编辑SEO</div>
	<!--主体 开始-->
	<form name="frmApply" class="cmxform" action="${ctx}/pub/seo/edit.do" target="hideframe" method="post">
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
                    <label class="left pt5"><em>*</em>title：</label>
                    <h1 class="cmxformh1"> <span class="cmxformspan">
                      <input type="text" id="title" class="wid350" name="title" value="${m.title}" />
                    </span></h1>
                  </li>
                  <li class="listyle_4">
                    <label class="left pt5"><em>*</em>keywords：</label>
                    <h1 class="cmxformh1"> <span class="cmxformspan">
                      <input type="text" id="keywords" class="wid350" name="keywords" value="${m.keywords}" />
                    </span></h1>
                  </li>
                  <li class="listyle_4">
                    <label class="left pt5"><em>*</em>description：</label>
                    <h1 class="cmxformh1"> <span class="cmxformspan">
                      <input type="text" id="description" class="wid350" name="description" value="${m.description}" />
                    </span></h1>
                  </li>
                  <li class="listyle_4">
                    <label class="left pt5"><em>*</em>generator：</label>
                    <h1 class="cmxformh1"> <span class="cmxformspan">
                      <input type="text" id="generator" class="wid350" name="generator" value="${m.generator}" />
                    </span></h1>
                  </li>
                </ol>
                <!--ol 结束-->               
              </fieldset>
           
          </div>
      	<div class="toolbar mb10">
        	<a href="#" class="sexybutton" onclick="beforeSubmit();return false"><span><span>保存</span></span></a>    
        	<a href="#" class="sexybutton" onClick="rbutton()"><span><span>重置 </span></span></a>
        	<a href="#" class="sexybutton" onclick="synRedis()"><span><span>同步到缓存</span></span></a>    
        </div>
		</div>
	</form>
	
	<form name="frmSeo" class="cmxform" action="${ctx}/pub/seo/synRedis.do" target="hideframe" method="post">
	</form>
	
</div>
</body>
</html>
<iframe name="hideframe" id="hideframe" width="0" height="0"></iframe>
