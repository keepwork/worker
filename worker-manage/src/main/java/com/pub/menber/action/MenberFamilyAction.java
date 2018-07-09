package com.pub.menber.action;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Iterator;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.pub.menber.model.dto.MenberDTO;
import com.pub.menber.model.dto.MenberPointDTO;
import com.pub.menber.model.facade.MenberFacade;
import com.sinovatech.common.config.GlobalConfig;
import com.sinovatech.common.util.Validate;
import com.sinovatech.common.web.action.ActionConstent;
import com.sinovatech.common.web.action.BaseAdmAction;
import com.sinovatech.common.web.action.CommonMapping;
import com.sinovatech.common.web.limit.ExLimitUtil;
import com.sinovatech.common.web.limit.HqlProperty;
import com.sinovatech.common.web.limit.ILimitUtil;
import com.sinovatech.common.web.limit.LimitInfo;

/**
 * 托管账户管理
 * 
 * @author kevin(keepwork512@163.com)
 * @date Jan 3, 2016 9:31:15 PM
 */
public class MenberFamilyAction extends BaseAdmAction
{ 
	private SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	private SimpleDateFormat format2 = new SimpleDateFormat("yyyy-MM-dd");
	
//	private MenberFamilyFacade myMenberFamilyFacade;
	private MenberFacade myMenberFacade;
	
	public void init(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception
	{
//		this.myMenberFamilyFacade = (MenberFamilyFacade) this.getBeanContext().getBean("myMenberFamilyFacade");
		this.myMenberFacade = (MenberFacade) this.getBeanContext().getBean("myMenberFacade");
	}
	
	public ActionForward queryList(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception
	{
		// 列表控件的TableId值
		String tableId = "FamilyList";
		// 获取分页信息
		ILimitUtil limitUtil = new ExLimitUtil();
		LimitInfo limit = limitUtil.getLimitInfo(request, tableId, 10);
		
		String familyMenId = request.getParameter("familyMenId");
		limit.addFilterProperty(HqlProperty.getEq("familyMenId",familyMenId));
		
		// 查询
		List<MenberDTO> list = myMenberFacade.list(limit);
		List menberlist = new ArrayList();
		for (Iterator iterator = list.iterator(); iterator.hasNext();) {
			MenberDTO menber = (MenberDTO) iterator.next();
			menber.setRegTimeStr(format.format(menber.getRegTime()));
			//两个时间之间的天数 
			String dateStr1= format.format(menber.getRegTime()); 
			String dateStr2= format.format(new Date()); 
			long days = (format2.parse(dateStr2).getTime()-format2.parse(dateStr1).getTime())/(24*60*60*1000); 
			menber.setDays(days);
			menberlist.add(menber);
		}
		request.setAttribute("list", menberlist);
		
		// 设置分页信息
		limitUtil.setLimitInfo(request, limit);
		// 查询过滤、分页状态保留
		this.setActionAttribute(request, "backUrlStore", this.getActionContext(request).getCurentURL());

		request.setAttribute("familyMenId", familyMenId);
		return mapping.findForward("list");
	}

	
	public ActionForward beforeAdd(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		
		String familyMenId = request.getParameter("familyMenId");
		request.setAttribute("familyMenId", familyMenId);
		
		return mapping.findForward("add");
	}

	
	public ActionForward add(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		MenberDTO dto = (MenberDTO) form;
		dto.setRegTime(new Date());
//		dto.setLoginName(dto.getPid());
		
		dto.setPoint(100);//注册用户免费赠送100商城积分
    	dto.setSign(0);//签到
		dto.setIsjoin(0);//未加入计划
		dto.setType(3);//综合会员
		dto.setStatus(0);//正常
		
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
		
		String pid = dto.getPid();
		String pw = pid.substring(pid.length()-6, pid.length());
		String k = (StringUtils.isBlank(GlobalConfig.getProperty("bms", "sysusercreateKey"))) ? 
			      "123456565643450987657689876543267676787651234567" : 
			      GlobalConfig.getProperty("bms", "sysusercreateKey");
	    String password_ = com.sinovatech.common.util.Des.encrytWithBase64("DESede", pw, k);
	    dto.setPassword(password_);
		this.myMenberFacade.save(dto);
		
		//增加被托管人送100积分
		MenberDTO saleMenber = myMenberFacade.get(dto.getFamilyMenId());
		if(null!=saleMenber){
			int point = saleMenber.getPoint() + 100;
			saleMenber.setPoint(point);//增加被托管人送100积分
			myMenberFacade.update(saleMenber);
			//保存积分历史
			MenberPointDTO p = new MenberPointDTO();
			p.setMenId(dto.getFamilyMenId());
			p.setPoint(100);
			p.setPointDesc("增加被托管人送积分");
			p.setCreateTime(new Date());
			myMenberFacade.saveMenberPoint(p);
		}
		
		CommonMapping mping = new CommonMapping("保存成功!", getRealUri(mapping,"family/queryList") + "?familyMenId=" + dto.getFamilyMenId(), ActionConstent.ALERT);
		
		request.setAttribute("mping", mping);
		return mapping.findForward(ActionConstent.COMMON_MAPPING);
	}

	
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
		setActionAttribute(request, "beforeEdit.familyMenId", m.getFamilyMenId());
		setActionAttribute(request, "beforeEdit.sign", m.getSign());
		setActionAttribute(request, "beforeEdit.isjoin", m.getIsjoin());
		setActionAttribute(request, "beforeEdit.regTime", m.getRegTime());
		setActionAttribute(request, "beforeEdit.lastTime", m.getLastTime());
		setActionAttribute(request, "beforeEdit.openId", m.getOpenId());
		setActionAttribute(request, "beforeEdit.loginName", m.getLoginName());
		setActionAttribute(request, "beforeEdit.city", m.getCity());
		setActionAttribute(request, "beforeEdit.headimgurl", m.getHeadimgurl());
		setActionAttribute(request, "beforeEdit.rightFee", m.getRightFee());
		setActionAttribute(request, "beforeEdit.contributeFee", m.getContributeFee());
		setActionAttribute(request, "beforeEdit.realNameEmergency", m.getRealNameEmergency());
		setActionAttribute(request, "beforeEdit.mobileEmergency", m.getMobileEmergency());
		setActionAttribute(request, "beforeEdit.remind", m.getRemind());
		setActionAttribute(request, "beforeEdit.password", m.getPassword());
		request.setAttribute("m", m);
		
		String familyMenId = request.getParameter("familyMenId");
		request.setAttribute("familyMenId", familyMenId);
		return mapping.findForward("edit");
	}

	
	public ActionForward edit(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception
	{
		MenberDTO dto = (MenberDTO) form;
		// 设置不需要用户更新的数据，如主键等
		dto.setId((String) getActionAttribute(request, "beforeEdit.id"));
		dto.setLoginName(dto.getPid());
		dto.setPoint((Integer) getActionAttribute(request, "beforeEdit.point"));
		dto.setBalanceFee((BigDecimal) getActionAttribute(request, "beforeEdit.balanceFee"));
		dto.setSalesMenId((String) getActionAttribute(request, "beforeEdit.salesMenId"));
		dto.setFamilyMenId((String) getActionAttribute(request, "beforeEdit.familyMenId"));
		dto.setSign((Integer) getActionAttribute(request, "beforeEdit.sign"));
		dto.setIsjoin((Integer) getActionAttribute(request, "beforeEdit.isjoin"));
		dto.setRegTime((Date) getActionAttribute(request, "beforeEdit.regTime"));
		dto.setLastTime((Date) getActionAttribute(request, "beforeEdit.lastTime"));
		dto.setOpenId((String) getActionAttribute(request, "beforeEdit.openId"));
		dto.setLoginName((String) getActionAttribute(request, "beforeEdit.loginName"));
		dto.setCity((String) getActionAttribute(request, "beforeEdit.city"));
		dto.setHeadimgurl((String) getActionAttribute(request, "beforeEdit.headimgurl"));
		dto.setRightFee((BigDecimal) getActionAttribute(request, "beforeEdit.rightFee"));
		dto.setContributeFee((BigDecimal) getActionAttribute(request, "beforeEdit.contributeFee"));
		dto.setRealNameEmergency((String) getActionAttribute(request, "beforeEdit.realNameEmergency"));
		dto.setMobileEmergency((String) getActionAttribute(request, "beforeEdit.mobileEmergency"));
		dto.setRemind((Integer) getActionAttribute(request, "beforeEdit.remind"));
		dto.setPassword((String) getActionAttribute(request, "beforeEdit.password"));
		this.myMenberFacade.update(dto);
		CommonMapping mping = new CommonMapping("保存成功!", getRealUri(mapping,"family/queryList") + "?familyMenId=" + dto.getFamilyMenId(), ActionConstent.ALERT);
		
		request.setAttribute("mping", mping);
		return mapping.findForward(ActionConstent.COMMON_MAPPING);
	}

	
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

	
	public ActionForward delete(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception
	{
		String ids = (String) this.getActionAttribute(request,"beforeDelete.ids");
//		String ids = (String)request.getParameter("ids");
		// 验证方法， 如果为null或者为空则直接返回异常
		Validate.notBlank(ids, "common", "errorparameter");
		
		this.myMenberFacade.delete(ids);
		
//		MenberDTO menber = this.myMenberFacade.get(ids);
//		menber.setSalesMenId("");
//		this.myMenberFacade.update(menber);
		
		CommonMapping mping = new CommonMapping("删除成功!",(String) getActionAttribute(request, "backUrlStore"),ActionConstent.ALERT);
		request.setAttribute("mping", mping);
		return mapping.findForward(ActionConstent.COMMON_MAPPING);
	}

	
	
	
	
	/**
	 * 查看我的家庭 - 前台会员中心
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward familyList(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception
	{
		String type = request.getParameter("type");//类型：wap,web
		String returnPage = "";
		MenberDTO menber = null;
		if(type.equals("wap")){
			menber = (MenberDTO)request.getSession().getAttribute("wxmenber");
			returnPage = "familyList_wap";
    	}else if(type.equals("web")){
    		menber = (MenberDTO)request.getSession().getAttribute("pcmenber");
    		returnPage = "familyList_web";
    		String leftMenu = request.getParameter("leftMenu");
    		request.setAttribute("leftMenu", leftMenu);
    	}
        if(null != menber)
        {
        	List familyList = myMenberFacade.listFamilyMenbers(menber.getId());
        	
        	List list = new ArrayList();
        	for (Iterator iterator = familyList.iterator(); iterator.hasNext();) {
        		MenberDTO dto = (MenberDTO) iterator.next();
        		dto.setRegTimeStr(format.format(dto.getRegTime()));
        		
        		//两个时间之间的天数 
        		String dateStr1= format.format(dto.getRegTime()); 
        		String dateStr2= format.format(new Date()); 
        		long days = (format2.parse(dateStr2).getTime()-format2.parse(dateStr1).getTime())/(24*60*60*1000); 
        		dto.setDays(days);
        		
    			list.add(dto);
    		}
        	request.setAttribute("familyList", list);
        	
        	return mapping.findForward(returnPage);
        }
		return null;
	}
	
	
	/**
	 * 新增亲友初始化 - 前台
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward beforeAddFront(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		String type = request.getParameter("type");//类型：wap,web
		String returnPage = "";
		MenberDTO menber = null;
		if(type.equals("wap")){
			menber = (MenberDTO)request.getSession().getAttribute("wxmenber");
			returnPage = "familyAddFront_wap";
    	}else if(type.equals("web")){
    		menber = (MenberDTO)request.getSession().getAttribute("pcmenber");
    		returnPage = "familyAddFront_web";
    		String leftMenu = request.getParameter("leftMenu");
    		request.setAttribute("leftMenu", leftMenu);
    	}
        if(null != menber)
        {
    		return mapping.findForward(returnPage);
        }
		return null;
	}

	/**
	 * 新增亲友 - 前台
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward addFront(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		MenberDTO dto = (MenberDTO) form;
		String type = request.getParameter("type");//类型：wap,web
		String leftMenu = request.getParameter("leftMenu");

		dto.setRegTime(new Date());
//		dto.setLoginName(dto.getPid());
		dto.setPoint(100);//注册用户免费赠送100商城积分
    	dto.setSign(0);//签到
		dto.setIsjoin(0);//未加入计划
		if(type.equals("web")){
			dto.setType(2);//网站会员
		}else if(type.equals("wap")){
			dto.setType(1);//微信会员
		}
		dto.setStatus(0);//正常
		
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
		
		//根据手机号码查询openid
//		String appid = GlobalConfig.getProperty("weixin", "appid");//应用ID
//		String appsecret = GlobalConfig.getProperty("weixin", "appsecret");//应用密钥
//		WeiXinOauth2Token accessToken = WeixinUtil.getAccessToken(appid,appsecret);    
//		System.out.println("add family ============= accessToken obj:"+accessToken);
//		System.out.println("add family ============= accessToken str:"+accessToken.getAccessToken());
//		String openId = "";
//		if (null != accessToken) 
//        {    
//			openId = WeixinUtil.getOpenId(accessToken.getAccessToken());
//    		System.out.println("add family ============= openId:"+openId);
//    		dto.setOpenId(openId);
//        }
		
		String pid = dto.getPid();
		String pw = pid.substring(pid.length()-6, pid.length());
		String k = (StringUtils.isBlank(GlobalConfig.getProperty("bms", "sysusercreateKey"))) ? 
			      "123456565643450987657689876543267676787651234567" : 
			      GlobalConfig.getProperty("bms", "sysusercreateKey");
	    String password_ = com.sinovatech.common.util.Des.encrytWithBase64("DESede", pw, k);
	    dto.setPassword(password_);
		this.myMenberFacade.save(dto);
		
		//增加被托管人送100积分
		MenberDTO saleMenber = myMenberFacade.get(dto.getFamilyMenId());
		if(null!=saleMenber){
			int point = saleMenber.getPoint() + 100;
			saleMenber.setPoint(point);//增加被托管人送100积分
			myMenberFacade.update(saleMenber);
			//保存积分历史
			MenberPointDTO p = new MenberPointDTO();
			p.setMenId(dto.getFamilyMenId());
			p.setPoint(100);
			p.setPointDesc("增加被托管人送积分");
			p.setCreateTime(new Date());
			myMenberFacade.saveMenberPoint(p);
		}
		
		CommonMapping mping = new CommonMapping("保存成功!", getRealUri(mapping,"family/familyList") + "?type=" + type + "&leftMenu=" + leftMenu, ActionConstent.ALERT);
		
		request.setAttribute("mping", mping);
		return mapping.findForward(ActionConstent.COMMON_MAPPING);
	}

	/**
	 * 修改亲友初始化 - 前台
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
			returnPage = "familyEditFront_wap";
    	}else if(type.equals("web")){
    		menber = (MenberDTO)request.getSession().getAttribute("pcmenber");
    		returnPage = "familyEditFront_web";
    		String leftMenu = request.getParameter("leftMenu");
    		request.setAttribute("leftMenu", leftMenu);
    	}
        if(null != menber)
        {
        	String id = request.getParameter("id");
    		MenberDTO m = this.myMenberFacade.get(id);
    		setActionAttribute(request, "beforeEdit.regTime", m.getRegTime());
    		request.setAttribute("m", m);
    		
    		//保障生效日期
        	String effectTime = "";
        	if(null!=m.getJoinTime()){
        		Calendar calendar = new GregorianCalendar(); 
                calendar.setTime(m.getJoinTime()); 
                calendar.add(calendar.DATE,180);//把日期往后增加180天（整数往后推,负数往前移）
                Date date = calendar.getTime();
                effectTime = format2.format(date); 
        	}
            request.setAttribute("effectTime", effectTime);
    		
        	return mapping.findForward(returnPage);
        }
		return null;
		
	}

	/**
	 * 修改亲友 - 前台
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
		String leftMenu = request.getParameter("leftMenu");
		
		MenberDTO old = this.myMenberFacade.get(dto.getId());
		old.setPid(dto.getPid());
		old.setRealName(dto.getRealName());
		this.myMenberFacade.update(old);
		CommonMapping mping = new CommonMapping("保存成功!", getRealUri(mapping,"family/familyList") + "?type=" + type + "&leftMenu=" + leftMenu, ActionConstent.ALERT);
		request.setAttribute("mping", mping);
		return mapping.findForward(ActionConstent.COMMON_MAPPING);
	}

	/**
	 * 解除托管关系 - 前台
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward deleteFront(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception
	{
		String type = request.getParameter("type");//类型：wap,web
		String leftMenu = request.getParameter("leftMenu");
		String id = request.getParameter("id");
		this.myMenberFacade.delete(id);
//		MenberDTO menber = this.myMenberFacade.get(id);
//		menber.setSalesMenId("");
//		this.myMenberFacade.update(menber);
		CommonMapping mping = new CommonMapping("解除托管成功!",getRealUri(mapping,"family/familyList") + "?type=" + type + "&leftMenu=" + leftMenu,ActionConstent.ALERT);
		request.setAttribute("mping", mping);
		return mapping.findForward(ActionConstent.COMMON_MAPPING);
	}
	
}