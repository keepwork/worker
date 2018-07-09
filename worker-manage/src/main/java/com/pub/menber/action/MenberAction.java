package com.pub.menber.action;

import java.io.IOException;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.commons.lang.StringUtils;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import com.common.util.ServletUtil;
import com.common.util.excel.ExcelBean;
import com.pub.menber.model.dto.MenberDTO;
import com.pub.menber.model.dto.MenberPointDTO;
import com.pub.menber.model.dto.MenberShareDTO;
import com.pub.menber.model.facade.MenberFacade;
import com.pub.menber.model.facade.MenberPointFacade;
import com.pub.menber.model.facade.MenberShareFacade;
import com.sinovatech.common.config.GlobalConfig;
import com.sinovatech.common.util.Validate;
import com.sinovatech.common.web.action.ActionConstent;
import com.sinovatech.common.web.action.BaseAdmAction;
import com.sinovatech.common.web.action.CommonMapping;
import com.sinovatech.common.web.limit.ExLimitUtil;
import com.sinovatech.common.web.limit.ILimitUtil;
import com.sinovatech.common.web.limit.LimitInfo;
/**
 * 会员管理
 * 
 * @author kevin(keepwork512@163.com)
 * @date Dec 30, 2015 9:55:43 PM
 */
public class MenberAction extends BaseAdmAction
{ 
	private SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	private SimpleDateFormat format2 = new SimpleDateFormat("yyyy-MM-dd");
	
	private MenberFacade myMenberFacade;
	private MenberPointFacade myMenberPointFacade;
	private MenberShareFacade myMenberShareFacade;
	
	public void init(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception
	{
		this.myMenberFacade = (MenberFacade) this.getBeanContext().getBean("myMenberFacade");
		this.myMenberPointFacade = (MenberPointFacade) this.getBeanContext().getBean("myMenberPointFacade");
		this.myMenberShareFacade = (MenberShareFacade) this.getBeanContext().getBean("myMenberShareFacade");
	}
	
	/**
	 * 分页查询 - 后台
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
		String tableId = "MenberList";

		// 获取分页信息
		ILimitUtil limitUtil = new ExLimitUtil();
		LimitInfo limit = limitUtil.getLimitInfo(request, tableId, 10);
		limit.setSortProperty("regTime");
		limit.setSortType("desc");

		// 查询
		List<MenberDTO> list = myMenberFacade.list(limit);

		// 设置分页信息
		limitUtil.setLimitInfo(request, limit);

		// 查询过滤、分页状态保留
		this.setActionAttribute(request, "backUrlStore", this.getActionContext(request).getCurentURL());

		List menberlist = new ArrayList();
		for (Iterator iterator = list.iterator(); iterator.hasNext();) {
			MenberDTO menber = (MenberDTO) iterator.next();
			menber.setRegTimeStr(format.format(menber.getRegTime()));
			if(null != menber.getLastTime()){
				menber.setLastTimeStr(format.format(menber.getLastTime()));
			}
			menberlist.add(menber);
		}
		request.setAttribute("list", menberlist);
		
		String listShowType = request.getParameter("listShowType");
		request.setAttribute("listShowType", listShowType);
		
		return mapping.findForward("list");
	}

	/**
	 * 新增初始化 - 后台
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward beforeAdd(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		return mapping.findForward("add");
	}

	/**
	 * 新增 - 后台
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward add(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		MenberDTO dto = (MenberDTO) form;
		CommonMapping mping=null;
		dto.setRegTime(new Date());
		dto.setPoint(100);//注册用户免费赠送100商城积分
//		if(dto.getSex().equals("1")){
//			dto.setSex("男");
//		}
//		if(dto.getSex().equals("2")){
//			dto.setSex("女");
//		}
		dto.setSign(0);
		dto.setIsjoin(0);//未加入计划
		
		//根据身份编号获取年龄，并给予权利金
//		int age = IdcardUtils.getAgeByIdCard(dto.getPid());
//		if(age>0 && age<=40){
//			dto.setRightFee(new BigDecimal(300000));
//		}else if(age>40 && age<=50){
//			dto.setRightFee(new BigDecimal(300000));
//		}else {
//			dto.setRightFee(new BigDecimal(0));
//		}
		dto.setRightFee(new BigDecimal(0.00));
		
		dto.setContributeFee(new BigDecimal(0.00));
		dto.setRemind(0);
		dto.setBalanceFee(new BigDecimal(0.00));
		
		String k = (StringUtils.isBlank(GlobalConfig.getProperty("bms", 
	      "sysusercreateKey"))) ? 
	      "123456565643450987657689876543267676787651234567" : 
	      GlobalConfig.getProperty("bms", "sysusercreateKey");
	    String password = com.sinovatech.common.util.Des.encrytWithBase64("DESede", dto.getPassword(), k);
	    dto.setPassword(password);
	    
		this.myMenberFacade.save(dto);
		mping = new CommonMapping("保存成功!", getRealUri(mapping,"menber/queryList"), ActionConstent.ALERT);
		
		request.setAttribute("mping", mping);
		return mapping.findForward(ActionConstent.COMMON_MAPPING);
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
	public ActionForward beforeEdit(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception
	{
		String id = request.getParameter("id");
		// 验证方法， 如果为null或者为空则直接返回异常
		Validate.notBlank(id, "common", "errorparameter");
		MenberDTO m = this.myMenberFacade.get(id);
		// 将当前编辑的数据中的不需要用户修改的数据保存的ActionContext中
		// 如主键等,代替使用隐藏域发送，更安全
		setActionAttribute(request, "beforeEdit.id", id);
		setActionAttribute(request, "beforeEdit.point", m.getPoint());
		setActionAttribute(request, "beforeEdit.balanceFee", m.getBalanceFee());
		setActionAttribute(request, "beforeEdit.salesMenId", m.getSalesMenId());
		setActionAttribute(request, "beforeEdit.sign", m.getSign());
		setActionAttribute(request, "beforeEdit.isjoin", m.getIsjoin());
		setActionAttribute(request, "beforeEdit.regTime", m.getRegTime());
		setActionAttribute(request, "beforeEdit.lastTime", m.getLastTime());
//		setActionAttribute(request, "beforeEdit.openId", m.getOpenId());
//		setActionAttribute(request, "beforeEdit.loginName", m.getLoginName());
		setActionAttribute(request, "beforeEdit.city", m.getCity());
		setActionAttribute(request, "beforeEdit.headimgurl", m.getHeadimgurl());
		setActionAttribute(request, "beforeEdit.rightFee", m.getRightFee());
		setActionAttribute(request, "beforeEdit.contributeFee", m.getContributeFee());
		setActionAttribute(request, "beforeEdit.realNameEmergency", m.getRealNameEmergency());
		setActionAttribute(request, "beforeEdit.mobileEmergency", m.getMobileEmergency());
		setActionAttribute(request, "beforeEdit.remind", m.getRemind());
		request.setAttribute("m", m);
		return mapping.findForward("edit");
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
	public ActionForward edit(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception
	{
		MenberDTO dto = (MenberDTO) form;
		// 设置不需要用户更新的数据，如主键等
		dto.setId((String) getActionAttribute(request, "beforeEdit.id"));
		dto.setPoint((Integer) getActionAttribute(request, "beforeEdit.point"));
		dto.setBalanceFee((BigDecimal) getActionAttribute(request, "beforeEdit.balanceFee"));
		dto.setSalesMenId((String) getActionAttribute(request, "beforeEdit.salesMenId"));
		dto.setSign((Integer) getActionAttribute(request, "beforeEdit.sign"));
		dto.setIsjoin((Integer) getActionAttribute(request, "beforeEdit.isjoin"));
		dto.setRegTime((Date) getActionAttribute(request, "beforeEdit.regTime"));
		dto.setLastTime((Date) getActionAttribute(request, "beforeEdit.lastTime"));
//		dto.setOpenId((String) getActionAttribute(request, "beforeEdit.openId"));
//		dto.setLoginName((String) getActionAttribute(request, "beforeEdit.loginName"));
		dto.setCity((String) getActionAttribute(request, "beforeEdit.city"));
		dto.setHeadimgurl((String) getActionAttribute(request, "beforeEdit.headimgurl"));
		dto.setRightFee((BigDecimal) getActionAttribute(request, "beforeEdit.rightFee"));
		dto.setContributeFee((BigDecimal) getActionAttribute(request, "beforeEdit.contributeFee"));
		dto.setRealNameEmergency((String) getActionAttribute(request, "beforeEdit.realNameEmergency"));
		dto.setMobileEmergency((String) getActionAttribute(request, "beforeEdit.mobileEmergency"));
		dto.setRemind((Integer) getActionAttribute(request, "beforeEdit.remind"));
		
		MenberDTO old = this.myMenberFacade.get(dto.getId());
		// 用户己修改密码
		if (!dto.getPassword().equals("A000111222333")) {
			String k = (StringUtils.isBlank(GlobalConfig.getProperty("bms","sysusercreateKey"))) ? 
		      "123456565643450987657689876543267676787651234567" : 
		      GlobalConfig.getProperty("bms", "sysusercreateKey");
		    String passWord = com.sinovatech.common.util.Des.encrytWithBase64("DESede", dto.getPassword(), k);
			dto.setPassword(passWord);
		}else{
			dto.setPassword(old.getPassword());
		}
		
//		dto.setRegTime(old.getRegTime());
//		if(dto.getSex().equals("1")){
//			dto.setSex("男");
//		}
//		if(dto.getSex().equals("2")){
//			dto.setSex("女");
//		}
	    
		this.myMenberFacade.update(dto);
		CommonMapping mping = new CommonMapping("保存成功!", getRealUri(mapping,"menber/queryList"), ActionConstent.ALERT);
		request.setAttribute("mping", mping);
		return mapping.findForward(ActionConstent.COMMON_MAPPING);
	}

	/**
	 * 删除初始化 - 后台
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward beforeDelete(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception
	{
		String ids = request.getParameter("ids");
		// 验证方法， 如果为null或者为空则直接返回异常
		Validate.notBlank(ids, "common", "errorparameter");
		List list = this.myMenberFacade.listByIds(ids);
		this.setActionAttribute(request, "beforeDelete.ids", ids);
		request.setAttribute("list", list);
		return mapping.findForward("deleteConfirm");
	}

	/**
	 * 删除 - 后台
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward delete(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception
	{
		String ids = (String) this.getActionAttribute(request,"beforeDelete.ids");
//		String ids = (String)request.getParameter("ids");
		// 验证方法， 如果为null或者为空则直接返回异常
		Validate.notBlank(ids, "common", "errorparameter");
		this.myMenberFacade.delete(ids);
		CommonMapping mping = new CommonMapping("删除成功!",(String) getActionAttribute(request, "backUrlStore"),ActionConstent.ALERT);
		request.setAttribute("mping", mping);
		return mapping.findForward(ActionConstent.COMMON_MAPPING);
	}
	
	
	
	
	/**
	 * 批量导出会员 - 后台
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	public ActionForward exportMenbers(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		String isjoin = request.getParameter("isjoin");
		String beginTimeStr = request.getParameter("beginTimeStr");
		String endTimeStr = request.getParameter("endTimeStr");
		
		Map map = new HashMap();
		if(null != isjoin && !isjoin.equals("")){
    		map.put("isjoin", isjoin);
    	}
		if(null != beginTimeStr && !beginTimeStr.equals("")){
    		map.put("beginTimeStr", beginTimeStr);
    	}
		if(null != endTimeStr && !endTimeStr.equals("")){
    		map.put("endTimeStr", endTimeStr);
    	}
		
		List menberList = myMenberFacade.listMenbers(map);
		System.out.println("=================================menberList:"+menberList.size());
		
		// 执行导出excel数据
		myMenberFacade.exportData(menberList, new ExcelBean(response),"new");
		return null;
	}
	
	
	
	/**
	 * 会员积分列表 - 后台
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
//	public ActionForward pointList(ActionMapping mapping, ActionForm form,
//			HttpServletRequest request, HttpServletResponse response)
//			throws Exception
//	{
//		// 列表控件的TableId值
//		String tableId = "PointList";
//
//		// 获取分页信息
//		ILimitUtil limitUtil = new ExLimitUtil();
//		LimitInfo limit = limitUtil.getLimitInfo(request, tableId, 10);
//
//		// 查询
//		List<MenberDTO> list = myMenberFacade.list(limit);
//
//		// 设置分页信息
//		limitUtil.setLimitInfo(request, limit);
//
//		// 查询过滤、分页状态保留
//		this.setActionAttribute(request, "backUrlStore", this.getActionContext(request).getCurentURL());
//
//		List menberlist = new ArrayList();
//		for (Iterator iterator = list.iterator(); iterator.hasNext();) {
//			MenberDTO menber = (MenberDTO) iterator.next();
//			menber.setRegTimeStr(format.format(menber.getRegTime()));
//			if(null != menber.getLastTime()){
//				menber.setLastTimeStr(format.format(menber.getLastTime()));
//			}
//			menberlist.add(menber);
//		}
//		request.setAttribute("list", menberlist);
//		return mapping.findForward("list");
//	}

	/**
	 * 会员签到 - 前台
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward sign(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception
	{
		StringBuilder sb = new StringBuilder();
		String type = request.getParameter("type");//类型：wap,web
		MenberDTO menber = null;
		if(type.equals("wap")){
			menber = (MenberDTO)request.getSession().getAttribute("wxmenber");
    	}else if(type.equals("web")){
    		menber = (MenberDTO)request.getSession().getAttribute("pcmenber");
    	}
        if(null != menber)
        {
        	boolean check = myMenberPointFacade.checkSign(menber.getId());//检查会员当前是否已签到
        	if(check){
        		int point = 1+(int)(Math.random()*20);
        		menber.setPoint(menber.getPoint()+point);
            	this.myMenberFacade.sign(menber,point);
            	sb.append("签到成功，恭喜您本次获得积分："+point);
        	}else{
            	sb.append("您当天已签过到，请明天再来吧!");
        	}
        }else{
        	sb.append("请重新登录!");
        }
        try{
            ServletUtil.outputXML(response, sb.toString());
        }catch (IOException e){
            e.printStackTrace();
        }
        
//        String type = request.getParameter("type");
//        if(type.equals("wap")){
//    		return mapping.findForward("sign_wap");
//    	}else if(type.equals("web")){
//    		return mapping.findForward("sign_web");
//    	}
        
        return null;
	}
	
	
	
	/**
	 * 会员中心初始化 - 前台
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward centerInit(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception
	{
		String type = request.getParameter("type");//类型：wap,web
		String returnPage = "";
		MenberDTO menber = null;
		if(type.equals("wap")){
			menber = (MenberDTO)request.getSession().getAttribute("wxmenber");
			returnPage = "center_wap";
    	}else if(type.equals("web")){
    		
    	}
        if(null != menber)
        {
    		return mapping.findForward(returnPage);
        }
		return null;
	}
	
	
	
	/**
	 * 修改个人信息初始化 - 前台会员中心
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward beforeEditFront(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception
	{
		String type = request.getParameter("type");//类型：wap,web
		String returnPage = "";
		MenberDTO menber = null;
		if(type.equals("wap")){
			menber = (MenberDTO)request.getSession().getAttribute("wxmenber");
			returnPage = "menberUpdate_wap";
    	}else if(type.equals("web")){
    		menber = (MenberDTO)request.getSession().getAttribute("pcmenber");
    		returnPage = "menberUpdate_web";
    		String leftMenu = request.getParameter("leftMenu");
    		request.setAttribute("leftMenu", leftMenu);
    	}
        if(null != menber)
        {
        	MenberDTO m = this.myMenberFacade.get(menber.getId());
    		request.setAttribute("m", m);
        	
    		return mapping.findForward(returnPage);
        }
		return null;
	}

	/**
	 * 修改个人信息 - 前台会员中心
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward editFront(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception
	{
		MenberDTO dto = (MenberDTO) form;
		String type = request.getParameter("type");//类型：wap,web
		dto.setLoginName(dto.getMobile());//手机号保存为登录名
		
		String pid = dto.getPid();
		if(null!=pid && !pid.equals("")){
			pid = pid.substring(pid.length()-6, pid.length());
			String k = (StringUtils.isBlank(GlobalConfig.getProperty("bms", 
		      "sysusercreateKey"))) ? 
		      "123456565643450987657689876543267676787651234567" : 
		      GlobalConfig.getProperty("bms", "sysusercreateKey");
		    String passWord = com.sinovatech.common.util.Des.encrytWithBase64("DESede", pid, k);
		    dto.setPassword(passWord);
		}
		this.myMenberFacade.updateMenberBySql(dto);
		
		MenberDTO menberInfo = this.myMenberFacade.get(dto.getId());
		
		//如果该会员还未加入计划，填写好个人资料以后且账户余额大于12元，则自动加入计划
    	if(menberInfo.getIsjoin()==0 && menberInfo.getBalanceFee().doubleValue()>=12 && null!=menberInfo.getPid() && !menberInfo.getPid().equals(""))
    	{
    		menberInfo.setIsjoin(1);
    		menberInfo.setJoinTime(new Date());
    		double rf = menberInfo.getRightFee().doubleValue() + 300000;
    		menberInfo.setRightFee(new BigDecimal(rf));//加入计划后，保额增加30万
    		
    		//如果该会员有推荐人，则送（200积分、200保额）给这个推荐人
    		//sign用于保存推荐人是否已经奖励过积分（1已奖励，0未奖励）
    		if(null != menberInfo.getSalesMenId() && !menberInfo.getSalesMenId().equals("") && !menberInfo.getSign().equals("1")){
    			MenberDTO saleMenber = myMenberFacade.get(menberInfo.getSalesMenId());
				if(null!=saleMenber){
					double rightFee = saleMenber.getRightFee().doubleValue() + 200;
					int point = saleMenber.getPoint() + 200;
					saleMenber.setRightFee(new BigDecimal(rightFee));//每邀请一个好友加入，赠送保额200元
					saleMenber.setPoint(point);//每邀请一个好友加入，赠送积分200元
					myMenberFacade.update(saleMenber);
					
					//保存积分历史
					MenberPointDTO p = new MenberPointDTO();
					p.setMenId(menberInfo.getSalesMenId());
					p.setPoint(200);
					p.setPointDesc("分享送积分");
					p.setCreateTime(new Date());
					myMenberFacade.saveMenberPoint(p);
					
					//保存我的分享
					MenberShareDTO s = new MenberShareDTO();
					s.setMenId(menberInfo.getSalesMenId());
					s.setShareMenId(menberInfo.getId());
					s.setCreateTime(new Date());
					myMenberShareFacade.save(s);
					
					menberInfo.setSign(1);
				}
    		}
    	}
    	
    	myMenberFacade.update(menberInfo);
    	request.getSession().setAttribute("wxmenber",menberInfo);//更新session
    	
		CommonMapping mping = null;
		if(menberInfo.getIsjoin() == 0){//未加入
			mping = new CommonMapping("保存成功!", getRealUri(mapping,"menber/recharge") + "?type=" + type + "&menId=" + menberInfo.getId() + "&leftMenu=wdzh", ActionConstent.ALERT);
		}else{
			mping = new CommonMapping("保存成功!", getRealUri(mapping,"menber/beforeEditFront") + "?type=" + type + "&leftMenu=grzl", ActionConstent.ALERT);
			
		}
		
		request.setAttribute("mping", mping);
		return mapping.findForward(ActionConstent.COMMON_MAPPING);
	}
	
	
	/**
	 * 检查会员信息是否完整 - 前台
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward checkMenberInfo(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception
	{
		StringBuilder sb = new StringBuilder();
		String type = request.getParameter("type");//类型：wap,web
		MenberDTO menber = null;
		if(null!=type){
			if(type.equals("wap")){
				menber = (MenberDTO)request.getSession().getAttribute("wxmenber");
	    	}else if(type.equals("web")){
	    		menber = (MenberDTO)request.getSession().getAttribute("pcmenber");
	    	}
		}
        if(null != menber)
        {
        	if(null == menber.getPid() || menber.getPid().equals("") || 
        			null == menber.getMobile() || menber.getMobile().equals("")){
        		sb.append("0");
        	}else{
        		sb.append("1");
        	}
        }else{
        	sb.append("请重新登录!");
        }
        
        try{
            ServletUtil.outputXML(response, sb.toString());
        }catch (IOException e){
            e.printStackTrace();
        }
        return null;
	}
	
	
	/**
	 * 加入计划前检查 - 前台
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward joinCheck(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception
	{
		String returnStr = "";
		String type = request.getParameter("type");//类型：wap,web
		MenberDTO menber = null;
		if(type.equals("wap")){
			menber = (MenberDTO)request.getSession().getAttribute("wxmenber");
    	}else if(type.equals("web")){
    		menber = (MenberDTO)request.getSession().getAttribute("pcmenber");
    	}
		
		//如果传了会员id，表示验证托管账户
		String menId = request.getParameter("menId");
		if(null!=menId && !menId.equals("")){
			menber = myMenberFacade.get(menId);
		}
		
        if(null != menber)
        {
        	if(menber.getBalanceFee().doubleValue()<12){//账户余额小于12元则跳转到充值中心
        		returnStr= "1";
        	}
        	
        	if(null!=menId && !menId.equals("")){//托管账户手机号可以不填，因为有小孩子
        		if(null == menber.getPid() || menber.getPid().equals("")){
            		returnStr= "0";
            	}
        	}else{
        		if(null == menber.getPid() || menber.getPid().equals("") || 
            			null == menber.getMobile() || menber.getMobile().equals("")){
            		returnStr= "0";
            	}
        	}
        	
        	if(returnStr.equals(""))
        	{
        		MenberDTO m = myMenberFacade.get(menber.getId());
            	m.setIsjoin(1);
            	m.setJoinTime(new Date());
            	double rf = m.getRightFee().doubleValue() + 300000;
				m.setRightFee(new BigDecimal(rf));//加入计划后，保额增加30万
            	
            	this.myMenberFacade.update(m);
            	
            	//如果该会员有推荐人，则送（200积分、200保额）给这个推荐人
        		if(null != m.getSalesMenId() && !m.getSalesMenId().equals("")){
        			MenberDTO saleMenber = myMenberFacade.get(m.getSalesMenId());
					if(null!=saleMenber){
						double rightFee = saleMenber.getRightFee().doubleValue() + 200;
						int point = saleMenber.getPoint() + 200;
						saleMenber.setRightFee(new BigDecimal(rightFee));//每邀请一个好友加入，赠送保额200元
						saleMenber.setPoint(point);//每邀请一个好友加入，赠送积分200元
						myMenberFacade.update(saleMenber);
						
						//保存积分历史
						MenberPointDTO p = new MenberPointDTO();
						p.setMenId(m.getSalesMenId());
						p.setPoint(200);
						p.setPointDesc("分享送积分");
						p.setCreateTime(new Date());
						myMenberFacade.saveMenberPoint(p);
						
						//保存我的分享
						MenberShareDTO s = new MenberShareDTO();
						s.setMenId(m.getSalesMenId());
						s.setShareMenId(m.getId());
						s.setCreateTime(new Date());
						myMenberShareFacade.save(s);
					}
        		}
            	
            	if(null==menId || menId.equals("")){
            		if(type.equals("wap")){
            			request.getSession().setAttribute("wxmenber",m);//更新session
                	}else if(type.equals("web")){
                		request.getSession().setAttribute("pcmenber",m);//更新session
                	}
            	}
            	
            	returnStr= "恭喜您成功加入了大象同舟会!";
        	}
        }else{
        	returnStr= "请重新登录!";
        }
        
        try{
            ServletUtil.outputXML(response, returnStr);
        }catch (IOException e){
            e.printStackTrace();
        }
        return null;
	}
	
	
	
	/**
	 * 充值初始化 - 前台会员中心
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward myAccount(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception
	{
		String type = request.getParameter("type");//类型：wap,web
		String returnPage = "";
		MenberDTO menber = null;
		if(type.equals("wap")){
			menber = (MenberDTO)request.getSession().getAttribute("wxmenber");
			returnPage = "myAccount_wap";
    	}else if(type.equals("web")){
    		menber = (MenberDTO)request.getSession().getAttribute("pcmenber");
    		returnPage = "myAccount_web";
    	}
        if(null != menber)
        {
        	MenberDTO dto = myMenberFacade.get(menber.getId());
        	request.setAttribute("menber", dto);
        	
        	//保障生效日期
        	if(null!=menber.getJoinTime()){
        		String effectTime = "";
                Calendar calendar = new GregorianCalendar(); 
                calendar.setTime(menber.getJoinTime()); 
                calendar.add(calendar.DATE,180);//把日期往后增加180天（整数往后推,负数往前移）
                Date date = calendar.getTime();
                effectTime = format2.format(date); 
                request.getSession().setAttribute("effectTime", effectTime);
        	}else{
        		request.getSession().setAttribute("effectTime", "");
        	}
            
        	return mapping.findForward(returnPage);
        }
		return null;
	}
	
	
	
	/**
	 * 我的邀请初台化 - 前台会员中心
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward myInvitation(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception
	{
		String type = request.getParameter("type");//类型：wap,web
		String returnPage = "";
		MenberDTO menber = null;
		if(type.equals("wap")){
			menber = (MenberDTO)request.getSession().getAttribute("wxmenber");
			returnPage = "myInvitation_wap";
    	}else if(type.equals("web")){
    		menber = (MenberDTO)request.getSession().getAttribute("pcmenber");
    		returnPage = "myInvitation_web";
    		String leftMenu = request.getParameter("leftMenu");
    		request.setAttribute("leftMenu", leftMenu);
    	}
        if(null != menber)
        {
    		return mapping.findForward(returnPage);
        }
		return null;
	}
	
	
//
//	/**
//	 * 我的邀请（宣传页） - 前台会员中心
//	 *
//	 * @param mapping
//	 * @param form
//	 * @param request
//	 * @param response
//	 * @return
//	 * @throws Exception
//	 */
//	public ActionForward myInvitationPage1(ActionMapping mapping,
//			ActionForm form, HttpServletRequest request,
//			HttpServletResponse response) throws Exception
//	{
//		String type = request.getParameter("type");//类型：wap,web
//		request.setAttribute("type", type);
//		String returnPage = "";
//		MenberDTO menber = null;
//		if(type.equals("wap")){
//			menber = (MenberDTO)request.getSession().getAttribute("wxmenber");
//			returnPage = "myInvitationPage1_wap";
//
//			//生成jsapi_ticket，用于签名算法
//			String appid = GlobalConfig.getProperty("weixin", "appid");//应用ID
//    		String appsecret = GlobalConfig.getProperty("weixin", "appsecret");//应用密钥
//			WeiXinOauth2Token token = WeixinUtil.getAccessToken(appid,appsecret);
////			System.out.println("accessToken:"+token.getAccessToken());
//			String jsapiTicket = WeixinUtil.getticket(token.getAccessToken());
////			System.out.println("jsapiTicket:"+jsapiTicket);
//			request.setAttribute("jsapiTicket", jsapiTicket);
//
//    	}else if(type.equals("web")){
//    		menber = (MenberDTO)request.getSession().getAttribute("pcmenber");
//    		returnPage = "myInvitationPage1_web";
//    		String leftMenu = request.getParameter("leftMenu");
//    		request.setAttribute("leftMenu", leftMenu);
//    	}
//        if(null != menber)
//        {
//    		return mapping.findForward(returnPage);
//        }
//		return null;
//	}
//
//
//
//	/**
//	 * 我的邀请（二维码） - 前台会员中心
//	 *
//	 * @param mapping
//	 * @param form
//	 * @param request
//	 * @param response
//	 * @return
//	 * @throws Exception
//	 */
//	public ActionForward myInvitationPage2(ActionMapping mapping,
//			ActionForm form, HttpServletRequest request,
//			HttpServletResponse response) throws Exception
//	{
//		String type = request.getParameter("type");//类型：wap,web
//		request.setAttribute("type", type);
//		String returnPage = "";
//		MenberDTO menber = null;
//		if(type.equals("wap")){
//			menber = (MenberDTO)request.getSession().getAttribute("wxmenber");
//			returnPage = "myInvitationPage2_wap";
//    	}else if(type.equals("web")){
//    		menber = (MenberDTO)request.getSession().getAttribute("pcmenber");
//    		returnPage = "myInvitationPage2_web";
//    		String leftMenu = request.getParameter("leftMenu");
//    		request.setAttribute("leftMenu", leftMenu);
//    	}
//        if(null != menber)
//        {
//        	String appid = GlobalConfig.getProperty("weixin", "appid");//应用ID
//    		String appsecret = GlobalConfig.getProperty("weixin", "appsecret");//应用密钥
//
//        	//获取永久二维码
//    		WeiXinOauth2Token token = WeixinUtil.getAccessToken(appid,appsecret);
//    		 //System.out.println("menberSaleApply AccessToken:"+token.getAccessToken());
//    		String ticket = WeixinUtil.getCodeImageTicket(token.getAccessToken(),menber.getId());
//    		//System.out.println("menberSaleApply ticket:"+ticket);
//    		String url = "https://mp.weixin.qq.com/cgi-bin/showqrcode?ticket="+ticket;
//    		request.setAttribute("applyURL",url);
//
//    		String jsapiTicket = WeixinUtil.getticket(token.getAccessToken());
////			System.out.println("jsapiTicket:"+jsapiTicket);
//			request.setAttribute("jsapiTicket", jsapiTicket);
//
//        	return mapping.findForward(returnPage);
//        }
//
//		return null;
//	}
//
//
//
//	/**
//	 * 分享邀请（二维码展示） - 前台
//	 *
//	 * @param mapping
//	 * @param form
//	 * @param request
//	 * @param response
//	 * @return
//	 * @throws Exception
//	 */
//	public ActionForward myInvitationPage2View(ActionMapping mapping,
//			ActionForm form, HttpServletRequest request,
//			HttpServletResponse response) throws Exception
//	{
//		System.out.println("======================myInvitationPage2View");
//		String type = request.getParameter("type");//类型：wap,web
//		String salesMenId = request.getParameter("salesMenId");
//		if(!type.equals("wap") || null==salesMenId || salesMenId.equals("")){
//        	//不合法
//        }else{
//        	String appid = GlobalConfig.getProperty("weixin", "appid");//应用ID
//    		String appsecret = GlobalConfig.getProperty("weixin", "appsecret");//应用密钥
//
//        	//获取永久二维码
//    		WeiXinOauth2Token token = WeixinUtil.getAccessToken(appid,appsecret);
//    		//System.out.println("menberSaleApply AccessToken:"+token.getAccessToken());
//    		String ticket = WeixinUtil.getCodeImageTicket(token.getAccessToken(),salesMenId);
//    		//System.out.println("menberSaleApply ticket:"+ticket);
//    		String url = "https://mp.weixin.qq.com/cgi-bin/showqrcode?ticket="+ticket;
//    		request.setAttribute("applyURL",url);
//
//        	return mapping.findForward("myInvitationPage2View_wap");
//        }
//
//		return null;
//	}
//
//
//	/**
//	 * 进入易企秀宣传页面
//	 *
//	 * @param mapping
//	 * @param form
//	 * @param request
//	 * @param response
//	 * @return
//	 * @throws Exception
//	 */
//	public ActionForward invitation(ActionMapping mapping,
//			ActionForm form, HttpServletRequest request,
//			HttpServletResponse response) throws Exception
//	{
//		String type = request.getParameter("type");//类型：wap,web
//		request.setAttribute("type", type);
//		String returnPage = "";
//		MenberDTO menber = null;
//		if(type.equals("wap")){
//			menber = (MenberDTO)request.getSession().getAttribute("wxmenber");
//			returnPage = "invitation_wap";
//
//    	}else if(type.equals("web")){
////    		menber = (MenberDTO)request.getSession().getAttribute("pcmenber");
////    		returnPage = "invitation_web";
//
//    	}
//        if(null != menber)
//        {
//    		return mapping.findForward(returnPage);
//        }
//		return null;
//	}
	
	/**
	 * 进入充值页面
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward recharge(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception
	{
		String type = request.getParameter("type");//类型：wap,web
		String menId = request.getParameter("menId");//要充值的会员id
		String returnPage = "";
		MenberDTO menber = null;
		if(type.equals("wap")){
			menber = (MenberDTO)request.getSession().getAttribute("wxmenber");
			returnPage = "recharge_wap";
			if(menId.equals("menu")){//微信菜单直接点击进入的
				menId = menber.getId();
			}
    	}else if(type.equals("web")){
    		menber = (MenberDTO)request.getSession().getAttribute("pcmenber");
    		returnPage = "recharge_web";
    	}
		if(null != menber)
        {
			MenberDTO dto = myMenberFacade.get(menber.getId());
        	request.setAttribute("menber", dto);
			request.setAttribute("type", type);
			
			MenberDTO m = myMenberFacade.get(menId);
			request.setAttribute("rechargeMenName", m.getRealName());
			request.setAttribute("rechargeMenId", menId);
			return mapping.findForward(returnPage);
        }
   
		return null;
	}
	
	/**
	 * 修改密码初始化 - 前台会员中心
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward beforeUpdatePW(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception
	{
		String type = request.getParameter("type");//类型：wap,web
		String returnPage = "";
		MenberDTO menber = null;
		if(type.equals("wap")){
			menber = (MenberDTO)request.getSession().getAttribute("wxmenber");
			returnPage = "updatePW_wap";
    	}else if(type.equals("web")){
    		menber = (MenberDTO)request.getSession().getAttribute("pcmenber");
    		returnPage = "updatePW_web";
    		String leftMenu = request.getParameter("leftMenu");
    		request.setAttribute("leftMenu", leftMenu);
    	}
        if(null != menber)
        {
    		request.setAttribute("menId", menber.getId());
    		return mapping.findForward(returnPage);
        }
		return null;
	}
	/**
	 * 修改密码 - 前台会员中心
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward updatePW(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception
	{
		String id = request.getParameter("id");
		String newPass1 = request.getParameter("newPass1");
		MenberDTO dto = myMenberFacade.get(id);
		
		String k = (StringUtils.isBlank(GlobalConfig.getProperty("bms", 
	      "sysusercreateKey"))) ? 
	      "123456565643450987657689876543267676787651234567" : 
	      GlobalConfig.getProperty("bms", "sysusercreateKey");
	    String password = com.sinovatech.common.util.Des.encrytWithBase64("DESede", newPass1, k);
	    dto.setPassword(password);
	    
		this.myMenberFacade.updateMenberPWBySql(dto);
		request.getSession().setAttribute("wxmenber",dto);//更新session
		
		String leftMenu = request.getParameter("leftMenu");
		request.setAttribute("leftMenu", leftMenu);
		CommonMapping mping = new CommonMapping("修改成功!", getRealUri(mapping,"menber/beforeUpdatePW") + "?type=web&leftMenu=" + leftMenu, ActionConstent.ALERT);
		request.setAttribute("mping", mping);
		return mapping.findForward(ActionConstent.COMMON_MAPPING);
	}
	
	
	
	
	public static void main(String[] ages){
//		String acColor = "%25E7%259A%2593%25E6%259C%2588%25E7%2599%25BD";
//		try {
//			acColor = java.net.URLDecoder.decode(acColor,"UTF-8");
//			System.out.println("acColor2:"+acColor);
//	    	acColor = java.net.URLDecoder.decode(acColor,"UTF-8"); 
//	    	System.out.println("acColor2:"+acColor);
//		} catch (UnsupportedEncodingException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		} 
		
	}	

}