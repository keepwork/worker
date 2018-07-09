<%@ page contentType="text/html; charset=UTF-8"%>
<%@ page import="com.sinovatech.bms.adm.model.dto.TBmsUserDTO"%>
<%@ page import="com.sinovatech.common.config.GlobalConfig,java.net.URLEncoder"%>
<% 
		TBmsUserDTO tBmsUserDTO = (TBmsUserDTO)request.getSession().getAttribute("loginUser");
		String username="";
		if(tBmsUserDTO!=null)
		{
			username=tBmsUserDTO.getUserLoginName();
		}
		
		//"${pageContext.request.contextPath}/images1.6/logo.gif"
		
	    String contextUrl = request.getContextPath();
		String url = GlobalConfig.getProperty("bms", "logo_url");
		//String logo_url = contextUrl + url;
		String logo_url = contextUrl + "/images/logo.jpg";
		
		logo_url = URLEncoder.encode(logo_url, "GB2312");
		logo_url=logo_url.replaceAll("%2F", "/");
		//System.out.println(logo_url);
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

<link href="${pageContext.request.contextPath}/common/css/yh.css" rel="stylesheet" type="text/css">
<link href="${pageContext.request.contextPath}/sys/css/public.css" rel="stylesheet" type="text/css">
<link href="${pageContext.request.contextPath}/sys/css/style.css" rel="stylesheet" type="text/css">

<style type="text/css">
html {
	margin:0px;padding:0px;
	*overflow-y:hidden;
}
body {
	margin:0px;
	padding:0;
	background-color: #fff;
	overflow:hidden;height:100%;
}

</style>
</head>

<body>
        <div id="header">
              <table border="0" cellSpacing=0 cellPadding=0 width="100%">
              <tbody>
                    <tr>
                          <td width="300"><img  src=<%=logo_url %> width="299" height="63" alt="logo" title="logo"></td>
                          <td valign="top"  align="center"></td>
                          <td class="cmstd" width="120">
                          <img src="${pageContext.request.contextPath}/sys/images/user.png" width="16" height="16" alt="你好" title="你好"><%=username%>您好<br />
                          </td>
                    </tr>
              </tbody>
              </table>
          </div>	
</body>
</html>
