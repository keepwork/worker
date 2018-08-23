package com.weixin.servlet;

import java.io.BufferedOutputStream;
import java.io.IOException;
import java.io.StringReader;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.jdom.Document;
import org.jdom.Element;
import org.jdom.input.SAXBuilder;
import org.xml.sax.InputSource;

import com.pub.menber.model.dto.MenberDTO;
//import com.pub.menber.model.dto.MenberPointDTO;
//import com.pub.menber.model.dto.MenberShareDTO;
//import com.pub.menber.model.facade.MenberChargeFacade;
//import com.pub.menber.model.facade.MenberShareFacade;
import com.pub.menber.model.facade.MenberFacade;
import com.shop.order.model.dto.OrderDTO;
import com.shop.order.model.facade.OrderFacade;
import com.sinovatech.framework.common.helper.SpringBeanHelper;

/**
 * 微信支付回调接口 - 修改支付状态
 * 测试地址：
 * 		http://www.daguoz.com/wxback.do
 * 
 * @author chenxin   keepwork512@163.com
 * @date 2015-06-16
 */
public class WXBackServlet extends HttpServlet {

	private SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	
	private OrderFacade myOrderFacade;
	
	private MenberFacade myMenberFacade;
	
//	private MenberChargeFacade myMenberChargeFacade;
//
//	private MenberShareFacade myMenberShareFacade;
	
	/**
	 * Constructor of the object.
	 */
	public WXBackServlet() {
		super();
	}

	/**
	 * Destruction of the servlet. <br>
	 */
	public void destroy() {
		super.destroy(); // Just puts "destroy" string in log
		// Put your code here
	}

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

		this.doPost(request, response);
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
		
//		System.out.println("微信支付回调....................................................开始");
		myOrderFacade = (OrderFacade) SpringBeanHelper.getBeanContext().getBean("myOrderFacade");
		myMenberFacade = (MenberFacade) SpringBeanHelper.getBeanContext().getBean("myMenberFacade");
//		myMenberChargeFacade = (MenberChargeFacade) SpringBeanHelper.getBeanContext().getBean("myMenberChargeFacade");
//		myMenberShareFacade = (MenberShareFacade) SpringBeanHelper.getBeanContext().getBean("myMenberShareFacade");


		String inputLine;
		String notityXml = "";
		String resXml = "";
		try {
			while ((inputLine = request.getReader().readLine()) != null) {
				notityXml += inputLine;
			}
			request.getReader().close();
		} catch (Exception e) {
			e.printStackTrace();
		}
//		System.out.println("接收到的报文：" + notityXml);
		
		Map m = parseXmlToList2(notityXml);
		/*System.out.println("appid:"+m.get("appid").toString());
		System.out.println("bank_type:"+m.get("bank_type").toString());
		System.out.println("cash_fee:"+m.get("cash_fee").toString());
		System.out.println("fee_type:"+m.get("fee_type").toString());
		System.out.println("is_subscribe:"+m.get("is_subscribe").toString());
		System.out.println("mch_id:"+m.get("mch_id").toString());
		System.out.println("nonce_str:"+m.get("nonce_str").toString());
		System.out.println("openid:"+m.get("openid").toString());
		System.out.println("out_trade_no:"+m.get("out_trade_no").toString());
		System.out.println("result_code:"+m.get("result_code").toString());
		System.out.println("return_code:"+m.get("return_code").toString());
		System.out.println("sign:"+m.get("sign").toString());
		System.out.println("time_end:"+m.get("time_end").toString());
		System.out.println("total_fee:"+m.get("total_fee").toString());
		System.out.println("trade_type:"+m.get("trade_type").toString());
		System.out.println("transaction_id:"+m.get("transaction_id").toString());*/
		System.out.println("total_fee:"+m.get("total_fee").toString());
		
		//修改订单支付状态
		if(null != m.get("result_code")  && "SUCCESS".equals(m.get("result_code").toString())){
			String outTradeNo = m.get("out_trade_no").toString();
			outTradeNo = outTradeNo.substring(0,outTradeNo.length()-1);//去除最后一位支付信息
			String totalFee = m.get("total_fee").toString();
			String attach = m.get("attach").toString();//存放的会员id
			
	        OrderDTO order = myOrderFacade.getOrderBySN(outTradeNo);
	        if(null!=order){
//	        	order.setPayStatus("1");//已支付
//		        order.setShippingStatus("1");//已配送
				if("0".equals(attach) || "1".equals(attach)){
					order.setPayTime1(new Date());
				}else if("2".equals(attach)){
					order.setPayTime2(new Date());
				}else if("3".equals(attach)){
					order.setPayTime3(new Date());
				}
		        myOrderFacade.update(order);
		        System.out.println("微信订单支付回调完成....................................................(" +
	        			"menberId="+order.getMenId()+",orderSn="+outTradeNo+","+format.format(new Date())+")");
	        }
	        
//	        MenberDTO menberInfo = myMenberFacade.get(attach);
//	        if(null!=menberInfo){
//	        	//防止多次返回
//	        	boolean isBack=true;
////	        	boolean isBack = myMenberChargeFacade.checkMenberCharge(menberInfo.getId(),outTradeNo);
//	        	if(isBack){
//		        	double totalFee2 = new BigDecimal(totalFee).doubleValue()/100;
//		        	double fee = menberInfo.getBalanceFee().doubleValue() + totalFee2;
//		        	menberInfo.setBalanceFee(new BigDecimal(fee));
//
//		        	//如果该会员还未加入计划，充完值以后账户余额大于12元，且已填写好个人资料，则自动加入计划
//		        	if(menberInfo.getIsjoin()==0 && fee>=12 && null!=menberInfo.getPid() && !menberInfo.getPid().equals(""))
//		        	{
//		        		menberInfo.setIsjoin(1);
//		        		menberInfo.setJoinTime(new Date());
//		        		double rf = menberInfo.getRightFee().doubleValue() + 300000;
//		        		menberInfo.setRightFee(new BigDecimal(rf));//加入计划后，保额增加30万
//
//		        		//如果该会员有推荐人，则送（200积分、200保额）给这个推荐人
//		        		if(null != menberInfo.getSalesMenId() && !menberInfo.getSalesMenId().equals("") && menberInfo.getSign()!=1){
//		        			MenberDTO saleMenber = myMenberFacade.get(menberInfo.getSalesMenId());
//							if(null!=saleMenber){
//								double rightFee = saleMenber.getRightFee().doubleValue() + 200;
//								int point = saleMenber.getPoint() + 200;
//								saleMenber.setRightFee(new BigDecimal(rightFee));//每邀请一个好友加入，赠送保额200元
//								saleMenber.setPoint(point);//每邀请一个好友加入，赠送积分200元
//								myMenberFacade.update(saleMenber);
//
//								//保存积分历史
////								MenberPointDTO p = new MenberPointDTO();
////								p.setMenId(menberInfo.getSalesMenId());
////								p.setPoint(200);
////								p.setPointDesc("分享送积分");
////								p.setCreateTime(new Date());
////								myMenberFacade.saveMenberPoint(p);
////
////								//保存我的分享
////								MenberShareDTO s = new MenberShareDTO();
////								s.setMenId(menberInfo.getSalesMenId());
////								s.setShareMenId(menberInfo.getId());
////								s.setCreateTime(new Date());
////								myMenberShareFacade.save(s);
//
//								menberInfo.setSign(1);
//							}
//		        		}
//		        	}
//
////		        	myMenberFacade.saveRecharge(menberInfo,new BigDecimal(totalFee2),outTradeNo);
//		        	System.out.println("微信充值支付回调完成....................................................(" +
//	        				"menberId="+attach+",squeues="+outTradeNo+","+format.format(new Date())+")");
//	        	}
//	        }
	        
	        System.out.println("微信支付回调....................................................支付成功,outTradeNo:"+outTradeNo);
		}else{
			System.out.println("微信支付回调....................................................支付失败,(result_code="+m.get("result_code")+")");
		}

		/*<xml>
			<appid><![CDATA[wx795a6bd223954b76]]></appid>
			<attach><![CDATA[123]]></attach>
			<bank_type><![CDATA[CFT]]></bank_type>
			<cash_fee><![CDATA[1]]></cash_fee>
			<fee_type><![CDATA[CNY]]></fee_type>
			<is_subscribe><![CDATA[Y]]></is_subscribe>
			<mch_id><![CDATA[1249374801]]></mch_id>
			<nonce_str><![CDATA[2124228354]]></nonce_str>
			<openid><![CDATA[oZMTCtxr_E390WG6FsTDL0oekwx8]]></openid>
			<out_trade_no><![CDATA[12320150628202214]]></out_trade_no>
			<result_code><![CDATA[SUCCESS]]></result_code>
			<return_code><![CDATA[SUCCESS]]></return_code>
			<sign><![CDATA[6B5FE87BB8CA0F8F0F0D408C650DCDFE]]></sign>
			<time_end><![CDATA[20150628212445]]></time_end>
			<total_fee>1</total_fee>
			<trade_type><![CDATA[JSAPI]]></trade_type>
			<transaction_id><![CDATA[1006240159201506280318731146]]></transaction_id>
		</xml>*/
		
		if("SUCCESS".equals(m.get("result_code").toString())){
			//支付成功
			resXml = "<xml>" + "<return_code><![CDATA[SUCCESS]]></return_code>"
			+ "<return_msg><![CDATA[OK]]></return_msg>" + "</xml> ";
		}else{
			resXml = "<xml>" + "<return_code><![CDATA[FAIL]]></return_code>"
			+ "<return_msg><![CDATA[报文为空]]></return_msg>" + "</xml> ";
		}

		System.out.println("微信支付回调数据结束");

		BufferedOutputStream out = new BufferedOutputStream(
				response.getOutputStream());
		out.write(resXml.getBytes());
		out.flush();
		out.close();
		
	}
	
	/**
	 * description: 解析微信通知xml
	 * 
	 * @param xml
	 * @return
	 * @author ex_yangxiaoyi
	 * @see
	 */
	@SuppressWarnings({ "unused", "rawtypes", "unchecked" })
	private static Map parseXmlToList2(String xml) {
		Map retMap = new HashMap();
		try {
			StringReader read = new StringReader(xml);
			// 创建新的输入源SAX 解析器将使用 InputSource 对象来确定如何读取 XML 输入
			InputSource source = new InputSource(read);
			// 创建一个新的SAXBuilder
			SAXBuilder sb = new SAXBuilder();
			// 通过输入源构造一个Document
			Document doc = (Document) sb.build(source);
			Element root = doc.getRootElement();// 指向根节点
			List<Element> es = root.getChildren();
			if (es != null && es.size() != 0) {
				for (Element element : es) {
					retMap.put(element.getName(), element.getValue());
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return retMap;
	}

	/**
	 * Initialization of the servlet. <br>
	 *
	 * @throws ServletException if an error occurs
	 */
	public void init() throws ServletException {
		// Put your code here
	}

}
