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

import com.sinovatech.bms.adm.model.dto.TBmsLocationDTO;
import com.sinovatech.bms.adm.model.dto.TBmsUserDTO;
import com.sinovatech.bms.adm.model.facade.BmsLocationFacade;
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
//	private BmsLocationFacade myBmsLocationFacade;
//	private MenberPointFacade myMenberPointFacade;
//	private MenberShareFacade myMenberShareFacade;
	
	public void init(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception
	{
		this.myMenberFacade = (MenberFacade) this.getBeanContext().getBean("myMenberFacade");
//		this.myBmsLocationFacade = ((BmsLocationFacade)getBeanContext().getBean("myBmsLocationFacade"));
//		this.myMenberPointFacade = (MenberPointFacade) this.getBeanContext().getBean("myMenberPointFacade");
//		this.myMenberShareFacade = (MenberShareFacade) this.getBeanContext().getBean("myMenberShareFacade");
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

		//1：微信客户，2：工人，3-工人申请中
		String type = request.getParameter("type");
		request.setAttribute("type", type);

		//登录用户权限过滤
		TBmsUserDTO loginUser = getUser(request);
		String locationId = "";
		if(!loginUser.getUserLoginName().equals("admin") && type.equals("2")){
			locationId = loginUser.getTbTBmsLocationDTO().getId();
		}

		// 查询
		List<MenberDTO> list = myMenberFacade.list(limit,type,locationId);

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
			if(null != menber.getJoinTime()){
				menber.setJoinTimeStr(format.format(menber.getJoinTime()));
			}
			if(null != menber.getTbTBmsLocationDTO()){
				menber.setLocationName( menber.getTbTBmsLocationDTO().getName());
			}else{
				menber.setLocationName("未指派");
			}

			menberlist.add(menber);
		}
		request.setAttribute("list", menberlist);
		
		String listShowType = request.getParameter("listShowType");
		request.setAttribute("listShowType", listShowType);
		
		if(type.equals("3")){
			return mapping.findForward("auditList");
		}else{
			return mapping.findForward("list");
		}
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

		//1：微信客户，2：工人
		String type = request.getParameter("type");
		request.setAttribute("type", type);

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
		mping = new CommonMapping("保存成功!", getRealUri(mapping,"menber/queryList") + "?type="+dto.getType(), ActionConstent.ALERT);
		
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
		CommonMapping mping = new CommonMapping("保存成功!", getRealUri(mapping,"menber/queryList") + "?type="+dto.getType(), ActionConstent.ALERT);
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
	public ActionForward pointList(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception
	{
		// 列表控件的TableId值
		String tableId = "PointList";

		// 获取分页信息
		ILimitUtil limitUtil = new ExLimitUtil();
		LimitInfo limit = limitUtil.getLimitInfo(request, tableId, 10);

		String type =  request.getParameter("type");
		// 查询
		List<MenberDTO> list = myMenberFacade.list(limit,type,"");

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
		return mapping.findForward("list");
	}


	/**
	 * 工人申请审核  - 后台
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward workAudit(ActionMapping mapping,
							   ActionForm form, HttpServletRequest request,
							   HttpServletResponse response) throws Exception
	{
		MenberDTO men = (MenberDTO) form;
		MenberDTO old = this.myMenberFacade.get(men.getId());
		old.setIsjoin(men.getIsjoin());
		old.setEffectTime(new Date());
		old.setFax(men.getFax());
		TBmsUserDTO user = getUser(request);
		old.setFamilyMenId(user.getId());

		if(men.getIsjoin()==1){
			old.setType(2);
		}
		this.myMenberFacade.update(old);

		CommonMapping mping = new CommonMapping("审核成功!", getRealUri(mapping,"menber/queryList") + "?type=3", ActionConstent.ALERT);
		request.setAttribute("mping", mping);
		return mapping.findForward(ActionConstent.COMMON_MAPPING);
	}

}