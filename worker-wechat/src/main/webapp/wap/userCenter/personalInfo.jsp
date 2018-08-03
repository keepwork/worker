<%@ page language="java" pageEncoding="UTF-8" isELIgnored="false"%>
<%@ taglib prefix="c" uri="/WEB-INF/tld/c.tld" %>
<c:set var="ctx" value="${pageContext.request.contextPath}" />

<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta content="width=device-width, initial-scale=1.0, minimum-scale=1.0, maximum-scale=1.0, user-scalable=no" name="viewport">
<meta content="yes" name="apple-mobile-web-app-capable" />
<meta content="black" name="apple-mobile-web-app-status-bar-style" />
<meta content="telephone=no" name="format-detection" />
<title>个人信息</title>
  <link rel="stylesheet" type="text/css" href="${ctx }/wap/css/footer.css">
  <link rel="stylesheet" type="text/css" href="${ctx }/wap/workerCenter/css/public.css">
</head>

<body>
  <header class="header" id="header">
  <a href="javascript:history.go(-1);" class="back">返回</a>
  <h1>个人资料</h1>
  </header>
  <!--header-end-->

  <div class="container" id="container">

    <div class="personal-data1">
      <ul>
        <li><a href="javascript:void(0);"><span>我的姓名</span><em>${m.realName}</em></a></li>
        <li><a href="javascript:void(0);"><span>我的手机</span><em>${m.mobile}</em></a></li>
        <li><a href="javascript:void(0);"><span>工作年限</span><em>${m.workYears}年</em></a></li>
        <li><a href="javascript:void(0);"><span>工作种类</span><em>
          <c:if test="${m.workType == '1'}">水工</c:if>
          <c:if test="${m.workType == '2'}">电工</c:if>
          <c:if test="${m.workType == '3'}">瓦工</c:if>
          <c:if test="${m.workType == '4'}">木工</c:if>
          <c:if test="${m.workType == '5'}">油漆工</c:if>
          <c:if test="${m.workType == '6'}">综合维修工</c:if>
        </em></a></li>
        <li><a href="javascript:void(0);"><span>服务地区</span><em>${m.locationName}</em></a></li>
        <li style="height:4.5rem;"><a href="javascript:void(0);" ><span>擅长技能</span>
          <em style="height: 2rem;text-align: left;display: block;overflow: visible;">${m.detailAddr}</em></a></li>
      </ul>
    </div>

  </div>
<!--container-end-->
</body>
</html>
