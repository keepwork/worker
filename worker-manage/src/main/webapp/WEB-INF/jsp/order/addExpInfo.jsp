<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib uri="/WEB-INF/tld/c.tld" prefix="c"%>
<c:set var="ctx" value="${pageContext.request.contextPath}" />
<%@ include file="../common/commonHeader.jsp"%>
<HTML>
<HEAD>
		<TITLE>后台管理系统</TITLE>
		<meta http-equiv="X-UA-Compatible" content="IE=EmulateIE7" />
		<meta name=generator content="MSHTML 8.00.6001.18939">
		<link href="${ctx}/sys/css/public.css" rel="stylesheet" type="text/css">
		<script type="text/javascript" src="${ctx}/common/js/jquery-1.4.4.min.js" ></script>
	    
		<script type="text/javascript">
		<!--
			function beforeSubmit()
			{
				parent.hide('cover1','expDiv');
				document.frmApply.submit();
			}
		//-->
		</script>
<meta name=generator content="MSHTML 8.00.6001.18939">
<body class="overfwidth">
<div id="mainDiv">
<div class="barnavtop">您所在的位置：订单管理 > 填写快递信息</div>
	<!--主体 开始-->
	<form name="frmApply" class="cmxform" action="${ctx}/pub/order/addExpInfo.do" target="hideframe" method="post">
	<input type="hidden" name="orderId" id="orderId" value="${order.orderId}" />
    <div id="container" style="width: 94% !important;">
    	<!--按钮 开始-->  

        <!--按钮 结束-->  
        <!--框内内容 开始-->
          <div class="editspace">
              <fieldset>
                <!--ol 开始-->
                <ol>
                  <li class="listyle_4">
                    <label class="left pt5" style="width: 103px;">订单号：</label>
                    <h1 class="cmxformh1"> <span class="cmxformspan">${order.orderSn}</span></h1>
                  </li>
                  <li class="listyle_4">
                    <label class="left pt5" style="width: 103px;">快递名称：</label>
                    <h1 class="cmxformh1"> <span class="cmxformspan">
                      <input type="text" id="expName" class="bgw" name="expName" value="${order.expName}" style="width:350px;"/>
                    </span></h1>
                  </li>
                  <li class="listyle_4">
                    <label class="left pt5" style="width: 103px;">快递单号：</label>
                    <h1 class="cmxformh1"> <span class="cmxformspan">
                      <input type="text" id="expNumber" class="bgw" name="expNumber" value="${order.expNumber}" style="width:350px;"/>
                    </span></h1>
                  </li>
                  <li class="listyle_4">
                    <label class="left pt5" style="width: 103px;">快递发货时间：</label>
                    <h1 class="cmxformh1"> <span class="cmxformspan">${expTimeStr}</span></h1>
                  </li>
                </ol>
                <!--ol 结束-->               
              </fieldset>
           
          </div>
      	<div class="toolbar mb10">
        	<a href="#" class="sexybutton" onclick="beforeSubmit();return false"><span><span>保存</span></span></a>    
        </div>
		</div>
	</form>
</div>

<iframe name="hideframe" id="hideframe" width="0" height="0"></iframe>

</body>
</html>