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
	 * 订单分页查询 - 后台
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward queryList(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception
	{
		// 列表控件的TableId值
		String tableId = "OrderList";
		// 获取分页信息
		ILimitUtil limitUtil = new ExLimitUtil();
		LimitInfo limit = limitUtil.getLimitInfo(request, tableId, 10);
		
		// 过滤的条件  开始
		OrderDTO order = (OrderDTO) form;
		String orderSn = request.getParameter("orderSn");
		if (!StringUtil.stringVerify(orderSn)) {
			order.setOrderSn(orderSn);
		}
		String orderStatus = request.getParameter("orderStatus");
		if (!StringUtil.stringVerify(orderStatus)) {
			order.setOrderStatus(orderStatus);
		}else{
			order.setOrderStatus(null);
		}
		String payType = request.getParameter("payType");
		if (!StringUtil.stringVerify(payType)) {
			order.setPayType(payType);
		}
		String payStatus = request.getParameter("payStatus");
		if (!StringUtil.stringVerify(payStatus)) {
			order.setPayStatus(payStatus);
		}
		String shippingStatus = request.getParameter("shippingStatus");
		if (!StringUtil.stringVerify(shippingStatus)) {
			order.setShippingStatus(shippingStatus);
		}
		String beginTimeStr = request.getParameter("beginTimeStr");
		if (!StringUtil.stringVerify(beginTimeStr)) {
			order.setBeginTime(DatePaltUtil.parseDate(beginTimeStr,"yyyy-MM-dd HH:mm:ss"));
			order.setBeginTimeStr(beginTimeStr);
		}
		String endTimeStr = request.getParameter("endTimeStr");
		if ((!StringUtil.stringVerify(endTimeStr))) {
			order.setEndTime(DatePaltUtil.parseDate(endTimeStr,"yyyy-MM-dd HH:mm:ss"));
			order.setEndTimeStr(endTimeStr);
		}
		String menId = request.getParameter("menId");
		if (!StringUtil.stringVerify(menId)) {
			order.setMenId(menId);
		}
		String workerId = request.getParameter("workerId");
		if (!StringUtil.stringVerify(workerId)) {
			order.setWorkerId(workerId);
		}
		request.setAttribute("order", order);
		limit = this.myOrderFacade.dtoFilterProperty(order, limit);
		limit.setSortProperty("orderTime");
		limit.setSortType("desc");
		// 过滤的条件  结束
		

		// 查询
		List<OrderDTO> list = myOrderFacade.list(limit);
		// 设置分页信息
		limitUtil.setLimitInfo(request, limit);
		// 查询过滤、分页状态保留
		this.setActionAttribute(request, "backUrlStore", this.getActionContext(request).getCurentURL());

		List orderList = new ArrayList();
		for (Iterator iterator = list.iterator(); iterator.hasNext();) {
			OrderDTO o = (OrderDTO) iterator.next();
			o.setOrderTimeStr(format.format(o.getOrderTime()));
			if(null != o.getPayTime()){
				o.setPayTimeStr(format.format(o.getPayTime()));
			}
			MenberDTO menber = myMenberFacade.get(o.getMenId());
			if(null!=menber){
				o.setMenName(menber.getRealName());
				o.setMenMobile(menber.getMobile());
			}
			MenberDTO worker = myMenberFacade.get(o.getWorkerId());
			if(null!=worker){
				o.setWorkerName(worker.getRealName());
			}
			orderList.add(o);
		}
		request.setAttribute("list", orderList);

		//查询安装工列表
		Map<String,String> params = new HashMap<String,String>();
		params.put("type","2");
		List workerList = myMenberFacade.listMenbers(params);
		request.setAttribute("workerList", workerList);

		return mapping.findForward("list");
	}

	
//	
//	public ActionForward add(ActionMapping mapping, ActionForm form,
//			HttpServletRequest request, HttpServletResponse response)
//			throws Exception {
//		OrderDTO dto = (OrderDTO) form;
//		dto.setOrderTime(new Date());
////		dto.setPoint(1);
//		this.myOrderFacade.save(dto);
//		CommonMapping mping = new CommonMapping("保存成功!", getRealUri(mapping,"order/queryList"), ActionConstent.ALERT);
//		request.setAttribute("mping", mping);
//		return mapping.findForward(ActionConstent.COMMON_MAPPING);
//	}

	
	public ActionForward beforeView(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception
	{
		String orderId = request.getParameter("orderId");
		// 验证方法， 如果为null或者为空则直接返回异常
		Validate.notBlank(orderId, "common", "errorparameter");
		OrderDTO m = this.myOrderFacade.get(orderId);
		
		if(null!=m.getOrderTime()){
			m.setOrderTimeStr(format.format(m.getOrderTime()));
		}
		if(null!=m.getTakeTime()){
			m.setTakeTimeStr(format.format(m.getTakeTime()));
		}
		if(null!=m.getSureTime()){
			m.setSureTimeStr(format.format(m.getSureTime()));
		}
		if(null!=m.getEndTime()){
			m.setEndTimeStr(format.format(m.getEndTime()));
		}
		
		MenberDTO menber = myMenberFacade.get(m.getMenId());
		m.setMenName(menber.getRealName());
		m.setMenMobile(menber.getMobile());
		request.setAttribute("m", m);
		
		MenberDTO worker = myMenberFacade.get(m.getWorkerId());
		request.setAttribute("worker", worker);
		
		MenberAddrDTO addr = myMenberAddrFacade.get(m.getAddrId());
		addr.setStreet(addr.getProvince()+addr.getCity()+addr.getStreet());//全地址=省+市+街道
		request.setAttribute("addr", addr);
		
//		List<OrderItemDTO> items = myOrderItemFacade.listByOrderId(m.getOrderId());
//		request.setAttribute("items", items);
		
		return mapping.findForward("view");
	}

	/**
	 * 修改订单 - 后台
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward edit(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception
	{
		OrderDTO dto = (OrderDTO) form;
		// 设置不需要用户更新的数据，如主键等
		dto.setOrderId((String) getActionAttribute(request, "beforeView.id"));
		this.myOrderFacade.update(dto);
		CommonMapping mping = new CommonMapping("保存成功!", getRealUri(mapping,"order/queryList"), ActionConstent.ALERT);
		request.setAttribute("mping", mping);
		return mapping.findForward(ActionConstent.COMMON_MAPPING);
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
	 * 修改订单金额
	 *
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward updateOrderPrice(ActionMapping mapping,
										   ActionForm form, HttpServletRequest request,
										   HttpServletResponse response) throws Exception
	{
		String orderId = request.getParameter("orderId");
		String workerId = request.getParameter("workerId");
		String totalPrice = request.getParameter("totalPrice");

		MenberDTO menber = myMenberFacade.get(workerId);

		OrderDTO order = myOrderFacade.get(orderId);
		order.setWorkerId(workerId);
		order.setWorkerName(menber.getRealName());
		order.setTotalPrice(new BigDecimal(totalPrice));
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

}