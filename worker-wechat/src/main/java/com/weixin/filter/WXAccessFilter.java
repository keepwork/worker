package com.weixin.filter;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.Date;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;

import com.pub.menber.model.dto.MenberDTO;
import com.pub.menber.model.facade.MenberFacade;
import com.sinovatech.common.config.GlobalConfig;
import com.sinovatech.framework.common.helper.SpringBeanHelper;
import com.weixin.po.WeiXinOauth2Token;
import com.weixin.po.WeiXinUserInfo;
import com.weixin.util.WeixinUtil;

/**
 * 微信访问过滤器
 * 
 * @author kevin(keepwork512@163.com)
 * @date Jan 4, 2016 2:41:57 PM
 */
public class WXAccessFilter implements Filter
{
    private static Logger log = Logger.getLogger(WXAccessFilter.class);
    
    private MenberFacade myMenberFacade;
    
    @Override
    public void doFilter(ServletRequest servletrequest, ServletResponse servletresponse, FilterChain filterchain)
        throws IOException, ServletException
    {
        HttpServletRequest request = (HttpServletRequest)servletrequest;
        HttpServletResponse response = (HttpServletResponse)servletresponse;
        myMenberFacade = (MenberFacade) SpringBeanHelper.getBeanContext().getBean("myMenberFacade");
        String serverDomain = GlobalConfig.getProperty("filePath", "prefix");
        
        String uri = request.getRequestURI();
		log.info("WXAccessFilter ============= uri:"+uri);
		
		String type = request.getParameter("type");
		log.info("WXAccessFilter ============= type:"+type);
		
		//网页版
		if(null != type && type.equals("web")){
			String leftMenu = request.getParameter("leftMenu");
			if(null != leftMenu && !leftMenu.equals("")){
				MenberDTO menber = (MenberDTO)request.getSession().getAttribute("pcmenber");
				if(null == menber){//未登录
					response.sendRedirect(serverDomain+"/web/login.jsp");
				}else{
					filterchain.doFilter(request, response);
					if(1==1){return;}
				}
			}
		}
		
		//微信版
		if(null != type && type.equals("wap")){
			try{
				
				//判断是否微信浏览器
		        boolean wxBrowser = WeixinUtil.isWeiXinBrowser(request);
		        if(!wxBrowser){
		        	response.sendRedirect(serverDomain+"/wap/error.html");
		        }

				if ("/weixin/index.do".equals(uri)
						|| "/pub/menber/workApply.do".equals(uri)
						|| "/pub/articleCate/articleCateList.do".equals(uri)
						|| "/pub/goodCate/firstCates.do".equals(uri)
						|| "/pub/menber/centerInit.do".equals(uri))
				{

				////////////////////////////////////////////////测试用，生产上要去掉
//				if ("/worker-wechat/weixin/index.do".equals(uri)
//						|| "/worker-wechat/pub/menber/workApply.do".equals(uri)
//						|| "/worker-wechat/pub/articleCate/articleCateList.do".equals(uri)
//						|| "/worker-wechat/pub/goodCate/firstCates.do".equals(uri)
//						|| "/worker-wechat/pub/menber/myAccount.do".equals(uri))
//				{
//					String _openID = "o7Jq2wIeiWcLoA7UCQL5VhAa118M";
//					log.info("WXAccessFilter ============= openID:"+openID);
//					if(null!=_openID && !_openID.equals("")){
//						request.getSession().setAttribute("openID", _openID);
//						MenberDTO sessionMenber = myMenberFacade.findMenberByOpenId(_openID);
//						request.getSession().setAttribute("wxmenber", sessionMenber);
//						request.getSession().setAttribute("wxmenberId", sessionMenber.getId());
//						request.getSession().setAttribute("jsapi_ticket", "");
//					}

//            		filterchain.doFilter(request, response);
//	                if(1==1){return;}
				//////////////////////////////////////////////测试用，生产上要去掉
					
	                
	                
					// 将请求、响应的编码均设置为UTF-8（防止中文乱码）
					request.setCharacterEncoding("UTF-8");
					response.setCharacterEncoding("UTF-8");

					String appid = GlobalConfig.getProperty("weixin", "appid");//应用ID
					String appsecret = GlobalConfig.getProperty("weixin", "appsecret");//应用密钥
					String openID = (String)request.getSession().getAttribute("openID");
					log.info("WXAccessFilter ============= session openId:"+openID);
			                
					MenberDTO sm = (MenberDTO)request.getSession().getAttribute("wxmenber");
					if(null==sm){
						log.info("WXAccessFilter ============= session menber is null ");
					}else{
						log.info("WXAccessFilter ============= session menber loginName:"+sm.getLoginName()+",realName:"+sm.getRealName());
					}
			                
			                
					if(null == openID || "".equals(openID))// || null == sm)
					{
						// ====== 第一步：用户同意授权，获取code（通过访问菜单地址进行授权后会返回code）
						String code = (String)request.getParameter("code");
						log.info("WXAccessFilter ============= code:"+code);
						if(null == code || code.equals("")){
							log.info("WXAccessFilter ============= 用户不同意授权");
							request.getSession().invalidate();
							return;
						}
			                    
						// ====== 第二步：通过code换取网页授权access_token
						WeiXinOauth2Token token = new WeiXinOauth2Token();
						token = WeixinUtil.getOauth2AccessToken(appid,appsecret,code);
						log.info("WXAccessFilter ============= token:"+token);
						if(null != token){
							request.getSession().setAttribute("openID", token.getOpenId());
							request.getSession().setAttribute("accessToken", token.getAccessToken());
							log.info("set seesion accessToken:"+token.getAccessToken());
			                    	
							// ====== 第四步：拉取用户信息(需scope为 snsapi_userinfo)
							WeiXinUserInfo wxuser = WeixinUtil.getWeiXinUserInfo(token.getAccessToken(),token.getOpenId(),request);
							log.info("wxuser0:"+wxuser);
							if(null == wxuser){
								log.info("getRefeshToken:"+token.getRefeshToken());
								token = WeixinUtil.refreshAccessToken(appid,token.getRefeshToken());
								log.info("getAccessToken:"+token.getAccessToken());
								wxuser = WeixinUtil.getWeiXinUserInfo(token.getAccessToken(),token.getOpenId(),request);
								log.info("wxuser1:"+wxuser);
							}
			                        
							if(null != wxuser && null != wxuser.getOpenid()){
								log.info("wxuser openId:"+wxuser.getOpenid());
								// 保存微信用户到数据库
								MenberDTO menber = myMenberFacade.findMenberByOpenId(wxuser.getOpenid());
								log.info("WXAccessFilter ============= menber1:"+menber);

								if(null == menber && "/menber/workerBind.do".equals(uri)){//工人师傅绑定
									response.sendRedirect("http://" + serverDomain + "/wap/workerBind.html");
									return;
								}else if(null == menber){
									addMenber(request,response,wxuser);//新增会员信息
								}else{
									//将用户信息保存在session中
									request.getSession().setAttribute("wxmenber", menber);
									request.getSession().setAttribute("wxmenberId", menber.getId());

									if(!menber.getHeadimgurl().equals(wxuser.getHeadimgurl())){
										updateMenber(menber,wxuser);//修改会员信息
									}
								}
							}
			                        
						}else{//获取网页授权凭证失败
							log.info("WXAccessFilter ============= 获取网页授权凭证失败");
							request.getSession().invalidate();
							return;
						}
					}else{

						// 更新微信用户资料到数据库
						MenberDTO menber = myMenberFacade.findMenberByOpenId(openID);
						log.info("WXAccessFilter ============= menber2:"+menber);
						if(null == menber && "/menber/workerBind.do".equals(uri)){//工人师傅绑定
							response.sendRedirect("http://" + serverDomain + "/wap/workerBind.html");
							return;
						}else if(null != menber)
						{
							String accessToken = (String)request.getSession().getAttribute("accessToken");
							log.info("WXAccessFilter ============= accessToken2:"+accessToken);
							if(null != accessToken)
							{
								WeiXinUserInfo wxuser = WeixinUtil.getWeiXinUserInfo(accessToken,openID,request);
								log.info("WXAccessFilter ============= wxuser2:"+wxuser);
								if(null != wxuser){
									//将用户信息保存在session中
									request.getSession().setAttribute("wxmenber", menber);
									request.getSession().setAttribute("wxmenberId", menber.getId());

									if(!menber.getHeadimgurl().equals(wxuser.getHeadimgurl())){
										updateMenber(menber,wxuser);//修改会员信息
									}
								}
							}
						}else{
							String accessToken = (String)request.getSession().getAttribute("accessToken");
							log.info("WXAccessFilter ============= accessToken3:"+accessToken);
							if(null != accessToken)
							{
								WeiXinUserInfo wxuser = WeixinUtil.getWeiXinUserInfo(accessToken,openID,request);
								addMenber(request,response,wxuser);//新增会员信息
							}
						}
					}
//			            }else{
//			            	log.info("WXAccessFilter ============= 未进入");
//			            }
			            
					filterchain.doFilter(request, response);
					if(1==1){return;}
	            }
				
				}catch (Exception e) {
					// TODO: handle exception
				}
//			}
		}
		
		filterchain.doFilter(request, response);
		if(1==1){return;}
//		log.info("WXAccessFilter ============= end");
		
		
    }
    
    /**
     * 新增会员信息
     * @param request
	 * @param response
	 * @param wxuser
     * @throws IOException
     */
    public void addMenber(HttpServletRequest request,HttpServletResponse response,WeiXinUserInfo wxuser) throws IOException
	{
    	String salesMenId = (String)request.getParameter("salesMenId");
    	log.info("WXAccessFilter ============= 新增会员openID："+wxuser.getOpenid()+",nickName:"+wxuser.getNickname()+",salesMenId："+salesMenId);	
    	if(null == wxuser.getOpenid() || "".equals(wxuser.getOpenid())){
			request.getSession().invalidate();//清除session
		}else{
			MenberDTO newMenber = new MenberDTO();
			newMenber.setOpenId(wxuser.getOpenid());
			newMenber.setLoginName(wxuser.getNickname());
			if(null != wxuser.getSex())
			{
				newMenber.setSex(wxuser.getSex());
			}
			newMenber.setCity(wxuser.getCity());
			newMenber.setHeadimgurl(wxuser.getHeadimgurl());
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
			
			if(null != salesMenId && !salesMenId.equals("")){
				newMenber.setSalesMenId(salesMenId);
			}
			
			String newMenId = myMenberFacade.save(newMenber);
			
			//将用户信息保存在session中
	    	request.getSession().setAttribute("wxmenber", newMenber);
	    	request.getSession().setAttribute("wxmenberId", newMenId);
	    	
	    	log.info("WXAccessFilter ============= 新增会员成功");	
		}
	}
    
    
    /**
     * 修改会员信息
	 * @param menber
	 * @param wxuser
     */
    public void updateMenber(MenberDTO menber,WeiXinUserInfo wxuser)
	{
    	log.info("WXAccessFilter ============= 修改会员信息："+wxuser.getNickname());	
    	menber.setLoginName(wxuser.getNickname()); 
		if(null != wxuser.getSex()){
			menber.setSex(wxuser.getSex());
		}
		menber.setCity(wxuser.getCity()); 
		menber.setHeadimgurl(wxuser.getHeadimgurl());
		menber.setLastTime(new Date());
		myMenberFacade.update(menber);
		log.info("WXAccessFilter ============= 修改会员成功");	
	}
    
    
    
    @Override
    public void init(FilterConfig filterconfig)
        throws ServletException
    {
        
    }
    
    @Override
    public void destroy()
    {
        // TODO Auto-generated method stub
        
    }
    
}
