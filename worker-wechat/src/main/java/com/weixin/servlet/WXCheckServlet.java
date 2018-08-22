package com.weixin.servlet;

import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.IOUtils;
import org.apache.commons.lang.StringUtils;

import com.pub.menber.model.dto.MenberDTO;
import com.pub.menber.model.facade.MenberFacade;
import com.qq.weixin.mp.aes.AesException;
import com.qq.weixin.mp.aes.WXBizMsgCrypt;
import com.sinovatech.common.config.GlobalConfig;
import com.sinovatech.framework.common.helper.SpringBeanHelper;
import com.weixin.util.MessageUtil;

/**
 * 微信验证成为开发者 - 测试类
 * 测试地址：
 * 		http://www.daguoz.com/wxcheck.do?token=dgz2015&key=vru8K1WgZUr8jC4MwoiPoNWAPQ1gwyxM9wQvePKRJsI&appId=wx87e23c6cb09631da
 * 
 * @author chenxin   keepwork512@163.com
 * @date 201506-16
 */
public class WXCheckServlet extends HttpServlet {

	private static SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	private static MenberFacade myMenberFacade;
	public MenberFacade getMyMenberFacade() {
		return myMenberFacade;
	}

	public void setMyMenberFacade(MenberFacade myMenberFacade) {
		this.myMenberFacade = myMenberFacade;
	}

	/**
	 * Constructor of the object.
	 */
	public WXCheckServlet() {
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

		System.out.println("=============WXCheckServlet doGet");
//		response.setContentType("text/html");
		request.setCharacterEncoding("UTF-8");
	  	response.setCharacterEncoding("UTF-8");
		
		
		/*
    	 * 服务器地址（URL）
    	 * 		描述：开发者用来接收微信消息和事件的接口URL
    	 * Token
    	 * 		描述：可由开发者可以任意填写，用作生成签名（该Token会和接口URL中包含的Token进行比对，从而验证安全性）
    	 * EncodingAESKey
    	 * 		描述：由开发者手动填写或随机生成，将用作消息体加解密密钥。 
    	 * 
    	 * 在安全模式或兼容模式下，url上会新增两个参数encrypt_type和msg_signature
    	 * encrypt_type
    	 * 		表示加密类型,
    	 * 		url上无encrypt_type参数或者其值为raw时表示为不加密；
    	 * 		encrypt_type为aes时，表示aes加密（暂时只有raw和aes两种值)。
    	 * 		公众帐号开发者根据此参数来判断微信公众平台发送的消息是否加密。 
    	 * msg_signature：表示对消息体的签名
    	 */
		
		// 微信加密签名，signature结合了开发者填写的token参数和请求中的timestamp参数、nonce参数
		String msg_signature = request.getParameter("msg_signature");
		// 时间戳
		String timestamp = request.getParameter("timestamp");
		// 随机数
		String nonce = request.getParameter("nonce");
		// 随机字符串
		String echostr = request.getParameter("echostr");
//		System.out.println("WXCheckServlet===================== msg_signature:"+msg_signature);
//		System.out.println("WXCheckServlet===================== timestamp:"+timestamp);
//		System.out.println("WXCheckServlet===================== nonce:"+nonce);
//		System.out.println("WXCheckServlet===================== echostr:"+echostr);

		String token = request.getParameter("token");
		String encodingAESKey = request.getParameter("key");
		String appId = request.getParameter("appId");
//		System.out.println("WXCheckServlet===================== token1:"+token);
//		System.out.println("WXCheckServlet===================== encodingAESKey1:"+encodingAESKey);
//		System.out.println("WXCheckServlet===================== appId1:"+appId);
		if(null == token || token.equals("")){
			token = GlobalConfig.getProperty("weixin", "token");
		}
		if(null == encodingAESKey || encodingAESKey.equals("")){
			encodingAESKey = GlobalConfig.getProperty("weixin", "encodingAESKey");
		}
		if(null == appId || appId.equals("")){
			appId = GlobalConfig.getProperty("weixin", "appid");
		}
//		System.out.println("WXCheckServlet===================== token2:"+token);
//		System.out.println("WXCheckServlet===================== encodingAESKey2:"+encodingAESKey);
//		System.out.println("WXCheckServlet===================== appId2:"+appId);

		// 通过检验signature对请求进行校验，若校验成功则原样返回echostr，表示接入成功，否则接入失败
		String result = null;
		try {
			WXBizMsgCrypt wxcpt = new WXBizMsgCrypt(token, encodingAESKey,
					appId);
//			result = wxcpt.VerifyURL(msg_signature, timestamp, nonce, echostr);
//			result = wxcpt.DecryptMsg(msg_signature, timestamp, nonce, echostr);
//			result = wxcpt.decrypt(echostr);
			result = echostr;
		} catch (AesException e) {
			e.printStackTrace();
		}
//		System.out.println("WXCheckServlet===================== result1:"+result);
		if (result == null) {
			result = token;
		}
//		System.out.println("WXCheckServlet===================== result2:"+result);
		
		PrintWriter out = response.getWriter();
		out.write(result);
		out.close();
		out = null;
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
		
			System.out.println("=============WXCheckServlet doPost");
			// 将请求、响应的编码均设置为UTF-8（防止中文乱码）
		  	request.setCharacterEncoding("UTF-8");
		  	response.setCharacterEncoding("UTF-8");

		  	// 微信加密签名
			String msg_signature = request.getParameter("msg_signature");
			// 时间戳
			String timestamp = request.getParameter("timestamp");
			// 随机数
			String nonce = request.getParameter("nonce");
//			System.out.println("WXCheckServlet===================== msg_signature:"+msg_signature);
//			System.out.println("WXCheckServlet===================== timestamp:"+timestamp);
//			System.out.println("WXCheckServlet===================== nonce:"+nonce);
			
			String token = request.getParameter("token");
			String encodingAESKey = request.getParameter("key");
			String appId = request.getParameter("appId");
			if(null == token || token.equals("")){
				token = GlobalConfig.getProperty("weixin", "token");
			}
			if(null == encodingAESKey || encodingAESKey.equals("")){
				encodingAESKey = GlobalConfig.getProperty("weixin", "encodingAESKey");
			}
			if(null == appId || appId.equals("")){
				appId = GlobalConfig.getProperty("weixin", "appId");
			}
//			System.out.println("WXCheckServlet===================== token2:"+token);
//			System.out.println("WXCheckServlet===================== encodingAESKey2:"+encodingAESKey);
//			System.out.println("WXCheckServlet===================== appId2:"+appId);
			
			//从请求中读取整个post数据
			InputStream inputStream = request.getInputStream();
			String postData = IOUtils.toString(inputStream, "UTF-8");
//			System.out.println("=============WXCheckServlet  postData:"+postData);
			
			String msg = "";
			WXBizMsgCrypt wxcpt = null;
			try {
				wxcpt = new WXBizMsgCrypt(token, encodingAESKey, appId);
				//解密消息
				msg = wxcpt.DecryptMsg(msg_signature, timestamp, nonce, postData);
			} catch (AesException e) {
				e.printStackTrace();
			}
//			System.out.println("------------msg------------------------------------");
//			System.out.println(msg);
//			System.out.println("------------msg------------------------------------");
			
			// 调用核心业务类接收消息、处理消息
			String respMessage = this.processRequest(msg,request);
//			System.out.println("------------respMessage------------------------------------");
//			System.out.println(respMessage);
//			System.out.println("------------respMessage------------------------------------");
			
			String encryptMsg = "";
			try {
				//加密回复消息
				encryptMsg = wxcpt.EncryptMsg(respMessage, timestamp, nonce);
			} catch (AesException e) {
				e.printStackTrace();
			}
//			System.out.println("------------encryptMsg------------------------------------");
//			System.out.println(encryptMsg);
//			System.out.println("------------encryptMsg------------------------------------");
			
			// 响应消息
			PrintWriter out = response.getWriter();
			out.write(encryptMsg);
//			out.print(respMessage);
			out.close();
	}
	


	public static String processRequest(String msg,HttpServletRequest request) {
//		MenberFacade myMenberFacade = (MenberFacade) SpringBeanHelper.getBeanContext().getBean("myMenberFacade");
		myMenberFacade = (MenberFacade) SpringBeanHelper.getBeanContext().getBean("myMenberFacade");
		//回复消息
		StringBuffer returnStr = new StringBuffer();  
		//消息内容
		String content = "";
		String respMessage = null;
		try {
			// 默认返回的文本消息内容
			String respContent = "请求处理异常，请稍候尝试！";

			// xml请求解析
			Map<String, String> requestMap = MessageUtil.parseXml(msg);
			
			// 事件类型（subscribe(关注)、unsubscribe(取消关注) ）
			String eventType = requestMap.get("Event");
			// 发送方帐号（open_id）
			String fromUserName = requestMap.get("FromUserName");
			System.out.println("=============WXCheckServlet fromUserName: "+fromUserName);
			// 公众帐号
			String toUserName = requestMap.get("ToUserName");
			System.out.println("=============WXCheckServlet toUserName: "+toUserName);
			// 消息类型
			String msgType = requestMap.get("MsgType");
			// 接收时间 
			String createTime = requestMap.get("CreateTime");
			System.out.println("=============WXCheckServlet createTime: "+createTime);
			// 事件KEY值，qrscene_为前缀，后面为二维码的参数值 
			String eventKey = requestMap.get("EventKey");
			
			// 回复文本消息
//			TextMessage textMessage = new TextMessage();
//			textMessage.setToUserName(fromUserName);
//			textMessage.setFromUserName(toUserName);
//			textMessage.setCreateTime(new Date().getTime());
//			textMessage.setMsgType(MessageUtil.RESP_MESSAGE_TYPE_TEXT);
//			textMessage.setFuncFlag(0);

			// 文本消息
			if (msgType.equals(MessageUtil.REQ_MESSAGE_TYPE_TEXT)) {
				String contentValue = requestMap.get("Content"); 
				respContent = "Sunlight提示：您发送的是文本消息！内容是："+contentValue;
			}
			// 图片消息
			else if (msgType.equals(MessageUtil.REQ_MESSAGE_TYPE_IMAGE)) {
				respContent = "Sunlight提示：您发送的是图片消息！";
			}
			// 地理位置消息
			else if (msgType.equals(MessageUtil.REQ_MESSAGE_TYPE_LOCATION)) {
				respContent = "Sunlight提示：您发送的是地理位置消息！"; 
			}
			// 链接消息
			else if (msgType.equals(MessageUtil.REQ_MESSAGE_TYPE_LINK)) {
				respContent = "Sunlight提示：您发送的是链接消息！";
			}
			// 音频消息
			else if (msgType.equals(MessageUtil.REQ_MESSAGE_TYPE_VOICE)) {
				respContent = "Sunlight提示：您发送的是音频消息！";
			}
			// 事件推送
			else if (msgType.equals(MessageUtil.REQ_MESSAGE_TYPE_EVENT)) {
				
//				System.out.println("=============WXCheckServlet  eventType:"+eventType);
				if (eventType.equalsIgnoreCase(MessageUtil.EVENT_TYPE_SUBSCRIBE)) //关注
				{
					System.out.println("=============WXCheckServlet:关注成功");
					
//					String eventKey = requestMap.get("EventKey");
//					System.out.println("=============WXCheckServlet  eventKey:"+eventKey);
					
					MenberDTO menber = myMenberFacade.findMenberByOpenId(fromUserName);
					if(null == menber){
						MenberDTO newMenber = new MenberDTO();
						newMenber.setOpenId(fromUserName);
						newMenber.setLoginName("");
						newMenber.setRealName("");
						newMenber.setSex("");
						newMenber.setCity("");
						newMenber.setHeadimgurl("");
						newMenber.setPoint(100);//注册用户免费赠送100商城积分
						newMenber.setRegTime(new Date());
						newMenber.setLastTime(new Date());
						newMenber.setStatus(0);
						newMenber.setFax("");
						newMenber.setAreaCode("");
						newMenber.setType(1);//微信会员
						newMenber.setBalanceFee(new BigDecimal(0.00));
						newMenber.setRightFee(new BigDecimal(0.00));
						newMenber.setContributeFee(new BigDecimal(0.00));
						newMenber.setIsjoin(0);
						newMenber.setSign(0);
						newMenber.setRemind(0);
						
						String k = (StringUtils.isBlank(GlobalConfig.getProperty("bms", 
					      "sysusercreateKey"))) ? 
					      "123456565643450987657689876543267676787651234567" : 
					      GlobalConfig.getProperty("bms", "sysusercreateKey");
					    String passWord = com.sinovatech.common.util.Des.encrytWithBase64("DESede", "123456", k);
					    newMenber.setPassword(passWord);
						
						if(null != eventKey && !eventKey.equals("")){
							String salesMenId = eventKey.substring(8,eventKey.length());
							System.out.println("=============WXCheckServlet salesMenId:"+salesMenId);
//							MenberDTO m = myMenberFacade.get(salesMenId);
//							if(null!=m){
//								double rightFee = m.getRightFee().doubleValue() + 200;
//								m.setRightFee(new BigDecimal(rightFee));//每邀请一个好友加入，附加保障金额增加200元
//								myMenberFacade.update(m);
//							}
//							content = "恭喜您由【" + salesMen.getLoginName() + "】推荐成为【延光同舟会】的成员。";
							
							newMenber.setSalesMenId(salesMenId);//保存分销员
						}
						String newMenId = myMenberFacade.save(newMenber);
						//将用户信息保存在session中
				    	request.getSession().setAttribute("wxmenber", newMenber);
				    	request.getSession().setAttribute("wxmenberId", newMenId);
				    	menber = newMenber;
                	}else{
                		menber.setStatus(0);
                		myMenberFacade.update(menber);
                		//将用户信息保存在session中
				    	request.getSession().setAttribute("wxmenber", menber);
				    	request.getSession().setAttribute("wxmenberId", menber.getId());
                	}
					
					if(content.equals("")){
						if(null != menber && !menber.getLoginName().equals("")){
							content = "恭喜您【" + menber.getLoginName() + "】已经关注到惠达,欢迎您成为我们的会员。";
						}else{
							content = "恭喜您已经关注到惠达,欢迎您成为我们的会员。";
						}
					}
					
//					content = content + "\n------------------\n";
//					content = content + "亲，赶快行动吧！";
					
					//回复消息
					returnStr.append("<xml>");  
					returnStr.append("<ToUserName><![CDATA[" + fromUserName + "]]></ToUserName>");  
					returnStr.append("<FromUserName><![CDATA[" + toUserName + "]]></FromUserName>");  
					returnStr.append("<CreateTime>" + createTime + "</CreateTime>");  
					returnStr.append("<MsgType><![CDATA[text]]></MsgType>");  
					returnStr.append("<Content><![CDATA["+content+"]]></Content>");  
					returnStr.append("</xml>");  
		            
				}else if (eventType.equalsIgnoreCase(MessageUtil.EVENT_TYPE_UNSUBSCRIBE)) {//取消关注
					System.out.println("=============WXCheckServlet:取消关注成功");
					MenberDTO m = myMenberFacade.findMenberByOpenId(fromUserName);
					if(null!=m){
						m.setStatus(1);
						myMenberFacade.update(m);
						System.out.println("=============WXCheckServlet:修改会员状态成功");
					}
				}
				
				/*String latitude = requestMap.get("Latitude");//纬度
				String longitude = requestMap.get("Longitude");//经度
				String precision = requestMap.get("Precision");//地理位置精度 

				LocationService locationService = (LocationService)SpringContextUtil.getBean("locationService");
				Location location = locationService.getLocationByOpenid(fromUserName);
				if(null != location){
					location.setLatitude(latitude);
					location.setLongitude(longitude);
					location.setPrecision(precision);
					locationService.updateLocation(location);
				}else{
					location = new Location();
					location.setOpenid(fromUserName);
					location.setLatitude(latitude);
					location.setLongitude(longitude);
					location.setPrecision(precision);
					locationService.insertLocation(location);
				}*/
				
//				request.getSession().setAttribute(fromUserName+"_Latitude", latitude);
//				request.getSession().setAttribute(fromUserName+"_Longitude", longitude);
//				request.getSession().setAttribute(fromUserName+"_Precision", precision);
				
				
				// 自定义菜单点击事件
//				if (eventType.equalsIgnoreCase(MessageUtil.EVENT_TYPE_CLICK)) {
//					// 事件KEY值，与创建自定义菜单时指定的KEY值对应  
//                    String eventKey = requestMap.get("EventKey");	
//                    System.out.println("EventKey="+eventKey);
//                    respContent = "Sunlight提示：您点击的菜单KEY是"+eventKey;
//				}
			}
			
//			System.out.println("------------respContent------------------------------------");
//			System.out.println(respContent);
//			System.out.println("------------respContent------------------------------------");

//			textMessage.setContent(respContent);
			respMessage = returnStr.toString();
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println(e);
			respMessage="有异常了。。。";
		}
		return respMessage;
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
