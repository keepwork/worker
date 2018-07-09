package com.pub.seo.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.pub.seo.model.dto.SeoDTO;
import com.pub.seo.model.facade.SeoFacade;
import com.sinovatech.common.web.action.ActionConstent;
import com.sinovatech.common.web.action.BaseAdmAction;
import com.sinovatech.common.web.action.CommonMapping;
import com.sinovatech.hd.tools.cache.CacheFactory;
import com.sinovatech.hd.tools.cache.ICache;

/**
 * SEO管理
 * 
 * @author kevin(keepwork512@163.com)
 * @date Dec 30, 2015 9:55:43 PM
 */
public class SeoAction extends BaseAdmAction
{ 
	private static ICache cache = CacheFactory.newCache();
	
	private SeoFacade mySeoFacade;
	
	public void init(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception
	{
		this.mySeoFacade = (SeoFacade) this.getBeanContext().getBean("mySeoFacade");
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
		SeoDTO m = this.mySeoFacade.get("1");
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
		SeoDTO dto = (SeoDTO) form;
		this.mySeoFacade.update(dto);
		CommonMapping mping = new CommonMapping("保存成功!", getRealUri(mapping,"seo/beforeEdit"), ActionConstent.ALERT);
		request.setAttribute("mping", mping);
		return mapping.findForward(ActionConstent.COMMON_MAPPING);
	}

	
	
	/**
	 * 同步到缓存
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward synRedis(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception
	{
		SeoDTO m = this.mySeoFacade.get("1");
		cache.set("seo_title", m.getTitle());
		cache.set("seo_keywords", m.getKeywords());
		cache.set("seo_description", m.getDescription());
		cache.set("seo_generator", m.getGenerator());
		
		CommonMapping mping = new CommonMapping("同步成功!", getRealUri(mapping,"seo/beforeEdit"), ActionConstent.ALERT);
		request.setAttribute("mping", mping);
		return mapping.findForward(ActionConstent.COMMON_MAPPING);
	}


}