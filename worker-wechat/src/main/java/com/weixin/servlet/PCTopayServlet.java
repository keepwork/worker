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

import com.pub.menber.model.dto.MenberDTO;
import com.shop.order.model.dto.OrderDTO;
import com.shop.order.model.facade.OrderFacade;
import com.sinovatech.common.config.GlobalConfig;
import com.sinovatech.framework.common.helper.SpringBeanHelper;
import com.weixin.util.GetWxOrderno;
import com.weixin.util.QRCodeUtil;
import com.weixin.util.RequestHandler;
import com.weixin.util.Sha1Util;


/**
 * PC端扫码支付
 * 
 * @author chenxin   keepwork512@163.com
 * @date 2015-06-28
 */
public class PCTopayServlet extends HttpServlet {

	private SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	
	private OrderFacade myOrderFacade;
    
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
//		System.out.println("PC扫码支付....................................................PCTopayServlet");
		myOrderFacade = (OrderFacade) SpringBeanHelper.getBeanContext().getBean("myOrderFacade");
		
		MenberDTO menber = (MenberDTO)request.getSession().getAttribute("pcmenber");
//		System.out.println("menId:"+menber.getId());
		
    	String orderId = request.getParameter("orderId");
//    	System.out.println("orderId:"+orderId);
    	OrderDTO order = myOrderFacade.get(orderId);
    	String out_trade_no = order.getOrderSn(); 	//商户订单号
    	String attach = order.getMenId().toString();//附加数据，存放用户的menId
    	String body = "网页订单";//商品描述
		double totalPay = order.getTotalPrice().doubleValue()* 100;//总金额以分为单位，不带小数点
		String totalPayStr = totalPay + "";
		if((totalPayStr).contains(".")){
			totalPayStr = totalPayStr.substring(0,totalPayStr.indexOf("."));
		}
		
		double total_fee = order.getTotalPrice().doubleValue();
		String detail = "";//商品详情
    	String product_id = order.getOrderSn();//trade_type=NATIVE，此参数必传。此id为二维码中包含的商品ID，商户自行定义。
		String nonce_str = Sha1Util.getNonceStr();//随机数 
		String spbill_create_ip = request.getRemoteAddr();//订单生成的机器 IP
		
		//商户相关资料 
		String notify_url = GlobalConfig.getProperty("weixin", "server.backurl.pc");//支付完成后的回调，修改订单状态等
		String trade_type = GlobalConfig.getProperty("weixin", "tradeType.pc");//
		String appid = GlobalConfig.getProperty("weixin", "appid");//应用ID
		String mchid = GlobalConfig.getProperty("weixin", "mchid");//商户号
		String appsecret = GlobalConfig.getProperty("weixin", "appsecret");//应用密钥
		String apikey = GlobalConfig.getProperty("weixin", "apikey");//API key
//		System.out.println("notify_url:"+notify_url);
//		System.out.println("trade_type:"+trade_type);
//		System.out.println("appid:"+appid);
//		System.out.println("mchid:"+mchid);
//		System.out.println("appsecret:"+appsecret);
//		System.out.println("apikey:"+apikey);
		
		SortedMap<String, String> packageParams = new TreeMap<String, String>();
		packageParams.put("appid", appid);  
		packageParams.put("body", body);
		packageParams.put("mch_id", mchid);  
		packageParams.put("nonce_str", nonce_str);  
		packageParams.put("notify_url", notify_url);  
		packageParams.put("out_trade_no", out_trade_no);  
		packageParams.put("spbill_create_ip", spbill_create_ip);  
		packageParams.put("trade_type", trade_type);  
		packageParams.put("attach", attach);
		packageParams.put("total_fee", totalPayStr);
		//packageParams.put("device_info", device_info); 
		
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
//			"<openid>"+openId+"</openid>"+
			"<out_trade_no>"+out_trade_no+"</out_trade_no>"+
			"<attach>"+attach+"</attach>"+
			"<total_fee>"+totalPayStr+"</total_fee>"+
			"<spbill_create_ip>"+spbill_create_ip+"</spbill_create_ip>"+
			"<trade_type>"+trade_type+"</trade_type>"+
//			"<product_id>"+product_id+"</product_id>"+
			"<sign>"+sign+"</sign>"+
		"</xml>";
//		System.out.println("=====================xml");
//		System.out.println(xml);
//		System.out.println("=====================xml");
		
		//统一下单接口提交
		String createOrderURL = "https://api.mch.weixin.qq.com/pay/unifiedorder";
		String code_url = new GetWxOrderno().getPayNoPC(createOrderURL, xml);
		System.out.println("PC订单生成二维码....................................................code_url=" + code_url);
		
//		String prepay_id = new GetWxOrderno().getPayNoPC(createOrderURL, xml);
//		System.out.println("微信支付....................................................prepay_id:"+prepay_id);
		
//		SortedMap<String, String> finalpackage = new TreeMap<String, String>();
//		String appid2 = appid;
//		String timestamp = Sha1Util.getTimeStamp();
//		finalpackage.put("appId", appid2);  
//		finalpackage.put("timeStamp", timestamp);  
//		finalpackage.put("nonceStr", nonce_str);  
//		finalpackage.put("package", "prepay_id="+prepay_id);  
//		finalpackage.put("signType", "MD5");
//		String finalsign = reqHandler.createSign(finalpackage);
		
		//生成二维码图片 
		String rootPath = GlobalConfig.getProperty("weixin", "image.root");
		String codeImageName = order.getOrderSn();//UUID.getUUID();
		request.getSession().setAttribute("codeImageName", codeImageName);
		try {
			QRCodeUtil.encode(code_url,rootPath + "/common/imagesCodePay/"+codeImageName+".jpg");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		String serverDomain = GlobalConfig.getProperty("filePath", "prefix");
		if(null!=code_url){
			code_url = java.net.URLEncoder.encode(code_url,"UTF-8"); 
			//code_url = java.net.URLEncoder.encode(code_url,"UTF-8"); 
	    }
		
		System.out.println("PC订单生成二维码完成....................................................(" +
					"orderId="+orderId+",menId="+menber.getId()+",code_url:"+code_url+","+format.format(new Date())+")");
		
		
		//测试目录跳转
		//response.sendRedirect("http://"+serverDomain+"/wap/test/pay.jsp?orderSn="+out_trade_no+"&totalFee="+total_fee+"&appid="+appid2+"&timeStamp="+timestamp+"&nonceStr="+nonce_str+"&package="+packages+"&sign="+finalsign);
		//正式目录跳转
		response.sendRedirect(serverDomain+"/web/pay.jsp?orderSn="+order.getOrderSn());
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
