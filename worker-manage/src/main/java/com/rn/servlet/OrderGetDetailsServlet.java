package com.rn.servlet;

import java.io.IOException;
import java.text.SimpleDateFormat;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import net.sf.json.JSONObject;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import com.pub.menber.model.dto.MenberAddrDTO;
import com.pub.menber.model.dto.MenberDTO;
import com.pub.menber.model.facade.MenberAddrFacade;
import com.pub.menber.model.facade.MenberFacade;
import com.rn.dto.Order;
import com.shop.good.model.dto.GoodCategoryDTO;
import com.shop.good.model.facade.GoodCategoryFacade;
import com.shop.order.model.dto.OrderDTO;
import com.shop.order.model.facade.OrderFacade;
import com.sinovatech.common.config.GlobalConfig;
import com.sinovatech.common.exception.AppException;
import com.sinovatech.framework.common.helper.SpringBeanHelper;


/**
 * 获取订单详情
 * 
 * @author chenxin   keepwork512@163.com
 * @date 2016-07-24
 */
public class OrderGetDetailsServlet extends HttpServlet {

	private static Log log = LogFactory.getLog(OrderGetDetailsServlet.class);
	private SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	private OrderFacade myOrderFacade;
	private MenberFacade myMenberFacade;
	private MenberAddrFacade myMenberAddrFacade;
	private GoodCategoryFacade myGoodCategoryFacade;
	
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
		log.info("获取订单详情....................................................OrderGetDetailsServlet");
		response.setContentType("text/html;charset=UTF-8"); 
		myOrderFacade = (OrderFacade) SpringBeanHelper.getBeanContext().getBean("myOrderFacade");
		myMenberFacade = (MenberFacade) SpringBeanHelper.getBeanContext().getBean("myMenberFacade");
		myMenberAddrFacade = (MenberAddrFacade) SpringBeanHelper.getBeanContext().getBean("myMenberAddrFacade");
		myGoodCategoryFacade = (GoodCategoryFacade) SpringBeanHelper.getBeanContext().getBean("myGoodCategoryFacade");
		String result = "";
		
		String orderId = request.getParameter("orderId");
		if(null==orderId || orderId.equals("")){
			result = "1001";//订单id不能为空
		}
		log.info("获取订单详情....................................................orderId:"+orderId);
		
		try {
			if(null!=orderId || !orderId.equals(""))
			{
				OrderDTO o = myOrderFacade.get(orderId);
				if(null != o){
					Order order = transList(o);
					JSONObject json = JSONObject.fromObject(order);
					response.getWriter().print(json);
				}else{
					response.getWriter().print("1111");//无数据
				}
			}else{
				response.getWriter().print(result);
			}
			
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
	
	
	@SuppressWarnings("unchecked")
	public Order transList(OrderDTO d) throws AppException{
		Order g = new Order();
		g.setOrderId(d.getOrderId());
		g.setOrderSn(d.getOrderSn());
		if(null!=d.getOrderTime()){
			g.setOrderTime(format.format(d.getOrderTime()));
		}
		if(null!=d.getTakeTime()){
			g.setOrderTakeTime(format.format(d.getTakeTime()));
		}
		if(null!=d.getEndTime()){
			g.setEndTime(format.format(d.getEndTime()));
		}
		if(null!=d.getSureTime()){
			g.setSureTime(format.format(d.getSureTime()));
		}
		
		g.setOrderDesc(d.getOrderDesc());
		g.setDesc1(d.getDesc1());
		g.setDesc2(d.getDesc2());
		
		g.setOrderStatus(d.getOrderStatus());
		g.setPayStatus(d.getPayStatus());
		if(null != d.getTotalPrice()){
			g.setOrderPrice(d.getTotalPrice().toString());
		}
		
		if(null != d.getWorkerId() && !d.getWorkerId().equals("")){
			MenberDTO menber = myMenberFacade.get(d.getWorkerId());
			g.setWorkerId(d.getWorkerId());
			g.setWorkerName(menber.getRealName());
			g.setWorkerMobile(menber.getMobile());
			MenberAddrDTO addr = (MenberAddrDTO) myMenberAddrFacade.get(d.getAddrId());
			g.setWorkerAddr(addr.getProvince()+addr.getCity()+addr.getCounty()+addr.getStreet());
		}
		
		g.setServiceType(d.getServiceType());
		GoodCategoryDTO f = myGoodCategoryFacade.get(d.getFirstCate());
		g.setFirstCateName(f.getName());
		GoodCategoryDTO s = myGoodCategoryFacade.get(d.getSecondCate());
		g.setSecondCateName(s.getName());
		
		String domain = GlobalConfig.getProperty("weixin", "server.domain");
	    String service_image = "http://"+domain+"/wap/images/service/";
	    if(d.getServiceType().equals("1")){
	    	service_image += "az.png";
	    }else if(d.getServiceType().equals("2")){
	    	service_image += "wx.png";
	    }else if(d.getServiceType().equals("3")){
	    	service_image += "by.png";
	    }else if(d.getServiceType().equals("4")){
	    	service_image += "cl.png";
	    }else if(d.getServiceType().equals("5")){
	    	service_image += "zx.png";
	    }
	    g.setServiceImage(service_image);
		    
		g.setResultCode("0000");
		return g;
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
