package com.pub.menber.action;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.common.util.ServletUtil;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.pub.menber.model.dto.MenberDTO;
import com.pub.menber.model.dto.MenberPointDTO;
import com.pub.menber.model.facade.MenberFacade;
import com.pub.menber.model.facade.MenberPointFacade;
import com.sinovatech.common.util.Validate;
import com.sinovatech.common.web.action.ActionConstent;
import com.sinovatech.common.web.action.BaseAdmAction;
import com.sinovatech.common.web.action.CommonMapping;
import com.sinovatech.common.web.limit.ExLimitUtil;
import com.sinovatech.common.web.limit.HqlProperty;
import com.sinovatech.common.web.limit.ILimitUtil;
import com.sinovatech.common.web.limit.LimitInfo;

/**
 * 会员积分管理
 * 
 * @author kevin(keepwork512@163.com)
 * @date Dec 30, 2015 9:55:43 PM
 */
public class MenberPointAction extends BaseAdmAction
{ 
	private SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	private SimpleDateFormat format2 = new SimpleDateFormat("yyyy-MM-dd");
	private MenberPointFacade myMenberPointFacade;
	private MenberFacade myMenberFacade;
	
	public void init(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception
	{
		this.myMenberPointFacade = (MenberPointFacade) this.getBeanContext().getBean("myMenberPointFacade");
		this.myMenberFacade = (MenberFacade) this.getBeanContext().getBean("myMenberFacade");
	}
	
	public ActionForward queryList(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception
	{
		// 列表控件的TableId值
		String tableId = "PointList";

		// 获取分页信息
		ILimitUtil limitUtil = new ExLimitUtil();
		LimitInfo limit = limitUtil.getLimitInfo(request, tableId, 10);

		String menId = request.getParameter("menId");
		limit.addFilterProperty(HqlProperty.getEq("menId",menId));
		
		// 查询
		List<MenberPointDTO> list = myMenberPointFacade.list(limit);

		// 设置分页信息
		limitUtil.setLimitInfo(request, limit);
		// 查询过滤、分页状态保留
		this.setActionAttribute(request, "backUrlStore", this.getActionContext(request).getCurentURL());

		List pointList = new ArrayList();
		for (Iterator iterator = list.iterator(); iterator.hasNext();) {
			MenberPointDTO p = (MenberPointDTO) iterator.next();
			p.setCreateTimeStr(format.format(p.getCreateTime()));
			pointList.add(p);
		}
		
		request.setAttribute("list", pointList);
		return mapping.findForward("list");
	}

	
	public ActionForward beforeAdd(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		return mapping.findForward("add");
	}

	
	public ActionForward add(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		MenberPointDTO dto = (MenberPointDTO) form;
		CommonMapping mping=null;
		if(this.myMenberPointFacade.save(dto)){
			mping = new CommonMapping("保存成功!", getRealUri(mapping,"menber/queryList"), ActionConstent.ALERT);
		}else{
			mping = new CommonMapping("parent.alert('保存失败!');");
		}

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
		MenberPointDTO m = this.myMenberPointFacade.get(id);
		// 将当前编辑的数据中的不需要用户修改的数据保存的ActionContext中
		// 如主键等,代替使用隐藏域发送，更安全
		setActionAttribute(request, "beforeEdit.id", id);
		request.setAttribute("m", m);
		return mapping.findForward("edit");
	}

	
	public ActionForward edit(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception
	{
		MenberPointDTO dto = (MenberPointDTO) form;
		// 设置不需要用户更新的数据，如主键等
		dto.setId((String) getActionAttribute(request, "beforeEdit.id"));
		this.myMenberPointFacade.update(dto);
		CommonMapping mping = new CommonMapping("保存成功!", getRealUri(mapping,"menber/queryList"), ActionConstent.ALERT);
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
		List list = this.myMenberPointFacade.listByIds(ids);
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
		this.myMenberPointFacade.delete(ids);
		CommonMapping mping = new CommonMapping("删除成功!",(String) getActionAttribute(request, "backUrlStore"),ActionConstent.ALERT);
		request.setAttribute("mping", mping);
		return mapping.findForward(ActionConstent.COMMON_MAPPING);
	}


}