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
	
	public void init(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception
	{
		this.myArticleCategoryFacade = (ArticleCategoryFacade) this.getBeanContext().getBean("myArticleCategoryFacade");
	}

	/**
	 * 文章分类列表 - 前台
	 * @param request
	 * @return
	 */
	public ActionForward articleCateList(ActionMapping mapping,
									 ActionForm form, HttpServletRequest request,
									 HttpServletResponse response) throws Exception
	{
		String type = request.getParameter("type");
		String parentCode = request.getParameter("parentCode");

		//查出指定主分类下所有子分类
		List<ArticleCategoryDTO> cateList = myArticleCategoryFacade.listByParent(parentCode);
		request.setAttribute("cateList", cateList);

		//菜单选中
		request.setAttribute("menu", "sfcx");

		if(type.equals("wap")){
			return mapping.findForward("articleCateList_wap");
		}else if(type.equals("web")){
			return mapping.findForward("articleCateList_web");
		}
		return null;
	}

}