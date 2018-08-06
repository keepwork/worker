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
  <title>订单支付</title>
  <link rel="stylesheet" type="text/css" href="${ctx}/wap/workerCenter/css/base.css">
  <link rel="stylesheet" type="text/css" href="${ctx}/wap/workerCenter/css/public.css">
  <script src="${ctx }/wap/js/clipboard.min.js"></script>
  <script type="text/javascript" src="${ctx}/wap/workerCenter/js/jquery.min.js" ></script>
  <script src="${ctx}/wap/workerCenter/js/common.js"></script>

  <script>
    function showCopyDiv() {
        var obj = $("#zfbinfo");
        if(obj.is(":hidden")){
            obj.show();
        }else{
            obj.hide();
        }
    }
  </script>
  <style>
    .button-info {
      background-color: #5bc0de;
      border: none;
      color: white;
      text-align: center;
      text-decoration: none;
      display: inline-block;
      font-size: 16px;
      margin: 0rem 1rem;
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
<h1>订单支付</h1>
</header>
<!--header-end-->
<div class="container" id="container" style="padding-top: 40px;">
  <div class="order-payment">
  <div class="order-num" style="font-size: 0.8rem"><strong>${requestScope.payTitle}</strong></div>
  <div class="order-num">订单编号：<em>${requestScope.order.orderSn}</em></div>
  <div class="order-num">定金金额：<em style="color:red">￥${requestScope.payPrice}</em></div>
  <div class="payways">
    <div class="payways-tit">支付方式</div>
    <ul>
      <li>
        <a href="#">
          <div class="payways-img"><img src="${ctx}/wap/images/weixinzf.png"></div>
          <div class="payways-txt">
            <h2>微信支付</h2>
            <p>客户端支付最便捷</p>
          </div>
        </a>
      </li>
      <li>
        <a href="javascript:void(0);" onclick="showCopyDiv()">
          <div class="payways-img"><img src="${ctx}/wap/images/zhifubaozf.png"></div>
          <div class="payways-txt">
            <h2>支付宝支付</h2>
            <p>客户端支付最便捷</p>
          </div>
        </a>
        <div style="margin-top:0.4rem;padding:0 0.6rem;display: none" id="zfbinfo">
          <input id="foo" type="text" value="[${requestScope.payText}${requestScope.order.orderSn}]">
          <br>
          <a href="javascript:void(0);" data-clipboard-action="copy" data-clipboard-target="#foo" class="button-info btn" style="background-color:#5bc0de;padding: 0 0;">复制信息</a>


          <em style="color:red">*注:请点击复制按钮复制支付信息，并将其粘贴到支付宝支付页面备注框！</em>
          <%--<button class="btn" data-clipboard-action="copy" data-clipboard-target="#foo">Copy</button> <br><br>--%>
            <%--<input id="foo" type="text" value="hello">--%>
            <%--<button class="btn" data-clipboard-action="copy" data-clipboard-target="#foo">Copy</button>--%>
        </div>
      </li>
    </ul>
  </div>
</div>
</div>
<!--order-payment-end-->
<!--container-end-->

<script>
    var clipboard = new Clipboard('.btn');

    clipboard.on('success', function(e) {
        console.log(e);
        //alert("成功");
        $(".btn").text("复制成功");
    });

    clipboard.on('error', function(e) {
        console.log(e);
    });
</script>
</body>
</html>
