package com.common.util;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * 
 * @author wangyonglong
 *
 * 2011-10-31
 */
public class ServletUtil {
	public static final String ENCODING = "UTF-8";
	
	/**
	 * 输出XML信息
	 * 
	 * @param response
	 * @param xmlStr
	 * @throws IOException
	 */
	public static void outputXML(HttpServletResponse response, String xmlStr)
			throws IOException {
		response.setContentType("text/html;charset=" + ENCODING);
		PrintWriter out = response.getWriter();
		out.println(xmlStr);
		out.close();		
	}
	
	
	/**
	 * 输出XML信息
	 * 
	 * @param response
	 * @param xmlStr
	 * @throws IOException
	 */
	public static void outputXML(HttpServletResponse response, int xmlStr)
			throws IOException {
		response.setContentType("text/html;charset=" + ENCODING);
		PrintWriter out = response.getWriter();
		out.println(xmlStr);
		out.close();		
	}
	
	
	/**
	 * 输出XML信息
	 * 
	 * @param response
	 * @param xmlStr
	 * @throws IOException
	 */
	public static void outputXML(HttpServletResponse response, String xmlStr,String contentType)
			throws IOException {
		response.setContentType("text/html;charset=" + contentType);
		PrintWriter out = response.getWriter();
		out.println(xmlStr);
		out.close();		
	}
}
