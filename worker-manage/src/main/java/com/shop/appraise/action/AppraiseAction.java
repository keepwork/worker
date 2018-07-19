package com.shop.appraise.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.sinovatech.common.util.Validate;
import com.sinovatech.common.web.action.ActionConstent;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import com.shop.appraise.model.dto.AppraiseDTO;
import com.shop.appraise.model.facade.AppraiseFacade;
import com.sinovatech.common.web.action.BaseAdmAction;
import com.sinovatech.common.web.action.CommonMapping;

/**
 * 评价管理
 * 
 * @author BruceKobe(javalc@163.com)
 * @date Dec 30, 2018 9:55:43 PM
 */
public class AppraiseAction extends BaseAdmAction
{ 
	private AppraiseFacade myAppraiseFacade;
	
	public void init(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception
	{
		this.myAppraiseFacade = (AppraiseFacade) this.getBeanContext().getBean("myAppraiseFacade");
	}

	/**
	 * 评价详情查看 - 后台
	 *
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward appraiseView(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception
	{
		String id = request.getParameter("appraiseId");
		// 验证方法， 如果为null或者为空则直接返回异常
		Validate.notBlank(id, "common", "errorparameter");
		AppraiseDTO m = this.myAppraiseFacade.get(id);
		// 将当前编辑的数据中的不需要用户修改的数据保存的ActionContext中
		// 如主键等,代替使用隐藏域发送，更安全
		setActionAttribute(request, "appraiseEdit.id", id);
		request.setAttribute("m", m);
		return mapping.findForward("view");
	}


	/**
	 * 评价隐藏 - 后台
	 *
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward appraiseHidden(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception
	{
		String appraiseId = (String)getActionAttribute(request, "appraiseEdit.id");
		AppraiseDTO dto = myAppraiseFacade.get(appraiseId);
		dto.setIsShow("0");
		myAppraiseFacade.update(dto);
		CommonMapping mping = new CommonMapping("保存成功!", getRealUri(mapping,"order/queryList"), ActionConstent.ALERT);
		request.setAttribute("mping", mping);
		return mapping.findForward(ActionConstent.COMMON_MAPPING);
	}


}