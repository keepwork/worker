package com.weixin.util;

import java.util.Date;
import org.apache.log4j.Logger;
import com.sinovatech.common.config.GlobalConfig;
import com.sinovatech.framework.common.helper.SpringBeanHelper;
import com.sinovatech.hd.tools.cache.CacheFactory;
import com.sinovatech.hd.tools.cache.ICache;
import com.weixin.po.WeiXinOauth2Token;
import com.weixin.token.dao.TokenDAO;
import com.weixin.token.dto.TokenDTO;

/**
 * 定时获取access_token并更新缓存
 * 
 * @author chenxin keepwork512@163.com
 * @date 2015-06-18
 */
public class TokenThread implements Runnable {

	private static Logger logger = Logger.getLogger(TokenThread.class);
	
    public void run() 
    {    
    	TokenDAO myTokenDAO = (TokenDAO) SpringBeanHelper.getBeanContext().getBean("myTokenDAO");
    	String appid = GlobalConfig.getProperty("weixin", "appid");//应用ID
		String appsecret = GlobalConfig.getProperty("weixin", "appsecret");//应用密钥
		ICache cache = CacheFactory.newCache();
        
    	/*while (true) {    
            try {    
            	boolean isSuccess = true;
				if(null != appid && !appid.equals("") && null != appsecret && !appsecret.equals(""))
				{
					WeiXinOauth2Token accessToken = WeixinUtil.getAccessToken(appid,appsecret);    
	                if (null != accessToken) 
	                {    
	                	logger.info("获取access_token成功，有效时长："+accessToken.getExpiresIn()+"秒 token:"+accessToken.getAccessToken());  
	                	
	                	//生成jsapi_ticket，用于签名算法
	        			String jsapiTicket = WeixinUtil.getticket(accessToken.getAccessToken());
	        			
	        			TokenDTO token = myTokenDAO.get("1");
	        			token.setAccess_token(accessToken.getAccessToken());
	        			token.setJsapi_ticket(jsapiTicket);
	        			token.setUpdateTime(new Date());
	                	myTokenDAO.update(token);
	                	
	                	cache.set("jsapi_ticket", jsapiTicket);
	                	cache.set("access_token", accessToken.getAccessToken());
	                	
	                }else{//获取失败
	                	logger.info("获取access_token失败");  
	                	isSuccess = false;
	                }
				}
            	
            	if(isSuccess){
            		// 休眠7000秒
                    //Thread.sleep((accessToken.getExpiresIn() - 200) * 1000);  
            		Thread.sleep(7000*1000); 
            	}else{
            		//如果有access_token为null，60秒后再获取    
                    Thread.sleep(60 * 1000);    
            	}
            	
            } catch (InterruptedException e) {    
                try {    
                    Thread.sleep(60 * 1000);    
                } catch (InterruptedException e1) {    
                	logger.error("{}", e1);    
                }    
                logger.error("{}", e);    
            }    
        }    */
    }    
}
