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
import com.shop.appraise.model.dto.AppraiseDTO;
import com.shop.appraise.model.facade.AppraiseFacade;
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
import com.sinovatech.hd.tools.cache.CacheFactory;
import com.sinovatech.hd.tools.cache.ICache;
import com.weixin.util.WeixinUtil;
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
	private AppraiseFacade myAppraiseFacade;
	private ICache cache = CacheFactory.newCache();
	
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
		this.myAppraiseFacade = (AppraiseFacade) this.getBeanContext().getBean("myAppraiseFacade");
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
            	String payType = request.getParameter("payType");

            	OrderDTO order = new OrderDTO();
                order.setOpenId(menber.getOpenId());

				ImitateUUID uuid = new ImitateUUID();
                order.setOrderSn(uuid.getID());
                order.setMenId(menber.getId());
                order.setUserId("");// 会员所对应的业务员
                order.setOrderStatus("1");// 订单状态，默认为进行中
                order.setShippingStatus("0");// 邮递状态，默认为未配送
				order.setPayStatus("0");//支付状态，默认为未支付
                order.setAmount(0);
                order.setOrderType("1");
            	order.setTotalPrice(new BigDecimal(0.00));
                order.setTotalPoint(0);
                order.setOrderDesc(orderDesc);
                order.setPayType(payType);

//                if(serviceType.equals("1")){
//                	String payType = request.getParameter("payType");
//                	order.setPayType(payType);
//                }
                
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
//                	MenberAddrDTO addr = myMenberAddrFacade.get(addressId);
//                	//将订单消息发送给所有安装工列队
//                	myOrderFacade.sendBaiduMessageAndroid(order,addr.getStreet());
//                	myOrderFacade.sendBaiduMessageIOS(order,addr.getStreet());
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
			sb.append("\"desc1\":\"" + order.getDesc1() + "\",");
			sb.append("\"desc2\":\"" + order.getDesc2() + "\",");
			sb.append("\"desc2\":\"" + order.getDesc2() + "\",");
			if(menber.getType().equals("1") && (order.getOrderStatus().equals("2")
					|| order.getOrderStatus().equals("3"))){//师傅端订单列表待确认时间和已确认时间状态时需联系业主
				MenberDTO menberDTO = myMenberFacade.get(order.getMenId());
				sb.append("\"mobile\":\"" + menber.getMobile() + "\",");
			}
//            sb.append("\"secondCateName\":\"" + order.getSecondCateName() + "\",");
			sb.append("\"orderTime\":\"" + DateUtil.date2string(order.getOrderTime(), "yyyy-MM-dd HH:mm:ss") + "\"}");
//			if(order.getOrderStatus().equals("2")) {//已派单
//				sb.append(",\"takeTime\":\"" + DateUtil.date2string(order.getTakeTime(), "yyyy-MM-dd HH:mm:ss") + "\"");
//			}else if(order.getOrderStatus().equals("3")){//已确定时间
//				sb.append(",\"sureTime\":\"" + DateUtil.date2string(order.getSureTime(), "yyyy-MM-dd HH:mm:ss") + "\"");
//			}else if(order.getOrderStatus().equals("4") || order.getOrderStatus().equals("5")) {//已完成或已关闭
//				sb.append(",\"endTime\":\"" + DateUtil.date2string(order.getEndTime(), "yyyy-MM-dd HH:mm:ss") + "\"");
//			}
//			sb.append("}");
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
     * 订单详情查看(客户端)
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
			if(null!=order.getTakeTime()){
				order.setTakeTimeStr(format.format(order.getTakeTime()));
			}
        	if(null!=order.getSureTime()){
        		order.setSureTimeStr(format.format(order.getSureTime()));
        	}
			if(null!=order.getActualTime()){
				order.setActualTimeStr(format.format(order.getActualTime()));
			}
			if(null!=order.getFinishTime()){
				order.setFinishTimeStr(format.format(order.getFinishTime()));
			}
			if(null!=order.getPayTime()){
				order.setPayTimeStr(format.format(order.getPayTime()));
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

        	int totalOrderNum = myOrderFacade.getWorkerOrderTotalNum(order.getWorkerId());
			request.setAttribute("totalOrderNum", totalOrderNum);//订单总数

			String positiveAppraiseRate = myAppraiseFacade.getAppraiseRate(order.getWorkerId(),"1");
			request.setAttribute("positiveAppraiseRate", positiveAppraiseRate);//好评率


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
	 * 客户端师傅已上门状态更新
	 *
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward sureArriveHomeUpdateStatus(ActionMapping mapping,
										ActionForm form, HttpServletRequest request,
										HttpServletResponse response) throws Exception
	{
		String orderId = request.getParameter("orderId");

		OrderDTO order = myOrderFacade.get(orderId);
		order.setActualTime(new Date());
		order.setOrderStatus("4");//4已上门
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
		}
		return null;
	}

	/**
	 * 客户端取消订单
	 *
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward cancelOrder(ActionMapping mapping,
				ActionForm form, HttpServletRequest request,
				HttpServletResponse response) throws Exception
	{
		String orderId = request.getParameter("orderId");
		MenberDTO menber = (MenberDTO)request.getSession().getAttribute("wxmenber");
        if(null != menber){
			OrderDTO order = myOrderFacade.get(orderId);
			order.setOrderStatus("8");//8取消订单
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
			}
		}
		return null;
	}

	/**
	 * 开始施工
	 *
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward startWorkUpdateStatus(ActionMapping mapping,
					ActionForm form, HttpServletRequest request,
					HttpServletResponse response) throws Exception
	{
		String orderId = request.getParameter("orderId");
		String result = "success";
		OrderDTO order = myOrderFacade.get(orderId);
		if(order.getPayType().equals("2") && null == order.getPayTime1()){//订单为分期支付类型时，开始施工前必须先支付定金
			result = "unPay1";//定金未支付
		}else{
			order.setStartTime(new Date());
			order.setOrderStatus("5");//5已开始施工
			myOrderFacade.update(order);
		}
		try
		{
			PrintWriter out = response.getWriter();
			out.print(result);
			out.close();
		}
		catch (IOException e)
		{
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * 完成施工
	 *
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward finishWorkUpdateStatus(ActionMapping mapping,
			   ActionForm form, HttpServletRequest request,
			   HttpServletResponse response) throws Exception
	{
		String orderId = request.getParameter("orderId");
		String result = "success";
		OrderDTO order = myOrderFacade.get(orderId);
		if(order.getPayType().equals("2")){//分期支付
			if(null == order.getPayTime1()){
				result = "unPay1";//定金未支付
			}else if(null == order.getPayTime2()){
				result = "unPay2";//中期款未支付
			}else if(null == order.getPayTime3()){
				result = "unPay3";//尾款未支付
			}
		}else{//一次性支付
			if(null == order.getPayTime1()){
				result = "unPay";//未支付
			}
		}
		if(result.equals("success")){
			order.setFinishTime(new Date());
			order.setOrderStatus("6");//6已开始施工
			myOrderFacade.update(order);
		}
		try
		{
			PrintWriter out = response.getWriter();
			out.print(result);
			out.close();
		}
		catch (IOException e)
		{
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * 工人端施工内容上传
	 *
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward workContentSubimt(ActionMapping mapping,
						ActionForm form, HttpServletRequest request,
						HttpServletResponse response) throws Exception
	{
		String orderId = request.getParameter("orderId");
		String workContent = request.getParameter("workContent");

		OrderDTO order = myOrderFacade.get(orderId);
		if(null != workContent && !"".equals(workContent)){
			order.setDesc1(workContent);
		}
		order.setOrderStatus("5");//已完成施工
		order.setFinishTime(new Date());
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
			if(null!=order.getTakeTime()){
				order.setTakeTimeStr(format.format(order.getTakeTime()));
			}
			if(null!=order.getSureTime()){
				order.setSureTimeStr(format.format(order.getSureTime()));
			}
			if(null!=order.getActualTime()){
				order.setActualTimeStr(format.format(order.getActualTime()));
			}
			if(null!=order.getFinishTime()){
				order.setFinishTimeStr(format.format(order.getFinishTime()));
			}
			if(null!=order.getPayTime()){
				order.setPayTimeStr(format.format(order.getPayTime()));
			}
//			if(null!=order.getSureTime()){//确认时间
//				order.setSureTimeStr(format.format(order.getSureTime()));
//			}
//			if(null!=order.getEndTime()){//完成时间
//				order.setEndTimeStr(format.format(order.getEndTime()));
//			}
//			if(null!=order.getTakeTime()){//接单时间
//				order.setTakeTimeStr(format.format(order.getTakeTime()));
//			}
			request.setAttribute("order", order);
			request.setAttribute("orderId", order.getOrderId());

			MenberAddrDTO address = myMenberAddrFacade.get(order.getAddrId());
			request.setAttribute("address", address);

			GoodCategoryDTO firstCate = myGoodCategoryFacade.get(order.getFirstCate());
//			GoodCategoryDTO secondCate = myGoodCategoryFacade.get(order.getSecondCate());
			request.setAttribute("firstCateName", firstCate.getName());
//	    	request.setAttribute("secondCateName", secondCate.getName());
			AppraiseDTO appraise = myAppraiseFacade.getByOrderId(orderId);
			request.setAttribute("appraise", appraise);

			//获取ticket
			String jsapi_ticket = (String)cache.get("jsapi_ticket");
			log.info("jsapi_ticket："+jsapi_ticket);
			request.setAttribute("jsapi_ticket",jsapi_ticket);

			return mapping.findForward(returnPage);
		}
		return null;
	}

	/**
	 * 去支付
	 * @param request
	 * @return
	 */
	public ActionForward toPaymentPage(ActionMapping mapping,
			 ActionForm form, HttpServletRequest request,
			 HttpServletResponse response) throws Exception
	{
		String orderId = request.getParameter("orderId");
		String payState = request.getParameter("payState");
		OrderDTO order = myOrderFacade.get(orderId);
		String payTitle = "";
		String payPrice = "";
		String payText = "";
		if(payState.equals("0")){
			payTitle = "订单总金额支付";
			payText = "总额";
			payPrice = order.getPayPrice1().toString();
		}else if(payState.equals("1")){
			payTitle = "定金金额支付";
			payText = "定金";
			payPrice = order.getPayPrice1().toString();
		}else if(payState.equals("2")){
			payTitle = "中期款金额支付";
			payText = "中期";
			payPrice = order.getPayPrice2().toString();
		}else if(payState.equals("3")){
			payTitle = "尾款金额支付";
			payText = "尾款";
			payPrice = order.getPayPrice3().toString();
		}
		request.setAttribute("payTitle",payTitle);
		request.setAttribute("payState",payState);
		request.setAttribute("payPrice",payPrice);
		request.setAttribute("payText",payText);
		request.setAttribute("order",order);
		return mapping.findForward("paymentPage_wap");
	}

	/**
	 * 根据时间按月统计产值和订单数
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward outputAndNumList(ActionMapping mapping,
				ActionForm form, HttpServletRequest request,
				HttpServletResponse response) throws Exception
	{
		String type = request.getParameter("type");//类型：wap,web
		String beginTime = request.getParameter("beginTime");
		String endTime = request.getParameter("endTime");
		String returnPage = "";
		MenberDTO menber = null;
		if(type.equals("wap")){
			menber = (MenberDTO)request.getSession().getAttribute("wxmenber");
				request.setAttribute("beginTime", beginTime);
				request.setAttribute("endTime", endTime);
				List<OrderDTO> list = myOrderFacade.outputAndNumByMonth(menber.getId(),beginTime,endTime);
				request.setAttribute("list",list);
				returnPage = "outputAndNumList_wap";
		}else if(type.equals("web")){

		}
		if(null != menber)
		{
			return mapping.findForward(returnPage);
		}
		return null;
	}

	/**
	 * 上传人脸图片
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward uploadImg(ActionMapping mapping,
									   ActionForm form, HttpServletRequest request,
									   HttpServletResponse response) throws Exception
	{
		log.info("========================= WeixinAction uploadFaceImg begin");
		String mediaId = (String)request.getParameter("mediaId");
		String type = (String)request.getParameter("type");
		String orderId = (String)request.getParameter("orderId");
		String token = (String)cache.get("access_token");
		String fileName = (int) ((Math.random() * 9 + 1) * 100000)+".jpg";

        //从微信服务器下载人脸图片到云服务器
        WeixinUtil.download(token,mediaId,type,fileName);

		OrderDTO dto = myOrderFacade.get(orderId);
		if("protocol".equals(type)){
			dto.setProtocolImgPath("common/upload/transactionRecord/"+type+fileName);
		}else if("quote".equals(type)){
			dto.setQuoteImgPath("common/upload/transactionRecord/"+type+fileName);
		}else if("service".equals(type)){
			dto.setServiceImgPath("common/upload/transactionRecord/"+type+fileName);
		}
		myOrderFacade.update(dto);

		log.info("mediaId:"+mediaId);
		log.info("token:"+token);

		response.getWriter().write(fileName);
		response.getWriter().flush();
		return null;
	}

}