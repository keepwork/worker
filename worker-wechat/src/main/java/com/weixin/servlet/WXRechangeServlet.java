package com.weixin.servlet;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.SortedMap;
import java.util.TreeMap;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.sinovatech.common.config.GlobalConfig;
import com.sinovatech.common.util.UUID;
import com.weixin.util.GetWxOrderno;
import com.weixin.util.RequestHandler;
import com.weixin.util.Sha1Util;


/**
 * 微信支付充值
 * 
 * @author chenxin   keepwork512@163.com
 * @date 2015-06-28
 */
public class WXRechangeServlet extends HttpServlet {

	private SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	
	/**
	 * The doGet method of the servlet. <br>
	 *
	 * This method is called when a form has its tag value method equals to get.
	 * 
	 * @param request the request send by the client to the server
	 * @param response the response send by the server to the client
	 * @throws ServletException if an error occurred
	 * @throws IOException if an error occurred
	 */
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
//		System.out.println("微信支付充值....................................................toPayServlet");
		String price = (String)request.getParameter("price");//充值金额
		String rechargeMenId = (String)request.getParameter("rechargeMenId");//充值会员id
//		System.out.println("rechargeMenId:"+rechargeMenId);
		

		String openId = (String)request.getSession().getAttribute("openID");
//		String wxmenberId = (String)request.getSession().getAttribute("wxmenberId");
//    		System.out.println("openId:"+openId);
//    		System.out.println("wxmenberId:"+wxmenberId);
		
		String out_trade_no = UUID.getUUID();//商户订单号
    	String attach = rechargeMenId;//附加数据，存放用户的menId
    	
    	//描述
    	double total_fee = Double.parseDouble(price);
		String totalPayStr = total_fee * 100 + "";//总金额以分为单位，不带小数点
		if((totalPayStr).contains(".")){
			totalPayStr = totalPayStr.substring(0,totalPayStr.indexOf("."));
		}
		
		String nonce_str = Sha1Util.getNonceStr();//随机数 
		String spbill_create_ip = request.getRemoteAddr();//订单生成的机器 IP
		String body = "会员充值";
		
		//商户相关资料 
		String notify_url = GlobalConfig.getProperty("weixin", "server.backurl");//支付完成后的回调，修改订单状态等
		String trade_type = GlobalConfig.getProperty("weixin", "tradeType");//
		String appid = GlobalConfig.getProperty("weixin", "appid");//应用ID
		String mchid = GlobalConfig.getProperty("weixin", "mchid");//商户号
		String appsecret = GlobalConfig.getProperty("weixin", "appsecret");//应用密钥
		String apikey = GlobalConfig.getProperty("weixin", "apikey");//API key
//    		System.out.println("notify_url:"+notify_url);
//    		System.out.println("trade_type:"+trade_type);
//    		System.out.println("appid:"+appid);
//    		System.out.println("mchid:"+mchid);
//    		System.out.println("appsecret:"+appsecret);
//    		System.out.println("apikey:"+apikey);
//    		System.out.println("totalPayStr:"+totalPayStr);
		
		SortedMap<String, String> packageParams = new TreeMap<String, String>();
		packageParams.put("appid", appid);  
		packageParams.put("body", body);
		packageParams.put("mch_id", mchid);  
		packageParams.put("nonce_str", nonce_str);  
		packageParams.put("notify_url", notify_url);  
		packageParams.put("openid", openId); 
		packageParams.put("out_trade_no", out_trade_no);  
		packageParams.put("spbill_create_ip", spbill_create_ip);  
		packageParams.put("trade_type", trade_type);  
		packageParams.put("attach", attach);
		packageParams.put("total_fee", totalPayStr);
		
		RequestHandler reqHandler = new RequestHandler(request, response);
		reqHandler.init();
		reqHandler.init(appid, appsecret, apikey);
		String sign = reqHandler.createSign(packageParams);
		String xml="<xml>"+
			"<appid>"+appid+"</appid>"+
			"<body><![CDATA[" + body + "]]></body>"+
			"<mch_id>"+mchid+"</mch_id>"+
			"<nonce_str>"+nonce_str+"</nonce_str>"+
			"<notify_url>"+notify_url+"</notify_url>"+
			"<openid>"+openId+"</openid>"+
			"<out_trade_no>"+out_trade_no+"</out_trade_no>"+
			"<attach>"+attach+"</attach>"+
			"<total_fee>"+totalPayStr+"</total_fee>"+
			"<spbill_create_ip>"+spbill_create_ip+"</spbill_create_ip>"+
			"<trade_type>"+trade_type+"</trade_type>"+
			"<sign>"+sign+"</sign>"+
		"</xml>";
//    		System.out.println("=====================xml");
//    		System.out.println(xml);
//    		System.out.println("=====================xml");
		
		//统一下单接口提交
		String createOrderURL = "https://api.mch.weixin.qq.com/pay/unifiedorder";
		String prepay_id = new GetWxOrderno().getPayNo(createOrderURL, xml);
		
		SortedMap<String, String> finalpackage = new TreeMap<String, String>();
		String appid2 = appid;
		String timestamp = Sha1Util.getTimeStamp();
		finalpackage.put("appId", appid2);  
		finalpackage.put("timeStamp", timestamp);  
		finalpackage.put("nonceStr", nonce_str);  
		finalpackage.put("package", "prepay_id="+prepay_id);  
		finalpackage.put("signType", "MD5");
		String finalsign = reqHandler.createSign(finalpackage);
		
		String serverDomain = GlobalConfig.getProperty("filePath", "prefix");
		System.out.println("微信充值支付准备完成....................................................(" +
				"rechargeMenId="+rechargeMenId+",prepay_id:"+prepay_id+","+format.format(new Date())+")");
		
		//正式目录跳转
		response.sendRedirect(serverDomain+"/wap/payRechargeWap.jsp?totalFee="+total_fee+"&appid="+appid2+"&timeStamp="+timestamp+"&nonceStr="+nonce_str+"&package="+prepay_id+"&sign="+finalsign);
    
		
	}
	
	
	
	/**
	 * 元转换成分
	 * @param money
	 * @return
	 */
	public static String getMoney(String amount) {
		if(amount==null){
			return "";
		}
		// 金额转化为分为单位
		String currency =  amount.replaceAll("\\$|\\￥|\\,", "");  //处理包含, ￥ 或者$的金额  
        int index = currency.indexOf(".");  
        int length = currency.length();  
        Long amLong = 0l;  
        if(index == -1){  
            amLong = Long.valueOf(currency+"00");  
        }else if(length - index >= 3){  
            amLong = Long.valueOf((currency.substring(0, index+3)).replace(".", ""));  
        }else if(length - index == 2){  
            amLong = Long.valueOf((currency.substring(0, index+2)).replace(".", "")+0);  
        }else{  
            amLong = Long.valueOf((currency.substring(0, index+1)).replace(".", "")+"00");  
        }  
        return amLong.toString(); 
	}
	

	/**
	 * The doPost method of the servlet. <br>
	 *
	 * This method is called when a form has its tag value method equals to post.
	 * 
	 * @param request the request send by the client to the server
	 * @param response the response send by the server to the client
	 * @throws ServletException if an error occurred
	 * @throws IOException if an error occurred
	 */
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
