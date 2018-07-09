package com.pub.article.action;

import java.util.ArrayList;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import com.pub.article.model.dto.ArticleCategoryDTO;
import com.pub.article.model.facade.ArticleCategoryFacade;
import com.sinovatech.common.util.Validate;
import com.sinovatech.common.web.action.ActionConstent;
import com.sinovatech.common.web.action.BaseAdmAction;
import com.sinovatech.common.web.action.CommonMapping;
import com.sinovatech.common.web.limit.ExLimitUtil;
import com.sinovatech.common.web.limit.ILimitUtil;
import com.sinovatech.common.web.limit.LimitInfo;

/**
 * 文章分类管理
 * 
 * @author kevin(keepwork512@163.com)
 * @date Dec 30, 2015 9:55:43 PM
 */
public class ArticleCategoryAction extends BaseAdmAction
{ 
	private ArticleCategoryFacade myArticleCategoryFacade;
	
	public ActionForward queryList(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception
	{
		// 列表控件的TableId值
		String tableId = "ArticleCategoryList";

		// 获取分页信息
		ILimitUtil limitUtil = new ExLimitUtil();
		LimitInfo limit = limitUtil.getLimitInfo(request, tableId, 30);
		limit.setSortProperty("parentCode,orderNum");
		limit.setSortType("asc");
		
		// 查询
		List<ArticleCategoryDTO> list = myArticleCategoryFacade.list(limit);

		// 设置分页信息
		limitUtil.setLimitInfo(request, limit);

		// 查询过滤、分页状态保留
		this.setActionAttribute(request, "backUrlStore", this.getActionContext(request).getCurentURL());

		// 保存数据
		List<ArticleCategoryDTO> cateList = new ArrayList<ArticleCategoryDTO>(); 
		for (ArticleCategoryDTO cat1 : list) {
			if(cat1.getParentCode()!=null && !cat1.getParentCode().equals("")){
				ArticleCategoryDTO cat2 = myArticleCategoryFacade.get(cat1.getParentCode());  
				cat1.setParentName(cat2.getName());
			}
			cateList.add(cat1);
		}
		request.setAttribute("list", cateList);
		return mapping.findForward("list");
	}

	
	public ActionForward beforeAdd(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception
	{
		return mapping.findForward("add");
	}

	
	public ActionForward add(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception
	{
		ArticleCategoryDTO dto = (ArticleCategoryDTO) form;
		CommonMapping mping=null;
		if(this.myArticleCategoryFacade.save(dto)){
			mping = new CommonMapping("保存成功!", getRealUri(mapping,"articleCate/queryList"), ActionConstent.ALERT);
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
		String code = request.getParameter("code");
		// 验证方法， 如果为null或者为空则直接返回异常
		Validate.notBlank(code, "common", "errorparameter");
		ArticleCategoryDTO m = this.myArticleCategoryFacade.get(code);
		// 将当前编辑的数据中的不需要用户修改的数据保存的ActionContext中
		// 如主键等,代替使用隐藏域发送，更安全
		setActionAttribute(request, "beforeEdit.code", code);
		request.setAttribute("m", m);
		return mapping.findForward("edit");
	}

	
	public ActionForward edit(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception
	{
		ArticleCategoryDTO dto = (ArticleCategoryDTO) form;
		// 设置不需要用户更新的数据，如主键等
		dto.setCode((String) getActionAttribute(request, "beforeEdit.code"));
		if(null != dto.getParentCode() && dto.getParentCode().equals(dto.getCode())){
			dto.setParentCode("");
		}
		this.myArticleCategoryFacade.update(dto);
		CommonMapping mping = new CommonMapping("保存成功!", getRealUri(mapping,"articleCate/queryList"), ActionConstent.ALERT);
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
		List list = this.myArticleCategoryFacade.listByIds(ids);
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
		this.myArticleCategoryFacade.delete(ids);
		CommonMapping mping = new CommonMapping("删除成功!",(String) getActionAttribute(request, "backUrlStore"),ActionConstent.ALERT);
		request.setAttribute("mping", mping);
		return mapping.findForward(ActionConstent.COMMON_MAPPING);
	}

	
	public void init(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception
	{
		this.myArticleCategoryFacade = (ArticleCategoryFacade) this.getBeanContext().getBean("myArticleCategoryFacade");
	}


}