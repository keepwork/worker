package com.pub.menber.action;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.common.util.ServletUtil;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.pub.menber.model.dto.MenberDTO;
import com.pub.menber.model.dto.MenberExitDTO;
import com.pub.menber.model.facade.MenberExitFacade;
import com.pub.menber.model.facade.MenberFacade;
import com.sinovatech.bms.adm.model.dto.TBmsUserDTO;
import com.sinovatech.common.web.action.ActionConstent;
import com.sinovatech.common.web.action.BaseAdmAction;
import com.sinovatech.common.web.action.CommonMapping;
import com.sinovatech.common.web.limit.ExLimitUtil;
import com.sinovatech.common.web.limit.HqlProperty;
import com.sinovatech.common.web.limit.ILimitUtil;
import com.sinovatech.common.web.limit.LimitInfo;

/**
 * 退出计划管理
 * 
 * @author kevin(keepwork512@163.com)
 * @date Dec 30, 2015 9:55:43 PM
 */
public class MenberExitAction extends BaseAdmAction
{ 
	private SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	
	private MenberExitFacade myMenberExitFacade;
	private MenberFacade myMenberFacade;
	public void init(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception
	{
		this.myMenberExitFacade = (MenberExitFacade) this.getBeanContext().getBean("myMenberExitFacade");
		this.myMenberFacade = (MenberFacade) this.getBeanContext().getBean("myMenberFacade");
	}
	
	
	public ActionForward queryList(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception
	{
		// 列表控件的TableId值
		String tableId = "ExitList";

		// 获取分页信息
		ILimitUtil limitUtil = new ExLimitUtil();
		LimitInfo limit = limitUtil.getLimitInfo(request, tableId, 10);

		String menId = request.getParameter("menId");
		limit.addFilterProperty(HqlProperty.getEq("menId",menId));
		limit.setSortProperty("createTime");
		limit.setSortType("desc");
		
		// 查询
		List<MenberExitDTO> list = myMenberExitFacade.list(limit);

		// 设置分页信息
		limitUtil.setLimitInfo(request, limit);
		// 查询过滤、分页状态保留
		this.setActionAttribute(request, "backUrlStore", this.getActionContext(request).getCurentURL());

		List pointList = new ArrayList();
		for (Iterator iterator = list.iterator(); iterator.hasNext();) {
			MenberExitDTO p = (MenberExitDTO) iterator.next();
			p.setCreateTimeStr(format.format(p.getCreateTime()));
			if(null!=p.getAuditTime()){
				p.setAuditTimeStr(format.format(p.getAuditTime()));
			}
			
			MenberDTO men = this.myMenberFacade.get(p.getMenId());
			
			if(null!=men){
				p.setMenRealName(men.getRealName());
				p.setMenPid(men.getPid());
			}
			pointList.add(p);
		}
		
		request.setAttribute("list", pointList);
		return mapping.findForward("list");
	}

	
    
    
    /**
	 * 退出计划申请初始化
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward exitPlanInit(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception
	{
		String type = request.getParameter("type");//类型：wap,web
		String menId = request.getParameter("menId");
		String returnPage = "";
		if(type.equals("wap")){
			returnPage = "exitPlan_wap";
    	}else if(type.equals("web")){
    		returnPage = "exitPlan_web";
    		String leftMenu = request.getParameter("leftMenu");
    		request.setAttribute("leftMenu", leftMenu);
    	}
    	request.setAttribute("menId", menId);
    	return mapping.findForward(returnPage);
	}
	/**
	 * 退出计划申请检查
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward exitPlanCheck(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception
	{
		String menId = request.getParameter("menId");
		String returnStr = "0";
		
		MenberDTO menber = this.myMenberFacade.get(menId);
		if(null != menber)
        {
			if(menber.getIsjoin()==0){
				returnStr = "1";//您尚未加入计划
			}
			List list = this.myMenberExitFacade.getByMenId(menber.getId());
			if(null!=list && list.size()>0){
				returnStr = "2";//您当前已有退出申请，请等待审核结果！
			}
        }
   
		try{
            ServletUtil.outputXML(response, returnStr);
        }catch (IOException e){
            e.printStackTrace();
        }
		return null;
	}
	/**
	 * 退出计划申请
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward exitPlan(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception
	{
		String type = request.getParameter("type");//类型：wap,web
		String menId = request.getParameter("menId");
		String leftMenu = "";
		MenberDTO menber = this.myMenberFacade.get(menId);
		if(type.equals("wap")){
			
    	}else if(type.equals("web")){
    		leftMenu = request.getParameter("leftMenu");
    	}
        if(null != menber)
        {
        	MenberExitDTO dto = (MenberExitDTO) form;
        	dto.setMenId(menber.getId());
        	dto.setCreateTime(new Date());
        	dto.setAuditResult(1);
        	this.myMenberExitFacade.save(dto);
        	
        	CommonMapping mping = new CommonMapping("申请成功!", getRealUri(mapping,"menber/centerInit") + "?type=" + type+ "&leftMenu=" + leftMenu, ActionConstent.ALERT);
    		request.setAttribute("mping", mping);
    		return mapping.findForward(ActionConstent.COMMON_MAPPING);
        }
		return null;
	}
	
	/**
	 * 退出计划申请审核  - 后台
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward audit(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception
	{
		MenberExitDTO dto = (MenberExitDTO) form;
		MenberExitDTO old = this.myMenberExitFacade.get(dto.getId());
		old.setAuditDesc(dto.getAuditDesc());
		old.setAuditTime(new Date());
		old.setAuditResult(dto.getAuditResult());
		TBmsUserDTO user = getUser(request);
		old.setAuditor(user.getId());
		this.myMenberExitFacade.update(old,request);
		
		CommonMapping mping = new CommonMapping("审核成功!", getRealUri(mapping,"exit/queryList"), ActionConstent.ALERT);
		request.setAttribute("mping", mping);
		return mapping.findForward(ActionConstent.COMMON_MAPPING);
	}
	

}