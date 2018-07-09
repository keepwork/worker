package com.rn.servlet;

import java.io.IOException;
import java.util.Date;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import com.shop.order.model.dto.OrderDTO;
import com.shop.order.model.facade.OrderFacade;
import com.sinovatech.framework.common.helper.SpringBeanHelper;


/**
 * 订单完成（安装）
 * 
 * @author chenxin   keepwork512@163.com
 * @date 2016-07-26
 */
public class WorkInstallServlet extends HttpServlet {

	private static Log log = LogFactory.getLog(WorkInstallServlet.class);
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
	@SuppressWarnings("unchecked")
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		log.info("订单完成（安装）....................................................OrderWorkFinishServlet");
		response.setContentType("text/html;charset=UTF-8"); 
		myOrderFacade = (OrderFacade) SpringBeanHelper.getBeanContext().getBean("myOrderFacade");
		String result = "";
		
		String orderId = request.getParameter("orderId");
		String measures = request.getParameter("measures");
		String desc = request.getParameter("desc");
		log.info("订单完成（安装）....................................................orderId:"+orderId+",measures:"+measures+",desc:"+desc);
		
		if(null==orderId || orderId.equals("")){
			result = "1001";//订单id不能为空
		}
		
		try {
			if(null!=orderId && !orderId.equals(""))
			{
				OrderDTO order = myOrderFacade.get(orderId);
				if(!order.getOrderStatus().equals("3")){
					log.info("订单完成（安装）....................................................getOrderStatus:"+order.getOrderStatus());
					result = "1002";//订单不处于已确定上门时间的状态
				}else{
					order.setEndTime(new Date());
					order.setOrderStatus("4");
					order.setDesc1(measures);
					order.setDesc2(desc);
					myOrderFacade.update(order);
					result = "0000";
				}
			}
			
			response.getWriter().print(result);
			response.getWriter().flush();
			response.getWriter().close();
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			response.getWriter().print("9999");//系统异常
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
