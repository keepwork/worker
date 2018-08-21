<%@ page language="java" pageEncoding="UTF-8" isELIgnored="false"%>
<%@ taglib prefix="c" uri="/WEB-INF/tld/c.tld" %>
<c:set var="ctx" value="${pageContext.request.contextPath}" />
<%@ page language="java" import="com.weixin.util.SignUtil" %>
<%@ page language="java" import="com.sinovatech.common.config.GlobalConfig" %>
<%@ page language="java" import="java.util.Map" %>

<%
  String appId = GlobalConfig.getProperty("weixin", "appid");
  String jsapi_ticket = (String)request.getAttribute("jsapi_ticket")+"";
  String code = (String)request.getParameter("code")+"";//网页自带的参数
  String url = "http://kg.hncnbot.com/kguser/beforeAddBaby.do?type=wap";
  Map<String, String> map = SignUtil.sign(jsapi_ticket, url);
  String signature = map.get("signature").toString();
  int timestamp = Integer.parseInt(map.get("timestamp").toString());
  String nonceStr = map.get("nonceStr");

  System.out.println("");
  System.out.println("url:"+url);
  System.out.println("appId:"+appId);
  System.out.println("jsapi_ticket:"+jsapi_ticket);
  System.out.println("signature:"+signature);
  System.out.println("timestamp:"+timestamp);
  System.out.println("nonceStr:"+nonceStr);
  System.out.println("");
%>

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
<%--<script type="text/javascript" src="${ctx}/wap/common/js/jweixin-1.2.0.js"></script>--%>
<script type="text/javascript" src="${ctx}/common/js/ajaxfileupload.js"></script>
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

<body style="overflow:scroll;overflow-x:hidden">
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
    <li><p>交易凭证</p></li>
    <li ><h2>协议书</h2></li>
    <li ><img src="${ctx}/wap/images/tptj.png" style="height: 3rem;width: 3rem;" id="protocol">
        <img src="${ctx}/${requestScope.order.protocolImgPath}" style="height: 3rem;width: 3rem;" id="protocol_img" onerror="this.style.display='none'" >
    </li>
    <li ><h2>报价单</h2></li>
    <li ><img src="${ctx}/wap/images/tptj.png" style="height: 3rem;width: 3rem;" id="quote">
        <img src="${ctx}/${requestScope.order.quoteImgPath}" style="height: 3rem;width: 3rem;" id="quote_img" onerror="this.style.display='none'">
    </li>
    <li ><h2>服务表</h2></li>
    <li ><img src="${ctx}/wap/images/tptj.png" style="height: 3rem;width: 3rem;" id="service">
        <img src="${ctx}/${requestScope.order.serviceImgPath}" style="height: 3rem;width: 3rem;" id="service_img" onerror="this.style.display='none'">
    </li>
  </ul>

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

  <div style="position: fixed;bottom: 0;width: 100%;">
    <button style="width: 50%;height: 2rem;float:left;background: #5bc0de;color: #fff;" id="uploadPhoto">保存</button>
    <button style="width: 50%;height: 2rem;float:right;background: #819bd8;color: #fff;">重选</button>
  </div>
</div>
<!--order-confirm-end-->

</div>
<!--container-end-->


<script type="text/javascript">
    var mediaId = '';
    var uploadType = '';
    var orderId = '${requestScope.order.id}'
    function test() {
        alert("999");
        $("#protocol").next().attr("src","${ctx}/wap/images/tptj.png").show();
    }

    /**
     *选择照片事件绑定
     *
     * @param id
     */
    function selectImgEventBing(id) {
        var takePhoto = document.getElementById(id);
        takePhoto.addEventListener('click', function() {
            wx.chooseImage({
                count: 1, // 张数,默认9
                sizeType: ['original', 'compressed'], // 可以指定是原图还是压缩图，默认二者都有
                sourceType: ['album', 'camera'], // 可以指定来源是相册还是相机，默认二者都有
                success: function (res) {
                    //var localIds = res.localIds; // 返回选定照片的本地ID列表，localId可以作为img标签的src属性显示图片
                    var localId = res.localIds[0];
                    faceObj.src = localId;

                    wx.uploadImage({
                        localId: localId, // 需要上传的图片的本地ID，由chooseImage接口获得
                        isShowProgressTips: 1, // 默认为1，显示进度提示
                        success: function (res) {
                            mediaId = res.serverId; // 返回图片的服务器端ID
                            //$("#"+id+"_img").val(mediaId);
                            $("#"+id+"_img").attr("src",mediaId).show();
                            uploadType = id;
                        },
                        fail: function (error) {

                        }
                    });
                }
            });
        });
    }

    window.onload = function() {
        //alert(window.location.href.split('#')[0]);
        wx.config({
            debug: false, // 开启调试模式,调用的所有api的返回值会在客户端alert出来，若要查看传入的参数，可以在pc端打开，参数信息会通过log打出，仅在pc端时才会打印。
            appId: '<%=appId%>', // 必填，公众号的唯一标识
            timestamp: <%=timestamp%>, // 必填，生成签名的时间戳
            nonceStr: '<%=nonceStr%>', // 必填，生成签名的随机串
            signature: '<%=signature%>',// 必填，签名，见附录1
            jsApiList: [
                'chooseImage',
                'uploadImage',
                'getLocalImgData',
                'downloadImage'
            ],
            success:function(res){
                alert("配置成功");
                alert(JSON.stringify(res));
            },
            fail:function(){
                alert("配置失败");
            }
        });

//        var takePhoto = document.getElementById('takePhoto');
        var uploadPhoto = document.getElementById('uploadPhoto');
        var faceObj = document.getElementById('faceImg');

        wx.ready(function(){
            selectImgEventBing("protocol");
            selectImgEventBing("quote");
            selectImgEventBing("service");

            uploadPhoto.addEventListener('click', function() {
              //alert(mediaId);
              if('' != mediaId){
                var url = "${ctx}/order/uploadImg.do?mediaId="+mediaId+"&uploadType="+uploadType+"&orderId="+orderId;
                $.ajax({
                  type : "get",
                  url : url,
                  dataType : "text",
                  success : function(data) {
                    //$("#headImgPath").val("/common/upload/face/"+data);
                    alert("保存成功");
                }
                });
              }else{
                alert("请先选择照片");
              }
            });
        });
    }


</script>
<iframe name="hideframe" id="hideframe" width="0" height="0"></iframe>
</body>
</html>
