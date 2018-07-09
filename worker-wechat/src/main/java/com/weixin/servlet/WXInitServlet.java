package com.weixin.servlet;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;

import com.weixin.util.TokenThread;

/**
 * 定时获取access_token
 * 
 * @author chenxin   keepwork512@163.com
 * @date 201506-16
 */
public class WXInitServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;    
    
    public void init() throws ServletException 
    {    
        // 启动定时获取access_token的线程    
        //new Thread(new TokenThread()).start();      
    }   

}
