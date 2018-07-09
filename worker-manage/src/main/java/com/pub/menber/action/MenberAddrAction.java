package com.pub.menber.action;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.common.util.ServletUtil;
import com.pub.menber.model.dto.MenberAddrDTO;
import com.pub.menber.model.dto.MenberDTO;
import com.pub.menber.model.facade.MenberAddrFacade;
import com.sinovatech.common.util.Validate;
import com.sinovatech.common.web.action.ActionConstent;
import com.sinovatech.common.web.action.BaseAdmAction;
import com.sinovatech.common.web.action.CommonMapping;
import com.sinovatech.common.web.limit.ExLimitUtil;
import com.sinovatech.common.web.limit.ILimitUtil;
import com.sinovatech.common.web.limit.LimitInfo;

/**
 * 配送地址管理
 * 
 * @author kevin(keepwork512@163.com)
 * @date Dec 30, 2015 9:55:43 PM
 */
public class MenberAddrAction extends BaseAdmAction
{ 
	private MenberAddrFacade myMenberAddrFacade;
	public void init(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception
	{
		this.myMenberAddrFacade = (MenberAddrFacade) this.getBeanContext().getBean("myMenberAddrFacade");
	}
	
	/**
	 * 查询地址 - 后台
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
		String tableId = "AddrList";

		// 获取分页信息
		ILimitUtil limitUtil = new ExLimitUtil();
		LimitInfo limit = limitUtil.getLimitInfo(request, tableId, 10);

		// 查询
		List<MenberAddrDTO> list = myMenberAddrFacade.list(limit);

		// 设置分页信息
		limitUtil.setLimitInfo(request, limit);

		// 查询过滤、分页状态保留
		this.setActionAttribute(request, "backUrlStore", this.getActionContext(request).getCurentURL());

		
		request.setAttribute("list", list);
		return mapping.findForward("list");
	}

	/**
	 * 新增地址初始化 - 后台
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
	 * 新增地址 - 后台
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
		MenberAddrDTO dto = (MenberAddrDTO) form;
		CommonMapping mping=null;
		if(this.myMenberAddrFacade.save(dto)){
			mping = new CommonMapping("保存成功!", getRealUri(mapping,"menber/queryList"), ActionConstent.ALERT);
		}else{
			mping = new CommonMapping("parent.alert('保存失败!');");
		}

		request.setAttribute("mping", mping);
		return mapping.findForward(ActionConstent.COMMON_MAPPING);
	}

	/**
	 * 修改地址初始化 - 后台
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
		MenberAddrDTO m = this.myMenberAddrFacade.get(id);
		// 将当前编辑的数据中的不需要用户修改的数据保存的ActionContext中
		// 如主键等,代替使用隐藏域发送，更安全
		setActionAttribute(request, "beforeEdit.id", id);
		request.setAttribute("m", m);
		return mapping.findForward("edit");
	}

	/**
	 * 修改地址 - 后台
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
		MenberAddrDTO dto = (MenberAddrDTO) form;
		// 设置不需要用户更新的数据，如主键等
		dto.setId((String) getActionAttribute(request, "beforeEdit.id"));
		this.myMenberAddrFacade.update(dto);
		CommonMapping mping = new CommonMapping("保存成功!", getRealUri(mapping,"menber/queryList"), ActionConstent.ALERT);
		request.setAttribute("mping", mping);
		return mapping.findForward(ActionConstent.COMMON_MAPPING);
	}

	/**
	 * 删除地址初始化 - 后台
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
		List list = this.myMenberAddrFacade.listByIds(ids);
		this.setActionAttribute(request, "beforeDelete.ids", ids);
		request.setAttribute("list", list);
		return mapping.findForward("deleteConfirm");
	}

	/**
	 * 删除地址 - 后台
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
		this.myMenberAddrFacade.delete(ids);
		CommonMapping mping = new CommonMapping("删除成功!",(String) getActionAttribute(request, "backUrlStore"),ActionConstent.ALERT);
		request.setAttribute("mping", mping);
		return mapping.findForward(ActionConstent.COMMON_MAPPING);
	}

}