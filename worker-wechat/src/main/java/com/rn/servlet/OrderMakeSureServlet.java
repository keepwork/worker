package com.rn.servlet;

import java.io.IOException;
import java.text.SimpleDateFormat;
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
 * 安装工确认上门时间接口
 * 
 * @author chenxin   keepwork512@163.com
 * @date 2016-07-26
 */
public class OrderMakeSureServlet extends HttpServlet {

	private static Log log = LogFactory.getLog(OrderMakeSureServlet.class);
	private SimpleDateFormat format = new SimpleDateFormat("yyyyMMddHHmmss");
	private SimpleDateFormat format2 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
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
		log.info("安装工确认上门时间接口....................................................MakeSureTimeServlet");
		response.setContentType("text/html;charset=UTF-8"); 
		myOrderFacade = (OrderFacade) SpringBeanHelper.getBeanContext().getBean("myOrderFacade");
		String result = "";
		
		String orderId = request.getParameter("orderId");
		String serviceTime = request.getParameter("serviceTime");
		log.info("安装工确认上门时间接口....................................................orderId:"+orderId+",serviceTime:"+serviceTime);
		
		if(null==orderId || orderId.equals("")){
			result = "1001";//订单id不能为空
		}
		if(null==serviceTime || serviceTime.equals("")){
			result = "1002";//上门时间不能为空
		}
		
		try {
			if(null!=orderId && !orderId.equals("") && null!=serviceTime && !serviceTime.equals(""))
			{
				OrderDTO order = myOrderFacade.get(orderId);
				if(order.getOrderStatus().equals("4")){
					result = "1003";//订单已经是已完成状态
				}else if(order.getOrderStatus().equals("5")){
					result = "1004";//订单已经是已取消状态
				}else{
					Date st = format.parse(serviceTime);
					String st2 = format2.format(st);
					order.setSureTime(format2.parse(st2));
					order.setOrderStatus("3");
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
