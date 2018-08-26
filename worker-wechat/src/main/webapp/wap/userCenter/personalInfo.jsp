<%@ page language="java" pageEncoding="UTF-8" isELIgnored="false"%>
<%@ taglib prefix="c" uri="/WEB-INF/tld/c.tld" %>
<c:set var="ctx" value="${pageContext.request.contextPath}" />

<!DOCTYPE html>
<html>
<head>
  <jsp:include page="../public/common.jsp"></jsp:include>
  <!-- 公用JS|-->
  <link rel="stylesheet" href="${ctx}/wap/userCenter/css/style.css">
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
      <ul style="margin-top: 0rem">
        <li><a href="javascript:void(0);"><span>我的姓名</span><em>${m.realName}</em></a></li>
        <li><a href="javascript:void(0);"><span>我的手机</span><em>${m.mobile}</em></a></li>
        <%--<li><a href="javascript:void(0);"><span>工作年限</span><em>${m.workYears}年</em></a></li>--%>
        <%--<li><a href="javascript:void(0);"><span>工作种类</span><em>--%>
          <%--<c:if test="${m.workType == '1'}">水工</c:if>--%>
          <%--<c:if test="${m.workType == '2'}">电工</c:if>--%>
          <%--<c:if test="${m.workType == '3'}">瓦工</c:if>--%>
          <%--<c:if test="${m.workType == '4'}">木工</c:if>--%>
          <%--<c:if test="${m.workType == '5'}">油漆工</c:if>--%>
          <%--<c:if test="${m.workType == '6'}">综合维修工</c:if>--%>
        <%--</em></a></li>--%>
        <%--<li><a href="javascript:void(0);"><span>服务地区</span><em>${m.locationName}</em></a></li>--%>
        <%--<li style="height:4.5rem;"><a href="javascript:void(0);" ><span>擅长技能</span>--%>
          <%--<em style="height: 2rem;text-align: left;display: block;overflow: visible;">${m.detailAddr}</em></a></li>--%>
      </ul>
    </div>

  </div>
<!--container-end-->

  <!-- 公用底部 -->
  <jsp:include page="../public/foot.jsp" flush="false">
    <jsp:param name="menu" value="fw" />
  </jsp:include>
</body>
</html>
