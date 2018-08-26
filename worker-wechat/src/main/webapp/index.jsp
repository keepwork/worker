<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <title>正在登入... ...</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<!-- <meta http-equiv="Refresh" content="0;url=${pageContext.request.contextPath}/bms/adm/main.do">     -->
  </head>
  
  <body>
	正在登入... ...
  </body>
  <script type="text/javascript">
   window.onload = function(){
	    if(isWeiXin()){
	        location.href="${pageContext.request.contextPath}/weixin/index.do?type=wap";
	    }else{
	    	location.href="${pageContext.request.contextPath}/pc/index.do";
	    }
   }
   function isWeiXin(){
	    var ua = window.navigator.userAgent.toLowerCase();
	    if(ua.match(/MicroMessenger/i) == 'micromessenger'){
	        return true;
	    }else{
	        return false;
	    }
	}
  </script>
</html>
