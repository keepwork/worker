package com.pub;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.log4j.Logger;
//import com.anbot.menber.model.dto.AnMenberDTO;
//import com.anbot.menber.model.facade.AnMenberFacade;
import com.common.util.encry.MD5;
import com.sinovatech.common.config.GlobalConfig;
import com.sinovatech.framework.common.helper.SpringBeanHelper;

/**
 * 访问过滤器
 *
 * @author kevin(keepwork512@163.com)
 * @date Apr 5, 2016 8:29:06 AM
 */
public class AccessFilter implements Filter
{
    private static Logger log = Logger.getLogger(AccessFilter.class);
    
//    private AnMenberFacade myAnMenberFacade;
    
    @Override
    public void doFilter(ServletRequest servletrequest, ServletResponse servletresponse, FilterChain filterchain)
        throws IOException, ServletException
    {
        HttpServletRequest request = (HttpServletRequest)servletrequest;
        HttpServletResponse response = (HttpServletResponse)servletresponse;
//        myAnMenberFacade = (AnMenberFacade) SpringBeanHelper.getBeanContext().getBean("myAnMenberFacade");
//        String serverDomain = GlobalConfig.getProperty("filePath", "prefix");
//        // 将请求、响应的编码均设置为UTF-8（防止中文乱码）
//        request.setCharacterEncoding("UTF-8");
//        response.setCharacterEncoding("UTF-8");
//        
//		String servletPath = request.getServletPath();
//		log.info("AccessFilter ============= path:"+servletPath);
//		
//		
//		
//		//WEB版
//		if(null != servletPath && servletPath.startsWith("/web"))
//		{
//			boolean isAccess = this.isWebAccessUrl(servletPath);
//			log.info("AccessFilter ============= web isAccess:"+isAccess);
//			if(isAccess){
//				AnMenberDTO menber = (AnMenberDTO)request.getSession().getAttribute("pcmenber");
//				if(null == menber){//未登录
//					response.sendRedirect(serverDomain+"/web/login.jsp");
//					return;
//				}else{
//					filterchain.doFilter(request, response);
//					return;
//				}
//			}else{
//				filterchain.doFilter(request, response);
//				return;
//			}
//		}
//		
//		
//		
//		//WAP版
//		if(null != servletPath && servletPath.startsWith("/wap"))
//		{
//			boolean isAccess = this.isWapAccessUrl(servletPath);
//			log.info("AccessFilter ============= wap isAccess:"+isAccess);
//			if(isAccess){
////				AnMenberDTO menber = (AnMenberDTO)request.getSession().getAttribute("wapmenber");
////				if(null == menber)//未登录
////				{
//					String userName = request.getParameter("userName");
//			    	String passWord = request.getParameter("passWord");
//			    	String robotId = request.getParameter("robotId");
//			    	if(null!=userName && !userName.equals("") && null!=passWord && !passWord.equals("")){
////			    		String password_ = MD5.GetMD5Code(password);
//				    	AnMenberDTO m = myAnMenberFacade.validateMenber(userName, passWord);
//				    	if(null!=m){
//				    		request.getSession().setAttribute("wapmenber", m);
//					    	request.getSession().setAttribute("wapmenberId", m.getF_index());
//					    	request.getSession().setAttribute("robotId", robotId);
//				    	}else{
//				    		response.sendRedirect(serverDomain+"/common/error.jsp");
//				    		return;
//				    	}
//			    	}else{
//			    		response.sendRedirect(serverDomain+"/common/error.jsp");
//			    		return;
//			    	}
////				}else{
////					filterchain.doFilter(request, response);
////					return;
////				}
//			}else{
//				filterchain.doFilter(request, response);
//				return;
//			}
//		}
		
		
		
		
		filterchain.doFilter(request, response);
		return;
    }
    
    /**
     * 是否需要验证登录
     * @param uri
     * @return
     */
    public boolean isWapAccessUrl(String uri){
		if ("/wap/anbot/index.do".equals(uri) 
			
		){
			return true;
		}else{
			return false;
		}
    }
    
    
    public boolean isWebAccessUrl(String uri){
		if ("/menber/centerInit.do".equals(uri) 
			
		){
			return true;
		}else{
			return false;
		}
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
