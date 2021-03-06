package com.shop.order.action;

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
import com.shop.appraise.model.facade.AppraiseFacade;
import com.shop.good.model.dto.GoodCategoryDTO;
import com.shop.good.model.facade.GoodCategoryFacade;
import com.shop.order.model.dto.OrderDTO;
import com.shop.order.model.facade.OrderFacade;
import com.shop.order.model.facade.OrderItemFacade;
import com.shop.order.model.facade.ShopCarFacade;
import com.sinovatech.bms.adm.model.dto.TBmsLocationDTO;
import com.sinovatech.bms.adm.model.dto.TBmsUserDTO;
import com.sinovatech.bms.adm.model.facade.BmsLocationFacade;
import com.sinovatech.common.config.GlobalConfig;
import com.sinovatech.common.util.DateUtil;
import com.sinovatech.common.util.Validate;
import com.sinovatech.common.web.action.ActionConstent;
import com.sinovatech.common.web.action.BaseAdmAction;
import com.sinovatech.common.web.action.CommonMapping;
import com.sinovatech.common.web.limit.ExLimitUtil;
import com.sinovatech.common.web.limit.HqlProperty;
import com.sinovatech.common.web.limit.ILimitUtil;
import com.sinovatech.common.web.limit.LimitInfo;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.apache.commons.lang.StringUtils;
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
	private AppraiseFacade myAppraiseFacade;
	private BmsLocationFacade myBmsLocationFacade;
	
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
		this.myBmsLocationFacade = (BmsLocationFacade) this.getBeanContext().getBean("myBmsLocationFacade");
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
		String serviceType = request.getParameter("serviceType");
		if (!StringUtil.stringVerify(serviceType)) {
			order.setServiceType(serviceType);
		}
		String province = request.getParameter("province");
		if (!StringUtil.stringVerify(province)) {
			order.setProvince(province);
		}
		String city = request.getParameter("city");
		if (!StringUtil.stringVerify(city)) {
			order.setCity(city);
		}
		String team = request.getParameter("team");
		if (!StringUtil.stringVerify(team)) {
			order.setTeam(team);
		}


		//登录用户权限过滤
		TBmsUserDTO loginUser = getUser(request);
		String currUserLevel = "1";//用户等级（1管理员，2省，3市，4队）
		if(!loginUser.getUserLoginName().equals("admin")){
			String locationId = loginUser.getTbTBmsLocationDTO().getId();
			TBmsLocationDTO l = new TBmsLocationDTO();
			l.setId(locationId);
			order.setTbTBmsLocationDTO(l);

			//判断当前用户等级
			TBmsLocationDTO locationDTO1 = myBmsLocationFacade.get(locationId);
			TBmsLocationDTO locationDTO2 = myBmsLocationFacade.get(locationDTO1.getParentid());
			if(null == locationDTO2.getParentid() || "".equals(locationDTO2.getParentid())){
				currUserLevel = "2";
			}else{
				TBmsLocationDTO locationDTO3 = myBmsLocationFacade.get(locationDTO2.getParentid());
				if(null == locationDTO3.getParentid() || "".equals(locationDTO3.getParentid())){
					currUserLevel = "3";
				}else{
					currUserLevel = "4";
				}
			}
		}
		order.setUserLevel(currUserLevel);
		request.setAttribute("order", order);
		request.setAttribute("currUserLevel", currUserLevel);
		request.setAttribute("currLocationId", loginUser.getTbTBmsLocationDTO().getId());//当前用户部门ID

		limit = this.myOrderFacade.dtoFilterProperty(order, limit);
		limit.setSortProperty("orderTime");
		limit.setSortType("desc");
		// 过滤的条件  结束

//		String locationId = loginUser.getTbTBmsLocationDTO().getId();
//		LimitInfo locationLimit = new LimitInfo();
//		locationLimit.addFilterProperty(HqlProperty.getEq("parentId",locationId));
//		List<TBmsLocationDTO> locationList = myBmsLocationFacade.list(locationLimit);
//
//		request.setAttribute("locationList", locationList);
		LimitInfo limitInfo = new LimitInfo();
		limitInfo = this.myOrderFacade.dtoFilterProperty(order, limitInfo);
		OrderDTO statistics = myOrderFacade.getStatistics(limitInfo);//统计总成本、总订单金额、总利润
		request.setAttribute("statistics", statistics);

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
			if(null != o.getTbTBmsLocationDTO()){
				o.setLocationName( o.getTbTBmsLocationDTO().getName());
			}else{
				o.setLocationName("未指派");
			}
			String projectProgress = myOrderFacade.getprojectProgress(o);
			if(!projectProgress.equals("0")){
				projectProgress = projectProgress + "%";
			}
			o.setProjectProgress(projectProgress);
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
		if(null!=m.getActualTime()){
			m.setActualTimeStr(format.format(m.getActualTime()));
		}
		if(null!=m.getFinishTime()){
			m.setFinishTimeStr(format.format(m.getFinishTime()));
		}
		if(null!=m.getPayTime()){
			m.setPayTimeStr(format.format(m.getPayTime()));
		}
		if(null!=m.getEndTime()){
			m.setEndTimeStr(format.format(m.getEndTime()));
		}


		String projectProgress = myOrderFacade.getprojectProgress(m);//项目进度
		request.setAttribute("projectProgress", projectProgress);

		MenberDTO menber = myMenberFacade.get(m.getMenId());
		m.setMenName(menber.getRealName());
		m.setMenMobile(menber.getMobile());
		request.setAttribute("m", m);
		
		MenberDTO worker = myMenberFacade.get(m.getWorkerId());
		request.setAttribute("worker", worker);
		
		MenberAddrDTO addr = myMenberAddrFacade.get(m.getAddrId());
		addr.setStreet(addr.getProvince()+addr.getCity()+addr.getStreet());//全地址=省+市+街道
		request.setAttribute("addr", addr);

		int totalOrderNum = myOrderFacade.getWorkerOrderTotalNum(m.getWorkerId());
		request.setAttribute("totalOrderNum", totalOrderNum);//订单总数

		String positiveAppraiseRate = myAppraiseFacade.getAppraiseRate(m.getWorkerId(),"1");
		request.setAttribute("positiveAppraiseRate", positiveAppraiseRate);//好评率

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
	 * 订单指派（修改订单金额和接单工人）
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
		String payType = request.getParameter("payType");
		String cost = request.getParameter("cost");
		Integer cycleInit = Integer.parseInt(request.getParameter("cycleInit"));
		BigDecimal totalPriceBd = new BigDecimal(totalPrice);

		MenberDTO menber = myMenberFacade.get(workerId);

		OrderDTO order = myOrderFacade.get(orderId);
		order.setWorkerId(workerId);
		order.setWorkerName(menber.getRealName());
		order.setTotalPrice(totalPriceBd);
		order.setOrderStatus("2");
		order.setTakeTime(new Date());
		order.setCycleInit(cycleInit);
		order.setPayType(payType);
		order.setPayPrice1(totalPriceBd);
		order.setCost(new BigDecimal(cost));
		if(payType.equals("2")){//分期支付
			String proportion1 = GlobalConfig.getProperty("redis", "payPrice1.proportion");//定金比例
			String proportion2 = GlobalConfig.getProperty("redis", "payPrice2.proportion");//中期款比例
			String proportion3 = GlobalConfig.getProperty("redis", "payPrice3.proportion");//尾款比例
			order.setPayPrice1(totalPriceBd.multiply(new BigDecimal(proportion1)));
			order.setPayPrice2(totalPriceBd.multiply(new BigDecimal(proportion2)));
			order.setPayPrice3(totalPriceBd.multiply(new BigDecimal(proportion3)));
		}
		order.setTbTBmsLocationDTO(menber.getTbTBmsLocationDTO());
		myOrderFacade.update(order);

		CommonMapping mping = new CommonMapping("保存成功!", getRealUri(mapping,"order/queryList"), ActionConstent.ALERT);
		request.setAttribute("mping", mping);
		return mapping.findForward(ActionConstent.COMMON_MAPPING);
	}

	/**
	 * 工期新增
	 *
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward orderCycleAdd(ActionMapping mapping,
			  ActionForm form, HttpServletRequest request,
			  HttpServletResponse response) throws Exception
	{
		String orderId = request.getParameter("orderId");
		Integer cycleAdd = Integer.parseInt(request.getParameter("cycleAdd"));

		OrderDTO order = myOrderFacade.get(orderId);
		order.setCycleAdd(cycleAdd);
		myOrderFacade.update(order);
		CommonMapping mping = new CommonMapping("保存成功!", getRealUri(mapping,"order/queryList"), ActionConstent.ALERT);
		request.setAttribute("mping", mping);
		return mapping.findForward(ActionConstent.COMMON_MAPPING);
	}

	/**
	 * 成本修改
	 *
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward editCost(ActionMapping mapping,
				   ActionForm form, HttpServletRequest request,
				   HttpServletResponse response) throws Exception
	{
		String orderId = request.getParameter("orderId");
		String cost = request.getParameter("cost");

		OrderDTO order = myOrderFacade.get(orderId);
		order.setCost(new BigDecimal(cost));
		myOrderFacade.update(order);
		CommonMapping mping = new CommonMapping("保存成功!", getRealUri(mapping,"order/queryList"), ActionConstent.ALERT);
		request.setAttribute("mping", mping);
		return mapping.findForward(ActionConstent.COMMON_MAPPING);
	}

	/**
	 * 获取兄弟部门列表
	 *
	 * @param mapping
	 * @param form
	 * @param req
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward getBrotherLocation(ActionMapping mapping,
				 ActionForm form, HttpServletRequest req,
				 HttpServletResponse response) throws Exception
	{
		String locationId = req.getParameter("locationId");

		TBmsLocationDTO dto = myBmsLocationFacade.get(locationId);
		LimitInfo limit = new LimitInfo();
		limit.addFilterProperty(HqlProperty.getEq("parentid",dto.getParentid()));
		List<TBmsLocationDTO> bmsLocationDTOList = myBmsLocationFacade.list(limit);
		StringBuilder sb = new StringBuilder();
		int i = 0;
		sb.append("{\"pageData\":[");
		for (TBmsLocationDTO locationDTO : bmsLocationDTOList)
		{
			if (i > 0)
			{
				sb.append(",");
			}
			sb.append("{");
			sb.append("\"id\":\"" + locationDTO.getId() + "\",");
			sb.append("\"name\":\"" + locationDTO.getName() + "\",");
			sb.append("\"parentId\":\"" + locationDTO.getParentid() + "\"}");
			i++;
		}
		sb.append("]}");
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
	 * 获取子部门列表
	 *
	 * @param mapping
	 * @param form
	 * @param req
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward getChildLocation(ActionMapping mapping,
											ActionForm form, HttpServletRequest req,
											HttpServletResponse response) throws Exception
	{
		String locationId = req.getParameter("locationId");
		LimitInfo limit = new LimitInfo();
		limit.addFilterProperty(HqlProperty.getEq("parentid",locationId));
		List<TBmsLocationDTO> bmsLocationDTOList = myBmsLocationFacade.list(limit);
		StringBuilder sb = new StringBuilder();
		int i = 0;
		sb.append("{\"pageData\":[");
		for (TBmsLocationDTO locationDTO : bmsLocationDTOList)
		{
			if (i > 0)
			{
				sb.append(",");
			}
			sb.append("{");
			sb.append("\"id\":\"" + locationDTO.getId() + "\",");
			sb.append("\"name\":\"" + locationDTO.getName() + "\",");
			sb.append("\"parentId\":\"" + locationDTO.getParentid() + "\"}");
			i++;
		}
		sb.append("]}");
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
	 * 取消订单
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

		OrderDTO order = myOrderFacade.get(orderId);
		order.setOrderStatus("8");
		myOrderFacade.update(order);
		CommonMapping mping = new CommonMapping("取消成功!", getRealUri(mapping,"order/queryList"), ActionConstent.ALERT);
		request.setAttribute("mping", mping);
		return mapping.findForward(ActionConstent.COMMON_MAPPING);
//		return null;
	}

	/**
	 * 修改初始化 - 后台
	 *
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward beforeUpdate(ActionMapping mapping,
				ActionForm form, HttpServletRequest request,
				HttpServletResponse response) throws Exception
	{
		String orderId = request.getParameter("orderId");
		// 验证方法， 如果为null或者为空则直接返回异常
		Validate.notBlank(orderId, "common", "errorparameter");
		setActionAttribute(request, "beforeEdit.id", orderId);
		OrderDTO o = myOrderFacade.get(orderId);
		o.setOrderTimeStr(format.format(o.getOrderTime()));
		if(null != o.getPayTime()){
			o.setPayTimeStr(format.format(o.getPayTime()));
		}
		MenberDTO menber = myMenberFacade.get(o.getMenId());
		if(null!=menber){
			o.setMenName(menber.getRealName());
			o.setMenMobile(menber.getMobile());
		}
		if(null != o.getPayTime1())
			o.setPayTime1Str(format.format(o.getPayTime1()));
		if(null != o.getPayTime2())
			o.setPayTime2Str(format.format(o.getPayTime2()));
		if(null != o.getPayTime3())
			o.setPayTime3Str(format.format(o.getPayTime3()));
//		MenberDTO worker = myMenberFacade.get(o.getWorkerId());
//		if(null!=worker){
//			o.setWorkerName(worker.getRealName());
//		}
//		if(null != o.getTbTBmsLocationDTO()){
//			o.setLocationName( o.getTbTBmsLocationDTO().getName());
//		}else{
//			o.setLocationName("未指派");
//		}
		String proportion1 = GlobalConfig.getProperty("redis", "payPrice1.proportion");//定金比例
		String proportion2 = GlobalConfig.getProperty("redis", "payPrice2.proportion");//中期款比例
		String proportion3 = GlobalConfig.getProperty("redis", "payPrice3.proportion");//尾款比例
		request.setAttribute("proportion1", proportion1);
		request.setAttribute("proportion2", proportion2);
		request.setAttribute("proportion3", proportion3);
		request.setAttribute("m", o);
		return mapping.findForward("beforeUpdate");
	}

	/**
	 * 修改 - 后台
	 *
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward orderUpdate(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception
	{
		String totalPrice = request.getParameter("totalPrice");
		String payTime1Str = request.getParameter("payTime1Str");
		String payTime2Str = request.getParameter("payTime2Str");
		String payTime3Str = request.getParameter("payTime3Str");
		String cycleAdd = request.getParameter("cycleAdd");
		String cost = request.getParameter("cost");

		String orderId = (String)getActionAttribute(request, "beforeEdit.id");
		OrderDTO orderDTO = myOrderFacade.get(orderId);

		if(!StringUtil.stringVerify(totalPrice) && !totalPrice.equals(orderDTO.getTotalPrice().toString())){//订单总价已修改
			BigDecimal totalPriceBd = new BigDecimal(totalPrice);
			if(orderDTO.getPayType().equals("2")){
				String proportion1 = GlobalConfig.getProperty("redis", "payPrice1.proportion");//定金比例
				String proportion2 = GlobalConfig.getProperty("redis", "payPrice1.proportion");//中期款比例
				String proportion3 = GlobalConfig.getProperty("redis", "payPrice1.proportion");//尾款比例
				orderDTO.setPayPrice1(totalPriceBd.multiply(new BigDecimal(proportion1)));
				orderDTO.setPayPrice2(totalPriceBd.multiply(new BigDecimal(proportion2)));
				orderDTO.setPayPrice3(totalPriceBd.multiply(new BigDecimal(proportion3)));
			}else{
				orderDTO.setPayPrice1(totalPriceBd);
			}
		}

		if(!StringUtil.stringVerify(payTime1Str)){
			orderDTO.setPayTime1(DatePaltUtil.parseDate(payTime1Str,"yyyy-MM-dd HH:mm:ss"));
		}
		if(!StringUtil.stringVerify(payTime2Str)){
			orderDTO.setPayTime2(DatePaltUtil.parseDate(payTime2Str,"yyyy-MM-dd HH:mm:ss"));
		}
		if(!StringUtil.stringVerify(payTime3Str)){
			orderDTO.setPayTime3(DatePaltUtil.parseDate(payTime3Str,"yyyy-MM-dd HH:mm:ss"));
		}
		if(!StringUtil.stringVerify(cycleAdd)){
			orderDTO.setCycleAdd(Integer.parseInt(cycleAdd));
		}
		if(!StringUtil.stringVerify(cost)){
			orderDTO.setCost(new BigDecimal(cost));
		}

		myOrderFacade.update(orderDTO);
		CommonMapping mping = new CommonMapping("保存成功!", getRealUri(mapping,"order/queryList"), ActionConstent.ALERT);
		request.setAttribute("mping", mping);
		return mapping.findForward(ActionConstent.COMMON_MAPPING);
	}

}