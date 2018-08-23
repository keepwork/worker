package com.weixin.util;

import java.io.*;
import javax.servlet.http.HttpServletRequest;


import com.sinovatech.common.config.GlobalConfig;
import com.weixin.po.WeiXinOauth2Token;
import com.weixin.po.WeiXinUserInfo;
import net.sf.json.JSONObject;
import org.apache.log4j.Logger;

/**
 * 微信接口帮助类
 * 
 * @author chenxin keepwork512@163.com
 * @date 2015-06-18
 *
 */
public class WeixinUtil {
	private static Logger log = Logger.getLogger(WeixinUtil.class);

	 /**
     * 通过code换取网页授权access_token
     * @param appId
     * @param appSecret
     * @param code
     * @return
     * {
     * 	"access_token":"OezXcEiiBSKSxW0eoylIeMshGK--9T2mIXkVltgZYwpwAmISY7k9Uocssr1-O-EjmH18E6sVZI-DsBmeX91lH0BVjEL8SXvJfT1DVM9BRCaZm5NdANTJlvl34GmKCdsLhx6OuqNRua-igh-bUEEt1w",
     *  "expires_in":7200,
     *  "refresh_token":"OezXcEiiBSKSxW0eoylIeMshGK--9T2mIXkVltgZYwpwAmISY7k9Uocssr1-O-EjfwazxfDyVVkQTRUOciCUm1kbtG-HI2Fwpccj2RkDSSiZRDX5DahIByG5hBLCw8b-GDA4uMDdsTcomwjVlXiJew",
     *  "openid":"oZMTCtxr_E390WG6FsTDL0oekwx8",
     *  "scope":"snsapi_userinfo"}
     * 
     */
    public static WeiXinOauth2Token getOauth2AccessToken(String appId,
			String appSecret , String code) {
		WeiXinOauth2Token wat = new WeiXinOauth2Token();
		
		//获取access_token
		String accessUrl = "https://api.weixin.qq.com/sns/oauth2/access_token?appid="+appId
			+"&secret="+appSecret+"&code="+code+"&grant_type=authorization_code";
//		System.out.println("==================getOauth2AccessToken======================");
//        System.out.println(accessUrl);
//        System.out.println("==================getOauth2AccessToken======================");
        
		JSONObject jsonObject = CommonUtil.httpsRequest(accessUrl,"GET");
		if (null != jsonObject) {
			try {
				wat = new WeiXinOauth2Token();
				//这里获得的accessToken是一个特殊的网页授权access_token,与基础支持中的access_token（该access_token用于调用其他接口）不同。
				wat.setAccessToken(jsonObject.getString("access_token")+"");
				wat.setExpiresIn(jsonObject.getInt("expires_in"));
				wat.setRefeshToken(jsonObject.getString("refresh_token")+"");
				wat.setOpenId(jsonObject.getString("openid")+"");
				wat.setScope(jsonObject.getString("scope")+"");
//				wat.setUnionid(jsonObject.getString("unionid")+"");
//				System.out.println("getOauth2AccessToken openid:"+jsonObject.getString("openid"));
//				System.out.println("getOauth2AccessToken unionid:"+jsonObject.getString("unionid"));
			} catch (Exception e) {
				wat = null;
				String errorCode = jsonObject.getString("errcode");
				String errorMsg = jsonObject.getString("errmsg");
				System.out.println("获取网页授权凭证失败 errcode{},errMsg,errorCode:"
						+ errorCode + ",errorMsg:" + errorMsg);
			}
		}
		return wat;
	}
    
    
    
    /**
     * 查询 access_token
     * @param appId
     * @param appSecret
     * @return
     */
    public static WeiXinOauth2Token getAccessToken(String appId,String appSecret) {
		WeiXinOauth2Token wat = new WeiXinOauth2Token();
		
		//获取access_token
		String accessUrl = "https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential&appid="+appId+"&secret="+appSecret;
//		System.out.println("==================getAccessToken======================");
//        System.out.println(accessUrl);
//        System.out.println("==================getAccessToken======================");
        
		JSONObject jsonObject = CommonUtil.httpsRequest(accessUrl,"GET");
		if (null != jsonObject) {
			try {
				wat = new WeiXinOauth2Token();
				wat.setAccessToken(jsonObject.getString("access_token")+"");
				wat.setExpiresIn(jsonObject.getInt("expires_in"));
			} catch (Exception e) {
				wat = null;
				String errorCode = jsonObject.getString("errcode");
				String errorMsg = jsonObject.getString("errmsg");
				System.out.println("获取网页授权凭证失败 errcode{},errMsg,errorCode:"
						+ errorCode + ",errorMsg:" + errorMsg);
			}
		}
		return wat;
	}
    
    
    
    /**
     * 刷新access_token（如果需要）
     * @param appId
     * @return
     */
    public static WeiXinOauth2Token refreshAccessToken(String appId, String refreshToken) {
		WeiXinOauth2Token wat = new WeiXinOauth2Token();
		
		//获取access_token
		String accessUrl = "https://api.weixin.qq.com/sns/oauth2/refresh_token?appid="+appId+"&grant_type=refresh_token&refresh_token="+refreshToken;
//		System.out.println("==================refreshAccessToken======================");
//        System.out.println(accessUrl);
//        System.out.println("==================refreshAccessToken======================");
        
		JSONObject jsonObject = CommonUtil.httpsRequest(accessUrl,"GET");
		if (null != jsonObject) {
			try {
				wat = new WeiXinOauth2Token();
				wat.setAccessToken(jsonObject.getString("access_token")+"");
				wat.setExpiresIn(jsonObject.getInt("expires_in"));
				wat.setRefeshToken(jsonObject.getString("refresh_token")+"");
				wat.setOpenId(jsonObject.getString("openid")+"");
				wat.setScope(jsonObject.getString("scope")+"");
				//wat.setUnionid(jsonObject.getString("unionid"));
			} catch (Exception e) {
				wat = null;
				String errorCode = jsonObject.getString("errcode");
				String errorMsg = jsonObject.getString("errmsg");
				System.out.println("获取网页授权凭证失败 errcode{},errMsg,errorCode:"
						+ errorCode + ",errorMsg:" + errorMsg);
			}
		}
		return wat;
	}
    
    
    /**
     * 拉取用户信息(需scope为 snsapi_userinfo)
     * 
     * @param accessToken
     * @param openId
     * @return
     * 
     * {
     * 		"openid":"oZMTCtxr_E390WG6FsTDL0oekwx8",
     * 		"nickname":"闄堟槙",
     * 		"sex":1,
     * 		"language":"zh_CN",
     *      "city":"娣卞湷",
     *      "province":"骞夸笢",
     *      "country":"涓浗",
     *      "headimgurl":"http:\/\/wx.qlogo.cn\/mmopen\/KKcJrEJXNTlqNQsA7gO2W5wRJ9q1Fg5JLGHxUcCTWe6j0qceOZFfWrfZDPpibJO4IfaqMoV6lNXRnBhdp1wramC8niad7F2p8x\/0",
     *      "privilege":[]
     * }
     */
    public static WeiXinUserInfo getWeiXinUserInfo(String accessToken,String openId,HttpServletRequest request) 
    {
    	WeiXinUserInfo user = new WeiXinUserInfo();
		
		//获取access_token
		String accessUrl = "https://api.weixin.qq.com/sns/userinfo?access_token="+accessToken+"&openid="+openId+"&lang=zh_CN";
//		System.out.println("==================getWeiXinUserInfo======================");
//        System.out.println(accessUrl);
//        System.out.println("==================getWeiXinUserInfo======================");
//        System.out.println("==================getWeiXinUserInfo======================openId:"+openId);
        
		JSONObject jsonObject = CommonUtil.httpsRequest(accessUrl,"GET");
		if (null != jsonObject) {
			try {
				user = new WeiXinUserInfo();
				user.setOpenid(jsonObject.getString("openid")+"");
				
				String nickname = jsonObject.getString("nickname")+"";
//				System.out.println("getWeiXinUserInfo nickname1: "+jsonObject.getString("nickname"));
				nickname = nickname.replaceAll("[^A-Za-z0-9\\u4e00-\\u9fa5]", " ");//取出中英文数字以及部分特殊字符，去掉其他字符
				user.setNickname(nickname);
//				System.out.println("getWeiXinUserInfo nickname2: "+jsonObject.getString("nickname"));

				user.setSex(jsonObject.getString("sex")+"");
				user.setProvince(jsonObject.getString("province")+"");
				user.setCity(jsonObject.getString("city")+"");
				user.setCountry(jsonObject.getString("country")+"");
				user.setHeadimgurl(jsonObject.getString("headimgurl")+"");
				//user.setPrivilege(jsonObject.getString("privilege"));
//				user.setUnionid(jsonObject.getString("unionid"));
			} catch (Exception e) {
				user = null;
				String errorCode = jsonObject.getString("errcode");
				String errorMsg = jsonObject.getString("errmsg");
				if(errorCode.equals("42001")){//如果报errorCode:42001,errorMsg:access_token expired，清除缓存
					request.getSession().invalidate();
			    	System.out.println("========================= session invalidate success ");
				}
				System.out.println("拉取用户信息失败 errcode{},errMsg,errorCode:" + errorCode + ",errorMsg:" + errorMsg);
			}
		}
		return user;
	}
    
    
    
    public static String getticket(String accessToken) 
    {
		//获取jsapi_ticket
		String accessUrl = "http://api.weixin.qq.com/cgi-bin/ticket/getticket?type=jsapi&access_token="+accessToken;
//		System.out.println("==================getticket======================");
//        System.out.println(accessUrl);
//        System.out.println("==================getticket======================");
        
		JSONObject jsonObject = CommonUtil.httpRequest(accessUrl,"GET");
		if (null != jsonObject) {
			try {
				int errcode = jsonObject.getInt("errcode");
				String errmsg = jsonObject.getString("errmsg")+"";
				String ticket = jsonObject.getString("ticket")+"";
				int expires_in = jsonObject.getInt("expires_in");
				if(errcode==0){
					return ticket;
				}
			} catch (Exception e) {
				int errcode = jsonObject.getInt("errcode");
				String errmsg = jsonObject.getString("errmsg")+"";
				System.out.println("获取jsapi_ticket失败 errcode{},errMsg,errorCode:" + errcode + ",errorMsg:" + errmsg);
			}
		}
		return "";
	}
    
    
    /**
     * 获取永久二维码所需的ticket
     * expire_seconds：该二维码有效时间，以秒为单位。 最大不超过604800（即7天）。
     * action_name ：二维码类型，QR_SCENE为临时,QR_LIMIT_SCENE为永久,QR_LIMIT_STR_SCENE为永久的字符串参数值 
     * action_info ：二维码详细信息 
     * scene_id ：场景值ID，临时二维码时为32位非0整型，永久二维码时最大值为100000（目前参数只支持1--100000） 
     * scene_str ：场景值ID（字符串形式的ID），字符串类型，长度限制为1到64，仅永久二维码支持此字段 
     * @param accessToken
     * @return 
     * 返回正确：{"ticket":"gQH47joAAAAAAAAAASxodHRwOi8vd2VpeGluLnFxLmNvbS9xL2taZ2Z3TVRtNzJXV1Brb3ZhYmJJAAIEZ23sUwMEmm3sUw==","expire_seconds":60,"url":"http:\/\/weixin.qq.com\/q\/kZgfwMTm72WWPkovabbI"}
     * 返回错误：{"errcode":40013,"errmsg":"invalid appid"}
     */
    public static String getCodeImageTicket(String accessToken,String menId) 
    {
    	String ticket = "";
    	String accessUrl = "https://api.weixin.qq.com/cgi-bin/qrcode/create?access_token="+accessToken;
        String param = "{\"action_name\":\"QR_LIMIT_STR_SCENE\",\"action_info\": {\"scene\": {\"scene_str\": \""+menId+"\"}}}";
		JSONObject jsonObject = CommonUtil.httpsRequestJSON(accessUrl,"POST",param);
		if (null != jsonObject) {
			try {
				String url = jsonObject.getString("url")+"";
				ticket = jsonObject.getString("ticket")+"";
			} catch (Exception e) {
				int errcode = jsonObject.getInt("errcode");
				String errmsg = jsonObject.getString("errmsg")+"";
				System.out.println("获取永久二维码失败 errcode{},errMsg,errorCode:" + errcode + ",errorMsg:" + errmsg);
			}
		}
		return ticket;
	}
    
    
    
    /*public static String getOpenId(String accessToken) 
    {
		//获取jsapi_ticket
		String accessUrl = "https://graph.z.qq.com/moc2/me?access_token="+accessToken;
		System.out.println("==================getOpenId======================");
        System.out.println("accessUrl:"+accessUrl);
        
		JSONObject jsonObject = CommonUtil.httpRequest(accessUrl,"GET");
        System.out.println("jsonObject:"+jsonObject.toString());
		if (null != jsonObject) {
			try {
				String openid = jsonObject.getString("openid")+"";
				System.out.println("openid:"+openid);
				return openid;
			} catch (Exception e) {
				int errcode = jsonObject.getInt("errcode");
				String errmsg = jsonObject.getString("errmsg")+"";
				System.out.println("获取jsapi_ticket失败 errcode{},errMsg,errorCode:" + errcode + ",errorMsg:" + errmsg);
			}
		}
		System.out.println("==================getOpenId======================");
		return "";
	}*/


    
    /**
     * 通过ticket换取二维码
     * @return
     * @throws UnsupportedEncodingException 
     */
    /*public static void getCodeImageByTicket(String ticket,String basePath)
    {
    	String accessUrl = "https://mp.weixin.qq.com/cgi-bin/showqrcode?ticket="+urlEncodeUTF8(ticket);
    	byte[] btImg = CommonUtil.httpsRequestImage(accessUrl);
    	if(null != btImg && btImg.length > 0){  
    		try {  
    			File file = new File(basePath);
                FileOutputStream out = new FileOutputStream(file);  
                out.write(btImg);  
                out.flush();  
                out.close();  
                System.out.println("图片已经写入碰盘");  
            } catch (Exception e) {  
                e.printStackTrace();  
            }  
		}
	}*/
    public static String urlEncodeUTF8(String source) {
		String result = source;
		try {
			result = java.net.URLEncoder.encode(source, "utf-8");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		return result;
	}
    
    
    /**
     * 判断是不是微信浏览器
     * @param request
     * @return
     */
    public static boolean isWeiXinBrowser(HttpServletRequest request) {
    	String online = GlobalConfig.getProperty("weixin", "online");
    	if(null!=online && online.equals("true")){
    		String ua = ((HttpServletRequest) request).getHeader("user-agent").toLowerCase();
    		if (ua.indexOf("micromessenger") > 0) {// 是微信浏览器
    			return true;
    		}else{
    			return false;
    		}
    	}
		return true;
	}
    
    
    /**
     * 发送消息
     * 
     * @param content
     * @param fromUserName
     * @param toUserName
     * @param createTime
     */
    public static void sendMessage(String content,String fromUserName,String toUserName,String createTime) {
    	StringBuffer returnStr = new StringBuffer();  
		returnStr.append("<xml>");  
		returnStr.append("<ToUserName><![CDATA[" + fromUserName + "]]></ToUserName>");  
		returnStr.append("<FromUserName><![CDATA[" + toUserName + "]]></FromUserName>");  
		returnStr.append("<CreateTime>" + createTime + "</CreateTime>");  
		returnStr.append("<MsgType><![CDATA[text]]></MsgType>");  
		returnStr.append("<Content><![CDATA["+content+"]]></Content>");  
		returnStr.append("</xml>");  
	}


	/**
	 * 下载并压缩文件
	 *
	 * @param accessToken
	 * @param media_id
	 * @param path
	 * @return null为成功
	 */
	private static final String download_url = "http://file.api.weixin.qq.com/cgi-bin/media/get?access_token=ACCESS_TOKEN&media_id=MEDIA_ID";
	public static String download(String accessToken, String media_id, String type,String fileName) {
//		log.debug("======================================== WeixinUtil download");
		String url = download_url.replace("ACCESS_TOKEN", accessToken).replace("MEDIA_ID", media_id);
		log.debug("======================================== url :"+url);
		byte[] data = CommonUtil.httpRequestImage(url);
		log.debug("======================================== data.length :"+data.length);
		log.debug("======================================== data :"+data);
		if (data.length < 200) { //报错
			String s = new String(data);
			log.debug("======================================== s :"+s);
			if (s.startsWith("{") && s.endsWith("}")) {
				return s;
			}
		}
		try {
			// 将字符串转换成二进制，用于显示图片
			// 将上面生成的图片格式字符串 imgStr，还原成图片显示
			InputStream in = new ByteArrayInputStream(data);
			//String file = "/data/wwwroot/default/common/upload/temporary/"+(int) ((Math.random() * 9 + 1) * 100000)+".jpg";//临时图片路径
			String filePath = GlobalConfig.getProperty("filePath", "order.transaction.record.path");//订单交易记录图片存储路径

			filePath = filePath+type+"/"+fileName;
			File tempFile = new File(filePath);
			FileOutputStream fos = new FileOutputStream(tempFile);
			byte[] b = new byte[1024];
			int nRead = 0;
			while ((nRead = in.read(b)) != -1) {
				fos.write(b, 0, nRead);
			}
			fos.flush();
			fos.close();
			in.close();

			/* 压缩处理
			int weight = Integer.parseInt(GlobalConfig.getProperty("filePath", "photo.small.weight"));
			//int height = Integer.parseInt(GlobalConfig.getProperty("filePath", "photo.small.height"));
			InputStream photoSmallIs = ImageUtil.zoomImageScale(tempFile, weight);//固定宽度，等比生成缩略图
			File file = new File(filePath,fileName);
			FileOutputStream fos1 = new FileOutputStream(file);
			byte[] b1 = new byte[1024];
			int nRead1 = 0;
			while ((nRead1 = photoSmallIs.read(b1)) != -1) {
				fos1.write(b1, 0, nRead1);
			}
			fos1.flush();
			fos1.close();
			photoSmallIs.close();

			FileTool.deleteFile(temporary);//删除临时文件
			-*/

//			FileOutputStream os = new FileOutputStream(filePath);
//			os.write(data);
//			os.close();
			return null;
		} catch (Exception e) {
			e.printStackTrace();
			JSONObject jsonObject = new JSONObject();
			jsonObject.put("assn_err_msg", "Download Exception:" + e.toString());
			return jsonObject.toString();
		}
	}

	/**
	 * 查询 用户列表
	 * @param accessToken
	 * @return
	 */
	public static boolean getCallBackIp(String accessToken) {
		String url = "https://api.weixin.qq.com/cgi-bin/getcallbackip?access_token="+accessToken;
		log.debug("==================getCallBackIp======================");
		log.debug(url);
		log.debug("==================getCallBackIp======================");

		Boolean exist = false;
		JSONObject jsonObject = CommonUtil.httpsRequest(url,"GET");
		if (null != jsonObject) {
			try {
				String ip_list = jsonObject.getString("ip_list")+"";
				log.debug("==================ip_list.length======================:"+ip_list.length());
				if(null != ip_list && !"".equals(ip_list)){
					exist = true;
				}
			} catch (Exception e) {
				String errorCode = jsonObject.getString("errcode");
				String errorMsg = jsonObject.getString("errmsg");
				log.debug("查询 微信服务器IP地址失败 errcode{},errMsg,errorCode:"
						+ errorCode + ",errorMsg:" + errorMsg);
			}
		}
		return exist;
	}

	/**
	 * 上传文件
	 * @return
	 */
	/*private static final String upload_url = "https://qyapi.weixin.qq.com/cgi-bin/media/upload?access_token=ACCESS_TOKEN&type=TYPE";
	public static Result<MdlUpload> Upload(String accessToken, String type, File file) {
		Result<MdlUpload> result = new Result<MdlUpload>();
		String url = upload_url.replace("ACCESS_TOKEN", accessToken).replace("TYPE", type);
		JSONObject jsonObject;
		try {
			HttpPostUtil post = new HttpPostUtil(url);
			post.addParameter("media", file);
			String s = post.send();
			jsonObject = JSONObject.fromObject(s);
			if (jsonObject.containsKey("media_id")) {
				MdlUpload upload=new MdlUpload();
				upload.setMedia_id(jsonObject.getString("media_id"));
				upload.setType(jsonObject.getString("type"));
				upload.setCreated_at(jsonObject.getString("created_at"));
				result.setObj(upload);
				result.setErrmsg("success");
				result.setErrcode("0");
			} else {
				result.setErrmsg(jsonObject.getString("errmsg"));
				result.setErrcode(jsonObject.getString("errcode"));
			}
		} catch (Exception e) {
			e.printStackTrace();
			result.setErrmsg("Upload Exception:"+e.toString());
		}
		return result;
	}*/

    public static void main(String[] args) {
		String url = "http://www.zhixiu.xyz/pub/menber/myAccount.do?type=wap";
		url = urlEncodeUTF8(url);
		System.out.println(url+"&response_type=code&scope=snsapi_userinfo&state=678#wechat_redirect");
		
//		String nickname = "_a%%d:中f|国[]{}|";
//		nickname = nickname.replaceAll("[^A-Za-z0-9!@#$%^&*()|<>{}?\\u4e00-\\u9fa5]", " ");//取出中文和数字，去掉其他字符
//		System.out.println("getWeiXinUserInfo nickname2: "+nickname);
		
		//https://open.weixin.qq.com/connect/oauth2/authorize?appid=wx795a6bd223954b76&redirect_uri=http%3A%2F%2Fwww.daguoz.com%2Fweixin.htm%3Fmethod%3DstoreList%26brandId%3D2&response_type=code&scope=snsapi_userinfo&state=123#wechat_redirect
	}
    
    
}
