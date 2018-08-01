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
<title>订单详情</title>
<link rel="stylesheet" type="text/css" href="${ctx }/wap/css/processmain.css">
<link rel="stylesheet" type="text/css" href="${ctx }/wap/css/process.css">
<%--footer 开始--%>
<link rel="stylesheet" type="text/css" href="${ctx }/wap/css/footer.css">
<link rel="stylesheet" type="text/css" href="${ctx }/wap/workerCenter/css/public.css">
<script type="text/javascript" src="${ctx }/wap/workerCenter/js/jquery.min.js" ></script>
<script src="${ctx }/wap/js/rem.js"></script>
<%--footer 结束--%>
  <style>
    .pay-state {
      border: none;
      text-align: center;
      text-decoration: none;
      margin: 0.4rem 3.6rem;
      cursor: pointer;
      border-radius: 4px;
      float:right;
      line-height: 1.5rem;
    }
  </style>
</head>

<body>
<header class="header" id="header">
<a href="javascript:history.go(-1)" target=_self class="back">返回</a>
<h1>订单详情</h1>
</header>
<!--header-end-->
<div class="container" id="container"> 
<%--<div class="order-schedule-img"><img src="${ctx }/wap/workerCenter/images/1.png"></div>--%>
<div class="order-confirm">
<div class="order-num"><span class="fl">订单号：<em>${requestScope.order.orderSn}</em></span></div>
<ul class="order-confirm-list clearfix order-cancel-list">
  <li><P>订单内容</P></li>
  <li>
    <h2>服务类型</h2>
    <span>
      <c:if test="${requestScope.order.serviceType == '1'}">安装</c:if>
      <c:if test="${requestScope.order.serviceType == '2'}">维修</c:if>
      <c:if test="${requestScope.order.serviceType == '3'}">保养</c:if>
      <c:if test="${requestScope.order.serviceType == '4'}">测量</c:if>
      <c:if test="${requestScope.order.serviceType == '5'}">咨询</c:if>
    </span>
  </li>
  <li><h2>产品类型</h2><span>${requestScope.firstCateName}</span></li>
  <li><h2>接单时间</h2><span>${requestScope.order.takeTimeStr}</span></li>
  <c:if test="${null != requestScope.order.sureTimeStr && '' != requestScope.order.sureTimeStr}">
    <li><h2>确认时间</h2><span>${requestScope.order.sureTimeStr}</span></li>
  </c:if>
  <c:if test="${null != requestScope.order.endTimeStr && '' != requestScope.order.endTimeStr}">
    <li><h2>完成时间</h2><span>${requestScope.order.endTimeStr}</span></li>
  </c:if>
  <li><h2>客户姓名</h2><span>${requestScope.address.consignee}</span></li>
  <li><h2>联系电话</h2><span>${requestScope.address.mobile}</span></li>
  <li><h2>服务地址</h2><span>${requestScope.address.province}${requestScope.address.city}${requestScope.address.county}${requestScope.address.street}</span></li>
  <%--<li><h2>备注留言</h2><span>${requestScope.order.orderDesc}</span></li>--%>
  <%--<li><h2>客户评价</h2><span>${requestScope.order.desc2}</span></li>--%>
  <%--<li><p>金额<em>￥100.00</em></p></li>--%>
  <li style="border-bottom: 1px solid #e5e5e5;"><h2>备注信息</h2><span>${requestScope.order.orderDesc}</span></li>
  <li style="height: 2.4rem;line-height: 2.4rem;padding: 0 0.5rem; border-bottom: 1px solid #e5e5e5;">
    <h2>订单金额</h2>
    <p>
      <em>￥${requestScope.order.totalPrice}</em>
      <c:if test="${requestScope.order.payType eq '1'}">
        <c:if test="${requestScope.order.payTime1 eq '' || requestScope.order.payTime1 eq null}">
          <span class="pay-state" style="color:#fa4b28">未支付</span>
        </c:if>
        <c:if test="${requestScope.order.payTime1 ne '' && requestScope.order.payTime1 ne null}">
          <span class="pay-state" style="color:#2abf3e">已支付</span>
        </c:if>
      </c:if>
    </p>
  </li>
  <c:if test="${requestScope.order.payType eq '2'}">
    <li style="height: 2.4rem;line-height: 2.4rem;padding: 0 0.5rem; border-bottom: 1px solid #e5e5e5;">
      <h2>定金金额</h2>
      <p>
        <em>￥${requestScope.order.payPrice1}</em>
        <c:if test="${requestScope.order.payTime1 eq '' || requestScope.order.payTime1 eq null}">
          <span class="pay-state" style="color:#fa4b28">未支付</span>
        </c:if>
        <c:if test="${requestScope.order.payTime1 ne '' && requestScope.order.payTime1 ne null}">
          <span class="pay-state" style="color:#2abf3e">已支付</span>
        </c:if>
      </p>
    </li>
    <li style="height: 2.4rem;line-height: 2.4rem;padding: 0 0.5rem; border-bottom: 1px solid #e5e5e5;">
      <h2>中期金额</h2>
      <p>
        <em>￥${requestScope.order.payPrice2}</em>
        <c:if test="${requestScope.order.payTime2 eq '' || requestScope.order.payTime2 eq null}">
          <span class="pay-state" style="color:#fa4b28">未支付</span>
        </c:if>
        <c:if test="${requestScope.order.payTime2 ne '' && requestScope.order.payTime2 ne null}">
          <span class="pay-state" style="color:#2abf3e">已支付</span>
        </c:if>
      </p>
    </li>
    <li style="height: 2.4rem;line-height: 2.4rem;padding: 0 0.5rem; border-bottom: 1px solid #e5e5e5;">
      <h2>尾款金额</h2>
      <p>
        <em>￥${requestScope.order.payPrice3}</em>
        <c:if test="${requestScope.order.payTime3 eq '' || requestScope.order.payTime3 eq null}">
          <span class="pay-state" style="color:#fa4b28">未支付</span>
        </c:if>
        <c:if test="${requestScope.order.payTime3 ne '' && requestScope.order.payTime3 ne null}">
          <span class="pay-state" style="color:#2abf3e">已支付</span>
        </c:if>
      </p>
    </li>
  </c:if>
</ul>
  <c:if test="${requestScope.appraise != null}">
    <div style="margin-bottom: 0.3rem"></div>
    <ul class="order-confirm-list clearfix order-cancel-list">
      <li><p>订单评价</p></li>
      <li><h2>服务质量</h2><span>${requestScope.appraise.scoreZhiLiang}分</span></li>
      <li><h2>服务态度</h2><span>${requestScope.appraise.scoreTaiDu}分</span></li>
      <li><h2>准时情况</h2><span>${requestScope.appraise.scoreZhunShi}分</span></li>
      <li><h2>评价内容</h2><span>${requestScope.appraise.content}</span></li>
    </ul>
  </c:if>
  <div style="margin-bottom: 0.3rem"></div>
  <ul class="order-confirm-list clearfix order-cancel-list">
    <li><p>订单进度</p></li>
    <div>
      <section id="cd-timeline" class="cd-container">
        <div class="cd-timeline-block">
          <div class="cd-timeline-img cd-movie">
          </div>
          <div class="cd-timeline-content">
            <p>接单</p>
            <span class="cd-date">${requestScope.order.takeTimeStr}</span>
          </div>
        </div>

        <c:if test="${requestScope.order.actualTimeStr ne ''}">
          <div class="cd-timeline-block">
            <div class="cd-timeline-img cd-movie">
            </div>
            <div class="cd-timeline-content">
              <p>已上门</p>
              <span class="cd-date">${requestScope.order.actualTimeStr}</span>
            </div>
          </div>
        </c:if>
        <c:if test="${requestScope.order.actualTimeStr eq ''}">
          <div class="cd-timeline-block">
            <div class="cd-timeline-img cd-picture">
            </div>
            <div class="cd-timeline-content">
              <p>已上门</p>
              <span class="cd-date">&emsp;</span>
            </div>
          </div>
        </c:if>

        <c:if test="${requestScope.order.finishTimeStr ne ''}">
          <div class="cd-timeline-block">
            <div class="cd-timeline-img cd-movie">
            </div>
            <div class="cd-timeline-content">
              <p>已完工</p>
              <span class="cd-date">${requestScope.order.finishTimeStr}</span>
            </div>
          </div>
        </c:if>
        <c:if test="${requestScope.order.finishTimeStr eq ''}">
          <div class="cd-timeline-block">
            <div class="cd-timeline-img cd-picture">
            </div>
            <div class="cd-timeline-content">
              <p>已完工</p>
              <span class="cd-date">&emsp;</span>
            </div>
          </div>
        </c:if>

        <c:if test="${requestScope.order.payTimeStr ne ''}">
          <div class="cd-timeline-block">
            <div class="cd-timeline-img cd-movie">
            </div>
            <div class="cd-timeline-content">
              <p>已支付</p>
              <span class="cd-date">${requestScope.order.payTimeStr}</span>
            </div>
          </div>
        </c:if>
        <c:if test="${requestScope.order.payTimeStr eq ''}">
          <div class="cd-timeline-block">
            <div class="cd-timeline-img cd-picture">
            </div>
            <div class="cd-timeline-content">
              <p>已支付</p>
              <span class="cd-date">&emsp;</span>
            </div>
          </div>
        </c:if>

        <c:choose>
          <c:when test="${requestScope.order.orderStatus eq '6'}">
            <div class="cd-timeline-block">
              <div class="cd-timeline-img cd-movie">
              </div>
              <div class="cd-timeline-content">
                <p>已评价</p>
                <span class="cd-date">&emsp;</span>
              </div>
            </div>
          </c:when>
          <c:otherwise>
            <div class="cd-timeline-block">
              <div class="cd-timeline-img cd-picture">
              </div>
              <div class="cd-timeline-content">
                <p>已评价</p>
                <span class="cd-date">&emsp;</span>
              </div>
            </div>
          </c:otherwise>
        </c:choose>
      </section>
    </div>
  </ul>

</div>
<!--order-confirm-end-->

</div>
<!--container-end-->
</body>
</html>
