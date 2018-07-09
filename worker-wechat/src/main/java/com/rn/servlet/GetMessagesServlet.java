package com.rn.servlet;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import net.sf.json.JSONArray;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.pub.message.model.dto.MessageCategoryDTO;
import com.pub.message.model.dto.MessageDTO;
import com.pub.message.model.facade.MessageFacade;
import com.sinovatech.framework.common.helper.SpringBeanHelper;


/**
 * 根据消息分类获取消息列表
 * 
 * @author chenxin   keepwork512@163.com
 * @date 2015-06-28
 */
public class GetMessagesServlet extends HttpServlet {

	private static Log log = LogFactory.getLog(GetMessagesServlet.class);
	private MessageFacade myMessageFacade;
	private SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
    
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
	@SuppressWarnings("unchecked")
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		log.info("获取消息列表....................................................GetMessagesServlet");
		response.setContentType("text/html;charset=UTF-8"); 
		myMessageFacade = (MessageFacade) SpringBeanHelper.getBeanContext().getBean("myMessageFacade");
		JSONArray jsonStr = null;
		
		String cateCode = request.getParameter("cateCode");
		log.info("获取消息列表....................................................cateCode:"+cateCode);
		try {
			List<MessageDTO> list = myMessageFacade.listByCateCode(cateCode);
			log.info("获取消息列表....................................................list:"+list.size());
			if(list.size()>0){
				List<MessageDTO> mList = new ArrayList<MessageDTO>(); 
				for (MessageDTO cat : list) {
					cat.setUpdateTimeStr(format.format(cat.getUpdateTime()));
					mList.add(cat);
				}
				jsonStr = JSONArray.fromObject(mList);
			}else{
				MessageDTO ww = new MessageDTO();
				ww.setResultCode("1111");//无数据
				jsonStr = JSONArray.fromObject(ww);
			}
			response.getWriter().print(jsonStr);
			response.getWriter().flush();
			response.getWriter().close();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			MessageDTO ww = new MessageDTO();
			ww.setResultCode("9999");//系统异常
			jsonStr = JSONArray.fromObject(ww);
			response.getWriter().print(jsonStr);
			response.getWriter().flush();
			response.getWriter().close();
			e.printStackTrace();
		}
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
