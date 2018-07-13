package com.shop.order.action;

import com.common.util.DateUtil;
import com.common.util.ImitateUUID;
import com.common.util.ServletUtil;
import com.common.util.date.DatePaltUtil;
import com.common.util.string.StringUtil;
import com.pub.menber.model.dto.MenberAddrDTO;
import com.pub.menber.model.dto.MenberDTO;
import com.pub.menber.model.dto.MenberPointDTO;
import com.pub.menber.model.facade.MenberAddrFacade;
import com.pub.menber.model.facade.MenberFacade;
import com.pub.menber.model.facade.MenberPointFacade;
import com.shop.good.model.dto.GoodCategoryDTO;
import com.shop.good.model.facade.GoodCategoryFacade;
import com.shop.order.model.dto.OrderDTO;
import com.shop.order.model.facade.OrderFacade;
import com.shop.order.model.facade.OrderItemFacade;
import com.shop.order.model.facade.ShopCarFacade;
import com.sinovatech.common.config.GlobalConfig;
import com.sinovatech.common.util.Validate;
import com.sinovatech.common.web.action.ActionConstent;
import com.sinovatech.common.web.action.BaseAdmAction;
import com.sinovatech.common.web.action.CommonMapping;
import com.sinovatech.common.web.limit.ExLimitUtil;
import com.sinovatech.common.web.limit.ILimitUtil;
import com.sinovatech.common.web.limit.LimitInfo;
import net.sf.json.JSONObject;
import org.apache.log4j.Logger;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * 订单管理
 * 
 * @author kevin(keepwork512@163.com)
 * @date Dec 30, 2015 9:55:43 PM
 */
public class OrderAction extends BaseAdmAction
{ 
	private SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	private static Logger log = Logger.getLogger(OrderAction.class);
	
	private OrderFacade myOrderFacade;
	private MenberFacade myMenberFacade;
	private GoodCategoryFacade myGoodCategoryFacade;
	private OrderItemFacade myOrderItemFacade;
//	private ShopCarFacade myShopCarFacade;
	private MenberAddrFacade myMenberAddrFacade;
	private MenberPointFacade myMenberPointFacade;
	
	public void init(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception
	{
		this.myOrderFacade = (OrderFacade) this.getBeanContext().getBean("myOrderFacade");
		this.myMenberFacade = (MenberFacade) this.getBeanContext().getBean("myMenberFacade");
		this.myGoodCategoryFacade = (GoodCategoryFacade) this.getBeanContext().getBean("myGoodCategoryFacade");
		this.myOrderItemFacade = (OrderItemFacade) this.getBeanContext().getBean("myOrderItemFacade");
//		this.myShopCarFacade = (ShopCarFacade) this.getBeanContext().getBean("myShopCarFacade");
		this.myMenberAddrFacade = (MenberAddrFacade) this.getBeanContext().getBean("myMenberAddrFacade");
		this.myMenberPointFacade = (MenberPointFacade) this.getBeanContext().getBean("myMenberPointFacade");
	}
	
	/**
	 * 订单填写 - 前台
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward orderWrite(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception
    {
		String type = request.getParameter("type");//类型：wap,web
		String serviceType = request.getParameter("serviceType");
    	String firstCateCode = request.getParameter("firstCateCode");
//    	String secondCateCode = request.getParameter("secondCateCode");
    	request.setAttribute("serviceType", serviceType);
    	System.out.println("========================== serviceType:"+serviceType+",firstCateCode:"+firstCateCode);
    	
		String returnPage = "";
		MenberDTO menber = null;
		if(type.equals("wap")){
			menber = (MenberDTO)request.getSession().getAttribute("wxmenber");
			returnPage = "orderWrite_wap";
			request.setAttribute("pageTitle", "填写订单");
			
			GoodCategoryDTO firstCate = myGoodCategoryFacade.get(firstCateCode);
//			GoodCategoryDTO secondCate = myGoodCategoryFacade.get(secondCateCode);
			request.setAttribute("firstCate", firstCate);
//	    	request.setAttribute("secondCate", secondCate);
    	}
		
        if(null != menber)
        {
        	// 查询出该用户所保存的常用地址信息，页面
            List<MenberAddrDTO> addressList = myMenberAddrFacade.listByMenId(menber.getId());
            request.setAttribute("addressList", addressList);
        }
        
    	request.getSession().setAttribute("saveOrderStatus", "true");//防止重复提交
    	return mapping.findForward(returnPage);
        
    }

	/**
	 * 提交订单前检查是否满足条件
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward checkOrder(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception
	{
		String result = "0";
		String type = request.getParameter("type");//类型：wap,web
		MenberDTO menber = null;
		if(type.equals("wap")){
			menber = (MenberDTO)request.getSession().getAttribute("wxmenber");
			
    	}
        if(null != menber)
        {
        	
        }else{
        	result = "4";//请先登录！
        }
        try{
            ServletUtil.outputXML(response, result);
        }catch (IOException e){
            e.printStackTrace();
        }
		return null;
    }
	
	
	/**
	 * 保存订单 前台
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
    public ActionForward saveOrder(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception
	{
    	String type = request.getParameter("type");//类型：wap,web
		String returnPage = "";
		MenberDTO menber = null;
		if(type.equals("wap")){
			menber = (MenberDTO)request.getSession().getAttribute("wxmenber");
			returnPage = "orderResult_wap";
			request.setAttribute("pageTitle", "订单结果");
    	}else if(type.equals("web")){
    		
    	}
        if(null != menber)
        {
        	String saveOrderStatus = (String)request.getSession().getAttribute("saveOrderStatus");
        	if(null != saveOrderStatus && saveOrderStatus.equals("true"))//防止重复提交
        	{
        		request.getSession().setAttribute("saveOrderStatus", "false");
            	String addressId = request.getParameter("addressId");
            	String serviceType = request.getParameter("serviceType");
            	String firstCateCode = request.getParameter("fcCode");
            	String firstCateName = request.getParameter("fcName");
//            	String secondCateCode = request.getParameter("scCode");
//            	String secondCateName = request.getParameter("scName");
            	String orderDesc = request.getParameter("orderDesc");
            	
            	OrderDTO order = new OrderDTO();
                order.setOpenId(menber.getOpenId());

				ImitateUUID uuid = new ImitateUUID();
                order.setOrderSn(uuid.getID());
                order.setMenId(menber.getId());
                order.setUserId("");// 会员所对应的业务员
                order.setOrderStatus("1");// 订单状态，默认为进行中
                order.setShippingStatus("0");// 邮递状态，默认为未配送
                order.setAmount(0);
                order.setOrderType("1");
            	order.setTotalPrice(new BigDecimal(0.00));
                order.setTotalPoint(0);
                order.setOrderDesc(orderDesc);
                
                if(serviceType.equals("1")){
                	String payType = request.getParameter("payType");
                	order.setPayType(payType);
                }
                
                order.setOrderTime(new Timestamp(System.currentTimeMillis()));// 下单时间
                if(!addressId.equals("")){
                	order.setAddrId(addressId);//地址ID
                }
                
                order.setServiceType(serviceType);
                order.setFirstCate(firstCateCode);
                order.setFirstCateName(firstCateName);
//                order.setSecondCate(secondCateCode);
//                order.setSecondCateName(secondCateName);
                String orderId = myOrderFacade.save(order);//保存订单
                if(null != orderId)
                {
                	MenberAddrDTO addr = myMenberAddrFacade.get(addressId);
                	//将订单消息发送给所有安装工列队
                	myOrderFacade.sendBaiduMessageAndroid(order,addr.getStreet());
                	myOrderFacade.sendBaiduMessageIOS(order,addr.getStreet());
                }
               
                return mapping.findForward("orderResult_wap");
                
        	}else{
        		String serverDomain = GlobalConfig.getProperty("filePath", "prefix");
        		response.sendRedirect(serverDomain+"/index.jsp");
        	}
        }
    	
        return null;
    }
    
    
    
    
    /**
     * 订单完成
     * @param request
     * @return
     */
    public ActionForward orderFilish(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception
	{
    	return mapping.findForward("orderFilish");
	}



	/**
	 * 我的订单列表初始化 - 前台
	 *
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward myOrderList(ActionMapping mapping,
									 ActionForm form, HttpServletRequest request,
									 HttpServletResponse response) throws Exception
	{
		String type = request.getParameter("type");//类型：wap,web
		String returnPage = "";
		MenberDTO menber = null;
		if(type.equals("wap")){
			menber = (MenberDTO)request.getSession().getAttribute("wxmenber");
			returnPage = "myOrderList_wap";
			String userType = menber.getType()+"";
			if("2".equals(userType)){//2:安装工
				returnPage = "workerOrderList_wap";
			}
			request.setAttribute("pageTitle", "我的订单列表");
		}else if(type.equals("web")){

		}
		if(null != menber)
		{
			request.setAttribute("status", "9");
			return mapping.findForward(returnPage);
		}
		return null;

	}
    
    
    
    /**
     * 我的订单列表（分页模式） - 前台
     * 
     * @param mapping
     * @param form
     * @param req
     * @param response
     * @return
     * @throws Exception
     */
	public ActionForward listOrdersForPagination(ActionMapping mapping,
												 ActionForm form, HttpServletRequest req,
												 HttpServletResponse response) throws Exception
	{
		Map<String, String> params = new HashMap<String, String>();
		String status = req.getParameter("status");
		req.setAttribute("status", status);
		params.put("status", status);

		MenberDTO menber = null;
		String type = req.getParameter("type");
		if(type.equals("wap")){
			menber = (MenberDTO)req.getSession().getAttribute("wxmenber");
			String userType = menber.getType()+"";
			if("1".equals(userType)){//1:微信客户2：安装工
				params.put("menId", menber.getId());
			}else{
				params.put("workerId", menber.getId());
			}
		}else if(type.equals("web")){
			menber = (MenberDTO)req.getSession().getAttribute("pcmenber");
		}


//    	if(orderType.equals("1")){//进行中
//    		params.put("orderStatus", "1");
//    	}else if(orderType.equals("2")){//已完成
//    		params.put("orderStatus", "2");
//    	}else if(orderType.equals("3")){//已关闭
//    		params.put("orderStatus", "4");
//    	}else if(orderType.equals("9") || orderType.equals("")  || null == orderType){//全部订单
//    		String orderStatus = req.getParameter("orderStatus");
//    		if(null != orderStatus && !orderStatus.equals("")){
//    			params.put("orderStatus", orderStatus);
//    		}
//    		String payStatus = req.getParameter("payStatus");
//    		if(null != payStatus && !payStatus.equals("")){
//    			params.put("payStatus", payStatus);
//    		}
//    		String payType = req.getParameter("payType");
//    		if(null != payType && !payType.equals("")){
//    			params.put("payType", payType);
//    		}
//    		String startTime = req.getParameter("startTime");
//    		if(null != startTime && !startTime.equals("")){
//    			params.put("startTime", startTime);
//    		}
//    		String endTime = req.getParameter("endTime");
//    		if(null != endTime && !endTime.equals("")){
//    			params.put("endTime", endTime);
//    		}
//    	}

//        String orderId = req.getParameter("orderId");
//        if (orderId != null && !orderId.equals(""))
//        {
//        	params.put("orderId", orderId);
//        }
//
//        String orderSn = req.getParameter("orderSn");
//        if (orderSn != null && !orderSn.equals(""))
//        {
//        	params.put("orderSn", orderSn);
//        }

		StringBuilder sb = new StringBuilder();
		int i = 0;
		String size = req.getParameter("pageCount") == null ? "20" : req.getParameter("pageCount").trim();
		String start = req.getParameter("page") == null ? "0" : req.getParameter("page").trim();
		int startTemp = (start == null || start.isEmpty() ? '0' : Integer.parseInt(start))
				* (size == null || size.isEmpty() ? '0' : Integer.parseInt(size));
		params.put("start", startTemp + "");
		params.put("size", size);

		@SuppressWarnings("unchecked")
		List<OrderDTO> list = (List<OrderDTO>)myOrderFacade.listForPagination(params).get("list");
		int count = (Integer)myOrderFacade.listForPagination(params).get("rows");
		sb.append("{\"pageCount\":\"" + count + "\",\"pageData\":[");

		for (OrderDTO order : list)
		{
			if (i > 0)
			{
				sb.append(",");
			}
			sb.append("{");
			sb.append("\"orderId\":\"" + order.getOrderId() + "\",");
			sb.append("\"orderSn\":\"" + order.getOrderSn() + "\",");
			sb.append("\"orderType\":\"" + order.getOrderType() + "\",");
			sb.append("\"totalPoint\":\"" + order.getTotalPoint() + "\",");
			sb.append("\"totalPrice\":\"" + order.getTotalPrice() + "\",");
			sb.append("\"orderStatus\":\"" + order.getOrderStatus() + "\",");
			sb.append("\"payStatus\":\"" + order.getPayStatus() + "\",");
			sb.append("\"payType\":\"" + order.getPayType() + "\",");
			sb.append("\"shippingStatus\":\"" + order.getShippingStatus() + "\",");
			sb.append("\"serviceType\":\"" + order.getServiceType() + "\",");
			sb.append("\"firstCateName\":\"" + order.getFirstCateName() + "\",");
//            sb.append("\"secondCateName\":\"" + order.getSecondCateName() + "\",");
			sb.append("\"orderTime\":\"" + DateUtil.date2string(order.getOrderTime(), "yyyy-MM-dd HH:mm:ss") + "\",");
			if(order.getOrderStatus().equals("2")) {//已派单
				sb.append("\"takeTime\":\"" + DateUtil.date2string(order.getTakeTime(), "yyyy-MM-dd HH:mm:ss") + "\"");
			}else if(order.getOrderStatus().equals("3")){//已确定时间
				sb.append("\"sureTime\":\"" + DateUtil.date2string(order.getSureTime(), "yyyy-MM-dd HH:mm:ss") + "\"");
			}else if(order.getOrderStatus().equals("4") || order.getOrderStatus().equals("5")) {//已完成或已关闭
				sb.append("\"endTime\":\"" + DateUtil.date2string(order.getEndTime(), "yyyy-MM-dd HH:mm:ss") + "\"");
			}
			sb.append("}");
			i++;

		}
		sb.append("],\"status\":\"" + status + "\"}");
		try
		{
			ServletUtil.outputXML(response, sb.toString());
		}
		catch (IOException e)
		{
			e.printStackTrace();
		}
		return null;
	}
    
    
    /**
     * 订单详情查看
     * @param request
     * @return
     */
    public ActionForward myOrderView(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception
	{
    	String type = request.getParameter("type");//类型：wap,web
		String returnPage = "";
		MenberDTO menber = null;
		if(type.equals("wap")){
			menber = (MenberDTO)request.getSession().getAttribute("wxmenber");
			returnPage = "myOrderView_wap";
			request.setAttribute("pageTitle", "订单明细");
    	}else if(type.equals("web")){
    	}
        if(null != menber)
        {
        	String orderId = request.getParameter("orderId");
        	OrderDTO order = myOrderFacade.get(orderId);
        	order.setOrderTimeStr(format.format(order.getOrderTime()));
        	if(null!=order.getSureTime()){
        		order.setSureTimeStr(format.format(order.getSureTime()));
        	}
        	request.setAttribute("order", order);
//        	if(null!=order.getExpTime()){
//        		request.setAttribute("expTimeStr", format.format(order.getExpTime()));
//        	}else{
//        		request.setAttribute("expTimeStr", "");
//        	}
        	
//    		List<OrderItemDTO> itemList = myOrderItemFacade.listByOrderId(orderId);
//        	request.setAttribute("itemList", itemList);
        	
        	MenberDTO worker = myMenberFacade.get(order.getWorkerId());
        	request.setAttribute("worker", worker);
        	
        	MenberAddrDTO address = myMenberAddrFacade.get(order.getAddrId());
        	request.setAttribute("address", address);
        	
        	GoodCategoryDTO firstCate = myGoodCategoryFacade.get(order.getFirstCate());
//			GoodCategoryDTO secondCate = myGoodCategoryFacade.get(order.getSecondCate());
			request.setAttribute("firstCateName", firstCate.getName());
//	    	request.setAttribute("secondCateName", secondCate.getName());
        	
    		return mapping.findForward(returnPage);
        }
    	return null;
	}
    
    
    
    /**
     * 修改订单状态
     * 
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    public ActionForward updateOrderStatus(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception
	{
        String orderId = request.getParameter("orderId");
        String orderStatus = request.getParameter("status");
        OrderDTO order = myOrderFacade.get(orderId);
        order.setOrderStatus(orderStatus);
        if(orderStatus.equals("3")){//已完成
        	order.setShippingStatus("2");
        }
        myOrderFacade.update(order);
        try
        {
            JSONObject json = new JSONObject();
            ServletUtil.outputXML(response, json.toString());
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
		return null;
    }
    
    
    /**
     * 修改支付状态
     * 
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    public ActionForward updatePayStatus(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception
	{
        String orderId = request.getParameter("orderId");
        String payStatus = request.getParameter("status");
        
        OrderDTO order = myOrderFacade.get(orderId);
        order.setOrderStatus("0");
        order.setPayStatus(payStatus);
        myOrderFacade.update(order);
        try
        {
            JSONObject json = new JSONObject();
            ServletUtil.outputXML(response, json.toString());
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
		return null;
    }
    
    /**
     * 修改配送状态
     * 
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    public ActionForward updateShippingStatus(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception
	{
        String orderId = request.getParameter("orderId");
        String shippingStatus = request.getParameter("status");
        
        OrderDTO order = myOrderFacade.get(orderId);
        order.setShippingStatus(shippingStatus);
        order.setOrderStatus("2");//修改订单状态为已发货
        myOrderFacade.update(order);
        
        MenberDTO menber = myMenberFacade.get(order.getMenId());
		if(null!=menber){
			//购买成功后立即赠送5%的积分
			int orderPoint = Integer.parseInt(new java.text.DecimalFormat("0").format(order.getTotalPrice().doubleValue()*0.05));
			int point = menber.getPoint() + orderPoint;
			menber.setPoint(point);
			myMenberFacade.update(menber);
			//保存积分历史
	        MenberPointDTO p = new MenberPointDTO();
			p.setMenId(order.getMenId());
			p.setPoint(orderPoint);
			p.setPointDesc("购物送积分");
			p.setCreateTime(new Date());
			myMenberPointFacade.save(p);
		}
        try
        {
            JSONObject json = new JSONObject();
            ServletUtil.outputXML(response, json.toString());
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
		return null;
    }
    
    
    /**
     * 录入快递信息 - 初始化
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
	public ActionForward addExpInfoInit(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception
	{
		String orderId = request.getParameter("orderId");
		OrderDTO order = myOrderFacade.get(orderId);
		request.setAttribute("order", order);
		if(null != order.getExpTime()){
			request.setAttribute("expTimeStr", format.format(order.getExpTime()));
		}else{
			request.setAttribute("expTimeStr", "");
		}
		return mapping.findForward("addExpInfo");
	}
	/**
     * 录入快递信息
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
	public ActionForward addExpInfo(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception
	{
		String orderId = request.getParameter("orderId");
		String expName = request.getParameter("expName");
		String expNumber = request.getParameter("expNumber");
		
		OrderDTO order = myOrderFacade.get(orderId);
        order.setExpName(expName);
        order.setExpNumber(expNumber);
        if(null == order.getExpTime()){
        	order.setExpTime(new Date());
        }
        myOrderFacade.update(order);
		
		CommonMapping mping = new CommonMapping("保存成功!", getRealUri(mapping,"order/queryList"), ActionConstent.ALERT);
		request.setAttribute("mping", mping);
		return mapping.findForward(ActionConstent.COMMON_MAPPING);
	}


	/**
	 * 工人端订单确认时间修改
	 *
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward sureTimeUpdate(ActionMapping mapping,
				ActionForm form, HttpServletRequest request,
				HttpServletResponse response) throws Exception
	{
		String orderId = request.getParameter("orderId");
		String sureTime = request.getParameter("sureTimeStr");

		OrderDTO order = myOrderFacade.get(orderId);
		order.setSureTime(DateUtil.string2date(sureTime,"yyyy-MM-dd HH:mm:ss"));
		order.setOrderStatus("3");//3待上门
		myOrderFacade.update(order);
		try
		{
			PrintWriter out = response.getWriter();
			out.print("success");
			out.close();
		}
		catch (IOException e)
		{
			e.printStackTrace();
			PrintWriter out = response.getWriter();
			out.print("error");
			out.close();
		}
		return null;
	}

	/**
	 * 工人端订单详情查看
	 * @param request
	 * @return
	 */
	public ActionForward workerOrderView(ActionMapping mapping,
										 ActionForm form, HttpServletRequest request,
										 HttpServletResponse response) throws Exception
	{
		String type = request.getParameter("type");//类型：wap,web
		String returnPage = "";
		MenberDTO menber = null;
		if(type.equals("wap")){
			menber = (MenberDTO)request.getSession().getAttribute("wxmenber");
			returnPage = "workerOrderView_wap";
			request.setAttribute("pageTitle", "订单明细");
		}else if(type.equals("web")){
		}
		if(null != menber)
		{
			String orderId = request.getParameter("orderId");
			OrderDTO order = myOrderFacade.get(orderId);
			order.setOrderTimeStr(format.format(order.getOrderTime()));
			if(null!=order.getSureTime()){//确认时间
				order.setSureTimeStr(format.format(order.getSureTime()));
			}
			if(null!=order.getEndTime()){//完成时间
				order.setEndTimeStr(format.format(order.getEndTime()));
			}
			if(null!=order.getTakeTime()){//接单时间
				order.setTakeTimeStr(format.format(order.getTakeTime()));
			}
			request.setAttribute("order", order);

			MenberAddrDTO address = myMenberAddrFacade.get(order.getAddrId());
			request.setAttribute("address", address);

			GoodCategoryDTO firstCate = myGoodCategoryFacade.get(order.getFirstCate());
//			GoodCategoryDTO secondCate = myGoodCategoryFacade.get(order.getSecondCate());
			request.setAttribute("firstCateName", firstCate.getName());
//	    	request.setAttribute("secondCateName", secondCate.getName());

			return mapping.findForward(returnPage);
		}
		return null;
	}
    

}