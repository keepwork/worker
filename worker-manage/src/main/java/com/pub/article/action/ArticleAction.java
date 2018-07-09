package com.pub.article.action;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import com.common.util.ServletUtil;
import com.common.util.date.DateUtil;
import com.pub.article.model.dto.ArticleCategoryDTO;
import com.pub.article.model.dto.ArticleDTO;
import com.pub.article.model.facade.ArticleCategoryFacade;
import com.pub.article.model.facade.ArticleFacade;
import com.sinovatech.common.util.Validate;
import com.sinovatech.common.web.action.ActionConstent;
import com.sinovatech.common.web.action.BaseAdmAction;
import com.sinovatech.common.web.action.CommonMapping;
import com.sinovatech.common.web.limit.ExLimitUtil;
import com.sinovatech.common.web.limit.ILimitUtil;
import com.sinovatech.common.web.limit.LimitInfo;
import com.sinovatech.hd.tools.cache.CacheFactory;
import com.sinovatech.hd.tools.cache.ICache;

/**
 * 文章管理
 * 
 * @author kevin(keepwork512@163.com)
 * @date Dec 30, 2015 9:55:43 PM
 */
public class ArticleAction extends BaseAdmAction
{ 
	private static ICache cache = CacheFactory.newCache();
	
	private ArticleFacade myArticleFacade;
	private ArticleCategoryFacade myArticleCategoryFacade;
	
	public void init(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception
	{
		this.myArticleFacade = (ArticleFacade) this.getBeanContext().getBean("myArticleFacade");
		this.myArticleCategoryFacade = (ArticleCategoryFacade) this.getBeanContext().getBean("myArticleCategoryFacade");
	}
	
	/**
	 * 分布查询 - 后台
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
		String tableId = "ArticleList";

		// 获取分页信息
		ILimitUtil limitUtil = new ExLimitUtil();
		LimitInfo limit = limitUtil.getLimitInfo(request, tableId, 30);
		limit.setSortProperty("catCode,orderNum");
		limit.setSortType("asc");
		
		// 查询
		List<ArticleDTO> list = myArticleFacade.list(limit);
		// 设置分页信息
		limitUtil.setLimitInfo(request, limit);
		// 查询过滤、分页状态保留
		this.setActionAttribute(request, "backUrlStore", this.getActionContext(request).getCurentURL());

		// 保存数据
		List<ArticleDTO> artList = new ArrayList<ArticleDTO>(); 
		for (ArticleDTO article : list) {
			if(article.getCatCode()!=null && !article.getCatCode().equals("")){
				ArticleCategoryDTO cate = myArticleCategoryFacade.get(article.getCatCode());  
				article.setCatName(cate.getName());
			}
			artList.add(article);
		}
		request.setAttribute("list", artList);
		return mapping.findForward("list");
	}

	/**
	 * 新增初始化 - 后台
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward beforeAdd(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception
	{
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
			throws Exception
	{
		ArticleDTO dto = (ArticleDTO) form;
		CommonMapping mping=null;
		dto.setUpdateTime(new Date());
		if(this.myArticleFacade.save(dto)){
			mping = new CommonMapping("保存成功!", getRealUri(mapping,"article/queryList"), ActionConstent.ALERT);
		}else{
			mping = new CommonMapping("parent.alert('保存失败!');");
		}

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
		ArticleDTO m = this.myArticleFacade.get(id);
		// 将当前编辑的数据中的不需要用户修改的数据保存的ActionContext中
		// 如主键等,代替使用隐藏域发送，更安全
		setActionAttribute(request, "beforeEdit.id", id);
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
		ArticleDTO dto = (ArticleDTO) form;
		// 设置不需要用户更新的数据，如主键等
		//dto.setId((String) getActionAttribute(request, "beforeEdit.id"));
		dto.setUpdateTime(new Date());
		//dto.setContent(JSDecode.decode(dto.getContent()).toString());
		this.myArticleFacade.update(dto);
		CommonMapping mping = new CommonMapping("保存成功!", getRealUri(mapping,"article/queryList"), ActionConstent.ALERT);
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
		List list = this.myArticleFacade.listByIds(ids);
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
		this.myArticleFacade.delete(ids);
		CommonMapping mping = new CommonMapping("删除成功!",(String) getActionAttribute(request, "backUrlStore"),ActionConstent.ALERT);
		request.setAttribute("mping", mping);
		return mapping.findForward(ActionConstent.COMMON_MAPPING);
	}

}